package com.yuzhentao.studioprojecttimeline.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.yuzhentao.studioprojecttimeline.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 视频播放器
 *
 * @author violetjack
 */
public class CustomVideoPlayer extends RelativeLayout {

    private final int MSG_HIDE_MEDIA_CONTROLLER = 10;//隐藏媒体控制器
    private final int MSG_UPDATE_PLAY_TIME = 11;//更新播放时间

    private MediaController.PageType currentPageType = MediaController.PageType.SHRINK;//当前页面样式（当前是横屏还是竖屏）

    private Context mContext;
    private VideoView videoView;//播放器
    private MediaController mediaController;//媒体控制器
    private Timer updateTimer;
    private VideoPlayCallback videoPlayCallback;//回调函数
    private FrameLayout flProgress;
    private FrameLayout flClose;

    private boolean isAutoHideMediaController = true;//是否自动隐藏媒体控制器

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == MSG_UPDATE_PLAY_TIME) {
                updatePlayTime();
                updatePlayProgress();
            } else if (msg.what == MSG_HIDE_MEDIA_CONTROLLER) {
                showOrHideMediaController();
            }
            return false;
        }
    });

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.framelayout_close_view_custom_video_player) {
                videoPlayCallback.onCloseVideo();//回调函数的关闭方法
            }
        }
    };

    private OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                showOrHideMediaController();
            }
            return currentPageType == MediaController.PageType.EXPAND;
        }
    };

    private MediaController.MediaControlCallback mediaControlCallback = new MediaController.MediaControlCallback() {//媒体播放器的回调函数
        @Override
        public void onPlayTurn() {
            if (videoView.isPlaying()) {
                pausePlay(true);
            } else {
                continuePlay();
            }
        }

        @Override
        public void onPageTurn() {
            videoPlayCallback.onSwitchPageType();
        }

        @Override
        public void onProgressTurn(MediaController.PlayProgressState state, int progress) {
            if (state.equals(MediaController.PlayProgressState.START)) {
                handler.removeMessages(MSG_HIDE_MEDIA_CONTROLLER);
            } else if (state.equals(MediaController.PlayProgressState.STOP)) {
                resetHideTimer();
            } else {
                int time = progress * videoView.getDuration() / 100;
                videoView.seekTo(time);
                updatePlayTime();
            }
        }
    };

    private MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {//当MediaPlayer准备好后触发该回调
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                        flProgress.setVisibility(View.GONE);
                        showCloseIcon(true);
                        return true;
                    }
                    return false;
                }
            });
        }
    };

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {//当MediaPlayer播放完成后触发该回调
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            stopUpdateTimer();
            stopHideTimer(true);
            mediaController.playFinish(videoView.getDuration());
            videoPlayCallback.onPlayFinish();
            Toast.makeText(mContext, "视频播放完成", Toast.LENGTH_SHORT).show();
        }
    };

    public CustomVideoPlayer(Context context) {
        this(context, null);
    }

    public CustomVideoPlayer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomVideoPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化View
     *
     * @param context：Context
     */
    private void initView(Context context) {
        mContext = context;
        View.inflate(context, R.layout.view_custom_video_player, this);//TODO 假如只是将java和Layout结合起来，可以直接这么写。
        videoView = (VideoView) findViewById(R.id.videoview_view_custom_video_player);
        mediaController = (MediaController) findViewById(R.id.mediacontroller_view_custom_video_player);
        flProgress = (FrameLayout) findViewById(R.id.framelayout_progress_view_custom_video_player);
        flClose = (FrameLayout) findViewById(R.id.framelayout_close_view_custom_video_player);
        mediaController.setMediaControlCallback(mediaControlCallback);
        videoView.setOnTouchListener(onTouchListener);
        showCloseIcon(false);
        showProgressView(false);
        flClose.setOnClickListener(onClickListener);
        flProgress.setOnClickListener(onClickListener);
    }

    /**
     * 强制横屏模式
     */
    @SuppressWarnings("unused")
    public void forceLandscapeMode() {
        mediaController.forceLandscapeMode();
    }

    /**
     * 设置页面状态（横屏or竖屏）
     *
     * @param pageType：页面样式
     */
    public void setPageType(MediaController.PageType pageType) {
        mediaController.setPageType(pageType);
        currentPageType = pageType;
    }

    /**
     * 暂停播放
     *
     * @param isShowMediaController：是否显示媒体控制器
     */
    public void pausePlay(boolean isShowMediaController) {
        videoView.pause();
        mediaController.setPlayState(MediaController.PlayState.PAUSE);
        stopHideTimer(isShowMediaController);
    }

    /**
     * 继续播放
     */
    public void continuePlay() {
        videoView.start();
        mediaController.setPlayState(MediaController.PlayState.PLAY);
        resetHideTimer();
        resetUpdateTimer();
    }

    /**
     * 关闭视频
     */
    public void close() {
        mediaController.setPlayState(MediaController.PlayState.PAUSE);
        stopHideTimer(true);
        stopUpdateTimer();
        videoView.pause();
        videoView.stopPlayback();
        videoView.setVisibility(GONE);
    }

    /**
     * 显示关闭视频的按钮
     *
     * @param isShowCloseIcon：是否显示关闭视频的按钮
     */
    private void showCloseIcon(boolean isShowCloseIcon) {
        flClose.setVisibility(isShowCloseIcon ? VISIBLE : INVISIBLE);
    }

    /**
     * 是否自动隐藏媒体控制器
     */
    public boolean isAutoHideMediaController() {
        return isAutoHideMediaController;
    }

    /**
     * 设置自动隐藏媒体控制器
     *
     * @param isAutoHideMediaController：是否自动隐藏媒体控制器
     */
    public void setAutoHideMediaController(boolean isAutoHideMediaController) {
        this.isAutoHideMediaController = isAutoHideMediaController;
    }

    /**
     * 加载并开始播放视频
     *
     * @param uri：Uri
     * @param seekTime：播放位置
     */
    public void loadAndPlay(Uri uri, int seekTime) {
        showProgressView(seekTime > 0);
        showCloseIcon(true);
        videoView.setOnPreparedListener(onPreparedListener);
        videoView.setVideoURI(uri);
        videoView.setVisibility(VISIBLE);
        startPlayVideo(seekTime);
    }

    /**
     * 开始播放视频
     *
     * @param seekTime：播放位置
     */
    private void startPlayVideo(int seekTime) {
        if (null == updateTimer) resetUpdateTimer();
        resetHideTimer();
        videoView.setOnCompletionListener(onCompletionListener);
        videoView.start();
        if (seekTime > 0) {
            videoView.seekTo(seekTime);
        }
        mediaController.setPlayState(MediaController.PlayState.PLAY);
    }

    /**
     * 更新播放的进度时间
     */
    private void updatePlayTime() {
        int allTime = videoView.getDuration();
        int playTime = videoView.getCurrentPosition();
        mediaController.setPlayTimeTxt(playTime, allTime);
    }

    /**
     * 更新播放进度条
     */
    private void updatePlayProgress() {
        int allTime = videoView.getDuration();
        int playTime = videoView.getCurrentPosition();
        int loadProgress = videoView.getBufferPercentage();
        int progress = playTime * 100 / allTime;
        mediaController.setSeekBar(progress, loadProgress);
    }

    /**
     * 显示loading圈
     *
     * @param isTransparentBg isTransparentBg
     */
    private void showProgressView(Boolean isTransparentBg) {
        flProgress.setVisibility(VISIBLE);
        if (!isTransparentBg) {
            flProgress.setBackgroundResource(android.R.color.black);
        } else {
            flProgress.setBackgroundResource(android.R.color.transparent);
        }
    }

    /**
     * 显示或者隐藏媒体控制器
     */
    private void showOrHideMediaController() {
        if (mediaController.getVisibility() == View.VISIBLE) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_hide_controller_from_buttom);
            animation.setAnimationListener(new AnimationCallback() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    super.onAnimationEnd(animation);
                    mediaController.setVisibility(View.GONE);
                }
            });
            mediaController.startAnimation(animation);
        } else {
            mediaController.setVisibility(View.VISIBLE);
            mediaController.clearAnimation();
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.anim_show_controller_from_buttom);
            mediaController.startAnimation(animation);
            resetHideTimer();
        }
    }

    /**
     * 总是显示播放控制器
     */
    private void alwaysShowMediaController() {
        handler.removeMessages(MSG_HIDE_MEDIA_CONTROLLER);
        mediaController.setVisibility(View.VISIBLE);
    }

    /**
     * 重置隐藏计时器
     */
    private void resetHideTimer() {
        if (!isAutoHideMediaController()) {
            return;
        }
        handler.removeMessages(MSG_HIDE_MEDIA_CONTROLLER);
        int TIME_SHOW_CONTROLLER = 4000;
        handler.sendEmptyMessageDelayed(MSG_HIDE_MEDIA_CONTROLLER, TIME_SHOW_CONTROLLER);
    }

    /**
     * 停止隐藏计时器
     */
    private void stopHideTimer(boolean isShowController) {
        handler.removeMessages(MSG_HIDE_MEDIA_CONTROLLER);
        mediaController.clearAnimation();
        mediaController.setVisibility(isShowController ? View.VISIBLE : View.GONE);
    }

    /**
     * 重置更新计时器
     */
    private void resetUpdateTimer() {
        updateTimer = new Timer();
        int TIME_UPDATE_PLAY_TIME = 1000;
        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG_UPDATE_PLAY_TIME);
            }
        }, 0, TIME_UPDATE_PLAY_TIME);
    }

    /**
     * 停止更新计时器
     */
    private void stopUpdateTimer() {
        if (updateTimer != null) {
            updateTimer.cancel();
            updateTimer = null;
        }
    }

    /**
     * 动画监听接口
     */
    private class AnimationCallback implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

    }

    /**
     * 视频播放接口回调
     *
     * @param videoPlayCallback：VideoPlayCallback
     */
    public void setVideoPlayCallback(VideoPlayCallback videoPlayCallback) {
        this.videoPlayCallback = videoPlayCallback;
    }

    /**
     * 视频播放接口
     */
    public interface VideoPlayCallback {
        /**
         * 关闭视频播放器
         */
        void onCloseVideo();

        /**
         * 切换页面样式
         */
        void onSwitchPageType();

        /**
         * 播放完成
         */
        void onPlayFinish();
    }

}