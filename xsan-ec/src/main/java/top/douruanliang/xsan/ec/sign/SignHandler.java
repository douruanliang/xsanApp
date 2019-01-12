package top.douruanliang.xsan.ec.sign;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import top.douruanliang.xsan.core.app.AccountManager;
import top.douruanliang.xsan.ec.database.DatabaseManager;
import top.douruanliang.xsan.ec.database.UserProfile;

/**
 * 辅助类
 */
public class SignHandler {

    //注册
    public static void onSignUp(String response,ISignListener listener){

        final JSONObject profileJson  = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");


        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(userProfile);
        //已经注册并登录成功
        AccountManager.setSignState(true);
        listener.onSignUpSuccess();

    }
    //登录
    public static void onSignIn(String response,ISignListener listener){

        final JSONObject profileJson  = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");


        final UserProfile userProfile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(userProfile);
        //已经注册并登录成功
        AccountManager.setSignState(true);
        listener.onSignInSuccess();

    }

}
