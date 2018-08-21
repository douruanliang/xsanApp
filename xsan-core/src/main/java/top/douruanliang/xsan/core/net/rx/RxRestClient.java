package top.douruanliang.xsan.core.net.rx;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import top.douruanliang.xsan.core.net.HttpMethod;
import top.douruanliang.xsan.core.net.RestCreator;
import top.douruanliang.xsan.core.net.RestService;
import top.douruanliang.xsan.core.net.callback.IError;
import top.douruanliang.xsan.core.net.callback.IFailure;
import top.douruanliang.xsan.core.net.callback.IRequest;
import top.douruanliang.xsan.core.net.callback.ISuccess;
import top.douruanliang.xsan.core.net.callback.RequestCallBacks;
import top.douruanliang.xsan.core.net.download.DownloadHandler;
import top.douruanliang.xsan.core.ui.loader.LoaderStyle;
import top.douruanliang.xsan.core.ui.loader.XsanLoader;

/**
 * author: dourl
 * created on: 2018/8/14 下午5:28
 * description: 参数不可修改
 */
public class RxRestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final RequestBody BODY;
    private final LoaderStyle LOADERSTYLE;
    private final File FILE;
    private final Context CONTEXT;

    public RxRestClient(String URL,
                        Map<String, Object> params,
                        RequestBody body,
                        File file,
                        Context context, LoaderStyle loaderstyle
    ) {
        this.URL = URL;
        PARAMS.putAll(params);
        this.BODY = body;
        this.FILE = file;
        this.LOADERSTYLE = loaderstyle;
        this.CONTEXT = context;
    }

    /**
     * 创建构造者
     */
    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestservice();
        Observable<String> observable = null;


        if (LOADERSTYLE != null) {
            XsanLoader.showLoading(CONTEXT, LOADERSTYLE);
        }
        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                _RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            default:
                break;
        }
            return  observable;
    }


    public final Observable get() {
        return request(HttpMethod.GET);
    }

    public final Observable post() {
        if (BODY == null) {
           return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return  request(HttpMethod.POST_RAW);
        }
    }

    public final Observable put() {
        if (BODY == null) {
            return  request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return  request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        return RestCreator.getRxRestservice().download(URL,PARAMS);
    }
}
