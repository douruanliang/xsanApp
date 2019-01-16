package top.douruanliang.xsan;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import top.douruanliang.xsan.core.app.Xsan;
import top.douruanliang.xsan.core.net.interceptors.DebugInterceptor;
import top.douruanliang.xsan.ec.database.DatabaseManager;
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
        //配置初始化
        Xsan.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontECModule())
                .withApiHost("http://127.0.0.1")
                .withLoaderDelayed(1000)
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .withWechatAppId("wx1fd21c7f4bf78aba")
                .withWechatAppSecret("0c0cdea715f70932abc2921febc589e2")
                .configure();
        //测试
        initStetho();
        //数据库初始化
        DatabaseManager.getInstance().init(this);
    }

    private void initStetho(){
        Stetho.initialize(Stetho.newInitializerBuilder(this)
        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());
    }
}
