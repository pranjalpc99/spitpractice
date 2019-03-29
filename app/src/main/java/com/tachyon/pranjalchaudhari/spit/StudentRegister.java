package com.tachyon.pranjalchaudhari.spit;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

//import static com.tachyon.pranjalchaudhari.spit.R.id.RegisterActionBar;

public class StudentRegister extends AppCompatActivity {

    Toolbar toolbar;
    private Button btnNext,btnBack,btnRegister;

    private static String year,branch,SelectedGender,gender;
    private RadioGroup radioGroup;
    private RadioButton maleRB,femaleRB;
    private Spinner spinnerClass,spinnerBranch;
    String[] stringClass,stringBranch;

    private TextInputLayout txtname,txtUID,txtcollegeEmailID;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private static String profilename;
    private String photo;
    int go=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_student_register);

        toolbar = (Toolbar) findViewById(R.id.RegisterActionBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register Student");

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        photo = getIntent().getExtras().getString("photo");

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        txtname = findViewById(R.id.text_input_name);
        txtUID = findViewById(R.id.text_input_UID);
        txtcollegeEmailID = findViewById(R.id.text_input_collegeEmail);

        btnRegister = (Button) findViewById(R.id.registerButton);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                //show();
                saveStudentInfo();

                if(go==1)
                {
                    startActivity(new Intent(StudentRegister.this,AfterRegistrationMainActivity.class));
                    finish();
                }

            }
        });

       // year = SecondStudentSlideLayout.getYear();
       // branch = SecondStudentSlideLayout.getBranch();

        maleRB = (RadioButton) findViewById(R.id.male_rb);
        femaleRB = (RadioButton) findViewById(R.id.female_rb);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
       // Toast.makeText(this, year, Toast.LENGTH_SHORT).show();
        spinnerClass = (Spinner) findViewById(R.id.idClass);
        spinnerBranch = (Spinner) findViewById(R.id.idBranch);

        Resources resources = getResources();
        stringClass = resources.getStringArray(R.array.Class_String);
        stringBranch = resources.getStringArray(R.array.Branch_String);

        ArrayAdapter <String> spinnerArrayAdapterClass = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,stringClass){

            @Override
            public boolean isEnabled(int position) {

                if(position==0)
                {
                    return  false;
                }
                else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerClass.setAdapter(spinnerArrayAdapterClass);

        ArrayAdapter <String> spinnerArrayAdapterBranch = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,stringBranch){

            @Override
            public boolean isEnabled(int position) {

                if(position==0)
                {
                    return  false;
                }
                else {
                    return true;
                }
            }




            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };

        spinnerBranch.setAdapter(spinnerArrayAdapterBranch);

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveStudentInfo()
    {
        String type = "Student";
       /* Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            photo = bundle.getString("photo");
        }*/


        //Toast.makeText(this, photo, Toast.LENGTH_SHORT).show();
        String name = txtname.getEditText().getText().toString();
        profilename = name;
        String UID = txtUID.getEditText().getText().toString();
        String collegeEmailId = txtcollegeEmailID.getEditText().getText().toString();
        year = spinnerClass.getSelectedItem().toString().trim();
        branch = spinnerBranch.getSelectedItem().toString().trim();
        String personalEmailID = firebaseAuth.getCurrentUser().getEmail().toString().trim();
        gender="";
        selectGender();

        if(checkDetails(name,UID,gender,collegeEmailId,year,branch))
        {
            String Registered = "yes";
            StudentInfo studentInfo = new StudentInfo(name,collegeEmailId,gender,personalEmailID,type,photo);
            StudentInfo2 studentInfo2 = new StudentInfo2(Registered,name,collegeEmailId,gender,personalEmailID,UID,branch,year,type,photo);

            databaseReference.child("Student").child(year).child(branch).child(UID).setValue(studentInfo);

            String personalemailwithoutID = personalEmailID.substring(0,personalEmailID.indexOf('@'));
            personalemailwithoutID = personalemailwithoutID.replaceAll("[^a-zA-Z0-9]", "");

            databaseReference.child(personalemailwithoutID).setValue(studentInfo2);

            go=1;
        }



        //Intent afterRegistrationMainActivity = new Intent(StudentRegister.this,AfterRegistrationMainActivity.class);
        //afterRegistrationMainActivity.putExtra("name",name);
        //startActivity(afterRegistrationMainActivity);

    }

    public boolean checkDetails(String cname,String cUID,String cgender,String ccollegeEmailID,String cyear,String cbranch)
    {
        if(cname.isEmpty())
        {
            txtname.setError("Enter Name");
        }
        else if(cUID.isEmpty())
        {
            txtUID.setError("Enter UID");
            txtname.setError(null);
            txtname.setErrorEnabled(false);
        }
        else if (cUID.length()!=10)
        {
            txtUID.setError("Enter correct UID");
            txtname.setError(null);
            txtname.setErrorEnabled(false);
        }
        else if(!maleRB.isChecked() && !femaleRB.isChecked())
        {
            maleRB.setError("Select gender");
            femaleRB.setError("Select gender");
            txtname.setError(null);
            txtname.setErrorEnabled(false);
            txtUID.setError(null);
            txtUID.setErrorEnabled(false);
        }
        else if(ccollegeEmailID.isEmpty())
        {
            txtcollegeEmailID.setError("Enter College Email Id");
            txtname.setError(null);
            txtname.setErrorEnabled(false);
            txtUID.setError(null);
            txtUID.setErrorEnabled(false);
            maleRB.setError(null);
            femaleRB.setError(null);
        }
        else if(!ccollegeEmailID.contains("@spit.ac.in"))
        {
            txtcollegeEmailID.setError("Enter correct College Email ID");
            txtname.setError(null);
            txtname.setErrorEnabled(false);
            txtUID.setError(null);
            txtUID.setErrorEnabled(false);
            maleRB.setError(null);
            femaleRB.setError(null);
        }
        else if(spinnerClass.getSelectedItemPosition()==0)
        {
            ((TextView)spinnerClass.getSelectedView()).setError("Select Year");
            txtname.setError(null);
            txtname.setErrorEnabled(false);
            txtUID.setError(null);
            txtUID.setErrorEnabled(false);
            maleRB.setError(null);
            femaleRB.setError(null);
            txtcollegeEmailID.setError(null);
            txtcollegeEmailID.setErrorEnabled(false);

        }
        else if(spinnerBranch.getSelectedItemPosition()==0)
        {
            ((TextView)spinnerBranch.getSelectedView()).setError("Select Branch");
            txtname.setError(null);
            txtname.setErrorEnabled(false);
            txtUID.setError(null);
            txtUID.setErrorEnabled(false);
            maleRB.setError(null);
            femaleRB.setError(null);
            txtcollegeEmailID.setError(null);
            txtcollegeEmailID.setErrorEnabled(false);
        }
        else
        {
            return true;
        }

        return false;
    }


    public static String profilename()
    {
        return profilename;
    }


    public static String profileyear()
    {
        return year;
    }

    public void selectGender()
    {

        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.male_rb: SelectedGender = "Male";
                        Toast.makeText(UserRegistration.this, SelectedGender+"1111", Toast.LENGTH_SHORT).show();
                                        break;
                    case R.id.female_rb: SelectedGender="Female";
                                        break;
                }
            }
        });*/

        if(maleRB.isChecked())
        {
            SelectedGender="Male";
        }
        else if(femaleRB.isChecked())
        {
            SelectedGender="Female";
        }

        gender = SelectedGender;


    }




    public void show()
    {
        Toast.makeText(this,gender+" "+year+" "+branch, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void back(View view)
    {
        onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent lastActivity;
        lastActivity= new Intent(StudentRegister.this,UserType.class);
        lastActivity.putExtra("photo",photo);
        lastActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        lastActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        lastActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(lastActivity);

    }
}
