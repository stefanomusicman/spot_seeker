package models;

import java.util.ArrayList;

public interface ListingsCallback {
    void onListingsLoaded(ArrayList<Listing> listings);
}
