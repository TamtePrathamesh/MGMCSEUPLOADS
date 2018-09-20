package com.mgm_cen_57.coder.mgmcseuploads;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button books,tt,prac,qb,sqb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        books=findViewById(R.id.buttonBooks);
        tt=findViewById(R.id.buttonTimeTable);
        prac=findViewById(R.id.buttonPracticlesUploads);
        qb=findViewById(R.id.buttonQuestionBank);
        sqb=findViewById(R.id.buttonSolvedQB);

        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ImagesUpload.class));
            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(MainActivity.this,BooksAndSyllabus.class);
                i.putExtra("Root","BE_CSE");
                i.putExtra("sub","BOOKS");

                startActivity(i);
            }
        });

        prac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,PracticlesActivity.class));

            }
        });

        qb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,BooksAndSyllabus.class);
                i.putExtra("Root","BE_CSE");
                i.putExtra("sub","Question Bank");

                startActivity(i);

            }
        });

        sqb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,BooksAndSyllabus.class);
                i.putExtra("Root","BE_CSE");
                i.putExtra("sub","Solved Question Bank");

                startActivity(i);
            }
        });



    }
}
