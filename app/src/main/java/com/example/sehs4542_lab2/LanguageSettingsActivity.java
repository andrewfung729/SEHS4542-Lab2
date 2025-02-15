package com.example.sehs4542_lab2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class LanguageSettingsActivity extends AppCompatActivity {
    private RadioGroup languageGroup;
    private RadioButton traditionalChineseRadio, simplifiedChineseRadio, englishRadio;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_settings);

        // Initialize views
        languageGroup = findViewById(R.id.languageRadioGroup);
        traditionalChineseRadio = findViewById(R.id.radioTraditionalChinese);
        simplifiedChineseRadio = findViewById(R.id.radioSimplifiedChinese);
        englishRadio = findViewById(R.id.radioEnglish);
        btnBack = findViewById(R.id.btnBack);

        // Set initial selection based on current locale
        String currentLanguage = getResources().getConfiguration().locale.getLanguage();
        String currentRegion = getResources().getConfiguration().locale.getCountry();

        if (currentLanguage.equals("zh")) {
            if (currentRegion.equals("TW")) {
                traditionalChineseRadio.setChecked(true);
            } else if (currentRegion.equals("CN")) {
                simplifiedChineseRadio.setChecked(true);
            }
        } else {
            englishRadio.setChecked(true);
        }

        // Handle language selection
        languageGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioTraditionalChinese) {
                setLocale("zh", "TW");
            } else if (checkedId == R.id.radioSimplifiedChinese) {
                setLocale("zh", "CN");
            } else if (checkedId == R.id.radioEnglish) {
                setLocale("en", "");
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void setLocale(String language, String region) {
        Locale locale;
        if (region.isEmpty()) {
            locale = new Locale(language);
        } else {
            locale = new Locale(language, region);
        }
        
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

        // Restart the activity to apply changes
        Intent refresh = new Intent(this, LanguageSettingsActivity.class);
        refresh.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(refresh);
        finish();
    }

    // Helper method to apply locale changes to the entire app
    public static void applyLanguage(Context context, String language, String region) {
        Locale locale;
        if (region.isEmpty()) {
            locale = new Locale(language);
        } else {
            locale = new Locale(language, region);
        }
        
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
