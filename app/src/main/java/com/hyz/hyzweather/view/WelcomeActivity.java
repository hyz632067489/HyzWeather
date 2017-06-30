package com.hyz.hyzweather.view;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import com.hyz.hyzweather.R;
import com.hyz.hyzweather.data.SplashData;
import com.hyz.hyzweather.presenter.SplashPresenter;
import com.hyz.hyzweather.util.DateUtil;
import com.hyz.hyzweather.util.ImageLoader;
import com.hyz.hyzweather.util.NetUtil;
import com.hyz.hyzweather.util.SPUtil;
import com.hyz.hyzweather.view.activity.BaseMvpActivity;
import com.hyz.hyzweather.view.fragment.SetFragment;
import com.hyz.hyzweather.view.view.SplashView;

import butterknife.BindView;

/**
 * Created by hyz on 2017/5/31.
 */

public  class WelcomeActivity extends BaseMvpActivity<SplashView, SplashPresenter> implements SplashView {
    private String mTimeLine;

    @BindView(R.id.splash_iv)
    ImageView mSplashIv;

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected void fetchData() {
        if (!DateUtil.formatDate().equals(mTimeLine)) {
            mPresenter.getSplashPic();
        }
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        if (!(Boolean) SPUtil.get(SetFragment.SPLASH, false) || !NetUtil.isConnected(mContext)) {
            ImageLoader.load(mContext, R.drawable.original_splash, mSplashIv);
        } else {
            ImageLoader.load(mContext, (String) SPUtil.get("splash_url", ""), mSplashIv);
        }

        startDelay();
    }

    @Override
    protected void initData() {
        mTimeLine = (String) SPUtil.get("splash_time", "");
    }

    @Override
    public void onSuccess(SplashData data) {
        SPUtil.save("splash_time", DateUtil.formatDate());
        SPUtil.save("splash_url", data.getUrl());
    }

    @Override
    public void onError() {

    }

    private void startDelay() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
