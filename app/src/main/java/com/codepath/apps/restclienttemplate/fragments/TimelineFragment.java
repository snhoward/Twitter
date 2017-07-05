package com.codepath.apps.restclienttemplate.fragments;

/**
 * Created by snhoward on 7/3/17.
 */

public class TimelineFragment extends TweetsListFragment {

    @Override
    protected void populateTimeline() {
        client.getHomeTimeline(getTimelineHandler());
    }
}
