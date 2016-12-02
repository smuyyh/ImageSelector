package com.yuyh.library.imgsel.utils;

import android.app.Application;
import android.content.Context;

/**
 * @author yuyh.
 * @date 2016/12/2.
 */
public class SelApp extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;
    }

    public static String getResString(int resId) {
        return sContext.getResources().getString(resId);
    }
}
