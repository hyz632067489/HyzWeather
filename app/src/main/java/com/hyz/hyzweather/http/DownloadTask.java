package com.hyz.hyzweather.http;

import android.os.AsyncTask;
import android.os.Environment;

/**
 * Created by hyz on 2017/6/4.
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    public DownloadListener listener;

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }


    /**
     * 用于执行后台下载逻辑
     * @param params
     * @return
     */
    @Override
    protected Integer doInBackground(String... params) {

        long downloadedLength=0;    //  记录以下载得文件长度
        String downloadUrl = params[0];
        String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

        return null;
    }

    /**
     * 用于界面更新当前得下载进度
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /**
     * 用于通知最终下载结果
     * @param integer
     */
    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }
}
