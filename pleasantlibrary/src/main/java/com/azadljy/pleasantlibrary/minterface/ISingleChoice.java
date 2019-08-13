package com.azadljy.pleasantlibrary.minterface;

public interface ISingleChoice<T> {

    void setSelectedPosition(int position);

    int getSelectedPosition();

    void setSelectedItem(T item);

    T getSelectedItem();

}
