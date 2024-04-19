package com.example.spot_seeker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import models.Listing;
import models.ListingsCallback;
import models.UserSingleton;

public class MySpots extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_spots);

        CardView cardView = findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MySpots.this, DetailedSpot.class));
            }
        });

        UserSingleton.getUserListings(new ListingsCallback() {
            @Override
            public void onListingsLoaded(ArrayList<Listing> listings) {
                // Handle the retrieved listings here
                Log.d("USER LISTINGS", listings.toString());
                // Do whatever you need with the listings, such as updating UI or processing data
            }
        });
    }
}

