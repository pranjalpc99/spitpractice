package com.tachyon.pranjalchaudhari.spit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerCommitteeAdapter extends RecyclerView.Adapter<RecyclerCommitteeAdapter.ViewHolder> {

    Context context;
    private int[] imgs;
    private String[] names;
    private String[] types;
    View view;
    ViewHolder viewHolder;
    int flag;

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textnames;
        public TextView textTypes;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.committeecardview);
            imageView = (ImageView) itemView.findViewById(R.id.committeeimg);
            textnames = (TextView) itemView.findViewById(R.id.committeename);
            textTypes = (TextView) itemView.findViewById(R.id.committeetype);

        }
    }

    public RecyclerCommitteeAdapter(Context context,int[]img, String[] type, String[] name)
    {
        this.context = context;
        this.imgs = img;
        this.types = type;
        this.names = name;
        flag=0;
    }

    public RecyclerCommitteeAdapter(Context context,String[] type,String[] name)
    {
        this.context = context;
        this.types = type;
        this.names = name;
        flag=1;
    }
    @NonNull
    @Override
    public RecyclerCommitteeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_committee_cardview,parent,false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(flag==0)
        {
            holder.imageView.setImageResource(imgs[position]);
            holder.textTypes.setText(types[position]);
            holder.textnames.setText(names[position]);
        }
        else if(flag==1)
        {
            holder.textTypes.setText(types[position]);
            holder.textnames.setText(names[position]);
        }


    }

    @Override
    public int getItemCount() {
        return types.length;
    }
}
