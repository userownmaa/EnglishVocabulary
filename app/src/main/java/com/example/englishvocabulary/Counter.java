package com.example.englishvocabulary;

import android.content.Context;
import android.content.SharedPreferences;

public class Counter {

    private static final String PREF_NAME = "GamePrefs";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static int getCounter(Context context) {
        return getPrefs(context).getInt("counter", 0);
    }

    public static void setCounter(Context context, int counter) {
        getPrefs(context).edit().putInt("counter", counter).apply();
    }

}