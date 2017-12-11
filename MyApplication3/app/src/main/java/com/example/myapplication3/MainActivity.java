package com.example.myapplication3;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication3.broadcast.Parameter;
import com.example.myapplication3.broadcast.PopBroadcastReceiver;
import com.example.myapplication3.pop.PopWindow;

import net.flyget.bluetoothhelper.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends Activity {
	private static final int REQUEST_ENABLE_BT = 0;
	private static final int REQUEST_CONNECT_DEVICE = 1;

    private static final int sendNum = 1;

    private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";

	private List<Integer> mBuffer;

	private static final String TAG = "MainActivity";
	private BluetoothAdapter mBluetoothAdapter;
	private ConnectThread mConnectThread;
	public ConnectedThread mConnectedThread;
	private Button holderBtn,cupBtn,mHomeBtn,keyUp,keyDown,keyLeft,keyRight,keyFront,keyBack;
	private TextView mTextView;
	private EditText mEditText;
	private static final int MSG_NEW_DATA = 3;
	private String mTitle;
	//新加广播
	private PopBroadcastReceiver popBroadcastReceiver = new PopBroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {

			if (Parameter.isSendPress) {
				Parameter.isSendPress = false;
				String tmps = " " + Parameter.sendString + " ";
				byte[] tt = tmps.getBytes();
				mConnectedThread.write(tt);
			}

			if (Parameter.isReset) {
				Parameter.isReset = false;
				byte[] tt = new byte[2];
				tt[0] = Parameter.RESET;
				tt[1] = (byte)' ';
				mConnectedThread.write(tt);
			}

			if (Parameter.isStepChange) {
				Parameter.isStepChange = false;
				byte[] tt = new byte[2*sendNum];
				for (int i = 0; i < sendNum; i++){
					tt[i*2] = (byte)(Parameter.STEP);tt[i*2+1] = (byte)(' ');
				}
				mConnectedThread.write(tt);
				((TextView)findViewById(R.id.tv_down)).setText("下按键： 停止状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
				((TextView)findViewById(R.id.tv_up)).setText("上按键： 停止状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
				((TextView)findViewById(R.id.tv_left)).setText("左按键： 停止状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
				((TextView)findViewById(R.id.tv_right)).setText("右按键： 停止状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
			}

			if (Parameter.isSliderChange) {
				Parameter.isSliderChange = false;
				int temp = Parameter.sliderValue;
				Parameter.sliderValue = 0;
				String tmps = String.valueOf(temp);
				tmps = Parameter.whichSlider + "  " + tmps + "  ";
				byte[] tt = tmps.getBytes();
				mConnectedThread.write(tt);
			}

			if (Parameter.isGetNewDate) {
				Parameter.isGetNewDate = false;
				byte[] tt = new byte[2*sendNum];
				for (int i = 0; i < sendNum; i++){
					tt[i*2] = (byte)(Parameter.newDate);tt[i*2+1] = (byte)(' ');
				}
				mConnectedThread.write(tt);
			}else {
				switch (Parameter.pop) {
					case Parameter.PAUSE:
						if (Parameter.isPause) {
							Parameter.isPause = false;
						} else {
							Parameter.isPause = true;
						}
						break;
					case Parameter.SCAN:
						if (mConnectThread != null) {
							mConnectThread.cancel();
							mConnectThread = null;
						}
						Intent serverIntent = new Intent(context, DeviceListActivity.class);
						startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
						break;

					case Parameter.SETTING:
//						byte[] tt = new byte[10];
//						for (int i = 0; i < 10; i++) {
//							tt[i] = (byte) ('a' + i);
//						}
//						mConnectedThread.write(tt);
						break;

					case Parameter.ABOUT:
						startActivity(new Intent(context, AboutActivity.class));
						break;
					case Parameter.EXIT:
						finish();
						break;
				}
				Parameter.pop = "";
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_test);

		//小房子按键
		mHomeBtn = (Button) findViewById(R.id.btn_title_right);
		mHomeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				PopWindow popWindow = new PopWindow(MainActivity.this,Parameter.isPause);
				popWindow.showPopupWindow(findViewById(R.id.btn_title_right));
			}
		});

		//真空吸杯按键
		cupBtn = (Button) findViewById(R.id.cup);
		cupBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (Parameter.isCupOn) {

					Parameter.isCupOn = false;
                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.CUP_ON);tt[i*2+1] = (byte)(' ');
                    }
                    cupBtn.setText("真空吸杯开");
					mConnectedThread.write(tt);
				}else {
					Parameter.isCupOn = true;
                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.CUP_OFF);tt[i*2+1] = (byte)(' ');
                    }
					cupBtn.setText("真空吸杯关");
					mConnectedThread.write(tt);
				}
			}
		});

		//夹持器按键
		holderBtn = (Button) findViewById(R.id.holder);
		holderBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (Parameter.isHolderOn) {

					Parameter.isHolderOn = false;

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.HOLDER_ON);tt[i*2+1] = (byte)(' ');
                    }

					holderBtn.setText("夹持器打开");
					mConnectedThread.write(tt);
				}else {
					Parameter.isHolderOn = true;

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.HOLDER_OFF);tt[i*2+1] = (byte)(' ');
                    }

					holderBtn.setText("夹持器关闭");
					mConnectedThread.write(tt);
				}
			}
		});

		//前按键
		keyFront = (Button) findViewById(R.id.front);
		keyFront.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
		keyFront.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.FRONT_KEY_DOWN);tt[i*2+1] = (byte)(' ');
                    }

					mConnectedThread.write(tt);
				}else if (motionEvent.getAction() == MotionEvent.ACTION_UP){

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.FRONT_KEY_UP);tt[i*2+1] = (byte)(' ');
                    }

					mConnectedThread.write(tt);
				}
				return false;
			}
		});

		//后按键
		keyBack = (Button) findViewById(R.id.back);
		keyBack.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
		keyBack.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.BACK_KEY_DOWN);tt[i*2+1] = (byte)(' ');
                    }

					mConnectedThread.write(tt);
				}else if (motionEvent.getAction() == MotionEvent.ACTION_UP){

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.BACK_KEY_UP);tt[i*2+1] = (byte)(' ');
                    }

					mConnectedThread.write(tt);
				}
				return false;
			}
		});

		//下按键
		keyDown = (Button) findViewById(R.id.down);
		keyDown.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
		keyDown.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.DOWN_KEY_DOWN);tt[i*2+1] = (byte)(' ');
                    }

					mConnectedThread.write(tt);
					((TextView)findViewById(R.id.tv_down)).setText("下按键： 发送状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
				}else if (motionEvent.getAction() == MotionEvent.ACTION_UP){

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.DOWN_KEY_UP);tt[i*2+1] = (byte)(' ');
                    }

					((TextView)findViewById(R.id.tv_down)).setText("下按键： 停止状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
					mConnectedThread.write(tt);
				}
				return false;
			}
		});

		//上按键
		keyUp = (Button) findViewById(R.id.up);
		keyUp.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
		keyUp.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.UP_KEY_DOWN);tt[i*2+1] = (byte)(' ');
                    }

					((TextView)findViewById(R.id.tv_up)).setText("上按键： 发送状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
					mConnectedThread.write(tt);
				}else if (motionEvent.getAction() == MotionEvent.ACTION_UP){

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.UP_KEY_UP);tt[i*2+1] = (byte)(' ');
                    }

					((TextView)findViewById(R.id.tv_up)).setText("上按键： 停止状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
					mConnectedThread.write(tt);
				}
				return false;
			}
		});

		//左按键
		keyLeft = (Button) findViewById(R.id.left);
		keyLeft.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
		keyLeft.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.LEFT_KEY_DOWN);tt[i*2+1] = (byte)(' ');
                    }

					((TextView)findViewById(R.id.tv_left)).setText("左按键： 发送状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
					mConnectedThread.write(tt);
				}else if (motionEvent.getAction() == MotionEvent.ACTION_UP){

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.LEFT_KEY_UP);tt[i*2+1] = (byte)(' ');
                    }

					((TextView)findViewById(R.id.tv_left)).setText("左按键： 停止状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
					mConnectedThread.write(tt);
				}
				return false;
			}
		});

		//右按键
		keyRight = (Button) findViewById(R.id.right);
		keyRight.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
		keyRight.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.RIGHT_KEY_DOWN);tt[i*2+1] = (byte)(' ');
                    }

					((TextView)findViewById(R.id.tv_right)).setText("右按键： 发送状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
					mConnectedThread.write(tt);
				}else if (motionEvent.getAction() == MotionEvent.ACTION_UP){

                    byte[] tt = new byte[2*sendNum];
                    for (int i = 0; i < sendNum; i++){
                        tt[i*2] = (byte)(Parameter.RIGUT_KEY_UP);tt[i*2+1] = (byte)(' ');
                    }

					((TextView)findViewById(R.id.tv_right)).setText("右按键： 停止状态...  步长:  0" + (int)((int)Parameter.STEP - 68));
					mConnectedThread.write(tt);
				}
				return false;
			}
		});

		mTextView = (TextView) findViewById(R.id.mTextView);
		mEditText = (EditText) findViewById(R.id.mEditText);

		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		// If the adapter is null, then Bluetooth is not supported
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, "Bluetooth is not available",
					Toast.LENGTH_LONG).show();
			finish();
			return;
		}

		mBuffer = new ArrayList<Integer>();

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (Parameter.isHolderOn) {
			holderBtn.setText("夹持器关闭");
		}else {
			holderBtn.setText("夹持器打开");
		}

		if (Parameter.isCupOn) {
			cupBtn.setText("真空吸杯关");
		}else {
			cupBtn.setText("真空吸杯开");
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		// If BT is not on, request that it be enabled.
		// setupChat() will then be called during onActivityResult
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
		}
		IntentFilter filter = new IntentFilter("com.example.broadcast.POP_BROADCAST");
		registerReceiver(popBroadcastReceiver, filter);
	}


	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
				case MSG_NEW_DATA:
					if (Parameter.isPause) {
						mBuffer.clear();
						break;
					} else {
						StringBuffer buf = new StringBuffer();
						synchronized (mBuffer) {
							for (int i : mBuffer) {
								buf.append((char) i);
							}
						}
//						Toast.makeText(MainActivity.this, buf.toString(), Toast.LENGTH_SHORT).show();
						mTextView.setText(buf.toString());
						Parameter.RECEIVE = buf.toString();
						sendBroadcast(new Intent("com.example.broadcast.SET_BROADCAST"));
						mBuffer.clear();
					}
					break;

				default:
					break;
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case REQUEST_ENABLE_BT:
			if (resultCode == Activity.RESULT_OK) {
				// Bluetooth is now enabled Launch the DeviceListActivity to see
				// devices and do scan
				Intent serverIntent = new Intent(this, DeviceListActivity.class);
				startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
			} else {
				// User did not enable Bluetooth or an error occured
				Log.d(TAG, "BT not enabled");
				Toast.makeText(this, "BT not enabled", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			break;
		case REQUEST_CONNECT_DEVICE:
			if (resultCode != Activity.RESULT_OK) {
				return;
			} else {
				String address = data.getExtras().getString(
						DeviceListActivity.EXTRA_DEVICE_ADDRESS);
				// Get the BLuetoothDevice object
				BluetoothDevice device = mBluetoothAdapter
						.getRemoteDevice(address);
				// Attempt to connect to the device
				connect(device);
			}
			break;
		}
	}

	public void connect(BluetoothDevice device) {
		Log.d(TAG, "connect to: " + device);
		// Start the thread to connect with the given device
		mConnectThread = new ConnectThread(device);
		mConnectThread.start();
	}

	/**
	 * This thread runs while attempting to make an outgoing connection with a
	 * device. It runs straight through; the connection either succeeds or
	 * fails.
	 */
	private class ConnectThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final BluetoothDevice mmDevice;

		public ConnectThread(BluetoothDevice device) {
			mmDevice = device;
			BluetoothSocket tmp = null;

			// Get a BluetoothSocket for a connection with the
			// given BluetoothDevice
			try {
				tmp = device.createRfcommSocketToServiceRecord(UUID
						.fromString(SPP_UUID));
			} catch (IOException e) {
				Log.e(TAG, "create() failed", e);
			}
			mmSocket = tmp;
		}

		public void run() {
			Log.i(TAG, "BEGIN mConnectThread");
			setName("ConnectThread");

			// Always cancel discovery because it will slow down a connection
			mBluetoothAdapter.cancelDiscovery();

			// Make a connection to the BluetoothSocket
			try {
				// This is a blocking call and will only return on a
				// successful connection or an exception
				mmSocket.connect();
			} catch (IOException e) {

				Log.e(TAG, "unable to connect() socket", e);
				// Close the socket
				try {
					mmSocket.close();
				} catch (IOException e2) {
					Log.e(TAG,
							"unable to close() socket during connection failure",
							e2);
				}
				return;
			}

			mConnectThread = null;

			// Start the connected thread
			// Start the thread to manage the connection and perform
			// transmissions
			mConnectedThread = new ConnectedThread(mmSocket);
			mConnectedThread.start();

		}

		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
				Log.e(TAG, "close() of connect socket failed", e);
			}
		}
	}

	/**
	 * This thread runs during a connection with a remote device. It handles all
	 * incoming and outgoing transmissions.
	 */
	private class ConnectedThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final InputStream mmInStream;
		private final OutputStream mmOutStream;

		public ConnectedThread(BluetoothSocket socket) {
			Log.d(TAG, "create ConnectedThread");
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;

			// Get the BluetoothSocket input and output streams
			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream();
			} catch (IOException e) {
				Log.e(TAG, "temp sockets not created", e);
			}

			mmInStream = tmpIn;
			mmOutStream = tmpOut;
		}

		public void run() {
			Log.i(TAG, "BEGIN mConnectedThread");
			byte[] buffer = new byte[256];
			int bytes;
			Boolean flag1 = false;
			// Keep listening to the InputStream while connected
			while (true) {
				try {
					// Read from the InputStream
					bytes = mmInStream.read(buffer);
					synchronized (mBuffer) {
						for (int i = 0; i < bytes; i++) {

							if (buffer[i] == (byte)Parameter.RECEIVE_START) {mBuffer.clear(); flag1 = true; continue;}

							if (flag1) {mBuffer.add(buffer[i] & 0xFF);}

							if (buffer[i] == (byte)Parameter.RECEIVE_END && flag1 && mBuffer.size() > 1) {
								flag1 = false;
								mHandler.sendEmptyMessage(MSG_NEW_DATA);
								mBuffer.remove(mBuffer.size() - 1);
							}
						}
					}
//					mHandler.sendEmptyMessage(MSG_NEW_DATA);
				} catch (IOException e) {
					Log.e(TAG, "disconnected", e);
					break;
				}
			}
		}

		/**
		 * Write to the connected OutStream.
		 * 
		 * @param buffer
		 *            The bytes to write
		 */
		public void write(byte[] buffer) {
			try {
				mmOutStream.write(buffer);
			} catch (IOException e) {
				Log.e(TAG, "Exception during write", e);
			}
		}

		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
				Log.e(TAG, "close() of connect socket failed", e);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		isBackCliecked = false;
		menu.add(0, 0, 0, "pause");
		menu.add(0, 1, 0, "scan");
		menu.add(0, 2, 0, "About");
		menu.add(0, 3, 0, "step");
		menu.add(0, 4, 0, "offset");
		menu.add(0, 5, 0, "About");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			if (Parameter.isPause) {
				Parameter.isPause = false;
				item.setTitle("Pause");
			} else {
				Parameter.isPause = true;
				item.setTitle("Resume");
			}
			Toast.makeText(MainActivity.this, "Menu 0", Toast.LENGTH_SHORT).show();

			break;
		case 1:

			if (mConnectThread != null) {
				mConnectThread.cancel();
				mConnectThread = null;
			}

			Intent serverIntent = new Intent(this, DeviceListActivity.class);
			startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
			Toast.makeText(MainActivity.this, "Menu 1", Toast.LENGTH_SHORT).show();

			break;
		case 2:

			startActivity(new Intent(this, AboutActivity.class));
			mHandler.sendEmptyMessage(MSG_NEW_DATA);
			break;

		}
		return true;
	}

	private boolean isBackCliecked = false;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isBackCliecked) {
				this.finish();
			} else {
				isBackCliecked = true;
				Toast t = Toast.makeText(this, "Press \'Back\' again to exit.",
						Toast.LENGTH_LONG);
				t.setGravity(Gravity.CENTER, 0, 0);
				t.show();
			}
		}
		return true;
	}

//	@Override
//	public void onClick(View v) {
//		Toast.makeText(MainActivity.this, "132456", Toast.LENGTH_SHORT).show();
//
//		switch (v.getId()) {
//			case R.id.btn_title_right:
//				PopWindow popWindow = new PopWindow(this,isPause);
//				popWindow.showPopupWindow(findViewById(R.id.btn_title_right));
//				break;
//		}
//
//
//
//		isBackCliecked = false;
//		if(v == mSendBtn){
//			String input = mEditText.getText().toString();//.trim();
//			char [] tmp1 = input.toCharArray();
//			byte [] tmp = new byte[tmp1.length];
//			for(int i = 0; i < tmp1.length; i++)
//			{
//				tmp[i] = (byte)tmp1[i];
//			}
//				Toast.makeText(MainActivity.this, input, Toast.LENGTH_SHORT).show();
//				mConnectedThread.write(tmp);
//		}
//	}
}
