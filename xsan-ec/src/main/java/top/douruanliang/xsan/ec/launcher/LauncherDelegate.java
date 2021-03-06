package top.douruanliang.xsan.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import top.douruanliang.xsan.core.app.AccountManager;
import top.douruanliang.xsan.core.app.IUserChecker;
import top.douruanliang.xsan.core.delegate.XsanDelegate;
import top.douruanliang.xsan.core.ui.launcher.ILauncherListener;
import top.douruanliang.xsan.core.ui.launcher.OnLauncherFinishTag;
import top.douruanliang.xsan.core.util.timer.BaseTimerTask;
import top.douruanliang.xsan.core.util.timer.ITimerListener;
import top.douruanliang.xsan.ec.R;
import top.douruanliang.xsan.ec.R2;

public class LauncherDelegate extends XsanDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView tvLauncherTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimeView() {
    }

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener;


    private void initTimer() {
        mTimer = new Timer();
        BaseTimerTask timerTask = new BaseTimerTask(this);
        mTimer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof ILauncherListener)
            mILauncherListener = (ILauncherListener) activity;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle saveInstanceState, View view) {
        initTimer();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTimer != null) {
                    tvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                        }
                    }
                }
            }
        });
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        //XsanPreference.getAppFlag()

        //检查用户是否登录APP
        AccountManager.checkAccount(new IUserChecker() {
            @Override
            public void onSignIn() {
                if (mILauncherListener != null) {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGHED);
                }

            }

            @Override
            public void onNotSignIn() {
                if (mILauncherListener != null) {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                }
            }
        });
    }
}
