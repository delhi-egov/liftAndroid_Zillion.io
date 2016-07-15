package com.zillion.delhibelly.liftsManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zillion.delhibelly.liftsManager.Fragments.FormFragment;
import com.zillion.delhibelly.liftsManager.Fragments.MapFragment;
import com.zillion.delhibelly.liftsManager.Network.ErrorUtils;
import com.zillion.delhibelly.liftsManager.Network.Models.ApiError;
import com.zillion.delhibelly.liftsManager.Network.Models.Listing;
import com.zillion.delhibelly.liftsManager.Network.ServiceGeneratorMain;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ListingActivity extends AppCompatActivity {

    Button button;
    int position = 0;
    private List<Listing> item_data;
    public ProgressDialog dialog;
    public ServiceGeneratorMain.UserClient userClient;
    public ApiError error;
    private List<Listing> listings = new ArrayList<>();
    public String address = "Cannaught Place";
    public int assignId;
    private TextView firm_name, firm_address, lift_type, lift_speed, lift_capacity, lift_total_weight, lift_top_clearance, lift_bottom_clearance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.zillion.delhibelly.liftsManager.R.layout.listing);
        userClient = ServiceGeneratorMain.createService(ServiceGeneratorMain.UserClient.class);

        Toolbar bar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        bar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha );
        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        final MapFragment mapFragment = MapFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flMapContainer, mapFragment)
                .commit();

        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormFragment fragment = FormFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.listingView,fragment)
                        .remove(mapFragment)
                        .commit();
            }
        });
        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt("position");

//        firm_name = (TextView) findViewById(R.id.firmName);
//        firm_address = (TextView) findViewById(R.id.firmAddress);
        lift_type = (TextView) findViewById(R.id.liftType);
        lift_speed = (TextView) findViewById(R.id.maxSpeed);
        lift_capacity = (TextView) findViewById(R.id.capacity_weight);
        lift_total_weight = (TextView) findViewById(R.id.totalWeight);
        lift_top_clearance = (TextView) findViewById(R.id.topClearance);
        lift_bottom_clearance = (TextView) findViewById(R.id.bottomClearance);

        getListing();
    }


    public void setData() {
//        firm_name.setText(listings.get(position).getAssocForm().getFirmName());
//        firm_address.setText(listings.get(position).getAssocForm().getFirmAddress());
        assignId = listings.get(position).getId();
        address = listings.get(position).getAssocForm().getApplicantAddress();
        lift_type.setText(listings.get(position).getAssocForm().getLiftType());
        lift_speed.setText(listings.get(position).getAssocForm().getLiftSpeedMax());
        lift_capacity.setText(listings.get(position).getAssocForm().getLiftCapacityWeight().toString());
        lift_total_weight.setText(listings.get(position).getAssocForm().getLiftTotalWeight().toString());
        lift_top_clearance.setText(listings.get(position).getAssocForm().getTopClearance().toString());
        lift_bottom_clearance.setText(listings.get(position).getAssocForm().getBottomClearance().toString());
    }


    public void getListing() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading...");
        dialog.show();
        Call<List<Listing>> call = userClient.getListing(MainActivity.token);
        call.enqueue(new Callback<List<Listing>>() {

            @Override
            public void onResponse(Response<List<Listing>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    for (Listing list : response.body()) {
                        listings.add(list);
                    }
                    setData();
                    dialog.dismiss();
                } else {
                    error = ErrorUtils.parseError(response, retrofit);
                    Log.d("Error", error.message());
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
            }
        });

    }


}



