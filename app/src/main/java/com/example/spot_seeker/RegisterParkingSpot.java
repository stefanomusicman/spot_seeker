package com.example.spot_seeker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import models.Listing;
import models.User;
import models.UserSingleton;

public class RegisterParkingSpot extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout addressTextInputLayout, pricePerHourTextInputLayout, additionalServicesTextInputLayout;
    MaterialAutoCompleteTextView additionalServicesAutoCompleteTextView;
    CheckBox termsCheckBox;
    Button registerSpotButton;
    RecyclerView selectedOptionsRecyclerView;
    SelectedOptionsAdapter adapter;
    Set<String> selectedOptionsSet;
    DatabaseReference usersDatabase, listingsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_parking_spot);
        initialize();
    }

    private void initialize() {
        addressTextInputLayout = findViewById(R.id.address);
        pricePerHourTextInputLayout = findViewById(R.id.pricePerHour);
        additionalServicesTextInputLayout = findViewById(R.id.additionalServices);
        additionalServicesAutoCompleteTextView = findViewById(R.id.optionsInput);
        termsCheckBox = findViewById(R.id.checkBoxTerms);
        registerSpotButton = findViewById(R.id.btnRegisterSpot);
        selectedOptionsRecyclerView = findViewById(R.id.selectedOptionsRecyclerView);

        selectedOptionsSet = new HashSet<>();
        adapter = new SelectedOptionsAdapter(selectedOptionsSet);
        selectedOptionsRecyclerView.setAdapter(adapter);
        selectedOptionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ---------------------------- DATABASE REFERENCES -------------------------------
        usersDatabase = FirebaseDatabase.getInstance().getReference("Users");
        listingsDatabase = FirebaseDatabase.getInstance().getReference("Listings");

        // ---------------------------- ONCLICK LISTENERS ---------------------------------
        registerSpotButton.setOnClickListener(this);
        additionalServicesAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                selectedOptionsSet.add(selectedItem);
                adapter.notifyDataSetChanged();
                selectedOptionsRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        adapter.setOnItemLongClickListener(new SelectedOptionsAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(String option) {
                selectedOptionsSet.remove(option);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.btnRegisterSpot) {
            if (!termsCheckBox.isChecked()) {
                Toast.makeText(RegisterParkingSpot.this, "Please Accept Terms and Conditions", Toast.LENGTH_SHORT).show();
            }
            if (validateFields()) {
                registerParkingSpot();
            }
        }
    }

    private boolean validateFields() {
        boolean isValid = true;

        String address = addressTextInputLayout.getEditText().getText().toString().trim();
        if (address.isEmpty()) {
            addressTextInputLayout.setError("Address cannot be empty");
            isValid = false;
        } else {
            addressTextInputLayout.setError(null);
        }

        String price = pricePerHourTextInputLayout.getEditText().getText().toString().trim();
        if (price.isEmpty()) {
            pricePerHourTextInputLayout.setError("Price cannot be empty");
            isValid = false;
        } else {
            pricePerHourTextInputLayout.setError(null);
        }

        return isValid;
    }

    //method to register spot
    private void registerParkingSpot() {
        // Generate a Listing ID
        int listingId = (int) (Math.random() * 10000);
        String address = addressTextInputLayout.getEditText().getText().toString();
        // Convert the Selected Options set to an ArrayList
        ArrayList<String> selectedOptionsList = new ArrayList<>(selectedOptionsSet);
        // Create the new Listing Object
        Listing newListing = new Listing(listingId, address, selectedOptionsList);
        // Add the Listing ID to the listing Id array list of the user
        User loggedInUser = UserSingleton.getUser();
        loggedInUser.getListingIds().add(listingId);
        // Update the user in the database
        usersDatabase.child(String.valueOf(loggedInUser.getUserId())).setValue(loggedInUser);
        // Add the new Listing to the database
        listingsDatabase.child(String.valueOf(listingId)).setValue(newListing);
        // Clear all fields and present a confirmation message that listing has been successfully created
        addressTextInputLayout.getEditText().setText("");
        pricePerHourTextInputLayout.getEditText().setText("");
        selectedOptionsSet.clear();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Parking spot registered successfully!", Toast.LENGTH_LONG).show();
    }

    // Adapter for RecyclerView/Additional Services
    public static class SelectedOptionsAdapter extends RecyclerView.Adapter<SelectedOptionsAdapter.SelectedOptionViewHolder> {
        private final Set<String> selectedOptionsSet;
        private OnItemLongClickListener onItemLongClickListener;

        public SelectedOptionsAdapter(Set<String> selectedOptionsSet) {
            this.selectedOptionsSet = selectedOptionsSet;
        }

        public void setOnItemLongClickListener(OnItemLongClickListener listener) {
            this.onItemLongClickListener = listener;
        }

        @Override
        public SelectedOptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_option_item, parent, false);
            return new SelectedOptionViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SelectedOptionViewHolder holder, int position) {
            String selectedOption = (String) selectedOptionsSet.toArray()[position];
            holder.selectedOptionItemTextView.setText(selectedOption);

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onItemLongClickListener != null) {
                        onItemLongClickListener.onItemLongClick(selectedOption);
                        return true;
                    }
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return selectedOptionsSet.size();
        }

        public static class SelectedOptionViewHolder extends RecyclerView.ViewHolder {
            public TextView selectedOptionItemTextView;

            public SelectedOptionViewHolder(View itemView) {
                super(itemView);
                selectedOptionItemTextView = itemView.findViewById(R.id.selectedOptionItemTextView);
            }
        }

        public interface OnItemLongClickListener {
            void onItemLongClick(String option);
        }
    }
}
