package com.example.classproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Reservationsfragment extends Fragment {

    signupdatabase db;
    //TextView user , reservations , city;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservationsfragment, container, false);
        db= new signupdatabase(getActivity());
        Button btn = view.findViewById(R.id.gotoreserve);
        Button vw = view.findViewById(R.id.viewreserve);
      //  user = view.findViewById(R.id.userview);
      //  reservations = view.findViewById(R.id.toreserves);
      //  city = view.findViewById(R.id.viewcity);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), getreservationactivity.class);
                startActivity(intent);
            }
        });

        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                          SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getActivity());
                         String user1 = preference.getString("mail", "no id");
                Cursor res=db.select2(user1);
                if(res.getCount()==0)
                {
                    showmessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("rid :" +res.getString(0)+"\n");
                    buffer.append("user :" +res.getString(1)+"\n");
                    buffer.append("city :" +res.getString(2)+"\n");
                    buffer.append("Area :" +res.getString(3)+"\n");
                    buffer.append("reservations :" +res.getString(4)+"\n");
                    buffer.append("amount :" +res.getString(5)+"\n\n");
                }
                showmessage("Data" , buffer.toString());
            }
        });

        return view;
    }
    public void showmessage(String title , String message)
    {
        AlertDialog.Builder bd =new AlertDialog.Builder(getActivity());
        bd.setCancelable(true);
        bd.setTitle(title);
        bd.setMessage(message);
        bd.show();
    }
}
