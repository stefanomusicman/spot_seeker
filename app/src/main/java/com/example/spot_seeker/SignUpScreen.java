package com.example.spot_seeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener {

    ImageView logoImage;
    TextView logoText, signUpText;
    TextInputLayout firstName, lastName, userName, email, password;
    Button btnSignIn, btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        //horizontal transition animation
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        initialize();
    }

    private void initialize() {
        logoImage = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        signUpText = findViewById(R.id.sign_up_text);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        userName = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnSignIn) {

        }
        if(id == R.id.btnSignUp) {
            Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
            startActivity(intent);
            //horizontal transition animation
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }
}