package top.douruanliang.xsan.core.ui.loader;

import android.content.Context;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * author: dourl
 * created on: 2018/8/15 上午10:40
 * description: 加载动画
 */
public class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String name, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(name) == null){
            final Indicator indicator = getIndicator(name);
            LOADING_MAP.put(name,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(name));

        return  avLoadingIndicatorView;
    }



    private static Indicator getIndicator(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        final StringBuilder drawbleClassName = new StringBuilder();

        if (name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawbleClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawbleClassName.append(name);

        try {
            Class<?> drawbleClass = Class.forName(drawbleClassName.toString());
            return (Indicator) drawbleClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
