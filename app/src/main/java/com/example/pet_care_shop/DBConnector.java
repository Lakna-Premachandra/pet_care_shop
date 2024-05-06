package com.example.pet_care_shop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnector extends SQLiteOpenHelper
{
    public DBConnector(Context context)
    {
        super(context,"User",null,1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table UserInfo(UserId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Email VARCHAR,Password VARCHAR,UserType VARCHAR)");
        db.execSQL("create table CustomerInfo(CustomerId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Name VARCHAR,Pet_name VARCHAR,Phone VARCHAR, Pet_breed VARCHAR, Pet_type VARCHAR, Pet_age VARCHAR, Pet_sex VARCHAR, Times VARCHAR)");
        db.execSQL("create table UserReviews(ReviewId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Name VARCHAR,Address VARCHAR,Review VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
