package com.tachyon.pranjalchaudhari.spit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.admin.spitpractice.databinding.ActivityUserTypeBinding;
import com.tachyon.pranjalchaudhari.spit.databinding.*;

public class UserType extends AppCompatActivity {

    Toolbar toolbar;

    View viewTeacher,viewStudent;
    ImageView teacherImg,studentImg;
    TextView studentText,teacherText;
    protected AlphaAnimation fadeOut = new AlphaAnimation(1.0f,0.0f);
    static int teacherFlag;

    private ActivityUserTypeBinding userTypeBinding;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        toolbar = (Toolbar) findViewById(R.id.userTypeToolBar);
        setSupportActionBar(toolbar);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userTypeBinding = DataBindingUtil.setContentView(this,R.layout.activity_user_type);
        viewTeacher = (View) findViewById(R.id.revealTeacherView);
        viewStudent = (View) findViewById(R.id.revealStudentView);
        teacherImg = (ImageView) findViewById(R.id.imageViewTeacher);
        studentImg = (ImageView) findViewById(R.id.imageViewStudent);
        teacherText = (TextView) findViewById(R.id.id_teacher_text);
        studentText = (TextView) findViewById(R.id.id_student_text);


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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void teacherReveal(View view)
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Access Denied");
        builder.setMessage("Contact your administrator ");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                builder.create().dismiss();
            }
        });
        builder.create().show();

        /*(NEED FROM)Animation student = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
      //  Animation studentTextanim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.);
        studentImg.startAnimation(student);
        studentText.startAnimation(fadeOut);
      //  studentText.startAnimation(studentTextanim);
        studentImg.setVisibility(View.INVISIBLE);
        studentText.setVisibility(View.INVISIBLE);

        int cx = viewTeacher.getWidth()/2;
        int cy = (teacherImg.getTop()+teacherImg.getBottom())/2;
        int radius = Math.max(viewTeacher.getWidth(),viewTeacher.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(viewTeacher,cx,cy,0,radius);
        viewTeacher.setVisibility(View.VISIBLE);
        animator.setDuration(350);

        animator.start();(TO)*/


    /*------    /*userTypeBinding.imageViewTeacher.setElevation(0f);
        userTypeBinding.revealTeacherView.setVisibility(View.VISIBLE);

        int x = userTypeBinding.revealTeacherView.getWidth();
        int y = userTypeBinding.revealTeacherView.getHeight();

        int startX = (int) (userTypeBinding.imageViewTeacher.getX());
        int startY = (int) (userTypeBinding.imageViewTeacher.getY());

        float radius = Math.max(x,y) + 1.2f;

        Animator reveal = ViewAnimationUtils.createCircularReveal(userTypeBinding.revealTeacherView,startX,startY,0,radius);
        reveal.setDuration(500);
        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finish();
            }
        });

        reveal.start();*/
       // teacherFlag=1;(NEED)
       // delayedStartNextTeacherActivity();(NEED)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void studentReveal(View view)
    {

        Animation teacher = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        //  Animation studentTextanim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.);
        teacherImg.startAnimation(teacher);
        teacherText.startAnimation(fadeOut);
        //  studentText.startAnimation(studentTextanim);
        teacherImg.setVisibility(View.INVISIBLE);
        teacherText.setVisibility(View.INVISIBLE);

        int cx = viewStudent.getWidth()/2;
        int cy = (studentImg.getTop()+studentImg.getBottom())/2;
       // int cx = userTypeBinding.imageViewStudent.getMeasuredWidth()/2;
       // int cy = userTypeBinding.imageViewStudent.getMeasuredHeight()/2;
        int radius = Math.max(viewStudent.getWidth(),viewStudent.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(viewStudent,cx,cy,0,radius);
        viewStudent.setVisibility(View.VISIBLE);
        animator.setDuration(350);

        animator.start();


        /*userTypeBinding.imageViewTeacher.setElevation(0f);
        userTypeBinding.revealTeacherView.setVisibility(View.VISIBLE);

        int x = userTypeBinding.revealTeacherView.getWidth();
        int y = userTypeBinding.revealTeacherView.getHeight();

        int startX = (int) (userTypeBinding.imageViewTeacher.getX());
        int startY = (int) (userTypeBinding.imageViewTeacher.getY());

        float radius = Math.max(x,y) + 1.2f;

        Animator reveal = ViewAnimationUtils.createCircularReveal(userTypeBinding.revealTeacherView,startX,startY,0,radius);
        reveal.setDuration(500);
        reveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finish();
            }
        });

        reveal.start();*/


        delayedStartNextStudentActivity();
    }

    public void delayedStartNextTeacherActivity()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(UserType.this,TeacherRegister.class));
            }
        },300);
        finishActivity();
    }

    public void delayedStartNextStudentActivity()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(UserType.this,StudentRegister.class);
                String photo = getIntent().getExtras().getString("photo");
                intent.putExtra("photo",photo);
               // Toast.makeText(UserType.this, photo, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        },300);
        finishActivity();

    }

    public void finishActivity()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                isDestroyed();

            }
        },300);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void back(View view)
    {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        Intent lastActivity;
        lastActivity= new Intent(UserType.this,MainActivity.class);
        lastActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        lastActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        lastActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(lastActivity);

    }
}


