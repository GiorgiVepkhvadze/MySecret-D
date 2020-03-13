package com.example.mysecret_d;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/bpg-banner-caps-webfont.ttf");
        TextView  main_header = (TextView)findViewById(R.id.main_header);
        EditText  username_input = (EditText)findViewById(R.id.username_input);
        EditText  password_input = (EditText)findViewById(R.id.password_input);
        main_header.setTypeface(custom_font);
        username_input.setTypeface(custom_font);
        password_input.setTypeface(custom_font);


    }

    public void login(View v){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.google.com";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Passuxi", response.substring(0,500));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("shecdoma", error.toString());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
