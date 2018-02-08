package com.example.thilina.e1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.internal.v;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GrDogActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener{

    TextView tv,id;
    Button b;
    String v1,v2;
    String url="http://192.168.8.100:8000/api/collectorSend";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gr_dog);
      id =(TextView) findViewById(R.id.id);
      id.setText(getIntent().getExtras().getString("id"));

        tv = (TextView) findViewById(R.id.txtViewFood);//xml file item id
        Button b = (Button) findViewById(R.id.btnFood);
        b.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                show(tv);
            }
        });
        Button btnSend = (Button) findViewById(R.id.btnSubmit);
        btnSend.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                v1= tv.getText().toString();
                v2= id.getText().toString();


                // Intent k = new Intent(GrActivity.this, MapActivity.class);
                // startActivity(k);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getBoolean("error")){
                                Toast.makeText(GrDogActivity.this,"Error!!!!!!!",Toast.LENGTH_LONG).show();


                            }

                            else{

                                Intent k = new Intent(GrDogActivity.this, MapActivity.class);
                                startActivity(k);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GrDogActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("DogFood",v1);
                        params.put("requestId",v2);
                        return params;
                    }
                };



                MySingleton.getInstance(GrDogActivity.this).addToRequestQueue(stringRequest);



            }
        });




    }


























    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.i("value is",""+newVal);

    }





    public void show(final TextView tv)
    {

        final Dialog d = new Dialog(GrDogActivity.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np1.setMaxValue(9);
        np1.setMinValue(0);
        np1.setWrapSelectorWheel(true);
        np1.setOnValueChangedListener(this);

        final NumberPicker np2 = (NumberPicker) d.findViewById(R.id.numberPicker2);
        np2.setMaxValue(9);
        np2.setMinValue(0);
        np2.setWrapSelectorWheel(true);
        np2.setOnValueChangedListener(this);

        final NumberPicker np3= (NumberPicker) d.findViewById(R.id.numberPicker3);
        np3.setMaxValue(9);
        np3.setMinValue(0);
        np3.setWrapSelectorWheel(true);
        np3.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                float n1= np1.getValue();
                int n2=np2.getValue();
                int n3=np3.getValue();

                float value=n3*10+n2+n1/10;





                AlertDialog.Builder builder = new AlertDialog.Builder(GrDogActivity.this);


                String val= String.valueOf(value);




                builder.setTitle("Are You sure?");
                builder.setMessage(val+"kgs");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing but close the dialog

                        float n1= np1.getValue();
                        int n2=np2.getValue();
                        int n3=np3.getValue();

                        float value=n3*10+n2+n1/10;
                        String val= String.valueOf(value);

                        tv.setText(val);
                        d.dismiss();

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();





            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();


    }



}
