package com.hyz.hyzweather;


import android.content.Context;


import com.hyz.hyzweather.util.SPUtil;

import org.litepal.LitePalApplication;


/**
 * Created by hyz on 2017/2/12.
 * powered by company
 */

public class WeatherAppLication extends LitePalApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();


        mContext = getApplicationContext();

        initRealm();
        SPUtil.init(mContext, "niceread");
    }

    public static Context getContext() {
        return mContext;
    }

    private void initRealm() {
//        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
//        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
