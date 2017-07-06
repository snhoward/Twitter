package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.fragments.TweetsPagerAdapter;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


public class AltTimelineActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 20;
    protected TwitterClient client;


    @BindView(R.id.viewpager) ViewPager vpPager;
    @BindView(R.id.sliding_tabs) TabLayout tabLayout;
    @BindView(R.id.fabCompose) FloatingActionButton fabCompose;
    @BindView(R.id.ivProfile) ImageView ivProfile;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_timeline);
        ButterKnife.bind(this);
        client = TwitterApp.getRestClient();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fabCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AltTimelineActivity.this, ComposeActivity.class);
                intent.putExtra("mode", 2); // pass arbitrary data to launched activity
                AltTimelineActivity.this.startActivityForResult(intent, REQUEST_CODE);
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AltTimelineActivity.this, ProfileActivity.class);
                intent.putExtra("mode", 2);
                AltTimelineActivity.this.startActivity(intent);
            }
        });

        client.getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                User user = null;
                try {
                    user = User.fromJSON(response);
                    Glide.with(AltTimelineActivity.this)
                            .load(user.profileImageUrl)
                            .into(ivProfile);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // set the adapter
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager(), this));
        // set the tab layout
        tabLayout.setupWithViewPager(vpPager);
    }
}



