package com.wdk.baselibrary.network;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/25 9:44 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/25 9:44 AM
 * @LastCheckBy: wdk
 */
public interface NetWorkFailedListener {

    void onFailed(int what, String error);
}
