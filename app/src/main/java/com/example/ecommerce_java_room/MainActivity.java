package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        //when login button of our homepage gets clicked, we change the view from mainactivity to the login activity
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
        //when sign-up button of our homepage gets clicked, we change the view from mainactivity to the register activity
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(login);
            }
        });
    }
}
