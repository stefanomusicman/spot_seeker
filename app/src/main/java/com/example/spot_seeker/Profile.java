package com.example.spot_seeker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.UserSingleton;

public class Profile extends AppCompatActivity {

    private TextView tvFullName, tvUserId;
    private EditText edFName, edLName, edUsername;
    private Button btnUpdate;
    private DatabaseReference userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initialize();
    }

    private void initialize() {
        // Initialize widgets
        tvFullName = findViewById(R.id.tvFullName);
        tvUserId = findViewById(R.id.tvUserId);
        edFName = findViewById(R.id.edFName);
        edLName = findViewById(R.id.edLName);
        edUsername = findViewById(R.id.edUsername);
        btnUpdate = findViewById(R.id.btnUpdate);
        // Initialize Database Reference
        userDatabase = FirebaseDatabase.getInstance().getReference("Users");
        // Print User's full name and user id in header section beneath the user icon
        String fullName = UserSingleton.getUser().getFirstName() + " " + UserSingleton.getUser().getLastName();
        String userId = String.valueOf(UserSingleton.getUser().getUserId());
        tvFullName.setText(fullName);
        tvUserId.setText(userId);

        // Set onClick listener for the update button
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        if(!edFName.getText().toString().trim().isEmpty()) {
            UserSingleton.getUser().setFirstName(edFName.getText().toString().trim());
        }
        if(!edLName.getText().toString().trim().isEmpty()) {
            UserSingleton.getUser().setLastName(edFName.getText().toString().trim());
        }
        if(!edUsername.getText().toString().trim().isEmpty()) {
            UserSingleton.getUser().setUserName(edUsername.getText().toString().trim());
        }
    }
}
