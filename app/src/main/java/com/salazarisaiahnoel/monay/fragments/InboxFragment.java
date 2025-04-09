package com.salazarisaiahnoel.monay.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.saiaaaaaaa.cod.EasySQL;
import com.salazarisaiahnoel.monay.R;
import com.salazarisaiahnoel.monay.activities.InboxDetails;
import com.salazarisaiahnoel.monay.adapters.InboxAdapter;

import java.util.ArrayList;
import java.util.List;

public class InboxFragment extends Fragment implements InboxAdapter.InboxItemOnClick {

    List<String> titles = new ArrayList<>();
    List<String> descriptions = new ArrayList<>();
    List<String> dates = new ArrayList<>();

    public InboxFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        requireActivity().getWindow().setStatusBarColor(requireContext().getColor(R.color.yellow));

        RecyclerView rv = view.findViewById(R.id.inboxrv);
        LinearLayoutManager llm = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(llm);

        EasySQL easySQL = new EasySQL(requireContext());
        SharedPreferences prefs = requireContext().getSharedPreferences("monay_sharedpreferences", MODE_PRIVATE);

        for (String a : easySQL.getTableValues(prefs.getString("user_email", "").replace("@", "").replace(".", "") + "_inbox_db", prefs.getString("user_email", "").replace("@", "").replace(".", "") + "_inbox_table")){
            String[] split = a.split(":");
            if (split[0].equals("inbox_title")){
                titles.add(split[1].substring(1, split[1].length() - 1));
            }
            if (split[0].equals("inbox_content")){
                descriptions.add(split[1].substring(1, split[1].length() - 1));
            }
            if (split[0].equals("date_sent")){
                dates.add(split[1].substring(1, split[1].length() - 1));
            }
        }

        InboxAdapter inboxAdapter = new InboxAdapter(titles, descriptions, dates, this);
        rv.setAdapter(inboxAdapter);
    }

    @Override
    public void click(int position) {
        Intent intent = new Intent(requireContext(), InboxDetails.class);
        intent.putExtra("inbox_title", titles.get(position));
        intent.putExtra("inbox_description", descriptions.get(position));
        intent.putExtra("inbox_date", dates.get(position));
        startActivity(intent);
    }
}