package com.example.spot_seeker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.User;
import models.UserSingleton;

public class Profile extends AppCompatActivity {

    private TextView tvFullName, tvUserId;
    private EditText edFName, edLName, edUsername;
    private Button btnUpdate;
    private DatabaseReference usersDatabase;

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
        usersDatabase = FirebaseDatabase.getInstance().getReference("Users");
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
        boolean fName = edFName.getText().toString().trim().isEmpty();
        boolean lName = edLName.getText().toString().trim().isEmpty();
        User loggedInUser = UserSingleton.getUser();
        boolean userName = edUsername.getText().toString().trim().isEmpty();
        if(!fName) {
            loggedInUser.setFirstName(edFName.getText().toString().trim());
        }
        if(!lName) {
            loggedInUser.setLastName(edLName.getText().toString().trim());
        }
        if(!userName) {
            loggedInUser.setUserName(edUsername.getText().toString().trim());
        }
        if(fName && lName && userName) {
            Toast.makeText(this, "Nothing to update", Toast.LENGTH_SHORT).show();
        } else {
            // UPDATE THE DATABASE
            usersDatabase.child(String.valueOf(loggedInUser.getUserId())).setValue(loggedInUser);
            // SHOW CONFIRMATION MESSAGE
            Toast.makeText(this, "User has been successfully updated!", Toast.LENGTH_SHORT).show();
            // CLEAR ALL INPUT FIELDS
            edFName.setText("");
            edLName.setText("");
            edUsername.setText("");
            // UPDATE THE USER'S NAME IN THE HEADER SECTION
            String fullName = UserSingleton.getUser().getFirstName() + " " + UserSingleton.getUser().getLastName();
            tvFullName.setText(fullName);
        }
    }
}
