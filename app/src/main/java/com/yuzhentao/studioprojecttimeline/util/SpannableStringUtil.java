package com.yuzhentao.studioprojecttimeline.util;

import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;

/**
 * SpannableString工具
 *
 * @author yuzhentao
 */
public class SpannableStringUtil {

    /**
     * 设置字号Span
     *
     * @param string：String
     * @param textSize：字号
     * @return SpannableString
     */
    public static SpannableString setAbsoluteSizeSpan(String string, int textSize, int start, int end, int flags) {
        SpannableString spannableString = new SpannableString(string);//复合字符串
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(textSize, true);//AbsoluteSizeSpan(int size, boolean dip：是否是dp，否则为px)：改变字号的Span
        spannableString.setSpan(span, start, end, flags);
        return spannableString;
    }

}