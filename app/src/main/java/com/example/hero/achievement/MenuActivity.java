package com.example.hero.achievement;

import android.os.Handler;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.hero.achievement.core.Core;
import com.example.hero.achievement.model.DatabaseModel1;
import com.example.hero.achievement.modeltwo.DatabaseModelTwo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MenuActivity extends AppCompatActivity {

    private MaterialDialog.Builder mBuilder;
    private MaterialDialog mDialog;
    private SQLiteDBHelper sqLiteDBHelper;

    private MaterialDialog.Builder sessionDialogBuilder;
    private MaterialDialog addingSessionDialog;


    Boolean hasUserClickedOnBack=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //liste darsa ke har bar ba click kardan rooye fab betoone bhesh ezafe kone
        LinkedList<String> subjectList=new LinkedList<>();
        sqLiteDBHelper = new SQLiteDBHelper(this);
        //sqLiteDBHelper.dropTable(sqLiteDBHelper.getDatabase());


        //adding project dialog
        // بیلدرشو تعریف کن با دیلوگ و فقط شو کن
        mBuilder= new MaterialDialog.Builder(this);
        mBuilder.customView(R.layout.addig_project_dialog_layout,false);
        mDialog=mBuilder.build();
//        mDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_dialog)); //vase inke kenaraye dialog gerd beshe

        initRecyclerView();


        Toast.makeText(this, Core.getTime(), Toast.LENGTH_LONG).show();



        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.show();

            }
        });

        initDialog();

    }


    private void initDialog(){

        AppCompatButton btnBackAddingDialog = (AppCompatButton) mDialog.findViewById(R.id.btnBackAddingDialog);
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
                else if (edtAddingDialogHourPerDay.getText().toString().equals("")){
                    edtAddingDialogHourPerDay.setError("is Empty...");
                }
                else {

                    List<DatabaseModel1> list = new ArrayList<>();
                    list.add(new DatabaseModel1(//Core.getTime(),
                            edtAddingDialogSubjName.getText().toString() ,
                            Integer.valueOf(edtAddingDialogSubjPriority.getText().toString()) ,
                            Integer.valueOf(edtAddingDialogDayPerWeek.getText().toString()),
                            Integer.valueOf(edtAddingDialogHourPerDay.getText().toString())

                    ));

                    sqLiteDBHelper.insertSubjects(list);

                    mDialog.dismiss();
                    initRecyclerView();

                }
            }
        });

    }

    private void initRecyclerView(){

        // وقتی دیتا رو درست وارد کردی یبار دیگه این متد رو صدا میکنی

        List<DatabaseModel1> list = sqLiteDBHelper.getTable();


        RecyclerView recycler = findViewById(R.id.recycler);
        NamesAdapter adapter = new NamesAdapter(MenuActivity.this,list);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(MenuActivity.this,LinearLayout.VERTICAL,false));
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
