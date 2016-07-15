package com.zillion.delhibelly.liftsManager.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zillion.delhibelly.liftsManager.Adapters.DataAdapter;
import com.zillion.delhibelly.liftsManager.MainActivity;
import com.zillion.delhibelly.liftsManager.Network.ErrorUtils;
import com.zillion.delhibelly.liftsManager.Network.Models.ApiError;
import com.zillion.delhibelly.liftsManager.Network.Models.Listing;
import com.zillion.delhibelly.liftsManager.Network.ServiceGeneratorMain;
import com.zillion.delhibelly.liftsManager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class Assigned extends Fragment {

    MainActivity main;
    private RecyclerView recyclerView;
    private HashMap<String, String> map;
    private List<HashMap> data;
    private String number;
    private SwipeRefreshLayout swipeContainer;
    private ServiceGeneratorMain.UserClient userClient;
    private ApiError error;
    private ProgressDialog dialog;



    public Assigned() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        userClient = ServiceGeneratorMain.createService(ServiceGeneratorMain.UserClient.class);
        // Send context to adapter
        main = (MainActivity) getActivity();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.upcoming_fragment, container, false);

        // 1. get a reference to recyclerView
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // 4. Swipe container view
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListing();
            }
        });
        swipeContainer.setColorSchemeResources(R.color.colorPrimary);

        return rootView;
    }

    boolean fragmentAlreadyLoaded = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null && !fragmentAlreadyLoaded) {
            fragmentAlreadyLoaded = true;
            getListing();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Set the adapter with retrieved information
        View view = getView();
        if (view != null) {
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(llm);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(20);

            setEmptyAdapter();

        }
    }

    public void getListing() {
        swipeContainer.setRefreshing(true);
        dialog = new ProgressDialog(main);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading...");
        dialog.show();
        Call<List<Listing>> call = userClient.getListing(MainActivity.token);
        call.enqueue(new Callback<List<Listing>>() {
            private List<Listing> listings = new ArrayList<>();

            @Override
            public void onResponse(Response<List<Listing>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    for (Listing list : response.body()) {
                        listings.add(list);
                    }
                    filterData(listings);
                    dialog.dismiss();
                    swipeContainer.setRefreshing(false);
//                    DataAdapter adapter = new DataAdapter(listings, main);
//                    recyclerView.setAdapter(adapter);

                } else {
                    error = ErrorUtils.parseError(response, retrofit);
                    Log.d("Error", error.message());
                    swipeContainer.setRefreshing(false);
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                swipeContainer.setRefreshing(false);
            }
        });

    }

    public void filterData(List<Listing> data)
    {
        List<Listing> listings = new ArrayList<>();

        String given_response = "Not Scheduled";
        String not_status = "pending";
        for(Listing list: data)
        {
            String response = list.getScheduledDate();
            String status = list.getStatus();
            if (response.equals(given_response) && status.equals(not_status))
            {
                listings.add(list);
                DataAdapter adapter = new DataAdapter(listings, main);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        }
    }


    public void setEmptyAdapter() {
        List<Listing> listings = new ArrayList<>();
        DataAdapter adapter = new DataAdapter(listings, main);
        recyclerView.setAdapter(adapter);
    }

}
