package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by snhoward on 6/26/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    private List<Tweet> mTweets;
    Context context;

    // pass in the Tweets array in the constructor
    public TweetAdapter(List<Tweet> tweets) {
        mTweets = tweets;

    }

    // for each row, inflate the layout and cache references into ViewHolder class
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    // bind the values based on the position of the element
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // get the data according to the position
        Tweet tweet = mTweets.get(position);

        // populate the views according to this data
        String screenName = "@" + tweet.user.screenName;
        holder.tvScreenName.setText(screenName);

        holder.tvBody.setText(tweet.body);
        holder.tvTimeStamp.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
        holder.tvFavoriteCount.setText("" + tweet.favoriteCount);
        holder.tvRetweetCount.setText("" + tweet.retweetCount);
        String name = tweet.user.name;
        holder.tvName.setText(name);



        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .into(holder.ivProfileImage);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    // create ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivProfileImage) public ImageView ivProfileImage;
        @BindView(R.id.tvScreenName) public TextView tvScreenName;
        @BindView(R.id.tvBody) public TextView tvBody;
        @BindView(R.id.tvTimeStamp) public TextView tvTimeStamp;
        @BindView(R.id.tvName) public TextView tvName;
        @BindView(R.id.tvFavoriteCount) public TextView tvFavoriteCount;
        @BindView(R.id.tvRetweetCount) public TextView tvRetweetCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> list) {
        mTweets.addAll(list);
        notifyDataSetChanged();
    }
}


//    @BindView(R.id.btnRetweet) public Button btnRetweet;
//    @BindView(R.id.btnHeart) public Button btnHeart;
//    @BindView(R.id.btnReply) public Button btnReply;

//        public boolean retweeted;
//        public boolean favorited;

