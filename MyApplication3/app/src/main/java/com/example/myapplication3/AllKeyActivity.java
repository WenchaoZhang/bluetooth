package com.example.myapplication3;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication3.broadcast.Parameter;
import com.example.myapplication3.broadcast.SetBroadcastReceiver;
import com.example.myapplication3.pop.PopWindow;

import net.flyget.bluetoothhelper.R;

public class AllKeyActivity extends AppCompatActivity {

    Button mHomeBtn,keyUp,keyDown,keyLeft,keyRight,keyFront,keyBack,threeLBtn,threeRBtn
            ,fourRBtn,fourLBtn,fiveLBtn,fiveRBtn,holderBtn,cupBtn;
    TextView mTextView;

    private SetBroadcastReceiver setBroadcastReceiver = new SetBroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            mTextView.setText(Parameter.RECEIVE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_key);

        mTextView = (TextView) findViewById(R.id.mTextView);

        //小房子按键
        mHomeBtn = (Button) findViewById(R.id.btn_title_right);
        mHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopWindow popWindow = new PopWindow(AllKeyActivity.this, Parameter.isPause);
                popWindow.showPopupWindow(findViewById(R.id.btn_title_right));
            }
        });

        //3r按键
        threeRBtn = (Button) findViewById(R.id.three_r);
        threeRBtn.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
        threeRBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.THREER_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.THREER_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }
                return false;
            }
        });

        //3l按键
        threeLBtn = (Button) findViewById(R.id.three_l);
        threeLBtn.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
        threeLBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.THREEL_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.THREEL_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }
                return false;
            }
        });

        //4r按键
        fourRBtn = (Button) findViewById(R.id.four_r);
        fourRBtn.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
        fourRBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.FOURR_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.FOURR_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }
                return false;
            }
        });

        //4l按键
        fourLBtn = (Button) findViewById(R.id.four_l);
        fourLBtn.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
        fourLBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.FOURL_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.FOURL_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }
                return false;
            }
        });

        //5r按键
        fiveRBtn = (Button) findViewById(R.id.five_r);
        fiveRBtn.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
        fiveRBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.FIVER_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.FIVER_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }
                return false;
            }
        });

        //5l按键
        fiveLBtn = (Button) findViewById(R.id.five_l);
        fiveLBtn.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
        fiveLBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.FIVEL_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.FIVEL_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }
                return false;
            }
        });

        //前按键
        keyFront = (Button) findViewById(R.id.front);
        keyFront.setOnLongClickListener(new View.OnLongClickListener() {@Override public boolean onLongClick(View view) {return false;}});
        keyFront.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.FRONT_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.FRONT_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
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
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.BACK_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.BACK_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
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
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.DOWN_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    ((TextView)findViewById(R.id.tv_down)).setText("下按键： 发送状态...  步长; 03");
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.DOWN_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    ((TextView)findViewById(R.id.tv_down)).setText("下按键： 停止状态...  步长; 03");
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
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.UP_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    ((TextView)findViewById(R.id.tv_up)).setText("上按键： 发送状态...  步长; 03");
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.UP_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    ((TextView)findViewById(R.id.tv_up)).setText("上按键： 停止状态...  步长; 03");
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
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.LEFT_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    ((TextView)findViewById(R.id.tv_left)).setText("左按键： 发送状态...  步长; 03");
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.LEFT_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    ((TextView)findViewById(R.id.tv_left)).setText("左按键： 停止状态...  步长; 03");
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
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.RIGHT_KEY_DOWN;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    ((TextView)findViewById(R.id.tv_right)).setText("右按键： 发送状态...  步长; 03");
                }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.RIGUT_KEY_UP;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    ((TextView)findViewById(R.id.tv_right)).setText("右按键： 停止状态...  步长; 03");
                }
                return false;
            }
        });

        //真空吸杯按键
        cupBtn = (Button) findViewById(R.id.cup);
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

        //夹持器按键
        holderBtn = (Button) findViewById(R.id.holder);
        holderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Parameter.isHolderOn) {
                    Parameter.isHolderOn = false;
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.HOLDER_ON;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    holderBtn.setText("夹持器打开");
                }else {
                    Parameter.isHolderOn = true;
                    Parameter.isGetNewDate = true;
                    Parameter.newDate = Parameter.HOLDER_OFF;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                    holderBtn.setText("夹持器关闭");
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mTextView.setText(Parameter.RECEIVE);
        IntentFilter filter = new IntentFilter("com.example.broadcast.SET_BROADCAST");
        registerReceiver(setBroadcastReceiver, filter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (setBroadcastReceiver != null) {
            unregisterReceiver(setBroadcastReceiver);
        }
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
    protected void onPause() {
        super.onPause();
        finish();
    }

}
