package com.wdk.wanandroid.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.wdk.baselibrary.viewmodel.BaseViewModel;
import com.wdk.wanandroid.data.bean.ArticleBean;
import com.wdk.wanandroid.data.bean.User;
import com.wdk.baselibrary.network.NetMutableLiveData;
import com.wdk.baselibrary.network.RequestData;
import com.wdk.wanandroid.data.repository.HomeRepository;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;

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

    private NetMutableLiveData<List<ArticleBean>, String> mArticleList;

    public MainViewModel() {
        homeRepository = new HomeRepository();

        //文章列表的监听
        mArticleList = new NetMutableLiveData<>();
        mArticleList.setSwitchDataListener(new NetMutableLiveData.SwitchDataListener<List<ArticleBean>, String>() {
            @Override
            public List<ArticleBean> getResultData(String s) {
                Log.e(TAG, "getResultData: " + s);
                Logger.addLogAdapter(new AndroidLogAdapter());
                Logger.json(s);
                try {

                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject dataObj = jsonObject.getJSONObject("data");
                    String datas = dataObj.getString("datas");
                    return JSON.parseArray(datas, ArticleBean.class);
                } catch (Exception e) {

                }

                return null;
            }
        });
    }

    int current;

    public void getArticleList() {
        RequestData requestData = getRequestData();
        requestData.addParams("page", current);
        homeRepository.getArticleList(requestData, mArticleList);
        current++;
    }

    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public void getUserInfo() {
        Random random = new Random();
        int i = random.nextInt(100);
        Log.e(TAG, "getUserInfo: " + i);

        User user = new User();
        user.setName("my name is wangdukang  " + i);

        userMutableLiveData.postValue(user);
    }


    public NetMutableLiveData<List<ArticleBean>, String> getmArticleList() {
        return mArticleList;
    }
}
