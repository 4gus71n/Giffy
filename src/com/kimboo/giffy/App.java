package com.kimboo.giffy;

import com.kimboo.giffy.utils.Constants;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {

    protected SharedPreferences mPreferences;
    private static App sMyself;
    
    public static App getInstance() {
        return sMyself;
    }
    
    
    @Override
    public void onCreate() {
        super.onCreate();
        mPreferences = getSharedPreferences(Constants.System.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        sMyself = this;
    }

    public void putInt(String key, int value) {
        mPreferences.edit().putInt(key, value).commit();
    }

    /**
     * @return True if the user just installed the app. False otherwise.
     */
    public boolean firstInstallation() {
        return mPreferences.getBoolean(Constants.System.FIRST_INSTALLATION, Boolean.TRUE);
    }


    public void firstInstallation(Boolean value) {
        mPreferences.edit().putBoolean(Constants.System.FIRST_INSTALLATION, value).commit();
    }


    /**
     * @return True if the user is already logged in. False otherwise.
     */
    public boolean userLogin() {
        return (mPreferences.getString(Constants.User.OAUTH_USERNAME, null) != null);
    }

}
