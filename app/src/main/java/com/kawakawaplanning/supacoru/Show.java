package com.kawakawaplanning.supacoru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.widget.ProgressBar;

/**
 * Created by KP on 15/05/31.
 */
public class Show extends ActionBarActivity {//表示用ViewPagerの親Activity

    int position;
    static public PAdapter adap;
    ViewPager vp;
    private static ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        vp = (ViewPager) findViewById(R.id.mypager);//定義
        adap = new PAdapter(this.getSupportFragmentManager());
        vp.setAdapter(adap);//アダプタ入れる
        vp.setOffscreenPageLimit(1);

        Intent intent = getIntent();
        position = intent.getIntExtra("pos", 0);
        vp.setCurrentItem(position);

    }

    public static ProgressBar getProgress(){

        return progressBar;
    }

}

