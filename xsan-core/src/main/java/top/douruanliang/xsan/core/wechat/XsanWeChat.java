package top.douruanliang.xsan.core.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import top.douruanliang.xsan.core.app.ConfigKeys;
import top.douruanliang.xsan.core.app.Xsan;
import top.douruanliang.xsan.core.wechat.callBacks.IWeChatSignInCallback;

public class XsanWeChat {

    static final String APP_ID = Xsan.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    static final String APP_SECRET = Xsan.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);

    private final IWXAPI WXAPI;

    private IWeChatSignInCallback mIWeChatSignInCallback;

    //初始化 API
    private XsanWeChat() {
        final Activity activity = Xsan.getConfiguration(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }


    //懒汉
    private static final class Holder {
        private static final XsanWeChat INSTENCE = new XsanWeChat();
    }

    public static XsanWeChat getInstance() {
        return Holder.INSTENCE;
    }

    //获取API
    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

    public XsanWeChat onSignSuccess(IWeChatSignInCallback callback){
        this.mIWeChatSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getIWeChatSignInCallback(){
        return mIWeChatSignInCallback;
    }
}
