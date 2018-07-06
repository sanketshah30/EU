package com.example.hp.eu.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hp.eu.R;
import com.example.hp.eu.common.MyUtils;
import com.example.hp.eu.model.ReceiverHomeModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<ReceiverHomeModel> array_Data;

    public HomeAdapter(Activity context, ArrayList<ReceiverHomeModel> array_Data) {
        this.context = context;
        this.array_Data = array_Data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_ad_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.ll_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils myUtils = new MyUtils();
                myUtils.request(context, holder.tv_provider.getText().toString());
            }
        });

        /*
        Glide.with(context).load(array_Data.get(position).getProfile_image())
                .thumbnail(0.1f) // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                .into(holder.imageview_profile);

        holder.tv_provider.setText(array_Data.get(position).getProviderName());
        holder.tv_timestamp.setText(array_Data.get(position).getTimeStamp());
        holder.tv_details.setText(array_Data.get(position).getBookName());
*/
    }

    @Override
    public int getItemCount() {
//        return array_Data.size();
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageview_profile;
        private TextView tv_provider, tv_timestamp, tv_details, tv_filters;
        LinearLayout ll_request;

        public ViewHolder(View itemView) {
            super(itemView);
            imageview_profile = itemView.findViewById(R.id.imageview_profile);
            tv_provider = itemView.findViewById(R.id.tv_provider);
            tv_timestamp = itemView.findViewById(R.id.tv_timestamp);
            tv_details = itemView.findViewById(R.id.tv_details);
            ll_request = itemView.findViewById(R.id.ll_request);
            tv_filters= itemView.findViewById(R.id.tv_filter);
/*
            tv_filters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent filters= new Intent(ReceiverHomeFragment.class, FilterPageActivity.class);
                }
            });*/

        }
    }

}
