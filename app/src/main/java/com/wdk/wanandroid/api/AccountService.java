package com.wdk.wanandroid.api;

import com.wdk.wanandroid.data.bean.LoginResponseBean;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 9:56 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 9:56 AM
 * @LastCheckBy: wdk
 */
public interface AccountService {

//    https://www.wanandroid.com/user/login

    //登录
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginResponseBean> login(@Field("username") String username, @Field("password") String password);


    //注册
    @FormUrlEncoded
    @POST("user/register")
    Observable<ResponseBody> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);
}
