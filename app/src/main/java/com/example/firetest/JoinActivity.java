package com.example.firetest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {
    EditText id1, pw1, name1;
    Button btn1, btn2;
    String sid, spw, sname;
    MemberDB test = new MemberDB(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);

        btn1 = findViewById(R.id.btn11);
        btn2 = findViewById(R.id.btn22);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        id1 = (EditText) findViewById(R.id.et1);
        pw1 = (EditText) findViewById(R.id.et2);
        name1 = (EditText) findViewById(R.id.et3);
    }

        public void onClick(View v){
            sid = id1.getText().toString();
            spw = pw1.getText().toString();
            sname = name1.getText().toString();
            if(v.getId() == R.id.btn22){
                try {
                    db = test.getWritableDatabase();
                    String str1;
                    str1 = "insert into member values('" + sid + "', '" + spw + "', '" + sname + "')";
                    db.execSQL(str1);
                }catch (SQLiteException sb){}
            }
            test.close();
            finish();
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }
    }

