package com.jmarkstar.googlemapsv2demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jmarkstar.googlemapsv2demo.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private OnMapFragmentInteractionListener mListener;

    private MapView mMapView;
    private GoogleMap mMap;

    public MapFragment() {}

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    public interface OnMapFragmentInteractionListener {

    }
}
