package com.example.myapplication3.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by admin on 2017/12/5.
 */

public class SetBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "SetBroadcastReceiver", Toast.LENGTH_SHORT).show();
    }
}
