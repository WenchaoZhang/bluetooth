package com.example.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication3.broadcast.Parameter;
import com.example.myapplication3.pop.PopWindow;

import net.flyget.bluetoothhelper.R;

public class AllKeyActivity extends AppCompatActivity {

    Button mHomeBtn,keyUp,keyDown,keyLeft,keyRight,keyFront,keyBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_key);

        //小房子按键
        mHomeBtn = (Button) findViewById(R.id.btn_title_right);
        mHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopWindow popWindow = new PopWindow(AllKeyActivity.this, Parameter.isPause);
                popWindow.showPopupWindow(findViewById(R.id.btn_title_right));
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

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
