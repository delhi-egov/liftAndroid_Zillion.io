package com.zillion.delhibelly.liftsManager.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zillion.delhibelly.liftsManager.R;

import java.util.HashMap;
import java.util.List;


public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {

    private List<HashMap> data;

    public AllAdapter(List<HashMap> data) {

        this.data = data;
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
        viewHolder.order.setText(data.get(position).get("order").toString());
        viewHolder.category.setText(data.get(position).get("category").toString());
        viewHolder.address.setText(data.get(position).get("address").toString());
        //viewHolder.items_requested.setText(data.get(position).get("items").toString() + " Item requested");
    }

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView order, category, address;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            order = (TextView) itemLayoutView.findViewById(com.zillion.delhibelly.liftsManager.R.id.order_id_list);
            category = (TextView) itemLayoutView.findViewById(com.zillion.delhibelly.liftsManager.R.id.category_list);
            address = (TextView) itemLayoutView.findViewById(com.zillion.delhibelly.liftsManager.R.id.address_list);
        }
    }
}