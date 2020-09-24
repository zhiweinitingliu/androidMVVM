package com.wdk.wanandroid.ui.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.wdk.baselibrary.basepage.BaseActivity;
import com.wdk.baselibrary.basepage.DataBindingConfig;
import com.wdk.baselibrary.utils.CustomerToast;
import com.wdk.wanandroid.R;
import com.wdk.wanandroid.databinding.ActivityRegisterBinding;
import com.wdk.wanandroid.viewmodels.AccountViewModel;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 11:25 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 11:25 AM
 * @LastCheckBy: wdk
 */
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {

    private AccountViewModel accountViewModel;

    @Override
    protected void initViewModel() {
        accountViewModel = getActivityViewModel(AccountViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_register);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName("注册");
    }

    /**
     * 去注册
     */
    public void toRegister(View view) {

        String userName = getMBinding().etUserName.getText().toString();
        String password = getMBinding().etPassword.getText().toString();
        String rePassword = getMBinding().etRePassword.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            CustomerToast.showToast("请输入注册用户名");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            CustomerToast.showToast("请输入注册密码");
            return;
        }

        if (TextUtils.isEmpty(rePassword)) {
            CustomerToast.showToast("请输入确认密码");
            return;
        }

        accountViewModel.doRegister(userName, password, rePassword);


    }


}
