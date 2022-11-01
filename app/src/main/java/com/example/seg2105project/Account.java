package com.example.seg2105project;



import android.os.Bundle;

public class Account {

    private int id;
    private String accountName;
    private String accountPassword;
    private String accountRole;

    public Account() {
    }

    public Account(String accountName, String accountPassword) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

    public Account(String accountName, String accountPassword, String accountRole) {
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.accountRole = accountRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(String accountRole) {
        this.accountRole = accountRole;
    }

}