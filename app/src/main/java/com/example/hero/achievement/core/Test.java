package com.example.hero.achievement.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class Test extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inittems();

        initViews();

        initDialog();

        initRecyclerView();

        //initMenus();

        int i = get(1);
    }


    private void initViews(){

    }

    private void inittems(){

    }

    private void initMenus(){

    }

    private void initDialog(){

    }

    private void initList(int i){

    }

    private int get(int i){
        return i + i * i;
    }

    private void initRecyclerView(){

    }


}
