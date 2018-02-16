package com.example.thilina.e1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.logging.Level;

public class SelectActivity extends AppCompatActivity {
    ImageButton btnDog,btnDaily;
    Button btnLogout;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);


        prefs=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor=prefs.edit();


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
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                editor.putBoolean("isLoggedIn",false);
                editor.commit();
                Intent i=new Intent(SelectActivity.this,LoginActivity.class);
                startActivity(i);

            }
        });




         }

}
