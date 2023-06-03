package com.example.englishvocabulary;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class ExerciseTwoActivity extends AppCompatActivity {

    int randInt1;
    Array array = new Array();
    Random random = new Random();
    int n = 1;
    Set<Integer> usedNumbers = new HashSet<>();
    Boolean found = false;
    int indexLevel = Index.getIndexLevel();
    int indexTopic = Index.getIndexTopic();
    String engWord = "";
    String correctWord;
    Handler handler;
    Runnable runnable;

    public void setNewWord(TextView rusWord) {
        rusWord.setText(array.strings_lvl_words_rus[indexLevel][indexTopic][randInt1]);
        correctWord = getString(array.strings_lvl_words_eng[indexLevel][indexTopic][randInt1]);
    }

    public void findNewInt() {
        while (!found) {
            randInt1 = random.nextInt(10);
            if (usedNumbers.add(randInt1)) found = true;
        }
        found = false;
    }

    public void end() {
        Intent intent = new Intent(ExerciseTwoActivity.this, ExerciseThreeActivity.class);
        startActivity(intent);finish();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_two);

        SharedPreferences sp = getSharedPreferences("Save", MODE_PRIVATE);

        TextView textLevel = findViewById(R.id.textview_two_level);
        TextView textTopic = findViewById(R.id.textview_two_topic);
        TextView textRusWord = findViewById(R.id.textview_two_russian_word);
        EditText editTextAnswer = findViewById(R.id.edittext_two_answer);
        TextView buttonAnswer = findViewById(R.id.button_two_answer);

        textLevel.setText(array.strings_lvl_names[indexLevel]);
        textTopic.setText(array.strings_lvl_topics_eng[indexLevel][indexTopic]);

        findNewInt();
        setNewWord(textRusWord);

        editTextAnswer.setInputType(InputType.TYPE_CLASS_TEXT);
        editTextAnswer.requestFocus();

        buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                engWord = String.valueOf(editTextAnswer.getText());
                if (engWord.equalsIgnoreCase(correctWord)) {
                    Counter.setCounter(ExerciseTwoActivity.this, Counter.getCounter(ExerciseTwoActivity.this) + 1);
                    editTextAnswer.setBackground(getDrawable(R.drawable.style_textview_green));
                } else {
                    editTextAnswer.setBackground(getDrawable(R.drawable.style_textview_red));
                    textRusWord.setText(correctWord);
                }
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        editTextAnswer.setBackground(getDrawable(R.drawable.style_textview));
                        editTextAnswer.getText().clear();
                        editTextAnswer.requestFocus();
                        if (n < 5) {
                            findNewInt();
                            setNewWord(textRusWord);
                            n++;
                        } else {
                            end();
                        }
                    }
                };

                handler.postDelayed(runnable, 700);
            }
        });


    }

}
