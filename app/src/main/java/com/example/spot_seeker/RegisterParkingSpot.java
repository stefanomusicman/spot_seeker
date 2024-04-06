package com.example.spot_seeker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashSet;
import java.util.Set;

public class RegisterParkingSpot extends AppCompatActivity {

   TextInputLayout addressTextInputLayout, pricePerHourTextInputLayout, additionalServicesTextInputLayout;
    MaterialAutoCompleteTextView additionalServicesAutoCompleteTextView;
    CheckBox termsCheckBox;
    Button registerSpotButton;
    RecyclerView selectedOptionsRecyclerView;
    SelectedOptionsAdapter adapter;
    Set<String> selectedOptionsSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_parking_spot);
        initialize();

        selectedOptionsSet = new HashSet<>();
        adapter = new SelectedOptionsAdapter(selectedOptionsSet);
        selectedOptionsRecyclerView.setAdapter(adapter);
        selectedOptionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //create a list of additional services
        additionalServicesAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                selectedOptionsSet.add(selectedItem);
                adapter.notifyDataSetChanged();
                selectedOptionsRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        //delete additional services
        adapter.setOnItemLongClickListener(new SelectedOptionsAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(String option) {
                selectedOptionsSet.remove(option);
                adapter.notifyDataSetChanged();
            }
        });

        registerSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!termsCheckBox.isChecked()) {
                    Toast.makeText(RegisterParkingSpot.this, "Please Accept Terms and Conditions", Toast.LENGTH_SHORT).show();
                } else if (validateFields()) {
                    registerParkingSpot();
                }
            }
        });
    }

    private void initialize() {
        addressTextInputLayout = findViewById(R.id.address);
        pricePerHourTextInputLayout = findViewById(R.id.pricePerHour);
        additionalServicesTextInputLayout = findViewById(R.id.additionalServices);
        additionalServicesAutoCompleteTextView = findViewById(R.id.optionsInput);
        termsCheckBox = findViewById(R.id.checkBoxTerms);
        registerSpotButton = findViewById(R.id.btnRegisterSpot);
        selectedOptionsRecyclerView = findViewById(R.id.selectedOptionsRecyclerView);
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
        // Implement your logic to register the parking spot here
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
