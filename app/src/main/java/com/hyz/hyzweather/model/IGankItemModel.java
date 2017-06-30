package com.hyz.hyzweather.model;

import com.hyz.hyzweather.data.GankItemData;
import com.hyz.hyzweather.data.HttpResult;

import java.util.List;

import rx.Observable;

/**
 * Author: Othershe
 * Time: 2016/8/12 14:30
 */
public interface IGankItemModel {
    Observable<HttpResult<List<GankItemData>>> getGankItemData(String suburl);
}
