package com.example.mysecret_d;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.provider.Settings.Secure;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

        final String secretKey = "AFD156A2DE9235B264F7F248B96F6";
        EditText  username_input = (EditText)findViewById(R.id.username_input);
        EditText  password_input = (EditText)findViewById(R.id.password_input);
        String username = String.valueOf(username_input.getText().toString());
        String password = String.valueOf(password_input.getText().toString());


        String encrypt_username = AES.encrypt(username, secretKey) ;
        String encrypt_password = AES.encrypt(password, secretKey) ;
        String encrypt_twice = AES.encrypt(username+password, secretKey) ;
        String android_id = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);


        System.out.println(encrypt_username);
        System.out.println(encrypt_password);
        System.out.println(encrypt_twice);
        System.out.println(android_id.toString());



        if (TextUtils.isEmpty(username)) {
            username_input.setError("გთხოვთ ჩაწეროთ მომხმარებელი");
            username_input.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            password_input.setError("გთხოვთ ჩაწეროთ პაროლი");
            password_input.requestFocus();
            return;
        }

        try{

            callvolly(encrypt_username, encrypt_password, encrypt_twice);

        } catch (Exception e){

        }



    }

    public void callvolly(final String username, final String password, final String device_id){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://itweb.ge/secret/index.php"; // <----enter your post url here
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.d("Result", "Good");
                Log.d("Result", response.toString());

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Log.d("Result", "Bad");
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("username", username);
                MyData.put("password", password);
                MyData.put("device_id", device_id);
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);
    }
}
