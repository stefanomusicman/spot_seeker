package models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Listing {
    private int listId;
    private String address;
    private String coordinates;
    private Boolean isAvailable;
    private ArrayList<String> additionalServices;

    public Listing() {
    }

    public Listing(int listId, String address, String coordinates, Boolean isAvailable, ArrayList<String> additionalServices) {
        this.listId = listId;
        this.address = address;
        this.coordinates = coordinates;
        this.isAvailable = isAvailable;
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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
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
