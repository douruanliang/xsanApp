package top.douruanliang.xsan.core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * author: dourl
 * created on: 2018/8/13 下午1:17
 * description: 对外的工具类
 */
public final class Xsan {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    };

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }
     public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
     }
    public static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getXsanConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }
}
