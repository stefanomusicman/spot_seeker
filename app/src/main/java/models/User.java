package models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private ArrayList<Integer> listingIds;
    private ArrayList<Integer> transactionIds;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String userName, ArrayList<Integer> listingIds, ArrayList<Integer> transactionIds) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.listingIds = listingIds;
        this.transactionIds = transactionIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Integer> getListingIds() {
        return listingIds;
    }

    public void setListingIds(ArrayList<Integer> listingIds) {
        this.listingIds = listingIds;
    }

    public ArrayList<Integer> getTransactionIds() {
        return transactionIds;
    }

    public void setTransactionIds(ArrayList<Integer> transactionIds) {
        this.transactionIds = transactionIds;
    }

    @NonNull
    @Override
    public String toString() {
        return this.userName;
    }
}
