package com.jmarkstar.googlemapsv2demo.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jmarkstar.googlemapsv2demo.R;
import com.jmarkstar.googlemapsv2demo.database.dao.RestaurantDao;
import com.jmarkstar.googlemapsv2demo.model.RestaurantModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements MapFragment.OnMapFragmentInteractionListener {

    private static final String TAG = "MainActivity";

    private ArrayList<RestaurantModel> restaurants;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getListFromResource();
        openMap();
    }

    public void getListFromResource(){
        RestaurantDao restaurantDao = new RestaurantDao(this);
        restaurants = restaurantDao.getAll();
    }

    private void openMap(){
        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.replace(android.R.id.content, MapFragment.newInstance(restaurants));
        //fragmentTransaction.commit();
        Log.v(TAG, "openMap");
        getSupportFragmentManager().beginTransaction().add(R.id.content_fragment, MapFragment.newInstance(restaurants)).commit();
    }
}
