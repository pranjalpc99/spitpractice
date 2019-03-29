package com.tachyon.pranjalchaudhari.spit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class CommitteeActivity extends AppCompatActivity {

    String name,photo,gender,year,branch,UID,personal,college,type;
    ImageView committeeback;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.committee_recycler_view);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorWhite));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        committeeback = findViewById(R.id.committeeback);
        DrawableCompat.setTint(committeeback.getDrawable(), ContextCompat.getColor(this, R.color.black));

        name = getIntent().getExtras().getString("name");
        photo = getIntent().getExtras().getString("photo");
        gender = getIntent().getExtras().getString("gender");
        year = getIntent().getExtras().getString("year");
        branch = getIntent().getExtras().getString("branch");
        UID = getIntent().getExtras().getString("UID");
        personal = getIntent().getExtras().getString("personalEmailId");
        college = getIntent().getExtras().getString("collegeEmailId");
        type = getIntent().getExtras().getString("type");

        int[] imgs = {R.drawable.mcp,
                R.drawable.udaanvcps,
                R.drawable.pa,
                R.drawable.fa,
                R.drawable.la,
                R.drawable.utech,
                R.drawable.creativehead,
                R.drawable.usecurity,
                R.drawable.uhospitality,
                R.drawable.councilimage};
        String[] types = {"CHAIRPERSON",
                "VICE CHAIRS",
                "HEADS PERFORMING ARTS",
                "HEADS FUN EVENTS",
                "HEADS LITERARY ARTS",
                "TECH HEADS",
                "CREATIVE HEADS",
                "SECURITY",
                "HOSPITALITY",
                "STUDENT COUNCIL"};
        String[] names = {"NISHANT BAKHREY",
                "SANSKRUTI JAIPURIA\nARPAN MODI\nAASTHA SHAH",
                "GAGAN HOLANI\nRUTVIJ MEHTA\nKAPIL PATIL",
                "RUMAN KAZI\nAPURV GHADI",
                "ADITYA DESAI",
                "KAUSTUBH TORASKAR\nSHLOK GUJAR\nMANAS SHUKLA",
                "AASHISH\n....",
                "NINAD JAGDALE\nPRATHAMESH LIMAYE\nNIKHIL KULKARNI\nNISCHINT JAGDALE",
                "VIDHI HARSOVA\nADITYA KHOPKAR\nGURPREET KAUR",
                "ROHIT KODATE\nANUSHKA IYER\nDIVIT KARMIANI\nDISHA GANDHI\nNAMAN PATWA\nPRUTHA ATRE\nNEIL DAFTARY\nSHARVIKA RAUT"};

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.committee_recycler_view);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerCommitteeAdapter(context,imgs,types,names);

        recyclerView.setAdapter(adapter);

    }

    public void committeeback(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final Intent committee = new Intent(CommitteeActivity.this,CommitteeCategory.class);
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
