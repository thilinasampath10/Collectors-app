package com.example.thilina.e1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SelectActivity extends AppCompatActivity {
    ImageButton btnDog,btnDaily;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        btnDog = (ImageButton) findViewById(R.id.btnDog);
        btnDaily =(ImageButton) findViewById(R.id.btnDaily);

        btnDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent i=new Intent(SelectActivity.this,DogMapActivity.class);
                startActivity(i);

            }

            });
         btnDaily.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(android.view.View v) {
                 Intent i = new Intent(SelectActivity.this, MapActivity.class);
                 startActivity(i);

             }

         });
         }

}
