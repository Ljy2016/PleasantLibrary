package com.azadljy.pleasantlibrary.adapter;

import androidx.annotation.Nullable;

import com.azadljy.pleasantlibrary.minterface.ISingleChoice;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public abstract class SingleChoiceAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> implements ISingleChoice<T> {

    int selectedPosition;


    public SingleChoiceAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public SingleChoiceAdapter(@Nullable List<T> data) {
        super(data);
    }

    public SingleChoiceAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void setSelectedPosition(int position) {
        notifyItemChanged(position);
        notifyItemChanged(selectedPosition);
        selectedPosition = position;

    }

    @Override
    public int getSelectedPosition() {
        return 0;
    }

    @Override
    public void setSelectedItem(T item) {

    }

    @Override
    public T getSelectedItem() {
        return null;
    }


}
