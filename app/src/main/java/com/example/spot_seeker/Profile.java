package com.example.spot_seeker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Objects;

public class Profile extends Fragment {

    private TextView emailTextView, phoneTextView, addressTextView;
    private EditText updateEmail, updatePhone, updateAddress;
    private Button updateInformation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize UI elements
        emailTextView = view.findViewById(R.id.emailTextView);
        phoneTextView = view.findViewById(R.id.phoneTextView);
        addressTextView = view.findViewById(R.id.addressTextView);
        updateEmail = view.findViewById(R.id.updateEmail);
        updatePhone = view.findViewById(R.id.updatePhone);
        updateAddress = view.findViewById(R.id.updateAddress);
        updateInformation = view.findViewById(R.id.Updateinformation);

        // Set onClickListener for the updateInformation button
        updateInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the new email, phone, and address from the corresponding EditText fields
                String newEmail = updateEmail.getText().toString().trim();
                String newPhone = updatePhone.getText().toString().trim();
                String newAddress = updateAddress.getText().toString().trim();

                // Update the TextViews with the new information
                if (!newEmail.isEmpty()) {
                    emailTextView.setText(newEmail);
                    updateEmail.setText("");
                }
                if (!newPhone.isEmpty()) {
                    phoneTextView.setText(newPhone);
                    updatePhone.setText("");
                }
                if (!newAddress.isEmpty()) {
                    addressTextView.setText(newAddress);
                    updateAddress.setText("");
                }
            }
        });
        return view;
    }
}
