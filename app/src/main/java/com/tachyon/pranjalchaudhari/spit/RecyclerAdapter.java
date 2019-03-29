package com.tachyon.pranjalchaudhari.spit;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private String[] titles;
    private String[] classes;
    private String[] types;


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public TextView textClass;
        public TextView textType;
        public CardView cardView;

        public ViewHolder (View view)
        {
            super(view);
            cardView = (CardView) view.findViewById(R.id.webNotificationCard);
            textView = (TextView) view.findViewById(R.id.textCard);
            textClass = (TextView) view.findViewById(R.id.textClass);
            textType = (TextView) view.findViewById(R.id.textType);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition()+1;

                    //Snackbar.make(v,"Clicked "+position,Snackbar.LENGTH_LONG).setAction("Action",null).show();


                }
            });

            cardView.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition()+1;


                    //Snackbar.make(v,"Clicked "+position,Snackbar.LENGTH_LONG).setAction("Action",null).show();
                    switch (position)
                    {
                        case 1: Intent intent = new Intent(v.getContext(),FinalResultEndSem.class);
                                v.getContext().startActivity(intent);
                                break;
                        case 2: Intent intent2 = new Intent(v.getContext(),IotBootcamp.class);
                                v.getContext().startActivity(intent2);
                                break;
                        case 3: Intent intent3 = new Intent(v.getContext(),BrainhackPython.class);
                                v.getContext().startActivity(intent3);
                                break;
                        case 4: Intent intent4 = new Intent(v.getContext(),MLPython.class);
                            v.getContext().startActivity(intent4);
                            break;
                        case 5: Intent intent5 = new Intent(v.getContext(),CyberSecurity.class);
                            v.getContext().startActivity(intent5);
                            break;
                        case 6: Intent intent6 = new Intent(v.getContext(),ProvisionResultOdd.class);
                            v.getContext().startActivity(intent6);
                            break;
                        case 7: Uri uri7 = Uri.parse("http://www.spit.ac.in/2018/06/14/provisional-result-make-up-exam-for-all-branch-ug-pg/");
                            Intent intent7 = new Intent(Intent.ACTION_VIEW,uri7);
                            v.getContext().startActivity(intent7);
                    }
                }
            });
        }

    }

    public RecyclerAdapter (String[] title,String[] classs,String[] type)
    {
        titles=title;
        classes=classs;
        types=type;

    }

    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.web_notification_cardview,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(titles[i]);
        viewHolder.textClass.setText(classes[i]);
        viewHolder.textType.setText(types[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
