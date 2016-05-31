package com.yuzhentao.studioprojecttimeline.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.yuzhentao.studioprojecttimeline.R;
import com.yuzhentao.studioprojecttimeline.util.DimensionUtil;
import com.yuzhentao.studioprojecttimeline.view.CustomVideoPlayer;
import com.yuzhentao.studioprojecttimeline.view.MediaController;

/**
 * 播放视频界面
 *
 * @author violetjack
 */
public class VideoDetailActivity extends Activity {

    private CustomVideoPlayer customVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        initView();
        playVideo();
    }

    /***
     * 旋转屏幕之后回调
     *
     * @param newConfig：Configuration
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (customVideoPlayer == null) {
            return;
        }
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {//如果是横屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
            getWindow().getDecorView().invalidate();//重绘DecorView，DecorView：TitleView + ContentView
            float width = DimensionUtil.getHeightInPx(this);//屏幕宽度
            float height = DimensionUtil.getWidthInPx(this);//屏幕高度
            customVideoPlayer.getLayoutParams().width = (int) height;//视频播放器的宽度
            customVideoPlayer.getLayoutParams().height = (int) width;//视频播放器的高度
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {//如果是竖屏
            /**
             * 执行了某个操作而全屏，然后还需要退出全屏
             */
//            WindowManager.LayoutParams attrs = getWindow().getAttributes();
//            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
//            getWindow().setAttributes(attrs);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            /**
             * 退出全屏
             */
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DimensionUtil.getWidthInPx(this);
            float height = DimensionUtil.dp2px(this, 200.0F);
            customVideoPlayer.getLayoutParams().height = (int) height;
            customVideoPlayer.getLayoutParams().width = (int) width;
        }
    }

    private void initView() {
        customVideoPlayer = (CustomVideoPlayer) findViewById(R.id.customvideoplayer);
        customVideoPlayer.setVideoPlayCallback(videoPlayCallback);
        findViewById(R.id.linearlayout_activity_video_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeVideoAndFinish();
            }
        });
    }

    /**
     * 播放视频
     */
    private void playVideo() {
        customVideoPlayer.setAutoHideMediaController(false);
        Uri uri = Uri.parse("http://7xkbzx.com1.z0.glb.clouddn.com/SampleVideo_1080x720_20mb.mp4");
        customVideoPlayer.loadAndPlay(uri, 0);
    }


    /**
     * 播放器的回调函数
     */
    private CustomVideoPlayer.VideoPlayCallback videoPlayCallback = new CustomVideoPlayer.VideoPlayCallback() {
        @Override
        public void onCloseVideo() {
            closeVideoAndFinish();
        }

        @Override
        public void onSwitchPageType() {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                customVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                customVideoPlayer.setPageType(MediaController.PageType.EXPAND);
            }
        }

        @Override
        public void onPlayFinish() {

        }
    };

    /**
     * 关闭VideoView
     */
    private void closeVideoAndFinish() {
        customVideoPlayer.close();//关闭VideoView
        resetPageToPortrait();
        finish();
    }

    /***
     * 恢复屏幕至竖屏
     */
    private void resetPageToPortrait() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            customVideoPlayer.setPageType(MediaController.PageType.SHRINK);
        }
    }

}