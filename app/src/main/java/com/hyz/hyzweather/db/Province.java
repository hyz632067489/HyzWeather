package com.hyz.hyzweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by hyz on 2017/2/12.
 * 省数据
 */

public class Province extends DataSupport {
    private  int id;
    private String provinceName;    //省名字
    private int provinceCode;       //省代号

    public int getId() {
        return id;
    }

    public Province setId(int id) {
        this.id = id;
        return this;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public Province setProvinceName(String provinceName) {
        this.provinceName = provinceName;
        return this;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public Province setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
        return this;
    }
}
