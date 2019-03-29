package com.tachyon.pranjalchaudhari.spit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

public class ClassroomNotificationFragment extends Fragment{

    FloatingActionButton addfab;
    String classtext,year,branch,name,status;
    private FirebaseAuth mAuth;
    private static DatabaseReference databaseReference;
    int flag=0,count,i,flag2,a;

    ArrayList<String> maintime = new ArrayList<String>();
    ArrayList<String> mainsender = new ArrayList<String>();
    ArrayList<String> mainnotify = new ArrayList<String>();
    ArrayList<String> mainattach = new ArrayList<String>();


    public ClassroomNotificationFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void goToLogin()
    {
        Intent log = new Intent(getContext(),MainActivity.class);
        log.putExtra("status",status);
        startActivity(log);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_classroom_notification_fragment, container, false);



       /* if(getArguments() != null)
        {
            classtext = getArguments().getString("classtext");
        }*/

        // year = classtext.substring(0,2);
      //  branch = classtext.substring(2,classtext.indexOf("."));

      //  Toast.makeText(getContext(),classtext, Toast.LENGTH_SHORT).show();

        /*ImageButton googleclass = (ImageButton) rootView.findViewById(R.id.googleclassroom);
        googleclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "https://classroom.google.com/h";
                Intent googleclassintent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                //googleclassintent.setPackage("com.google.android.apps.classroom");
                startActivity(googleclassintent);
            }
        });*/


            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            if(user == null)
            {
                status = "no";
                goToLogin();
            }
            else
            {
                String ID = mAuth.getCurrentUser().getEmail().toString().trim();
                String ref = ID.substring(0,ID.indexOf('@'));
                ref = ref.replaceAll("[^a-zA-Z0-9]", "");
                databaseReference = FirebaseDatabase.getInstance().getReference(ref);

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> Userlist = new ArrayList<String >();

                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            Userlist.add(String.valueOf(ds.getValue()));
                        }
                    /*String newPost = dataSnapshot.getValue(String.class);
                    String sname = ""+newPost;
                    profname = sname;*/
                        // status = Userlist.get(0);
                        // profUID = Userlist.get(1);
                        branch = Userlist.get(2);
                        // profcollegeemailId = Userlist.get(3);
                        // profgender = Userlist.get(4);
                        name = Userlist.get(5);
                        // profpersonalEmailID = Userlist.get(6);
                        // profphoto = Userlist.get(7);
                        // proftype = Userlist.get(8);
                        year = Userlist.get(9);
                        flag=1;
                        gotonotify(rootView);
                        // Toast.makeText(getContext(), name+" "+branch+" "+flag, Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }







        addfab = (FloatingActionButton) rootView.findViewById(R.id.class_notification_add_fab);
        addfab.setColorFilter(Color.WHITE);

        addfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddClassNotificationActivity.class));
            }
        });


        return rootView;
    }

    public void gotonotify(final View root)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notification").child(year).child(branch).child("counter");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> Userlist = new ArrayList<String >();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Userlist.add(String.valueOf(ds.getValue()));
                }
                //Toast.makeText(getContext(), Userlist.get(0), Toast.LENGTH_SHORT).show();
                count = Integer.parseInt(Userlist.get(0));

                notifysteptwo(count,root);
               // Toast.makeText(getContext(), ""+count, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*if (flag == 1)
        {
          //  Toast.makeText(getContext(), "in 1", Toast.LENGTH_SHORT).show();



        }*/



    }

    public void notifysteptwo(final int countval, final View rootv)
    {
        if(countval>0)
        {
            final String [] sender = new String[countval+1];
            final String [] date = new String[countval+1];
            final String [] notification = new String[countval+1];
            final String [] attachment = new String[countval+1];

        /*    final ArrayList<String> sender2 = new ArrayList<String>();
            final ArrayList<String> date2 = new ArrayList<String>();
            final ArrayList<String> notification2 = new ArrayList<String>();
            final ArrayList<String> attachment2 = new ArrayList<String>(); */
           // Toast.makeText(getContext(), "in 2", Toast.LENGTH_SHORT).show();
            for(i=countval;i>0;i--)
            {
                int ref = i;
                String child = String.valueOf(ref);
                databaseReference = FirebaseDatabase.getInstance().getReference("Notification").child(year).child(branch).child(child);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        ArrayList<String> Notifylist = new ArrayList<String>();

                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            Notifylist.add(String.valueOf(ds.getValue()));
                        }

                       // Toast.makeText(getContext(), Notifylist.get(0), Toast.LENGTH_SHORT).show();

                        date[i] = Notifylist.get(0);
                        sender[i] = Notifylist.get(1);
                        //notification[i] = Notifylist.get(2);

                        String year = Notifylist.get(2);
                        year = year.substring(0,2);

                        String branch = Notifylist.get(2);
                        branch = branch.substring(2,branch.indexOf("#"));

                        String main = Notifylist.get(2);
                        main = main.substring(main.indexOf("#")+1,main.length());

                        notification[i] = main;

                        maintime.add(date[i]);
                        mainsender.add(sender[i]);
                        mainnotify.add(notification[i]);

                       // Toast.makeText(getContext(), date[i], Toast.LENGTH_SHORT).show();

                      //  date2.add(i,Notifylist.get(0));
                     //   sender2.add(i,Notifylist.get(1));
                      //  notification2.add(i,Notifylist.get(2));

                       // Toast.makeText(getContext(),""+countval, Toast.LENGTH_SHORT).show();

                        notifystepthree(rootv,date,sender,notification,attachment);


                        /*for(a=0;a<date.length-1;a++) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(getContext(), date[a], Toast.LENGTH_LONG).show();

                                }
                            }, 1000);
                        }*/


                        flag2 = i;



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        }

    }

    public void notifystepthree(View rootv,String [] date,String [] sender,String [] notification,String [] attachment) //ArrayList<String> date,ArrayList<String> sender,ArrayList<String> notification,ArrayList<String> attachment
    {

        String [] mt = new String[maintime.size()];
        String [] ms = new String[maintime.size()];
        String [] mn = new String[maintime.size()];
        //String [] ma = new String[maintime.size()];
        for(int x=0;x<maintime.size();x++)
        {
            mt[x] = maintime.get(x);
            ms[x] = mainsender.get(x);
            mn[x] = mainnotify.get(x);
            //Toast.makeText(getContext(),one[x], Toast.LENGTH_SHORT).show();
        }

        //Toast.makeText(getContext(), Arrays.toString(mn), Toast.LENGTH_SHORT).show();


        RecyclerView rv = (RecyclerView) rootv.findViewById(R.id.class_notification_recycler_view);
        rv.setHasFixedSize(true);

        NotificationAdapter notificationAdapter = new NotificationAdapter(mt,ms,mn,null);

        rv.setAdapter(notificationAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
    }

}
