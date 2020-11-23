package com.sudipacharya.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COLUMN1_ID = "ID";
    public static final String COLUMN2_NAME = "NAME";
    public static final String COLUMN3_SURNAME = "SURNAME";
    public static final String COLUMN4_MARKS = "MARKS";

    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
        //Whenever Database Helper is called all the upper columns table will be called
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , SURNAME TEXT , MARKS INTEGER ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //For Inserting the data into database we use boolean
    public boolean insertData(String name, String surname, String marks){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN2_NAME, name);
        contentValues.put(COLUMN3_SURNAME, surname);
        contentValues.put(COLUMN4_MARKS, marks);
       long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        return result != -1;
        /*upper returns is
        if(result == -1)
        return false;
        else
        return true;
         */
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor results = sqLiteDatabase.rawQuery("select * from "+ TABLE_NAME,null);
        return results;
    }

    public Cursor GetId(String id) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = ?", new String[]{ id }, null);
             return cursor;
    }

    public boolean updateData(String id, String name, String surname, String marks){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN1_ID, id);
        contentValues.put(COLUMN2_NAME, name);
        contentValues.put(COLUMN3_SURNAME, surname);
        contentValues.put(COLUMN4_MARKS, marks);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       return sqLiteDatabase.delete(TABLE_NAME,"ID = ?", new String[] { id });
        //whereclause is on the basis of ...
    }

}
