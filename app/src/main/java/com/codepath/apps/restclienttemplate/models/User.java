package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by snhoward on 6/26/17.
 */

@Parcel
public class User {

    // list the attributes
    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;
    public String backgroundImageUrl;
    public String description, location, following, followers;

    // no-arg, empty constructor required for Parceler
    public User() {}

    // deserialize the JSON
    public static User fromJSON(JSONObject json) throws JSONException {
        User user = new User();

        // extract and fill the values
        user.name = json.getString("name");
        user.uid = json.getLong("id");
        user.screenName = json.getString("screen_name");
        user.profileImageUrl = json.getString("profile_image_url").replace("_normal", "");
        user.backgroundImageUrl = json.getString("profile_banner_url").replace("_normal", "");
        user.description = json.getString("description");
        user.location = json.getString("location");
        user.following = json.getString("friends_count").toString();
        user.followers = json.getString("followers_count").toString();

        return user;
    }
}
