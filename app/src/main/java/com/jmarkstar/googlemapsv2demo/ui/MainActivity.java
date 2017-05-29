package com.jmarkstar.googlemapsv2demo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.jmarkstar.googlemapsv2demo.R;
import com.jmarkstar.googlemapsv2demo.database.dao.RestaurantDao;
import com.jmarkstar.googlemapsv2demo.model.RestaurantModel;
import java.util.ArrayList;

/**
 * @author jmarkstar
 * */
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
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_fragment, MapFragment.newInstance(restaurants)).commit();
    }

    @Override public void onMapReady() {
        //TODO if you need to do something after the map is ready, you could do it here.
    }

    @Override public void onMapClick(LatLng latLng) {
        //TODO if you need to do something when the user click the map, you could do it here.
    }

    @Override public void onMarkerClick(RestaurantModel restaurant, Marker marker) {
        //TODO if you need to do something when the user click a marker, you could do it here.
    }
}
