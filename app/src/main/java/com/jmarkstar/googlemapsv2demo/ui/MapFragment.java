package com.jmarkstar.googlemapsv2demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jmarkstar.googlemapsv2demo.R;
import com.jmarkstar.googlemapsv2demo.model.RestaurantModel;
import java.util.ArrayList;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "MapFragment";

    public static final String RESTAURANT_PARAM = "restaurant";

    private OnMapFragmentInteractionListener mListener;

    private MapView mMapView;
    private GoogleMap mMap;

    private ArrayList<RestaurantModel> restaurants;
    private ArrayMap<Marker, RestaurantModel> markerRestaurants;

    public MapFragment() {}

    public static MapFragment newInstance(ArrayList<RestaurantModel> restaurants) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(RESTAURANT_PARAM, restaurants);
        Log.v(TAG, "newInstance restaurants size = "+restaurants.size());
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurants = getArguments().getParcelableArrayList(RESTAURANT_PARAM);
            Log.v(TAG, "onCreate restaurants size = "+restaurants.size());
        }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); //to iniciate immediately.
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(this);
        return rootView;
    }

    @Override public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override public void onAttach(Context context) {
        Log.v(TAG, "onAttach");
        super.onAttach(context);
        if (context instanceof OnMapFragmentInteractionListener) {
            mListener = (OnMapFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        focusPeru();
        addMarkerRestaurants();
    }

    private void focusPeru(){
        LatLng peru = new LatLng(-9.6282167,-75.2398817);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(peru, 5.0f));
    }

    /** Adds markers and zoom to fit all of them.
     * */
    private void addMarkerRestaurants(){
        markerRestaurants = new ArrayMap<>();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(RestaurantModel restaurant : restaurants){
            LatLng latLng = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());
            builder.include(latLng);
            Marker marker = mMap.addMarker( new MarkerOptions().position(latLng).title(restaurant.getName()));
            markerRestaurants.put(marker, restaurant);
        }

        LatLngBounds bounds = builder.build();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, 10));
    }

    public interface OnMapFragmentInteractionListener {

    }
}
