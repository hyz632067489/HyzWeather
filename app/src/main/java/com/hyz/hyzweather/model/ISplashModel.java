package com.hyz.hyzweather.model;

import com.hyz.hyzweather.data.SplashData;

import rx.Observable;

/**
 * Author: Othershe
 * Time:  2016/8/11 15:17
 */
public interface ISplashModel {
    Observable<SplashData> getSplashPic();
}
