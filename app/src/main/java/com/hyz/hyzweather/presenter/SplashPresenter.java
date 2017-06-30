package com.hyz.hyzweather.presenter;


import com.hyz.hyzweather.data.SplashData;
import com.hyz.hyzweather.model.ISplashModel;
import com.hyz.hyzweather.model.impl.SplashModelImpl;
import com.hyz.hyzweather.rx.RxManager;
import com.hyz.hyzweather.rx.RxSubscriber;
import com.hyz.hyzweather.view.view.SplashView;

/**
 * Author: Othershe
 * Time:  2016/8/11 11:26
 */
public class SplashPresenter extends BasePresenter<SplashView> {
    private ISplashModel mModel;

    public SplashPresenter() {
        mModel = new SplashModelImpl();
    }

    public void getSplashPic() {
        mSubscription = RxManager.getInstance().doSubscribe(mModel.getSplashPic(), new RxSubscriber<SplashData>(false) {
            @Override
            protected void _onNext(SplashData data) {
                mView.onSuccess(data);
            }

            @Override
            protected void _onError() {
                mView.onError();
            }
        });
    }
}
