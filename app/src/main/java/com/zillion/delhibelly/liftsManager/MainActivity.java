package com.zillion.delhibelly.liftsManager;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.maps.MapView;
import com.zillion.delhibelly.liftsManager.Adapters.DataAdapter;
import com.zillion.delhibelly.liftsManager.Fragments.Assigned;
import com.zillion.delhibelly.liftsManager.Fragments.Submitted;
import com.zillion.delhibelly.liftsManager.Fragments.Upcoming;
import com.zillion.delhibelly.liftsManager.Network.ErrorUtils;
import com.zillion.delhibelly.liftsManager.Network.Models.ApiError;
import com.zillion.delhibelly.liftsManager.Network.Models.Listing;
import com.zillion.delhibelly.liftsManager.Network.ServiceGeneratorMain;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String email = null;
    public static String password = null;
    public static String token = null;
    public static int id = 0;
    ServiceGeneratorMain.UserClient userClient;
    public List<Listing> listings = new ArrayList<>();
    private ApiError error;
    private Snackbar snackbar;
    private CoordinatorLayout coordinatorLayout;
    public String time = "-";
    public List<Listing> upcoming_data = new ArrayList<>();
    private Assigned assigned;
    private Upcoming upcoming;
    private Submitted submitted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        /*setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);*/

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        email = preferences.getString("email", null);
        password = preferences.getString("password", null);
        token = preferences.getString("token", null);
        id = preferences.getInt("id", 0);

        userClient = ServiceGeneratorMain.createService(ServiceGeneratorMain.UserClient.class);


        // Fixing Later Map loading Delay
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MapView mv = new MapView(getApplicationContext());
                    mv.onCreate(null);
                    mv.onPause();
                    mv.onDestroy();
                } catch (Exception ignored) {
                }
            }
        }).start();

    }

    public void getListing(final int fragment) {
        Call<List<Listing>> call = userClient.getListing(MainActivity.token);
        call.enqueue(new Callback<List<Listing>>() {
            private List<Listing> listings = new ArrayList<>();

            @Override
            public void onResponse(Response<List<Listing>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    for (Listing list : response.body()) {
                        listings.add(list);
                    }
                    setData(fragment,listings);

                } else {
                    error = ErrorUtils.parseError(response, retrofit);
                    Log.d("Error", error.message());
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });

    }

    public void setData(int name,List<Listing> listings){
    switch (name)
    {
        case 0:
            assigned.setListings(listings);
            break;
        case 1:
            upcoming.setListings(listings);
            break;
        case 2:
            submitted.setListings(listings);
            break;
    }
    }


    /*public void filterData(List<Listing> data)
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
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(assigned = new Assigned(), getString(R.string.fragment_two_all));
        adapter.addFragment(upcoming = new Upcoming(), getString(R.string.fragment_one_upcoming));
        adapter.addFragment(submitted = new Submitted(), getString(R.string.fragment_three_settings));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
