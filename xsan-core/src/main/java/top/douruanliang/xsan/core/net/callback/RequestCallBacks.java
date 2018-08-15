package top.douruanliang.xsan.core.net.callback;


import android.os.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import top.douruanliang.xsan.core.ui.loader.LoaderStyle;
import top.douruanliang.xsan.core.ui.loader.XsanLoader;

public class RequestCallBacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADERSTYLE;
    private static  final Handler HANDLER = new Handler();


    public RequestCallBacks(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADERSTYLE =style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stoploading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stoploading();
    }

    private void stoploading(){
        if (LOADERSTYLE!=null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    XsanLoader.stopLoading();
                }
            },1000);
        }
    }
}
