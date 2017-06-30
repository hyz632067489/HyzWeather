package com.hyz.hyzweather.api;

import com.hyz.hyzweather.constant.Api;
import com.hyz.hyzweather.data.GankItemData;
import com.hyz.hyzweather.data.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author: Othershe
 * Time: 2016/8/12 16:50
 */
public interface GankItemService {
    String BASE_URL = Api.URL_GET_GANK;

    @GET("{suburl}")
    Observable<HttpResult<List<GankItemData>>> getGankItemData(@Path("suburl") String suburl);
}
