package com.example.hp.eu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.hp.eu.R;
import com.example.hp.eu.activities.EditKidDetailsActivity;
import com.example.hp.eu.adapter.HomeAdapter;
import com.example.hp.eu.model.ReceiverHomeModel;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    private RecyclerView recyclerview_ads;
    private HomeAdapter adapter;
    ArrayList<ReceiverHomeModel> ads = new ArrayList<>();
    private TextView tv_kidDetails;
    private SearchView action_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_update_profile, container, false);

        getActivity().setTitle("Profile");
        tv_kidDetails = view.findViewById(R.id.tv_kidDetails);
        tv_kidDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editKid = new Intent(getActivity(), EditKidDetailsActivity.class);
                getActivity().startActivity(editKid);
            }
        });
        return view;

    }
}
