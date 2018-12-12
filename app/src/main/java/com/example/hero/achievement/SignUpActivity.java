package com.example.hero.achievement;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText edtUsername=findViewById(R.id.edtUsername);
        EditText edtEmail=findViewById(R.id.edtEmail);
        EditText editPassword=findViewById(R.id.edtPassword);


        //aval mikhaym etelaat ro az kaarbar begirim
        //Intent intent=new Intent();
        String name=edtUsername.getText().toString();
        String email=edtEmail.getText().toString();
        String password=editPassword.getText().toString();


        //mikham inja yek dialog activity baz she bad bege ke aya mikhyd zakhire konid ya ana age khas ino  zakhire kone


        PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this).edit().putString("name",name).apply();
        PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this).edit().putString("email",email).apply();
        PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this).edit().putString("password",password).apply();


        // dg  too kolle application in etelaat zakhite hast har vaght mikhastimeshoon injoori az kaarbar daryaft mikonim
        //dg lazem nis ke beyne activitia paasesh bedim ino tooye sharePreferencemoon zakhire mikonim

        String deatUser =PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this).getString("name","Dear user");

    }
}
