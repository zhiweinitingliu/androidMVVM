package com.wdk.baselibrary.network;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.MainThread;

import com.google.gson.Gson;
import com.wdk.baselibrary.data.bean.ServiceDataBean;
import com.wdk.baselibrary.network.error.ResponseException;
import com.wdk.baselibrary.network.jsonconverter.NetGsonConverterFactory;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/21 10:04 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/21 10:04 PM
 * @LastCheckBy: wdk
 */
public class NetWorkManager {
    private static final String TAG = "NetWorkManager";

    private NetWorkManager() {

    }

    public static NetWorkManager getInstance() {
        return InnerClass.netWorkManager;
    }

    private static class InnerClass {
        private static NetWorkManager netWorkManager = new NetWorkManager();
    }

    public <T> void getDataFromServer(Observable<T> observable, RequestData requestData, NetWorkCallBackListener<T> netWorkCallBackListener) {
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .onErrorReturn(new Function<Throwable, T>() {
                    @Override
                    public T apply(Throwable throwable) throws Throwable {
                        if (throwable instanceof ResponseException) {
                            ResponseException responseException = (ResponseException) throwable;
                            String errorMessage=responseException.getErrorMsg();
                            Handler mainThread=new Handler(Looper.getMainLooper());
                            mainThread.post(new Runnable() {
                                @Override
                                public void run() {
                                    netWorkCallBackListener.onFailed(errorMessage);
                                    if (requestData.getNetWorkFailedListener() != null) {
                                        requestData.getNetWorkFailedListener().onFailed(requestData.getWhat(), errorMessage);
                                    }
                                }
                            });

                        }
                        return null;
                    }
                })
                .subscribe(new NetWorkObserver<T>(requestData, netWorkCallBackListener));
    }

    public Retrofit getRetrofit() {
        ServiceInterceptor serviceInterceptor = new ServiceInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(serviceInterceptor);

        return new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(NetGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl("https://www.wanandroid.com/")
                .build();

    }

    public <T> T create(Class<T> service) {
        return getRetrofit().create(service);
    }


}
