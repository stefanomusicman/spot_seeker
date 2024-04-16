package com.example.spot_seeker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.menuImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.man) {
            startActivity(new Intent(this, Profile.class));;
        } else if (id == R.id.parkingSpot) {
            // Navigate to MySpots Fragment
            startActivity(new Intent(this, MySpots.class));
        } else if (id == R.id.registerSpot) {
            // Navigate to RegisterParkingSpot Activity
            startActivity(new Intent(this, RegisterParkingSpot.class));
        } else if (id == R.id.logout) {
            // Perform logout action
            performLogout();
        }

        // Close the drawer after handling the item click
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }


    private void performLogout() {
        // Add your logout logic here, for example:
        // Redirect to login screen
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
        // Finish current activity
        finish();
    }

}
