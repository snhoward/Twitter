package com.codepath.apps.restclienttemplate.fragments;

import android.os.Bundle;

/**
 * Created by snhoward on 7/5/17.
 */

public class UserFragment extends TweetsListFragment {

    public static UserFragment newInstance(String screenName) {
        UserFragment userFragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString("screen_name", screenName);
        userFragment.setArguments(args);
        return userFragment;
    }

    @Override
    protected void populateTimeline() {
        String screenName = getArguments().getString("screen_name");
        if(screenName == null) {
            screenName = "";
        }
        client.getUserTimeline(screenName, getTimelineHandler());
    }
}
