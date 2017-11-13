package com.example.myapplication3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
                Toast.makeText(CmdActivity.this,"ddd",Toast.LENGTH_SHORT).show();
                if (!editText.getText().toString().isEmpty()) {
                    String tmp ="send: " + editText.getText().toString();
                    list.add(tmp);
                    adapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }
}
