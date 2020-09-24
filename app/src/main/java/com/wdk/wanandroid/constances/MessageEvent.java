package com.wdk.wanandroid.constances;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 6:56 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 6:56 PM
 * @LastCheckBy: wdk
 */
public class MessageEvent {
    public static final String login_success = "login_success";


    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
