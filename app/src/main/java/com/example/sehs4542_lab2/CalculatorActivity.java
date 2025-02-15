package com.example.sehs4542_lab2;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class CalculatorActivity extends AppCompatActivity {
    private EditText firstNumberInput, secondNumberInput;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculator);

        // Initialize views
        firstNumberInput = findViewById(R.id.editTextFirstNumber);
        secondNumberInput = findViewById(R.id.editTextSecondNumber);
        resultView = findViewById(R.id.textViewResult);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnReset = findViewById(R.id.btnReset);
        Button btnBack = findViewById(R.id.btnBack);

        // Set click listeners
        btnAdd.setOnClickListener(v -> calculate('+'));
        btnSubtract.setOnClickListener(v -> calculate('-'));
        btnMultiply.setOnClickListener(v -> calculate('×'));
        btnDivide.setOnClickListener(v -> calculate('÷'));
        btnReset.setOnClickListener(v -> resetCalculator());
        btnBack.setOnClickListener(v -> finish());
    }

    private void calculate(char operator) {
        try {
            double num1 = Double.parseDouble(firstNumberInput.getText().toString());
            double num2 = Double.parseDouble(secondNumberInput.getText().toString());
            double result = 0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '×':
                    result = num1 * num2;
                    break;
                case '÷':
                    if (num2 == 0) {
                        Toast.makeText(this, getString(R.string.error_divide_by_zero), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            // Format result to remove unnecessary decimal places
            String formattedResult = result % 1 == 0 ?
                String.format(Locale.getDefault(),"%.0f", result) : String.format(Locale.getDefault(),"%.2f", result);
            resultView.setText(formattedResult);

        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.error_invalid_number), Toast.LENGTH_SHORT).show();
        }
    }

    private void resetCalculator() {
        firstNumberInput.setText("");
        secondNumberInput.setText("");
        resultView.setText("");
    }
}
