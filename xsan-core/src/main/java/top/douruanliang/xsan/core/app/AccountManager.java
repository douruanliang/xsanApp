package top.douruanliang.xsan.core.app;

import top.douruanliang.xsan.core.util.storage.XsanPreference;

public class AccountManager {

    private enum SignTag{
        SIGN_TAG

    }
    //保存用户登录状态 登录后调用
    public static void setSignState(boolean state){

        XsanPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);

    }

    public static boolean isSignIn(){
        return XsanPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker iUserChecker){
        if (isSignIn()){
            iUserChecker.onSignIn();
        }else
        {
            iUserChecker.onNotSignIn();
        }
    }
}
