package com.yuzhentao.studioprojecttimeline.util;

import com.yuzhentao.studioprojecttimeline.activity.TimeLineActivity;

/**
 * 常量类
 * Intent的键使用INTENT_开头
 * SharedPreferences的键使用SP_开头
 *
 * @author Violetjack
 */
@SuppressWarnings("unused")
public class Param {

    /**
     * 启动应用时是否显示引导页或登录页
     */
    public static final String SP_SHOW_GUIDE = "sp_show_guide";
    public static final String SP_SHOW_PROGRESS = "sp_show_progress";
    public static final int FIRST = 1000;//第一次启动应用
    public static final int NOT_SIGNED_IN = 1001;//未登录
    public static final int SIGNED_IN = 1002;//已登录

    /**
     * 测试模式开关参数
     * true：用户模式
     * false：测试模式
     */
    public static final boolean IS_USER = false;
    public static final Class<?> TARGET_CLASS = TimeLineActivity.class;//目标class

    /**
     * 主界面
     */
    public static final String SP_CURRENT_YEAR = "sp_current_year";

    /**
     * 宝宝档案
     */
    public static final String SP_BABY_NAME = "sp_baby_name";
    public static final String SP_BABY_BIRTHDAY = "sp_baby_birthday";
    public static final String SP_BABY_AGE = "sp_baby_age";
    public static final String SP_BABY_HOBBY = "sp_baby_hobby";
    public static final String SP_BABY_IN_PARENTS = "sp_baby_in_parents";
    public static final String SP_BITMAP_SAVE_NAME = "sp_bitmap_save_name";

    /**
     * 宝宝头像
     */
    public static final String SP_BABY_AVATAR_NAME = "sp_baby_avatar_name";
    public static final String SP_BABY_AVATAR_PATH = "sp_baby_avatar_path";
    public static final String INTENT_BABY_AVATAR_PATH = "intent_baby_avatar_path";

    /**
     * 跳转到EditActivity的Intent的Key
     */
    public static final String INTENT_EDIT_TITLE = "intent_edit_title";
    public static final String INTENT_EDIT_CONTENT = "intent_edit_content";

    /**
     * 每月成长
     */
    public static final String SP_CURRENT_MONTHS = "sp_current_months";
    public static final String PARENTS_MESSAGE = "parents_message";

    /**
     * ImageModeSelectActivity
     */
    public static final int SELECTED_MODE_SINGLE = 0;//单选模式
    public static final int SELECTED_MODE_MULTIPLE = 1;//多选模式
    public static final int SELECTED_MODE_SINGLE_CROP = 2;//单选裁切
    public static final int POSITION_TYPE_CAMERA = 4;//相机位置
    public static final int POSITION_TYPE_IMAGE = 5;//图片位置
    public static final int REQUEST_CODE_IMAGE = 6;//请求码
    public static final String SP_POSITION_TYPE = "sp_position_type";//位置类型
    public static final String SP_SELECTED_MODE = "sp_selected_mode";//选择模式（用来选择照片存储位置）
    public static final String INTENT_SELECTED_MODE = "selected_mode";//选择模式
    public static final String INTENT_MAX_SELECTED_IMAGE_COUNT = "max_selected_image_count";//最多可选择的图片数量
    public static final String INTENT_IS_NEED_CROP_IMAGE = "is_need_crop_image";//是否需要裁切图片
    public static final String SP_IS_EXIT_FRAGMENT = "is_exit_fragment";//是否是退出Fragment
    public static final String INTENT_IMAGE_PATH_LIST = "image_path_list";//图片路径集合

}