package com.example.hero.achievement;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hero.achievement.core.Core;
import com.example.hero.achievement.model.DatabaseAddProject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MenuActivity extends AppCompatActivity {

    private MaterialDialog.Builder mBuilder;   // dialog for adding  subject
    private MaterialDialog mDialog;
    private SQLiteDBHelper sqLiteDBHelper;
    private MaterialDialog.Builder sessionDialogBuilder;         // dialog for adding session
    private MaterialDialog addingSessionDialog;
                                                                 //dialog for showing charts

    Boolean hasUserClickedOnBack=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        LinkedList<String> subjectList=new LinkedList<>();        //liste darsa ke har bar ba click kardan rooye fab betoone bhesh ezafe kone

        sqLiteDBHelper = new SQLiteDBHelper(this);
        //sqLiteDBHelper.dropTable(sqLiteDBHelper.getDatabase());


        //adding project dialog
        // بیلدرشو تعریف کن با دیلوگ و فقط شو کن
        mBuilder= new MaterialDialog.Builder(this);
        mBuilder.customView(R.layout.addig_project_dialog_layout,false);
        mDialog=mBuilder.build();
//        mDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_dialog)); //vase inke kenaraye dialog gerd beshe



        FloatingActionButton fab = findViewById(R.id.fab);   //done

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.show();

            }
        });

        initRecyclerView(fab);

        initDialog(fab);

    }


    private void initDialog(final FloatingActionButton floatingActionButton){

        Button btnBackAddingDialog = (Button) mDialog.findViewById(R.id.btnBackAddingDialog);
        Button btnSaveBackAddingDialog = (Button) mDialog.findViewById(R.id.btnSaveBackAddingDialog);
        final EditText edtAddingDialogSubjName = (EditText) mDialog.findViewById(R.id.edtAddingDialogSubjName) ;
        final EditText edtAddingDialogSubjPriority = (EditText) mDialog.findViewById(R.id.edtAddingDialogSubjPriority) ;
        final EditText edtAddingDialogDayPerWeek = (EditText) mDialog.findViewById(R.id.edtAddingDialogDayPerWeek) ;
        final EditText edtAddingDialogHourPerDay = (EditText) mDialog.findViewById(R.id.edtAddingDialogHourPerDay) ;


        btnBackAddingDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        btnSaveBackAddingDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtAddingDialogSubjName.getText().toString().equals("")){
                    edtAddingDialogSubjName.setError("is Empty...");
                }
                else if (edtAddingDialogSubjPriority.getText().toString().equals("")){
                    edtAddingDialogSubjPriority.setError("is Empty...");
                }
                else if (edtAddingDialogDayPerWeek.getText().toString().equals("")){
                    edtAddingDialogDayPerWeek.setError("is Empty...");
                }
                else if (!edtAddingDialogDayPerWeek.getText().toString().matches("[(0-7)]")) {
                    edtAddingDialogDayPerWeek.setError("It must be between 0 to 7 days...");
                }
                else if (edtAddingDialogHourPerDay.getText().toString().equals("")){
                    edtAddingDialogHourPerDay.setError("is Empty...");
                }     else if (!edtAddingDialogHourPerDay.getText().toString().matches("[(0-24)]")) {
                    edtAddingDialogHourPerDay.setError("it must be between 0 to 24 hours...");
                }
                else {
                    List<DatabaseAddProject> list = new ArrayList<>();
                    list.add(new DatabaseAddProject(
                            edtAddingDialogSubjName.getText().toString() ,
                            Integer.valueOf(edtAddingDialogSubjPriority.getText().toString()) ,
                            Integer.valueOf(edtAddingDialogDayPerWeek.getText().toString()),
                            Integer.valueOf(edtAddingDialogHourPerDay.getText().toString())

                    ));
                    Log.d("star","data base add project worked ");


                    sqLiteDBHelper.insertSubjects(list);

                    mDialog.dismiss();
                    initRecyclerView(floatingActionButton);

                }
            }
        });

    }

    private void initRecyclerView(final FloatingActionButton fab){

        // وقتی دیتا رو درست وارد کردی یبار دیگه این متد رو صدا میکنی

        List<DatabaseAddProject> list = sqLiteDBHelper.getTable();


        RecyclerView recycler = findViewById(R.id.recycler);
        NamesAdapter adapter = new NamesAdapter(MenuActivity.this,list);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(MenuActivity.this,LinearLayout.VERTICAL,false));

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dx < dy){
                    animateFab(fab , false);
                }
                else {
                    animateFab(fab , true);
                }
            }
        });

        animateFab(fab,false);
    }

    private void animateFab(FloatingActionButton fab , boolean animate){

        // ok

        if (!animate){
            fab.animate().translationY(1000f).setDuration(500).start();
        }
        else if (animate){
            fab.animate().translationY(0f).setDuration(500).start();
        }
    }


    /*
         if the user clicked on back
    */
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
            //?????????????????????????????????????????????????????????????????? inja mikham kolan yeho az barname biroon bere

        }

    }
}
