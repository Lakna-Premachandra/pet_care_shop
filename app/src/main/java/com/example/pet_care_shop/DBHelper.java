package com.example.pet_care_shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper
{
    private Context con;
    private SQLiteDatabase db;

    public DBHelper(Context con)
    {
        this.con=con;
    }

    public DBHelper OpenDB(){
        DBConnector dbCon=new DBConnector(con);
        db=dbCon.getWritableDatabase();
        return this;
    }

    public boolean SignUpUser(User user){
        try {
            ContentValues cv=new ContentValues();
            cv.put("Email",user.getEmail());
            cv.put("Password",user.getPassword());
            cv.put("UserType",user.getUserType());

            db.insert("UserInfo",null,cv);
            return true;
        }
        catch (Exception ex){
            Toast.makeText(con,ex.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public ArrayList<User> ValidLogin(String Email, String Password)
    {
        ArrayList<User> userList=new ArrayList<User>();
        try {
            Cursor cursor=db.rawQuery("Select * from UserInfo where Email='"+Email+"'and Password='"+Password+"' ",null);
            if (cursor.moveToFirst())
            {
                do {
                    User user=new User();
                    user.setEmail(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    user.setUserType(cursor.getString(3));
                    userList.add(user);
                }while (cursor.moveToNext());
            }
        }catch (Exception ex)
        {
            Toast.makeText(con,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
        return userList;
    }

    public boolean SaveCustomerAdInfo(CustomerAdInfo customerAdInfo){
        try {
            ContentValues cv=new ContentValues();
            cv.put("Name",customerAdInfo.getName());
            cv.put("Pet_name",customerAdInfo.getPet_name());
            cv.put("phone",customerAdInfo.getPhone());
            cv.put("Pet_breed",customerAdInfo.getPet_breed());
            cv.put("Pet_type",customerAdInfo.getPet_type());
            cv.put("Pet_age",customerAdInfo.getPet_age());
            cv.put("Pet_sex",customerAdInfo.getPet_sex());
            cv.put("Times",customerAdInfo.getTimes());

            db.insert("CustomerInfo",null,cv);
            return true;
        }
        catch (Exception ex){
            Toast.makeText(con,ex.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public Cursor getdata(){
        DBConnector dbCon=new DBConnector(con);
        db=dbCon.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from CustomerInfo", null);
        return cursor;
    }

    public boolean SaveCleanerReview(ClnReviews clnReviews){
        try {
            ContentValues cv =new ContentValues();
            cv.put("Name",clnReviews.getName());
            cv.put("Address",clnReviews.getAddress());
            cv.put("Review",clnReviews.getReview());

            db.insert("UserReviews",null,cv);
            return true;
        }
        catch (Exception ex){
            Toast.makeText(con,ex.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean SaveCustomerReview(CstReviews cstReviews){
        try {
            ContentValues cv =new ContentValues();
            cv.put("Name",cstReviews.getName());
            cv.put("Address",cstReviews.getAddress());
            cv.put("Review",cstReviews.getReview());

            db.insert("UserReviews",null,cv);
            return true;
        }
        catch (Exception ex){
            Toast.makeText(con,ex.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public Cursor getReviewsData(){
        DBConnector dbCon=new DBConnector(con);
        db=dbCon.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from UserReviews", null);
        return cursor;
    }
}