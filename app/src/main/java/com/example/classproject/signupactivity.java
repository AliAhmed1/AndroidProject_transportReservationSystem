package com.example.classproject;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class signupactivity extends AppCompatActivity {


    signupdatabase db;
    EditText fname , lname , username , password,dob;
    Spinner country;
    RadioGroup gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);
        fname=findViewById(R.id.firstname);
        lname=findViewById(R.id.lastname);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        country=findViewById(R.id.country);
        gender=findViewById(R.id.genderselect);
        dob=findViewById(R.id.dateofbirth);
        db = new signupdatabase(this);

    }

    public void signupaccess(View view) {

        int gen = gender.getCheckedRadioButtonId();
        RadioButton rb = findViewById(gen);
        String firstname = fname.getText().toString();
        String lastname = lname.getText().toString();
        String pword = password.getText().toString();
        String count = country.getSelectedItem().toString();
        String date = dob.getText().toString();


        String email=username.getText().toString();

        if(firstname.equals("")|| lastname.equals("") || email.equals("") || pword.equals("") || gen==0)
        {
            Toast.makeText(this,"Values are not entered",Toast.LENGTH_LONG).show();
        }
        else if (email.equals(pword) )
        {
            Toast.makeText(this,"username and password can not be same",Toast.LENGTH_LONG).show();
        }
        else if (firstname.equals(lastname))
        {
            Toast.makeText(this,"firstname and lastname can not be same",Toast.LENGTH_LONG).show();
        }
        else if(db.select(email,pword)==false)
        {
            Toast.makeText(this,"username already exist",Toast.LENGTH_LONG).show();
        }
        else
        {
            db.insert(firstname, lastname, rb.getText().toString(), email, pword, date,count);
            Toast.makeText(this, "Successfully Signed up", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, loginactivity.class);
            startActivity(intent);

               }

    }
}
