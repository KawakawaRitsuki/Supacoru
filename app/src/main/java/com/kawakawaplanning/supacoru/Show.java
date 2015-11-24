package com.KawakawaPlanning.supacoru;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

/**
 * Created by KP on 15/05/31.
 */
public class Show extends ActionBarActivity {//表示用ViewPagerの親Activity

    int position;
    static public PAdapter adap;
    ViewPager vp;
    public ArrayList<String> mTitles;
    public ArrayList<String> mUrls;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        mTitles = getIntent().getStringArrayListExtra("titles");
        mUrls = getIntent().getStringArrayListExtra("urls");
        init();
    }

    public void init(){

        vp = (ViewPager) findViewById(R.id.mypager);//定義
        adap = new PAdapter(this.getSupportFragmentManager(),mTitles,mUrls);
        vp.setAdapter(adap);//アダプタ入れる
        vp.setOffscreenPageLimit(1);

        position = getIntent().getIntExtra("position", 0);
        vp.setCurrentItem(position);
    }

}