package edu.scu.kawa.advancebmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SharedPreferencesDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_demo);

        EditText nameEditText = findViewById(R.id.nameEditText);
        String name = load();
        nameEditText.setText(name);

        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(nameEditText.getText().toString());
            }
        });

        Button loadButton = findViewById(R.id.loadButton);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = load();
                nameEditText.setText(name);
            }
        });
    }

    void save(String name) {
        SharedPreferences settings = getSharedPreferences("INFO", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", name);
        editor.commit();
    }

    String load() {
        SharedPreferences settings = getSharedPreferences("INFO", MODE_PRIVATE);
        String name = settings.getString("name", "");
        return name;
    }
}