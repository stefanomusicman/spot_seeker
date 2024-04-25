package com.example.spot_seeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
            GoogleMap.OnMarkerClickListener
    {
        private GoogleMap spotMap;

        private Marker markerHome;

        private final LatLng spot1 = new LatLng(45.508164, -73.574621);
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            spotMap = googleMap;

            markerHome = spotMap.addMarker(new MarkerOptions()
                    .position(spot1)
                    .title("Spot1"));
            markerHome.setTag(0);
            spotMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spot1, 12));

            googleMap.setOnMarkerClickListener(this);

        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_home_screen);

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            mapFragment.getMapAsync(this);
        }

        @Override
        public boolean onMarkerClick(@NonNull Marker marker) {
            return false;
        }
    }
}