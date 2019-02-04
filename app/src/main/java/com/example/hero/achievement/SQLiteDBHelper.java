package com.example.hero.achievement;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import  android.support.annotation.Nullable;

import com.example.hero.achievement.model.DatabaseModel1;
import com.example.hero.achievement.modeltwo.DatabaseModelChart;
import com.example.hero.achievement.modeltwo.DatabaseModelTwo;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDBHelper  extends SQLiteOpenHelper {

    private static final String local = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String dbPath = local + "/Android/";

   // private static final String DATABASE_NAME = dbPath + "Amozesh.db";
    private static final String DATABASE_NAME_2 = "Amozesh.db";

    private static final int VERSION_NAME = 1;
    private SQLiteDatabase database;

    String TABLE_NAME_subject = "Projects";
    String TABLE_NAME_session = "Session";

    String create_table_subjects = " CREATE TABLE " + TABLE_NAME_subject + " (" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "subjectName TEXT ," +
            "priority INTEGER ," +
            "daysPerWeek INTEGER ," +
            "hoursPerSession INTEGER " +
            ")";

    String create_table_add_session = " CREATE TABLE " + TABLE_NAME_session + "(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "subjectName TEXT ," +
            "satisfaction INTEGER ," +
            "hour INTEGER ," +
            "quality INTEGER ," +
            "date TEXT " +

            ")";

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME_2, null, VERSION_NAME);
        this.database = getWritableDatabase();
    }

    /*
    delete a table if it exists
     */
    public void dropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_subject);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_session);

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
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_subjects);
        db.execSQL(create_table_add_session);
    }

    /*
     age bekhay taghir bedi inja taghirato anjam midi
     va versiono mibari balatar
     miad table ghabliaro delete mikone jadid misaze
     age mikhay save beshe bayad migration bnevisi
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
 /*
 barrye gerreftane etelaat e har dars
  */
    public void insertSubjects(List<DatabaseModel1> list) {

        String insertSubjectToDB = "" +
                "INSERT INTO " + TABLE_NAME_subject +
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

    public void insertAddSEssion(List<DatabaseModelTwo> list) {

        String insertSessionToDB = "INSERT INTO " + TABLE_NAME_session +
                "(subjectName,satisfaction,hour,quality,date)" +
                " VALUES ('" +
                list.get(0).getSubjectName() + "'," +
                list.get(0).getSatisfaction() + "," +
                list.get(0).getHour() + "," +
                list.get(0).getQuality() +",'" +
                list.get(0).getdate() +
                "')";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertSessionToDB);
        db.close();

    }
    /*
    baraye khoondan az database
     */
    public List<DatabaseModel1> getTable(){

        List<DatabaseModel1> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor all  = db.rawQuery("SELECT * FROM " +  TABLE_NAME_subject , null);

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

/*
baraye gereftane etelaate kole jadval
 */
    public  List<DatabaseModelTwo> getSessionTable(){


        List<DatabaseModelTwo> sessionList=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor all  = db.rawQuery("SELECT * FROM " +  TABLE_NAME_session , null);


        if(all.moveToFirst()){
            do{
                int id      = all.getInt(0);
                String s1   = all.getString(1);
                int i1      = all.getInt(3);
                int i2      = all.getInt(4);
                int i3      = all.getInt(5);
                String d1   = all.getString(2);


                sessionList.add(new DatabaseModelTwo(s1,i1,i2,i3,d1));
            }
            while(all.moveToNext());
        }
        db.close();

            return sessionList;
    }

    public  List<DatabaseModelChart> getSessionDateAndHour(String subject_name){

        String query = "SELECT * FROM  " +  TABLE_NAME_session + " WHERE subjectName = '" + subject_name + "'";

        List<DatabaseModelChart> sessionList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor all  = db.rawQuery(query,null);

        if(all.moveToFirst()){
            do{

                String date   = all.getString(0);
                int hourPerSession     = all.getInt(1);

                sessionList.add(new DatabaseModelChart(date,hourPerSession));
            }
            while(all.moveToNext());
        }


        db.close();

        return sessionList;
    }


}
