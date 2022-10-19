package com.pburkhardt.inventory_application;

import androidx.annotation.NonNull;

public class InventoryUser {

    private int invUserID;
    private String invUserName;
    private String invUserPassword;
    private int userPhoneNum;
    private boolean smsFlag;

    //CONSTRUCTORS
    public InventoryUser(int invUserID, String invUserName, String invUserPassword, int phoneNum, boolean smsFlag) {
        this.invUserID = invUserID;
        this.invUserName = invUserName;
        this.invUserPassword = invUserPassword;
        this.userPhoneNum = 0;
        this.smsFlag = false;
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

    public void setInvUserPassword(String invUserPassword) {this.invUserPassword = invUserPassword;}

    public int getUserPhoneNum() {return userPhoneNum;}

    public void setUserPhoneNum(int userPhoneNum) {this.userPhoneNum = userPhoneNum;}

    public boolean isSmsFlag() {return smsFlag;}

    public void setSmsFlag(boolean smsFlag) {this.smsFlag = smsFlag;}
}
