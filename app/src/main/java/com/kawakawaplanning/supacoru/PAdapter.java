package com.kawakawaplanning.supacoru;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PAdapter extends FragmentPagerAdapter {

    public PAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return MainFragment.newInstance(position);

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