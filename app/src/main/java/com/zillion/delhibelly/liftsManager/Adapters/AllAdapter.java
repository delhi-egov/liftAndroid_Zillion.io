package com.zillion.delhibelly.liftsManager.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zillion.delhibelly.liftsManager.MainActivity;
import com.zillion.delhibelly.liftsManager.Network.Models.Listing;
import com.zillion.delhibelly.liftsManager.R;

import java.util.HashMap;
import java.util.List;


public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {

    private List<Listing> data;
    private MainActivity main;

    public AllAdapter(List<Listing> data, MainActivity main) {
        this.data = data;
        this.main = main;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_item, parent, false);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.name.setText(data.get(position).getAssocForm().getApplicantName());
        viewHolder.address.setText(data.get(position).getAssocForm().getApplicantAddress());
        viewHolder.order.setText(data.get(position).getAssocForm().getFirmName());
        viewHolder.category.setText(data.get(position).getAssocForm().getContactPersonName());
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,order, category, address;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView.findViewById(R.id.cust_id_list);
            order = (TextView) itemLayoutView.findViewById(R.id.order_id_list);
            category = (TextView) itemLayoutView.findViewById(R.id.category_list);
            address = (TextView) itemLayoutView.findViewById(R.id.address_list);
        }
    }
}