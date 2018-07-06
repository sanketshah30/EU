package com.example.hp.eu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.eu.R;
import com.example.hp.eu.activities.ReceiverProfileActivity;
import com.example.hp.eu.model.ProviderRequestModel;

import java.util.ArrayList;

public class ProviderRequestsAdapter extends RecyclerView.Adapter<ProviderRequestsAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<ProviderRequestModel> array_Data;

    public ProviderRequestsAdapter(Activity context, ArrayList<ProviderRequestModel> array_Data) {
        this.context = context;
        this.array_Data = array_Data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_requests_provider, parent, false);
        return new ProviderRequestsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProviderRequestsAdapter.ViewHolder holder, int position) {
        /*holder.tv_viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tv_details.getVisibility() == View.VISIBLE)
                    holder.tv_details.setVisibility(View.GONE);

                else
                    holder.tv_details.setVisibility(View.VISIBLE);

            }
        });*/

        final Context context= this.context;
        holder.tv_receiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent receiverprofile = new Intent(context, ReceiverProfileActivity.class);
                context.startActivity(receiverprofile);
            }
        });
    }

    @Override
    public int getItemCount() {
//        return array_Data.size();
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_receiver, tv_status, tv_details, tv_viewmore;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_receiver = itemView.findViewById(R.id.tv_name_receiver);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_details = itemView.findViewById(R.id.tv_details);/*
            tv_viewmore = itemView.findViewById(R.id.tv_view_more);*/

        }
    }

}
