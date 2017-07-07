package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.models.User;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

        if(tweet.mediaURL != null) {
            holder.ivMedia.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(tweet.mediaURL)
                    .into(holder.ivMedia);
        } else {
            holder.ivMedia.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    // create ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.ivProfileImage) public ImageView ivProfileImage;
        @BindView(R.id.tvScreenName) public TextView tvScreenName;
        @BindView(R.id.tvBody) public TextView tvBody;
        @BindView(R.id.tvTimeStamp) public TextView tvTimeStamp;
        @BindView(R.id.tvUsername) public TextView tvName;
        @BindView(R.id.tvFavoriteCount) public TextView tvFavoriteCount;
        @BindView(R.id.tvRetweetCount) public TextView tvRetweetCount;
        @BindView(R.id.ivMedia) public ImageView ivMedia;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Tweet tweet = mTweets.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, DetailActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                // show the activity
                context.startActivity(intent);
            }
        }



        @OnClick(R.id.ivProfileImage)
        public void startProfileActivity() {
            Intent intent = new Intent(context, ProfileActivity.class);
            int position = getAdapterPosition();
            Tweet tweet = mTweets.get(position);
            User user = tweet.user;
            intent.putExtra(user.getClass().getSimpleName(), Parcels.wrap(user));
            context.startActivity(intent);
        }

        @OnClick(R.id.btnReply)
        public void startReplyActivity() {
            Intent intent = new Intent(context, ComposeActivity.class);
            int position = getAdapterPosition();
            Tweet tweet = mTweets.get(position);
            long id = tweet.getId();
            User user = tweet.user;
            intent.putExtra("screen_name", "@" + user.screenName);
            intent.putExtra("replyId", id);
            context.startActivity(intent);
        }

    }

    // Clean all elements of the recycler
    public void clear() {
        mTweets.clear();
        notifyDataSetChanged();
    }

    public Tweet getAt(int position) {
        return this.mTweets.get(position);
    }

}


//    @BindView(R.id.btnRetweet) public Button btnRetweet;
//    @BindView(R.id.btnHeart) public Button btnHeart;
//    @BindView(R.id.btnReply) public Button btnReply;

//        public boolean retweeted;
//        public boolean favorited;


