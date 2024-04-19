package com.example.spot_seeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import models.Listing;
import models.User;
import models.UserSingleton;

public class DetailedSpot extends AppCompatActivity implements View.OnClickListener {

    TextView tvAddress, tvIsAvailable;
    ListView lvAdditionalServices;
    DatabaseReference usersDatabase, listingsDatabase;
    Listing listing;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_spot);
        initialize();
    }

    private void initialize() {
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        // ------------ GET INFORMATION SENT FROM INTENT FOR LISTING --------
        Intent intent = getIntent();
        listing = (Listing) intent.getSerializableExtra("listing");
        tvAddress = findViewById(R.id.tvAddress);
        tvIsAvailable = findViewById(R.id.tvIsAvailable);
        lvAdditionalServices = findViewById(R.id.lvAdditionalServices);
        // --------------------------- DATABASES -----------------------------
        usersDatabase = FirebaseDatabase.getInstance().getReference("Users");
        listingsDatabase = FirebaseDatabase.getInstance().getReference("Listings");
        // Populate the TextViews with the proper information related to the listing
        tvAddress.setText(listing.getAddress());
        tvIsAvailable.setText(listing.getAvailable().toString());
        // Populate the ListView with the additional services
        ArrayList<String> additionalServices = listing.getAdditionalServices();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, additionalServices);
        lvAdditionalServices.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.btnDelete) {
            deleteListing();
        }
    }

    private void deleteListing() {
        User loggedInUser = UserSingleton.getUser();
        // Update the UserSingleton object by removing the Listing ID from the listingIds list
        loggedInUser.getListingIds().remove(Integer.valueOf(listing.getListId()));
        // Update the Users collection in the database with the newly modified UserSingleton object
        usersDatabase.child(String.valueOf(loggedInUser.getUserId())).setValue(loggedInUser);
        // Update the Listings collection in the database by removing the document
        listingsDatabase.child(String.valueOf(listing.getListId())).removeValue();
        setResult(RESULT_OK);
        finish();
    }
}
