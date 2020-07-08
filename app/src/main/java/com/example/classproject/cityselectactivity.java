package com.example.classproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.lang.reflect.Array;

public class cityselectactivity extends AppCompatActivity {


   // String[] cityarray={"A","B"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityselectactivity);
        //cityarray = getResources().getStringArray(R.array.city);
        //ArrayAdapter cityadapter = new ArrayAdapter(this,R.layout.activity_cityselectactivity,cityarray);
        ListView list = findViewById(R.id.cities);
        //list.setAdapter(cityadapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String select = String.valueOf(adapterView.getItemAtPosition(i));
                Intent intent = new Intent(adapterView.getContext(),areaselectionkhi.class);
                adapterView.getContext().startActivity(intent);
            }
        });

    }


    //public void selectcity(View view) {
     //   Intent intent = new Intent(this,areaselectionkhi.class);
      //  startActivity(intent);
    //}
}
