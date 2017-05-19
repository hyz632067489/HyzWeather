package com.hyz.hyzweather.util;

import android.text.TextUtils;

import com.hyz.hyzweather.db.City;
import com.hyz.hyzweather.db.County;
import com.hyz.hyzweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hyz on 2017/2/12.
 * powered by company
 */

public class Utility {
    /*
    解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for(int i = 0 ;i < allProvinces.length(); i++){
                    JSONObject proviceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(proviceObject.getString("name"));
                    province.setProvinceCode(proviceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String responce,int provinceId){

        if(!TextUtils.isEmpty(responce)){
            try {
                JSONArray allCities = new JSONArray(responce);
                for(int i = 0 ; i < allCities.length();i++){
                    JSONObject jsonObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(jsonObject.getString("name"));
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  false;
    }

    /*
    解析和处理服务器返回的县级数据
     */
    public static  boolean handleCountyResponse(String response,int cityId){

        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounty = new JSONArray(response);
                for(int i = 0 ; i<allCounty.length(); i++){
                    JSONObject countyObject = allCounty.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
