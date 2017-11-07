package com.example.myapplication3.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.myapplication3.MainActivity;

/**
 * Created by admin on 2017/10/28.
 */

public class PopBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "PopBroadcastReceiver", Toast.LENGTH_SHORT).show();
    }
}
