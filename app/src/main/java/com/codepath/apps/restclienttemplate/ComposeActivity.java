package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

import static com.codepath.apps.restclienttemplate.TwitterClient.REST_CONSUMER_KEY;
import static com.codepath.apps.restclienttemplate.TwitterClient.REST_URL;

public class ComposeActivity extends AppCompatActivity {

    TwitterClient client;
    @Nullable @BindView(R.id.etTweet) EditText etTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
    }

    @OnClick(R.id.btnTweet)
    public void sendRequest() {
        // set the request parameters
        RequestParams params = new RequestParams();
        params.put(REST_URL, REST_CONSUMER_KEY); // TODO: Is this right?
        client.sendTweet(etTweet.toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Tweet tweet = null;
                try {
                    tweet = Tweet.fromJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onSubmit(tweet);
            }
        });
    }

    public void onSubmit(Tweet tweet) {
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("user", tweet.getUser().toString());
        data.putExtra("body", tweet.getBody().toString());
        data.putExtra("code", 200); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}

