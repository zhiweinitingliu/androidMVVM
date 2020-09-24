package com.wdk.baselibrary.network;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 9:53 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 9:53 PM
 * @LastCheckBy: wdk
 */
public class ServiceInterceptor implements Interceptor {
    private static final String TAG = "ServiceInterceptor";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());
        Log.e(TAG, "intercept: " + (response.body() == null ? "" : response.body().toString()));

        return response;
    }
}
