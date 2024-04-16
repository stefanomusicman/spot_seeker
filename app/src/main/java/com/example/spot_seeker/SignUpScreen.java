package com.example.spot_seeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import models.FormValidationHelpers;
import models.User;
import models.UserSingleton;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener {

    ImageView logoImage;
    TextView logoText, signUpText;
    EditText edFirstName, edLastName, edUsername, edEmail, edPassword;
    Button btnSignIn, btnSignUp;
    ArrayList<EditText> textFields = new ArrayList<EditText>();
    DatabaseReference databaseRef;
    boolean isFormValid = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        //horizontal transition animation
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        initialize();
    }

    private void initialize() {
        databaseRef = FirebaseDatabase.getInstance().getReference("Users");
        logoImage = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        signUpText = findViewById(R.id.sign_up_text);
        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        edEmail = findViewById(R.id.edEmail);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        populateEditTextList();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnSignIn) {
            ExecuteRegistration();
        }
        if(id == R.id.btnSignUp) {
            Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
            startActivity(intent);
            //horizontal transition animation
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }

    private void populateEditTextList() {
        textFields.add(edFirstName);
        textFields.add(edLastName);
        textFields.add(edUsername);
        textFields.add(edEmail);
        textFields.add(edPassword);
    }

    private void ExecuteRegistration() {
        // Check if all input fields have been filled with a value
        isFormValid = FormValidationHelpers.verifyEmptyFields(textFields);
        if(!isFormValid) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        } else {
            AddNewUser();
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
    }

    private void AddNewUser() {
        // Generate a User ID
        int id = (int) (Math.random() * 10000);
        String firstName = edFirstName.getText().toString();
        String lastName = edLastName.getText().toString();
        String userName = edUsername.getText().toString();
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        User newUser = new User(id, firstName, lastName, email, userName, password);
        // Store the user in the UserSingleton class that will be
        // accessible anywhere throughout the app
        UserSingleton.setUser(newUser);
        databaseRef.child(String.valueOf(id)).setValue(newUser);
    }
}