package top.douruanliang.xsan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import top.douruanliang.xsan.core.delegate.XsanDelegate;
import top.douruanliang.xsan.core.net.RestClient;
import top.douruanliang.xsan.core.net.callback.IError;
import top.douruanliang.xsan.core.net.callback.IFailure;
import top.douruanliang.xsan.core.net.callback.ISuccess;

/**
 * author: dourl
 * created on: 2018/8/14 下午5:08
 * description:
 */
public class ExampleDelegate extends XsanDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_layout;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View view) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://hi.baidu.com/")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
