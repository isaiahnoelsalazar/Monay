package com.salazarisaiahnoel.monay.activities;

import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;

import com.salazarisaiahnoel.monay.R;
import com.salazarisaiahnoel.monay.fragments.LoginFragment;
import com.salazarisaiahnoel.monay.fragments.RegisterFragment;

public class LoginRegister extends AppCompatActivity {

    public static RegisterFragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new LoginFragment()).commit();

        registerFragment = new RegisterFragment();
        registerFragment.setEnterTransition(new Slide(Gravity.BOTTOM));
        registerFragment.setExitTransition(new Slide(Gravity.BOTTOM));
    }

    @Override
    public void onBackPressed() {
        if (!(getSupportFragmentManager().findFragmentById(R.id.frame_layout) instanceof LoginFragment)){
            getSupportFragmentManager().beginTransaction().remove(registerFragment).commit();
        } else {
            super.onBackPressed();
        }
    }
}