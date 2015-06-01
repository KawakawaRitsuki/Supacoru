package com.kawakawaplanning.supacoru;


import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import jp.sharakova.android.urlimageview.UrlImageView;


public class MainFragment extends Fragment{

    public int pos;
    ProgressDialog progressDialog;
    Item items;

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

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("読み込み中...");
        progressDialog.setMessage("画像を読み込み中です。しばらくお待ち下さい。");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        items = MainActivity.adapter.getItem(pos);

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();

        if (nInfo != null) {
            /* NetWork接続可 */
            UrlImageView imageView = (UrlImageView) v.findViewById(R.id.showImg);
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

        } else {
            /* NetWork接続不可 */
            Toast.makeText(getActivity(), "No Network Connection!", Toast.LENGTH_LONG)
                    .show();
        }

        return v;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
