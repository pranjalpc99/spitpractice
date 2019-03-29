package com.tachyon.pranjalchaudhari.spit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.RemoteMessage;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddClassNotificationActivity extends AppCompatActivity {
    TextInputLayout notificationtext;
    String text,classtxt,sender,year,branch,time,currentDateTimeString;
    TimePicker timePicker;
    TextView date;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    int count;
    ImageView addback,attach,send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_notification);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blueVibrantDark));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        addback = findViewById(R.id.addclassnotificationback);
        attach = findViewById(R.id.addAttachments);
        send = findViewById(R.id.sendNotification);

        DrawableCompat.setTint(addback.getDrawable(), ContextCompat.getColor(this,R.color.colorWhite));
        DrawableCompat.setTint(attach.getDrawable(), ContextCompat.getColor(this,R.color.colorWhite));
        DrawableCompat.setTint(send.getDrawable(), ContextCompat.getColor(this,R.color.colorWhite));

        AfterRegistrationMainActivity obj = new AfterRegistrationMainActivity();
        classtxt = obj.myclass();
        year = classtxt.substring(0,2);
        branch = classtxt.substring(2,classtxt.length());
        sender = obj.myname();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notification").child(year).child(branch).child("counter");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<String> Userlist = new ArrayList<String >();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Userlist.add(String.valueOf(ds.getValue()));
                }
                //Toast.makeText(AddClassNotificationActivity.this, Userlist.get(0), Toast.LENGTH_SHORT).show();
                count = Integer.parseInt(Userlist.get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        date = findViewById(R.id.texttime);

        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

       // Toast.makeText(AddClassNotificationActivity.this, classtxt, Toast.LENGTH_SHORT).show();

        notificationtext = findViewById(R.id.text_input_class_notification);
    }

    public void addclassnotificationback(View view) {
        onBackPressed();
    }

    public void addAttachments(View view) {
    }

    public void sendNotification(View view) {

        text = year+branch+"#"+notificationtext.getEditText().getText().toString();

        SendNotificationHelper sendNotificationHelper = new SendNotificationHelper(sender,currentDateTimeString,text,null);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        int newcount = count + 1;

        String countkey = ""+newcount;

        databaseReference.child("Notification").child(year).child(branch).child(countkey).setValue(sendNotificationHelper);
        databaseReference.child("Notification").child(year).child(branch).child("counter").child("count").setValue(newcount);

        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddClassNotificationActivity.this,AfterRegistrationMainActivity.class));
        finish();
    }
}
