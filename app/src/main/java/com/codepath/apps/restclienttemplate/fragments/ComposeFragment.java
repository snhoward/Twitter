package com.codepath.apps.restclienttemplate.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterApp;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by snhoward on 7/6/17.
 */

public class ComposeFragment extends Fragment {

    TwitterClient client;
    @BindView(R.id.etTweet) EditText etTweet;
    @BindView(R.id.tvScreenName) TextView tvScreenName;
    @BindView(R.id.tvUsername) TextView tvUsername;
    @BindView(R.id.ivProfile) ImageView ivProfile;

    private EditText mEditText;

    public ComposeFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static ComposeFragment newInstance(String composition) {
        ComposeFragment frag = new ComposeFragment();
        Bundle args = new Bundle();
        args.putString("composition", composition);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_compose, container, false);
        ButterKnife.bind(this, v);
        client = TwitterApp.getRestClient();

        client.getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                User user = null;
                try {
                    user = User.fromJSON(response);
                    tvUsername.setText(user.name);
                    tvScreenName.setText("@" + user.screenName);

                    Glide.with(ComposeFragment.this)
                            .load(user.profileImageUrl)
                            .into(ivProfile);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return v;

    }

//    public void sendRequests(View view) {
//        // set the request parameters
//        client.sendTweet(etTweet.getText().toString(), new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                Tweet tweet = null;
//                try {
//                    tweet = Tweet.fromJSON(response);
//                    // send result back to TimelineActivity
//                    onSubmit(tweet);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public void onSubmit(Tweet tweet) {
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("tweet", Parcels.wrap(tweet));
//        data.putExtra("code", 200); // ints work too
        // Activity finished ok, return the data
//        setResult(RESULT_OK, data); // set result code and bundle data for response
//        finish(); // closes the activity, pass data to parent
    }
}
