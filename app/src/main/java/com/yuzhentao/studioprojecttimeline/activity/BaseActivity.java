package com.yuzhentao.studioprojecttimeline.activity;

import android.app.Activity;
import android.view.View;

import com.yuzhentao.studioprojecttimeline.R;

/**
 * 基类
 *
 * @author yuzhentao
 */
public class BaseActivity extends Activity {

    /**
     * 查找组件
     *
     * @param viewId：View的ID
     * @return View
     */
    protected <view extends View> view getViewById(int viewId) {
        return (view) findViewById(viewId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_back_enter, R.anim.anim_slide_back_exit);
    }

}