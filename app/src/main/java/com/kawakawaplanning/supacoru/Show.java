package com.kawakawaplanning.supacoru;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import jp.sharakova.android.urlimageview.UrlImageView;

/**
 * Created by KP on 15/05/31.
 */
public class Show extends ActionBarActivity {

    String title;
    String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");

        Log.v("kp",title + "," + url);

        UrlImageView imageView = (UrlImageView)findViewById(R.id.showImg);
        imageView.setImageUrl(url);
    }
}
