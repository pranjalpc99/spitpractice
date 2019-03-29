package com.tachyon.pranjalchaudhari.spit;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DeveloperActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    String name,photo,gender,year,branch,UID,personal,college,type,dev_img,status;
    ImageView profileImage,devback,devimage,devloc;
    TextView profile;
    Integer REQ_CAM=1,SELECT_FILE=0;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        devback = findViewById(R.id.developerback);
        DrawableCompat.setTint(devback.getDrawable(), ContextCompat.getColor(this, R.color.colorWhite));


        devimage = findViewById(R.id.dev_profile_pic);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        String personalEmailID = firebaseAuth.getCurrentUser().getEmail().toString().trim();

      /*  FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.dev_fab);

        if(personalEmailID.equals("pranjalpchaudhari99@gmail.com"))
        {
            floatingActionButton.setVisibility(View.VISIBLE);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectImage();
                }
            });
        }
        else
        {
            floatingActionButton.setEnabled(false);
            floatingActionButton.setVisibility(View.INVISIBLE);
        } */

        devloc = findViewById(R.id.dev_loc);
        DrawableCompat.setTint(devloc.getDrawable(), ContextCompat.getColor(this, R.color.gray500));

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
        Glide.with(DeveloperActivity.this).load(photo).apply(RequestOptions.circleCropTransform()).into(profileImage);

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

    public void selectImage()
    {
        final CharSequence [] items = {"Camera","Gallery","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(DeveloperActivity.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(items[i].equals("Camera"))
                {
                    Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cam,REQ_CAM);
                }
                else if(items[i].equals("Gallery"))
                {
                    Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    gallery.setType("image/*");
                    startActivityForResult(gallery.createChooser(gallery,"Select file"),SELECT_FILE);
                }
                else if(items[i].equals("Cancel"))
                {
                    dialogInterface.dismiss();
                }
            }
        });

        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK)
        {
            if(requestCode==REQ_CAM)
            {
                Bundle bundle =data.getExtras();
                final Bitmap bm = (Bitmap) bundle.get("data");
                devimage.setImageBitmap(bm);
            }
            else if(requestCode==SELECT_FILE)
            {
                Uri select = data.getData();
                dev_img = select.toString();
                devimage.setImageURI(select);
            }
        }
    }

    public void saveImage()
    {
        SaveDevProfile saveDevProfile = new SaveDevProfile(dev_img);
        String personalEmailID = firebaseAuth.getCurrentUser().getEmail().toString().trim();
        String personalemailwithoutID = personalEmailID.substring(0,personalEmailID.indexOf('@'));

        databaseReference.child(personalemailwithoutID).setValue(saveDevProfile);
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
                        startActivity(new Intent(DeveloperActivity.this, AfterRegistrationMainActivity.class));
                    }
                },200);

                break;
            case R.id.nav_profile:
                final Intent prof = new Intent(DeveloperActivity.this, ProfileActivity.class);
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
                final Intent quick = new Intent(DeveloperActivity.this, QuickActionsActivity.class);
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
                final Intent fest = new Intent(DeveloperActivity.this, CollegeFestivalsActivity.class);
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
                final Intent committee = new Intent(DeveloperActivity.this,CommitteeCategory.class);
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
                final Intent aboutus = new Intent(DeveloperActivity.this,AboutUsActivity.class);
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
                break;
            case R.id.nav_about_app:
                final Intent aboutapp = new Intent(DeveloperActivity.this,AboutAppActivity.class);
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
        Intent log = new Intent(DeveloperActivity.this,MainActivity.class);
        log.putExtra("status",status);
        startActivity(log);
    }

    public void devback(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if(this.mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            startActivity(new Intent(DeveloperActivity.this, AfterRegistrationMainActivity.class));
            finish();
        }
    }


    public void instagrambtn(View view) {
        String username = "pranjal_pc_99";

        Uri uri = Uri.parse("https://instagram.com/"+username);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);

        intent.setPackage("com.instagram.android");

        try
        {
            startActivity(intent);
        }
        catch (ActivityNotFoundException anfe)
        {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://instagram.com/"+username)));
        }

    }

    public void googleplusbtn(View view) {

        String username = "117603764256619003511";

        try
        {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("org.chromium.webapk.a25d3e89e3fc82c91");
            //intent.setClassName("com.google.android.apps.plus","com.google.android.apps.plus.phone.UrlGatewayActivity");
            intent.putExtra("customAppUri",username);
            startActivity(intent);
        }
        catch (ActivityNotFoundException anfe)
        {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://plus.google.com/"+username)));
        }
    }

    public void facebookbtn(View view) {
        String username = "pranjal.chaudhari.99";

        Uri uri = Uri.parse("fb://profile/100008055900592");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);

        try
        {
            this.getPackageManager().getPackageInfo("com.facebook.katana",0);
            startActivity(intent);
        }
        catch (Exception e)
        {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/"+username)));
        }

    }

    public void twitterbtn(View view) {

        String username = "3271045135";

        Uri uri = Uri.parse("twitter://user?user_id="+username);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);

        try
        {
            this.getPackageManager().getPackageInfo("com.twitter.android",0);
            startActivity(intent);
        }
        catch (Exception e)
        {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/"+username)));
        }
    }

    public void sendemailbtn(View view) {
        composeEmail();
    }

    public void composeEmail()
    {
        String [] address = {"pranjalpchaudhari99@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,address);

        if(intent.resolveActivity(getPackageManager())!= null)
        {
            startActivity(intent);
        }
    }
}
