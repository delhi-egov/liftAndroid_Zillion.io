package com.zillion.delhibelly.liftsManager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private Context cT;


    public Login(Context cT) {
        this.cT = cT;
    }

    public Login() {
    }

    CountDownTimer countDownTimer;
    private EditText phoneNo;
    private EditText enterOtp;
    private TextView storeOtp;
    private TextView viewOtp;
    private TextView viewTimer;
    private LinearLayout enterNumberParent;
    private LinearLayout enterOtpParent;
    private Button sendOtp;
    private Button verifyOtp;
    private String phoneNumber;
    private Handler handler;
    private Runnable runnable;
    private int min = 111111;
    private int max = 999999;
    private int wrongattempts = 1;
    private Random r;
    private RadioButton eng_radio;
    private RadioButton hi_radio;
    private Locale myLocale;
    private String lang;

    public final static boolean isValidPhoneNumber(CharSequence target) {
        Matcher mOthers = null;
        mOthers = Pattern.compile("(^[6789][0-9]{9}$)", Pattern.CASE_INSENSITIVE).matcher(target);
        if (mOthers.find()) {
            return true;
        } else {
            return false;
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.zillion.delhibelly.liftsManager.R.layout.login);
        enterNumberParent = (LinearLayout) findViewById(com.zillion.delhibelly.liftsManager.R.id.enterNumberParent);
        enterOtpParent = (LinearLayout) findViewById(com.zillion.delhibelly.liftsManager.R.id.enterOtpParent);

        phoneNo = (EditText) findViewById(com.zillion.delhibelly.liftsManager.R.id.phone_number);
        enterOtp = (EditText) findViewById(com.zillion.delhibelly.liftsManager.R.id.enterOtp);
        sendOtp = (Button) findViewById(com.zillion.delhibelly.liftsManager.R.id.sendOtp);

        storeOtp = (TextView) findViewById(com.zillion.delhibelly.liftsManager.R.id.store_otp);
        viewOtp = (TextView) findViewById(com.zillion.delhibelly.liftsManager.R.id.viewOtp);
        viewTimer = (TextView) findViewById(com.zillion.delhibelly.liftsManager.R.id.viewTimer);
        verifyOtp = (Button) findViewById(com.zillion.delhibelly.liftsManager.R.id.verifyOtp);

        eng_radio = (RadioButton) findViewById(R.id.eng_radio);
        hi_radio = (RadioButton) findViewById(R.id.hi_radio);

        handler = new Handler();

//        runnable = new Runnable() {
//            @Override
//            public void run() {
//                int temp = generateOtp();
//                viewOtp.setText(Integer.toString(temp));
//                storeOtp.setTag(Integer.toString(temp));
//                handler.postDelayed(this, 10000);
//            }
//        };

        storeOtp = (TextView) findViewById(com.zillion.delhibelly.liftsManager.R.id.store_otp);
        View.OnClickListener mHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case com.zillion.delhibelly.liftsManager.R.id.sendOtp: {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    }
                        /*if (!isValidPhoneNumber(phoneNo.getText().toString())) {
                            Toast.makeText(Login.this, "Incorrect Number", Toast.LENGTH_SHORT).show();
                        } else {
                            phoneNumber = phoneNo.getText().toString();
                            wrongattempts = 1;
                            //runnable.run();
                            generateOtp();


                            sendOtp.setVisibility(View.GONE);
                            enterNumberParent.setVisibility(View.GONE);

                            viewOtp.setVisibility(View.VISIBLE);
                            viewTimer.setVisibility(View.VISIBLE);

                            enterOtpParent.setVisibility(View.VISIBLE);
                            verifyOtp.setVisibility(View.VISIBLE);
                            countDownTimer.start();
                            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(sendOtp.getWindowToken(), 0);
                        }*/
                    break;

                    case com.zillion.delhibelly.liftsManager.R.id.verifyOtp:

                        if (enterOtp.getText().toString().equals((String) storeOtp.getTag())) {
                            viewOtp.setText("Successful");
                            enterOtp.setText("");
                            countDownTimer.cancel();
                            generateOtp();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            if (wrongattempts == 3) {
                                reset();
                            } else {
                                Toast.makeText(Login.this, "Attempt : " + ++wrongattempts, Toast.LENGTH_SHORT).show();

                            }
                        }


                        //Toast.makeText(Login.this,storeOtp.getTag().toString(),Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.hi_radio:
                        setHindi();
                        saveAndRefresh();
                        break;
                    case R.id.eng_radio:
                        setEnglish();
                        saveAndRefresh();
                        break;
                }

            }
        };

        countDownTimer = new CountDownTimer(130000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long tempTime = millisUntilFinished / 1000;
                if (tempTime >= 60) {
                    if (tempTime % 60 > 0) {
                        if (tempTime % 60 < 10) {
                            viewTimer.setText("Expires In : 0" + tempTime / 60 + ":0" + tempTime % 60);
                        } else {
                            viewTimer.setText("Expires In : 0" + tempTime / 60 + ":" + tempTime % 60);
                        }

                    } else {
                        viewTimer.setText("Expires In : 0" + ((tempTime / 60) - 1) + ":59");
                    }
                } else {
                    if (tempTime % 60 < 10) {
                        viewTimer.setText("Expires In : 00:0" + tempTime);
                    } else {
                        viewTimer.setText("Expires In : 00:" + tempTime);
                    }
                }

            }

            @Override
            public void onFinish() {
                reset();
                Toast.makeText(Login.this, "OTP EXPIRED", Toast.LENGTH_SHORT).show();
            }
        };

        sendOtp.setOnClickListener(mHandler);
        verifyOtp.setOnClickListener(mHandler);
        eng_radio.setOnClickListener(mHandler);
        hi_radio.setOnClickListener(mHandler);

    }

    public void generateOtp() {
        r = new Random();
        int temp = r.nextInt(max - min + 1) + min;
        viewOtp.setText(Integer.toString(temp));
        storeOtp.setTag(Integer.toString(temp));
    }

    public void reset() {
        viewOtp.setVisibility(View.GONE);
        viewTimer.setVisibility(View.GONE);

        enterOtpParent.setVisibility(View.GONE);
        verifyOtp.setVisibility(View.GONE);
        enterOtp.setText("");
        enterNumberParent.setVisibility(View.VISIBLE);
        sendOtp.setVisibility(View.VISIBLE);
        wrongattempts = 1;
        generateOtp();
        countDownTimer.cancel();
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
        public void saveAndRefresh() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("locale", lang);
            editor.apply();
            Intent refresh = new Intent(this, Login.class);
            startActivity(refresh);
            finish();
        }

    public void setHindi() {
        setLocale("hi");
        getPackageManager().setComponentEnabledSetting(
                new ComponentName("com.zillion.delhibelly.liftsManager", "com.zillion.delhibelly.liftsManager.SplashScreen_New"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        getPackageManager().setComponentEnabledSetting(
                new ComponentName("com.zillion.delhibelly.liftsManager", "com.zillion.delhibelly.liftsManager.SplashScreen_Old"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }

    public void setEnglish() {
        setLocale("eng");
        getPackageManager().setComponentEnabledSetting(
                new ComponentName("com.zillion.delhibelly.liftsManager", "com.zillion.delhibelly.liftsManager.SplashScreen_Old"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        getPackageManager().setComponentEnabledSetting(
                new ComponentName("com.zillion.delhibelly.liftsManager", "com.zillion.delhibelly.liftsManager.SplashScreen_New"),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
}