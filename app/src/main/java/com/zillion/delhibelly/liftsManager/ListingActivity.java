package com.zillion.delhibelly.liftsManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zillion.delhibelly.liftsManager.Fragments.MapFragment;

public class ListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.zillion.delhibelly.liftsManager.R.layout.listing);

        MapFragment mapFragment = MapFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flMapContainer, mapFragment)
                .commit();
    }



}



