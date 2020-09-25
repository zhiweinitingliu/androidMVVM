package com.wdk.baselibrary.network.error;

/**
 * Description :服务器正常返回code过滤非成功
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/23 4:50 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/23 4:50 PM
 * @LastCheckBy: wdk
 */
public class ResponseException extends RuntimeException {

    private int errorCode;

    private String errorMsg;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
