package com.zillion.delhibelly.liftsManager.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zillion.delhibelly.liftsManager.Adapters.UpcomingAdapter;
import com.zillion.delhibelly.liftsManager.ListingActivity;
import com.zillion.delhibelly.liftsManager.MainActivity;
import com.zillion.delhibelly.liftsManager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Upcoming extends Fragment {

    MainActivity main;
    private RecyclerView recyclerView;
    private HashMap<String, String> map;
    private List<HashMap> data;
    private String number;
    private SwipeRefreshLayout swipeContainer;

    public Upcoming() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        preparedata();

        // Send context to adapter
        main = (MainActivity) getActivity();
        UpcomingAdapter mAdapter = new UpcomingAdapter(data, main, this);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.upcoming_fragment, container, false);

        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 4. Swipe container view
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeContainer.setRefreshing(false);
            }
        });
        swipeContainer.setColorSchemeResources(R.color.colorPrimary);

        // 3. this is data for recycler view
        preparedata();

        // 5. set adapter
        recyclerView.setAdapter(mAdapter);

        // 6. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    public void nextPage(UpcomingAdapter.ViewHolder holder) {
        Intent intent = new Intent(getActivity(), ListingActivity.class);
        startActivity(intent);
    }

    public void telephonyCaller(UpcomingAdapter.ViewHolder holder) {

        final Intent callIntent = new Intent(Intent.ACTION_CALL);
        number = holder.mobile_no;

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int choice) {
                switch (choice) {
                    case DialogInterface.BUTTON_POSITIVE:

                        callIntent.setData(Uri.parse("tel:+91" + number));
                        try {
                            startActivity(callIntent);
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), R.string.permission_alert, Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.dialog);
        builder.setTitle(main.getString(R.string.alert_call_title))
                .setIcon(R.drawable.ic_action_call)
                .setMessage(main.getString(R.string.alert_call_msg1) + " " + number + main.getString(R.string.alert_call_msg2))
                .setPositiveButton(main.getString(R.string.alert_call_yes), dialogClickListener)
                .setNegativeButton(main.getString(R.string.alert_call_no), dialogClickListener).show();


    }

    public void rescheduleTime(UpcomingAdapter.ViewHolder holder) {
        TimePickerFragment newFragment = new TimePickerFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(newFragment, "TimePicker");
        ft.commit();
        TextView time = (TextView) getActivity().findViewById(R.id.cust_pref_time);

    }

    private void preparedata() {
        data = new ArrayList();
        map = new HashMap<String, String>();
        map.put("distance", "10 km away");
        map.put("address", "Sector 18");
        map.put("customer_name", "Dinesh Sharma");
        map.put("customer_preferred_time", "8AM");
        map.put("customer_mobile_no", "8483892912");
        data.add(0, map);
        map = new HashMap<String, String>();
        map.put("distance", "20 km away");
        map.put("address", "Sector 22");
        map.put("customer_name", "Hariom Vashisht");
        map.put("customer_preferred_time", "6PM");
        map.put("customer_mobile_no", "9012389123");
        data.add(1, map);
        map = new HashMap<String, String>();
        map.put("distance", "13 km away");
        map.put("address", "Palam Vihar");
        map.put("customer_name", "Jafar Naqvi");
        map.put("customer_preferred_time", "12PM");
        map.put("customer_mobile_no", "84234234123");
        data.add(2, map);
        map = new HashMap<String, String>();
        map.put("distance", "11 km away");
        map.put("address", "Railway Road");
        map.put("customer_name", "Sanchit Nigam");
        map.put("customer_preferred_time", "1PM");
        map.put("customer_mobile_no", "8483892912");
        data.add(3, map);
        map = new HashMap<String, String>();
        map.put("distance", "33 km away");
        map.put("address", "GK Vihar");
        map.put("customer_name", "Sumit Hada");
        map.put("customer_preferred_time", "6PM");
        map.put("customer_mobile_no", "9823423489");
        data.add(4, map);
        map = new HashMap<String, String>();
        map.put("distance", "4 km away");
        map.put("address", "Purana Qila");
        map.put("customer_name", "Javed Tak");
        map.put("customer_preferred_time", "10AM");
        map.put("customer_mobile_no", "8483892912");
        data.add(5, map);
    }
}
