package com.yuzhentao.studioprojecttimeline.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * 尺寸工具
 *
 * @author yuzhentao
 */
@SuppressWarnings("unused")
public class DimensionUtil {

    /**
     * 获取屏幕宽度（单位px）
     *
     * @param context：Context
     * @return 屏幕宽度
     */
    public static float getWidthInPx(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度（单位px）
     *
     * @param context：Context
     * @return 屏幕高度
     */
    public static float getHeightInPx(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度（单位dp）
     *
     * @param context：Context
     * @return 屏幕宽度
     */
    public static int getWidthInDp(Context context) {
        float width = context.getResources().getDisplayMetrics().widthPixels;
        return px2dp(context, width);
    }

    /**
     * 获取屏幕高度（单位dp）
     *
     * @param context：Context
     * @return 屏幕高度
     */
    public static int getHeightInDp(Context context) {
        float height = context.getResources().getDisplayMetrics().heightPixels;
        return px2dp(context, height);
    }

    /**
     * px转dp
     *
     * @param context：Context
     * @param px：像素
     * @return 转换值
     */
    public static int px2dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5F);
    }

    /**
     * dp转px
     *
     * @param context：Context
     * @param dp：设备独立像素
     * @return 转换值
     */
    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5F);
    }

    /**
     * px转sp
     *
     * @param context：Context
     * @param px：像素
     * @return 转换值
     */
    public static int px2sp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / scale + 0.5F);
    }

    /**
     * sp转px
     *
     * @param context：Context
     * @param sp：放大像素
     * @return 转换值
     */
    public static int sp2px(Context context, float sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5F);
    }

    /**
     * 使用TypedValue类将dp转px
     *
     * @param context：Context
     * @param dp：设备独立像素
     * @return 转换值
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    /**
     * 使用TypedValue类将sp转px
     *
     * @param context：Context
     * @param sp：放大像素
     * @return 转换值
     */
    public static int sp2px(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

}