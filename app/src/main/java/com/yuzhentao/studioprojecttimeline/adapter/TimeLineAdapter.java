package com.yuzhentao.studioprojecttimeline.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yuzhentao.studioprojecttimeline.R;
import com.yuzhentao.studioprojecttimeline.activity.ImageDetailActivity;
import com.yuzhentao.studioprojecttimeline.activity.VideoDetailActivity;
import com.yuzhentao.studioprojecttimeline.bean.TimeLineItem;
import com.yuzhentao.studioprojecttimeline.util.SpannableStringUtil;
import com.yuzhentao.studioprojecttimeline.util.ToastUtil;
import com.yuzhentao.studioprojecttimeline.view.TimeLineSquaredImageView;

import java.util.ArrayList;

/**
 * 时间轴Adapter
 *
 * @author yuzhentao
 */
public class TimeLineAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private Activity activity;
    private ArrayList<TimeLineItem> timeLineItemList;
    private ArrayList<TimeLineSquaredImageView> imageViewList;
    private LayoutInflater layoutInflater;
    private int imageCount;

    public TimeLineAdapter(Context context, Activity activity, ArrayList<TimeLineItem> timeLineItemList) {
        this.context = context;
        this.activity = activity;
        this.timeLineItemList = timeLineItemList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return timeLineItemList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return timeLineItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewTimeLineViewHolder holder;
        imageViewList = new ArrayList<>();
        if (convertView == null) {
            holder = new NewTimeLineViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_time_line, null);
            holder.tvAgeYear = (TextView) convertView.findViewById(R.id.textview_age_year_item_time_line);
            holder.tvAgeMonth = (TextView) convertView.findViewById(R.id.textview_age_month_item_time_line);
            holder.tvAgeDay = (TextView) convertView.findViewById(R.id.textview_age_day_item_time_line);
            holder.tvBabyBirthday = (TextView) convertView.findViewById(R.id.textview_baby_birthday_item_time_line);
            holder.tvDate = (TextView) convertView.findViewById(R.id.textview_date_item_time_line);
            holder.tvDescription = (TextView) convertView.findViewById(R.id.textview_description_item_time_line);
            holder.tvBirthday = (TextView) convertView.findViewById(R.id.textview_birthday_item_time_line);
            holder.ivImage01 = (TimeLineSquaredImageView) convertView.findViewById(R.id.imageview_01_item_time_line_image);
            holder.ivImage02 = (TimeLineSquaredImageView) convertView.findViewById(R.id.imageview_02_item_time_line_image);
            holder.ivImage03 = (TimeLineSquaredImageView) convertView.findViewById(R.id.imageview_03_item_time_line_image);
            holder.ivImage04 = (TimeLineSquaredImageView) convertView.findViewById(R.id.imageview_04_item_time_line_image);
            holder.ivImage05 = (TimeLineSquaredImageView) convertView.findViewById(R.id.imageview_05_item_time_line_image);
            holder.ivImage06 = (TimeLineSquaredImageView) convertView.findViewById(R.id.imageview_06_item_time_line_image);
            holder.ivImage07 = (TimeLineSquaredImageView) convertView.findViewById(R.id.imageview_07_item_time_line_image);
            holder.ivImage08 = (TimeLineSquaredImageView) convertView.findViewById(R.id.imageview_08_item_time_line_image);
            holder.ivImage09 = (TimeLineSquaredImageView) convertView.findViewById(R.id.imageview_09_item_time_line_image);
            holder.llAge = (LinearLayout) convertView.findViewById(R.id.linearlayout_age_item_time_line);
            holder.llImage = (LinearLayout) convertView.findViewById(R.id.linearlyaout_image_item_time_line_image);
            holder.llBirthday = (LinearLayout) convertView.findViewById(R.id.linearlayout_birthday_item_time_line);
            holder.vLine01 = convertView.findViewById(R.id.view_line_01_time_line_image);
            holder.vLine02 = convertView.findViewById(R.id.view_line_02_time_line_image);
            holder.vLine03 = convertView.findViewById(R.id.view_line_03_time_line_image);
            holder.vLine04 = convertView.findViewById(R.id.view_line_04_time_line_image);
            holder.vLine05 = convertView.findViewById(R.id.view_line_05_time_line_image);
            holder.vLine06 = convertView.findViewById(R.id.view_line_06_time_line_image);
            holder.vLine07 = convertView.findViewById(R.id.view_line_07_time_line_image);
            holder.vLine08 = convertView.findViewById(R.id.view_line_08_time_line_image);
            holder.ivAvatar = (ImageView) convertView.findViewById(R.id.imageview_avatar_item_time_line);
            holder.ivShare = (ImageView) convertView.findViewById(R.id.imageview_share_item_time_line);
            holder.ivDelete = (ImageView) convertView.findViewById(R.id.imageview_delete_item_time_line);
            imageViewList.add(holder.ivImage01);
            imageViewList.add(holder.ivImage02);
            imageViewList.add(holder.ivImage03);
            imageViewList.add(holder.ivImage04);
            imageViewList.add(holder.ivImage05);
            imageViewList.add(holder.ivImage06);
            imageViewList.add(holder.ivImage07);
            imageViewList.add(holder.ivImage08);
            imageViewList.add(holder.ivImage09);
            convertView.setTag(holder);
        } else {
            holder = (NewTimeLineViewHolder) convertView.getTag();
            imageViewList.add(holder.ivImage01);
            imageViewList.add(holder.ivImage02);
            imageViewList.add(holder.ivImage03);
            imageViewList.add(holder.ivImage04);
            imageViewList.add(holder.ivImage05);
            imageViewList.add(holder.ivImage06);
            imageViewList.add(holder.ivImage07);
            imageViewList.add(holder.ivImage08);
            imageViewList.add(holder.ivImage09);
        }
        if (position < timeLineItemList.size()) {
            final TimeLineItem item = timeLineItemList.get(position);
            imageCount = item.getImageUrls().length;
            holder.tvBabyBirthday.setVisibility(View.GONE);
            holder.tvDate.setVisibility(View.VISIBLE);
            holder.llAge.setVisibility(View.VISIBLE);
            holder.llImage.setVisibility(View.VISIBLE);
            holder.llBirthday.setVisibility(View.GONE);
            if (item.getAgeYear() != null) {
                holder.tvAgeYear.setText(SpannableStringUtil.setAbsoluteSizeSpan(item.getAgeYear(), 24, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE));//设置Span，Spannable.SPAN_INCLUSIVE_INCLUSIVE：指定范围前后输入新的字符时前后都不包括效果
            } else {
                holder.tvAgeYear.setText("");
            }
            if (item.getAgeMonth() != null) {
                holder.tvAgeMonth.setText(SpannableStringUtil.setAbsoluteSizeSpan(item.getAgeMonth(), 24, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE));
            } else {
                holder.tvAgeMonth.setText("");
            }
            if (item.getAgeDay() != null) {
                holder.tvAgeDay.setText(SpannableStringUtil.setAbsoluteSizeSpan(item.getAgeDay(), 24, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE));
            } else {
                holder.tvAgeDay.setText("");
            }
            if (item.getDate() != null) {
                holder.tvDate.setText(item.getDate());
            } else {
                holder.tvDate.setText("");
            }
            if (item.getDescription() != null) {
                holder.tvDescription.setText(item.getDescription());
            } else {
                holder.tvDescription.setText("");
            }
            if (item.getImageUrls() != null) {
                if (isVideo(item.getImageUrls()[0])) {
                    Picasso.with(context).load(R.drawable.img_default_video).into(holder.ivImage01);
                } else {
                    Picasso.with(context).load(item.getImageUrls()[0]).into(holder.ivImage01);
                }
                displayTimeLineImage(item);
                int visible = View.VISIBLE;
                int invisible = View.INVISIBLE;
                int gone = View.GONE;
                switch (imageCount) {
                    case 1:
                        setViewVisibility(holder,
                                gone, gone, gone, gone, gone, gone, gone, gone,
                                visible, gone, gone, gone, gone, gone, gone, gone, gone);
                        break;
                    case 2:
                        setViewVisibility(holder,
                                visible, gone, gone, gone, gone, gone, gone, gone,
                                visible, visible, gone, gone, gone, gone, gone, gone, gone);
                        break;
                    case 3:
                        setViewVisibility(holder,
                                visible, visible, gone, gone, gone, gone, gone, gone,
                                visible, visible, visible, gone, gone, gone, gone, gone, gone);
                        break;
                    case 4:
                        setViewVisibility(holder,
                                visible, gone, visible, gone, gone, gone, visible, gone,
                                visible, visible, gone, visible, visible, gone, gone, gone, gone);
                        break;
                    case 5:
                        setViewVisibility(holder,
                                visible, visible, visible, invisible, gone, gone, visible, gone,
                                visible, visible, visible, visible, visible, invisible, gone, gone, gone);
                        break;
                    case 6:
                        setViewVisibility(holder,
                                visible, visible, visible, visible, gone, gone, visible, gone,
                                visible, visible, visible, visible, visible, visible, gone, gone, gone);
                        break;
                    case 7:
                        setViewVisibility(holder,
                                visible, visible, visible, visible, invisible, invisible, visible, visible,
                                visible, visible, visible, visible, visible, visible, visible, invisible, invisible);
                        break;
                    case 8:
                        setViewVisibility(holder,
                                visible, visible, visible, visible, visible, invisible, visible, visible,
                                visible, visible, visible, visible, visible, visible, visible, visible, invisible);
                        break;
                    case 9:
                        setViewVisibility(holder,
                                visible, visible, visible, visible, visible, visible, visible, visible,
                                visible, visible, visible, visible, visible, visible, visible, visible, visible);
                        break;
                }
            }
            if (imageCount != 4) {
                onTimeLineItemClick(holder.ivImage01, item, 0);
                onTimeLineItemClick(holder.ivImage02, item, 1);
                onTimeLineItemClick(holder.ivImage03, item, 2);
                onTimeLineItemClick(holder.ivImage04, item, 3);
                onTimeLineItemClick(holder.ivImage05, item, 4);
                onTimeLineItemClick(holder.ivImage06, item, 5);
                onTimeLineItemClick(holder.ivImage07, item, 6);
                onTimeLineItemClick(holder.ivImage08, item, 7);
                onTimeLineItemClick(holder.ivImage09, item, 8);
            } else {
                onTimeLineItemClick(holder.ivImage01, item, 0);
                onTimeLineItemClick(holder.ivImage02, item, 1);
                onTimeLineItemClick(holder.ivImage04, item, 2);
                onTimeLineItemClick(holder.ivImage05, item, 3);
            }
        } else {
            holder.tvBabyBirthday.setVisibility(View.VISIBLE);
            holder.tvDate.setVisibility(View.INVISIBLE);
            holder.llAge.setVisibility(View.GONE);
            holder.llImage.setVisibility(View.GONE);
            holder.llBirthday.setVisibility(View.VISIBLE);
            holder.tvDescription.setText("宝宝生日");
            Picasso.with(context).load(R.mipmap.ic_launcher).into(holder.ivAvatar);
            holder.tvBirthday.setText("2015年1月1日");
            holder.ivAvatar.setOnClickListener(this);
            holder.tvBirthday.setOnClickListener(this);
        }
        holder.ivShare.setOnClickListener(this);
        holder.ivDelete.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.imageview_avatar_item_time_line:
                ToastUtil.showShortToastByString(context, "宝宝头像");
                break;
            case R.id.textview_birthday_item_time_line:
                ToastUtil.showShortToastByString(context, "宝宝生日");
                break;
            case R.id.imageview_share_item_time_line:
                ToastUtil.showShortToastByString(context, "分享");
                break;
            case R.id.imageview_delete_item_time_line:
                ToastUtil.showShortToastByString(context, "删除");
                break;
        }
    }

    /**
     * 显示时间轴的图片
     *
     * @param item：TimeLineItem
     */
    private void displayTimeLineImage(TimeLineItem item) {
        if (imageCount != 4) {
            for (int i = 1; i < imageCount; i ++) {
                Picasso.with(context).load(item.getImageUrls()[i]).into(imageViewList.get(i));
            }
        } else {
            Picasso.with(context).load(item.getImageUrls()[1]).into(imageViewList.get(1));
            Picasso.with(context).load(item.getImageUrls()[2]).into(imageViewList.get(3));
            Picasso.with(context).load(item.getImageUrls()[3]).into(imageViewList.get(4));
        }
    }

    /**
     * 设置View的可见性
     *
     * @param holder：NewTimeLineViewHolder
     * @param viewVisibility01：View可见性
     * @param viewVisibility02：View可见性
     * @param viewVisibility03：View可见性
     * @param viewVisibility04：View可见性
     * @param viewVisibility05：View可见性
     * @param viewVisibility06：View可见性
     * @param viewVisibility07：View可见性
     * @param viewVisibility08：View可见性
     * @param imageViewVisibility01：ImageView可见性
     * @param imageViewVisibility02：ImageView可见性
     * @param imageViewVisibility03：ImageView可见性
     * @param imageViewVisibility04：ImageView可见性
     * @param imageViewVisibility05：ImageView可见性
     * @param imageViewVisibility06：ImageView可见性
     * @param imageViewVisibility07：ImageView可见性
     * @param imageViewVisibility08：ImageView可见性
     * @param imageViewVisibility09：ImageView可见性
     */
    private void setViewVisibility(NewTimeLineViewHolder holder,
                                   int viewVisibility01, int viewVisibility02, int viewVisibility03, int viewVisibility04, int viewVisibility05, int viewVisibility06, int viewVisibility07, int viewVisibility08,
                                   int imageViewVisibility01, int imageViewVisibility02, int imageViewVisibility03, int imageViewVisibility04, int imageViewVisibility05, int imageViewVisibility06, int imageViewVisibility07, int imageViewVisibility08, int imageViewVisibility09) {
        holder.vLine01.setVisibility(viewVisibility01);
        holder.vLine02.setVisibility(viewVisibility02);
        holder.vLine03.setVisibility(viewVisibility03);
        holder.vLine04.setVisibility(viewVisibility04);
        holder.vLine05.setVisibility(viewVisibility05);
        holder.vLine06.setVisibility(viewVisibility06);
        holder.vLine07.setVisibility(viewVisibility07);
        holder.vLine08.setVisibility(viewVisibility08);
        holder.ivImage01.setVisibility(imageViewVisibility01);
        holder.ivImage02.setVisibility(imageViewVisibility02);
        holder.ivImage03.setVisibility(imageViewVisibility03);
        holder.ivImage04.setVisibility(imageViewVisibility04);
        holder.ivImage05.setVisibility(imageViewVisibility05);
        holder.ivImage06.setVisibility(imageViewVisibility06);
        holder.ivImage07.setVisibility(imageViewVisibility07);
        holder.ivImage08.setVisibility(imageViewVisibility08);
        holder.ivImage09.setVisibility(imageViewVisibility09);
    }

    /**
     * 时间轴项单击
     *
     * @param view：View
     * @param item：TimeLineItem
     * @param position：位置
     */
    private void onTimeLineItemClick(View view, final TimeLineItem item, final int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putStringArray("imageArr", item.getImageUrls());
                bundle.putInt("currentImage", position);
                intent.putExtras(bundle);
                if (isVideo(item.getImageUrls()[0])) {
                    intent.setClass(context, VideoDetailActivity.class);
                } else {
                    intent.setClass(context, ImageDetailActivity.class);
                }
                context.startActivity(intent);
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                if (!isVideo(item.getImageUrls()[0])) {
                    ToastUtil.showShortToastByString(context, "图片" + (position + 1));
                } else {
                    ToastUtil.showShortToastByString(context, "视频");
                }
            }
        });
    }

    /**
     * 是否是视频
     *
     * @param url：视频地址
     * @return boolean
     */
    private boolean isVideo(String url) {
        String result = url.substring(url.length() - 3, url.length());
        return result.equals("mp4") || result.equals("3gp");
    }

    private class NewTimeLineViewHolder {
        TextView tvAgeYear, tvAgeMonth, tvAgeDay, tvBabyBirthday, tvDate, tvDescription, tvBirthday;
        TimeLineSquaredImageView ivImage01, ivImage02, ivImage03, ivImage04, ivImage05, ivImage06, ivImage07, ivImage08, ivImage09;
        LinearLayout llAge, llImage, llBirthday;
        View vLine01, vLine02, vLine03, vLine04, vLine05, vLine06, vLine07, vLine08;
        ImageView ivAvatar, ivShare, ivDelete;
    }

}