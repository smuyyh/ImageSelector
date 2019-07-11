package com.yuyh.library.imgsel.utils;

import android.content.Context;

/**
 * Created by yuyuhang on 2019-07-11.
 */
public class DisplayUtils {

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
