package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerce_java_room.data.UserDAO;
import com.example.ecommerce_java_room.data.UserDatabase;
import com.example.ecommerce_java_room.model.User;

public class LoginActivity extends AppCompatActivity {

    //initializing our xml elements
    private Button loginButton;
    private EditText loginEmail;
    private EditText loginPassword;

    //initializing our database stuff
    UserDAO db;
    UserDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //getting all the elements from our xml file
        loginButton = (Button) findViewById(R.id.login_button);
        loginEmail = (EditText) findViewById(R.id.login_email);
        loginPassword = (EditText) findViewById(R.id.login_password);

        //getting our database
        database = Room.databaseBuilder(this, UserDatabase.class, "User")
                .allowMainThreadQueries().build();
        db = database.getUserDao();

        //trying to log in with the given credentials
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                //finding out if the user exists in our database or if the user is the admin
                User user = db.getUser(email, password);
                if (email.equals("admin") && password.equals("admin")) {
                    Intent i = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                    startActivity(i);
                } else if (user != null) {
                    Intent i = new Intent(LoginActivity.this, AdminCategoryActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
