package com.pburkhardt.inventory_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    private int SMS_PERMISSIONS_CODE = 1;
    String CURRENT_USER;
    SwitchCompat SMSpermSwitch;
    private EditText phoneNumFieldText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHelper DBHelper = new DBHelper(SettingsActivity.this);
        setContentView(R.layout.activity_settings);
        CURRENT_USER = getIntent().getStringExtra("CURRENT_USER");
        phoneNumFieldText = findViewById(R.id.editTextPhoneNum);
        phoneNumFieldText.setHint(DBHelper.getUserPhoneNum(CURRENT_USER).toString());

        //toolbar setup
        Toolbar toolbar = findViewById(R.id.settingsToolBar);
        toolbar.setTitle("Settings");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // set the switch and its state based off of the SMS permission state
        SMSpermSwitch = findViewById(R.id.SMS_permSwitch);
        SMSpermSwitch.setChecked(DBHelper.getUserSMSflag(CURRENT_USER));
        SMSpermSwitch.setOnCheckedChangeListener((compoundButton, checkedStatus) -> {
            if (checkedStatus) {
                DBHelper.updateUserSMSflag(CURRENT_USER, 1);
                requestSMSperms();
            } else {
                DBHelper.updateUserSMSflag(CURRENT_USER, 0);
                Toast.makeText(getApplicationContext(), "SMS permissions denied", Toast.LENGTH_SHORT).show();
            }
        });

        //set listener to detect changes in phone number editText field
        phoneNumFieldText.setOnEditorActionListener((textView, i, keyEvent) -> {
            if ((i & EditorInfo.IME_MASK_ACTION) != 0) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(phoneNumFieldText.getWindowToken(), 0);
                try {
                    DBHelper.updateUserPhoneNum(CURRENT_USER, Long.parseLong(phoneNumFieldText.getText().toString()));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "That's not a phone number.", Toast.LENGTH_SHORT).show();
                }

                phoneNumFieldText.setFocusable(false);
                phoneNumFieldText.setFocusableInTouchMode(true);
                return true;
            } else {
                return false;
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
                    .setPositiveButton("Ok", (dialogInterface, i) -> ActivityCompat.requestPermissions(SettingsActivity.this,
                            new String[] {Manifest.permission.SEND_SMS},
                            SMS_PERMISSIONS_CODE))
                    .setNegativeButton("Cancel", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        SMSpermSwitch.setChecked(false);
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

    //needed to use the back button at the top and still keep the CURRENT_USER
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("CURRENT_USER", CURRENT_USER);
                setResult(111, intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}