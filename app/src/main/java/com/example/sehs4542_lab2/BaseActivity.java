package com.example.sehs4542_lab2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context base) {
        SharedPreferences prefs = base.getSharedPreferences(App.PREF_NAME, Context.MODE_PRIVATE);
        String language = prefs.getString(App.PREF_LANGUAGE, "");
        String region = prefs.getString(App.PREF_REGION, "");

        Locale locale = new Locale(language, region);
        Configuration config = base.getResources().getConfiguration();
        config.setLocale(locale);
        Context newContext = base.createConfigurationContext(config);
        super.attachBaseContext(newContext);
    }
}
