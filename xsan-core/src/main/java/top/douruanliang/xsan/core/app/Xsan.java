package top.douruanliang.xsan.core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * author: dourl
 * created on: 2018/8/13 下午1:17
 * description: 对外的工具类
 */
public final class Xsan {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    };

    private static WeakHashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getXsanConfigs();

    }
}
