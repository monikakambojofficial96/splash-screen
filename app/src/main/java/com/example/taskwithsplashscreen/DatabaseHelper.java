package com.example.taskwithsplashscreen;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.taskwithsplashscreen.utils.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(@Nullable Context context) {


        super(context,Constants.DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " +Constants.TABLE_NAME+ "(NAME TEXT,PHONE INTEGER(10), EMAIL TEXT primary key  ,ADDRESS TEXT, CITY TEXT,GENDER INTEGER)");
        db.execSQL("create table "+Constants.SECOND_TABLE_NAME+ "(ID INTEGER,HOBBIES TEXT )");
        db.execSQL ( "create table "+Constants.THIRD_TABLE_NAME+ "(ID INTEGER primary key autoincrement, USER_ID TEXT,HOBBIES_ID INTEGER)");
        db.execSQL ( "create table "+Constants.FOURTH_TABLE_NAME+ "(ID INTEGER primary key autoincrement,HOBBIES TEXT)");
        db.execSQL("INSERT INTO "+ Constants.FOURTH_TABLE_NAME +"(HOBBIES) VALUES( 'Books Reading');");
        db.execSQL("INSERT INTO "+ Constants.FOURTH_TABLE_NAME+" (HOBBIES) VALUES('NewsPaper Reading');");
        db.execSQL("INSERT INTO "+Constants.FOURTH_TABLE_NAME+ " (HOBBIES) VALUES('Supports');");
        db.execSQL("INSERT INTO "+Constants.FOURTH_TABLE_NAME+ " (HOBBIES) VALUES('Movies');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE iF EXISTS "+Constants.TABLE_NAME);
        db.execSQL(" DROP TABLE iF EXISTS "+Constants.SECOND_TABLE_NAME);
        db.execSQL(" DROP TABLE iF EXISTS "+Constants.THIRD_TABLE_NAME);
        db.execSQL(" DROP TABLE iF EXISTS "+Constants.FOURTH_TABLE_NAME);
        onCreate(db);
    }

    boolean insertData(String name, String phone, String email, String address,String city , String gender ){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put( Constants.SECOND_COLUMN,name);
        contentValues.put(Constants.THIRD_COLUMN,phone);
        contentValues.put(Constants.FOURTH_COLUMN,email);
        contentValues.put(Constants.FIFTH_COLUMN,address);
        contentValues.put(Constants.SIXTH_COLUMN,city);
        contentValues.put(Constants.SEVENTH_COLUMN,gender);
//        contentValues.put (Constants.EIGTH_COLUMN,hobies );


        long result=db.insert(Constants.TABLE_NAME,null,contentValues);

        return result != -1;


    }

    boolean insertHobbies(int Id,String Hobbies){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues ( );
        contentValues.put(Constants.HOBBIES,Hobbies);
        contentValues.put ( Constants.ID,  Id );
        long result=db.insert(Constants.SECOND_TABLE_NAME,null,contentValues);
        return result != -1;
    }
    boolean insertFinal(String USER_ID,String HOBBIES_ID){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("USER_ID ",USER_ID);
        contentValues.put("HOBBIES_ID",HOBBIES_ID);
        long result=db.insert(Constants.THIRD_TABLE_NAME,null,contentValues);
        return result != -1;
    }
//    boolean insertion(String hobbie1, String hobbie2, String hobbie3, String hobbie4){
//
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues contentValues=new ContentValues();
//        contentValues.put("HOBBIES",hobbie1);
//        contentValues.put("HOBBIES",hobbie2);
//        contentValues.put("HOBBIES",hobbie3);
//        contentValues.put("HOBBIES",hobbie4);
//        long result=db.insert(Constants.THIRD_TABLE_NAME,null,contentValues);
//        return result != -1;

}
