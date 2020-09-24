package com.wdk.baselibrary.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wdk.baselibrary.network.RequestData;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/17 2:21 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/17 2:21 PM
 * @LastCheckBy: wdk
 */
public class BaseViewModel extends ViewModel {

    public MutableLiveData<Integer> loadingShowLiveData;

    public MutableLiveData<Integer> getLoadingShowLiveData() {
        return loadingShowLiveData;
    }

    public void setLoadingShowLiveData(MutableLiveData<Integer> loadingShowLiveData) {
        this.loadingShowLiveData = loadingShowLiveData;
    }

    /**
     * 获取到网络请求使用到的封装类
     *
     * @return requestData
     */
    public RequestData getRequestData() {
        RequestData requestData = new RequestData();
        requestData.setLoadingShowLiveData(loadingShowLiveData);
        return requestData;
    }


}
