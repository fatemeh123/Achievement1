package com.example.hero.achievement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.example.hero.achievement.model.DatabaseAddProject;
import com.example.hero.achievement.modeltwo.DatabaseModelChart;
import com.example.hero.achievement.modeltwo.DatabaseAddSession;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDBHelper  extends SQLiteOpenHelper {

    private static final String local = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String dbPath = local + "/Android/";

    private static final String DATABASE_NAME = dbPath + "Fatemeh.db";
    private static final String DATABASE_NAME_2 = "Amozesh.db";

    private static final int VERSION_NAME = 1;
    private SQLiteDatabase database;

    String TABLE_NAME_SUBJECT = "Projects";
    String TABLE_NAME_SESSION = "Session";

    String create_table_subjects = " CREATE TABLE " + TABLE_NAME_SUBJECT + " (" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT ," +
            "subjectName TEXT ," +
            "priority INTEGER ," +
            "daysPerWeek INTEGER ," +
            "hoursPerSession INTEGER " +
            ")";

    // TODO: When refactoring, change naming of hour to duration
    String create_table_add_session = " CREATE TABLE " + TABLE_NAME_SESSION + "(" +
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SESSION);

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
    public void insertSubjects(List<DatabaseAddProject> list) {

        String insertSubjectToDB = "" +
                "INSERT INTO " + TABLE_NAME_SUBJECT +
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

    public void insertAddSEssion(List<DatabaseAddSession> list) {

        String insertSessionToDB = "INSERT INTO " + TABLE_NAME_SESSION +
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
    public List<DatabaseAddProject> getTable(){

        List<DatabaseAddProject> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor all  = db.rawQuery("SELECT * FROM " + TABLE_NAME_SUBJECT, null);

        if(all.moveToFirst()){
            do{
                int id      = all.getInt(0);
                String s1   = all.getString(1);
                int i1      = all.getInt(2);
                int i2      = all.getInt(3);
                int i3      = all.getInt(4);

                list.add(new DatabaseAddProject(s1 , i1 , i2 , i3));
            }
            while(all.moveToNext());
        }
        db.close();
        return list;
    }

/*
baraye gereftane etelaate kole jadval
 */
    public  List<DatabaseAddSession> getSessionTable(){


        List<DatabaseAddSession> sessionList=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor all  = db.rawQuery("SELECT * FROM " + TABLE_NAME_SESSION, null);


        if(all.moveToFirst()){
            do{
                int id      = all.getInt(0);
                String s1   = all.getString(1);
                int i1      = all.getInt(3);
                int i2      = all.getInt(4);
                int i3      = all.getInt(5);
                String d1   = all.getString(2);


                sessionList.add(new DatabaseAddSession(s1,i1,i2,i3,d1));
            }
            while(all.moveToNext());
        }
        db.close();

            return sessionList;
    }

    public  List<DatabaseModelChart> getSessionDateAndHour(String inputSubjectName){

        String query = "SELECT * FROM  " + TABLE_NAME_SESSION + " WHERE subjectName = '" + inputSubjectName + "'";

        List<DatabaseModelChart> sessionList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor all  = db.rawQuery(query,null);

        if(all.moveToFirst()){
            do{

                String date      = all.getString(5);
                int duration     = all.getInt(3);

                sessionList.add(new DatabaseModelChart(date,duration));
            }
            while(all.moveToNext());
        }
        db.close();

        return sessionList;
    }


}
