package top.douruanliang.xsan.core.net;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import top.douruanliang.xsan.core.net.callback.IError;
import top.douruanliang.xsan.core.net.callback.IFailure;
import top.douruanliang.xsan.core.net.callback.IRequest;
import top.douruanliang.xsan.core.net.callback.ISuccess;

/**
 * author: dourl
 * created on: 2018/8/14 下午5:28
 * description: 参数不可修改
 */
public class RestClient {

     private final String URL;
     private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();

     private final IRequest REQUEST;
     private final ISuccess SUCCESS;
     private final IFailure DAILURE;
     private final IError   ERROR;
     private final RequestBody BODY;


     public RestClient(String URL
             , Map<String
             , Object> params
             , IRequest request
             , ISuccess success
             , IFailure dailure
             , IError error
             , RequestBody body) {
          this.URL = URL;
          PARAMS.putAll(params);
          this.REQUEST = request;
          this.SUCCESS = success;
          this.DAILURE = dailure;
          this.ERROR = error;
          this.BODY = body;
     }
     /**
      * 创建构造者
      */
     public static RestClientBuilder builder (){
          return new RestClientBuilder();
     }
}
