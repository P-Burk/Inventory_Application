package com.pburkhardt.inventory_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Toolbar toolbar = findViewById(R.id.materialToolbar);
        toolbar.setTitle("Add Item");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        Button addButton = findViewById(R.id.addItemButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addItemToList();
                goToInventoryActivity(view);
            }
        });
    }

    private void addItemToList(String newItemName, int newItemCount) {
        //TODO: add functionality to add item to database
    }

    public void goToInventoryActivity(View view) {
        Intent intent = new Intent(this, inventory.class);
        startActivity(intent);
    }
}