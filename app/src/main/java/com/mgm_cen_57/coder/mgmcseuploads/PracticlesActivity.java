package com.mgm_cen_57.coder.mgmcseuploads;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class PracticlesActivity extends AppCompatActivity {


    private Button adbms,plVI,cn,ip,lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicles);


        adbms=findViewById(R.id.btn_adbms);
        plVI=findViewById(R.id.btn_pl_VI);
        cn=findViewById(R.id.btn_CN);
        ip=findViewById(R.id.btn_IP);
        lm=findViewById(R.id.btn_labM);




        adbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(PracticlesActivity.this,PracticlesUpload.class);
                i.putExtra("Root","BE_CSE");
                i.putExtra("sub1","Practicles");
                i.putExtra("sub2","adbms");
                i.putExtra("pos","0");
                startActivity(i);

            }
        });
        plVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(PracticlesActivity.this,PracticlesUpload.class);
                i.putExtra("Root","BE_CSE");
                i.putExtra("sub1","Practicles");
                i.putExtra("sub2","plVI");
                i.putExtra("pos","1");
                startActivity(i);

            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(PracticlesActivity.this,PracticlesUpload.class);
                i.putExtra("Root","BE_CSE");
                i.putExtra("sub1","Practicles");
                i.putExtra("sub2","cn");
                i.putExtra("pos","2");
                startActivity(i);

            }
        });
        ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PracticlesActivity.this,PracticlesUpload.class);
                i.putExtra("Root","BE_CSE");
                i.putExtra("sub1","Practicles");
                i.putExtra("sub2","ip");
                i.putExtra("pos","3");
                startActivity(i);

            }
        });
        lm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PracticlesActivity.this,PracticlesUpload.class);
                i.putExtra("Root","BE_CSE");
                i.putExtra("sub1","Practicles");
                i.putExtra("sub2","Labm");
                i.putExtra("pos","4");
                startActivity(i);
            }
        });
    }


}
