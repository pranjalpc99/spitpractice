package com.tachyon.pranjalchaudhari.spit;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String text,classtxt,year,branch,time;

    public MyFirebaseMessagingService() {

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        AfterRegistrationMainActivity obj = new AfterRegistrationMainActivity();
        classtxt = obj.myclass();
        year = classtxt.substring(0,2);
        branch = classtxt.substring(2,classtxt.length());
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        sendNotification(remoteMessage.getNotification().getBody());
    }

    public void sendNotification(String messagebody)
    {
        Intent intent = new Intent(this,AfterRegistrationMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(this);
        notificationbuilder.setSmallIcon(R.drawable.spit_logo);
        notificationbuilder.setContentTitle("New Update");
        notificationbuilder.setContentText(messagebody);
        notificationbuilder.setAutoCancel(true);
        notificationbuilder.setSound(defaultsound);
        notificationbuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationbuilder.build());
    }
}
