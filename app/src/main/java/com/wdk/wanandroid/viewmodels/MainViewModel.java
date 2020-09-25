package com.wdk.wanandroid.viewmodels;

import com.wdk.baselibrary.viewmodel.BaseViewModel;
import com.wdk.wanandroid.data.bean.ArticleBean;
import com.wdk.baselibrary.network.NetMutableLiveData;
import com.wdk.baselibrary.network.RequestData;
import com.wdk.wanandroid.data.repository.HomeRepository;

import java.util.List;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/16 8:53 AM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/16 8:53 AM
 * @LastCheckBy: wdk
 */
public class MainViewModel extends BaseViewModel {

    private static final String TAG = "MainViewModel";

    private HomeRepository homeRepository;

    private NetMutableLiveData<List<ArticleBean.ArticleChildBean>> mArticleList;

    public MainViewModel() {
        homeRepository = new HomeRepository();
        //文章列表的
        mArticleList = new NetMutableLiveData<>();
    }

    int current;

    public void getArticleList() {
        RequestData requestData = getRequestData();
        requestData.addParams("page", current);
        homeRepository.getArticleList(requestData, mArticleList);
        current++;
    }

    public NetMutableLiveData<List<ArticleBean.ArticleChildBean>> getmArticleList() {
        return mArticleList;
    }
}
