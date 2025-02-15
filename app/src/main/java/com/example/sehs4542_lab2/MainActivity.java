package com.example.sehs4542_lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnOpenTextMovement = findViewById(R.id.btnOpenTextMovement);
        Button btnOpenCalculator = findViewById(R.id.btnOpenCalculator);
        Button btnOpenLanguageSettings = findViewById(R.id.btnOpenLanguageSettings);

        btnOpenTextMovement.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TextMovementActivity.class);
            startActivity(intent);
        });

        btnOpenCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });

        btnOpenLanguageSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LanguageSettingsActivity.class);
            startActivity(intent);
        });
    }
}