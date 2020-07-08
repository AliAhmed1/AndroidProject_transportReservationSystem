package com.example.classproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class loginactivity extends AppCompatActivity {

    signupdatabase db;
    EditText email, pass ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        db=new signupdatabase(this);
        email=findViewById(R.id.emailtext);
        pass=findViewById(R.id.passtext);
    }

    public void loginaccess(View view) {
        String mail = email.getText().toString();
        String password = pass.getText().toString();


        boolean c =db.select(mail,password);

        if(c==false ) {
            Intent intent = new Intent(this, homepageactivity.class);
            startActivity(intent);
            Toast.makeText(this,"Login Successful",Toast.LENGTH_LONG).show();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("mail", mail); //InputString: from the EditText
            editor.commit();

           // Intent intent1 = new Intent(this,getreservationactivity.class);
            //intent1.putExtra("mail",mail);
        }
        else if(c==true)
        {
            Toast.makeText(this,"Incorrect",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"User not found",Toast.LENGTH_LONG).show();
        }
    }
}
