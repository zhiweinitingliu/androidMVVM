package com.wdk.baselibrary.network;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/21 10:36 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/21 10:36 PM
 * @LastCheckBy: wdk
 */
public interface NetWorkCallBackListener<T> {

    void onSuccess(T t);

    void onFailed(String error);

}
