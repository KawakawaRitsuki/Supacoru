package com.kawakawaplanning.supacoru;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class PAdapter extends FragmentPagerAdapter {

    public static List<String> newsTitle;



    public PAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Item items = MainActivity.adapter.getItem(position);
        return MainFragment.newInstance(items.url,position);

    }

    @Override
    public int getCount() {

        return MainActivity.max;

    }

    @Override
    public CharSequence getPageTitle(int position){

        Item items = MainActivity.adapter.getItem(position);
        return items.title;
    }
}