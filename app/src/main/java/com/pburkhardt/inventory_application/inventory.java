package com.pburkhardt.inventory_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class inventory extends AppCompatActivity {

    ArrayList<inventoryItemModel> inventoryItemsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        Toolbar inventoryToolBar = (Toolbar) findViewById(R.id.inventoryToolBar);
        setSupportActionBar(inventoryToolBar);

        RecyclerView recyclerView = findViewById(R.id.inventoryList);
        populateInventoryList();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, inventoryItemsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // populates the array list for that holds all of the inventory items
    private void populateInventoryList() {
        String[] inventoryItemNames = getResources().getStringArray(R.array.placeHolderItems);
        String[] inventoryItemCounts = getResources().getStringArray(R.array.placeHolderItemCounts);

        for (int i = 0; i < inventoryItemNames.length; i++) {
            inventoryItemsList.add(new inventoryItemModel(inventoryItemNames[i], inventoryItemCounts[i]));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_search:
                //TODO: call search function here
                placeHolderAction(findViewById(R.id.inventoryToolBar));
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

    //place holder action
    public void placeHolderAction(View view) {
        Snackbar someActionSnack = Snackbar.make(view,
                "PLACE HOLDER ACTION",
                BaseTransientBottomBar.LENGTH_SHORT);
        someActionSnack.show();
    }
}