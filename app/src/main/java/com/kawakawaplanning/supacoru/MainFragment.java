package com.KawakawaPlanning.supacoru;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import jp.sharakova.android.urlimageview.UrlImageView;


public class MainFragment extends Fragment{

    public int pos;
    Item items;
    ProgressBar progressBar;
    UrlImageView imageView;
    public MainFragment(){}

    public static Fragment newInstance(int pos) {

        MainFragment fragment = new MainFragment();
        fragment.pos = pos;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        imageView = (UrlImageView) v.findViewById(R.id.showImg);
        progressBar = Show.getProgress();


        return v;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressBar.setVisibility(View.INVISIBLE);
        items = MainActivity.adapter.getItem(pos);

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();

        if (nInfo != null) {
            /* NetWork接続可 */
            final Handler handler = new Handler();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageUrl(items.url + "?resize=516%2C729", new UrlImageView.OnImageLoadListener() {
                                @Override
                                public void onStart(String url) {
                                    progressBar.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onComplete(String url) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                }

                            });
                        }
                    });

                }
            }).start();


        } else {
            Toast.makeText(getActivity(), "No Network Connection!", Toast.LENGTH_LONG)
                    .show();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(null);
                        imageView = null;
                    }
                });
            }
        }).start();
    }

    @Override
    public void onStart() {
        super.onStart();
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageUrl(items.url, new UrlImageView.OnImageLoadListener() {
                            @Override
                            public void onStart(String url) {
                                progressBar.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onComplete(String url) {
                                progressBar.setVisibility(View.INVISIBLE);
                            }

                        });
                    }
                });

            }
        }).start();

    }
}