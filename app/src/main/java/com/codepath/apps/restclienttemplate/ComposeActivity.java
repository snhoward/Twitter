package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    TwitterClient client;
    @BindView(R.id.etTweet) EditText etTweet;
    @BindView(R.id.tvScreenName) TextView tvScreenName;
    @BindView(R.id.tvUsername) TextView tvUsername;
    @BindView(R.id.ivProfile) ImageView ivProfile;
    @BindView(R.id.tvReply) TextView tvReply;
    // Instance of the progress action-view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        ButterKnife.bind(this);
        client = TwitterApp.getRestClient();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        client.getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                User user = null;
                try {
                    user = User.fromJSON(response);
                    tvUsername.setText(user.name);
                    tvScreenName.setText("@" + user.screenName);
                    tvReply.setText("Replying to " + getIntent().getStringExtra("screen_name"));
                    if(tvReply.toString() != "") {
                        tvReply.setVisibility(View.VISIBLE);
                    } else {
                        tvReply.setVisibility(View.GONE);
                    }


                    Glide.with(ComposeActivity.this)
                            .load(user.profileImageUrl)
                            .into(ivProfile);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendRequest(View view) {
        // set the request parameters
        long replyId = getIntent().getLongExtra("replyId", -1);
        String message = "";
        if (replyId > 0) {
            message = getIntent().getStringExtra("screen_name") + " ";
        }
        client.sendTweet(replyId, message.toString() + etTweet.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Tweet tweet = null;
                try {
                    tweet = Tweet.fromJSON(response);
                    // send result back to TimelineActivity
                    onSubmit(tweet);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onSubmit(Tweet tweet) {
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("tweet", Parcels.wrap(tweet));
//        data.putExtra("code", 200); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}

