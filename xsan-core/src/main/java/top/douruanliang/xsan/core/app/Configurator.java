package top.douruanliang.xsan.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * author: dourl
 * created on: 2018/8/13 下午12:56
 * description: 配置文件的存储和获取
 */
public class Configurator {
    private static final HashMap<Object, Object> XSAN_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    public Configurator() {
        XSAN_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);

    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    //线程安全的懒汉
    public static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        initIcons();
        XSAN_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);

    }

    final HashMap<Object, Object> getXsanConfigs() {
        return XSAN_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        XSAN_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }
    //添加（单）拦截器
    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        XSAN_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    public final Configurator withInterceptor(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        XSAN_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }
    //字体
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));

            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    //检查配置项是否完成
    private void checkConfiguration() {
        final boolean isReady = (boolean) XSAN_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is  not ready ,call configuration");
        }
    }
/*
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigKeys> key) {
        checkConfiguration();
        return (T) XSAN_CONFIGS.get(key.name());
    }*/

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = XSAN_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) XSAN_CONFIGS.get(key);
    }
}
