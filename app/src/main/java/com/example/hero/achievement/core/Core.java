package com.example.hero.achievement.core;

import android.app.Application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Core extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static String getTime(){

        SimpleDateFormat yy = new SimpleDateFormat("YYYY");
        String y = yy.format(new Date());


        SimpleDateFormat mmmm = new SimpleDateFormat("MM");
        String mm = mmmm.format(new Date());



        SimpleDateFormat dd = new SimpleDateFormat("DD");
        String d = dd.format(new Date());



        SimpleDateFormat hh = new SimpleDateFormat("HH");
        String h = hh.format(new Date());


        SimpleDateFormat mmm = new SimpleDateFormat("MM");
        String m = mmm.format(new Date());


        SimpleDateFormat ss = new SimpleDateFormat("SS");
        String s = ss.format(new Date());

        //return y + "." + mm + "." + d + "_" + h + "." + m + "." + s;
        return h +":" + m + ":" + s;

    }

}
