package com.example.englishvocabulary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    int indexLevel = Index.getIndexLevel();

    @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        SharedPreferences sp = getSharedPreferences("Save", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        int counter = (int) (Counter.getCounter(this) * 1.5);
        int resultPercentInt = Math.round((counter * 100) / 45);

        int beginnerLvl = sp.getInt("level_beginner", 0);
        int elementaryLvl = sp.getInt("level_elementary", 0);
        int intermediateLvl = sp.getInt("level_intermediate", 0);
        int upIntermediateLvl = sp.getInt("level_up_intermediate", 0);
        int advancedLvl = sp.getInt("level_advanced", 0);
        int proficiencyLvl = sp.getInt("level_proficiency", 0);

        final int[] progresses = {
                beginnerLvl,
                elementaryLvl,
                intermediateLvl,
                upIntermediateLvl,
                advancedLvl,
                proficiencyLvl,
        };

        TextView textResultPercent = findViewById(R.id.textview_result_percent);
        TextView btnRedo = findViewById(R.id.button_result_redo);
        TextView btnNext = findViewById(R.id.button_result_next);
        TextView btnGoBack = findViewById(R.id.button_result_back);

        textResultPercent.setText(String.valueOf(resultPercentInt));

        if (resultPercentInt >= 70 || progresses[indexLevel] >= 70) {
            btnNext.setEnabled(true);
            btnNext.setBackground(getDrawable(R.drawable.style_button));
            btnNext.setTextColor(getResources().getColor(R.color.black));
        }  else { btnNext.setEnabled(false); }

        if (indexLevel == 0) {
            if (beginnerLvl < resultPercentInt) {
                editor.putInt("level_beginner", resultPercentInt);
                editor.apply();
            }
        }
        if (indexLevel == 1) {
            if (sp.getInt("level_elementary", 0) < resultPercentInt) {
                editor.putInt("level_elementary", resultPercentInt);
                editor.apply();
            }
        }
        if (indexLevel == 2) {
            if (sp.getInt("level_intermediate", 0) < resultPercentInt) {
                editor.putInt("level_intermediate", resultPercentInt);
                editor.apply();
            }
        }
        if (indexLevel == 3) {
            if (sp.getInt("level_up_intermediate", 0) < resultPercentInt) {
                editor.putInt("level_up_intermediate", resultPercentInt);
                editor.apply();
            }
        }if (indexLevel == 4) {
            if (sp.getInt("level_advanced", 0) < resultPercentInt) {
                editor.putInt("level_advanced", resultPercentInt);
                editor.apply();
            }
        }if (indexLevel == 5) {
            if (sp.getInt("level_proficiency", 0) < resultPercentInt) {
                editor.putInt("level_proficiency", resultPercentInt);
                editor.apply();
            }
        }

        btnRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter.setCounter(ResultsActivity.this, 0);
                Index.setIndexLevel(indexLevel);
                Index.setIndexTopic(0);
                Intent intent = new Intent(ResultsActivity.this, ExerciseOneActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter.setCounter(ResultsActivity.this, 0);
                Index.setIndexLevel(indexLevel + 1);
                Index.setIndexTopic(0);
                Intent intent = new Intent(ResultsActivity.this, ExerciseOneActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter.setCounter(ResultsActivity.this, 0);
                Index.setIndexLevel(0);
                Index.setIndexTopic(0);
                Intent intent = new Intent(ResultsActivity.this, LevelsActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
