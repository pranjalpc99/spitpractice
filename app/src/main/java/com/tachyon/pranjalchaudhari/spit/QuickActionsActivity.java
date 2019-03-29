package com.tachyon.pranjalchaudhari.spit;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class QuickActionsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    String name,photo,gender,year,branch,UID,personal,college,type,status;
    String [] titles = {"Time Table","Syllabus","Academic Calendar"};
    String [] subTitles = {"Your class time table","Your subjects and its syllabus","Annual holidays, exams schedule etc."};
    int [] quickpic = {R.drawable.event,R.drawable.open_book,R.drawable.calendar};
    ListView listView;
    ImageView profileImage;
    TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_actions);

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        profile = (TextView) headerView.findViewById(R.id.profileText);
        profileImage = (ImageView) headerView.findViewById(R.id.profileImage);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        profile.setText(name);
        Glide.with(QuickActionsActivity.this).load(photo).apply(RequestOptions.circleCropTransform()).into(profileImage);

        //String name = getIntent().getExtras().getString("name");
        navigationView.setNavigationItemSelectedListener(this);
        navigationViewScrollThumbColor(navigationView);

        final CharSequence [] items = {"Academic Calender 2018-19 Odd Semester","Academic Calender 2018-19 Even Semester","Cancel"};

        listView = findViewById(R.id.quicklistview);
        QuickAdapter quickAdapter = new QuickAdapter(this,titles,subTitles,quickpic);
        listView.setAdapter(quickAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        switch (branch) {
                            case "COMP":
                                Uri comptt = Uri.parse("https://drive.google.com/open?id=1mPllSTEjY4hKOn5Z6hmoFqgV9X7eUJmH");
                                Intent compintent = new Intent(Intent.ACTION_VIEW,comptt);
                                startActivity(compintent);
                                break;
                            case "IT":
                                Uri ittt = Uri.parse("https://drive.google.com/open?id=1PGzvBE92U8WXtqu2Ft-hZqajeBXMVus9");
                                Intent itintent = new Intent(Intent.ACTION_VIEW,ittt);
                                startActivity(itintent);
                                break;
                            case "EXTC":
                                Uri extctt = Uri.parse("https://drive.google.com/open?id=1GzBk2NyYmxzu9cdr-cbMvJnWwQ7iR1sz");
                                Intent extcintent = new Intent(Intent.ACTION_VIEW,extctt);
                                startActivity(extcintent);
                                break;
                            case "ETRX":
                                Uri etrxtt = Uri.parse("https://drive.google.com/open?id=1PGzvBE92U8WXtqu2Ft-hZqajeBXMVus9");
                                Intent etrxintent = new Intent(Intent.ACTION_VIEW,etrxtt);
                                startActivity(etrxintent);
                                break;
                        }
                        break;
                    case 1:
                        switch (year) {
                            case "FE":
                                Uri uri0 = Uri.parse("http://library.spit.ac.in/AS2018-19/FE2018-19.pdf");
                                Intent intent0 = new Intent(Intent.ACTION_VIEW, uri0);
                                startActivity(intent0);
                                break;
                            case "SE":
                                switch (branch) {
                                    case "COMP":
                                        Uri uri1 = Uri.parse("http://library.spit.ac.in/AS2018-19/SE3-4-COMP-2018.pdf");
                                        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                                        startActivity(intent1);
                                        break;
                                    case "IT":
                                        Uri uri2 = Uri.parse("http://library.spit.ac.in/AS2018-19/SE3-4-IT-2018.pdf");
                                        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                                        startActivity(intent2);
                                        break;
                                    case "EXTC":
                                        Uri uri3 = Uri.parse("http://library.spit.ac.in/AS2018-19/SE3-4-EXTC-2018.pdf");
                                        Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
                                        startActivity(intent3);
                                        break;
                                    case "ETRX":
                                        Uri uri4 = Uri.parse("http://library.spit.ac.in/AS2018-19/SE3-4-ETRX-2018.pdf");
                                        Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);
                                        startActivity(intent4);
                                        break;
                                }
                                break;
                            case "TE":
                                switch (branch) {
                                    case "COMP":
                                        Uri uri1 = Uri.parse("http://library.spit.ac.in/AS2018-19/TE5-6-COMP-2018.pdf");
                                        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                                        startActivity(intent1);
                                        break;
                                    case "IT":
                                        Uri uri2 = Uri.parse("http://library.spit.ac.in/AS2018-19/TE5-6-IT-2018.pdf");
                                        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                                        startActivity(intent2);
                                        break;
                                    case "EXTC":
                                        Uri uri3 = Uri.parse("http://library.spit.ac.in/AS2018-19/TE5-6-EXTC-2018.pdf");
                                        Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
                                        startActivity(intent3);
                                        break;
                                    case "ETRX":
                                        Uri uri4 = Uri.parse("http://library.spit.ac.in/AS2018-19/TE5-6-ETRX-2018.pdf");
                                        Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);
                                        startActivity(intent4);
                                        break;
                                }
                                break;
                            case "BE":
                                switch (branch) {
                                    case "COMP":
                                        Uri uri1 = Uri.parse("http://library.spit.ac.in/AS2018-19/BE7-8-COMP-2018.pdf");
                                        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                                        startActivity(intent1);
                                        break;
                                    case "IT":
                                        Uri uri2 = Uri.parse("http://library.spit.ac.in/AS2018-19/BE7-8-IT-2018.pdf");
                                        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                                        startActivity(intent2);
                                        break;
                                    case "EXTC":
                                        Uri uri3 = Uri.parse("http://library.spit.ac.in/AS2018-19/BE7-8-EXTC-2018.pdf");
                                        Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
                                        startActivity(intent3);
                                        break;
                                    case "ETRX":
                                        Uri uri4 = Uri.parse("http://library.spit.ac.in/AS2018-19/BE7-8-ETRX-2018.pdf");
                                        Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);
                                        startActivity(intent4);
                                        break;
                                }
                                break;
                        }
                        break;
                    case 2:
                        AlertDialog.Builder builder = new AlertDialog.Builder(QuickActionsActivity.this);
                        //builder.setTitle("Add Image");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(items[i].equals("Academic Calender 2018-19 Odd Semester"))
                                {
                                    Uri odd = Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/05/Academic-Calendar-2018-19-Odd-Sem_16_May_2018.pdf");
                                    Intent oddintent = new Intent(Intent.ACTION_VIEW,odd);
                                    startActivity(oddintent);
                                }
                                else if(items[i].equals("Academic Calender 2018-19 Even Semester"))
                                {
                                    Uri even = Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/05/Academic-Calendar-2018-19-Even_16_May_2018.pdf");
                                    Intent evenintent = new Intent(Intent.ACTION_VIEW,even);
                                    startActivity(evenintent);
                                }
                                else if(items[i].equals("Cancel"))
                                {
                                    dialogInterface.dismiss();
                                }
                            }
                        });

                        builder.show();

                        break;
                }
            }
        });
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
                        startActivity(new Intent(QuickActionsActivity.this, AfterRegistrationMainActivity.class));
                    }
                },200);

                break;
            case R.id.nav_profile:
                final Intent prof = new Intent(QuickActionsActivity.this, ProfileActivity.class);
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
                break;
            case R.id.nav_college_fest:
                final Intent fest = new Intent(QuickActionsActivity.this, CollegeFestivalsActivity.class);
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
                final Intent committee = new Intent(QuickActionsActivity.this,CommitteeCategory.class);
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
            case R.id.nav_about_us:
                final Intent aboutus = new Intent(QuickActionsActivity.this,AboutUsActivity.class);
                aboutus.putExtra("status",status);
                aboutus.putExtra("name", name);
                aboutus.putExtra("collegeEmailId",college);
                aboutus.putExtra("gender",gender);
                aboutus.putExtra("personalEmailId",personal);
                aboutus.putExtra("UID",UID);
                aboutus.putExtra("branch",branch);
                aboutus.putExtra("year",year);
                aboutus.putExtra("type",type);
                aboutus.putExtra("photo", photo);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(aboutus);
                    }
                },200);
                break;
            case R.id.nav_visit_college_website:
                Uri collegewebsite = Uri.parse("http://www.spit.ac.in/");
                Intent web = new Intent(Intent.ACTION_VIEW, collegewebsite);
                startActivity(web);
                break;
            case R.id.nav_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                startActivity(Intent.createChooser(share,"Share Via"));
                break;
            case R.id.nav_developer:
                final Intent developer = new Intent(QuickActionsActivity.this,DeveloperActivity.class);
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
                final Intent aboutapp = new Intent(QuickActionsActivity.this,AboutAppActivity.class);
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
        Intent log = new Intent(QuickActionsActivity.this,MainActivity.class);
        log.putExtra("status",status);
        startActivity(log);
    }

    public void quickactionsback(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if(this.mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            startActivity(new Intent(QuickActionsActivity.this, AfterRegistrationMainActivity.class));
            finish();
        }
    }
}

class QuickAdapter extends ArrayAdapter
{
    private int [] picArray;
    private String [] titleArray;
    private String [] subTitleArray;

    public QuickAdapter(Context context,String[] titles1,String[] subTitle1,int [] pics)
    {
        super(context,R.layout.custom_quick_listview,R.id.quick_title,titles1);
        this.picArray = pics;
        this.titleArray = titles1;
        this.subTitleArray = subTitle1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_quick_listview,parent,false);
        ImageView qpic = (ImageView) row.findViewById(R.id.quick_pic);
        TextView qtitle = (TextView) row.findViewById(R.id.quick_title);
        TextView qsubtitle = (TextView) row.findViewById(R.id.quick_subTitle);

        qpic.setImageResource(picArray[position]);
        qtitle.setText(titleArray[position]);
        qsubtitle.setText(subTitleArray[position]);
        return row;
    }
}
