package com.duanlian.nicecodes.base;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * Fragment的基类
 */

public abstract class BaseFragment extends Fragment implements IBaseView {
    private BaseActivity mActivity;
    private View mLayoutView;

    /**
     * 初始化布局
     */
    public abstract int getLayoutRes();


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        if (mLayoutView != null) {
            ViewGroup parent = (ViewGroup) mLayoutView.getParent();
            if (parent != null) {
                parent.removeView(mLayoutView);
            }
        } else {
            mLayoutView = getCreateView(inflater, container);
            initView(mLayoutView);     //初始化布局
        }

        return mLayoutView;
    }
    /**
     * 获取Fragment布局文件的View
     */
    private View getCreateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getLayoutRes(), container, false);
    }
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化view
     */
    public abstract void initView(View mLayoutView);

    /**
     * 初始化数据
     */
    public void initData() {
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showProgress(flag, message);
            }
        }
    }

    @Override
    public void showProgress(String message) {
        showProgress(true, message);
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void showProgress(boolean flag) {
        showProgress(flag, "");
    }

    @Override
    public void hideProgress() {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.hideProgress();
            }
        }
    }

    @Override
    public void showToast(int resId) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showToast(resId);
            }
        }

    }

    @Override
    public void showToast(String msg) {
        if (getStatus()) {
            BaseActivity activity = getBaseActivity();
            if (activity != null) {
                activity.showToast(msg);
            }
        }
    }

    @Override
    public void close() {

    }

    /**
     * 获取当前Fragment状态
     *
     * @return true为正常 false为未加载或正在删除
     */
    private boolean getStatus() {
        return (isAdded() && !isRemoving());
    }

    /**
     * 获取Activity
     */
    public BaseActivity getBaseActivity() {
        if (mActivity == null) {
            mActivity = (BaseActivity) getActivity();
        }
        return mActivity;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
