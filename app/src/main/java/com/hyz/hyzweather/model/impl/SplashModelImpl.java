package com.hyz.hyzweather.model.impl;


import com.hyz.hyzweather.api.SplashService;
import com.hyz.hyzweather.data.SplashData;
import com.hyz.hyzweather.model.ISplashModel;
import com.hyz.hyzweather.net.NetManager;

import rx.Observable;

/**
 * Author: Othershe
 * Time:  2016/8/11 12:22
 */
public class SplashModelImpl implements ISplashModel {

    @Override
    public Observable<SplashData> getSplashPic() {
        SplashService service = NetManager.getInstance().create(SplashService.class);
        return service.getSplashPic();
    }
}
