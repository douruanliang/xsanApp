package top.douruanliang.xsan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import top.douruanliang.xsan.core.delegate.XsanDelegate;

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

    }
}
