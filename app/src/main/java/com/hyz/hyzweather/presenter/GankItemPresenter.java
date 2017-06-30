package com.hyz.hyzweather.presenter;


import com.hyz.hyzweather.data.GankItemData;
import com.hyz.hyzweather.model.IGankItemModel;
import com.hyz.hyzweather.model.impl.GankItemModelImpl;
import com.hyz.hyzweather.rx.RxManager;
import com.hyz.hyzweather.rx.RxSubscriber;
import com.hyz.hyzweather.view.view.GankItemView;

import java.util.List;

/**
 * Author: Othershe
 * Time: 2016/8/12 14:29
 */
public class GankItemPresenter extends BasePresenter<GankItemView> {
    private IGankItemModel mModel;

    public GankItemPresenter() {
        mModel = new GankItemModelImpl();
    }

    public void getGankItemData(String suburl) {
        mSubscription = RxManager.getInstance().doSubscribe1(mModel.getGankItemData(suburl), new RxSubscriber<List<GankItemData>>(true) {
            @Override
            protected void _onNext(List<GankItemData> gankItemData) {
                mView.onSuccess(gankItemData);
            }

            @Override
            protected void _onError() {
                mView.onError();
            }
        });
    }
}
