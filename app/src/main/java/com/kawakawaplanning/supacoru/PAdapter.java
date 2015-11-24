package com.KawakawaPlanning.supacoru;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PAdapter extends FragmentPagerAdapter {

    public ArrayList<String> mTitles;
    public ArrayList<String> mUrls;
    public PAdapter(FragmentManager fm,ArrayList<String> titles,ArrayList<String> urls) {
        super(fm);
        this.mTitles = titles;
        this.mUrls   = urls;
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position,mTitles.get(position),mUrls.get(position));
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mTitles.get(position);
    }
}