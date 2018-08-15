package top.douruanliang.xsan.core.net;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import top.douruanliang.xsan.core.app.ConfigType;
import top.douruanliang.xsan.core.app.Xsan;

/**
 * author: dourl
 * created on: 2018/8/14 下午5:40
 * description:
 */
public class RestCreator {

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class ParamsHolder{
        public static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Xsan.getConfigurations()
                .get(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();


    }

    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;

        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }


    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
                .create(RestService.class);
    }


}