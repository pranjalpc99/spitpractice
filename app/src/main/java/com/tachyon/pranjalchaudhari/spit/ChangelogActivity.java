package com.tachyon.pranjalchaudhari.spit;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChangelogActivity extends AppCompatActivity {

    String name,photo,gender,year,branch,UID,personal,college,type;
    ImageView changelogback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelog);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorWhite));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        changelogback = findViewById(R.id.changelogback);
        DrawableCompat.setTint(changelogback.getDrawable(), ContextCompat.getColor(this, R.color.black));

        name = getIntent().getExtras().getString("name");
        photo = getIntent().getExtras().getString("photo");
        gender = getIntent().getExtras().getString("gender");
        year = getIntent().getExtras().getString("year");
        branch = getIntent().getExtras().getString("branch");
        UID = getIntent().getExtras().getString("UID");
        personal = getIntent().getExtras().getString("personalEmailId");
        college = getIntent().getExtras().getString("collegeEmailId");
        type = getIntent().getExtras().getString("type");
    }

    public void changelogback(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        final Intent changelog = new Intent(ChangelogActivity.this,AboutAppActivity.class);
        changelog.putExtra("name", name);
        changelog.putExtra("collegeEmailId",college);
        changelog.putExtra("gender",gender);
        changelog.putExtra("personalEmailId",personal);
        changelog.putExtra("UID",UID);
        changelog.putExtra("branch",branch);
        changelog.putExtra("year",year);
        changelog.putExtra("type",type);
        changelog.putExtra("photo", photo);
        startActivity(changelog);
        finish();
    }
}
