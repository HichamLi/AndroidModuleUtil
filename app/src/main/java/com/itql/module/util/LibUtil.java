package com.itql.module.util;

import android.content.pm.ApplicationInfo;

public class LibUtil {
    public static void runOnUIThread(Runnable runnable) {
        runOnUIThread(runnable, 0);
    }

    public static void runOnUIThread(Runnable runnable, long delay) {
        if (delay <= 0 && Thread.currentThread().getId() == LibUtilApplication.getTid()) {
            runnable.run();
        } else {
            LibUtilApplication.getHandler().postDelayed(runnable, delay);
        }
    }

    public static boolean isDebug() {
        try {
            ApplicationInfo info = LibUtilApplication.getContext().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int dp2px(float value) {
        float scale = LibUtilApplication.getApplication().getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     */
    public static int px2dp(float value) {
        float scale = LibUtilApplication.getApplication().getResources().getDisplayMetrics().density;
        return (int) (value / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(float value) {
        final float fontScale = LibUtilApplication.getApplication().getResources().getDisplayMetrics().scaledDensity;
        return (int) (value * fontScale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(float value) {
        final float fontScale = LibUtilApplication.getApplication().getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / fontScale + 0.5f);
    }
}
