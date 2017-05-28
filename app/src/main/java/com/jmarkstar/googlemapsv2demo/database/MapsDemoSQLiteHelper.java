package com.jmarkstar.googlemapsv2demo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jmarkstar on 27/05/2017.
 */
public class MapsDemoSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "restaurant.db";
    private static final int DB_VERSION = 1;



    public MapsDemoSQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {

    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
