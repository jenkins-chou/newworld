package com.jenkins.newworld.util;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by zhouzhenjian on 2018/4/13.
 */

public class CommonWindowUtil {
    public static boolean SetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        int version = android.os.Build.VERSION.SDK_INT;
        if (version >= 24) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }else{
            //flyme系统修改状态栏文字
            if (window != null) {
                try {
                    WindowManager.LayoutParams lp = window.getAttributes();
                    Field darkFlag = WindowManager.LayoutParams.class
                            .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                    Field meizuFlags = WindowManager.LayoutParams.class
                            .getDeclaredField("meizuFlags");
                    darkFlag.setAccessible(true);
                    meizuFlags.setAccessible(true);
                    int bit = darkFlag.getInt(null);
                    int value = meizuFlags.getInt(lp);
                    if (dark) {
                        value |= bit;
                    } else {
                        value &= ~bit;
                    }
                    meizuFlags.setInt(lp, value);
                    window.setAttributes(lp);
                    result = true;
                } catch (Exception e) {
                }
            }
        }
        return result;
    }

    public static void setLightStatusBar(Window window){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
