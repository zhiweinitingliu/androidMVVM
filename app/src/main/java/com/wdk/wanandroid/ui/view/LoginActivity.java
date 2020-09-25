package com.wdk.wanandroid.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.wdk.baselibrary.basepage.BaseActivity;
import com.wdk.baselibrary.basepage.DataBindingConfig;
import com.wdk.wanandroid.BR;
import com.wdk.wanandroid.R;
import com.wdk.wanandroid.databinding.ActivityLoginBinding;
import com.wdk.wanandroid.viewmodels.AccountViewModel;


/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 10:24 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 10:24 AM
 * @LastCheckBy: wdk
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    private static final String TAG = "LoginActivity";

    private AccountViewModel accountViewModel;

    @Override
    protected void initViewModel() {
        accountViewModel = getActivityViewModel(AccountViewModel.class);
        accountViewModel.getLoginResultLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.e(TAG, "onChanged: " + aBoolean);
                //登录成功
                if (aBoolean) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        accountViewModel.getLoginRequestLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    getMBinding().btnLogin.setEnabled(false);
                    getMBinding().btnLogin.setText("登录中...");
                } else {
                    getMBinding().btnLogin.setText("登录");
                    getMBinding().btnLogin.setEnabled(true);
                }
            }
        });
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig dataBindingConfig = new DataBindingConfig(R.layout.activity_login);
        dataBindingConfig.addBindingParam(BR.accountViewModel, accountViewModel);
        return dataBindingConfig;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleName("登录");
    }

    /**
     * 登录
     */
    public void doLogin(View view) {
        String userName = getMBinding().etUserName.getText().toString();
        String password = getMBinding().etPassword.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "请输入登录名", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入登录密码", Toast.LENGTH_SHORT).show();
            return;
        }

        accountViewModel.doLogin(userName, password);
    }

    /**
     * 去注册页面
     */
    public void goRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
