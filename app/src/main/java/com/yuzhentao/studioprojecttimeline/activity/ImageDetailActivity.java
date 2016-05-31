package com.yuzhentao.studioprojecttimeline.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yuzhentao.studioprojecttimeline.R;
import com.yuzhentao.studioprojecttimeline.adapter.ImagePagerAdapter;
import com.yuzhentao.studioprojecttimeline.util.Param;
import com.yuzhentao.studioprojecttimeline.util.SharedPreferencesUtil;
import com.yuzhentao.studioprojecttimeline.view.PhotoView;

import java.util.ArrayList;

/**
 * 查看大图界面
 *
 * @author yuzhentao
 */
public class ImageDetailActivity extends BaseActivity {

    private LinearLayout llProgress;
    private ProgressBar pb;
    private TextView tvProgress;//进度文本
    private TextView tvNumber;//图片数量
    private ArrayList<ImageView> photoViewList;//图片列表
    private int currentImageIndex;//当前图片的值
    private int previousImageIndex;//上一张显示过的图片的索引值
    private String[] imageUrlArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        llProgress = getViewById(R.id.linearlayout_progress_activity_image_detail);
        pb = getViewById(R.id.progressbar_activity_image_detail);
        tvProgress = getViewById(R.id.textview_percent_activity_image_detail);
        //获取URL数组和当前点击页
        Bundle bundle = getIntent().getExtras();
        imageUrlArr = bundle.getStringArray("imageArr");//URL数组
        currentImageIndex = bundle.getInt("currentImage");//当前Image的页数
        photoViewList = new ArrayList<>();
        tvNumber = getViewById(R.id.textview_number_activity_image_detail);
        showImage();
        if (SharedPreferencesUtil.getSP(this, Param.SP_SHOW_PROGRESS, false)) {
            new AsyncTask<Void, Integer, Void>() {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    llProgress.setVisibility(View.VISIBLE);
                }

                @Override
                protected Void doInBackground(Void... params) {
                    for (int i = 0; i < imageUrlArr.length; i++) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        publishProgress((int) (i * 100 / imageUrlArr.length));
                    }
                    return null;
                }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    super.onProgressUpdate(values);
                    pb.setProgress(values[0]);
                    tvProgress.setText(values[0] + "%");
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    llProgress.setVisibility(View.GONE);
                }

            }.execute();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferencesUtil.setSP(this, Param.SP_SHOW_PROGRESS, false);
    }

    /**
     * 显示图片
     */
    private void showImage() {
        int displayMetricsWidth = getResources().getDisplayMetrics().widthPixels;
        for (int i = 0; i < imageUrlArr.length; i++) {
            PhotoView photoView = new PhotoView(ImageDetailActivity.this);
            Picasso.with(ImageDetailActivity.this)
                    .load(imageUrlArr[i])
                    .placeholder(R.drawable.bg_picasso_placeholder)
                    .error(R.drawable.bg_picasso_error)
                    .resize(displayMetricsWidth, displayMetricsWidth)
                    .centerCrop()
                    .into(photoView);
            photoView.enable();
            photoViewList.add(photoView);
        }
        tvNumber.setText((currentImageIndex + 1) + "/" + photoViewList.size());
        ViewPager viewPager = getViewById(R.id.viewpager_activity_image_detail);
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, photoViewList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentImageIndex);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((PhotoView) photoViewList.get(previousImageIndex)).reset();
                previousImageIndex = position;
                tvNumber.setText((position + 1) + "/" + photoViewList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}