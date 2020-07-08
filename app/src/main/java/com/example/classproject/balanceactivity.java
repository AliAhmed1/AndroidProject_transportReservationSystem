package com.example.classproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class balanceactivity extends AppCompatActivity {


    EditText id,balance;
    signupdatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balanceactivity);
    id=findViewById(R.id.ac_id);
    balance=findViewById(R.id.balance);
    db = new signupdatabase(this);
    }

    public void recharge(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userget = prefs.getString("mail" ,"no id");
        int aid;
        int ablc;

        SharedPreferences prefer = PreferenceManager.getDefaultSharedPreferences(this);
        Integer add = prefer.getInt("addbalance" ,0);
        aid=Integer.parseInt(id.getText().toString());
        ablc=Integer.parseInt(balance.getText().toString());
        Fragment fragment = new Fragment();
        fragment = new Balancefragment();
        int addvalue = ((Balancefragment) fragment).addvalue;
        if (add>0) {
            ablc += add;
        }
        else
        {

        }
        boolean chk = db.checkaccount(userget);
        if(!id.getText().equals("") || !balance.getText().equals("")) {
            if(chk==false)
            {
             db.updateaccount(userget,aid,ablc);
                Intent intent = new Intent(this, homepageactivity.class);
                startActivity(intent);
                SharedPreferences prefsss = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = prefsss.edit();
                editor.putString("user", userget);
                editor.putString("aid", String.valueOf(aid));
                editor.putString("ablc", String.valueOf(ablc));//InputString: from the EditText
                editor.commit();
            }
            else {
                db.insertbalance(aid, ablc, userget);
                Intent intent = new Intent(this, homepageactivity.class);
                startActivity(intent);
                SharedPreferences prefss = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = prefss.edit();
                editor.putString("user", userget);
                editor.putString("aid", String.valueOf(aid));
                editor.putString("ablc", String.valueOf(ablc));//InputString: from the EditText
                editor.commit();
            }
        }
        else if (id.getText().equals(0) || balance.getText().equals(0))
        {
            Toast.makeText(this,"Incorrect Entries",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"Invalid",Toast.LENGTH_LONG).show();
        }

    }
}
