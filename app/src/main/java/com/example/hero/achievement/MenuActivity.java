package com.example.hero.achievement;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    Boolean hasUserClickedOnBack=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //liste darsa ke har bar ba click kardan rooye fab betoone bhesh ezafe kone
        LinkedList<String> subjectList=new LinkedList<>();



        RecyclerView recycler=findViewById(R.id.recycler);
        NamesAdapter adapter= new NamesAdapter(subjectList);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(MenuActivity.this,LinearLayout.VERTICAL,false));



        //adding fab to the project

        FloatingActionButton fab = findViewById(R.id.fab);
        /*
        inja ba harbar click kardan bayad ye dialog activity baz she o etelaate darso begire va too data base zakhire kone
         */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }

    @Override
    public void onBackPressed() {


        //avalin bar ke miaym too in method yani kaarbar  ybar dokmeye backo click karde

        if (hasUserClickedOnBack==false){
            Toast.makeText(MenuActivity.this,"If you want to leave the application please press on bach again",Toast.LENGTH_LONG ).show();
            hasUserClickedOnBack=true;


            //inja migim ke agar 3 ssanie gozasht va kaarbar dobare rooye back click nakard  hasUserClickedOnBack ro dobare false konke dobare azash soal beprse
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hasUserClickedOnBack=false;

                }
            },3000);
        }else {
            super.onBackPressed();

        }

    }
}
