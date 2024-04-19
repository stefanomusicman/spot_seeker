package models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Listing implements Serializable {
    private int listId;
    private String address;
    private double latitude;
    private double longitude;
    private Boolean isAvailable;
    private ArrayList<String> additionalServices;

    public Listing() {
    }

    public Listing(int listId, String address, double latitude, double longitude, Boolean isAvailable, ArrayList<String> additionalServices) {
        this.listId = listId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isAvailable = isAvailable;
        this.additionalServices = additionalServices;
    }

    public Listing(int listId, String address, ArrayList<String> additionalServices) {
        this.listId = listId;
        this.address = address;
        this.latitude = 0;
        this.longitude = 0;
        this.isAvailable = true;
        this.additionalServices = additionalServices;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public ArrayList<String> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(ArrayList<String> additionalServices) {
        this.additionalServices = additionalServices;
    }

    @NonNull
    @Override
    public String toString() {
        return this.address;
    }
}
