package com.wdk.baselibrary.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

/**
 * Description :网络请求使用的liveData，返回数据需要用户自行处理的
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/18 11:36 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/18 11:36 AM
 * @LastCheckBy: wdk
 */
public class NetMutableLiveData<T, V> extends MutableLiveData<T> {

    private static final String TAG = "NetMutableLiveData";

    public void setNetWorkResponse(V v) {
        if (switchDataListener != null) {
            postValue(switchDataListener.getResultData(v));
        }
    }

    private SwitchDataListener<T, V> switchDataListener;

    public SwitchDataListener<T, V> getSwitchDataListener() {
        return switchDataListener;
    }

    public void setSwitchDataListener(SwitchDataListener<T, V> switchDataListener) {
        this.switchDataListener = switchDataListener;
    }

    public interface SwitchDataListener<T, V> {
        T getResultData(V v);
    }
}
