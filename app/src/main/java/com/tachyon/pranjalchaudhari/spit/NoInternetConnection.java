package com.tachyon.pranjalchaudhari.spit;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NoInternetConnection extends AppCompatActivity {

    boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorWhite));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        checkConnection();

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkConnection();
    }

    public void checkConnection()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        {
            connected = true;
            startActivity(new Intent(NoInternetConnection.this,AfterRegistrationMainActivity.class));
        }
        else
        {
            connected = false;

        }
    }

    @Override
    public void onBackPressed() {
        checkConnection();
    }

    public void retry(View view) {
        checkConnection();
    }
}
