package com.example.ecommerce_java_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerce_java_room.data.UserDAO;
import com.example.ecommerce_java_room.data.UserDatabase;
import com.example.ecommerce_java_room.model.User;

public class RegisterActivity extends AppCompatActivity {

    //initializing our xml elements
    private Button createAccountButton;
    private EditText registerEmail;
    private EditText registerPassword;
    private EditText registerFullName;

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //getting all the elements from our xml file
        createAccountButton = (Button) findViewById(R.id.register_button);
        registerFullName = (EditText) findViewById(R.id.register_full_name);
        registerEmail = (EditText) findViewById(R.id.register_email);
        registerPassword = (EditText) findViewById(R.id.register_password);

        //allowing queries
        userDAO = Room.databaseBuilder(this, UserDatabase.class, "User")
                .allowMainThreadQueries().build().getUserDao();
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //trim removes the leading and trailing spaces
                String email = registerEmail.getText().toString().trim();
                String fullName = registerFullName.getText().toString().trim();
                String password = registerPassword.getText().toString().trim();
                if (userDAO.getUserEmail(email) == null) { //checking if the user exists
                    if (!password.equals("") && !fullName.equals("") && !email.equals("")) { //or !password.equals("")
                        User user = new User(fullName, password, email);
                        userDAO.insert(user);
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        //emptying the values
                        registerEmail.setText("");
                        registerFullName.setText("");
                        registerPassword.setText("");
                        Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Please Fill all the Fields..", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Unable to Create a new Account with this Email Address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
