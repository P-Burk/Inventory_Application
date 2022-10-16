package com.pburkhardt.inventory_application;

import androidx.annotation.NonNull;

public class InventoryUser {

    private int invUserID;
    private String invUserName;
    private String invUserPassword;

    //CONSTRUCTORS
    public InventoryUser(int invUserID, String invUserName, String invUserPassword) {
        this.invUserID = invUserID;
        this.invUserName = invUserName;
        this.invUserPassword = invUserPassword;
    }

    public InventoryUser() {
    }

    //DEBUG HELPER FUNCTION

    @NonNull
    @Override
    public String toString() {
        return "InventoryUser{" +
                "invUserID=" + invUserID +
                ", invUserName='" + invUserName + '\'' +
                ", invUserPassword='" + invUserPassword + '\'' +
                '}';
    }

    //SETTERS and GETTERS
    public int getInvUserID() {
        return invUserID;
    }

    public void setInvUserID(int invUserID) {
        this.invUserID = invUserID;
    }

    public String getInvUserName() {
        return invUserName;
    }

    public void setInvUserName(String invUserName) {
        this.invUserName = invUserName;
    }

    public String getInvUserPassword() {
        return invUserPassword;
    }

    public void setInvUserPassword(String invUserPassword) {
        this.invUserPassword = invUserPassword;
    }
}
