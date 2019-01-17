package top.douruanliang.xsan.core.delegate.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import top.douruanliang.xsan.core.R;
import top.douruanliang.xsan.core.delegate.XsanDelegate;

public abstract class BottomItemDelegete extends XsanDelegate implements View.OnKeyListener {

    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();

        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME){
                Toast.makeText(getContext(),"双击退出"+getString(R.string.app_name),Toast.LENGTH_LONG).show();
                mExitTime = System.currentTimeMillis();
            }else{
                _mActivity.finish();
                if (mExitTime!=0){
                    mExitTime =0;
                }
            }
            return true;
        }
        return false;
    }
}
