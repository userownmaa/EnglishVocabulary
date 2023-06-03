package com.example.englishvocabulary;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LevelsActivity extends AppCompatActivity {

    public void start() {
        Intent intent = new Intent(LevelsActivity.this, ExerciseOneActivity.class);
        startActivity(intent);
        finish();
    }
    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    public void openLevel(TextView textLvl, Button btnLvl, ProgressBar pgLvl, RelativeLayout layout) {
        btnLvl.setEnabled(true);
        btnLvl.setBackground(getDrawable(R.drawable.style_button));
        btnLvl.setTextColor(getResources().getColor(R.color.black));
        layout.setBackground(getDrawable(R.drawable.style_textview));
        pgLvl.setProgressDrawable(getDrawable(R.drawable.style_progressbar));
        textLvl.setTextColor(getResources().getColor(R.color.black));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        Counter.setCounter(this, 0);

        SharedPreferences sp = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        int beginnerLvl = sp.getInt("level_beginner", 0);
        int elementaryLvl = sp.getInt("level_elementary", 0);
        int intermediateLvl = sp.getInt("level_intermediate", 0);
        int upIntermediateLvl = sp.getInt("level_up_intermediate", 0);
        int advancedLvl = sp.getInt("level_advanced", 0);
        int proficiencyLvl = sp.getInt("level_proficiency", 0);

        TextView btnGoBack = findViewById(R.id.button_levels_go_back);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelsActivity.this, MainActivity.class);
                startActivity(intent);finish();
            }
        });

        TextView textLvlOne = findViewById(R.id.textview_beginner);
        TextView textLvlTwo = findViewById(R.id.textview_elementary);
        TextView textLvlThree = findViewById(R.id.textview_intermediate);
        TextView textLvlFour = findViewById(R.id.textview_up_intermediate);
        TextView textLvlFive = findViewById(R.id.textview_advanced);
        TextView textLvlSix = findViewById(R.id.textview_proficiency);

        RelativeLayout layoutOne = findViewById(R.id.cardview_a1);
        RelativeLayout layoutTwo = findViewById(R.id.cardview_a2);
        RelativeLayout layoutThree = findViewById(R.id.cardview_b1);
        RelativeLayout layoutFour = findViewById(R.id.cardview_b2);
        RelativeLayout layoutFive = findViewById(R.id.cardview_c1);
        RelativeLayout layoutSix = findViewById(R.id.cardview_c2);

        Button btnLvlOne = findViewById(R.id.button_start_a1);
        Button btnLvlTwo = findViewById(R.id.button_start_a2);
        Button btnLvlThree = findViewById(R.id.button_start_b1);
        Button btnLvlFour = findViewById(R.id.button_start_b2);
        Button btnLvlFive = findViewById(R.id.button_start_c1);
        Button btnLvlSix = findViewById(R.id.button_start_c2);

        ProgressBar pgLvlOne = findViewById(R.id.progress_bar_a1);
        ProgressBar pgLvlTwo = findViewById(R.id.progress_bar_a2);
        ProgressBar pgLvlThree = findViewById(R.id.progress_bar_b1);
        ProgressBar pgLvlFour = findViewById(R.id.progress_bar_b2);
        ProgressBar pgLvlFive = findViewById(R.id.progress_bar_c1);
        ProgressBar pgLvlSix = findViewById(R.id.progress_bar_c2);

        pgLvlOne.setProgress(beginnerLvl);
        pgLvlTwo.setProgress(elementaryLvl);
        pgLvlThree.setProgress(intermediateLvl);
        pgLvlFour.setProgress(upIntermediateLvl);
        pgLvlFive.setProgress(advancedLvl);
        pgLvlSix.setProgress(proficiencyLvl);


        if (beginnerLvl >= 70) {
            openLevel(textLvlTwo, btnLvlTwo, pgLvlTwo, layoutTwo);
        }  else { btnLvlTwo.setEnabled(false); }
        if (elementaryLvl >= 70) {
            openLevel(textLvlThree, btnLvlThree, pgLvlThree, layoutThree);
        } else { btnLvlThree.setEnabled(false); }
        if (intermediateLvl >= 70) {
            openLevel(textLvlFour, btnLvlFour, pgLvlFour, layoutFour);
        } else { btnLvlFour.setEnabled(false); }
        if (upIntermediateLvl >= 70) {
            openLevel(textLvlFive, btnLvlFive, pgLvlFive, layoutFive);
        } else { btnLvlFive.setEnabled(false); }
        if (advancedLvl >= 70) {
            openLevel(textLvlSix, btnLvlSix, pgLvlSix, layoutSix);
        } else { btnLvlSix.setEnabled(false); }

        btnLvlOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
                Index.setIndexLevel(0);
                Index.setIndexTopic(0);
            }
        });
        btnLvlTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
                Index.setIndexLevel(1);
                Index.setIndexTopic(0);
            }
        });
        btnLvlThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
                Index.setIndexLevel(2);
                Index.setIndexTopic(0);
            }
        });
        btnLvlFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
                Index.setIndexLevel(3);
                Index.setIndexTopic(0);
            }
        });
        btnLvlFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
                Index.setIndexLevel(4);
                Index.setIndexTopic(0);
            }
        });
        btnLvlSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
                Index.setIndexLevel(5);
                Index.setIndexTopic(0);
            }
        });


    }
}
