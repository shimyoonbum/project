package com.example.firetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class Intro extends Activity {
    final Handler hdr = new Handler() {
        public void handleMessage(Message msg){
            Intent i = new Intent(Intro.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            overridePendingTransition(0, 0);
            finish();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Message msg = hdr.obtainMessage();
                hdr.sendMessage(msg);
            }
        }, 3000);
    }
}
