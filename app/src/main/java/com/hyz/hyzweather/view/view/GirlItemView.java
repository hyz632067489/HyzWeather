package com.hyz.hyzweather.view.view;

import com.hyz.hyzweather.data.GirlItemData;

import java.util.List;

/**
 * Author: Othershe
 * Time: 2016/8/16 12:10
 */
public interface GirlItemView extends IBaseView{
    void onSuccess(List<GirlItemData> data);
}
