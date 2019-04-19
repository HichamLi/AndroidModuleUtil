package com.itql.module.util;

import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    public static int getColor(int res) {
        return LibUtilApplication.getContext().getResources().getColor(res);
    }

    public static String getString(int res) {
        return LibUtilApplication.getContext().getResources().getString(res);
    }

    public static View inflate(int res) {
        return inflate(res, null, false);
    }

    public static View inflate(int res, ViewGroup group) {
        return inflate(res, group, false);
    }

    public static View inflate(int res, ViewGroup group, boolean attachToRoot) {
        return LayoutInflater.from(group == null ? LibUtilApplication.getContext() : group.getContext()).inflate(res, group, attachToRoot);
    }

    public static void showToast(int res) {
        showToast(getString(res));
    }

    public static void showToast(int res, int duration) {
        showToast(getString(res), duration);
    }

    public static void showToast(String s) {
        Toast.makeText(LibUtilApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(String s, int duration) {
        Toast.makeText(LibUtilApplication.getContext(), s, duration).show();
    }
}
