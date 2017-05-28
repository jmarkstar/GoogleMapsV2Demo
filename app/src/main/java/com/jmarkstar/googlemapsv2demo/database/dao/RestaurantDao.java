package com.jmarkstar.googlemapsv2demo.database.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jmarkstar.googlemapsv2demo.database.MapsDemoSQLiteHelper;
import com.jmarkstar.googlemapsv2demo.model.RestaurantModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmarkstar on 27/05/2017.
 */
public class RestaurantDao {

    private String [] ALL_COLUMNS = { RestaurantModel.ID_FIELD,
            RestaurantModel.NAME_FIELD,
            RestaurantModel.DESCRIPTION_FIELD,
            RestaurantModel.LATITUDE_FIELD,
            RestaurantModel.LONGITUDE_FIELD };

    private SQLiteDatabase mSQLiteDatabase;

    public RestaurantDao(Context context){
        mSQLiteDatabase = new MapsDemoSQLiteHelper(context).getWritableDatabase();
    }

    public long insertRestaurant(RestaurantModel restaurantModel){
        //TODO implement row creating
        return 0;
    }

    public long updateRestaurant(RestaurantModel restaurantModel){
        //TODO implement row updating
        return 0;
    }

    public long deleteRestaurant(Integer id){
        //TODO implement row deleting
        return 0;
    }

    public ArrayList<RestaurantModel> getAll(){
        Cursor mCursor = mSQLiteDatabase.query(true, RestaurantModel.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null, null);
        return convertCursorToList(mCursor);
    }

    private ArrayList<RestaurantModel> convertCursorToList(Cursor mCursor){
        ArrayList<RestaurantModel> restaurant = new ArrayList<>();
        try{
            if(mCursor.moveToFirst()){
                do {
                    RestaurantModel restaurantItem = new RestaurantModel();
                    restaurantItem.setId(mCursor.getInt(mCursor.getColumnIndex(RestaurantModel.ID_FIELD)));
                    restaurantItem.setName(mCursor.getString(mCursor.getColumnIndex(RestaurantModel.NAME_FIELD)));
                    restaurantItem.setDescription(mCursor.getString(mCursor.getColumnIndex(RestaurantModel.DESCRIPTION_FIELD)));
                    restaurantItem.setLatitude(mCursor.getDouble(mCursor.getColumnIndex(RestaurantModel.LATITUDE_FIELD)));
                    restaurantItem.setLongitude(mCursor.getDouble(mCursor.getColumnIndex(RestaurantModel.LONGITUDE_FIELD)));
                    restaurant.add(restaurantItem);
                }while (mCursor.moveToNext());
            }
        }finally {
            if (mCursor != null && !mCursor.isClosed())
                mCursor.close();
        }
        return restaurant;
    }

    public void closeDataBase(){
        mSQLiteDatabase.close();
    }
}
