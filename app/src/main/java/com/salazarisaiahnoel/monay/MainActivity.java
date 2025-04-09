package com.salazarisaiahnoel.monay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.salazarisaiahnoel.monay.activities.LoginRegister;
import com.salazarisaiahnoel.monay.activities.MainPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getWindow().setStatusBarColor(getColor(R.color.yellow));
        getWindow().setNavigationBarColor(getColor(R.color.yellow));

        SharedPreferences prefs = getSharedPreferences("monay_sharedpreferences", MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (prefs.getBoolean("isLoggedIn", false)){
                    startActivity(new Intent(MainActivity.this, MainPage.class));
                } else {
                    startActivity(new Intent(MainActivity.this, LoginRegister.class));
                }
            }
        }, 1500);
    }
}