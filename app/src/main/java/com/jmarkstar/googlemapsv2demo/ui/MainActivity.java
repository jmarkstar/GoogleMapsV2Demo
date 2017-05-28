package com.jmarkstar.googlemapsv2demo.ui;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jmarkstar.googlemapsv2demo.R;

public class MainActivity extends AppCompatActivity
        implements MapFragment.OnMapFragmentInteractionListener {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, MapFragment.newInstance());
        fragmentTransaction.commit();
    }
}
