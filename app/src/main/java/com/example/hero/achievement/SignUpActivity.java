package com.example.hero.achievement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText edtUsername ;
    EditText edtEmail    ;
    EditText edtPassword;
    EditText edtPhone;
    Button saveBtn       ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final SharedPreferences mSharedPreferences;


        initiviews();

        String userName1=edtUsername.getText().toString();
        final String email=edtEmail.getText().toString();
        final String password1= edtPassword.getText().toString();

        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
       // userName1 =  mSharedPreferences.getString("userName", "");




        //mikham inja yek dialog activity baz she bad bege ke aya mikhyd zakhire konid ya ana age khas ino  zakhire kone


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName;
                String password;

                Log.d("star","preferences ");

                if (edtUsername.getText().toString().equals("")){
                    edtUsername.setError("is Empty...");

                } else if (edtEmail.getText().toString().equals("")){
                    edtEmail.setError("is Empty...");

                } else  if (edtPassword.getText().toString().equals("")){
                    edtPassword.setError("is Empty...");

                } else  if (edtPhone.getText().toString().equals("")){
                    edtPhone.setError("is Empty...");

                } else {

                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("userName", edtUsername.getText().toString());
                    editor.commit();
                    editor.putString("password",edtPassword.getText().toString());
                    editor.commit();
                    editor.putString("phone",edtPhone.getText().toString());
                    editor.commit();

                    Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
                    startActivity(intent);
                }

              //  userName = PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this).getString("userName","aaa");
                //password = PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this).getString("password","123");


                //Toast.makeText(SignUpActivity.this, userName+password, Toast.LENGTH_LONG).show();
            }
        });

       // String deatUser =PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this).getString("name","Dear user");

    }
    public void initiviews(){


         edtUsername   =findViewById(R.id.edtUsername);
         edtEmail       =findViewById(R.id.edtEmail);
         edtPassword =findViewById(R.id.edtPassword);
         saveBtn        =findViewById(R.id.btnSave);
         edtPhone =findViewById(R.id.phoneNo);


    }
}
