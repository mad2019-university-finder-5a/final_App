package com.example.ozedu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "feedback.db";
    public static final String TABLE_NAME = "feedback_table";
    public static final String COL_1 = "FID";
    public static final String COL_2 = "Sname";
    public static final String COL_3= "ContactNo";
    public static final String COL_4 = "email";
    public static final String COL_5 = "feedback";
    public FDBHelper(Context context) {
        super(context, DATABASE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (FID INTEGER PRIMARY KEY AUTOINCREMENT,Sname TEXT,ContactNo INTEGER,email TEXT,feedback TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Sname,String ContactNo,String email,String feedback){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Sname);
        contentValues.put(COL_3,ContactNo);
        contentValues.put(COL_4,email);
        contentValues.put(COL_5,feedback);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME ,null);
        return res;

    }

    public boolean updateData(String FID,String Sname,String ContactNo,String email,String feedback){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,FID);
        contentValues.put(COL_2,Sname);
        contentValues.put(COL_3,ContactNo);
        contentValues.put(COL_4,email);
        contentValues.put(COL_5,feedback);
        db.update(TABLE_NAME, contentValues, "FID = ?",new String[] {FID});
        return true;


    }

    public Integer deleteData (String FID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "FID = ?",new String[] {FID});





    }


}
