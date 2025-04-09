package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);
        String username = getIntent().getStringExtra("userName");

        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewScoreValue = findViewById(R.id.textViewScoreValue);
        Button buttonRestart = findViewById(R.id.buttonRestart);
        Button buttonFinish = findViewById(R.id.buttonFinish);

        textViewName.setText("Congratulations "+username+"!");
        textViewScoreValue.setText(score + "/" + total);

        buttonRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            intent.putExtra("userName", username);
            startActivity(intent);
            finish();
        });

        buttonFinish.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}