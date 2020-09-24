package com.wdk.wanandroid.viewmodels;

import androidx.lifecycle.MutableLiveData;

import com.wdk.baselibrary.network.NetMutableLiveData;
import com.wdk.baselibrary.network.RequestData;
import com.wdk.baselibrary.viewmodel.BaseViewModel;
import com.wdk.wanandroid.constances.MessageEvent;
import com.wdk.wanandroid.data.bean.LoginResponseBean;
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
public class AccountViewModel extends BaseViewModel {
    private static final String TAG = "LoginViewModel";

    private LoginRepository loginRepository;
    private NetMutableLiveData<LoginResponseBean, LoginResponseBean> loginResponseLiveData;
    private NetMutableLiveData<LoginResponseBean, String> registerResponseLiveData;
    private MutableLiveData<Boolean> loginResultLiveData;
    private MutableLiveData<Boolean> registerResultLiveData;

    public MutableLiveData<Boolean> getLoginResultLiveData() {
        return loginResultLiveData;
    }

    public AccountViewModel() {
        loginRepository = new LoginRepository();
        loginResultLiveData = new MutableLiveData<>();
        loginResponseLiveData = new NetMutableLiveData<>();

        loginResponseLiveData.setSwitchDataListener(new NetMutableLiveData.SwitchDataListener<LoginResponseBean, LoginResponseBean>() {
            @Override
            public LoginResponseBean getResultData(LoginResponseBean loginResponseBean) {
                EventBus.getDefault().post(new MessageEvent(MessageEvent.login_success));
                loginResultLiveData.postValue(true);
                return loginResponseBean;
            }
        });

        registerResultLiveData = new MutableLiveData<>();
        registerResponseLiveData = new NetMutableLiveData<>();
        registerResponseLiveData.setSwitchDataListener(new NetMutableLiveData.SwitchDataListener<LoginResponseBean, String>() {
            @Override
            public LoginResponseBean getResultData(String s) {
                registerResultLiveData.postValue(true);
                return null;
            }
        });
    }


    public void doLogin(String username, String password) {
        RequestData requestData = getRequestData();
        requestData.addParams("username", username)
                .addParams("password", password);
        loginRepository.doLogin(requestData, loginResponseLiveData);
    }

    /**
     * 注册
     */
    public void doRegister(String userName, String password, String rePassword) {
        RequestData requestData = getRequestData();
        requestData.addParams("username", userName)
                .addParams("password", password)
                .addParams("repassword", password);
        loginRepository.doRegister(requestData, registerResponseLiveData);
    }
}
