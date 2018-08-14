package top.douruanliang.xsan;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import top.douruanliang.xsan.core.app.Xsan;
import top.douruanliang.xsan.ec.icon.FontECModule;

/**
 * author: dourl
 * created on: 2018/8/14 下午2:42
 * description:
 */
public class XsanApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Xsan.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontECModule())
                .withApiHost("http://127.0.01")
                .configure();
    }
}
