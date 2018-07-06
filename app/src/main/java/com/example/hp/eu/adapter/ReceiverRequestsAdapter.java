package com.example.hp.eu.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.eu.R;
import com.example.hp.eu.model.ReceiverRequestModel;

import java.util.ArrayList;

public class ReceiverRequestsAdapter extends RecyclerView.Adapter<ReceiverRequestsAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<ReceiverRequestModel> array_Data;

    public ReceiverRequestsAdapter(Activity context, ArrayList<ReceiverRequestModel> array_Data) {
        this.context = context;
        this.array_Data = array_Data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_requests_requestor, parent, false);
        return new ReceiverRequestsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReceiverRequestsAdapter.ViewHolder holder, int position) {
        holder.tv_viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tv_details.getVisibility() == View.VISIBLE)
                    holder.tv_details.setVisibility(View.GONE);

                else
                    holder.tv_details.setVisibility(View.VISIBLE);

            }
        });


    }

    @Override
    public int getItemCount() {
//        return array_Data.size();
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_provider, tv_status, tv_details, tv_viewmore;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_provider = itemView.findViewById(R.id.tv_provider);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_details = itemView.findViewById(R.id.tv_details);
            tv_viewmore = itemView.findViewById(R.id.tv_view_more);

        }
    }

}
