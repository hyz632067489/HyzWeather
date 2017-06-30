package com.hyz.hyzweather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hyz.hyzweather.view.fragment.BaseMvpFragment;

import java.util.List;

/**
 * Created by hyz on 2017/6/26.
 */

public class TypePageAdapter extends FragmentPagerAdapter {


    private List<BaseMvpFragment> fragments;
    private List<String> title;


    public TypePageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<BaseMvpFragment> fragments ,List<String> titles){
        this.fragments = fragments;
        this.title = titles;
    }



    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
