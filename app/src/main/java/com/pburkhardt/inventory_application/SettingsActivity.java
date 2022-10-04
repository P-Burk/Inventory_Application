package com.pburkhardt.inventory_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.Objects;

//import android.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    private int SMS_PERMISSIONS_CODE = 1;
    SwitchCompat SMSpermSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.settingsToolBar);
        toolbar.setTitle("Settings");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // set the switch and its state based off of the SMS permission state
        SMSpermSwitch = (SwitchCompat) findViewById(R.id.SMS_permSwitch);
        SMSpermSwitch.setChecked(checkSMSperms());
        SMSpermSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checkedStatus) {
                if (checkedStatus) {
                    requestSMSperms();
                } else {
                    Toast.makeText(getApplicationContext(), "SMS permissions denied", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //request permissions
    private void requestSMSperms() {
        // if the user already denied the permissions once
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
            new AlertDialog.Builder(this)
                    .setTitle("SMS Send Permission")
                    .setMessage("Needed in order to send low inventory SMS messages.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(SettingsActivity.this,
                                    new String[] {Manifest.permission.SEND_SMS},
                                    SMS_PERMISSIONS_CODE);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            SMSpermSwitch.setChecked(false);
                        }
                    })
                    .create().show();

        } else {
            // user has not denied the permissions yet
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.SEND_SMS},
                    SMS_PERMISSIONS_CODE);
        }
    }

    //messages for if the permissions are granted or denied
    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SMS_PERMISSIONS_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "SMS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "SMS permission NOT granted (possibly due to multiple denials).",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // check to see what the permission is set to
    private boolean checkSMSperms() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }
}