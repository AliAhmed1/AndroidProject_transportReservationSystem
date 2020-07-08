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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class Balancefragment extends Fragment {

    public int addvalue;
   signupdatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_balancefragment, container, false);

        //int num =0;
        //pay.setText(num);
        db= new signupdatabase(getActivity());


        Button btn = view.findViewById(R.id.gotobalance);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), balanceactivity.class);
                startActivity(intent);
            }

        });
        Button btn2 = view.findViewById(R.id.viewtobalance);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String user1 = preference.getString("mail", "no id");
                Cursor res=db.select3(user1);

                if(res.getCount()==0)
                {
                    addvalue=0;
                    showmessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("balance :" +res.getString(1)+"\n");
                    buffer.append("user :" +res.getString(2)+"\n");
                    addvalue=Integer.parseInt(res.getString(1));
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("addbalance", addvalue); //InputString: from the EditText
                editor.commit();
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