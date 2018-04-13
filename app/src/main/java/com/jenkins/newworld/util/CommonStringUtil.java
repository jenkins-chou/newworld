package com.jenkins.newworld.util;

/**
 * Created by zhouzhenjian on 2018/4/13.
 */

public class CommonStringUtil {
    public static String subString(String src,int length){
        if (src==null||src.equals("")){

        }else{
            if (src.length()>length){
                src = src.substring(0,length);
            }
        }
        return src;
    }
}
