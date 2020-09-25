package com.wdk.wanandroid.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.wdk.baselibrary.network.NetMutableLiveData;
import com.wdk.baselibrary.network.NetWorkFailedListener;
import com.wdk.baselibrary.network.RequestData;
import com.wdk.baselibrary.utils.CustomerToast;
import com.wdk.baselibrary.viewmodel.BaseViewModel;
import com.wdk.wanandroid.constances.MessageEvent;
import com.wdk.wanandroid.data.bean.LoginResponseBean;
import com.wdk.wanandroid.data.bean.RegisterResponseBean;
import com.wdk.wanandroid.data.repository.LoginRepository;

import org.greenrobot.eventbus.EventBus;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 10:05 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 10:05 AM
 * @LastCheckBy: wdk
 */
public class AccountViewModel extends BaseViewModel implements NetWorkFailedListener {
    private static final String TAG = "LoginViewModel";

    private LoginRepository loginRepository;
    private NetMutableLiveData<LoginResponseBean> loginResponseLiveData;
    private NetMutableLiveData<RegisterResponseBean> registerResponseLiveData;
    private MutableLiveData<Boolean> loginResultLiveData;
    private MutableLiveData<Boolean> registerResultLiveData;

    public MutableLiveData<Boolean> getLoginResultLiveData() {
        return loginResultLiveData;
    }

    public MutableLiveData<Boolean> getRegisterResultLiveData() {
        return registerResultLiveData;
    }

    public AccountViewModel() {
        loginRepository = new LoginRepository();
        loginResultLiveData = new MutableLiveData<>();
        loginResponseLiveData = new NetMutableLiveData<>();
        loginResponseLiveData.observeForever(new Observer<LoginResponseBean>() {
            @Override
            public void onChanged(LoginResponseBean loginResponseBean) {
                EventBus.getDefault().post(new MessageEvent(MessageEvent.login_success));
                loginResultLiveData.postValue(true);
            }
        });

        registerResultLiveData = new MutableLiveData<>();
        registerResponseLiveData = new NetMutableLiveData<>();
        registerResponseLiveData.observeForever(new Observer<RegisterResponseBean>() {
            @Override
            public void onChanged(RegisterResponseBean registerResponseBean) {
                EventBus.getDefault().post(new MessageEvent(MessageEvent.login_success));
                registerResultLiveData.postValue(true);
            }
        });
    }


    public void doLogin(String username, String password) {
        loginRequestLiveData.postValue(1);
        RequestData requestData = getRequestData();
        requestData.setNetWorkFailedListener(101,this);
        requestData.addParams("username", username)
                .addParams("password", password);
        loginRepository.doLogin(requestData, loginResponseLiveData);
    }

    /**
     * 注册
     */
    public void doRegister(String userName, String password, String rePassword) {
        registerRequestLiveData.postValue(1);
        RequestData requestData = getRequestData();
        requestData.setNetWorkFailedListener(102, this);
        requestData.addParams("username", userName)
                .addParams("password", password)
                .addParams("repassword", rePassword);
        loginRepository.doRegister(requestData, registerResponseLiveData);
    }

    private MutableLiveData<Integer> loginRequestLiveData=new MutableLiveData<>();
    private MutableLiveData<Integer> registerRequestLiveData=new MutableLiveData<>();

    public MutableLiveData<Integer> getLoginRequestLiveData() {
        return loginRequestLiveData;
    }

    public MutableLiveData<Integer> getRegisterRequestLiveData() {
        return registerRequestLiveData;
    }

    /**
     * 接口失败的回调
     *
     * @param what  接口请求的标记 自己定义
     * @param error 失败信息
     */
    @Override
    public void onFailed(int what, String error) {

        switch (what) {
            //登录失败
            case 101:
                loginRequestLiveData.postValue(2);
                break;
            //注册
            case 102:
                registerRequestLiveData.postValue(2);
                break;
        }
        CustomerToast.showToast(error);
    }


}
