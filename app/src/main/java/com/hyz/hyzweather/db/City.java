package com.hyz.hyzweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by hyz on 2017/2/12.
 * 市数据
 */

public class City extends DataSupport {

    private int id;
    private String CityName;
    private int cityCode;
    private int provinceId;

    public int getId() {
        return id;
    }

    public City setId(int id) {
        this.id = id;
        return this;
    }

    public String getCityName() {
        return CityName;
    }

    public City setCityName(String cityName) {
        CityName = cityName;
        return this;
    }

    public int getCityCode() {
        return cityCode;
    }

    public City setCityCode(int cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public City setProvinceId(int provinceId) {
        this.provinceId = provinceId;
        return this;
    }
}
