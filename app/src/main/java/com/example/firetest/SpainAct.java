package com.example.firetest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SpainAct extends AppCompatActivity implements View.OnClickListener{
    Button button, button2, button3;
    TextView textView, textView2, textView3, textView_time, textafter;
    EditText editText;

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
        editText = (EditText)findViewById(R.id.sedit);
        textafter = (TextView)findViewById(R.id.safter);

        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Double after = Double.parseDouble(s.toString()) * 0.00076;
                textafter.setText(after.toString());
            }

            @Override
            public void afterTextChanged(Editable s){
            }
        });


        final VideoView vv = (VideoView) findViewById(R.id.vv);

        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.spa;

        Uri uri = Uri.parse(uriPath);

        vv.setVideoURI(uri);

        vv.requestFocus();

        vv.start();
        vv.setOnInfoListener(new MediaPlayer.OnInfoListener() {

            @Override

            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                        Toast.makeText(getApplicationContext(), "MEDIA_ERROR_TIMED_OUT", Toast.LENGTH_LONG).show();
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        // Progress Diaglog 출력
                        Toast.makeText(getApplicationContext(), "MEDIA_INFO_BUFFERING_START", Toast.LENGTH_LONG).show();
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        // Progress Dialog 삭제
                        Toast.makeText(getApplicationContext(), "MEDIA_INFO_BUFFERING_END", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }

        });

        long now = System.currentTimeMillis();
        Date date = new Date(now);

        TimeZone tz = TimeZone.getTimeZone("GMT +07:00");
        final SimpleDateFormat sdfNow = new SimpleDateFormat("MM월 dd일 HH시 mm분", Locale.KOREAN);
        Calendar cal = Calendar.getInstance(Locale.KOREAN);
        String nal = sdfNow.format(cal.getTime());
        Runnable r = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sdfNow.setTimeZone(TimeZone.getTimeZone("GMT+01:00"));
                            textView_time.setText("시간 : "+sdfNow.format(new Date()));
                        }
                    });
                }
            }
        };
        new Thread(r).start();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b1:
                Intent it = new Intent(this, Sevilla.class);
                startActivity(it);
                break;
            case R.id.text:
                Intent it2 = new Intent(this, Sevilla.class);
                startActivity(it2);
                break;
            case R.id.b2:
                Intent it3 = new Intent(this, Barcelona.class);
                startActivity(it3);
                break;
            case R.id.text2:
                Intent it4 = new Intent(this, Barcelona.class);
                startActivity(it4);
                break;
            case R.id.b3:
                Intent it5 = new Intent(this, Madrid.class);
                startActivity(it5);
                break;
            case R.id.text3:
                Intent it6 = new Intent(this, Madrid.class);
                startActivity(it6);
                break;
        }
    }
}