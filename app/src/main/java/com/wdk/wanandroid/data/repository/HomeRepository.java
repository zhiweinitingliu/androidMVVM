package com.wdk.wanandroid.data.repository;

import com.wdk.wanandroid.api.HomeArticleService;
import com.wdk.wanandroid.data.bean.ArticleBean;
import com.wdk.baselibrary.network.NetMutableLiveData;
import com.wdk.baselibrary.network.NetWorkCallBackListener;
import com.wdk.baselibrary.network.NetWorkManager;
import com.wdk.baselibrary.network.RequestData;
import com.wdk.baselibrary.data.repository.BaseRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;

/**
 * Description : 获取数据的仓库，用于给viewmodel提供数据
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/16 10:05 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/16 10:05 PM
 * @LastCheckBy: wdk
 */
public class HomeRepository extends BaseRepository {

    private static final String TAG = "HomeRepository";

    /**
     * 获取文章列表
     *
     * @param requestData        请求的参数
     * @param netMutableLiveData 请求的
     */
    public void getArticleList(RequestData requestData, NetMutableLiveData<List<ArticleBean>, String> netMutableLiveData) {
        HomeArticleService service = NetWorkManager.getInstance().create(HomeArticleService.class);
        Observable<ResponseBody> observable = service.articleList(requestData.getIntParams("page"));
        NetWorkManager.getInstance().getDataFromServer(observable, requestData, new NetWorkCallBackListener<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    netMutableLiveData.setNetWorkResponse(string);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailed(String error) {

            }

        });
    }

}
