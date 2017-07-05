package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by snhoward on 7/3/17.
 */

public class TweetsPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Home", "Mentions"};
    private Context context;

    public TweetsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    // return the total num of fragments
    @Override
    public int getCount() {
        return 2;
    }

    // return the fragments to use depending on position
    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new TimelineFragment();
        } else if(position == 1){
            return new MentionsFragment();
        } else {
            return null;
        }
    }


    // return title
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
