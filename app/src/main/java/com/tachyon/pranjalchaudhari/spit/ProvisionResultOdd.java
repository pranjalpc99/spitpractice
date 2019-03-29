package com.tachyon.pranjalchaudhari.spit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProvisionResultOdd extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewFE,listViewSE,listViewTE,listViewBE;

    String[] resultFE = {"PROV. ODD SEM Re-exam FE COMP",
            "PROV. ODD SEM Re-exam FE IT",
            "PROV. ODD SEM Re-exam FE EXTC",
            "PROV. ODD SEM Re-exam FE ETRX"};

    String[] resultSE = {"PROV. ODD SEM Re-exam SE COMP",
            "PROV. ODD SEM Re-exam SE IT",
            "PROV. ODD SEM Re-exam SE EXTC",
            "PROV. ODD SEM Re-exam SE ETRX"};

    String[] resultTE = {"PROV. ODD SEM Re-exam TE COMP",
            "PROV. ODD SEM Re-exam TE IT",
            "PROV. ODD SEM Re-exam TE EXTC",
            "PROV. ODD SEM Re-exam TE ETRX"};

    String[] resultBE = {"PROV. ODD SEM Re-exam BE COMP",
            "PROV. ODD SEM Re-exam BE IT",
            "PROV. ODD SEM Re-exam BE EXTC",
            "PROV. ODD SEM Re-exam BE ETRX"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provision_result_odd);

        toolbar =  (Toolbar) findViewById(R.id.toolBarprovisionalresultodd);
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listViewFE = (ListView) findViewById(R.id.listViewFE);

        ArrayAdapter<String> adapterUGFE = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultFE);
        listViewFE.setAdapter(adapterUGFE);

        listViewFE.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToUrlFE(position+1);
            }
        });

        listViewSE = (ListView) findViewById(R.id.listViewSE);

        ArrayAdapter<String> adapterUGSE = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultSE);
        listViewSE.setAdapter(adapterUGSE);

        listViewSE.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToUrlSE(position+1);
            }
        });
        listViewTE = (ListView) findViewById(R.id.listViewTE);

        ArrayAdapter<String> adapterUGTE = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultTE);
        listViewTE.setAdapter(adapterUGTE);

        listViewTE.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToUrlTE(position+1);
            }
        });

        listViewBE = (ListView) findViewById(R.id.listViewBE);

        ArrayAdapter<String> adapterUGBE = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultBE);
        listViewBE.setAdapter(adapterUGBE);

        listViewBE.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToUrlBE(position+1);
            }
        });

    }

    public void goToUrlFE(int i)
    {
        switch (i)
        {
            case 1: Uri uri1= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-FE-COM-xlsx.pdf");
                Intent intent1 = new Intent(Intent.ACTION_VIEW,uri1);
                startActivity(intent1);
                break;
            case 2: Uri uri2= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-FE-IT-xlsx.pdf");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent2);
                break;
            case 3: Uri uri3= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-FE-EXTC-xlsx.pdf");
                Intent intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent3);
                break;
            case 4: Uri uri4= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-FE-ETRX-xlsx");
                Intent intent4 = new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent4);
                break;
        }
    }

    public void goToUrlSE(int i)
    {
        switch (i)
        {
            case 1: Uri uri1= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-SE-COM-xlsx.pdf");
                Intent intent1 = new Intent(Intent.ACTION_VIEW,uri1);
                startActivity(intent1);
                break;
            case 2: Uri uri2= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-SE-IT-xlsx.pdf");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent2);
                break;
            case 3: Uri uri3= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-SE-EXTC-xlsx.pdf");
                Intent intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent3);
                break;
            case 4: Uri uri4= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-SE-ETRX-xlsx");
                Intent intent4 = new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent4);
                break;
        }
    }

    public void goToUrlTE(int i)
    {
        switch (i)
        {
            case 1: Uri uri1= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-TE-COM-xlsx.pdf");
                Intent intent1 = new Intent(Intent.ACTION_VIEW,uri1);
                startActivity(intent1);
                break;
            case 2: Uri uri2= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-TE-IT-xlsx.pdf");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent2);
                break;
            case 3: Uri uri3= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-TE-EXTC-xlsx.pdf");
                Intent intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent3);
                break;
            case 4: Uri uri4= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-TE-ETRX-xlsx");
                Intent intent4 = new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent4);
                break;
        }
    }
    public void goToUrlBE(int i)
    {
        switch (i)
        {
            case 1: Uri uri1= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-BE-COM-xlsx.pdf");
                Intent intent1 = new Intent(Intent.ACTION_VIEW,uri1);
                startActivity(intent1);
                break;
            case 2: Uri uri2= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-BE-IT-xlsx.pdf");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent2);
                break;
            case 3: Uri uri3= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-BE-EXTC-xlsx.pdf");
                Intent intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent3);
                break;
            case 4: Uri uri4= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/PROV.-ODD-SEM-Re-exam-BE-ETRX-xlsx");
                Intent intent4 = new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent4);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home: onBackPressed();
                return true;
            default: return super.onOptionsItemSelected(item);
        }


    }
}

