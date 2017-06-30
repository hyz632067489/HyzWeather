package com.hyz.hyzweather.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hyz.hyzweather.presenter.BasePresenter;

/**
 * Created by hyz on 2017/6/26.
 */

public abstract class BaseMvpFragment<V, P extends BasePresenter<V>> extends BaseFragment {

    protected static final String SUB_TYPE = "subtype";

    protected P mPresenter;

    protected abstract P initPresenter();

    protected abstract void fetchData();

    protected boolean mIsViewInitiated;
    protected boolean mIsVisibleToUser;
    protected boolean mIsDataInitiated;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        initFetchData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);

        mIsViewInitiated = true;
        initFetchData();
    }

    private void initFetchData() {
        if (mIsVisibleToUser && mIsDataInitiated && !mIsDataInitiated) {
            fetchData();
            mIsDataInitiated = true;
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
