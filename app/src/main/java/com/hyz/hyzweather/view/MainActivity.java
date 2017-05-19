package com.hyz.hyzweather.view;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;

import com.hyz.hyzweather.R;
import com.hyz.hyzweather.bean.CommonModel;
import com.hyz.hyzweather.sliding.AbOnItemClickListener;
import com.hyz.hyzweather.sliding.AbSlidingPlayView;
import com.hyz.hyzweather.util.GlideImageLoader;
import com.hyz.hyzweather.util.ImageLoaderUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity {


    @BindView(R.id.banner)
    Banner mBanner;

    int[] images={R.mipmap.ic_launcher,R.mipmap.default_image,R.mipmap.grid_camera};
String [] titles={"1","2","3"};
    List<CommonModel> imageUrls = new ArrayList<>();

    CommonModel model;

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

getDatas();

        //设置图片加载器
        mBanner.setImageLoader(new ImageLoaderUtil());
        //设置图片集合
        mBanner.setImages(imageUrls);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

//设置banner样式
//        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
//        //设置图片加载器
//        mBanner.setImageLoader(new ImageLoaderUtil());
//        //设置图片集合
//        mBanner.setImages(imageUrls);
//        //设置banner动画效果
//        mBanner.setBannerAnimation(Transformer.DepthPage);
//        //设置标题集合（当banner样式有显示title时）
//        mBanner.setBannerTitles(Arrays.asList(titles));
//        //设置自动轮播，默认为true
//        mBanner.isAutoPlay(true);
//        //设置轮播时间
//        mBanner.setDelayTime(1500);
//        //设置指示器位置（当banner模式中有指示器时）
//        mBanner.setIndicatorGravity(BannerConfig.CENTER);
//        //banner设置方法全部调用完毕时最后调用
//        mBanner.start();

//        new Thread(){
//            @Override
//            public void run() {
//
//            }
//        }.start();




    }

    private void getDatas() {

        for(int i=0;i<images.length;i++){
            model = new CommonModel();
            model.setIcon(images[i]);
            imageUrls.add(model);
        }
    }
}
