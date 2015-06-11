package com.kawakawaplanning.supacoru;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import jp.sharakova.android.urlimageview.UrlImageView;

/**
 * Created by KP on 15/05/04.
 */
public class CustomAdapter extends ArrayAdapter<Item> {//メイン画面List用Adapter

    LayoutInflater mInflater;
    int mResId;
    int mAnimatedPosition = ListView.INVALID_POSITION;
    UrlImageView mImageView;

    public CustomAdapter(Context context, int resource) {
        super(context, 0);
        mResId = resource;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mResId, parent, false);
        }

        Item item = getItem(position);

        TextView titleTv = (TextView) convertView.findViewById(R.id.title);
        titleTv.setText(item.title);

        mImageView = (UrlImageView)convertView.findViewById(R.id.image);

        mImageView.setImageUrl(item.url + "?resize=520%2C245");

        if (mAnimatedPosition < position) {
            Animator animator = AnimatorInflater.loadAnimator(getContext(), R.animator.card_slide_in);
            animator.setTarget(convertView);
            animator.start();
            mAnimatedPosition = position;
        }

        return convertView;
    }


}