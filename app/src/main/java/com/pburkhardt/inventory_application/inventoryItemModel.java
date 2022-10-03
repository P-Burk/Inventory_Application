package com.pburkhardt.inventory_application;

public class inventoryItemModel {
    ///// ATTRIBUTES /////
    private String itemName;
    private String itemCount;
//    private int subtractBtnImg;
//    private int addBtnImg;
//    private int trashBtnImg;

    ///// CONSTRUCTORS /////
    public inventoryItemModel(String itemName, String itemCount) {
        this.itemName = itemName;
        this.itemCount = itemCount;
//        this.subtractBtnImg = subtractBtnImg;
//        this.addBtnImg = addBtnImg;
//        this.trashBtnImg = trashBtnImg;
    }

    ///// GETTERS + SETTERS /////
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

//    public int getSubtractBtnImg() {
//        return subtractBtnImg;
//    }
//
//    public void setSubtractBtnImg(int subtractBtnImg) {
//        this.subtractBtnImg = subtractBtnImg;
//    }
//
//    public int getAddBtnImg() {
//        return addBtnImg;
//    }
//
//    public void setAddBtnImg(int addBtnImg) {
//        this.addBtnImg = addBtnImg;
//    }
//
//    public int getTrashBtnImg() {
//        return trashBtnImg;
//    }
//
//    public void setTrashBtnImg(int trashBtnImg) {
//        this.trashBtnImg = trashBtnImg;
//    }
}
