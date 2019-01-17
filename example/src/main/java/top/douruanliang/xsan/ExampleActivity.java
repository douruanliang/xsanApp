package top.douruanliang.xsan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import top.douruanliang.xsan.core.activity.ProxyActivity;
import top.douruanliang.xsan.core.app.Xsan;
import top.douruanliang.xsan.core.delegate.XsanDelegate;
import top.douruanliang.xsan.core.ui.launcher.ILauncherListener;
import top.douruanliang.xsan.core.ui.launcher.OnLauncherFinishTag;
import top.douruanliang.xsan.ec.launcher.LauncherDelegate;
import top.douruanliang.xsan.ec.main.XsanBottomDelegate;
import top.douruanliang.xsan.ec.sign.ISignListener;
import top.douruanliang.xsan.ec.sign.SignInDelegate;
import top.douruanliang.xsan.ec.sign.SignUpDelegate;

/**
 * author: dourl
 * created on: 2018/8/14 下午5:07
 * description:
 */
public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.hide();
        Xsan.getConfigurator().withActivity(this);
    }

    @Override
    public XsanDelegate setRootDelegate() {
        return new LauncherDelegate();
    }


    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG);
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG);
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGHED:
                Toast.makeText(this, "启动结束已登录", Toast.LENGTH_LONG);
               startWithPop(new XsanBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束未登录", Toast.LENGTH_LONG);
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
