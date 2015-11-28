package com.johnnyzhou.movieme.ui.base;

public interface MvpPresenter<V extends MvpView> {
    void bindView(V view);

    void unbindView();

    void onDestroy();
}
