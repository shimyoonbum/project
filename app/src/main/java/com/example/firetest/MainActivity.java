package com.example.firetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText id1, pw1;
    Button btn1, btn2;
    String sid, spw;
    MemberDB test = new MemberDB(this);
    SQLiteDatabase db;
    Cursor cursor = null;
    CheckBox autologin;

    SharedPreferences setting;
    SharedPreferences.Editor editor;

    private long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        id1 = (EditText) findViewById(R.id.id);
        pw1 = (EditText) findViewById(R.id.pw);
        autologin = (CheckBox)findViewById(R.id.auto);
        setting = getSharedPreferences("setting", 0);
        editor = setting.edit();

        //자동 로그인 설정 했을 때 앱이 다시 시작하는 경우에도 입력값이 유지되도록 설정
        if(setting.getBoolean("autoLogin_enable", false)){
            id1.setText(setting.getString("ID", ""));
            pw1.setText(setting.getString("PW", ""));
            autologin.setChecked(true);
        }

        //자동로그인 체크박스 체크 리스너 설정
        autologin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    String id = id1.getText().toString();
                    String pw = pw1.getText().toString();

                    editor.putString("ID", id);
                    editor.putString("PW", pw);
                    editor.putBoolean("autoLogin_enable", true);
                    editor.commit();    //최종 작성값을 저장하기 위해 반드시 사용
                }else{
                    editor.clear();
                    editor.commit();
                }
            }
        });
        id1.requestFocus();
    }
    @Override
    public void onClick(View view) {
        db = test.getReadableDatabase();
        switch (view.getId()) {
            case R.id.btn1:
                if (btn1.getText().equals("로그인")) {
                    try {
                        sid = id1.getText().toString();
                        spw = pw1.getText().toString();
                        cursor = db.rawQuery("select id, pw from member where id = '" + sid + "' and pw = '" + spw + "';", null);

                        if (cursor.getCount() > 0) {
                            Intent i = new Intent(this, TravelMain.class);
                            startActivity(i);
                            finish();
                            Date d = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            Toast.makeText(this, "로그인 완료! 일시 : " + sdf.format(d.getTime()), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, "아이디 또는 비번 불일치!", Toast.LENGTH_LONG).show();
                        }
                        cursor.close();
                    } catch (SQLiteException sq) {
                        sq.printStackTrace();
                    }
                }
                break;
            case R.id.btn2:
                Intent it = new Intent(this, JoinActivity.class);
                startActivity(it);
                finish();
                break;
        }
        test.close();
    }

    //뒤로가기 버튼 입력시 앱 종료
    @Override
    public void onBackPressed()
    {
        if(System.currentTimeMillis() - lastTime < 1500){
            finish();
            return;
        }

        Toast.makeText(this, "'뒤로' 버튼을 한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();

        lastTime = System.currentTimeMillis();
    }
}
