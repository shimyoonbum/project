package com.example.firetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MemberDB extends SQLiteOpenHelper {
    public MemberDB(Context context){
        super(context, "db1", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String s = "create table member(id varchar, pw varchar, name varchar);";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists member");
        onCreate(sqLiteDatabase);
    }
}
