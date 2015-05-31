package com.kawakawaplanning.supacoru;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import jp.sharakova.android.urlimageview.UrlImageView;

/**
 * Created by KP on 15/05/31.
 */
public class Show extends ActionBarActivity {

    Item items;
    int position;
    Context context;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        context = this;

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("読み込み中...");
        progressDialog.setMessage("画像を読み込み中です。しばらくお待ち下さい。");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);


        Intent intent = getIntent();
        position = intent.getIntExtra("pos", 0);

        items = MainActivity.adapter.getItem(position);

        getSupportActionBar().setTitle(items.title);

        UrlImageView imageView = (UrlImageView) findViewById(R.id.showImg);
        imageView.setImageUrl(items.url, new UrlImageView.OnImageLoadListener() {
            @Override
            public void onStart(String url) {
                progressDialog.show();
            }

            @Override
            public void onComplete(String url) {
                progressDialog.dismiss();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem backBtn = menu.add("back");
        backBtn.setIcon(R.drawable.ic_menu_back);
        backBtn.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        MenuItem nextBtn = menu.add("next");
        nextBtn.setIcon(R.drawable.ic_menu_next);
        nextBtn.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getTitle().toString()){
            case "next":
                if(position >= 1){
                    position--;

                    items = MainActivity.adapter.getItem(position);

                    getSupportActionBar().setTitle(items.title);

                    UrlImageView imageView = (UrlImageView) findViewById(R.id.showImg);
                    imageView.setImageUrl(items.url, new UrlImageView.OnImageLoadListener() {
                        @Override
                        public void onStart(String url) {
                            progressDialog.show();
                        }

                        @Override
                        public void onComplete(String url) {
                            progressDialog.dismiss();
                        }
                    });
                }else{
                    Toast.makeText(this,"最新話です",Toast.LENGTH_SHORT).show();
                }

                break;

            case "back":
                if(position < MainActivity.max-1){
                    position++;

                    items = MainActivity.adapter.getItem(position);

                    getSupportActionBar().setTitle(items.title);

                    UrlImageView imageView = (UrlImageView) findViewById(R.id.showImg);
                    imageView.setImageUrl(items.url, new UrlImageView.OnImageLoadListener() {
                        @Override
                        public void onStart(String url) {
                            progressDialog.show();
                        }

                        @Override
                        public void onComplete(String url) {
                            progressDialog.dismiss();
                        }
                    });
                }else{
                    Toast.makeText(this,"1話目です",Toast.LENGTH_SHORT).show();
                }

                break;
        }
        return true;
    }
}

