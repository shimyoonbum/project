package com.example.firetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThaiAct extends AppCompatActivity implements View.OnClickListener{
    Button button, button2, button3;
    TextView textView, textView2, textView3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thai);

        button = (Button)findViewById(R.id.b1);
        button2 = (Button)findViewById(R.id.b2);
        button3 = (Button)findViewById(R.id.b3);
        textView = (TextView)findViewById(R.id.text);
        textView2 = (TextView)findViewById(R.id.text2);
        textView3 = (TextView)findViewById(R.id.text3);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b1:
                Intent it = new Intent(this, Bangkok.class);
                startActivity(it);
                break;
            case R.id.text:
                Intent it2 = new Intent(this, Bangkok.class);
                startActivity(it2);
                break;
            case R.id.b2:
                Intent i = new Intent(this, Review.class);
                startActivity(i);
                break;
            case R.id.b3:
                Intent i2 = new Intent(this, Chiangmai.class);
                startActivity(i2);
                break;
            case R.id.text3:
                Intent i3 = new Intent(this, Chiangmai.class);
                startActivity(i3);
                break;
        }
    }
}
