package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapplication3.broadcast.Parameter;

import net.flyget.bluetoothhelper.R;

import java.util.ArrayList;
import java.util.List;

public class CmdActivity extends AppCompatActivity implements View.OnClickListener{

    private List<String> list =new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private ListView listView;
    private EditText editText;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmd);

        editText = (EditText) findViewById(R.id.cmd_edit);
        sendBtn = (Button) findViewById(R.id.send) ;
        sendBtn.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.commen_list_view);
        adapter = new ArrayAdapter<String>(CmdActivity.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                if (!editText.getText().toString().isEmpty()) {
                    String tmp ="send: " + editText.getText().toString();
                    list.add(0,tmp);
                    adapter.notifyDataSetChanged();
                    Parameter.sendString = Parameter.SEND + " " + editText.getText().toString();
                    Parameter.isSendPress = true;
                    sendBroadcast(new Intent("com.example.broadcast.POP_BROADCAST"));
                }
                break;
            default:
                break;
        }
    }
}
