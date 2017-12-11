package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication3.broadcast.Parameter;

import net.flyget.bluetoothhelper.R;

public class SlideActivity extends AppCompatActivity {

    protected static final String TAG = "SlideActivity";

    private SeekBar seekLR,seekUpDown,seekFB,seekR3,seekR4,seekR5,seekHolder;
    private TextView tvLR,tvUpDown,tvFB,tvR3,tvR4,tvR5,tvHolder;
    private Button cupBtn;
    private boolean flagTimer = false;
    Thread mThread;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        seekLR = (SeekBar) findViewById(R.id.seek_left_right);
        seekUpDown = (SeekBar) findViewById(R.id.seek_up_down);
        seekFB = (SeekBar) findViewById(R.id.seek_front_back);
        seekR3 = (SeekBar) findViewById(R.id.rotation_3);
        seekR4 = (SeekBar) findViewById(R.id.rotation_4);
        seekR5 = (SeekBar) findViewById(R.id.rotation_5);
        seekHolder = (SeekBar) findViewById(R.id.holder1);

        seekLR.setProgress(1024);
        seekLR.setOnSeekBarChangeListener(seekListenerLR);
        seekUpDown.setProgress(1024);
        seekUpDown.setOnSeekBarChangeListener(seekListenerUP);
        seekFB.setProgress(1024);
        seekFB.setOnSeekBarChangeListener(seekListenerFB);
        seekR3.setProgress(1024);
        seekR3.setOnSeekBarChangeListener(seekListenerR3);
        seekR4.setProgress(1024);
        seekR4.setOnSeekBarChangeListener(seekListenerR4);
        seekR5.setProgress(1024);
        seekR5.setOnSeekBarChangeListener(seekListenerR5);
        seekHolder.setProgress(1024);
        seekHolder.setOnSeekBarChangeListener(seekListenerH);

        tvLR = (TextView) findViewById(R.id.tv_left_right);
        tvUpDown = (TextView) findViewById(R.id.tv_up_down);
        tvFB = (TextView) findViewById(R.id.tv_front_back);
        tvR3 = (TextView) findViewById(R.id.tv_rotation_3);
        tvR4 = (TextView) findViewById(R.id.tv_rotation_4);
        tvR5 = (TextView) findViewById(R.id.tv_rotation_5);
        tvHolder = (TextView) findViewById(R.id.tv_holder);

        cupBtn = (Button) findViewById(R.id.cup1);
        cupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Parameter.isCupOn) {
                    Parameter.isCupOn = false;
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.CUP_ON;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    cupBtn.setText("真空吸杯开");
                }else {
                    Parameter.isCupOn = true;
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.CUP_OFF;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    cupBtn.setText("真空吸杯关");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Parameter.isCupOn) {
            cupBtn.setText("真空吸杯关");
        }else {
            cupBtn.setText("真空吸杯开");
        }
    }
    private SeekBar.OnSeekBarChangeListener seekListenerLR = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            flagTimer = false;
            mThread.interrupt();
            mThread = null;
            sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            flagTimer = true;
            mThread = new Thread(new ThreadTimer());
            mThread.start();
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            Parameter.isSliderChange = true;
            Parameter.whichSlider = Parameter.SLIDER0;
            Parameter.sliderValue = progress;
//            sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
            tvLR.setText("steer0值: " + (progress+1024));
        }
    };

    private SeekBar.OnSeekBarChangeListener seekListenerUP = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            flagTimer = false;
            mThread.interrupt();
            mThread = null;
            sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            flagTimer = true;
            mThread = new Thread(new ThreadTimer());
            mThread.start();
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            Parameter.isSliderChange = true;
            Parameter.sliderValue = progress;
            Parameter.whichSlider = Parameter.SLIDER1;
            tvUpDown.setText("steer1值: " + (progress+1024));
        }
    };

    private SeekBar.OnSeekBarChangeListener seekListenerFB = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            flagTimer = false;
            mThread.interrupt();
            mThread = null;
            sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            flagTimer = true;
            mThread = new Thread(new ThreadTimer());
            mThread.start();
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            Parameter.isSliderChange = true;
            Parameter.sliderValue = progress;
            Parameter.whichSlider = Parameter.SLIDER2;
            tvFB.setText("steer2值: " + (progress+1024));
        }
    };

    private SeekBar.OnSeekBarChangeListener seekListenerR3 = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            flagTimer = false;
            mThread.interrupt();
            mThread = null;
            sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            flagTimer = true;
            mThread = new Thread(new ThreadTimer());
            mThread.start();
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            Parameter.isSliderChange = true;
            Parameter.sliderValue = progress;
            Parameter.whichSlider = Parameter.SLIDER3;
            tvR3.setText("steer3值: " + (progress+1024));
        }
    };

    private SeekBar.OnSeekBarChangeListener seekListenerR4 = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            flagTimer = false;
            mThread.interrupt();
            mThread = null;
            sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            flagTimer = true;
            mThread = new Thread(new ThreadTimer());
            mThread.start();
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            Parameter.isSliderChange = true;
            Parameter.sliderValue = progress;
            Parameter.whichSlider = Parameter.SLIDER4;
            tvR4.setText("steer4值: " + (progress+1024));
        }
    };

    private SeekBar.OnSeekBarChangeListener seekListenerR5 = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            flagTimer = false;
            mThread.interrupt();
            mThread = null;
            sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            flagTimer = true;
            mThread = new Thread(new ThreadTimer());
            mThread.start();
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            Parameter.isSliderChange = true;
            Parameter.sliderValue = progress;
            Parameter.whichSlider = Parameter.SLIDER5;
            tvR5.setText("steer5值: " + (progress+1024));
        }
    };

    private SeekBar.OnSeekBarChangeListener seekListenerH = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            flagTimer = false;
            mThread.interrupt();
            mThread = null;
            sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            flagTimer = true;
            mThread = new Thread(new ThreadTimer());
            mThread.start();
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            Parameter.isSliderChange = true;
            Parameter.sliderValue = progress;
            Parameter.whichSlider = Parameter.SLIDER6;
            tvHolder.setText("steer6值: " + (progress+1024));
        }
    };

    // handler类接收数据
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
            }
        }
    };

    //定时器子线程类
    class ThreadTimer implements Runnable{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (flagTimer) {
                try {
                    Thread.sleep(50);
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                    System.out.println("send...");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("thread error...");
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Parameter.isReset = true;
        Parameter.RESET = Parameter.START_RESET;
        sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Parameter.isReset = true;
        Parameter.RESET = Parameter.END_RESET;
        sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
    }

}
