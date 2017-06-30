package com.hyz.hyzweather.view.view;


import com.hyz.hyzweather.data.GankItemData;

import java.util.List;

/**
 * Author: Othershe
 * Time: 2016/8/12 14:31
 */
public interface GankItemView extends IBaseView {
    void onSuccess(List<GankItemData> data);
}
