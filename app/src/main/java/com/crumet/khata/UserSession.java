package com.crumet.khata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class UserSession {
    private SharedPreferences pref;
    private Editor editor;
    private Context _context;
    private static final String PREFER_NAME = "Reg";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "txtPassword";
    private static final String FIRST_TIME = "firstTime";

    public UserSession(Context context) {
        this._context = context;
        // Shared preferences mode
        int PRIVATE_MODE = 0;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTime() {
        editor.putBoolean(FIRST_TIME, false);
        editor.commit();
    }

    public boolean getFirstTime() {
        return pref.getBoolean(FIRST_TIME, true);
    }

    public void createUserLoginSession(String uEmail, String uPassword) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_EMAIL, uEmail);
        editor.putString(KEY_PASSWORD, uPassword);
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     */
    public boolean checkLogin() {
        // Check login status
        if (!this.isUserLoggedIn()) {

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }


    /**
     * Get stored session data
     */
    HashMap<String, String> getUserDetails() {

        //Use hashmap to store user credentials
        HashMap<String, String> user = new HashMap<String, String>();


        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logoutUser() {

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to MainActivity
        Intent i = new Intent(_context, MainActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }


    // Check for login
    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}
