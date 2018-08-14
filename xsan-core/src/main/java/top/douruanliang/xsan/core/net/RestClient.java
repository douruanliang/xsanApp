package top.douruanliang.xsan.core.net;

import java.util.Map;
import java.util.WeakHashMap;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import top.douruanliang.xsan.core.net.callback.IError;
import top.douruanliang.xsan.core.net.callback.IFailure;
import top.douruanliang.xsan.core.net.callback.IRequest;
import top.douruanliang.xsan.core.net.callback.ISuccess;
import top.douruanliang.xsan.core.net.callback.RequestCallBacks;

/**
 * author: dourl
 * created on: 2018/8/14 下午5:28
 * description: 参数不可修改
 */
public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;


    public RestClient(String URL
            , Map<String
            , Object> params
            , IRequest request
            , ISuccess success
            , IFailure failure
            , IError error
            , RequestBody body) {
        this.URL = URL;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    /**
     * 创建构造者
     */
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
            case DELETE:
                call = service.delete(URL, PARAMS);
            default:
                break;
        }


        if (call != null) {
            call.enqueue(getRequestCallBacks());
        }
    }

    private Callback<String> getRequestCallBacks() {
        return new RequestCallBacks(REQUEST, SUCCESS, FAILURE, ERROR);
    }


    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
