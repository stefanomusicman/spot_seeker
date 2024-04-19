package com.example.spot_seeker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import models.Listing;
import models.ListingsCallback;
import models.ParkingSpotAdapter;
import models.UserSingleton;

public class MySpots extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Listing> listingsList;
    ListView lvParkingSpots;
    ParkingSpotAdapter parkingSpotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spots);
        initialize();
//        CardView cardView = findViewById(R.id.cardView);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MySpots.this, DetailedSpot.class));
//            }
//        });

//        UserSingleton.getUserListings(new ListingsCallback() {
//            @Override
//            public void onListingsLoaded(ArrayList<Listing> listings) {
//                // Handle the retrieved listings here
//                Log.d("USER LISTINGS", listings.toString());
//                // Do whatever you need with the listings, such as updating UI or processing data
//                listingsList = listings;
//            }
//        });
    }

    private void initialize() {
        UserSingleton.getUserListings(new ListingsCallback() {
            @Override
            public void onListingsLoaded(ArrayList<Listing> listings) {
                listingsList = listings;
                Log.d("USER LISTINGS", listings.toString());

                lvParkingSpots = findViewById(R.id.lvParkingSpots);
                lvParkingSpots.setOnItemClickListener(MySpots.this);
                parkingSpotAdapter = new ParkingSpotAdapter(MySpots.this, listings);
                lvParkingSpots.setAdapter(parkingSpotAdapter);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}

