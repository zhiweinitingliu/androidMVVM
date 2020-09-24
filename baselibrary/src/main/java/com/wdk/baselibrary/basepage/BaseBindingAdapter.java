package com.wdk.baselibrary.basepage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.wdk.baselibrary.data.bean.BaseBean;

import java.util.List;

/**
 * Description :
 *
 * @Author : wdk
 * @CreateTiem : 2020/9/19 3:46 PM
 * @LaseModify(最终修改人): wdk
 * @LastModityTime(最终修改时间): 2020/9/19 3:46 PM
 * @LastCheckBy: wdk
 */
public abstract class BaseBindingAdapter<VB extends ViewDataBinding, M extends BaseBean, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    public Context context;
    protected ObservableArrayList<M> items;

    public BaseBindingAdapter(Context context) {
        this.context = context;
        items = new ObservableArrayList<>();
    }

    public abstract int getLayoutId();

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VB binding = DataBindingUtil.inflate(LayoutInflater.from(context), getLayoutId(), parent, false);
        return createViewHolder(binding.getRoot());
    }

    public abstract VH createViewHolder(View view);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        M bean = items.get(position);
        VB binding = DataBindingUtil.getBinding(holder.itemView);
        onBindItem(binding, bean, position);
    }

    public abstract void onBindItem(VB binding, M bean, int position);

    public ObservableArrayList<M> getItems() {
        return items;
    }
}
