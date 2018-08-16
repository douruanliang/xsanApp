package top.douruanliang.xsan.core.net.interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
