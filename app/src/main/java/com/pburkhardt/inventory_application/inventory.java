package com.pburkhardt.inventory_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class inventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.inventoryToolBar);
        setSupportActionBar(myToolbar);
        //setTitle("Inventory");
    }
}