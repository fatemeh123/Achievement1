package com.example.hero.achievement;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  android.support.annotation.Nullable;

public class SQLiteDBHelper  extends SQLiteOpenHelper {

    String TABLE_NAME = "subjects";
    String create_table_subjects = "" + " CREATE TABLE " + TABLE_NAME + "(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "subjectName TEXT ," +
            "priority INTEGER ," +
            "daysPerWeek INTEGER ," +
            "hoursPerDay INTEGER " +
            ")";


    public SQLiteDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);

    }


    /*
    data base or misaze
    faghat yebar ejra mishe
     */

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(create_table_subjects); }

    /*
     age bekhay taghir bedi ija taghirato anjam midi
     va versiono mibari balatar
     miad table ghabliaro delete mikone jadid misaze
     age mikhay save beshe bayad migration bnevisi
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertSubjects(String subjectName, int priority,int daysPerWeek, int hoursPerDay) {

        String insertSubjectToDB = "" +
                "INSERT INTO " + TABLE_NAME +
                "(name,family)" +
                "VALUES ('" +subjectName + "'," + priority + "," + daysPerWeek+ "," + hoursPerDay + ")" + "";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertSubjectToDB);
        db.close();




    }
}
