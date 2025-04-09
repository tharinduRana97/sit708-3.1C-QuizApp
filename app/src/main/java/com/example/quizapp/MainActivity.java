package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        buttonStart = findViewById(R.id.buttonStart);

        SharedPreferences prefs = getSharedPreferences("QuizAppPrefs", MODE_PRIVATE);
        String name = prefs.getString("username", "");
        editTextName.setText(name);

        buttonStart.setOnClickListener(v -> {
            String userName = editTextName.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.apply();

            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        });
    }
}