package com.example.hp.eu.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.eu.R;
import com.example.hp.eu.model.RequestorProfileModel;

import java.util.ArrayList;

public class RequestorProfileAdapter extends RecyclerView.Adapter<RequestorProfileAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<RequestorProfileModel> array_Data;

    public RequestorProfileAdapter(Activity context, ArrayList<RequestorProfileModel> array_Data) {
        this.context = context;
        this.array_Data = array_Data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_profile_providerview, parent, false);
        return new RequestorProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RequestorProfileAdapter.ViewHolder holder, int position) {
        holder.tv_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Opening Document", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
//        return array_Data.size();
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name, tv_school, tv_board, tv_standard, tv_fees, tv_document;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_receiver_kidname);
            tv_school = itemView.findViewById(R.id.tv_receiver_kidschool);
            tv_board = itemView.findViewById(R.id.tv_receiver_kidboard);
            tv_standard = itemView.findViewById(R.id.tv_receiver_kidstandard);
            tv_fees = itemView.findViewById(R.id.tv_receiver_schoolfees);
            tv_document = itemView.findViewById(R.id.tv_receiver_view_document);

        }
    }

}
