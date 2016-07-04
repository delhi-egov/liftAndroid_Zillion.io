package com.zillion.delhibelly.liftsManager.Adapters;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zillion.delhibelly.liftsManager.Fragments.Upcoming;
import com.zillion.delhibelly.liftsManager.Helpers.TextViewPlus;
import com.zillion.delhibelly.liftsManager.MainActivity;
import com.zillion.delhibelly.liftsManager.R;

import java.util.HashMap;
import java.util.List;


public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {

    private List<HashMap> data;
    MainActivity main;
    Upcoming fragment;

    public UpcomingAdapter(List<HashMap> data, MainActivity main, Upcoming fragment) {

        this.data = data;
        this.main = main;
        this.fragment = fragment;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(com.zillion.delhibelly.liftsManager.R.layout.upcoming_item, parent, false);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.distance.setText(data.get(position).get("distance").toString());
        viewHolder.address.setText(data.get(position).get("address").toString());
        viewHolder.customer_name.setText(data.get(position).get("customer_name").toString());
        viewHolder.customer_pref.setText(data.get(position).get("customer_preferred_time").toString());
        viewHolder.mobile_no = data.get(position).get("customer_mobile_no").toString();
        viewHolder.call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragment.telephonyCaller(viewHolder);
            }
        });
        viewHolder.reschedule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragment.rescheduleTime(viewHolder);

            }
        });
        viewHolder.more.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fragment.nextPage(viewHolder);

            }

        });

    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView distance, customer_name, address, customer_pref;
        public ImageButton call, reschedule, more;
        public String mobile_no;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            distance = (TextView) itemLayoutView.findViewById(R.id.cust_km_away);
            address = (TextView) itemLayoutView.findViewById(R.id.cust_adr);
            customer_name = (TextView) itemLayoutView.findViewById(R.id.cust_name);
            customer_pref = (TextView) itemLayoutView.findViewById(R.id.cust_pref_time);
            call = (ImageButton) itemLayoutView.findViewById(R.id.upcoming_call_btn);
            reschedule = (ImageButton) itemLayoutView.findViewById(R.id.upcoming_reschd_btn);
            more = (ImageButton) itemLayoutView.findViewById(R.id.upcoming_more_btn);
        }
    }
}