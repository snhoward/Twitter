package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    Tweet tweet;
    TwitterClient client;
    @BindView(R.id.tvUsername) TextView tvUsername;
    @BindView(R.id.tvScreenName) TextView tvScreenName;
    @BindView(R.id.tvBody) TextView tvBody;
    @BindView(R.id.tvTimeStamp) TextView tvTimeStamp;
    @BindView(R.id.tvFavoriteCount) TextView tvFavoriteCount;
    @BindView(R.id.tvRetweetCount) TextView tvRetweetCount;
    @BindView(R.id.ivProfileImage) ImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //adding all of the text and images into the xml from the parceled tweet that was passed with the intent
        client = TwitterApp.getRestClient();
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));
        Log.d("DetailActivity", String.format("Showing details for '%s'", tweet.user));
        // set the title and overview
        tvScreenName.setText(tweet.user.screenName);
        tvUsername.setText(tweet.user.name);
        tvBody.setText(tweet.body);
        tvFavoriteCount.setText("" + tweet.favoriteCount + " Likes");
        tvRetweetCount.setText("" + tweet.retweetCount + " Retweets");
        tvTimeStamp.setText(tweet.getOriginalTimeAgo(tweet.createdAt));

        Glide.with(ivProfileImage.getContext())
                .load(tweet.user.profileImageUrl)
                .into(ivProfileImage);

    }
}
