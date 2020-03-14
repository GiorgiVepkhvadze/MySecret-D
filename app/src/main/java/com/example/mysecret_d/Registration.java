package com.example.mysecret_d;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
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

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/bpg-banner-caps-webfont.ttf");
        TextView main_header = (TextView) findViewById(R.id.main_header);
        main_header.setTypeface(custom_font);
        EditText username_input = (EditText) findViewById(R.id.username_input);
        username_input.setTypeface(custom_font);
        EditText password_input = (EditText) findViewById(R.id.password_input);
        password_input.setTypeface(custom_font);
        EditText password_input2 = (EditText) findViewById(R.id.password_input2);
        password_input2.setTypeface(custom_font);
        Button button2_login = (Button) findViewById(R.id.button2_login);
        button2_login.setTypeface(custom_font);

    }


    public void Registration_void(View v){

        final String secretKey = "AFD156A2DE9235B264F7F248B96F6AAAAAAA";
        EditText  username_input = (EditText)findViewById(R.id.username_input);
        EditText  password_input = (EditText)findViewById(R.id.password_input);
        EditText password_input2 = (EditText) findViewById(R.id.password_input2);

        String Username = String.valueOf(username_input.getText().toString());
        String Password = String.valueOf(password_input.getText().toString());
        String Repassword = String.valueOf(password_input2.getText().toString());

        String encrypt_username = AES.encrypt(Username, secretKey) ;
        String encrypt_password = AES.encrypt(Password, secretKey) ;
        String encrypt_repassword = AES.encrypt(Repassword, secretKey) ;
        String encrypt_twice = AES.encrypt(Username+Password, secretKey) ;
        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        if (TextUtils.isEmpty(Username)) {
            username_input.setError("გთხოვთ ჩაწეროთ მომხმარებელი");
            username_input.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Password)) {
            password_input.setError("გთხოვთ ჩაწეროთ პაროლი");
            password_input.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Repassword)) {
            password_input.setError("გთხოვთ ჩაწეროთ პაროლი");
            password_input.requestFocus();
            return;
        }

        if(Password.equals(Repassword)){
        } else {
            password_input.setError("პაროლები არ ემთხვევა ერთმანეთს");
            password_input.requestFocus();
            return;
        }

        try{
            security_registration(encrypt_username, encrypt_password, encrypt_twice, android_id);

        } catch (Exception e){

        }

    }


    public void security_registration(final String username, final String password, final String twice,  final String device_id){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://itweb.ge/secret/new_user.php"; // <----enter your post url here
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
                MyData.put("twice", twice);
                MyData.put("device_id", device_id);
                return MyData;
            }
        };


        MyRequestQueue.add(MyStringRequest);
    }










}
