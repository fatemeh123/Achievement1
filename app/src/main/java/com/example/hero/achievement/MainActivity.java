package com.example.hero.achievement;

import android.content.Intent;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnSignUp;
    private Button btnSignIn;
    private EditText usernameEdt;
    private EditText passwordEdt;
    Boolean hasUserClickedOnBack=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if the username and password is Entered or not
                if (usernameEdt.getText().toString().equals("")){
                    usernameEdt.setError("is Empty...");

                } else if (passwordEdt.getText().toString().equals("")){
                    passwordEdt.setError("is Empty...");

                } else{
                    Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(intent);
                }
            }
        });


   }
   //intialize the views
    private void initViews(){
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnLogIn);
        usernameEdt = findViewById(R.id.edtUsername);
        passwordEdt = findViewById(R.id.edtPassword);
       // PreferenceManager.setDefaultValues("email",              );

        String userName = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("userName","");
        String password = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("password","");

/*************************************************************
 * dg vaghti user yebar log in mione yeho varede safeye badi mishe o login o nmiare
        if(!userName.equals("")&&!password.equals("")){
            usernameEdt.setText(userName);
            passwordEdt.setText(password);
            Intent intent = new Intent(MainActivity.this,MenuActivity.class);
            startActivity(intent);
        }
*/
        if(!userName.equals("")&&!password.equals("")){
            usernameEdt.setText(userName);
        }
        if(!password.equals("")){
            passwordEdt.setText(password);
        }

    }
    /*
     if the user clicked on back
*/
    @Override
    public void onBackPressed() {


        //avalin bar ke miaym too in method yani kaarbar  ybar dokmeye backo click karde

        if (hasUserClickedOnBack==false){
            Toast.makeText(MainActivity.this,"If you want to leave the application please press on bach again",Toast.LENGTH_LONG ).show();
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


