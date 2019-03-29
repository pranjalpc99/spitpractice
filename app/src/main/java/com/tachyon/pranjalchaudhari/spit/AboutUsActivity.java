package com.tachyon.pranjalchaudhari.spit;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AboutUsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    String name,photo,gender,year,branch,UID,personal,college,type,status;
    ImageView profileImage;
    TextView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar =  (Toolbar) findViewById(R.id.toolBarAboutUs);
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        status = getIntent().getExtras().getString("status");
        name = getIntent().getExtras().getString("name");
        photo = getIntent().getExtras().getString("photo");
        gender = getIntent().getExtras().getString("gender");
        year = getIntent().getExtras().getString("year");
        branch = getIntent().getExtras().getString("branch");
        UID = getIntent().getExtras().getString("UID");
        personal = getIntent().getExtras().getString("personalEmailId");
        college = getIntent().getExtras().getString("collegeEmailId");
        type = getIntent().getExtras().getString("type");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        profile = (TextView) headerView.findViewById(R.id.profileText);
        profileImage = (ImageView) headerView.findViewById(R.id.profileImage);

        profile.setText(name);
        Glide.with(AboutUsActivity.this).load(photo).apply(RequestOptions.circleCropTransform()).into(profileImage);

        //String name = getIntent().getExtras().getString("name");
        navigationView.setNavigationItemSelectedListener(this);
        navigationViewScrollThumbColor(navigationView);
    }

    public void navigationViewScrollThumbColor(NavigationView navigationView)
    {
        if(navigationView!=null)
        {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if(navigationMenuView!=null)
            {
                try
                {
                    Field mScrollCacheField = View.class.getDeclaredField("mScrollCache");
                    mScrollCacheField.setAccessible(true);
                    Object mScrollCache = mScrollCacheField.get(navigationMenuView);

                    Field scrollBarField = mScrollCache.getClass().getDeclaredField("scrollBar");
                    scrollBarField.setAccessible(true);
                    Object scrollBar = scrollBarField.get(mScrollCache);

                    Method method = scrollBar.getClass().getDeclaredMethod("setVerticalThumbDrawable", Drawable.class);
                    method.setAccessible(true);
                    method.invoke(scrollBar,getResources().getDrawable(R.color.gray500));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(AboutUsActivity.this, AfterRegistrationMainActivity.class));
                    }
                },200);

                break;
            case R.id.nav_profile:
                final Intent prof = new Intent(AboutUsActivity.this, ProfileActivity.class);
                prof.putExtra("status",status);
                prof.putExtra("name", name);
                prof.putExtra("collegeEmailId", college);
                prof.putExtra("gender", gender);
                prof.putExtra("personalEmailId", personal);
                prof.putExtra("UID", UID);
                prof.putExtra("branch", branch);
                prof.putExtra("year", year);
                prof.putExtra("type", type);
                prof.putExtra("photo",photo);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(prof);
                    }
                },200);
                break;
            case R.id.nav_marks:
                final Dialog dialog;
                ImageView closeDialog;

                dialog = new Dialog(this);

                dialog.setContentView(R.layout.marks_dialog);
                closeDialog = dialog.findViewById(R.id.marks_close_dialog);
                //DrawableCompat.setTint(closeDialog.getDrawable(), ContextCompat.getColor(this, R.color.darkeryellow));

                closeDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            case R.id.nav_quick_actions:
                final Intent quick = new Intent(AboutUsActivity.this, QuickActionsActivity.class);
                quick.putExtra("status",status);
                quick.putExtra("name", name);
                quick.putExtra("collegeEmailId", college);
                quick.putExtra("gender", gender);
                quick.putExtra("personalEmailId", personal);
                quick.putExtra("UID", UID);
                quick.putExtra("branch", branch);
                quick.putExtra("year", year);
                quick.putExtra("type", type);
                quick.putExtra("photo", photo);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(quick);
                    }
                },200);
                break;
            case R.id.nav_college_fest:
                final Intent fest = new Intent(AboutUsActivity.this, CollegeFestivalsActivity.class);
                fest.putExtra("status",status);
                fest.putExtra("name", name);
                fest.putExtra("collegeEmailId",college);
                fest.putExtra("gender", gender);
                fest.putExtra("personalEmailId", personal);
                fest.putExtra("UID", UID);
                fest.putExtra("branch", branch);
                fest.putExtra("year", year);
                fest.putExtra("type", type);
                fest.putExtra("photo", photo);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(fest);
                    }
                },200);
                break;
            case R.id.nav_committee:
                final Intent committee = new Intent(AboutUsActivity.this,CommitteeCategory.class);
                committee.putExtra("status",status);
                committee.putExtra("name", name);
                committee.putExtra("collegeEmailId",college);
                committee.putExtra("gender",gender);
                committee.putExtra("personalEmailId",personal);
                committee.putExtra("UID",UID);
                committee.putExtra("branch",branch);
                committee.putExtra("year",year);
                committee.putExtra("type",type);
                committee.putExtra("photo", photo);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(committee);
                    }
                },200);

                break;
            case R.id.nav_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                startActivity(Intent.createChooser(share,"Share Via"));
                break;
            case R.id.nav_about_us:
                // Handle the camera action
                break;
            case R.id.nav_visit_college_website:
                Uri collegewebsite = Uri.parse("http://www.spit.ac.in/");
                Intent web = new Intent(Intent.ACTION_VIEW, collegewebsite);
                startActivity(web);
                break;
            case R.id.nav_developer:
                final Intent developer = new Intent(AboutUsActivity.this,DeveloperActivity.class);
                developer.putExtra("status",status);
                developer.putExtra("name", name);
                developer.putExtra("collegeEmailId",college);
                developer.putExtra("gender",gender);
                developer.putExtra("personalEmailId",personal);
                developer.putExtra("UID",UID);
                developer.putExtra("branch",branch);
                developer.putExtra("year",year);
                developer.putExtra("type",type);
                developer.putExtra("photo", photo);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(developer);
                    }
                },200);

                break;
            case R.id.nav_about_app:
                final Intent aboutapp = new Intent(AboutUsActivity.this,AboutAppActivity.class);
                aboutapp.putExtra("status",status);
                aboutapp.putExtra("name", name);
                aboutapp.putExtra("collegeEmailId",college);
                aboutapp.putExtra("gender",gender);
                aboutapp.putExtra("personalEmailId",personal);
                aboutapp.putExtra("UID",UID);
                aboutapp.putExtra("branch",branch);
                aboutapp.putExtra("year",year);
                aboutapp.putExtra("type",type);
                aboutapp.putExtra("photo", photo);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(aboutapp);
                    }
                },200);

                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                goToLogin();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void goToLogin()
    {
        Intent log = new Intent(AboutUsActivity.this,MainActivity.class);
        log.putExtra("status",status);
        startActivity(log);
    }

    public void visitwebsite(View view) {
        Uri collegewebsite = Uri.parse("http://www.spit.ac.in/");
        Intent web = new Intent(Intent.ACTION_VIEW,collegewebsite);
        startActivity(web);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home: onBackPressed();
                return true;
            default: return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onBackPressed() {
        if(this.mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            startActivity(new Intent(this, AfterRegistrationMainActivity.class));
            finish();
        }
    }
}