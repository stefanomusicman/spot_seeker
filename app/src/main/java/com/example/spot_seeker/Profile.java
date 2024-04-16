package com.example.spot_seeker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    private TextView emailTextView, phoneTextView, addressTextView;
    private EditText updateEmail, updatePhone, updateAddress;
    private Button updateInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize UI elements
        emailTextView = findViewById(R.id.emailTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        addressTextView = findViewById(R.id.addressTextView);
        updateEmail = findViewById(R.id.updateEmail);
        updatePhone = findViewById(R.id.updatePhone);
        updateAddress = findViewById(R.id.updateAddress);
        updateInformation = findViewById(R.id.Updateinformation);

        // Set onClickListener for the updateInformation button
        updateInformation.setOnClickListener(view -> {
            // Handle the update information logic here
            // You can retrieve the new email, phone, and address from the corresponding EditText fields
            String newEmail = updateEmail.getText().toString().trim();
            String newPhone = updatePhone.getText().toString().trim();
            String newAddress = updateAddress.getText().toString().trim();

            // Update the TextViews with the new information
            emailTextView.setText(newEmail);
            phoneTextView.setText(newPhone);
            addressTextView.setText(newAddress);

            // Clear the EditText fields
            updateEmail.setText("");
            updatePhone.setText("");
            updateAddress.setText("");
        });
    }
}
