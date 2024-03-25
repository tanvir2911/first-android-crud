package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import kotlin.Suppress;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userQuery = "create table user(id integer PRIMARY KEY AUTOINCREMENT,"+
                " name text, email text, phone text, password text)";

        String studentQuery = "create table student(id integer PRIMARY KEY AUTOINCREMENT,"+
                " name text, department text, address text, phone text, gender text, active text)";

        db.execSQL(userQuery);
        db.execSQL(studentQuery);
    }
    
    
    public void addNewUser(String name, String email,  String password,String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("email", email);
        values.put("phone", phone);
        values.put("password", password);

        db.insert("user",null,values);

        db.close();
    }

    public void addNewStudent(String name, String department, String address, String phone, String gender, String active){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("department", department);
        values.put("address",address);
        values.put("phone",phone);
        values.put("gender",gender);
        values.put("active",active);

        db.insert("student",null,values);
        db.close();
    }

    public ArrayList<HashMap<String,String>> getStudents(){
        HashMap<String, String> student;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from student", null);

        ArrayList<HashMap<String,String>> studentList = new ArrayList<>(c.getCount());

        if(c.moveToFirst()){
            do{
                student = new HashMap<>();
                student.put("name", c.getString(1));
                student.put("department", c.getString(2));
                student.put("address", c.getString(3));
                student.put("phone",c.getString(4));
                student.put("gender",c.getString(5));

                studentList.add(student);
            }while (c.moveToNext());
        }

        db.close();
        return studentList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public int login(String email, String password){
        int result = 0;
        String[] arr = new String[2];
        arr[0] = email;
        arr[1] = password;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from user where email=? and password=? ", arr);
        if (c.moveToFirst()){
            return 1;
        }
        return 0;
    }
}
