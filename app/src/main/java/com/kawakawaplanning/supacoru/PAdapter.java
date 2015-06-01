package com.kawakawaplanning.supacoru;

/**
 * Created by KP on 15/05/05.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * Created by KP on 2015/03/25.
 */
public class PAdapter extends FragmentPagerAdapter {

    public static List<String> newsTitle;



    public PAdapter(FragmentManager fm) {
        super(fm);
//        newsTitle = new ArrayList<String>();
//
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
//        int getInt = sharedPref.getInt("URLNum", 0);
//
//        for(int i = 0;getInt != i ; i++) {
//            newsTitle.add("");
//        }
//        if (getInt == 0){
//            newsTitle.add("");
//        }
    }

    @Override
    public Fragment getItem(int position) {
//        switch(position){
//            case 0:
//                return MainFragment.newInstance("vol1",);
//            case 1:
//                return MainFragment.newInstance("http://feeds.gizmodo.jp/rss/gizmodo/index.xml");
//            case 2:
//                return MainFragment.newInstance("http://feeds.lifehacker.jp/rss/lifehacker/index.xml");
//            case 3:
//                return MainFragment.newInstance("http://y-anz-m.blogspot.com/feeds/posts/default");
//            case 4:
//                return MainFragment.newInstance("http://kyoko-np.net/index.xml");
//        }

//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
//        String getStr[] = sharedPref.getString("URLData", "").split(",");


        return MainFragment.newInstance("http://i1.wp.com/pronama.azurewebsites.net/wp-content/uploads/2015/05/150515.png",1);


//        return null;
    }

    @Override
    public int getCount() {
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
//        int getInt = sharedPref.getInt("URLNum", 0);

        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){

//        return newsTitle.get(position) ;
        return "vol1";
    }
}