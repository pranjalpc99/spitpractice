package com.tachyon.pranjalchaudhari.spit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class BrainhackPython extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brainhack_python);

        toolbar =  (Toolbar) findViewById(R.id.toolBarBrainhack);
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setTitle("");
    }

    public void brochurebrainhack(View view)
    {
        Uri uri = Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/brain-hack_broucher.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    public void registerbrainhack(View view)
    {
        Uri uri = Uri.parse("https://goo.gl/forms/zC57jo859Rk9r2AE2");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home: onBackPressed();
                return true;
            default: return super.onOptionsItemSelected(item);
        }


    }
}
