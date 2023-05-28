package com.example.spareparts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelperSp extends SQLiteOpenHelper {
    public DBhelperSp(Context context) {
        super(context, "SPmini.db", null, 1);
}
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table sparepart(id INTEGER primary key AUTOINCREMENT, name TEXT, price TEXT, quntity TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public Boolean prodinsart(String name, String price, String quntity)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("name", name);
        val.put("price", price);
        val.put("quntity", quntity);
        long result=DB.insert("sparepart", null, val);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateprod(String id,String name, String price, String quntity)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put("id", id);
        val.put("name", name);
        val.put("price", price);
        val.put("quntity", quntity);
        Cursor cursor = DB.rawQuery("Select * from sparepart where id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("sparepart", val, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deleteprod (String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from sparepart where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("sparepart", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor showproducts ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from sparepart", null);
        return cursor;
    }
}