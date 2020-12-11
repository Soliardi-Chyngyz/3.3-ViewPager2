package com.example.viewpager2.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    private static SharedPreferences preferences;
    private static final String USER_PASS = "kg_geektech.lesson3.user_pass";
    private static final String APP_PREF = "kg.geecktech.lesson3.app_pref";
    private static final String USER_NAME = "kg_geektech.lesson3.user_name";

    public static void init(Context context) {
        preferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
    }

    public static void saveUserName (String userName) {
        // под ключом key сохраняем значение userName
        preferences.edit().putString(USER_NAME, userName).apply();
    }

    public static String getUserName () {
        return preferences.getString(USER_NAME, "");
    }

    public static void saveUserPass (String userPass) {
        preferences.edit().putString(USER_PASS, userPass).apply();
    }

    public static String getUserPass () {
        return preferences.getString(USER_PASS, "");
    }

}
