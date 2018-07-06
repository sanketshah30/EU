package com.example.hp.eu.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.eu.R;
import com.example.hp.eu.adapter.HomeAdapter;
import com.example.hp.eu.common.MyUtils;
import com.example.hp.eu.model.ReceiverHomeModel;

import java.util.ArrayList;

public class ReceiverHomeFragment extends Fragment {
    private RecyclerView recyclerview_ads;
    private HomeAdapter adapter;
    ArrayList<ReceiverHomeModel> ads = new ArrayList<>();
    private TextView tv_filter, tv_sort;
    private View ll_sortlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Home");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        tv_filter = view.findViewById(R.id.tv_filter);
        tv_sort=view.findViewById(R.id.tv_sort);
        ll_sortlist= view.findViewById(R.id.ll_sortlist);
        recyclerview_ads = view.findViewById(R.id.recyclerview_ads);
        recyclerview_ads.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeAdapter(getActivity(), ads);
        //adapter.setClickListener(this);
        tv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils filter= new MyUtils();
                filter.filterDialog(getActivity());
            }
        });

        tv_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_sortlist.setVisibility(View.VISIBLE);
                /*Intent goToFilter = new Intent(getActivity().getApplication(), FilterPageActivity.class);
                startActivity(goToFilter);
           */ }
        });

        ll_sortlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_sortlist.setVisibility(View.INVISIBLE);
            }
        });
        recyclerview_ads.setAdapter(adapter);
        return view;

    }
}
