package com.example.hp.eu.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.eu.R;
import com.example.hp.eu.model.UpdateProfileModel;

import java.util.ArrayList;

public class UpdateProfileAdapter extends RecyclerView.Adapter<UpdateProfileAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<UpdateProfileModel> array_Data;

    public UpdateProfileAdapter(Activity context, ArrayList<UpdateProfileModel> array_Data) {
        this.context = context;
        this.array_Data = array_Data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_kid_details, parent, false);
        return new UpdateProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UpdateProfileAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
//        return array_Data.size();
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name, tv_school, tv_board,tv_standard, tv_booklist;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_kidname);
            tv_school=itemView.findViewById(R.id.tv_kidschool);
            tv_board=itemView.findViewById(R.id.tv_kidboard);
            tv_standard=itemView.findViewById(R.id.tv_kidstandard);
            tv_booklist=itemView.findViewById(R.id.tv_kidbookslist);
        }

    }

}
