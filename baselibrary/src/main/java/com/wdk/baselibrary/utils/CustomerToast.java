package com.wdk.baselibrary.utils;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;

import com.wdk.baselibrary.basepage.AppInitUtil;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 1:00 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 1:00 PM
 * @LastCheckBy: wdk
 */
public class CustomerToast {

    public static void showToast(String msg){
        if (TextUtils.isEmpty(msg)){
            return;
        }

        Toast.makeText(AppInitUtil.getInstance().getApplication(),msg,Toast.LENGTH_SHORT).show();

    }
}
