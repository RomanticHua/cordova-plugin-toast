package com.king.test;

import android.content.Context;

import org.apache.cordova.CordovaPreferences;

public class Utils {
    private static Context sContext;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    /**
     * 获取id
     */
    public static int getId(String group, String key) {
        return sContext.getResources().getIdentifier(key, group, sContext.getPackageName());
    }

    /**
     * 默认使用的是强转，因此需要try...catch一下
     */
    public static int getInt(CordovaPreferences preferences, String key, int defaultValue) {
        int value = defaultValue;
        try {
            value = preferences.getInteger(key, defaultValue);
        } catch (Exception e) {
        }
        return value;
    }

    public static boolean getBoolean(CordovaPreferences preferences, String key, boolean defaultValue) {
        boolean value = defaultValue;
        try {
            value = preferences.getBoolean(key, defaultValue);
        } catch (Exception e) {
        }
        return value;
    }

    public static double getDouble(CordovaPreferences preferences, String key, double defaultValue) {
        double value = defaultValue;
        try {
            value = preferences.getDouble(key, defaultValue);
        } catch (Exception e) {
        }
        return value;
    }

    public static String getString(CordovaPreferences preferences, String key, String defaultValue) {
        String value = defaultValue;
        try {
            value = preferences.getString(key, defaultValue);
        } catch (Exception e) {
        }
        return value;
    }

}
