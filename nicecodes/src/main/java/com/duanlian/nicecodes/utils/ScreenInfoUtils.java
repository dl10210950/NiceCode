package com.duanlian.nicecodes.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

public class ScreenInfoUtils {
    private static final String TAG = "ScreenInfoUtils";

    /**
     * Get Screen Width
     */
    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * Get Screen Height
     */
    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }


    /**
     * Get Screen Real Height
     *
     * @param context Context
     * @return Real Height
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getRealHeight(Context context) {
        Display display = getDisplay(context);
        if (display == null) {
            return 0;
        }
        DisplayMetrics dm = new DisplayMetrics();
        display.getRealMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * Get Screen Real Width
     *
     * @param context Context
     * @return Real Width
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static int getRealWidth(Context context) {
        Display display = getDisplay(context);
        if (display == null) {
            return 0;
        }
        DisplayMetrics dm = new DisplayMetrics();
        display.getRealMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * Get StatusBar Height
     */
    public static int getStatusBarHeight(Context mContext) {
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * Get ActionBar Height
     */
    public static int getActionBarHeight(Context mContext) {
        TypedValue tv = new TypedValue();
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, mContext.getResources().getDisplayMetrics());
        }
        return 0;
    }

    /**
     * Get NavigationBar Height
     */
    public static int getNavigationBarHeight(Context mContext) {
        Resources resources = mContext.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    /**
     * Get Density
     */
    private static float getDensity(Context context) {
        return getDisplayMetrics(context).density;
    }

    /**
     * Get Dpi
     */
    private static int getDpi(Context context) {
        return getDisplayMetrics(context).densityDpi;
    }

    /**
     * Get Display
     *
     * @param context Context for get WindowManager
     * @return Display
     */
    private static Display getDisplay(Context context) {
        WindowManager wm;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            wm = activity.getWindowManager();
        } else {
            wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        if (wm != null) {
            return wm.getDefaultDisplay();
        }
        return null;
    }

    /**
     * Get DisplayMetrics
     *
     * @param context Context for get Resources
     * @return DisplayMetrics
     */
    private static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }


    /**
     * Get ScreenInfo
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static String getScreenInfo(Context context) {
        return " \n" +
                "--------ScreenInfo--------" + "\n" +
                "Screen Width : " + getScreenWidth(context) + "px\n" +
                "Screen RealWidth :" + getRealWidth(context) + "px\n" +
                "Screen Height: " + getScreenHeight(context) + "px\n" +
                "Screen RealHeight: " + getRealHeight(context) + "px\n" +
                "Screen StatusBar Height: " + getStatusBarHeight(context)+ "px\n" +
                "Screen ActionBar Height: " + getActionBarHeight(context)+ "px\n" +
                "Screen NavigationBar Height: " + getNavigationBarHeight(context)+ "px\n" +
                "Screen Dpi: " + getDpi(context) + "\n" +
                "Screen Density: " + getDensity(context) + "\n" +
                "--------------------------";
    }


    /**
     * Print screenInfo to logcat
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void printScreenInfo(Context context) {
        Log.d(TAG, getScreenInfo(context));
    }
}
