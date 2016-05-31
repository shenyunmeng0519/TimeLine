package com.yuzhentao.studioprojecttimeline.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * ViewPager图片适配器
 *
 * @author violetjack
 */
public class ImagePagerAdapter extends PagerAdapter {

    private Activity activity;
    private ArrayList<ImageView> list;

    public ImagePagerAdapter(Activity activity, ArrayList<ImageView> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(list.get(position));
        list.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        return list.get(position);
    }

}