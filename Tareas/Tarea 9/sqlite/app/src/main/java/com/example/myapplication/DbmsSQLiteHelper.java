package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import androidx.annotation.Nullable;

public class DbmsSQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "agenda.db";
    public static final String TABLE_CONTACTOS = "Contactos";

    String sqlCreate = "CREATE TABLE " + TABLE_CONTACTOS + "(id INTEGER, nombre TEXT)";
    public DbmsSQLiteHelper(@Nullable Context c){
        super(c, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqld, int ov, int nv) {
        sqld.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTOS );
        sqld.execSQL(sqlCreate);
    }

    public int getProfilesCount() {
        String countQuery = "SELECT * FROM Contactos";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}
