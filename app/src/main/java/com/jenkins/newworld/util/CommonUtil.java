package com.jenkins.newworld.util;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by zzj on 2018/5/21.
 */

public class CommonUtil {
    public static void log(String tag,String value){
        Log.e(tag,value);
        System.out.println("------"+tag+":"+value);
    }
}
