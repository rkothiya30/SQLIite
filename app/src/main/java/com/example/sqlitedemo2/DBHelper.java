package com.example.sqlitedemo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MyTable (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, age TEXT, active TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("age", user.getAge());
        values.put("active", user.getActive());

        long myTable = db.insert("MyTable", null, values);
        db.close();
        if (myTable == -1) {
            return false;
        } else {
            return true;
        }

    }




    public List<User> showData(){
        List<User> lu = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM MyTable", null);
        if(cursor.moveToFirst()){
            do{
                User u1 = new User(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                lu.add(u1);
            }while(cursor.moveToNext());
        }
        return lu;
    }


    public int UpdateUser(User u){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("email",u.getEmail());
            values.put("age", u.getAge());
            values.put("active", u.getActive());
            return db.update("MyTable", values, "name=?", new String[]{ u.getName() });


    }

    public int DeleteUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("MyTable","name=?", new String[]{ u.getName() });
    }



}
