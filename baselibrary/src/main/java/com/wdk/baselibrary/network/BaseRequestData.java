package com.wdk.baselibrary.network;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/18 2:25 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/18 2:25 PM
 * @LastCheckBy: wdk
 */
public class BaseRequestData {
    //loading使用到的liveData
    private MutableLiveData<Integer> loadingShowLiveData;
    //网络请求使用到的参数
    private Map<String, Object> mParams = new HashMap<>();

    public MutableLiveData<Integer> getLoadingShowLiveData() {
        return loadingShowLiveData;
    }

    public void setLoadingShowLiveData(MutableLiveData<Integer> loadingShowLiveData) {
        this.loadingShowLiveData = loadingShowLiveData;
    }

    public void setAllParams(Map<String, Object> params) {
        mParams.putAll(params);
    }

    public BaseRequestData addParams(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public Map<String, Object> getMParams() {
        return mParams;
    }

    public int getIntParams(String key) {
        if (mParams != null && mParams.containsKey(key)) {
            Object o = mParams.get(key);
            if (o instanceof Integer) {
                return (int) o;
            }
        }
        return -1;
    }

    public String getStrParams(String key) {
        if (mParams != null && mParams.containsKey(key)) {
            Object o = mParams.get(key);
            if (o instanceof String) {
                return (String) o;
            }
        }
        return "";
    }
}
