package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by anirudh on 26-07-2019.
 */

public class sql_helper extends SQLiteOpenHelper {

    public  static final String tb_name="data";
    public sql_helper(Context context) {
        super(context, "Todo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tb_name + "(items text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + tb_name);
        onCreate(db);
    }



    public Cursor get_data()
    {

        SQLiteDatabase sd = this.getReadableDatabase();

        return sd.rawQuery("select * from " + tb_name,null);
    }


    public void put_data(ArrayList<String> values) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("drop table if exists " + tb_name);
        db.execSQL("create table " + tb_name + "(items text)");

        ContentValues lk = new ContentValues();
        for (int i = 0; i < values.size(); i++) {
            lk.put("items", values.get(i));
            long flag = db.insert(tb_name, null, lk);

        }

    }




    public boolean add(String msg){

        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues  lk = new ContentValues();
        lk.put("items",msg);
        long flag = sd.insert(tb_name,null,lk);
        if(flag==-1)
            return false;

        return true;

    }

    public boolean update(String old, String New) {

        SQLiteDatabase sd = this.getWritableDatabase();
        String selectionArgs[] = new String[1];
        selectionArgs[0]=old;
        ContentValues k = new ContentValues(1);
        k.put("Items",New);
        int i =  sd.update(tb_name,k,"Items LIKE?",selectionArgs);
        if(i>=1)
           return true;

        return false;

    }

//todo : 4.add deleted tasks to a separate table.
}












