package com.example.spot_seeker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import models.FormValidationHelpers;
import models.User;
import models.UserSingleton;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener, ValueEventListener {
    Button btnSignIn, btnSignUp, btnforgotPassword;
    ImageView logoImage;
    TextView logoText;
    EditText edUsername, edPassword;
    DatabaseReference databaseRef;
    ArrayList<EditText> textFields = new ArrayList<EditText>();
    boolean isFormValid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initialize();
    }

    private void initialize() {
        databaseRef = FirebaseDatabase.getInstance().getReference("Users");
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnforgotPassword = findViewById(R.id.btnForgot);
        logoImage = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        textFields.add(edUsername);
        textFields.add(edPassword);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnSignIn) {
            ExecuteLogin();
        }
        if(id == R.id.btnSignUp) {
            Intent intent = new Intent(LoginScreen.this, SignUpScreen.class);
            startActivity(intent);
            //horizontal transition animation
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }

    private void ExecuteLogin() {
        isFormValid = FormValidationHelpers.verifyEmptyFields(textFields);
        if(!isFormValid) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        } else {
            // Search through the Users collection to see if there is a document that
            // exists with the username that was entered
            String userName = edUsername.getText().toString();
            databaseRef.orderByChild("userName").equalTo(userName).addListenerForSingleValueEvent(this);
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists()) {
            try {
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    String id = childSnapshot.child("userId").getValue().toString();
                    String firstName = childSnapshot.child("firstName").getValue().toString();
                    String lastName = childSnapshot.child("lastName").getValue().toString();
                    String email = childSnapshot.child("email").getValue().toString();
                    String userName = childSnapshot.child("userName").getValue().toString();
                    String password = childSnapshot.child("password").getValue().toString();

                    ArrayList<Integer> listingIds = new ArrayList<>();
                    ArrayList<Integer> transactionIds = new ArrayList<>();

                    Iterable<DataSnapshot> listingIdsData = childSnapshot.child("listingIds").getChildren();
                    Iterable<DataSnapshot> transactionIdsData = childSnapshot.child("transactionIds").getChildren();

                    // Iterate for listing ids
                    for(DataSnapshot idSnapshot : listingIdsData) {
                        Integer listingId = idSnapshot.getValue(Integer.class);
                        if(listingId != null) {
                            listingIds.add(listingId);
                        }
                    }
                    // Iterate for transaction ids
                    for(DataSnapshot idSnapshot : transactionIdsData) {
                        Integer transactionId = idSnapshot.getValue(Integer.class);
                        if(transactionId != null) {
                            transactionIds.add(transactionId);
                        }
                    }

                    Log.d("TRANSACTION IDs", "THIS IS THE AMOUNT OF TRANSACTION IDS: " + transactionIds.size());
                    Log.d("LISTING IDs", "THIS IS THE AMOUNT OF LISTING IDS: " + listingIds.size());
                    if (edPassword.getText().toString().equals(password)) {
                        int userId = Integer.valueOf(id);
                        User loggedInUser = new User(userId, firstName, lastName, email, userName, password, listingIds, transactionIds);
                        // Store the user in the UserSingleton class that will be
                        // accessible anywhere throughout the app
                        UserSingleton.setUser(loggedInUser);
                        Intent intent = new Intent(this, HomeScreen.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                Log.d("ERROR: ", e.getMessage());
            }
        } else {
            Toast.makeText(this, "There is no user with this username " + edUsername.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        Log.e("Firebase Query", "Query cancelled: " + error.getMessage());
    }
}