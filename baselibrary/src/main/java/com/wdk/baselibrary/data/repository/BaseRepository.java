package com.wdk.baselibrary.data.repository;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/17 4:18 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/17 4:18 PM
 * @LastCheckBy: wdk
 */
public class BaseRepository {

    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl("https://www.wanandroid.com/")
                .build();

    }


    /**
     * 从服务器获取数据
     */
    public void getDataFromService() {

    }

}
