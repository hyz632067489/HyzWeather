package com.hyz.hyzweather.presenter;

import rx.Subscription;

/**
 * Created by hyz on 2017/6/27.
 */

public class BasePresenter<V> {
    public V mView;
    protected Subscription mSubscription;

    public void attach(V view) {
        mView = view;
    }

    public void detach() {
        mView = null;
        if (mSubscription != null) {

            mSubscription.unsubscribe();
        }
    }
}
