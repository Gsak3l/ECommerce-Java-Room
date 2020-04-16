package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    //variables
    private Button loginButton;
    private Button signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //giving the values to the buttons from our xml
        loginButton = (Button) findViewById(R.id.welcome_login_button);
        signupButton = (Button) findViewById(R.id.welcome_signup_button);

    }
}
