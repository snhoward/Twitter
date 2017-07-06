package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.fragments.UserFragment;
import com.codepath.apps.restclienttemplate.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {

    TwitterClient client;
    User user;
    private final int REQUEST_CODE = 20;


    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.tvUsername) TextView tvUsername;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvLocation) TextView tvLocation;
    @BindView(R.id.tvFollowingCount) TextView tvFollowingCount;
    @BindView(R.id.tvFollowersCount) TextView tvFollowersCount;
    @BindView(R.id.ivProfileImage) ImageView ivProfileImage;
    @BindView(R.id.ivBackgroundImage) ImageView ivBackgroundImage;
    @BindView(R.id.btnEditProfile) Button btnEditProfile;
    @BindView(R.id.fabCompose) FloatingActionButton fabCompose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        client = TwitterApp.getRestClient();


        user = (User) Parcels.unwrap(getIntent().getParcelableExtra(User.class.getSimpleName()));
        String screenName = user.screenName;
//        getIntent().getStringExtra("screen_name");
        UserFragment userTimelineFragment = UserFragment.newInstance(screenName);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flContainer, userTimelineFragment);
        ft.commit();


        getSupportActionBar().hide();

        fabCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ComposeActivity.class);
                intent.putExtra("mode", 2); // pass arbitrary data to launched activity
                ProfileActivity.this.startActivityForResult(intent, REQUEST_CODE);
            }
        });

        client.getUserInfo(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    tvName.setText(user.name);
                    tvUsername.setText("@" + user.screenName);
                    tvDescription.setText(user.description);
                    tvLocation.setText(user.location);
                    tvFollowingCount.setText(user.following);
                    tvFollowersCount.setText(user.followers);

                    Glide.with(ProfileActivity.this)
                            .load(user.profileImageUrl)
                            .into(ivProfileImage);

                    Glide.with(ProfileActivity.this)
                            .load(user.backgroundImageUrl)
                            .into(ivBackgroundImage);
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "Loading Edit Profile screen...", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
