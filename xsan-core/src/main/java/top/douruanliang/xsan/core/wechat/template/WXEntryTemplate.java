package top.douruanliang.xsan.core.wechat.template;

import top.douruanliang.xsan.core.activity.ProxyActivity;
import top.douruanliang.xsan.core.delegate.XsanDelegate;
import top.douruanliang.xsan.core.wechat.BaseWXActivity;
import top.douruanliang.xsan.core.wechat.BaseWXEntryActivity;
import top.douruanliang.xsan.core.wechat.XsanWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        XsanWeChat.getInstance().getIWeChatSignInCallback().onSignInSuccess(userInfo);
    }
}
