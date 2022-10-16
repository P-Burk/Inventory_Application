package com.pburkhardt.inventory_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class inventory extends AppCompatActivity {

    List<inventoryItemModel> inventoryItemsList = new ArrayList<>();
    DBHelper DBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        Toolbar inventoryToolBar = (Toolbar) findViewById(R.id.inventoryToolBar);
        setSupportActionBar(inventoryToolBar);
        DBHelper = new DBHelper(inventory.this);
        RecyclerView recyclerView = findViewById(R.id.inventoryList);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, DBHelper.getAllItemsInInventory());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_search:
                //TODO: call search function here
                placeHolderAction(findViewById(R.id.inventoryToolBar));
                return true;
            case R.id.action_settings:
                goToSettings(findViewById(R.id.action_settings));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inventory_menu, menu);
        return true;
    }

    public void goToSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    //place holder action
    public void placeHolderAction(View view) {
        Snackbar someActionSnack = Snackbar.make(view,
                "PLACE HOLDER ACTION",
                BaseTransientBottomBar.LENGTH_SHORT);
        someActionSnack.show();
    }

    public void goToAddActivity(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }
}