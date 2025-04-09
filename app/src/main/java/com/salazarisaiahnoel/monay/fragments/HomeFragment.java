package com.salazarisaiahnoel.monay.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.saiaaaaaaa.cod.Convert;
import com.github.saiaaaaaaa.cod.EasySQL;
import com.salazarisaiahnoel.monay.R;

public class HomeFragment extends Fragment {

    float money = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getWindow().setStatusBarColor(requireContext().getColor(R.color.pale_yellow));

        TextView available_balance = view.findViewById(R.id.available_balance);

        EasySQL easySQL = new EasySQL(requireContext());
        SharedPreferences prefs = requireContext().getSharedPreferences("monay_sharedpreferences", MODE_PRIVATE);
        boolean captured = false;

        for (String a : easySQL.getTableValues("user_details_db", "user_details_table")){
            String[] split = a.split(":");
            if (split[0].equals("user_email")){
                if (split[1].substring(1, split[1].length() - 1).equals(prefs.getString("user_email", ""))){
                    captured = true;
                }
            }
            if (split[0].equals("money") && captured){
                available_balance.setText(split[1]);
            }
        }
    }
}