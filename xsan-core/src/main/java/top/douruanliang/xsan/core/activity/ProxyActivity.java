package top.douruanliang.xsan.core.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import me.yokeyword.fragmentation.SupportActivity;
import top.douruanliang.xsan.core.R;
import top.douruanliang.xsan.core.delegate.XsanDelegate;

/**
 * author: dourl
 * created on: 2018/8/14 下午3:57
 * description:  单activuty
 */
public abstract class ProxyActivity extends SupportActivity {

    public abstract XsanDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.delegate_container);
        setContentView(contentFrameLayout);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.gc();
        System.runFinalization();
    }
}
