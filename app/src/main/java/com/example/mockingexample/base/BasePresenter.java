package com.example.mockingexample.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView> {

    // View 的弱引用
    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    protected V getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public void showLoading() {
        getView().showLoading();
    }

    public void hideLoading() {
        getView().hideLoading();
    }

    public void showToast(String msg) {
        getView().showToast(msg);
    }
}


