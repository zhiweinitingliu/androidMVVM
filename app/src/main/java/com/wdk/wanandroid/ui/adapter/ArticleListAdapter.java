package com.wdk.wanandroid.ui.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.wdk.baselibrary.basepage.BaseBindingAdapter;
import com.wdk.baselibrary.basepage.BaseViewHolder;
import com.wdk.wanandroid.R;
import com.wdk.wanandroid.data.bean.ArticleBean;
import com.wdk.wanandroid.databinding.ItemArticleBinding;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/18 5:22 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/18 5:22 PM
 * @LastCheckBy: wdk
 */
public class ArticleListAdapter extends BaseBindingAdapter<ItemArticleBinding, ArticleBean, ArticleListAdapter.MyViewHolder> {

    public ArticleListAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_article;
    }

    @Override
    public MyViewHolder createViewHolder(View view) {
        return new MyViewHolder(view);
    }

    @Override
    public void onBindItem(ItemArticleBinding binding, ArticleBean bean, int position) {
        binding.setArticleBean(bean);
    }

    static class MyViewHolder extends BaseViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
