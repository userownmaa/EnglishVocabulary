package com.example.englishvocabulary;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ExerciseOneActivity extends AppCompatActivity {

    int randInt1;
    int randInt2;
    Array array = new Array();
    Random random = new Random();
    int n = 1;
    Set<Integer> usedNumbersEng = new HashSet<>();
    Set<Integer> usedNumbersRus = new HashSet<>();
    Boolean foundEng = false;
    Boolean foundRus = false;
    Dialog dialog_window;
    int indexLevel = Index.getIndexLevel();
    int indexTopic = Index.getIndexTopic();
    Handler handler;
    Runnable runnable;

    public void setNewWords(TextView engWord, TextView rusWord) {
        engWord.setText(array.strings_lvl_words_eng[indexLevel][indexTopic][randInt1]);
        rusWord.setText(array.strings_lvl_words_rus[indexLevel][indexTopic][randInt2]);
    }

    public void findNewInts() {
        while (!foundEng || !foundRus) {
            randInt1 = random.nextInt(10);
            randInt2 = random.nextInt(10);
            if (usedNumbersEng.add(randInt1)) foundEng = true;
            if (usedNumbersRus.add(randInt2)) foundRus = true;
            if (!foundEng && foundRus) {
                usedNumbersRus.remove(randInt2);
                foundRus = false;
            }
            if (foundEng && !foundRus) {
                usedNumbersEng.remove(randInt1);
                foundEng = false;
            }
        }
        foundEng = false;
        foundRus = false;
    }

    public void end() {
        Intent intent = new Intent(ExerciseOneActivity.this, ExerciseTwoActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exrecise_one);

        dialog_window = new Dialog(this);
        dialog_window.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_window.setContentView(R.layout.dialog_window);
        dialog_window.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView btnNext = dialog_window.findViewById(R.id.button_dialog_next);
        TextView btnBack = dialog_window.findViewById(R.id.button_dialog_back);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_window.dismiss();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(ExerciseOneActivity.this, LevelsActivity.class);
                startActivity(intent);finish();
                dialog_window.dismiss();
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView textIndexTopic = dialog_window.findViewById(R.id.textview_dialog_topic_index);
        textIndexTopic.setText(String.valueOf(indexTopic + 1));
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView textTopicEng = dialog_window.findViewById(R.id.textview_dialog_topic_eng);
        textTopicEng.setText(array.strings_lvl_topics_eng[indexLevel][indexTopic]);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView textTopicRus = dialog_window.findViewById(R.id.textview_dialog_topic_rus);
        textTopicRus.setText(array.strings_lvl_topics_rus[indexLevel][indexTopic]);

        dialog_window.show();

        SharedPreferences sp = getSharedPreferences("Save", MODE_PRIVATE);

        TextView textLevel = findViewById(R.id.textview_one_level);
        TextView textTopic = findViewById(R.id.textview_one_topic);
        TextView textEngWord = findViewById(R.id.textview_one_english_word);
        TextView textRusWord = findViewById(R.id.textview_one_russian_word);

        ImageView img_true = findViewById(R.id.img_true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img_true.setClipToOutline(true);
        }

        ImageView img_false = findViewById(R.id.img_false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img_false.setClipToOutline(true);
        }

        textLevel.setText(array.strings_lvl_names[indexLevel]);
        textTopic.setText(array.strings_lvl_topics_eng[indexLevel][indexTopic]);

        findNewInts();
        setNewWords(textEngWord, textRusWord);

        img_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (randInt1 == randInt2) {
                    Counter.setCounter(ExerciseOneActivity.this, Counter.getCounter(ExerciseOneActivity.this) + 1);
                    img_true.setImageResource(R.drawable.true_green);
                } else {
                    img_true.setImageResource(R.drawable.true_red);
                    img_false.setImageResource(R.drawable.false_green);
                }
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        img_true.setImageResource(R.drawable.true_basic);
                        img_false.setImageResource(R.drawable.false_basic);
                        if (n < 5) {
                            findNewInts();
                            setNewWords(textEngWord, textRusWord);
                            n++;
                        } else {
                            end();
                        }
                    }
                };

                handler.postDelayed(runnable, 400);
            }
        });

        img_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (randInt1 != randInt2) {
                    Counter.setCounter(ExerciseOneActivity.this, Counter.getCounter(ExerciseOneActivity.this) + 1);
                    img_false.setImageResource(R.drawable.false_green);
                } else {
                    img_false.setImageResource(R.drawable.false_red);
                    img_true.setImageResource(R.drawable.true_green);
                }
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        img_true.setImageResource(R.drawable.true_basic);
                        img_false.setImageResource(R.drawable.false_basic);
                        if (n < 5) {
                            findNewInts();
                            setNewWords(textEngWord, textRusWord);
                            n++;
                        } else {
                            end();
                        }
                    }
                };

                handler.postDelayed(runnable, 400);
            }
        });


    }
}
