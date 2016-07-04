package com.zillion.delhibelly.liftsManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    private String lang;
    private Locale myLocale;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);


        Thread timerThread = new Thread() {
            public void run() {
                try {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
                    lang = preferences.getString("locale", null);
                    if(lang!=null && lang.equals("hi")) {
                        setLocale("hi");
                    }
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent1 = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent1);
                }
            }
        };
        timerThread.start();
    }

    public void setLocale(String lang) {
        this.lang = lang;
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
