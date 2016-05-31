package com.yuzhentao.studioprojecttimeline.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.yuzhentao.studioprojecttimeline.R;
import com.yuzhentao.studioprojecttimeline.adapter.TimeLineAdapter;
import com.yuzhentao.studioprojecttimeline.bean.TimeLineItem;
import com.yuzhentao.studioprojecttimeline.util.ToastUtil;

import java.util.ArrayList;

/**
 * 时间轴
 *
 * @author yuzhentao
 */
public class TimeLineActivity extends BaseActivity {

    private PullToRefreshListView ptrlv;//下拉刷新ListView

    private String[] urlImage01 = {"http://b.zol-img.com.cn/sjbizhi/images/8/320x510/142347101736.jpg"};
    private String[] urlImage02 = {"http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1423039452137.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1422959296333.jpg"};
    private String[] urlImage03 = {"http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1422608942847.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1422609030469.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1422262832379.jpg"};
    private String[] urlImage04 = {"http://b.zol-img.com.cn/sjbizhi/images/6/320x510/1381224253397.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/6/320x510/1381204188438.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/6/320x510/1380511740815.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/6/320x510/1381224253397.jpg"};
    private String[] urlImage05 = {"http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720702858.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg"};
    private String[] urlImage06 = {"http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720702858.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720702858.jpg"};
    private String[] urlImage07 = {"http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720702858.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720702858.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg"};
    private String[] urlImage08 = {"http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720702858.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720702858.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg"};
    private String[] urlImage09 = {"http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720702858.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720702858.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720713472.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720723551.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/320x510/1444720702858.jpg"};
    private String[] urlVideo = {"http://7xkbzx.com1.z0.glb.clouddn.com/SampleVideo_1080x720_20mb.mp4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        ptrlv = getViewById(R.id.pulltorefreshlistview);
        ArrayList<TimeLineItem> itemList = new ArrayList<>();
        ArrayList<String[]> urlList = new ArrayList<>();
        urlList.add(urlImage01);
        urlList.add(urlImage02);
        urlList.add(urlImage03);
        urlList.add(urlImage04);
        urlList.add(urlImage05);
        urlList.add(urlImage06);
        urlList.add(urlImage07);
        urlList.add(urlImage08);
        urlList.add(urlImage09);
        urlList.add(urlVideo);
        for (int i = urlList.size() - 1; i >= 0; i--) {
            TimeLineItem item = new TimeLineItem();
            item.setAgeYear("05岁");
            item.setAgeMonth("06个月");
            item.setAgeDay("1" + i + "天");
            item.setDate("2015年11月1" + i + "日");
            item.setDescription("今天宝宝很开心");
            item.setImageUrls(urlList.get(i));
            itemList.add(item);
        }
        TimeLineAdapter adapter = new TimeLineAdapter(this, this, itemList);
        ptrlv.setAdapter(adapter);
        ptrlv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                final Picasso picasso = Picasso.with(TimeLineActivity.this);
                if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    picasso.resumeTag(TimeLineActivity.this);
                } else {
                    picasso.pauseTag(TimeLineActivity.this);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        ptrlv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {//下拉刷新
                ToastUtil.showShortToastByString(TimeLineActivity.this, "下拉刷新完成");
                new RefreshCompleteAsyncTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {//上拉加载
                ToastUtil.showShortToastByString(TimeLineActivity.this, "上拉加载完成");
                new RefreshCompleteAsyncTask().execute();
            }
        });
    }

    /**
     * 刷新完成的异步任务
     */
    private class RefreshCompleteAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            ptrlv.onRefreshComplete();
        }

    }

}