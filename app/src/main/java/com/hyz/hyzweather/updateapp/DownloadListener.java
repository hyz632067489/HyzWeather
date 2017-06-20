package com.hyz.hyzweather.updateapp;

/**
 * Created by hkc on 2017/6/20.
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
