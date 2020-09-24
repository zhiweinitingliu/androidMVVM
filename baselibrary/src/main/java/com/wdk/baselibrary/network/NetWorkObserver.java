package com.wdk.baselibrary.network;

import com.wdk.baselibrary.data.bean.ServiceDataBean;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/21 10:16 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/21 10:16 PM
 * @LastCheckBy: wdk
 */
public class NetWorkObserver<T> implements Observer<T> {

    private RequestData requestData;
    private NetWorkCallBackListener<T> netWorkCallBackListener;

    public NetWorkObserver(RequestData requestData, NetWorkCallBackListener<T> netWorkCallBackListener) {
        this.requestData = requestData;
        this.netWorkCallBackListener = netWorkCallBackListener;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (requestData != null) {
            requestData.requestStart();
        }
    }

    @Override
    public void onNext(@NonNull T t) {
        netWorkCallBackListener.onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        netWorkCallBackListener.onFailed(e.toString());
    }

    @Override
    public void onComplete() {
        requestData.requestComplete();
    }
}
