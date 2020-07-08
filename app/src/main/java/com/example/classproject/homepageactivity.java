package com.example.classproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class homepageactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepageactivity);
        Fragment fragment=null;
        fragment = new Reservationsfragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.homecontainer , fragment);
        ft.commit();
    }

    public void reservationpage(View view) {
        Fragment fragment=null;
        fragment = new Reservationsfragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.homecontainer , fragment);
        ft.commit();
    }

    public void balancepage(View view) {
        Fragment fragment=null;
        fragment = new Balancefragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.homecontainer , fragment);
        ft.commit();
    }

    public void settingpage(View view) {
        Fragment fragment=null;
        fragment = new Settingfragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.homecontainer , fragment);
        ft.commit();
    }
}
