package com.kimboo.giffy.utils;

import android.util.Log;

public class MyLog {
    
    private static final boolean LOG_ENABLE = true;

    public static void d(String tag, String string) {
        if (LOG_ENABLE) {
            Log.d(tag,string);
        }
    }

}
