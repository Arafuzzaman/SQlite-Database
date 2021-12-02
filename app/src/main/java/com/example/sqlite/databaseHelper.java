package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import androidx.annotation.Nullable;
class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Book.db";
    public static final String TABLE_NAME="employee";
    public static final String COL_1="Id";
    public static final String COL_2="designation";
    public static final String COL_3="experience";
    public static final String COL_4="phno";
    public static final String COL_5="name";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(Id INTEGER PRIMARY KEY AUTOINCREMENT,designation TEXT,experience TEXT,phno INTEGER,name TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(Editable text, String designation, String experience, String
            phno, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,designation);
        contentValues.put(COL_3,experience);
        contentValues.put(COL_4,phno);
        contentValues.put(COL_5,name);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String id,String designation,String experience,String phno,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,designation);
        contentValues.put(COL_3,experience);
        contentValues.put(COL_4,phno);
        contentValues.put(COL_5,name);
        db.update(TABLE_NAME, contentValues, "Id = ?",new String[] { id
        });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Id = ?",new String[] {id});
    }
}
