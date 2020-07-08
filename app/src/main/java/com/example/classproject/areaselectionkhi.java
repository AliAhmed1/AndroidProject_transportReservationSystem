package com.example.classproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class areaselectionkhi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areaselectionkhi);
        ListView list = findViewById(R.id.areas);
        //list.setAdapter(cityadapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectareas = String.valueOf(adapterView.getItemAtPosition(i));
                Intent intent = new Intent(adapterView.getContext(),getreservationactivity.class);
                adapterView.getContext().startActivity(intent);
            }
        });
    }
}
