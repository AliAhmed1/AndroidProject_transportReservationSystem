package com.example.classproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.renderscript.Sampler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class getreservationactivity extends AppCompatActivity {

    Spinner cityspnr,areaspnr;
    Spinner noofres;
    TextView famount , timeof ;
    signupdatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getreservationactivity);
        noofres = findViewById(R.id.reservationstext);
        famount = findViewById(R.id.Feeamount);
        cityspnr = findViewById(R.id.cityspinnerdefault);
        timeof = findViewById(R.id.time);

       // pay = findViewById(R.id.cash);
        db = new signupdatabase(this);

       // Bundle bundle = getIntent().getExtras();
        //check.setText(bundle.getString("mail"));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String chk = prefs.getString("mail" ,"no id");


        final int[] val = getResources().getIntArray(R.array.values);
        final Integer[] number = {1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter a = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item, number);
        noofres.setAdapter(a);


        final String[] cities = {"karachi", "lahore "," Islamabad" , "Quetta"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cities);
        cityspnr.setAdapter(arrayAdapter);

        areaspnr = findViewById(R.id.areaspinnerdefault);
        final String[] khi = {"North karachi", "North Nazimabad", "saddar"};
        final String[] lhr = {"Allama Iqbal town", "sabzazar"};
        final String[] isl = {"chowk", "block"};
        final String[] qut = {"ghaat", "chandigar"};

        noofres.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int price = 100;
                int select = number[i];
                int total = price*select;
                    famount.setText(total+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cityspnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   String itemselect = cities[i];

         if(i==0)
       {
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getreservationactivity.this,android.R.layout.simple_spinner_dropdown_item,khi);
        areaspnr.setAdapter(arrayAdapter);
        timeof.setText("2AM-6AM");

       }
        else if(i==1)
        {
       ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getreservationactivity.this,android.R.layout.simple_spinner_dropdown_item,lhr);
     areaspnr.setAdapter(arrayAdapter);
     timeof.setText("2AM-5AM");

        }
       else if(i==2)
       {ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getreservationactivity.this,android.R.layout.simple_spinner_dropdown_item,isl);
        areaspnr.setAdapter(arrayAdapter);
        timeof.setText("3AM-7AM");

       }
        else if(i==3)
        { ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getreservationactivity.this,android.R.layout.simple_spinner_dropdown_item,qut);
        areaspnr.setAdapter(arrayAdapter);
        timeof.setText("3AM-8AM");
        }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    public void reserved(View view) {
        String city = cityspnr.getSelectedItem().toString();
        String area = areaspnr.getSelectedItem().toString();
        String res = noofres.getSelectedItem().toString();
        Integer amount = Integer.parseInt(famount.getText().toString());

        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString("city", city);
        editor.putString("area", area);
        editor.putString("res", res);
        editor.putInt("amount", amount);
        int balance;
        SharedPreferences prefer = PreferenceManager.getDefaultSharedPreferences(this);
        balance = prefer.getInt("addbalance" , 0);

        editor.commit();

        //Bundle bundle = getIntent().getExtras();
        //String userget =bundle.getString("mail");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userget = prefs.getString("mail" ,"no id");
        //db.selectbalance(balance,userget);
        if (balance<=0) {
            Toast.makeText(this,"no balance",Toast.LENGTH_LONG).show();
        }
      else if(amount>balance)
        {
           Toast.makeText(this,"Not enough money",Toast.LENGTH_LONG).show();
        }
        else {
            db.insertreserve(userget,city, area, res, amount);
            Toast.makeText(this,"Reservation successful",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,homepageactivity.class);
            startActivity(intent);
            balance=balance-amount;
            db.update(userget,balance);
        }
    }
}


