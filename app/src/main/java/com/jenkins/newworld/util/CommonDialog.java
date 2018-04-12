package com.jenkins.newworld.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by zhouzhenjian on 2018/4/8.
 */

public class CommonDialog {

    //显示普通dialog
    public static void showBaseDialog(Context context,String message){
        new SweetAlertDialog(context)
                .setTitleText(message)
                .show();
    }
    //显示带标题dialog
    public static void showTitleDialog(Context context,String title,String message){
        new SweetAlertDialog(context)
                .setTitleText(title)
                .setTitleText(message)
                .show();
    }
    //显示错误dialog
    public static void showErrorDialog(Context context,String title,String message){
        new SweetAlertDialog(context,SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setTitleText(message)
                .show();
    }
    //显示警告dialog
    public static void showWarningDialog(Context context,String title,String message){
        new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setTitleText(message)
                .show();
    }
    //显示成功dialog
    public static void showSuccessDialog(Context context,String title,String message){
        new SweetAlertDialog(context,SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setTitleText(message)
                .show();
    }
    //显示自定义图标dialog
    public static void showIconDialog(Context context,String title,String message,int icon){
        new SweetAlertDialog(context,SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText(title)
                .setTitleText(message)
                .setCustomImage(icon)
                .show();
    }

    //显示带确认按钮和监听器的dialog
    public static void showConfirmDialog(Context context,String title,String message){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setConfirmText("Yes,delete it!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }
    //显示带取消按钮和监听器的dialog
    public static void showCancelDialog(Context context,String title,String message){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setConfirmText("Yes,delete it!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

    //Change the dialog style upon confirming：
    public static void showCustomDialog(Context context,String title,String message){
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setConfirmText("Yes,delete it!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog
                                .setTitleText("Deleted!")
                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                })
                .show();
    }
}
