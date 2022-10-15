package com.pburkhardt.inventory_application;

public class inventoryItemModel {
    ///// ATTRIBUTES /////
    private String itemName;
    private int itemCount;

    ///// CONSTRUCTORS /////
    public inventoryItemModel(String itemName, int itemCount) {
        this.itemName = itemName;
        this.itemCount = itemCount;
    }

    ///// GETTERS + SETTERS /////
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void incrementItemCount() {
        this.itemCount++;
    }
    public void decrementItemCount() {
        this.itemCount--;
    }
}
