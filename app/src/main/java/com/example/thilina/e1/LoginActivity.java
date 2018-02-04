package com.example.thilina.e1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET, passwordET;
    Button loginBTN;
    String username, password;
    String loginUrl = "http://192.168.8.100:8000/api/collectorLogin";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent i=new Intent(LoginActivity.this,MapActivity.class);


        prefs=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        editor=prefs.edit();

        if(prefs.getBoolean("isLoggedIn",false)){

            startActivity(i);
        }

        usernameET = (EditText) findViewById(R.id.txtUsername);
       passwordET = (EditText) findViewById(R.id.txtPassword);
        loginBTN = (Button) findViewById(R.id.btnLogin);


        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                username = usernameET.getText().toString();
                password = passwordET.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, loginUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("error")) {
                                Toast.makeText(LoginActivity.this, "Error!!!!", Toast.LENGTH_LONG).show();

                                editor.putBoolean("isLoggedIn",false);
                                editor.commit();


                            } else {


                                editor.putBoolean("isLoggedIn",true);
                                JSONObject cIdobj=jsonObject.getJSONObject("cId");
                                String cId=cIdobj.getString("id");
                                editor.putString("cId",cId);
                                editor.commit();
                                Intent i=new Intent(LoginActivity.this,MapActivity.class);
                                Toast.makeText(LoginActivity.this,prefs.getString("cId",null),Toast.LENGTH_LONG).show();

                                startActivity(i);
                                finish();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("collectorname", username);
                        params.put("password", password);
                        return params;
                    }
                };
                MySingleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);

            }
        });


    }

}
