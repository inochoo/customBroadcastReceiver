package com.sibaamap.custombroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MY_ACTION  = "com.tincoder.ACTION";
    private static final String MY_TEXT = "com.tincoder.TEXT";
    private Button btnSendBroadcast;
    private TextView tvReceived;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(MY_ACTION.equals(intent.getAction())){
                String text = intent.getStringExtra(MY_TEXT);
                tvReceived.setText(text);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendBroadcast = findViewById(R.id.btn_send_broadcast);
        tvReceived = findViewById(R.id.tv_received);

        btnSendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSendBroadCast();
            }
        });

    }

    private void clickSendBroadCast() {
        Intent intent = new Intent(MY_ACTION);
        intent.putExtra(MY_TEXT,"This is fkb channel");
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(MY_ACTION);
        registerReceiver(mBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mBroadcastReceiver);
    }
}