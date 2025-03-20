package com.salazarisaiahnoel.monay.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salazarisaiahnoel.monay.MainActivity;
import com.salazarisaiahnoel.monay.R;
import com.salazarisaiahnoel.monay.activities.LoginRegister;

public class ProfileFragment extends Fragment {

    TextView user_full_name, loginlogoutbtn;
    ImageView imageView6;
    SharedPreferences prefs;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getWindow().setStatusBarColor(requireContext().getColor(R.color.yellow));

        prefs = requireActivity().getSharedPreferences("monay_sharedpreferences", Context.MODE_PRIVATE);
        user_full_name = view.findViewById(R.id.user_full_name);
        loginlogoutbtn = view.findViewById(R.id.loginlogoutbtn);
        imageView6 = view.findViewById(R.id.imageView6);
        ConstraintLayout loginbtn = view.findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "asdasdasdasd", Toast.LENGTH_SHORT).show();
                logbutton();
            }
        });
    }

    void logbutton(){
        if (prefs.getBoolean("isLoggedIn", false)){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user_fname", "");
            editor.putString("user_lname", "");
            editor.putString("user_email", "");
            editor.putString("user_password", "");
            editor.putBoolean("isLoggedIn", false);
            editor.apply();
            user_full_name.setText(prefs.getString("user_email", ""));
            loginlogoutbtn.setText("Log in");
            loginlogoutbtn.setTextColor(Color.BLACK);
            imageView6.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.round_login_24));
            requireActivity().finish();
            startActivity(new Intent(requireContext(), LoginRegister.class));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        user_full_name.setText(prefs.getString("user_fname", "") + " " + prefs.getString("user_lname", ""));
        if (prefs.getBoolean("isLoggedIn", false)){
            loginlogoutbtn.setText("Log out");
            loginlogoutbtn.setTextColor(Color.RED);
            imageView6.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.round_logout_24));
        }
    }
}