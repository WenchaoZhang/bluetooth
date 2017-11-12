package com.example.myapplication3;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication3.broadcast.Parameter;

import net.flyget.bluetoothhelper.R;

public class StepActivity extends Activity {

    private RadioGroup stepRadioGroup;
    private RadioButton stepRadio1,stepRadio2,stepRadio3,stepRadio4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        stepRadioGroup = (RadioGroup)  findViewById(R.id.gendergroup);
        stepRadio1 = (RadioButton) findViewById(R.id.step1);
        stepRadio2 = (RadioButton) findViewById(R.id.step2);
        stepRadio3 = (RadioButton) findViewById(R.id.step3);
        stepRadio4 = (RadioButton) findViewById(R.id.step4);

        stepRadioGroup.setOnCheckedChangeListener(stepListenner);
    }

    private RadioGroup.OnCheckedChangeListener stepListenner =  new RadioGroup.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == stepRadio1.getId()) {
                Parameter.isStepChange = true;
                Parameter.STEP = Parameter.STEP1;
                sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
            }
            else if(i == stepRadio2.getId()) {
                Parameter.isStepChange = true;
                Parameter.STEP = Parameter.STEP2;
                sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
            }
            else if(i == stepRadio3.getId()) {
                Parameter.isStepChange = true;
                Parameter.STEP = Parameter.STEP3;
                sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
            }
            else if(i == stepRadio4.getId()) {
                Parameter.isStepChange = true;
                Parameter.STEP = Parameter.STEP4;
                sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
            }

        }
    };
}
