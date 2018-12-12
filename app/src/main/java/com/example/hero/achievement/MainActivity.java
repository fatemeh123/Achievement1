package com.example.hero.achievement;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signUpButton=findViewById(R.id.btnSignUp);
    /*
    connecting sign in and sign up fragments into it
    the question is can we use fragments for sigh in ?
     */

        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivityForResult(intent,150);

        //mikhaym bedoonim ke ay in user ghablan login karde ya na. agelogin karde yeho varede application beshim




   }
}

/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();


        AlertDialog dialog=new AlertDialog.Builder(MainActivity.this).create();
        dialog.setTitle("Exit!");
        dialog.setMessage("Are you sure to exit the application?");
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //khrooj az application
            }
        });
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

}
**/