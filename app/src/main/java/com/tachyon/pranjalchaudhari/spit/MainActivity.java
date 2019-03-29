package com.tachyon.pranjalchaudhari.spit;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class   MainActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar toolbar;
    private SignInButton signInButton;
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseUser userdirect;
    private DatabaseReference databaseReference;
    public static int flag,num,logout=0;
    public String yearCheck,photo,status="";
    ImageView spit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(this, "Main", Toast.LENGTH_SHORT).show();

        toolbar = (Toolbar) findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle("SPIT");

        toolbar.setPadding(0,getStatusBarHeight(),0,0);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        spit = findViewById(R.id.main_spit_logo);
       // DrawableCompat.setTint(spit.getDrawable(), ContextCompat.getColor(this,R.color.colorWhite));
        mAuth = FirebaseAuth.getInstance();
        userdirect= mAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        findViewById(R.id.sign_in_button).setOnClickListener(MainActivity.this);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        status = getIntent().getExtras().getString("status");
        //Toast.makeText(this, status, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            Toast.makeText(this, "App works best in portrait mode. Please switch back to portrait mode", Toast.LENGTH_SHORT).show();
        }
        else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT)
        {

        }
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
           // FirebaseUser currentUser = mAuth.getCurrentUser();
            //updateUI(currentUser);

    }
    // [END on_start_check_user]


    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            //Toast.makeText(this, "rc match", Toast.LENGTH_SHORT).show();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                photo = account.getPhotoUrl().toString();
                //Toast.makeText(this, photo, Toast.LENGTH_SHORT).show();
                //Glide.with
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }
    // [END onactivityresult]


    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        //Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        //showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                           // Toast.makeText(MainActivity.this, "update user", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "signInWithCredential:failure", task.getException());
                            // Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]

    private void signIn() {

        if(logout==10)
        {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);
           // Toast.makeText(this, "from logout", Toast.LENGTH_SHORT).show();
        }

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        if (user != null)
        {


            if(status.equals("no"))
            {

              /*  String personalEmailID = mAuth.getCurrentUser().getEmail().toString().trim();
                String personalemailwithoutID = personalEmailID.substring(0,personalEmailID.indexOf('@'));
                personalemailwithoutID = personalemailwithoutID.replaceAll("[^a-zA-Z0-9]", "");
                databaseReference = FirebaseDatabase.getInstance().getReference();

                HashMap<String,String> reg_statue = new HashMap<>();
                reg_statue.put("Registered","no");
                databaseReference.child(personalemailwithoutID).setValue(reg_statue);*/

                /*FirebaseDatabase.getInstance().getReference("Student")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String name = dataSnapshot.child(year).getValue().toString();

                                if(!TextUtils.isEmpty(name))
                                {
                                    Toast.makeText(MainActivity.this, "Main DR", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this,AfterRegistrationMainActivity.class));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });*/
               /*databaseReference.child("Student").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //  progressDialog.setMessage("Verifying User...");
                        //progressDialog.show();
                        String value = dataSnapshot.child("name").getValue().toString();
                        if(!TextUtils.isEmpty(value))
                        {
                            Toast.makeText(MainActivity.this, "Main DR", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,AfterRegistrationMainActivity.class));
                            finish();
                            // Toast.makeText(UserRegistration.this, "Name "+value, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });*/
                num = AfterRegistrationMainActivity.num();

                Intent usertype = new Intent(MainActivity.this,UserType.class);
                usertype.putExtra("photo",photo);
                startActivity(usertype);
                finish();

                //flag=1;
               // Toast.makeText(this, ""+flag+"....", Toast.LENGTH_SHORT).show();
            }
            else
            {
                startActivity(new Intent(this,AfterRegistrationMainActivity.class));
                //Toast.makeText(this, "ARMA", Toast.LENGTH_SHORT).show();
                finish();
            }

            /*mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);*/
        }
        else
        {
            //Toast.makeText(this, "no", Toast.LENGTH_SHORT).show();
            /*else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }*/
        }
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_in_button) {
            signIn();
        }
    }


    public int getStatusBarHeight()
    {
        int result = 0;
        int resourceId = getResources().getIdentifier("statur_bar_height","dimen","android");
        if(resourceId>0)
        {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

   /* public void login(View view)
    {
        startActivity(new Intent(MainActivity.this,UserType.class));
    }*/

    private boolean exit = false;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //finish();
        //System.exit(0);

        if(exit){
            Intent lastActivity = new Intent(Intent.ACTION_MAIN);
            lastActivity.addCategory(Intent.CATEGORY_HOME);
            lastActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            lastActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            lastActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(lastActivity);
            finish();
        }
        else
        {
            Toast.makeText(this, " Press back again to exit ", Toast.LENGTH_SHORT).show();
            exit = true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            },3*1000);
        }
    }
}
