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
import android.widget.ImageButton;
import android.widget.TextView;

import com.zillion.delhibelly.liftsManager.Adapters.DataAdapter;
import com.zillion.delhibelly.liftsManager.Adapters.SettingsAdapter;
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

public class Submitted extends Fragment {
    MainActivity main;
    private RecyclerView recyclerView;
    private HashMap<String, String> map;
    private List<HashMap> data;
    private String number;
    private SwipeRefreshLayout swipeContainer;
    private ProgressDialog dialog;
    private ServiceGeneratorMain.UserClient userClient;
    private ApiError error;
    ImageButton reschd;
    TextView reschd_title;

    public Submitted() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Send context to adapter
        main = (MainActivity) getActivity();

        userClient = ServiceGeneratorMain.createService(ServiceGeneratorMain.UserClient.class);

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
                setEmptyAdapter();
                main.getListing(2);
              //  showData();
                swipeContainer.setRefreshing(false);
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
            main.getListing(2);
           // showData();
        }
        main.getListing(2);
     //   showData();
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

    public final void setListings(List<Listing> listings){
        DataAdapter adapter = new DataAdapter(filterData(listings), main);
        recyclerView.setAdapter(adapter);
    }

    public void showData(){
        DataAdapter adapter = new DataAdapter(filterData(main.listings), main);
        recyclerView.setAdapter(adapter);
    }

    /*public void getListing() {
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

    }*/

    public List<Listing> filterData(List<Listing> data)
    {
        List<Listing> listings = new ArrayList<>();

        String given_response = "completed";
        String given_response2 = "finished";
        for(Listing list: data)
        {
            String response = list.getStatus();
            if (response.equals(given_response) || response.equals(given_response2))
            {
                listings.add(list);
                DataAdapter adapter = new DataAdapter(listings, main);
                recyclerView.setAdapter(adapter);
            }
        }
        return listings;
    }


    public void setEmptyAdapter() {
        List<Listing> listings = new ArrayList<>();
        DataAdapter adapter = new DataAdapter(listings, main);
        recyclerView.setAdapter(adapter);
    }
}
