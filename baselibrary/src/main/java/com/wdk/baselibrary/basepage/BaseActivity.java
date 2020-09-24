package com.wdk.baselibrary.basepage;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.wdk.baselibrary.R;
import com.wdk.baselibrary.utils.ViewUtil;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/17 2:23 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/17 2:23 PM
 * @LastCheckBy: wdk
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends DataBindingActivity<T> {

    private View titleView;

    public View getTitleView() {
        return titleView;
    }

    @Override
    public void initLayout() {
        titleView = LayoutInflater.from(this).inflate(R.layout.item_base_title, null, false);
        ViewUtil.textViewBlod(titleView.findViewById(R.id.tvTitle));
        titleView.findViewById(R.id.rlBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initLoadingListener() {
        loadingShowLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case 1:
                        Toast.makeText(BaseActivity.this, "开始", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        Toast.makeText(BaseActivity.this, "-----end-----", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    /**
     * 设置标题
     */
    public void setTitleName(String titleName) {
        if (getMBinding() != null && getMBinding().getRoot() instanceof LinearLayout && titleView != null && titleView.getParent() == null) {
            ((LinearLayout) getMBinding().getRoot()).addView(titleView, 0);
        }
        if (titleView != null) {
            ((TextView) titleView.findViewById(R.id.tvTitle)).setText(titleName);
        }
    }

    /**
     * 设置右侧标题
     *
     * @param rightName
     */
    public void setRightTitle(String rightName) {
        if (titleView != null) {
            titleView.findViewById(R.id.tvRight).setVisibility(View.VISIBLE);
            ((TextView) titleView.findViewById(R.id.tvRight)).setText(rightName);

        }
    }

    public void hideCallBack() {
        if (titleView != null) {
            titleView.findViewById(R.id.rlBack).setVisibility(View.GONE);
        }
    }


    public void showLoading() {

    }


    public void hideLoading() {

    }
}
