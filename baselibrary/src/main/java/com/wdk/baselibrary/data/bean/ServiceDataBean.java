package com.wdk.baselibrary.data.bean;

import java.io.Serializable;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 9:18 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 9:18 PM
 * @LastCheckBy: wdk
 */
public class ServiceDataBean<T> implements Serializable {

    private int errorCode;

    private T data;

    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
