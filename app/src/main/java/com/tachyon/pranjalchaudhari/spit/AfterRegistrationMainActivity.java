package com.tachyon.pranjalchaudhari.spit;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.tachyon.pranjalchaudhari.spit.MainActivity.flag;

public class AfterRegistrationMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Window window = getWindow();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FirebaseAuth mAuth;
    static int flag=0,logout=0;
    private static String profname,profcollegeemailId,profgender,profpersonalEmailID,profUID,profbranch,profyear,proftype,profphoto,status;
    private static DatabaseReference databaseReference;
    TextView profile;
    ImageView profileImage;
    NavigationView navigationView;
    boolean connected = false;
    String version,appversion,updatelink;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
   // ViewPagerAdapter adapter;


    public static int num()
    {
        return 12;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_registration_main);



        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        {
            connected = true;
            progressDialog = new ProgressDialog(AfterRegistrationMainActivity.this);
            progressDialog.setMessage("Loading Credentials");
            progressDialog.show();

            // FirebaseDatabase.getInstance().setPersistenceEnabled(false);

        }
        else
        {
            connected = false;
           // startActivity(new Intent(AfterRegistrationMainActivity.this,NoInternetConnection.class));
            try
            {
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            }
            catch (Exception e)
            {

            }
        }


        //startActivity(new Intent(this,Main2Activity.class));
        logout=1;

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SPIT");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //window.setStatusBarColor(Color.TRANSPARENT);
        //window.setStatusBarColor(getResources().getColor(R.color.transparent));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),AfterRegistrationMainActivity.this);
        viewPager.setAdapter(pagerAdapter);
        // setupViewPager(viewPager);

        tabLayout =(TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        for(int i = 0;i<tabLayout.getTabCount();i++)
        {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        profile = (TextView) headerView.findViewById(R.id.profileText);
        profileImage = (ImageView) headerView.findViewById(R.id.profileImage);

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

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }
    // [END on_start_check_user]

    public void updateUI(FirebaseUser user)
    {
        if(user != null)
        {
            try
            {

                appversion = getString(R.string.versionnumber);
                databaseReference = FirebaseDatabase.getInstance().getReference("appversion");
               // FirebaseDatabase.getInstance().setPersistenceEnabled(true);

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<String> versiondetail = new ArrayList<String>();
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            versiondetail.add(String.valueOf(ds.getValue()));
                        }
                        version = versiondetail.get(1);
                        updatelink = versiondetail.get(0);
                        //Toast.makeText(AfterRegistrationMainActivity.this, version, Toast.LENGTH_SHORT).show();

                        if(!TextUtils.equals(appversion,version))
                        {
                            // Toast.makeText(this, appversion, Toast.LENGTH_SHORT).show();
                            final AlertDialog.Builder builder = new AlertDialog.Builder(AfterRegistrationMainActivity.this);
                            builder.setTitle("New Update");
                            builder.setMessage("Update your app to get the latest features");
                            builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    builder.create().dismiss();
                                    Uri update = Uri.parse(updatelink);
                                    Intent updateintent = new Intent(Intent.ACTION_VIEW,update);
                                    startActivity(updateintent);

                                }
                            });
                            builder.setNegativeButton("Cancel and close app", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    builder.create().dismiss();
                                    onBackPressed();
                                }
                            });
                            builder.create().show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                try
                {
                    String ID = mAuth.getCurrentUser().getEmail().toString().trim();
                    String ref = ID.substring(0,ID.indexOf('@'));
                    ref = ref.replaceAll("[^a-zA-Z0-9]", "");
                    databaseReference = FirebaseDatabase.getInstance().getReference(ref);
                }
                catch (Exception e)
                {

                }




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
                        status = Userlist.get(0);
                        profUID = Userlist.get(1);
                        profbranch = Userlist.get(2);
                        profcollegeemailId = Userlist.get(3);
                        profgender = Userlist.get(4);
                        profname = Userlist.get(5);
                        profpersonalEmailID = Userlist.get(6);
                        profphoto = Userlist.get(7);
                        proftype = Userlist.get(8);
                        profyear = Userlist.get(9);

                        profile.setText(profname);
                        Glide.with(AfterRegistrationMainActivity.this).load(profphoto).apply(RequestOptions.circleCropTransform()).into(profileImage);

                        if(connected)
                        {
                            progressDialog.dismiss();
                        }

                        myclass();

                        Bundle bundle = new Bundle();
                        bundle.putString("classtext",profyear+profbranch);
                        // set Fragmentclass Arguments
                        ClassroomNotificationFragment fragobj = new ClassroomNotificationFragment();
                        fragobj.setArguments(bundle);
                        flag =1;

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
            catch (Exception e)
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Profile Incomplete");
                builder.setMessage("Please complete your profile first to access the app");
                builder.setPositiveButton("Proceed for completion", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        builder.create().dismiss();
                        Intent completeprofile = new Intent(AfterRegistrationMainActivity.this,MainActivity.class);
                        startActivity(completeprofile);

                    }
                });
                builder.create().show();
            }


        }
        else
        {
            status = "no";
            goToLogin();
        }
    }

    public String myname()
    {
        return profname;
    }

    public String myclass()
    {
        return profyear+profbranch;
    }

    public static String nameprof()
    {
        return profname;
    }

    public void goToLogin()
    {
        Intent log = new Intent(AfterRegistrationMainActivity.this,MainActivity.class);
        log.putExtra("status",status);
        startActivity(log);
    }


   /* private void setupViewPager(ViewPager viewPager)
    {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(),"One");
        adapter.addFragment(new Tab2Fragment(),"Two");
        viewPager.setAdapter(adapter);

    }*/

   /* class ViewPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList =  new ArrayList<>();
        private final List<String> mFragmentTitleList =  new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0: return new BlankFragment();
            }
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment,String title)
        {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }*/


    class PagerAdapter extends FragmentPagerAdapter {

        String tabTitles[] = new String[]{"Web Notifications", "Class Notifications"};
        Context context;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new BlankFragment();
                case 1:
                    return new ClassroomNotificationFragment();

            }

            return null;
        }


        @Override
        public CharSequence getPageTitle(int position){
            return tabTitles[position];
        }

        public View getTabView(int position){
            View tab = LayoutInflater.from(AfterRegistrationMainActivity.this).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) tab.findViewById(R.id.custom_text);
            tv.setText(tabTitles[position]);
            tv.setEnabled(false);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            return tab;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:

                break;
            case R.id.nav_profile:
                final Intent prof = new Intent(AfterRegistrationMainActivity.this, ProfileActivity.class);
                prof.putExtra("status",status);
                prof.putExtra("name", profname);
                prof.putExtra("collegeEmailId", profcollegeemailId);
                prof.putExtra("gender", profgender);
                prof.putExtra("personalEmailId", profpersonalEmailID);
                prof.putExtra("UID", profUID);
                prof.putExtra("branch", profbranch);
                prof.putExtra("year", profyear);
                prof.putExtra("type", proftype);
                prof.putExtra("photo", profphoto);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(prof);
                    }
                },200);
                break;
            case R.id.nav_marks:
                /*final Intent marks = new Intent(AfterRegistrationMainActivity.this, MarksActivity.class);
                marks.putExtra("name", profname);
                marks.putExtra("collegeEmailId", profcollegeemailId);
                marks.putExtra("gender", profgender);
                marks.putExtra("personalEmailId", profpersonalEmailID);
                marks.putExtra("UID", profUID);
                marks.putExtra("branch", profbranch);
                marks.putExtra("year", profyear);
                marks.putExtra("type", proftype);
                marks.putExtra("photo", profphoto);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(marks);
                    }
                },200);*/
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
                final Intent quick = new Intent(AfterRegistrationMainActivity.this, QuickActionsActivity.class);
                quick.putExtra("status",status);
                quick.putExtra("name", profname);
                quick.putExtra("collegeEmailId", profcollegeemailId);
                quick.putExtra("gender", profgender);
                quick.putExtra("personalEmailId", profpersonalEmailID);
                quick.putExtra("UID", profUID);
                quick.putExtra("branch", profbranch);
                quick.putExtra("year", profyear);
                quick.putExtra("type", proftype);
                quick.putExtra("photo", profphoto);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(quick);
                    }
                },200);
                break;
            case R.id.nav_college_fest:
                final Intent fest = new Intent(AfterRegistrationMainActivity.this, CollegeFestivalsActivity.class);
                fest.putExtra("status",status);
                fest.putExtra("name", profname);
                fest.putExtra("collegeEmailId", profcollegeemailId);
                fest.putExtra("gender", profgender);
                fest.putExtra("personalEmailId", profpersonalEmailID);
                fest.putExtra("UID", profUID);
                fest.putExtra("branch", profbranch);
                fest.putExtra("year", profyear);
                fest.putExtra("type", proftype);
                fest.putExtra("photo", profphoto);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(fest);
                    }
                },200);
                break;
            case R.id.nav_committee:
                final Intent committee = new Intent(AfterRegistrationMainActivity.this, CommitteeCategory.class);
                committee.putExtra("status",status);
                committee.putExtra("name", profname);
                committee.putExtra("collegeEmailId", profcollegeemailId);
                committee.putExtra("gender", profgender);
                committee.putExtra("personalEmailId", profpersonalEmailID);
                committee.putExtra("UID", profUID);
                committee.putExtra("branch", profbranch);
                committee.putExtra("year", profyear);
                committee.putExtra("type", proftype);
                committee.putExtra("photo", profphoto);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(committee);
                    }
                },200);
                 //startActivity(new Intent(AfterRegistrationMainActivity.this,CommitteeActivity.class));
                 break;
            case R.id.nav_visit_college_website:
                 Uri collegewebsite = Uri.parse("http://www.spit.ac.in/");
                 Intent web = new Intent(Intent.ACTION_VIEW,collegewebsite);
                 startActivity(web);

                break;
            case R.id.nav_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                startActivity(Intent.createChooser(share,"Share Via"));

                break;
            case R.id.nav_about_us:
                final Intent aboutus = new Intent(AfterRegistrationMainActivity.this,AboutUsActivity.class);
                aboutus.putExtra("status",status);
                aboutus.putExtra("name", profname);
                aboutus.putExtra("collegeEmailId", profcollegeemailId);
                aboutus.putExtra("gender", profgender);
                aboutus.putExtra("personalEmailId", profpersonalEmailID);
                aboutus.putExtra("UID", profUID);
                aboutus.putExtra("branch", profbranch);
                aboutus.putExtra("year", profyear);
                aboutus.putExtra("type", proftype);
                aboutus.putExtra("photo", profphoto);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(aboutus);
                    }
                },200);

                break;
            case R.id.nav_developer:
                final Intent developer = new Intent(AfterRegistrationMainActivity.this,DeveloperActivity.class);
                developer.putExtra("status",status);
                developer.putExtra("name", profname);
                developer.putExtra("collegeEmailId",profcollegeemailId);
                developer.putExtra("gender",profgender);
                developer.putExtra("personalEmailId",profpersonalEmailID);
                developer.putExtra("UID",profUID);
                developer.putExtra("branch",profbranch);
                developer.putExtra("year",profyear);
                developer.putExtra("type",proftype);
                developer.putExtra("photo", profphoto);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(developer);
                    }
                },200);

                break;
            case R.id.nav_about_app:
                final Intent aboutapp = new Intent(AfterRegistrationMainActivity.this,AboutAppActivity.class);
                aboutapp.putExtra("status",status);
                aboutapp.putExtra("name", profname);
                aboutapp.putExtra("collegeEmailId",profcollegeemailId);
                aboutapp.putExtra("gender",profgender);
                aboutapp.putExtra("personalEmailId",profpersonalEmailID);
                aboutapp.putExtra("UID",profUID);
                aboutapp.putExtra("branch",profbranch);
                aboutapp.putExtra("year",profyear);
                aboutapp.putExtra("type",proftype);
                aboutapp.putExtra("photo", profphoto);
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


    public static int logout()
    {
        return logout;
    }

    @Override
    public void onBackPressed() {
        if(this.mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            onResume();
            Intent lastActivity = new Intent(Intent.ACTION_MAIN);
            lastActivity.addCategory(Intent.CATEGORY_HOME);
            lastActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            lastActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            lastActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(lastActivity);
            finish();
            //System.exit(0);
        }

    }

}
