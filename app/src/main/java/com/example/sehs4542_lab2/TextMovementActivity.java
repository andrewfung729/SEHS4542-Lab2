package com.example.sehs4542_lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class TextMovementActivity extends AppCompatActivity {
    private EditText northText, southText, eastText, westText;
    private Button btnWestToNorth, btnNorthToEast, btnEastToSouth, btnSouthToWest;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Apply saved language settings before setting content view
        LanguageSettingsActivity.applyLanguageFromPreferences(this);
        
        setContentView(R.layout.activity_text_movement);

        // Initialize EditTexts
        northText = findViewById(R.id.editTextNorth);
        southText = findViewById(R.id.editTextSouth);
        eastText = findViewById(R.id.editTextEast);
        westText = findViewById(R.id.editTextWest);

        // Initialize Buttons
        btnWestToNorth = findViewById(R.id.btnWestToNorth);
        btnNorthToEast = findViewById(R.id.btnNorthToEast);
        btnEastToSouth = findViewById(R.id.btnEastToSouth);
        btnSouthToWest = findViewById(R.id.btnSouthToWest);
        btnBack = findViewById(R.id.btnBack);

        // Set up button click listeners
        btnWestToNorth.setOnClickListener(v -> moveText(westText, northText));
        btnNorthToEast.setOnClickListener(v -> moveText(northText, eastText));
        btnEastToSouth.setOnClickListener(v -> moveText(eastText, southText));
        btnSouthToWest.setOnClickListener(v -> moveText(southText, westText));
        
        btnBack.setOnClickListener(v -> finish());
    }

    private void moveText(EditText from, EditText to) {
        String fromText = from.getText().toString().trim();
        String toText = to.getText().toString().trim();
        
        // Only move text if source has text and destination is empty
        if (!fromText.isEmpty() && toText.isEmpty()) {
            to.setText(fromText);
            from.setText("");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reapply language settings when returning to activity
        LanguageSettingsActivity.applyLanguageFromPreferences(this);
    }
}
