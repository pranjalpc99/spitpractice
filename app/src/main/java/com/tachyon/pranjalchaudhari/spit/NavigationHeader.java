package com.tachyon.pranjalchaudhari.spit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NavigationHeader extends AppCompatActivity{

    TextView profiletext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_header);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);


        profiletext = (TextView) headerView.findViewById(R.id.profileText);

        String name = getIntent().getExtras().getString("name");
        profiletext.setText(name);

        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this,AfterRegistrationMainActivity.class));
        finish();

    }
}
