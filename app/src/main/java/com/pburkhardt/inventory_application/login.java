package com.pburkhardt.inventory_application;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class login extends AppCompatActivity {

    //Controls for the activity
    Button loginButton;
    Button registerButton;
    EditText userNameText;
    EditText userPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        userNameText = findViewById(R.id.editTextTextUserName);
        userPasswordText = findViewById(R.id.editTextTextPassword);

        //Actions taken when login button is pressed
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check to see if user entered a name and password
                if (userNameText.getText().toString().matches("") ||
                userPasswordText.getText().toString().matches("")) {
                    Toast.makeText(login.this, "Username and/or password fields blank.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    InventoryUser newUser = new InventoryUser(-1, userNameText.getText().toString(),
                            userPasswordText.getText().toString());
                    //TODO: add function to check DB
                    //TODO: add function to go to inventory activity if user and password in DB
                    Toast.makeText(login.this, newUser.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Actions taken when register button is pressed
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check to see if user entered a name and password
                if (userNameText.getText().toString().matches("") ||
                        userPasswordText.getText().toString().matches("")) {
                    Toast.makeText(login.this, "Username and/or password fields blank.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    InventoryUser newUser = new InventoryUser(-1, userNameText.getText().toString(),
                            userPasswordText.getText().toString());
                    //TODO: add function to check DB if user already exists
                    //TODO: add function to add username and password to DB
                    Toast.makeText(login.this, newUser.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goToInventory(View view) {
        Intent intent = new Intent(this, inventory.class);
        startActivity(intent);
    }
}