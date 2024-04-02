package com.example.spot_seeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {
    Button btnSignIn, btnSignUp, btnforgotPassword;
    ImageView logoImage;
    TextView logoText;
    TextInputLayout username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initialize();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this, SignUpScreen.class);
                startActivity(intent);
                //horizontal transition animation
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        btnSignIn.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btnSignIn) {
                    ExecuteLogin();
                } else {
                    finish();
                }
            }
        });
    }

    private void initialize() {

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnforgotPassword = findViewById(R.id.btnForgot);
        logoImage = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
    }

    private void ExecuteLogin() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}