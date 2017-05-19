package com.hyz.hyzweather.view;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hyz on 2017/3/7.
 * powered by company
 */

public class BaseActivity extends FragmentActivity {

    protected Unbinder mUnbinder;

    protected Activity mActivity;
    protected Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivity = this;
        mContext = getApplication();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //绑定
        mUnbinder = ButterKnife.bind(this);
    }

    public void toast(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示加载dialog
     *
     * @param message messageIsChanged message是否已经改变
     */



    /**
     * 隐藏
     */


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mUnbinder.unbind();
    }

}
