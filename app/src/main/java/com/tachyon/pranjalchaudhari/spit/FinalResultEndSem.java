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

import java.util.List;

public class FinalResultEndSem extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewUGFE,listViewUGSE,listViewUGTE,listViewUGBE;

    String[] resultUGFE = {"FE Sem II COMP May 2018 Gazette",
            "FE Sem II IT May 2018 Gazette",
            "FE Sem II EXTC May 2018 Gazette",
            "FE Sem II ETRX May 2018 Gazette"};

    String[] resultUGSE = {"SE Sem IV COMP May 2018 Gazette",
            "SE Sem IV IT May 2018 Gazette",
            "SE Sem IV EXTC May 2018 Gazette",
            "SE Sem IV ETRX May 2018 Gazette"};

    String[] resultUGTE = {"TE Sem VI COMP Gr I May 2018 Gazette",
            "TE Sem VI COMP Gr II May 2018 Gazette",
            "TE Sem VI IT May 2018 Gazette",
            "TE Sem VI EXTC May 2018 Gazette",
            "TE Sem VI ETRX May 2018 Gazette"};

    String[] resultUGBE = {"BE Sem VIII COMP Gr I May 2018 Gazette",
            "BE Sem VIII COMP Gr II May 2018 Gazette",
            "BE Sem VIII COMP Gr III May 2018 Gazette",
            "BE Sem VIII IT Gr I May 2018 Gazette",
            "BE Sem VIII IT Gr II May 2018 Gazette",
            "BE Sem VIII EXTC Gr I May 2018 Gazette",
            "BE Sem VIII EXTC Gr II May 2018 Gazette",
            "BE Sem VIII ETRX Gr I May 2018 Gazette",
            "BE Sem VIII ETRX Gr II May 2018 Gazette",
            "BE Sem VIII ETRX Gr III May 2018 Gazette"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result_end_sem);

        toolbar =  (Toolbar) findViewById(R.id.toolBarfinalresult);
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listViewUGFE = (ListView) findViewById(R.id.listViewUGFE);

        ArrayAdapter<String> adapterUGFE = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultUGFE);
        listViewUGFE.setAdapter(adapterUGFE);

        listViewUGFE.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToUrlFE(position+1);
            }
        });

        listViewUGSE = (ListView) findViewById(R.id.listViewUGSE);

        ArrayAdapter<String> adapterUGSE = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultUGSE);
        listViewUGSE.setAdapter(adapterUGSE);

        listViewUGSE.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToUrlSE(position+1);
            }
        });
        listViewUGTE = (ListView) findViewById(R.id.listViewUGTE);

        ArrayAdapter<String> adapterUGTE = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultUGTE);
        listViewUGTE.setAdapter(adapterUGTE);

        listViewUGTE.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToUrlTE(position+1);
            }
        });

        listViewUGBE = (ListView) findViewById(R.id.listViewUGBE);

        ArrayAdapter<String> adapterUGBE = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,resultUGBE);
        listViewUGBE.setAdapter(adapterUGBE);

        listViewUGBE.setOnItemClickListener(new AdapterView.OnItemClickListener(){
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
            case 1: Uri uri1= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/FE-Sem-II-COMP-May-2018-Gazette.pdf");
                    Intent intent1 = new Intent(Intent.ACTION_VIEW,uri1);
                    startActivity(intent1);
                    break;
            case 2: Uri uri2= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/FE-Sem-II-IT-May-2018-Gazette.pdf");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent2);
                break;
            case 3: Uri uri3= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/FE-Sem-II-EXTC-May-2018-Gazette.pdf");
                Intent intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent3);
                break;
            case 4: Uri uri4= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/FE-Sem-II-ETRX-May-2018.pdf");
                Intent intent4 = new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent4);
                break;
        }
    }

    public void goToUrlSE(int i)
    {
        switch (i)
        {
            case 1: Uri uri1= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/SE-Sem-IV-COMP-May-2018-Gazette.pdf");
                Intent intent1 = new Intent(Intent.ACTION_VIEW,uri1);
                startActivity(intent1);
                break;
            case 2: Uri uri2= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/SE-Sem-IV-IT-May-2018-Gazette.pdf");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent2);
                break;
            case 3: Uri uri3= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/SE-Sem-IV-EXTC-May-2018-Gazette.pdf");
                Intent intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent3);
                break;
            case 4: Uri uri4= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/SE-Sem-IV-ETRX-May-2018-Gazette.pdf");
                Intent intent4 = new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent4);
                break;
        }
    }

    public void goToUrlTE(int i)
    {
        switch (i)
        {
            case 1: Uri uri1= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/TE-Sem-VI-COMP-Gr-I-May-2018-Gazette.pdf");
                Intent intent1 = new Intent(Intent.ACTION_VIEW,uri1);
                startActivity(intent1);
                break;
            case 2: Uri uri2= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/TE-Sem-VI-COMP-Gr-II-May-2018-Gazette.pdf");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent2);
                break;
            case 3: Uri uri3= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/TE-Sem-VI-IT-May-2018-Gazette.pdf");
                Intent intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent3);
                break;
            case 4: Uri uri4= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/TE-Sem-VI-EXTC-May-2018-Gazette.pdf");
                Intent intent4 = new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent4);
                break;
            case 5: Uri uri5= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/TE-Sem-VI-ETRX-May-2018-Gazette.pdf");
                Intent intent5 = new Intent(Intent.ACTION_VIEW,uri5);
                startActivity(intent5);
                break;
        }
    }

    public void goToUrlBE(int i)
    {
        switch (i)
        {
            case 1: Uri uri1= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/BE-COMP-Sem-VIII-Gr-I-May-2018-Gazette.pdf");
                Intent intent1 = new Intent(Intent.ACTION_VIEW,uri1);
                startActivity(intent1);
                break;
            case 2: Uri uri2= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/BE-COMP-Sem-VIII-Gr-II-May-2018-Gazette.pdf");
                Intent intent2 = new Intent(Intent.ACTION_VIEW,uri2);
                startActivity(intent2);
                break;
            case 3: Uri uri3= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/BE-COMP-Sem-VIII-Gr-III-May-2018-Gazette.pdf");
                Intent intent3 = new Intent(Intent.ACTION_VIEW,uri3);
                startActivity(intent3);
                break;
            case 4: Uri uri4= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/BE-IT-Sem-VIII-Gr-I-May-2018-Gazette.pdf");
                Intent intent4 = new Intent(Intent.ACTION_VIEW,uri4);
                startActivity(intent4);
                break;
            case 5: Uri uri5= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/BE-IT-Sem-VIII-Gr-II-May-2018-Gazette.pdff");
                Intent intent5 = new Intent(Intent.ACTION_VIEW,uri5);
                startActivity(intent5);
                break;
            case 6: Uri uri6= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/BE-EXTC-Sem-VIII-Gr-I-May-2018-Gazette.pdf");
                Intent intent6 = new Intent(Intent.ACTION_VIEW,uri6);
                startActivity(intent6);
                break;
            case 7: Uri uri7= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/BE-EXTC-Sem-VIII-Gr-II-May-2018-Gazette.pdf");
                Intent intent7 = new Intent(Intent.ACTION_VIEW,uri7);
                startActivity(intent7);
                break;
            case 8: Uri uri8= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/BE-ETRX-Sem-VIII-Gr-I-May-2018-Gazette.pdf");
                Intent intent8 = new Intent(Intent.ACTION_VIEW,uri8);
                startActivity(intent8);
                break;
            case 9: Uri uri9= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/BE-ETRX-Sem-VIII-Gr-II-May-2018-Gazette.pdf");
                Intent intent9 = new Intent(Intent.ACTION_VIEW,uri9);
                startActivity(intent9);
                break;
            case 10: Uri uri10= Uri.parse("http://www.spit.ac.in/wp-content/uploads/2018/06/BE-ETRX-Sem-VIII-Gr-III-May-2018-Gazette.pdf");
                Intent intent10 = new Intent(Intent.ACTION_VIEW,uri10);
                startActivity(intent10);
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
