package com.tachyon.pranjalchaudhari.spit;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    // Integer REQ_CAMERA=1,SELECT_FILE=0;
    String name, gender, year, branch, UID, personal, college, photo, type,SelectedGender,status;
    private EditText txtname, txtUID, txtcollegeEmailID;
    private RadioGroup radioGroup;
    private RadioButton maleRB, femaleRB;
    private Spinner spinnerClass, spinnerBranch;
    String[] stringClass, stringBranch;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    ImageView profeditback, profeditimg;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profeditback = findViewById(R.id.profeditback);
        profeditimg = findViewById(R.id.profeditImage);

        status = getIntent().getExtras().getString("status");
        name = getIntent().getExtras().getString("name");
        gender = getIntent().getExtras().getString("gender");
        year = getIntent().getExtras().getString("year");
        branch = getIntent().getExtras().getString("branch");
        UID = getIntent().getExtras().getString("UID");
        personal = getIntent().getExtras().getString("personalEmailId");
        college = getIntent().getExtras().getString("collegeEmailId");
        type = getIntent().getExtras().getString("type");
        photo = getIntent().getExtras().getString("photo");

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorWhite));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        DrawableCompat.setTint(profeditback.getDrawable(), ContextCompat.getColor(this, R.color.blue500));

        Glide.with(EditProfileActivity.this).load(photo).apply(RequestOptions.circleCropTransform()).into(profeditimg);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        txtname = findViewById(R.id.txteditprofilename);
        txtUID = findViewById(R.id.txteditprofileUID);
        txtcollegeEmailID = findViewById(R.id.txteditprofilecollege);

        //ColorStateList colorStateList = ColorStateList.valueOf(getResources().getColor(R.color.blue500));
        //txtcollegeEmailID.setBackgroundTintList(colorStateList);

        txtname.setText(name);
        txtUID.setText(UID);
        txtcollegeEmailID.setText(college);

        maleRB = (RadioButton) findViewById(R.id.editmale_rb);
        femaleRB = (RadioButton) findViewById(R.id.editfemale_rb);
        radioGroup = (RadioGroup) findViewById(R.id.editradioGroup);

        if(gender.equals("Male"))
        {
            //maleRB.setEnabled(true);
            //femaleRB.setEnabled(false);
            //maleRB.setSelected(true);
            maleRB.toggle();
        }
        else
        {
            femaleRB.toggle();
            //femaleRB.setSelected(true);
            //maleRB.setEnabled(false);
            //femaleRB.setEnabled(true);
        }

        spinnerClass = (Spinner) findViewById(R.id.editidClass);
        spinnerBranch = (Spinner) findViewById(R.id.editidBranch);

        Resources resources = getResources();
        stringClass = resources.getStringArray(R.array.Class_String);
        stringBranch = resources.getStringArray(R.array.Branch_String);


        ArrayAdapter<String> spinnerArrayAdapterClass = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, stringClass) {

            @Override
            public boolean isEnabled(int position) {

                if (position == 0) {
                    return false;
                } else {
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

        switch (year) {
            case "FE":
                spinnerClass.setSelection(1);
                break;
            case "SE":
                spinnerClass.setSelection(2);
                break;
            case "TE":
                spinnerClass.setSelection(3);
                break;
            case "BE":
                spinnerClass.setSelection(4);
                break;
        }

        ArrayAdapter<String> spinnerArrayAdapterBranch = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, stringBranch) {

            @Override
            public boolean isEnabled(int position) {

                if (position == 0) {
                    return false;
                } else {
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

        switch (branch) {
            case "COMP":
                spinnerBranch.setSelection(1);
                break;
            case "IT":
                spinnerBranch.setSelection(2);
                break;
            case "EXTC":
                spinnerBranch.setSelection(3);
                break;
            case "ETRX":
                spinnerBranch.setSelection(4);
                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean saveStudentInfo()
    {

        String name = txtname.getText().toString();
        //profilename = name;
        String UID = txtUID.getText().toString();
        String collegeEmailId = txtcollegeEmailID.getText().toString();
        year = spinnerClass.getSelectedItem().toString().trim();
        branch = spinnerBranch.getSelectedItem().toString().trim();
        String personalEmailID = firebaseAuth.getCurrentUser().getEmail().toString().trim();

        if(check())
        {
            StudentInfo studentInfo = new StudentInfo(name,collegeEmailId,gender,personalEmailID,type,photo);
            StudentInfo2 studentInfo2 = new StudentInfo2(status,name,collegeEmailId,gender,personalEmailID,UID,branch,year,type,photo);

            databaseReference.child("Student").child(year).child(branch).child(UID).setValue(studentInfo);

            String personalemailwithoutID = personalEmailID.substring(0,personalEmailID.indexOf('@'));

            databaseReference.child(personalemailwithoutID).setValue(studentInfo2);
            return true;

        }

        return false;

        //Intent afterRegistrationMainActivity = new Intent(StudentRegister.this,AfterRegistrationMainActivity.class);
        //afterRegistrationMainActivity.putExtra("name",name);
        //startActivity(afterRegistrationMainActivity);

    }

    public boolean check()
    {
        if(TextUtils.isEmpty(txtname.getText().toString()))
        {
            txtname.setError("Enter Name");
        }
        else if(TextUtils.isEmpty(txtUID.getText().toString()))
        {
            txtUID.setError("Enter UID");
        }
        else if(txtUID.getText().length()!=10)
        {
            txtUID.setError("Enter Correct UID");
        }
        else if(TextUtils.isEmpty(txtcollegeEmailID.getText().toString()))
        {
            txtcollegeEmailID.setError("Enter College Email ID");
        }
        else if(!txtcollegeEmailID.getText().toString().contains("@spit.ac.in"))
        {
            txtcollegeEmailID.setError("Enter correct College Email ID");
        }
        else
        {
            return true;
        }

        return false;
    }

    public void editselectGender(View view)
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




       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }*/
  /*  private void selectImage()
    {
        final String [] items = {"Camera","Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
        builder.setTitle("Add image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(items[which].equals("Camera"))
                {
                    Intent intent = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
                    startActivityForResult(intent,REQ_CAMERA);
                }
                else if(items[which].equals("Gallery"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent.createChooser(intent,"Select File"),SELECT_FILE);
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "in", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "req ok", Toast.LENGTH_SHORT).show();
            if(requestCode==REQ_CAMERA)
            {
                Bundle bundle = data.getExtras();
                final Drawable drawable = (Drawable) bundle.get("data");
                profeditimg.setImageDrawable(drawable);
            }
            else if(requestCode==SELECT_FILE)
            {
                Uri selectImgUri = data.getData();
                String pic = selectImgUri.toString();
                //profeditimg.setImageURI(selectImgUri);
                Toast.makeText(this, pic, Toast.LENGTH_SHORT).show();
                Glide.with(EditProfileActivity.this).load(pic).apply(RequestOptions.circleCropTransform()).into(profeditimg);
            }
        }*/

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void profeditsave(View view) {
        if(saveStudentInfo())
        {
            Intent intent = new Intent(EditProfileActivity.this,ProfileActivity.class);
            intent.putExtra("status",status);
            intent.putExtra("name",name);
            intent.putExtra("gender",gender);
            intent.putExtra("year",year);
            intent.putExtra("branch",branch);
            intent.putExtra("UID",UID);
            intent.putExtra("personalEmailId",personal);
            intent.putExtra("collegeEmailId",college);
            intent.putExtra("type",type);
            intent.putExtra("photo",photo);
            startActivity(intent);
            finish();
        }


    }

    public void profeditback(View view) {
        onBackPressed();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
