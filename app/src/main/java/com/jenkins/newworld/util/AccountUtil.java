package com.jenkins.newworld.util;

import android.content.Context;

import io.vov.vitamio.utils.Log;

/**
 * Created by zhouzhenjian on 2018/5/3.
 */

public class AccountUtil {
    public static boolean isLogin(Context context){
        boolean result = false;
        if (context==null){
            Log.e("account util","请输入正确的上下文");
        }else{
            String user_name = (String)SPHelper.get(context,"user_name","");
            Log.e("user_name",user_name);
            if (user_name!=null&&!user_name.equals("")){
                result = true;//说明已经登录了
            }
        }

        return result;
    }
}
