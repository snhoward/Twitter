package com.codepath.apps.restclienttemplate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by snhoward on 6/26/17.
 */

public class TweetAdapter {

    // pass in the Tweets array in the constructor

    // for each row, inflate the layout and cache references into ViewHolder class

    // bind the values based on the position of the element

    // create ViewHolder class

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivProfileImage) public ImageView ivProfileImage;
        @BindView(R.id.tvUsername) public TextView tvUsername;
        @BindView(R.id.tvBody) public TextView tvBody;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }



}
