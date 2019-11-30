package com.example.firetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SpainAct extends AppCompatActivity implements View.OnClickListener{
    Button button, button2, button3;
    TextView textView, textView2, textView3, textView_time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spain);
        button = (Button)findViewById(R.id.b1);
        button2 = (Button)findViewById(R.id.b2);
        button3 = (Button)findViewById(R.id.b3);
        textView = (TextView)findViewById(R.id.text);
        textView2 = (TextView)findViewById(R.id.text2);
        textView3 = (TextView)findViewById(R.id.text3);
        textView_time = (TextView)findViewById(R.id.thaiTime);

        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        long now = System.currentTimeMillis();

        Date date = new Date(now);

        TimeZone tz = TimeZone.getTimeZone("GMT +07:00");
        final SimpleDateFormat sdfNow = new SimpleDateFormat("MM월 dd일 HH시 mm분", Locale.KOREAN);
        Calendar cal = Calendar.getInstance(Locale.KOREA);
        String nal = sdfNow.format(cal.getTime());
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1000);

                    }catch (Exception e){

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sdfNow.setTimeZone(TimeZone.getTimeZone("GMT+07:00"));
                            textView_time.setText("시간 : "+sdfNow.format(new Date()));
                        }
                    });
                }
            }
        };
        new Thread(r).start();



        /*
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1000);

                    }catch (Exception e){

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView_time.setText("현재시간 : "+sdfNow.format(new Date()));
                        }
                    });
                }
            }
        };
        new Thread(r).start();

         */


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b1:
                Intent it = new Intent(this, Sevilla.class);
                startActivity(it);
                finish();
                break;
            case R.id.text:
                Intent it2 = new Intent(this, Sevilla.class);
                startActivity(it2);
                finish();
                break;
            case R.id.b2:
                Intent it3 = new Intent(this, Barcelona.class);
                startActivity(it3);
                finish();
                break;
            case R.id.text2:
                Intent it4 = new Intent(this, Barcelona.class);
                startActivity(it4);
                finish();
                break;
            case R.id.b3:
                Intent it5 = new Intent(this, Madrid.class);
                startActivity(it5);
                finish();
                break;
            case R.id.text3:
                Intent it6 = new Intent(this, Madrid.class);
                startActivity(it6);
                finish();
                break;
        }

    }
}