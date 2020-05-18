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
    private UserDAO userDAO;
    private UserDatabase database;


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
        userDAO = database.getUserDao();

        //trying to log in with the given credentials
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                //finding out if the user exists in our database or if the user is the admin
                User user = userDAO.getUser(email, password);
                if (email.equals("admin") && password.equals("admin")) {
                    intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                    intent.putExtra("userType", "admin");
                    intent.putExtra("userId", -1);
                } else if (user != null) {
                    intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                    intent.putExtra("userType", "user");
                    intent.putExtra("userId", user.getId());
                } else if(userDAO.getUserEmail(email) != null){
                    Toast.makeText(LoginActivity.this, "Incorrect Password!",  Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Those Credentials do not match any User. Sign Up Now!",
                            Toast.LENGTH_LONG).show();
                    intent = new Intent(LoginActivity.this, RegisterActivity.class);
                }
                loginEmail.setText("");
                loginPassword.setText("");
                startActivity(intent);
            }
        });
    }
}
