package com.example.sehs4542_lab2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LanguageSettingsActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_settings);

        // Initialize views
        RadioGroup languageGroup = findViewById(R.id.languageRadioGroup);
        RadioButton traditionalChineseRadio = findViewById(R.id.radioTraditionalChinese);
        RadioButton simplifiedChineseRadio = findViewById(R.id.radioSimplifiedChinese);
        RadioButton englishRadio = findViewById(R.id.radioEnglish);
        Button btnBack = findViewById(R.id.btnBack);

        // Set initial selection based on saved preferences
        SharedPreferences prefs = getSharedPreferences(App.PREF_NAME, Context.MODE_PRIVATE);
        String savedLanguage = prefs.getString(App.PREF_LANGUAGE, "en");
        String savedRegion = prefs.getString(App.PREF_REGION, "");

        // Set the correct radio button
        if (savedLanguage.equals("zh")) {
            if (savedRegion.equals("TW")) {
                traditionalChineseRadio.setChecked(true);
            } else if (savedRegion.equals("CN")) {
                simplifiedChineseRadio.setChecked(true);
            }
        } else {
            englishRadio.setChecked(true);
        }

        // Set up radio group listener
        languageGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioTraditionalChinese) {
                updateLanguage("zh", "TW");
            } else if (checkedId == R.id.radioSimplifiedChinese) {
                updateLanguage("zh", "CN");
            } else if (checkedId == R.id.radioEnglish) {
                updateLanguage("en", "");
            }
            recreate();
        });

        // Set up back button
        btnBack.setOnClickListener(v -> finish());
    }

    private void updateLanguage(String language, String region) {
        SharedPreferences.Editor editor = getSharedPreferences(App.PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(App.PREF_LANGUAGE, language);
        editor.putString(App.PREF_REGION, region);
        editor.apply();

        // 重啟應用，讓語言變更立即生效
        Intent intent = new Intent(this, MainActivity.class);  // 替換為應用主入口的 Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
