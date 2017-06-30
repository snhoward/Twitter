package com.codepath.apps.restclienttemplate.models;

import android.text.format.DateUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by snhoward on 6/26/17.
 */
@Parcel
public class Tweet {

    // list of attributes
    public String body;
    public long uid; // database ID for tweet
    public User user;
    public String createdAt;
    public int favoriteCount;
    public boolean favorited;
    public int retweetCount;
    public boolean retweeted;


    // no-arg, empty constructor required for Parceler
    public Tweet() {}

    // deserialize the data
    public static Tweet fromJSON(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();

        // extract the values
        tweet.body = jsonObject.getString("text");
        tweet.uid = jsonObject.getLong("id");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
        tweet.favoriteCount = jsonObject.getInt("favorite_count");
        tweet.favorited = jsonObject.getBoolean("favorited");
        tweet.retweetCount = jsonObject.getInt("retweet_count");
        tweet.retweeted = jsonObject.getBoolean("retweeted");
        return tweet;
    }

    public String getOriginalTimeAgo(String createdAt) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(createdAt).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    /**
     * Given a date String of the format given by the Twitter API, returns a display-formatted
     * String representing the relative time difference, e.g. "2m", "6d", "23 May", "1 Jan 14"
     * depending on how great the time difference between now and the given date is.
     * This, as of 2016-06-29, matches the behavior of the official Twitter app.
     */
    public static String getRelativeTimeAgo(String rawJsonDate) {
        String time = "";
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat format = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        format.setLenient(true);
        try {
            long diff = (System.currentTimeMillis() - format.parse(rawJsonDate).getTime()) / 1000;
            if (diff < 5)
                time = "Just now";
            else if (diff < 60)
                time = String.format(Locale.ENGLISH, "%ds",diff);
            else if (diff < 60 * 60)
                time = String.format(Locale.ENGLISH, "%dm", diff / 60);
            else if (diff < 60 * 60 * 24)
                time = String.format(Locale.ENGLISH, "%dh", diff / (60 * 60));
            else if (diff < 60 * 60 * 24 * 30)
                time = String.format(Locale.ENGLISH, "%dd", diff / (60 * 60 * 24));
            else {
                Calendar now = Calendar.getInstance();
                Calendar then = Calendar.getInstance();
                then.setTime(format.parse(rawJsonDate));
                if (now.get(Calendar.YEAR) == then.get(Calendar.YEAR)) {
                    time = String.valueOf(then.get(Calendar.DAY_OF_MONTH)) + " "
                            + then.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US);
                } else {
                    time = String.valueOf(then.get(Calendar.DAY_OF_MONTH)) + " "
                            + then.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US)
                            + " " + String.valueOf(then.get(Calendar.YEAR) - 2000);
                }
            }
        }  catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
