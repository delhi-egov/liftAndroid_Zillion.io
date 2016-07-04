package com.zillion.delhibelly.liftsManager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zillion.delhibelly.liftsManager.Adapters.SettingsAdapter;
import com.zillion.delhibelly.liftsManager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Settings extends Fragment {

    private RecyclerView recyclerView;
    private HashMap map;

    private List<HashMap> data = new ArrayList<>();

    public Settings() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.settings_fragment, container, false);
        // Inflate the layout for this fragment

        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // this is data fro recycler view
        // 3. create an adapter
        preparedata();

        SettingsAdapter mAdapter = new SettingsAdapter(data);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    private void preparedata() {
        map = new HashMap();
        map.put("order", 101);
        map.put("category", "furniture");
        map.put("bids", "10");
        map.put("highest", "₹20000");
        data.add(map);
    }

}
