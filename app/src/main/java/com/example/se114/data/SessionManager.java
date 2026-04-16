package com.example.se114.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.se114.model.User;

public class SessionManager {
    private static final String PREF_NAME = "social_mini_pref";

    private static final String KEY_IS_LOGIN = "is_login";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_AVATAR = "avatar";
    private static final String KEY_DESC = "description";

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void saveUser(User user) {
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_ADDRESS, user.getAddress());
        editor.putString(KEY_AVATAR, user.getAvatarUrl());
        editor.putString(KEY_DESC, user.getDescription());
        editor.apply();
    }

    public User getUser() {
        return new User(
            preferences.getString(KEY_NAME, ""),
            preferences.getString(KEY_EMAIL, ""),
            preferences.getString(KEY_PASSWORD, ""),
            preferences.getString(KEY_ADDRESS, ""),
            preferences.getString(KEY_AVATAR, ""),
            preferences.getString(KEY_DESC, "")
        );
    }

    public boolean hasUser() {
        return !preferences.getString(KEY_EMAIL, "").isEmpty();
    }

    public void setLogin(boolean isLogin) {
        editor.putBoolean(KEY_IS_LOGIN, isLogin);
        editor.apply();
    }

    public boolean isLogin() {
        return preferences.getBoolean(KEY_IS_LOGIN, false);
    }

    public boolean login(String email, String password) {
        String savedEmail = preferences.getString(KEY_EMAIL, "");
        String savedPassword = preferences.getString(KEY_PASSWORD, "");
        boolean ok = savedEmail.equals(email.trim()) && savedPassword.equals(password.trim());
        if (ok) {
            setLogin(true);
        }
        return ok;
    }

    public void logout() {
        editor.putBoolean(KEY_IS_LOGIN, false);
        editor.apply();
    }
}