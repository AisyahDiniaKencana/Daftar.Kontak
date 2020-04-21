package com.example.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

class dbKontak extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String MHS_TABLE_NAME = "kontak";
    public static final String MHS_COLUMN_TELEPON = "phone";
    public static final String MHS_COLUMN_NAMA = "nama";
    public static final String MHS_COLUMN_ALAMAT = "alamat";
    public static final String MHS_COLUMN_EMAIL = "email";
    private HashMap hp;
    public dbKontak(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

//    public dbKontak(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table kontak " + "(phone integer primary key, nama text,alamat text,email text)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS kontak");
        onCreate(db);
    }
    public boolean insertContact(String s, String nama, String alamat, String
            email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("alamat", alamat);
        contentValues.put("email", email);
        db.insert("kontak", null, contentValues);
        return true;
    }
    public Cursor getData(int phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from kontak where phone="+phone+"", null );
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,
                MHS_TABLE_NAME);
        return numRows;
    }

    public ArrayList<String> getAllContacs() {
        ArrayList<String> array_list = new ArrayList<String>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from kontak", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){

            array_list.add(res.getString(res.getColumnIndex(MHS_COLUMN_NAMA)));
            res.moveToNext();
        }
        return array_list;
    }
}
