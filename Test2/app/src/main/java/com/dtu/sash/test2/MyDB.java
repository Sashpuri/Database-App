package com.dtu.sash.test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hp pc on 24-01-2016.
 */
public class MyDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="products.db";
    public static final String TABLE_PRODUCTS="products";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_NAME="_name";
    public static final String COLUMN_PNO="_pno";
    public static final String COLUMN_ENTITY="_entity";
    public static final String COLUMN_REGION="_region" ;

    public MyDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS+"("+
                COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME+" TEXT, "+
                COLUMN_PNO+" TEXT, "+
                COLUMN_ENTITY+" TEXT, "+
                COLUMN_REGION+" TEXT, "+

                        ")";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
        onCreate(db);
    }
    public void addItem(Products products){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, products.get_name());
        values.put(COLUMN_PNO, products.get_pno());
        values.put(COLUMN_ENTITY, products.get_entity());
        values.put(COLUMN_REGION, products.get_region());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }
    public String searchItem(int id,String name,String pno,String entity,String region){
        SQLiteDatabase db=getWritableDatabase();
        String dbString="";
        String query="SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + "=\"" + id + "\" " +
                "OR "+COLUMN_NAME + "=\"" + name + "\"+ " +
                "OR "+COLUMN_PNO + "=\"" + pno + "\"+ " +
                "OR "+COLUMN_ENTITY + "=\"" + entity + "\"+ " +
                "OR "+COLUMN_REGION + "=\"" + region + "\"";

        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_name"))!=null) {
                dbString+=c.getString(c.getColumnIndex("_name"));

                dbString+="    ";
                dbString+=c.getString(c.getColumnIndex("_pno"));

                dbString+="      ";
                dbString+=c.getString(c.getColumnIndex("_entity"));
                dbString+="      ";
                dbString+=c.getString(c.getColumnIndex("_region"));

                dbString+="\n";
                c.moveToNext();
            }
            db.close();
    }

    return dbString;
    }

    public void removeItem(String id)
    {
        SQLiteDatabase db=getWritableDatabase();
        //Log.w("MyApp1", "remove item1");
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + "=\"" + id + "\";");
        //Log.w("MyApp1", "remove item2");
    }
    public String displayTable()
    {
       String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_PRODUCTS +" WHERE 1";
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("_name"))!=null) {
                dbString+=c.getString(c.getColumnIndex("_id"));

                dbString+="    ";

                dbString+=c.getString(c.getColumnIndex("_name"));

                dbString+="    ";
                dbString+=c.getString(c.getColumnIndex("_pno"));

                dbString+="      ";
                dbString+=c.getString(c.getColumnIndex("_entity"));
                dbString+="      ";
                dbString+=c.getString(c.getColumnIndex("_region"));

                dbString+="\n";
                c.moveToNext();
            }
            db.close();
        }


        return dbString;
    }

}
