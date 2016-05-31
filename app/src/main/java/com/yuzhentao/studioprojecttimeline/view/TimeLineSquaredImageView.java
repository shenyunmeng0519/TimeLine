package com.yuzhentao.studioprojecttimeline.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 时间轴正方形ImageView
 *
 * @author yuzhentao
 */
public class TimeLineSquaredImageView extends ImageView {

    public TimeLineSquaredImageView(Context context) {
        super(context);
    }

    public TimeLineSquaredImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

}