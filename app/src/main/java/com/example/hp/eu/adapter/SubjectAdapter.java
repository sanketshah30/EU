package com.example.hp.eu.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.eu.R;
import com.example.hp.eu.model.SubjectModel;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<SubjectModel> array_Data;

    public SubjectAdapter(Activity context, ArrayList<SubjectModel> array_Data) {
        this.context = context;
        this.array_Data = array_Data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_subject, parent, false);
        return new SubjectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SubjectAdapter.ViewHolder holder, int position) {
        holder.rl_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.rl_button.setVisibility(View.GONE);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
//        return array_Data.size();
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_Subject;
        private ImageView image_close;
        private View rl_button;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_Subject = itemView.findViewById(R.id.tv_Subject);
            image_close = itemView.findViewById(R.id.image_close);
            rl_button=itemView.findViewById(R.id.rl_button);
        }
    }

}
