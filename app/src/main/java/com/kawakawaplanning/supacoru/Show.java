package com.kawakawaplanning.supacoru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

/**
 * Created by KP on 15/05/31.
 */
public class Show extends ActionBarActivity {

    int position;
    static public PAdapter adap;
    ViewPager vp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        vp = (ViewPager) findViewById(R.id.mypager);//定義
        adap = new PAdapter(this.getSupportFragmentManager());
        vp.setAdapter(adap);//アダプタ入れる

        Intent intent = getIntent();
        position = intent.getIntExtra("pos", 0);
        vp.setCurrentItem(position);

    }
}

