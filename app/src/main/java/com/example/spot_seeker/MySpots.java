package com.example.spot_seeker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import models.Listing;
import models.ListingsCallback;
import models.ParkingSpotAdapter;
import models.UserSingleton;

public class MySpots extends AppCompatActivity implements AdapterView.OnItemClickListener {
    TextView tvFullName, tvUserId;
    ArrayList<Listing> listingsList;
    ListView lvParkingSpots;
    ParkingSpotAdapter parkingSpotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spots);
        initialize();
    }

    private void initialize() {
        tvFullName = findViewById(R.id.tvFullName);
        tvUserId = findViewById(R.id.tvUserId);
        String fullName = UserSingleton.getUser().getFirstName() + " " + UserSingleton.getUser().getLastName();
        String userId = "User ID: " + String.valueOf(UserSingleton.getUser().getUserId());
        tvFullName.setText(fullName);
        tvUserId.setText(userId);
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
    protected void onPostResume() {
        super.onPostResume();
        initialize();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}

