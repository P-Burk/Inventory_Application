package com.pburkhardt.inventory_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    String CURRENT_USER;
    Button addButton;
    EditText itemNameText, itemCountText;
    DBHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        CURRENT_USER = getIntent().getStringExtra("CURRENT_USER");

        Toolbar toolbar = findViewById(R.id.materialToolbar);
        toolbar.setTitle("Add Item");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        itemNameText = findViewById(R.id.addItemName);
        itemCountText = findViewById(R.id.addItemCount);
        addButton = findViewById(R.id.addItemButton);
        DBHelper = new DBHelper(AddItemActivity.this);

        //listener for the add button
        addButton.setOnClickListener(view -> {
            inventoryItemModel newItem = new inventoryItemModel(itemNameText.getText().toString(),
                    Integer.parseInt(itemCountText.getText().toString()));
            if (DBHelper.checkDBforItem(newItem)) {     //check for duplicate item
                Toast.makeText(AddItemActivity.this, "Item already in inventory.",
                        Toast.LENGTH_SHORT).show();
            } else {    //add item to DB if no duplicate
                boolean addSuccess = DBHelper.addItem(newItem);
                if (!addSuccess) {  //failed to add item
                    Toast.makeText(AddItemActivity.this, "Failed to add new item.",
                            Toast.LENGTH_SHORT).show();
                }
            }
            goToInventoryActivity(view);
        });
    }

    //navigates to the Add Item activity
    public void goToInventoryActivity(View view) {
        Intent intent = new Intent(this, inventory.class);
        intent.putExtra("CURRENT_USER", CURRENT_USER);
        startActivity(intent);
    }
}