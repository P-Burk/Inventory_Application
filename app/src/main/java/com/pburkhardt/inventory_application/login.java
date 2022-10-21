package com.pburkhardt.inventory_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    String CURRENT_USER;

    //Controls for the activity
    Button loginButton;
    Button registerButton;
    EditText userNameText;
    EditText userPasswordText;
    DBHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        userNameText = findViewById(R.id.editTextTextUserName);
        userPasswordText = findViewById(R.id.editTextTextPassword);
        DBHelper = new DBHelper(login.this);

        //Actions taken when login button is pressed
        loginButton.setOnClickListener(new View.OnClickListener() {
            InventoryUser newUser;

            @Override
            public void onClick(View view) {
                //check to see if user entered a name and password
                if (userNameText.getText().toString().matches("") ||
                userPasswordText.getText().toString().matches("")) {
                    Toast.makeText(login.this, "Username and/or password fields blank.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //create user object
                    //hardcoded phone number for the emulated phone
                    newUser = new InventoryUser(-1, userNameText.getText().toString(),
                            userPasswordText.getText().toString(), 5555215554L, false);

                    //check to see if user is in the DB
                    if (DBHelper.checkDBforUser(newUser, true)) {     //user found -> login
                        Toast.makeText(login.this, "Login Successful.",
                                Toast.LENGTH_SHORT).show();
                        CURRENT_USER = userNameText.getText().toString();
                        goToInventory(view);
                    } else {                                    //user not found -> error
                        Toast.makeText(login.this, "Login Failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Actions taken when register button is pressed
        registerButton.setOnClickListener(new View.OnClickListener() {
            InventoryUser newUser;

            @Override
            public void onClick(View view) {
                //check to see if user entered a name and password
                if (userNameText.getText().toString().matches("") ||
                        userPasswordText.getText().toString().matches("")) {
                    Toast.makeText(login.this, "Username and/or password fields blank.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //hardcoded phone number for the emulated phone
                    newUser = new InventoryUser(-1, userNameText.getText().toString(),
                            userPasswordText.getText().toString(), 5555215554L, false);

                    //check to see if username is already in DB
                    if (DBHelper.checkDBforUser(newUser, false)) {
                        Toast.makeText(login.this, "User already in database.", Toast.LENGTH_SHORT).show();
                    } else {    //username not in DB -> add new user
                        boolean addSuccess = DBHelper.addUser(newUser);
                        if (addSuccess) {
                            Toast.makeText(login.this, "User added.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(login.this, "Failed to add user.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    //navigate to inventory activity
    public void goToInventory(View view) {
        Intent intent = new Intent(this, inventory.class);
        intent.putExtra("CURRENT_USER", CURRENT_USER);
        startActivity(intent);
    }
}