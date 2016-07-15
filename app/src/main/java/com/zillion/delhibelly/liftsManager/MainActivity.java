package com.zillion.delhibelly.liftsManager;

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
import android.view.Menu;
import android.view.MenuInflater;

import com.crashlytics.android.Crashlytics;
import com.zillion.delhibelly.liftsManager.Fragments.Assigned;
import com.zillion.delhibelly.liftsManager.Fragments.Submitted;
import com.zillion.delhibelly.liftsManager.Fragments.Upcoming;
import com.zillion.delhibelly.liftsManager.Network.Models.ApiError;
import com.zillion.delhibelly.liftsManager.Network.Models.Listing;
import com.zillion.delhibelly.liftsManager.Network.ServiceGeneratorMain;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

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
    public String time="-";
    public List<Listing> upcoming_data = new ArrayList<>();


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


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Assigned(), getString(R.string.fragment_two_all));
        adapter.addFragment(new Upcoming(), getString(R.string.fragment_one_upcoming));
        adapter.addFragment(new Submitted(), getString(R.string.fragment_three_settings));
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
