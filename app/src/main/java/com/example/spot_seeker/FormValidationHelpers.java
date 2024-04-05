package com.example.spot_seeker;

import android.widget.EditText;

import java.util.ArrayList;

public class FormValidationHelpers {
    public static boolean verifyEmptyFields(ArrayList<EditText> inputFields) {
        for(int i = 0; i < inputFields.size(); i++) {
            if(inputFields.get(i).getText().toString().length() == 0) {
                return false;
            }
        }
        return true;
    }
}
