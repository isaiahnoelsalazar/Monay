package com.salazarisaiahnoel.monay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.salazarisaiahnoel.monay.activities.LoginRegister;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getWindow().setStatusBarColor(getColor(R.color.yellow));
        getWindow().setNavigationBarColor(getColor(R.color.yellow));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LoginRegister.class));
            }
        }, 1500);
    }
}