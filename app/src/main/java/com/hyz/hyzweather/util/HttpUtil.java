package com.hyz.hyzweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by hyz on 2017/2/12.
 * powered by company
 */

public class HttpUtil {

    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);

    }
}
