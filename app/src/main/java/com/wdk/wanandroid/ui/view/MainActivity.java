package com.wdk.wanandroid.ui.view;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wdk.baselibrary.basepage.BaseActivity;
import com.wdk.baselibrary.basepage.DataBindingConfig;
import com.wdk.baselibrary.utils.SharedPreferencesUtil;
import com.wdk.wanandroid.R;
import com.wdk.wanandroid.constances.Constants;
import com.wdk.wanandroid.constances.MessageEvent;
import com.wdk.wanandroid.data.bean.ArticleBean;
import com.wdk.wanandroid.ui.adapter.ArticleListAdapter;
import com.wdk.wanandroid.utils.UserInfoUtil;
import com.wdk.wanandroid.viewmodels.MainViewModel;
import com.wdk.wanandroid.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private ArticleListAdapter articleListAdapter;

    @Override
    protected void initViewModel() {
        mainViewModel = getActivityViewModel(MainViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig dataBindingConfig = new DataBindingConfig(R.layout.activity_main);
        dataBindingConfig.addBindingParam(BR.mainViewModel, mainViewModel);
        return dataBindingConfig;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setTitleName("首页");
        hideCallBack();
        if (UserInfoUtil.getInstance().getUserId() <= 0) {
            setRightTitle("登录");
        } else {
            setRightTitle("退出");
        }

        recyclerView = getMBinding().recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        articleListAdapter = new ArticleListAdapter(this);
        recyclerView.setAdapter(articleListAdapter);

        initListener();
        mainViewModel.getArticleList();
    }

    private static final String TAG = "MainActivity";

    private void initListener() {
        mainViewModel.getmArticleList().observe(this, new Observer<List<ArticleBean>>() {
            @Override
            public void onChanged(List<ArticleBean> articleBeans) {
                Log.e(TAG, "article_onChanged: " + articleBeans.size());
                articleListAdapter.getItems().addAll(articleBeans);
                articleListAdapter.notifyDataSetChanged();
            }
        });

        if (getTitleView() != null) {
            getTitleView().findViewById(R.id.rlTitle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (UserInfoUtil.getInstance().getUserId() <= 0) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        SharedPreferencesUtil.getInstance().putStringValue(Constants.user_info_json, "");
                        setRightTitle("登录");
                    }
                }
            });
        }


    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe()
    public void onMessageEvent(MessageEvent event) {
        switch (event.getMessage()) {
            //登录成功
            case MessageEvent.login_success:
                setRightTitle("退出");
                break;
        }

    }
}