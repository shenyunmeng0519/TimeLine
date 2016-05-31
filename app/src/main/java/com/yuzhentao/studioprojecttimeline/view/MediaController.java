package com.yuzhentao.studioprojecttimeline.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yuzhentao.studioprojecttimeline.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 媒体控制器
 *
 * @author violetjack
 */
public class MediaController extends FrameLayout implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private ImageView ivPlay;//播放按钮
    private SeekBar sbProgress;//播放拖动条
    private TextView tvTime;//播放时间
    private ImageView ivExpand;//播放展开按钮
    private ImageView ivShrink;//播放缩放按钮

    private MediaControlCallback mediaControlCallback;//媒体控制器接口

    /**
     * 页面样式：展开、缩放
     */
    public enum PageType {
        EXPAND, SHRINK
    }

    /**
     * 播放状态：播放、暂停
     */
    public enum PlayState {
        PLAY, PAUSE
    }

    /**
     * 播放进度状态：开始、进行、停止
     */
    public enum PlayProgressState {
        START, DOING, STOP
    }

    public MediaController(Context context) {
        this(context, null);
    }

    public MediaController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MediaController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View.inflate(context, R.layout.view_custom_video_player_media_controller, this);
        ivPlay = (ImageView) findViewById(R.id.imageview_play_custom_video_player_media_controller);
        sbProgress = (SeekBar) findViewById(R.id.seekbar_progress_custom_video_player_media_controller);
        tvTime = (TextView) findViewById(R.id.textview_time_custom_video_player_media_controller);
        ivExpand = (ImageView) findViewById(R.id.imageview_expand_custom_video_player_media_controller);
        ivShrink = (ImageView) findViewById(R.id.imageview_shrink_custom_video_player_media_controller);
        initData();
    }

    private void initData() {
        sbProgress.setOnSeekBarChangeListener(this);
        ivPlay.setOnClickListener(this);
        ivShrink.setOnClickListener(this);
        ivExpand.setOnClickListener(this);
        setPageType(PageType.SHRINK);
        setPlayState(PlayState.PAUSE);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean isFromUser) {
        if (isFromUser) {
            mediaControlCallback.onProgressTurn(PlayProgressState.DOING, progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mediaControlCallback.onProgressTurn(PlayProgressState.START, 0);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaControlCallback.onProgressTurn(PlayProgressState.STOP, 0);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageview_play_custom_video_player_media_controller) {
            mediaControlCallback.onPlayTurn();
        } else if (view.getId() == R.id.imageview_expand_custom_video_player_media_controller) {
            mediaControlCallback.onPageTurn();
        } else if (view.getId() == R.id.imageview_shrink_custom_video_player_media_controller) {
            mediaControlCallback.onPageTurn();
        }
    }

    /**
     * 强制横屏模式
     */
    public void forceLandscapeMode() {
        ivExpand.setVisibility(INVISIBLE);
        ivShrink.setVisibility(INVISIBLE);
    }

    /**
     * 设置SeekBar
     *
     * @param progress：播放进度
     * @param secondProgress：缓冲进度
     */
    public void setSeekBar(int progress, int secondProgress) {
        if (progress < 0) progress = 0;
        if (progress > 100) progress = 100;
        if (secondProgress < 0) secondProgress = 0;
        if (secondProgress > 100) secondProgress = 100;
        sbProgress.setProgress(progress);
        sbProgress.setSecondaryProgress(secondProgress);
    }

    /**
     * 设置播放状态
     *
     * @param playState：播放状态
     */
    public void setPlayState(PlayState playState) {
        ivPlay.setImageResource(playState.equals(PlayState.PLAY) ? R.drawable.ic_custom_video_player_pause : R.drawable.ic_custom_video_player_play);
    }

    /**
     * 设置页面状态
     *
     * @param pageType：页面状态
     */
    public void setPageType(PageType pageType) {
        ivExpand.setVisibility(pageType.equals(PageType.EXPAND) ? GONE : VISIBLE);
        ivShrink.setVisibility(pageType.equals(PageType.SHRINK) ? GONE : VISIBLE);
    }

    /**
     * 设置播放时间文本
     *
     * @param playTime：播放时间
     * @param allTime：总共时间
     */
    public void setPlayTimeTxt(int playTime, int allTime) {
        tvTime.setText(getPlayTime(playTime, allTime));
    }

    /**
     * 播放完成
     *
     * @param allTime：总共时间
     */
    public void playFinish(int allTime) {
        sbProgress.setProgress(0);
        setPlayTimeTxt(0, allTime);
        setPlayState(PlayState.PAUSE);
    }

    /**
     * 格式化播放时间
     *
     * @param time：时间
     * @return 格式化的时间
     */
    @SuppressLint("SimpleDateFormat")
    private String formatPlayTime(long time) {
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(new Date(time));
    }

    /**
     * 获取播放时间
     *
     * @param playTime：播放时间
     * @param allTime：总共时间
     * @return 播放时间
     */
    private String getPlayTime(int playTime, int allTime) {
        String playTimeStr = "00:00";
        String allTimeStr = "00:00";
        if (playTime > 0) {
            playTimeStr = formatPlayTime(playTime);
        }
        if (allTime > 0) {
            allTimeStr = formatPlayTime(allTime);
        }
        return playTimeStr + "/" + allTimeStr;
    }

    /**
     * 设置播放控制器接口回调
     *
     * @param mediaControlCallback：播放控制器接口
     */
    public void setMediaControlCallback(MediaControlCallback mediaControlCallback) {
        this.mediaControlCallback = mediaControlCallback;
    }

    /**
     * 播放控制器接口
     */
    public interface MediaControlCallback {
        /**
         * 切换播放
         */
        void onPlayTurn();

        /**
         * 切换页面
         */
        void onPageTurn();

        /**
         * 切换播放进度
         *
         * @param state：播放进度状态
         * @param progress：进度
         */
        void onProgressTurn(PlayProgressState state, int progress);

    }

}