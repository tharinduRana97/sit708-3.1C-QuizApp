package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {
    TextView textViewName, textViewQuestion, textViewProgress;
    RadioGroup radioGroup;
    RadioButton option1, option2, option3, option4;
    Button buttonSubmit;
    ProgressBar progressBar;

    String[] questions = {"Capital of France?", "5 + 3 = ?",  "Which planet is known as the Red Planet?"};
    String[][] options = {{"Paris", "Berlin", "Madrid", "Rome"}, {"6", "7", "8", "9"}, {"Earth", "Venus", "Mars", "Jupiter"}};
    int[] answers = {0, 2, 2};
    int index = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewName = findViewById(R.id.textViewName);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewProgress = findViewById(R.id.textViewProgress);
        progressBar = findViewById(R.id.progressBar);
        String username = getIntent().getStringExtra("userName");
        textViewName.setText("Welcome " + username);
        loadQuestion();

        buttonSubmit.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) return;
            RadioButton selectedButton = findViewById(selectedId);
            int selectedIndex = radioGroup.indexOfChild(selectedButton);

            // Show correct/incorrect coloring
            if (selectedIndex == answers[index]) {
                selectedButton.setBackgroundColor(Color.GREEN);
                score++;
            } else {
                selectedButton.setBackgroundColor(Color.RED);
                RadioButton correctBtn = (RadioButton) radioGroup.getChildAt(answers[index]);
                correctBtn.setBackgroundColor(Color.GREEN);
            }

            buttonSubmit.setEnabled(false);
            buttonSubmit.postDelayed(() -> {
                index++;
                if (index < questions.length) {
                    loadQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("total", questions.length);
                    intent.putExtra("userName", username);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        });
    }

    void loadQuestion() {
        textViewQuestion.setText(questions[index]);
        option1.setText(options[index][0]);
        option2.setText(options[index][1]);
        option3.setText(options[index][2]);
        option4.setText(options[index][3]);
        radioGroup.clearCheck();
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
        }
        buttonSubmit.setEnabled(true);
        int progress = (index * 100) / questions.length;
        progressBar.setProgress(progress);
        textViewProgress.setText((index + 1) + "/" + questions.length);
    }
}