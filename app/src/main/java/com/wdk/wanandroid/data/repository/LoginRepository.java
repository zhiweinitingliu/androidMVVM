package com.wdk.wanandroid.data.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.wdk.baselibrary.data.repository.BaseRepository;
import com.wdk.baselibrary.network.NetMutableLiveData;
import com.wdk.baselibrary.network.NetWorkCallBackListener;
import com.wdk.baselibrary.network.NetWorkManager;
import com.wdk.baselibrary.network.RequestData;
import com.wdk.baselibrary.utils.SharedPreferencesUtil;
import com.wdk.wanandroid.api.AccountService;
import com.wdk.wanandroid.constances.Constants;
import com.wdk.wanandroid.data.bean.LoginResponseBean;
import com.wdk.wanandroid.data.bean.RegisterResponseBean;

import io.reactivex.rxjava3.core.Observable;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 10:07 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 10:07 AM
 * @LastCheckBy: wdk
 */
public class LoginRepository extends BaseRepository {
    private static final String TAG = "LoginRepository";

    //登录
    public void doLogin(RequestData requestData, NetMutableLiveData<LoginResponseBean> netMutableLiveData) {
        AccountService accountService = NetWorkManager.getInstance().create(AccountService.class);
        Observable<LoginResponseBean> login = accountService.login(requestData.getStrParams("username"), requestData.getStrParams("password"));
        NetWorkManager.getInstance().getDataFromServer(login, requestData, new NetWorkCallBackListener<LoginResponseBean>() {
            @Override
            public void onSuccess(LoginResponseBean loginResponseBean) {
                try {
                    SharedPreferencesUtil.getInstance().putStringValue(Constants.user_info_json, new Gson().toJson(loginResponseBean));
                    netMutableLiveData.postValue(loginResponseBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(String error) {
                Log.e(TAG, "onFailed: " + error);

            }
        });
    }

    public void doRegister(RequestData requestData, NetMutableLiveData<RegisterResponseBean> netMutableLiveData) {
        AccountService accountService = NetWorkManager.getInstance().create(AccountService.class);
        Observable<RegisterResponseBean> register = accountService.register(requestData.getStrParams("username"), requestData.getStrParams("password"), requestData.getStrParams("repassword"));
        NetWorkManager.getInstance().getDataFromServer(register, requestData, new NetWorkCallBackListener<RegisterResponseBean>() {
            @Override
            public void onSuccess(RegisterResponseBean registerResponseBean) {
                try {
                    SharedPreferencesUtil.getInstance().putStringValue(Constants.user_info_json, new Gson().toJson(registerResponseBean));
                    netMutableLiveData.postValue(registerResponseBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(String error) {
                Log.e(TAG, "onFailed: " + error);

            }
        });
    }
}
