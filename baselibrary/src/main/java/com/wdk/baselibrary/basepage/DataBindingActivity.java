package com.wdk.baselibrary.basepage;

import android.os.Bundle;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.wdk.baselibrary.viewmodel.BaseViewModel;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/22 4:39 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/22 4:39 PM
 * @LastCheckBy: wdk
 */
public abstract class DataBindingActivity<T extends ViewDataBinding> extends AppCompatActivity {
    private ViewModelProvider mViewModelProvider;
    private T mBinding;

    /**
     * 1、 开始加载loading
     * 2、加载完成 loading
     */
    public MutableLiveData<Integer> loadingShowLiveData = new MutableLiveData<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        initDataBinding();
        initLayout();
        initLoadingListener();
    }

    protected abstract void initViewModel();

    private void initDataBinding() {
        DataBindingConfig dataBindingConfig = getDataBindingConfig();
        T binding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        binding.setLifecycleOwner(this);
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mBinding = binding;
    }

    protected abstract DataBindingConfig getDataBindingConfig();

    public T getMBinding() {
        return mBinding;
    }

    /**
     * 初始化布局，
     */
    public abstract void initLayout();

    public abstract void initLoadingListener();

    /**
     * 此方法给子类提供获取viewmodel的方法
     * 一个activity或者是fragment中可能会有多个viewmodel，所以将创建方法抛出，在view中进行创建
     *
     * @param clazz viewmodel的 .class
     * @param <T>   创建的viewmodel
     * @return
     */
    public <T extends BaseViewModel> T getActivityViewModel(@NonNull Class<T> clazz) {
        if (mViewModelProvider == null) {
            mViewModelProvider = new ViewModelProvider(this);
        }
        T t = mViewModelProvider.get(clazz);
        t.setLoadingShowLiveData(loadingShowLiveData);
        return t;
    }
}
