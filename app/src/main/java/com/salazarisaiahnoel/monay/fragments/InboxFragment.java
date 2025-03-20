package com.salazarisaiahnoel.monay.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salazarisaiahnoel.monay.R;
import com.salazarisaiahnoel.monay.adapters.InboxAdapter;

import java.util.ArrayList;
import java.util.List;

public class InboxFragment extends Fragment {

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

        List<String> titles = new ArrayList<>();
        List<String> descriptions = new ArrayList<>();

        titles.add("Notice");
        titles.add("Hi!");

        descriptions.add("Secure your account! Set a custom PIN in Profile > Settings.");
        descriptions.add("Welcome to Monay! We are pleased to have you in our platform. Enjoy first-timer benefits by adding your email address in Profile > Settings");

        InboxAdapter inboxAdapter = new InboxAdapter(titles, descriptions);
        rv.setAdapter(inboxAdapter);
    }
}