package com.example.classproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;


public class Settingfragment extends Fragment {
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_settingfragment, container, false);
        listView=view.findViewById(R.id.settinglist);

        String[] array = getResources().getStringArray(R.array.setting);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                if(i==0)
                {
                    Intent intent = new Intent(getActivity(),loginactivity.class);
                    startActivity(intent);
                }
                else if(i==1)
                {
                    getActivity().moveTaskToBack(true);
                    android.os.Process.killProcess(Process.myPid());
                    System.exit(1);
                }
            }
        });




        return view;
    }
}
