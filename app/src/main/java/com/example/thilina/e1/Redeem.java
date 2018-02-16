package com.example.thilina.e1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Redeem extends AppCompatActivity {

EditText userId;
TextView remainingBalance;


   String urlCheck="http://192.168.8.100:8000/api/checkPoints";
   String urlRedeem="http://192.168.8.100:8000/api/pointsRedeem";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem);



        userId = (EditText) findViewById(R.id.txtUserId);
        remainingBalance = (TextView) findViewById(R.id.txtRemainingBalance);
        ;
        Button btnRedeem=(Button)findViewById(R.id.btnRedeem);

        Button btnCheck=(Button)findViewById(R.id.btnCheck);


   btnCheck.setOnClickListener(new View.OnClickListener(){
       @Override
       public void onClick(View v) {

           final String uId= userId.getText().toString();

           StringRequest stringRequest = new StringRequest(Request.Method.POST, urlCheck, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {

                   try {
                       JSONObject jsonObject = new JSONObject(response);
                       String txtremainingBalance=jsonObject.get("remainingBalance").toString();

                       remainingBalance.setText(txtremainingBalance);
                       double validate=jsonObject.getDouble("remainingBalance");




                   } catch (JSONException e) {
                       e.printStackTrace();
                   }
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   Toast.makeText(Redeem.this, error.getMessage(), Toast.LENGTH_LONG).show();
               }
           }) {
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String, String> params = new HashMap<String, String>();
                   params.put("uId", uId);

                   return params;
               }
           };
           MySingleton.getInstance(Redeem.this).addToRequestQueue(stringRequest);


       }
   });









         btnRedeem.setOnClickListener(new View.OnClickListener(){


             @Override
             public void onClick(View v) {
                  String validate=(remainingBalance.getText().toString());





                 if (validate!=null) {
                     remainingBalance.setText("");
                     Toast.makeText(Redeem.this, "Redeem Completed!", Toast.LENGTH_LONG).show();

                     final String uId = userId.getText().toString();

                     StringRequest stringRequest = new StringRequest(Request.Method.POST, urlRedeem, new Response.Listener<String>() {
                         @Override
                         public void onResponse(String response) {

                             try {
                                 JSONObject jsonObject = new JSONObject(response);


                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                         }
                     }, new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             Toast.makeText(Redeem.this, error.getMessage(), Toast.LENGTH_LONG).show();
                         }
                     }) {
                         @Override
                         protected Map<String, String> getParams() throws AuthFailureError {
                             Map<String, String> params = new HashMap<String, String>();
                             params.put("uId", uId);

                             return params;
                         }
                     };
                     MySingleton.getInstance(Redeem.this).addToRequestQueue(stringRequest);


                 } else {

                     Toast.makeText(Redeem.this, "Your currentbalance is not sufficiant to redeem!", Toast.LENGTH_LONG).show();
                 }
             } });





         }








}
