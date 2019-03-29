package com.tachyon.pranjalchaudhari.spit;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MarksActivity extends AppCompatActivity {
    String name,photo,gender,year,branch,UID,personal,college,type;
    Dialog dialog;
    ImageView closeDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks);

        name = getIntent().getExtras().getString("name");
        photo = getIntent().getExtras().getString("photo");
        gender = getIntent().getExtras().getString("gender");
        year = getIntent().getExtras().getString("year");
        branch = getIntent().getExtras().getString("branch");
        UID = getIntent().getExtras().getString("UID");
        personal = getIntent().getExtras().getString("personalEmailId");
        college = getIntent().getExtras().getString("collegeEmailId");
        type = getIntent().getExtras().getString("type");

        dialog = new Dialog(this);

        dialog.setContentView(R.layout.marks_dialog);
        closeDialog = dialog.findViewById(R.id.marks_close_dialog);

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onBackPressed();
            }
        });
        dialog.show();
    }

    public void marksback(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MarksActivity.this,AfterRegistrationMainActivity.class));
        finish();
    }
}
