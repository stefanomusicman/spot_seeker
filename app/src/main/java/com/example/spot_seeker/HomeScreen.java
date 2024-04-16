package com.example.spot_seeker;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class HomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null); // Remove icon tinting
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.menuImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int itemId = item.getItemId();

        if (itemId == R.id.Profile) {
            fragment = new Profile();

        } else if (itemId == R.id.parkingSpot) {
            fragment = new Myspots();
        } else if (itemId == R.id.Home) {
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.logout) {
            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
            return true; // Return true to prevent further actions
        }

        if (fragment != null) {
            replaceFragment(fragment);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        return false;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }
}


