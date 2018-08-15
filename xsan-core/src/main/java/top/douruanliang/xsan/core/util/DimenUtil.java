package top.douruanliang.xsan.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import top.douruanliang.xsan.core.app.Xsan;

/**
 * author: dourl
 * created on: 2018/8/15 上午11:44
 * description:
 */
public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Xsan.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Xsan.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
