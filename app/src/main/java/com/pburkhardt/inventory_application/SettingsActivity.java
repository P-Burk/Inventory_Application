package com.pburkhardt.inventory_application;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

//import android.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    SwitchCompat SMSpermSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.settingsToolBar);
        toolbar.setTitle("Settings");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SMSpermSwitch = (SwitchCompat) findViewById(R.id.SMS_permSwitch);
        SMSpermSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checkedStatus) {
                if (checkedStatus) {
                    Toast.makeText(getApplicationContext(), "SMS perms granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "SMS perms denied", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}