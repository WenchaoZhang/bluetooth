package com.example.myapplication3.pop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication3.AboutActivity;
import com.example.myapplication3.DeviceListActivity;
import com.example.myapplication3.MainActivity;
import com.example.myapplication3.SlideActivity;
import com.example.myapplication3.broadcast.Parameter;

import net.flyget.bluetoothhelper.R;

/**
 * Created by admin on 2017/10/28.
 */

public class PopWindow extends PopupWindow {
    private View conentView;
    public PopWindow(final Activity context, Boolean f){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.pop_window, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w / 3 + 15);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);
        if (f) {
            TextView tvPause = (TextView) conentView.findViewById(R.id.pause);
            tvPause.setText("resume");
        }

        //暂停
        conentView.findViewById(R.id.pause).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                context.sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                Parameter.pop = Parameter.PAUSE;
                PopWindow.this.dismiss();
            }
        });


        //扫描
        conentView.findViewById(R.id.scan).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                context.sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                Parameter.pop = Parameter.SCAN;
//                context.startActivity(new Intent(conentView.getContext(), DeviceListActivity.class));
                PopWindow.this.dismiss();
            }
        });

        //滑块界面
        conentView.findViewById(R.id.slide).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                context.startActivity(new Intent(conentView.getContext(), SlideActivity.class));
                PopWindow.this.dismiss();
            }
        });

        //设置
        conentView.findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                context.sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                Parameter.pop = Parameter.SETTING;
                PopWindow.this.dismiss();
            }
        });

        //关于
        conentView.findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                context.sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                Parameter.pop = Parameter.ABOUT;
                PopWindow.this.dismiss();
            }
        });

        //退出
        conentView.findViewById(R.id.ability_logout).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // do something before signing out
                context.sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                Parameter.pop = Parameter.EXIT;
//                context.finish();
                PopWindow.this.dismiss();
            }
        });


    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 5);
        } else {
            this.dismiss();
        }
    }
}
