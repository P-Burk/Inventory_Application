package com.pburkhardt.inventory_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
                Toast.makeText(login.this, "Login button pressed.", Toast.LENGTH_SHORT).show();
            }
        });

        //Actions taken when register button is pressed
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(login.this, "Register button pressed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToInventory(View view) {
        Intent intent = new Intent(this, inventory.class);
        startActivity(intent);
    }
}