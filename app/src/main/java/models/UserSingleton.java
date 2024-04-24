package models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class UserSingleton {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User newUser) {
        user = newUser;
    }

    // This is a function that will convert the user's ArrayList of listings Ids into an ArrayList of Listing Objects
    // to the use the display the user's listings on the "My Spots" screen
    public static void getUserListings(final ListingsCallback callback) {
        final ArrayList<Listing> listings = new ArrayList<>();
        DatabaseReference listingsDatabase = FirebaseDatabase.getInstance().getReference("Listings");
        ArrayList<Integer> usersListingsIds = user.getListingIds();

        final AtomicInteger counter = new AtomicInteger(usersListingsIds.size());

        for (final Integer listingId : usersListingsIds) {
            listingsDatabase.child(String.valueOf(listingId)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String address = snapshot.child("address").getValue(String.class);
                        Double latitude = snapshot.child("latitude").getValue(Double.class);
                        Double longitude = snapshot.child("longitude").getValue(Double.class);
                        Boolean isAvailable = snapshot.child("available").getValue(Boolean.class);
                        ArrayList<String> additionalServices = new ArrayList<>();
                        DataSnapshot addServicesSnapshot = snapshot.child("additionalServices");

                        if (addServicesSnapshot.exists()) {
                            for (DataSnapshot serviceSnapshot : addServicesSnapshot.getChildren()) {
                                String service = serviceSnapshot.getValue(String.class);
                                additionalServices.add(service);
                            }
                        }

                        Listing newListing = new Listing(listingId, address, latitude, longitude, isAvailable, additionalServices);
                        listings.add(newListing);
                    } else {
                        Log.d("NO LISTING", "Listing with ID " + listingId + " does not exist in the database.");
                    }

                    // Decrease the counter and check if all listings have been processed
                    if (counter.decrementAndGet() == 0) {
                        callback.onListingsLoaded(listings);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle error
                    Log.e("GET LISTINGS ERROR", "Error retrieving listing with ID " + listingId + ": " + error.getMessage());
                }
            });
        }
    }
}
