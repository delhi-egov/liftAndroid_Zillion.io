package com.zillion.delhibelly.liftsManager.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zillion.delhibelly.liftsManager.Adapters.AllAdapter;
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


public class All extends Fragment {

    private RecyclerView recyclerView;
    private HashMap map;
    private SwipeRefreshLayout swipeContainer;
    private List<HashMap> data = new ArrayList<>();
    private MainActivity main;
    public List<Listing> listings = new ArrayList<>();
    ServiceGeneratorMain.UserClient userClient;
    private ApiError error;
    private Snackbar snackbar;
    private CoordinatorLayout coordinatorLayout;


    public All() {
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
        View rootView = inflater.inflate(R.layout.all_fragment, container, false);
        // Inflate the layout for this fragment

        // 1. get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        coordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id
                .coordinatorLayout);

        main = (MainActivity) getActivity();

        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListing();
            }
        });
        swipeContainer.setColorSchemeResources(R.color.colorPrimary);

        // this is data fro recycler view
        // 3. create an adapter
        //   preparedata();

        // 4. set adapter
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        userClient = ServiceGeneratorMain.createService(ServiceGeneratorMain.UserClient.class);
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
            //  setEmptyAdapter();
        }
    }

    public void getListing() {
        swipeContainer.setRefreshing(true);
        Call<List<Listing>> call = userClient.getListing(MainActivity.token, MainActivity.id);
        call.enqueue(new Callback<List<Listing>>() {

            @Override
            public void onResponse(Response<List<Listing>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    for (Listing list : response.body()) {
                        listings.add(list);
                    }
                    AllAdapter adapter = new AllAdapter(listings, main);
                    recyclerView.setAdapter(adapter);
                    swipeContainer.setRefreshing(false);
                } else {
                    swipeContainer.setRefreshing(false);
                    error = ErrorUtils.parseError(response, retrofit);
                    snackbar = Snackbar
                            .make(coordinatorLayout, error.message(), Snackbar.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                swipeContainer.setRefreshing(false);
                Log.d("Error", t.getMessage());
            }
        });

    }
}
