package com.example.mysecret_d;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView  header_text = (TextView)findViewById(R.id.header_text);
      //  TextView  app_name = (TextView)findViewById(R.id.app_name);
        EditText  username_input = (EditText)findViewById(R.id.username_input);
        EditText  password_input = (EditText)findViewById(R.id.password_input);
     //   Button login_button = (Button)findViewById(R.id.login_button);



        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/bpg-banner-caps-webfont.ttf");


        //header_text.setTypeface(custom_font);
       // app_name.setTypeface(custom_font);
        username_input.setTypeface(custom_font);
        password_input.setTypeface(custom_font);
        //login_button.setTypeface(custom_font);

    }
}
