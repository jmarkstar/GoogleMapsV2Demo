package com.jmarkstar.googlemapsv2demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jmarkstar.googlemapsv2demo.R;
import com.jmarkstar.googlemapsv2demo.model.RestaurantModel;
import java.util.ArrayList;

/**
 * @author jmarkstar
 * */
public class MapFragment extends Fragment implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener {

    private static final String TAG = "MapFragment";

    public static final String RESTAURANT_PARAM = "restaurant";
    private static final float ZOOM_NORMAL = 12f;
    private static final float ZOOM_CLICKED_MARKER = 17f;
    private static final int TIME_ZOOM_CLICKED_MARKER = 1300;

    private OnMapFragmentInteractionListener mListener;

    private MapView mMapView;
    private GoogleMap mMap;
    private Marker previousMarker;

    private ArrayList<RestaurantModel> restaurants;
    private ArrayMap<Marker, RestaurantModel> markerRestaurants;

    public MapFragment() {}

    public static MapFragment newInstance(ArrayList<RestaurantModel> restaurants) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(RESTAURANT_PARAM, restaurants);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            restaurants = getArguments().getParcelableArrayList(RESTAURANT_PARAM);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        //focusPeru();
        addMarkerRestaurants();
    }

    @Override public void onMapClick(LatLng latLng) {
        if(previousMarker!=null){
            previousMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));
            previousMarker.hideInfoWindow();
            LatLng markerPosition = previousMarker.getPosition();
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, ZOOM_NORMAL), TIME_ZOOM_CLICKED_MARKER, null);
            previousMarker = null;
        }
    }

    /** Its returning true because i dont want the default behavior of the markers.
     *  https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap.OnMarkerClickListener
     * */
    @Override public boolean onMarkerClick(Marker marker) {
        RestaurantModel restaurant = markerRestaurants.get(marker);
        if(previousMarker == null){
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_selected_marker));
            previousMarker = marker;
            previousMarker.showInfoWindow();
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), ZOOM_CLICKED_MARKER), TIME_ZOOM_CLICKED_MARKER, null);
        } else {
            if(previousMarker.equals(marker)){
                previousMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));
                previousMarker.hideInfoWindow();
                previousMarker = null;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), ZOOM_NORMAL), TIME_ZOOM_CLICKED_MARKER, null);
            }else{
                previousMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));
                marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_selected_marker));
                previousMarker = marker;
                previousMarker.showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), ZOOM_CLICKED_MARKER), TIME_ZOOM_CLICKED_MARKER, null);
            }
        }

        return true;
    }

    /** Focus the complete peruvian territory.
     * */
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

            MarkerOptions markerOptions = new MarkerOptions()
                    .position(latLng)
                    .title(restaurant.getName())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker));
            Marker marker = mMap.addMarker(markerOptions);
            markerRestaurants.put(marker, restaurant);
        }

        LatLngBounds bounds = builder.build();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, 20));
    }

    /** If you need to do something im some especific cases, you could use this interface to transfer
     *  the action to the activity.
     * */
    public interface OnMapFragmentInteractionListener {
        void onMapReady();
        void onMapClick(LatLng latLng);
        void onMarkerClick(RestaurantModel restaurant, Marker marker);
    }
}
