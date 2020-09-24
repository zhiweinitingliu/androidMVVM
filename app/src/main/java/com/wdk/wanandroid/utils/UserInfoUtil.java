package com.wdk.wanandroid.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.wdk.baselibrary.utils.SharedPreferencesUtil;
import com.wdk.wanandroid.constances.Constants;
import com.wdk.wanandroid.data.bean.LoginResponseBean;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/24 8:29 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/24 8:29 AM
 * @LastCheckBy: wdk
 */
public class UserInfoUtil {

    private UserInfoUtil() {

    }

    public static UserInfoUtil getInstance() {
        return InnerClass.userInfoUtil;
    }

    private static class InnerClass {
        public static UserInfoUtil userInfoUtil = new UserInfoUtil();
    }

    public int getUserId() {
        LoginResponseBean loginBean = getLoginBean();
        if (loginBean != null) {
            return getLoginBean().getId();
        }

        return -1;
    }

    private LoginResponseBean getLoginBean() {

        String loginJson = SharedPreferencesUtil.getInstance().getStringValue(Constants.user_info_json);
        if (TextUtils.isEmpty(loginJson)) {
            return null;
        }
        return new Gson().fromJson(loginJson, LoginResponseBean.class);
    }
}
