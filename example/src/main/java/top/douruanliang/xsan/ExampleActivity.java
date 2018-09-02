package top.douruanliang.xsan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import top.douruanliang.xsan.core.activity.ProxyActivity;
import top.douruanliang.xsan.core.delegate.XsanDelegate;
import top.douruanliang.xsan.ec.launcher.LauncherDelegate;
import top.douruanliang.xsan.ec.sign.SignUpDelegate;

/**
 * author: dourl
 * created on: 2018/8/14 下午5:07
 * description:
 */
public class ExampleActivity  extends ProxyActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar!= null)
            actionBar.hide();
    }

    @Override
    public XsanDelegate setRootDelegate() {
        return new SignUpDelegate();
    }



}
