package com.tachyon.pranjalchaudhari.spit;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CommitteeUdaan extends AppCompatActivity {

    String name,photo,gender,year,branch,UID,personal,college,type;
    ImageView committeeudaanback;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_udaan);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorWhite));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        committeeudaanback = findViewById(R.id.committeeudaanback);
        DrawableCompat.setTint(committeeudaanback.getDrawable(), ContextCompat.getColor(this, R.color.black));

        name = getIntent().getExtras().getString("name");
        photo = getIntent().getExtras().getString("photo");
        gender = getIntent().getExtras().getString("gender");
        year = getIntent().getExtras().getString("year");
        branch = getIntent().getExtras().getString("branch");
        UID = getIntent().getExtras().getString("UID");
        personal = getIntent().getExtras().getString("personalEmailId");
        college = getIntent().getExtras().getString("collegeEmailId");
        type = getIntent().getExtras().getString("type");

        String[] types = {"CHAIRPERSON",
                "VICE CHAIRS",
                "TECHNICAL SECRETARY",
                "EXECUTIVE",
                "TECH",
                "PUBLIC RELATIONS",
                "MARKETING",
                "EVENTS",
                "DECOR",
                "ADMIN",
                "SECURITY"};
        String[] names = {"ANUSHKA RINGSHIA",
                "KARAN RANE\nVIKRAM SINGH",
                "MADHAV WAGH",
                "FARZEEM JIVANI\nARNNAVA SHARMA",
                "ADNAN ANSARI\nKARAN BHEDA\nSHUBHAM MAHAJAN\nATHARVA PATIL\nAKSHAY RAUL",
                "SURMEET KAUR JHAJJ\nPURVA JHAVERI\nNISHA RANGNANI\nSHREYA SAWLESHWARKAR",
                "MANSI DANDIWALA\nVIJETA MARIWALLA\nABHISHEK NAIK\nDIVITA VORA",
                "JANIT PARIKH\nOMKAR RAVATE\nAPURVA SAKSENA",
                "AARFAH AHMAD\nANUSHKA IYER\nISHA PANDYA\nNISHITA UPADHYAY",
                "VIRAJ KAMAT\nSHUBHAM PATIL\nHARSH VORA",
                "RUSSELL D'MONTE\nSHADAB KHAN\nSANIYA SANTOSH\nAKHIL SARDESAI\nAMIT SONAWANE"};

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.committee_recycler_view_udaan);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerCommitteeAdapter(context,types,names);

        recyclerView.setAdapter(adapter);

    }

    public void committeeudaanback(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final Intent committee = new Intent(CommitteeUdaan.this,CommitteeCategory.class);
        committee.putExtra("name", name);
        committee.putExtra("collegeEmailId",college);
        committee.putExtra("gender",gender);
        committee.putExtra("personalEmailId",personal);
        committee.putExtra("UID",UID);
        committee.putExtra("branch",branch);
        committee.putExtra("year",year);
        committee.putExtra("type",type);
        committee.putExtra("photo", photo);
        startActivity(committee);
        finish();
    }
}
