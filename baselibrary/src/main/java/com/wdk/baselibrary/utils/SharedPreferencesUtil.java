package com.wdk.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wdk.baselibrary.basepage.AppInitUtil;
import com.wdk.baselibrary.common.Constants;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 6:28 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 6:28 PM
 * @LastCheckBy: wdk
 */
public class SharedPreferencesUtil {

    SharedPreferences sp;

    private SharedPreferencesUtil() {
        createPreferences();
    }

    public static SharedPreferencesUtil getInstance() {
        return InnerClass.sharedPreferencesUtil;
    }

    private static class InnerClass {
        public static SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil();
    }

    /***
     * 实例化
     **/
    public void createPreferences() {
        sp = AppInitUtil.getInstance().getApplication().getSharedPreferences(Constants.sp_name, Context.MODE_PRIVATE);
    }

    /**
     * 取得String类型数据
     **/
    public String getStringValue(String key) {
        if (sp == null) {
            createPreferences();
        }
        try {
            if (sp.contains(key)) {
                return sp.getString(key, "");
            }
        } catch (Exception e) {

        }

        return "";
    }

    /**
     * 存放String类型数据
     **/
    public void putStringValue(String xml, String str) {
        if (sp == null) {
            createPreferences();
        }

        if (str == null) {
            str = "";
        }

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(xml, str);
        editor.apply();
    }
}
