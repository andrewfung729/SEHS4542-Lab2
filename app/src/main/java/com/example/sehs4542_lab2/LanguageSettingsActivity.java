package com.example.sehs4542_lab2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private static final String PREF_NAME = "LanguagePrefs";
    private static final String PREF_LANGUAGE = "language";
    private static final String PREF_REGION = "region";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Apply saved language before setting content view
        applyLanguageFromPreferences(this);
        
        setContentView(R.layout.activity_language_settings);

        // Initialize views
        languageGroup = findViewById(R.id.languageRadioGroup);
        traditionalChineseRadio = findViewById(R.id.radioTraditionalChinese);
        simplifiedChineseRadio = findViewById(R.id.radioSimplifiedChinese);
        englishRadio = findViewById(R.id.radioEnglish);
        btnBack = findViewById(R.id.btnBack);

        // Set initial selection based on saved preferences
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String savedLanguage = prefs.getString(PREF_LANGUAGE, "en");
        String savedRegion = prefs.getString(PREF_REGION, "");

        if (savedLanguage.equals("zh")) {
            if (savedRegion.equals("TW")) {
                traditionalChineseRadio.setChecked(true);
            } else if (savedRegion.equals("CN")) {
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

        btnBack.setOnClickListener(v -> {
            // Restart main activity to apply language changes
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private void setLocale(String language, String region) {
        // Save language preferences
        SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(PREF_LANGUAGE, language);
        editor.putString(PREF_REGION, region);
        editor.apply();

        // Apply the new locale
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
        recreate();
    }

    // Static method to apply saved language preferences
    public static void applyLanguageFromPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String language = prefs.getString(PREF_LANGUAGE, "en");
        String region = prefs.getString(PREF_REGION, "");

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
