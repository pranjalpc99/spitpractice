package com.tachyon.pranjalchaudhari.spit;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private String[] sendername;
    private String[] time;
    private String[] notification;
    private String[] attachments;

   /* ArrayList<String> sendername;
    ArrayList<String> time;
    ArrayList<String> notification;
    ArrayList<String> attachments;*/

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textSendername;
        public TextView texttime;
        public TextView textNotification;
        public TextView textAttachments;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.notificationCard);
            textSendername = (TextView) itemView.findViewById(R.id.textSender);
            texttime = (TextView) itemView.findViewById(R.id.texttime);
            textNotification = (TextView) itemView.findViewById(R.id.textnotification);
            textAttachments = (TextView) itemView.findViewById(R.id.textattachments);
        }
    }

    public NotificationAdapter (String [] time,String [] sendername,String [] notification,String [] attachments)//ArrayList<String> sendername,ArrayList<String> time,ArrayList<String> notification,ArrayList<String> attachments
    {
        this.sendername=sendername;
        this.time=time;
        this.notification=notification;
        this.attachments = attachments;

    }


    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        holder.textSendername.setText(sendername[position]);//sendername.get(position)
        holder.texttime.setText(time[position]);//time.get(position)
        holder.textNotification.setText(notification[position]);//notification.get(position)
       // holder.textAttachments.setText(attachments[position]);//attachments.get(position)
    }

    @Override
    public int getItemCount() {
        return time.length; //size();
    }
}
