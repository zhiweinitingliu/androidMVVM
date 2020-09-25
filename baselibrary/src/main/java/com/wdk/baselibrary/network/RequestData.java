package com.wdk.baselibrary.network;

/**
 * Description : 请求携带的参数类
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/18 10:39 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/18 10:39 AM
 * @LastCheckBy: wdk
 */
public class RequestData extends BaseRequestData {

    private int what;

    public void requestStart() {
        if (getLoadingShowLiveData() != null) {
            getLoadingShowLiveData().postValue(1);
        }
    }

    public void requestComplete() {
        if (getLoadingShowLiveData() != null) {
            getLoadingShowLiveData().postValue(2);
        }
    }

    private NetWorkFailedListener netWorkFailedListener;

    public void setNetWorkFailedListener(int what, NetWorkFailedListener netWorkFailedListener) {
        this.netWorkFailedListener = netWorkFailedListener;
        this.what = what;
    }

    public NetWorkFailedListener getNetWorkFailedListener() {
        return netWorkFailedListener;
    }

    public int getWhat() {
        return what;
    }
}
