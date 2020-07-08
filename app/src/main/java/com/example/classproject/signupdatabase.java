package com.example.classproject;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.preference.PreferenceManager;
import android.widget.EditText;

import java.nio.charset.IllegalCharsetNameException;
import java.sql.ResultSet;
import java.util.Date;

public class signupdatabase extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "transport.db";

    public String TABLE_NAME = "userinfo";
    public String COL_1 = "userid";
    public String COL_2 = "firstname";
    public String COL_3 = "lastname";
    public String COL_4 = "gender";
    public String COL_5 = "username";
    public String COL_6 = "password";
    public String COL_7 = "date_of_birth";
    public String COL_8 = "country";
    public String userentries = " create table " + TABLE_NAME + "(" + COL_1 + " integer primary key autoincrement , " + COL_2 + " text not null ," +
            " " + COL_3 + " text not null , " + COL_4 + " text not null , " + COL_5 + " text not null , " + COL_6 + " text not null , " + COL_7 + " text not null" +
            " , " + COL_8 + " text not null)";

    //public String TABLE_2="AvailableReservation";
    //public String VAL_1="aid";
    //public String VAL_2="totalseats";
    //public String VAL_3="availableseats";
    //public String VAL_4=COL_1;

    public String TABLE_3 = "GettingReservation";

    public String RES_1 = "rid";
    public String RES_2 = "city";
    public String RES_3 = "area";
    public String RES_4 = "noofreservations";
    public String RES_5 = "amount";
    public String RES_6 = COL_5;

    public String TABLE_0 = "userbalance";
    public String BAL_1 = "accountid";
    public String BAL_2 = "accountbalance";
    public String BAL_3 = COL_5;


    public signupdatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(userentries);

        sqLiteDatabase.execSQL("create table " + TABLE_0 + "(" + BAL_1 + " integer primary key unique , " + BAL_2 + " integer , "+BAL_3+" text not null  )   ");


        //  sqLiteDatabase.execSQL("create table "+ TABLE_2 + "("+ VAL_1 +" integer primary key autoincrement " +
        //        ", " +VAL_2+ " text not null , "+VAL_3+" text not null  )");

        sqLiteDatabase.execSQL("create table " + TABLE_3 + "(" + RES_1 + " integer primary key autoincrement " +
                "," + COL_5 + " text not null ," + RES_2 + " text not null," + RES_3 + " text not null," + RES_4 + " integer " +
                "," + RES_5 + ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME + TABLE_3);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String firstname, String lastname, String gender, String username, String password, String dob, String country) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, firstname);
        contentValues.put(COL_3, lastname);
        contentValues.put(COL_4, gender);
        contentValues.put(COL_5, username);
        contentValues.put(COL_6, password);
        contentValues.put(COL_7, dob);
        contentValues.put(COL_8, country);
        long db = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (db == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertreserve(String user, String city, String area, String reserves, Integer amount) {

        ContentValues contentValues = new ContentValues();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //  String userget;
        // select2(userget);


        Cursor id = sqLiteDatabase.rawQuery("select " + COL_1 + " from userinfo where  username=?", new String[]{user});

        contentValues.put(RES_6, user);
        contentValues.put(RES_2, city);
        contentValues.put(RES_3, area);
        contentValues.put(RES_4, reserves);
        contentValues.put(RES_5, amount);
        long db = sqLiteDatabase.insert(TABLE_3, null, contentValues);
        if (db == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertbalance(int id, int balance, String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BAL_1, id);
        values.put(BAL_2, balance);
        values.put(BAL_3, user);
        long db2 = db.insert(TABLE_0, null, values);
        if (db2 == -1) {
            return false;
        } else {
            return true;
        }
    }
    public void delete()
    {
     SQLiteDatabase db = this.getWritableDatabase();
     db.delete(TABLE_NAME , "ID = ?" ,new String[]{COL_1});
    }
    public boolean select(String user , String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from userinfo where username=? and password=?",new String[]{user,pass,});
        if (res.getCount()>0)
        {return false;}
        else
        {return true;}
    }
    public Cursor select2(String user) {


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_3+" where username =?",new String[]{user});
        return res;
    }
    public Cursor select3(String user) {


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_0+" where username =?",new String[]{user});
        return res;
    }

    public Integer selectbalance(int balance,String user)
    {
        balance=Integer.parseInt(BAL_2);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select amount from "+ TABLE_3+" where  username=?",new String[]{user});;
        return balance;
    }
    public void update(String a , int balance )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BAL_2,balance);
        db.update(TABLE_0,cv,COL_5+"=?",new String[]{a});
    }
    public boolean checkaccount(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from userbalance where username=?",new String[]{user});
        if (res.getCount()>0)
        {return false;}
        else
        {return true;}
    }
    public void updateaccount(String a , int id, int balance )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BAL_1,id);
        cv.put(BAL_2,balance);
        db.update(TABLE_0,cv,COL_5+"=?",new String[]{a});
    }
}
