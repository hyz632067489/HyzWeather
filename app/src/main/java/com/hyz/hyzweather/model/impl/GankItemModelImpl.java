package com.hyz.hyzweather.model.impl;


import com.hyz.hyzweather.api.GankItemService;
import com.hyz.hyzweather.data.GankItemData;
import com.hyz.hyzweather.data.HttpResult;
import com.hyz.hyzweather.model.IGankItemModel;
import com.hyz.hyzweather.net.NetManager;

import java.util.List;

import rx.Observable;

/**
 * Author: Othershe
 * Time: 2016/8/12 14:30
 */
public class GankItemModelImpl implements IGankItemModel {

    @Override
    public Observable<HttpResult<List<GankItemData>>> getGankItemData(String suburl) {
        GankItemService service = NetManager.getInstance().create(GankItemService.class);
        return service.getGankItemData(suburl);
    }
}
