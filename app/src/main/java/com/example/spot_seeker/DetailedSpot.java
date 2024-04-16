package com.example.spot_seeker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DetailedSpot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_spot);

        Button btnDeleteSpot = findViewById(R.id.btnDeleteSpot);
        btnDeleteSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your delete spot logic here
                // For now, just show a toast message
            }
        });
    }
}
