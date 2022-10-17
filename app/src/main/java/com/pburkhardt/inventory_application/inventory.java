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
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class inventory extends AppCompatActivity {

    List<inventoryItemModel> inventoryItemsList;
    DBHelper DBHelper;
    private RecyclerView inventoryRecyclerView;
    private RecyclerViewAdapter invRecViewAdapter;
    private RecyclerView.LayoutManager invLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        Toolbar inventoryToolBar = (Toolbar) findViewById(R.id.inventoryToolBar);
        setSupportActionBar(inventoryToolBar);
        DBHelper = new DBHelper(inventory.this);
        buildRecyclerView();
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

    public void buildRecyclerView() {
        inventoryItemsList = DBHelper.getAllItemsInInventory();
        inventoryRecyclerView = findViewById(R.id.inventoryList);
        invRecViewAdapter = new RecyclerViewAdapter(this, inventoryItemsList);
        invLayoutManager = new LinearLayoutManager(this);

        inventoryRecyclerView.setHasFixedSize(true);
        inventoryRecyclerView.setAdapter(invRecViewAdapter);
        inventoryRecyclerView.setLayoutManager(invLayoutManager);

        invRecViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int itemPos) {
                //do nothing
            }

            @Override
            public void deleteItem(int itemPos) {
                String itemName = inventoryItemsList.get(itemPos).getItemName();
                if (DBHelper.deleteInvItem(itemName)) {
                    Toast.makeText(inventory.this, "Item deleted.",
                            Toast.LENGTH_SHORT).show();
                }
                inventoryItemsList.remove(itemPos);
                invRecViewAdapter.notifyItemRemoved(itemPos);
            }
        });
    }

}