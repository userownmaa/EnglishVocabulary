package com.example.englishvocabulary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class ExerciseThreeActivity extends AppCompatActivity {

    int correctAnswerInt;
    int randInt;
    int correctFieldInt;
    TextView correctField;
    Array array = new Array();
    Random random = new Random();
    int n = 1;
    Set<Integer> usedNumbersCorrect = new HashSet<>();
    Set<Integer> usedNumbersRegular;
    Boolean foundCorrect = false;
    Boolean foundRegular = false;
    int indexLevel = Index.getIndexLevel();
    int indexTopic = Index.getIndexTopic();
    String correctWord;
    Handler handler;
    Runnable runnable;

    public void setCorrectWord(TextView rusWord, ImageView img) {
        correctField.setText(array.strings_lvl_words_eng[indexLevel][indexTopic][correctAnswerInt]);
        correctWord = getString(array.strings_lvl_words_rus[indexLevel][indexTopic][correctAnswerInt]);
        img.setImageResource(array.imgs_lvl[indexLevel][indexTopic][correctAnswerInt]);
        rusWord.setText(array.strings_lvl_words_rus[indexLevel][indexTopic][correctAnswerInt]);
    }

    public void setRandomInts(TextView[] fields) {
        usedNumbersRegular = new HashSet<>();
        usedNumbersRegular.add(correctAnswerInt);
        for (int i = 0; i < 4; i++) {
            if (i != correctFieldInt) {
                while (!foundRegular) {
                    randInt= random.nextInt(10);
                    if (usedNumbersRegular.add(randInt)) foundRegular = true;
                }
                fields[i].setText(array.strings_lvl_words_eng[indexLevel][indexTopic][randInt]);
                foundRegular = false;
            }
        }
    }

    public void findCorrectInt(TextView[] fields) {
        while (!foundCorrect) {
            correctAnswerInt = random.nextInt(10);
            if (usedNumbersCorrect.add(correctAnswerInt)) foundCorrect = true;
        }
        foundCorrect = false;
        correctFieldInt = random.nextInt(4);
        correctField = fields[correctFieldInt];
    }

    public void end() {
        if (indexTopic < 2) {
            Intent intent = new Intent(ExerciseThreeActivity.this, ExerciseOneActivity.class);
            startActivity(intent);finish();
            Index.setIndexTopic(Index.getIndexTopic() + 1);
        } else {
            Intent intent = new Intent(ExerciseThreeActivity.this, ResultsActivity.class);
            startActivity(intent);finish();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_three);

        SharedPreferences sp = getSharedPreferences("Save", MODE_PRIVATE);

        TextView textLevel = findViewById(R.id.textview_three_level);
        TextView textTopic = findViewById(R.id.textview_three_topic);
        TextView textRusWord = findViewById(R.id.textview_three_russian_word);
        TextView textAnswerOne = findViewById(R.id.textview_three_answer1);
        TextView textAnswerTwo = findViewById(R.id.textview_three_answer2);
        TextView textAnswerThree = findViewById(R.id.textview_three_answer3);
        TextView textAnswerFour = findViewById(R.id.textview_three_answer4);
        TextView[] fields = {
                textAnswerOne,
                textAnswerTwo,
                textAnswerThree,
                textAnswerFour,
        };

        ImageView img_three = findViewById(R.id.img_three);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            img_three.setClipToOutline(true);
        }

        textLevel.setText(array.strings_lvl_names[indexLevel]);
        textTopic.setText(array.strings_lvl_topics_eng[indexLevel][indexTopic]);

        findCorrectInt(fields);
        setCorrectWord(textRusWord, img_three);
        setRandomInts(fields);

        textAnswerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(correctWord, String.valueOf(textAnswerOne.getText()))) {
                    Counter.setCounter(ExerciseThreeActivity.this, Counter.getCounter(ExerciseThreeActivity.this) + 1);
                    textAnswerOne.setBackground(getDrawable(R.drawable.style_textview_green));
                } else {
                    textAnswerOne.setBackground(getDrawable(R.drawable.style_textview_red));
                    correctField.setBackground(getDrawable(R.drawable.style_textview_green));
                }
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        textAnswerOne.setBackground(getDrawable(R.drawable.style_textview));
                        correctField.setBackground(getDrawable(R.drawable.style_textview));
                        if (n < 5) {
                            findCorrectInt(fields);
                            setCorrectWord(textRusWord, img_three);
                            setRandomInts(fields);
                            n++;
                        } else {
                            end();
                        }
                    }
                };

                handler.postDelayed(runnable, 500);
            }
        });


        textAnswerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(correctWord, String.valueOf(textAnswerOne.getText()))) {
                    Counter.setCounter(ExerciseThreeActivity.this, Counter.getCounter(ExerciseThreeActivity.this) + 1);
                    textAnswerTwo.setBackground(getDrawable(R.drawable.style_textview_green));
                } else {
                    textAnswerTwo.setBackground(getDrawable(R.drawable.style_textview_red));
                    correctField.setBackground(getDrawable(R.drawable.style_textview_green));
                }
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        textAnswerTwo.setBackground(getDrawable(R.drawable.style_textview));
                        correctField.setBackground(getDrawable(R.drawable.style_textview));
                        if (n < 5) {
                            findCorrectInt(fields);
                            setCorrectWord(textRusWord, img_three);
                            setRandomInts(fields);
                            n++;
                        } else {
                            end();
                        }
                    }
                };

                handler.postDelayed(runnable, 500);
            }
        });

        textAnswerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(correctWord, String.valueOf(textAnswerOne.getText()))) {
                    Counter.setCounter(ExerciseThreeActivity.this, Counter.getCounter(ExerciseThreeActivity.this) + 1);
                    textAnswerThree.setBackground(getDrawable(R.drawable.style_textview_green));
                } else {
                    textAnswerThree.setBackground(getDrawable(R.drawable.style_textview_red));
                    correctField.setBackground(getDrawable(R.drawable.style_textview_green));
                }
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        textAnswerThree.setBackground(getDrawable(R.drawable.style_textview));
                        correctField.setBackground(getDrawable(R.drawable.style_textview));
                        if (n < 5) {
                            findCorrectInt(fields);
                            setCorrectWord(textRusWord, img_three);
                            setRandomInts(fields);
                            n++;
                        } else {
                            end();
                        }
                    }
                };

                handler.postDelayed(runnable, 500);
            }
        });

        textAnswerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(correctWord, String.valueOf(textAnswerOne.getText()))) {
                    Counter.setCounter(ExerciseThreeActivity.this, Counter.getCounter(ExerciseThreeActivity.this) + 1);
                    textAnswerFour.setBackground(getDrawable(R.drawable.style_textview_green));
                } else {
                    textAnswerFour.setBackground(getDrawable(R.drawable.style_textview_red));
                    correctField.setBackground(getDrawable(R.drawable.style_textview_green));
                }
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        textAnswerFour.setBackground(getDrawable(R.drawable.style_textview));
                        correctField.setBackground(getDrawable(R.drawable.style_textview));
                        if (n < 5) {
                            findCorrectInt(fields);
                            setCorrectWord(textRusWord, img_three);
                            setRandomInts(fields);
                            n++;
                        } else {
                            end();
                        }
                    }
                };

                handler.postDelayed(runnable, 500);
            }
        });



    }
}
