package com.wdk.wanandroid.api;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/16 8:49 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/16 8:49 AM
 * @LastCheckBy: wdk
 */
public interface HomeArticleService {

//    https://www.wanandroid.com/article/list/1/json

    @GET("article/list/{page}/json")
    Observable<ResponseBody> articleList(@Path("page") int page);


}
