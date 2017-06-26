package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by snhoward on 6/26/17.
 */

public class Tweet {

    // list of attributes
    public String body;
    public long uid; // database ID for tweet
    // public User user;
    public String createdAt;

    // deserialize the data
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        // extract the values
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        return tweet;
    }

}
