package com.zillion.delhibelly.liftsManager;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import java.util.Locale;
import com.zillion.delhibelly.liftsManager.Network.ErrorUtils;
import com.zillion.delhibelly.liftsManager.Network.Models.ApiError;
import com.zillion.delhibelly.liftsManager.Network.Models.User;
import com.zillion.delhibelly.liftsManager.Network.ServiceGeneratorMain;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Locale myLocale;
    private String lang;
    private RadioButton eng;
    private RadioButton hi;
    private Button login;
    public static String email = null;
    public static String password = null;
    public static String token = null;
    private EditText loginEmail;
    private EditText loginPassword;
    private ProgressDialog dialog;
    private CoordinatorLayout coordinatorLayout;
    private Snackbar snackbar;
    private ApiError error;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.zillion.delhibelly.liftsManager.R.layout.login);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);


        eng = (RadioButton) findViewById(R.id.eng_radio);
        hi = (RadioButton) findViewById(R.id.hi_radio);
        login = (Button) findViewById(R.id.login_btn);
        eng.setOnClickListener(this);
        hi.setOnClickListener(this);
        login.setOnClickListener(this);

        loginEmail = (EditText) findViewById(R.id.login_username);
        loginPassword = (EditText) findViewById(R.id.login_pwd);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
        email = preferences.getString("email",null);
        password = preferences.getString("password",null);
        loginEmail.setText(email);
        loginPassword.setText(password);

    }

    public void setCredentials(String email, String password, String token,int id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.putString("email", email);
        editor.putString("password", password);
        System.out.println("token "+token);
        editor.putInt("id", id);
        editor.apply();
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    public void login() {
        final String username = this.loginEmail.getText().toString();
        final String password = this.loginPassword.getText().toString();
        final String login_token = "login_token";
        if (username.isEmpty() || password.isEmpty()) {
            snackbar = Snackbar
                    .make(coordinatorLayout, "Please Fill Assigned Details", Snackbar.LENGTH_SHORT);
            snackbar.show();
        } else {
            dialog = new ProgressDialog(Login.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading...");
            dialog.show();
            final ServiceGeneratorMain.UserClient userClient = ServiceGeneratorMain.createService(ServiceGeneratorMain.UserClient.class);
            Call<User> call = userClient.getToken(login_token,username, password);
            call.enqueue(new Callback<User>() {

                @Override
                public void onResponse(Response<User> response, Retrofit retrofit) throws NullPointerException {
                    if (response.isSuccess()) {
                        setCredentials(username, password, response.body().getToken(),response.body().getInspector().getId());
                        dialog.dismiss();
                    } else {
                        error = ErrorUtils.parseError(response,retrofit);
                        snackbar = Snackbar
                                .make(coordinatorLayout, error.message(), Snackbar.LENGTH_SHORT);
                        snackbar.show();
                        dialog.dismiss();
                    }

                }

                @Override
                public void onFailure(Throwable t) {
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Error", Snackbar.LENGTH_SHORT);
                    dialog.dismiss();
                }
            });
        }
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.hi_radio:
                setHindi();
                saveAndRefresh();
                break;
            case R.id.eng_radio:
                setEnglish();
                saveAndRefresh();
                break;
            case R.id.login_btn:
                login();
                break;
        }

    }
}