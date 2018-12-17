package com.example.hero.achievement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);





        RecyclerView recycler=findViewById(R.id.recycler);

        NamesAdapter adapter= new NamesAdapter();
        recycler.setAdapter(adapter);




        recycler.setLayoutManager(new LinearLayoutManager(MenuActivity.this,LinearLayout.VERTICAL,false));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//  vaghti dokmeye back ro mizane che etefaghi biofte
        
    }
}
