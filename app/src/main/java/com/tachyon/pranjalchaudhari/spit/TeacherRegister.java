package com.tachyon.pranjalchaudhari.spit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

;import com.tachyon.pranjalchaudhari.spit.databinding.*;
public class TeacherRegister extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        toolbar = (Toolbar) findViewById(R.id.RegisterActionBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register Teacher");
    }

    public void back(View view)
    {
        onBackPressed();
    }

    public void register(View view)
    {
        startActivity(new Intent(TeacherRegister.this,AfterRegistrationMainActivity.class));
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent lastActivity;
        lastActivity= new Intent(TeacherRegister.this,UserType.class);
        lastActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        lastActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        lastActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(lastActivity);

    }
}
