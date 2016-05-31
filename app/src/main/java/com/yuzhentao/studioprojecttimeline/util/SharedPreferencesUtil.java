package com.yuzhentao.studioprojecttimeline.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences工具
 *
 * @author Violetjack
 */
public class SharedPreferencesUtil {

    private static final String PREF_NAME = "pref_name";

    /**
     * 设置SharedPreferences
     *
     * @param context：上下文
     * @param key：键
     * @param value：值(int)
     */
    public static void setSP(Context context, String key, int value) {
        Editor et = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        et.putInt(key, value);
        et.apply();
    }

    /**
     * 设置SharedPreferences
     *
     * @param context：上下文
     * @param key：键
     * @param value：值(String)
     */
    public static void setSP(Context context, String key, String value) {
        Editor et = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        et.putString(key, value);
        et.apply();
    }

    /**
     * 设置SharedPreferences
     *
     * @param context：上下文
     * @param key：键
     * @param value：值(Boolean)
     */
    public static void setSP(Context context, String key, Boolean value) {
        Editor et = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        et.putBoolean(key, value);
        et.apply();
    }

    /**
     * 获取SharedPreferences
     *
     * @param context：上下文
     * @param key：键
     * @return 值
     */
    public static int getSP(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }


    /**
     * 获取SharedPreferences
     *
     * @param context：上下文
     * @param key：键
     * @return 值
     */
    public static String getSP(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    /**
     * 获取SharedPreferences
     *
     * @param context：上下文
     * @param key：键
     * @return 值
     */
    public static boolean getSP(Context context, String key, Boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * 移除SharedPreferences
     *
     * @param context：上下文
     * @param key：键
     */
    public static void removeSP(Context context, String key) {
        Editor et = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        et.remove(key);
        et.apply();
    }

    /**
     * 清除SharedPreferences
     *
     * @param context：上下文
     */
    public static void clearSP(Context context) {
        Editor et = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        et.clear();
        et.apply();
    }

}
