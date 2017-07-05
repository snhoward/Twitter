package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codepath.apps.restclienttemplate.fragments.TweetsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AltTimelineActivity extends AppCompatActivity {


//    TwitterClient client;
//    TweetAdapter tweetAdapter;
//    ArrayList<Tweet> tweets;
//    @BindView(R.id.rvTweets)
//    RecyclerView rvTweets;
    private final int REQUEST_CODE = 20;
//    private SwipeRefreshLayout swipeContainer;

    @BindView(R.id.viewpager) ViewPager vpPager;
    @BindView(R.id.sliding_tabs) TabLayout tabLayout;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_timeline);
        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCompose);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AltTimelineActivity.this, ComposeActivity.class);
                intent.putExtra("mode", 2); // pass arbitrary data to launched activity
                AltTimelineActivity.this.startActivityForResult(intent, REQUEST_CODE);
            }
        });

//        client = TwitterApp.getRestClient();
        // init the ArrayList
//        tweets = new ArrayList<>();
//        // construct the adapter from this data source
//        tweetAdapter = new TweetAdapter(tweets);
//        // RecyclerView setup (layout manager, use adapter)
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rvTweets.setLayoutManager(linearLayoutManager);
//        // set the adapter
//        rvTweets.setAdapter(tweetAdapter);
//        populateTimeline();
//        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
//        // Setup refresh listener which triggers new data loading
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Your code to refresh the list here.
//                // Make sure you call swipeContainer.setRefreshing(false)
//                // once the network request has completed successfully.
//                fetchTimelineAsync(0);
//            }
//        });
//
//        // Configure the refreshing colors
//        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);

        // set the adapter
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager(), this));
        // set the tab layout
        tabLayout.setupWithViewPager(vpPager);
    }

//    public void fetchTimelineAsync(int page) {
//        // Send the network request to fetch the updated data
//        populateTimeline();
//    }

//    private void populateTimeline() {
//        client.getHomeTimeline(new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                Log.d("TwitterClient", response.toString());
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                tweetAdapter.clear();
////                Log.d("TwitterClient", response.toString());
//                // iterate through the JSON array
//                // for each entry, deserialize the JSON object
////                for(int i = 0; i < response.length(); i++) {
////                    // convert each object to a Tweet model
////                    // add that Tweet model to our data source
////                    // notify the adapter that we've added an item
////                    try {
////                        Tweet tweet = Tweet.fromJSON(response.getJSONObject(i));
////                        tweets.add(tweet);
////                        tweetAdapter.notifyItemInserted(tweets.size() - 1);
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
//                swipeContainer.setRefreshing(false);
//                fragmentTweetsList.addItems(response);
//            }
//
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                Log.d("TwitterClient", responseString);
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                Log.d("TwitterClient", errorResponse.toString());
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//                Log.d("TwitterClient", errorResponse.toString());
//                throwable.printStackTrace();
//            }
//        });
//    }
}



