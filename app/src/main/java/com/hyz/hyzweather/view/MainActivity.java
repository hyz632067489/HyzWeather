package com.hyz.hyzweather.view;

import android.os.Bundle;

import com.hyz.hyzweather.R;
import com.hyz.hyzweather.bean.CommonModel;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity {



    int[] images = {R.mipmap.ic_launcher, R.mipmap.default_image, R.mipmap.grid_camera};
    String[] titles = {"1", "2", "3"};
    List<CommonModel> imageUrls = new ArrayList<>();

    CommonModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatas();




    }

    private void getDatas() {

        for (int i = 0; i < images.length; i++) {
            model = new CommonModel();
            model.setIcon(images[i]);
            imageUrls.add(model);
        }
    }
}
