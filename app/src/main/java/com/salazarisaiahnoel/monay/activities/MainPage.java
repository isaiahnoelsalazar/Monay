package com.salazarisaiahnoel.monay.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.salazarisaiahnoel.monay.R;
import com.salazarisaiahnoel.monay.fragments.HomeFragment;
import com.salazarisaiahnoel.monay.fragments.InboxFragment;
import com.salazarisaiahnoel.monay.fragments.ProfileFragment;
import com.salazarisaiahnoel.monay.fragments.TransactionsFragment;

public class MainPage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        BottomNavigationView bnv = findViewById(R.id.bottom_navigation_view);
        bnv.setOnNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.tab_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            return true;
        }
        if (item.getItemId() == R.id.tab_inbox){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new InboxFragment()).commit();
            return true;
        }
        if (item.getItemId() == R.id.tab_transactions){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new TransactionsFragment()).commit();
            return true;
        }
        if (item.getItemId() == R.id.tab_profile){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ProfileFragment()).commit();
            return true;
        }
        return false;
    }
}