package com.example.hero.achievement;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  android.support.annotation.Nullable;

import com.example.hero.achievement.model.DatabaseModel1;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDBHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Amozesh.db";
    private static final int VERSION_NAME = 1;
    private SQLiteDatabase database;

    String TABLE_NAME = "Projects";

    String create_table_subjects = " CREATE TABLE " + TABLE_NAME + " (" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "subjectName TEXT ," +
            "priority INTEGER ," +
            "daysPerWeek INTEGER ," +
            "hoursPerSession INTEGER " +
            ")";

    String create_table_adding_session = " CREATE TABLE " + TABLE_NAME + "(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "subjectName TEXT ," +
            "priority INTEGER ," +
            "daysPerWeek INTEGER ," +
            "hoursPerSession INTEGER " +
            ")";

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NAME);
        this.database = this.getWritableDatabase();
    }

    public void dropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }

    /*
    data base or misaze
    faghat yebar ejra mishe
     */

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(create_table_subjects); }

    /*
     age bekhay taghir bedi inja taghirato anjam midi
     va versiono mibari balatar
     miad table ghabliaro delete mikone jadid misaze
     age mikhay save beshe bayad migration bnevisi
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertSubjects(List<DatabaseModel1> list) {

        String insertSubjectToDB = "" +
                "INSERT INTO " + TABLE_NAME +
                "(subjectName,priority,daysPerWeek,hoursPerSession)" +
                " VALUES ('" +
                list.get(0).getSubjectName() + "'," +
                list.get(0).getPriority() + "," +
                list.get(0).getDaysPerWeek() + "," +
                list.get(0).getHoursPerSession()  +
                ")";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertSubjectToDB);
        db.close();

    }

    public List<DatabaseModel1> getTable(){

        List<DatabaseModel1> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor all  = db.rawQuery("SELECT * FROM " +  TABLE_NAME , null);

        if(all.moveToFirst()){
            do{
                int id      = all.getInt(0);
                String s1   = all.getString(1);
                int i1      = all.getInt(2);
                int i2      = all.getInt(3);
                int i3      = all.getInt(4);

                list.add(new DatabaseModel1(s1 , i1 , i2 , i3));
            }
            while(all.moveToNext());
        }
        db.close();
        return list;
    }


}
