package com.example.xjc.achievementshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by XJC on 2015/9/1.
 */
public class AchievementDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME1 = "Wch_db";
    public static final String TABLE_NAME2 = "Des_db";
    public static final String Ach_thing = "Ach_thing ";
    public static final String Ach_point = "Ach_point";
    public static final String Des_thing = "Des_thing";
    public static final String Des_point = "Des_point";
    public static final String ID = "_id";
    public AchievementDB(Context context) {
        super(context, "Ach_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME1+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Ach_thing +" TEXT,"
                +Ach_point +" TEXT);");
        db.execSQL("CREATE TABLE "+TABLE_NAME2+"("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +Des_thing +" TEXT,"
                +Des_point +" TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
