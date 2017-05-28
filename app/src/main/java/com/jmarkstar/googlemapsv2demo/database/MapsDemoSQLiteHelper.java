package com.jmarkstar.googlemapsv2demo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jmarkstar.googlemapsv2demo.model.RestaurantModel;

/**
 * Created by jmarkstar on 27/05/2017.
 */
public class MapsDemoSQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "restaurant.db";
    private static final int DB_VERSION = 1;

    private static final StringBuilder SQL_CREATE_TABLE = new StringBuilder()
            .append("CREATE TABLE IF NOT EXISTS "+ RestaurantModel.TABLE_NAME+" ( ")
            .append(RestaurantModel.ID_FIELD+" INTEGER PRIMARY KEY AUTOINCREMENT, ")
            .append(RestaurantModel.NAME_FIELD+" TEXT, ")
            .append(RestaurantModel.DESCRIPTION_FIELD+" TEXT, ")
            .append(RestaurantModel.LATITUDE_FIELD+" REAL, ")
            .append(RestaurantModel.LONGITUDE_FIELD+" REAL)");

    private static final StringBuilder SQL_DROP_TABLE = new StringBuilder()
            .append("DROP TABLE IF EXISTS "+RestaurantModel.TABLE_NAME);

    public MapsDemoSQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE.toString());

        //Inserting data
        RestaurantModel restaurant1 = new RestaurantModel("Restaurant 1","", -12.0702941, -77.0416958);
        RestaurantModel restaurant2 = new RestaurantModel("Restaurant 2","", -12.0821151,-77.039089);
        RestaurantModel restaurant3 = new RestaurantModel("Restaurant 3","", -12.0925665,-77.0348192);
        RestaurantModel restaurant4 = new RestaurantModel("Restaurant 4","",  -12.0834783,-77.0417898);
        RestaurantModel restaurant5 = new RestaurantModel("Restaurant 5","", -12.1074453,-77.0481677);
        RestaurantModel restaurant6 = new RestaurantModel("Restaurant 6","", -12.0657877,-77.0992737);
        RestaurantModel restaurant7 = new RestaurantModel("Restaurant 7","", -12.0777436,-77.0832981);
        RestaurantModel restaurant8 = new RestaurantModel("Restaurant 8","", -12.1286487,-76.9906587);
        RestaurantModel restaurant9 = new RestaurantModel("Restaurant 9","", -12.0837727,-77.0135127);
        RestaurantModel restaurant10 = new RestaurantModel("Restaurant 10","", -12.0970697,-77.0143937);

        insertData(db, restaurant1);
        insertData(db, restaurant2);
        insertData(db, restaurant3);
        insertData(db, restaurant4);
        insertData(db, restaurant5);
        insertData(db, restaurant6);
        insertData(db, restaurant7);
        insertData(db, restaurant8);
        insertData(db, restaurant9);
        insertData(db, restaurant10);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE.toString());
        onCreate(db);
    }

    private void insertData(SQLiteDatabase db, RestaurantModel restaurant){

        StringBuilder SQL_INSERT = new StringBuilder()
                .append("INSERT INTO "+RestaurantModel.TABLE_NAME)
                .append("("+RestaurantModel.NAME_FIELD+ ","+RestaurantModel.DESCRIPTION_FIELD+","+
                        RestaurantModel.LATITUDE_FIELD+","+RestaurantModel.LONGITUDE_FIELD+") ")
                .append("VALUES( ")
                .append("'"+restaurant.getName()+"', ")
                .append("'"+restaurant.getDescription()+"', ")
                .append(restaurant.getLatitude()+", ")
                .append(restaurant.getLongitude()+")");

        db.execSQL(SQL_INSERT.toString());
    }
}
