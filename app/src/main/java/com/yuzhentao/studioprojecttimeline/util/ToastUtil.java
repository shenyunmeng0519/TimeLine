package com.yuzhentao.studioprojecttimeline.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 *
 * @author yuzhentao
 */
@SuppressWarnings("unused")
public class ToastUtil {

    public static void showShortToastByString(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToastByString(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void showShortToastByResrouceId(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToastByResrouceId(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

}