package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codepath.apps.restclienttemplate.fragments.TweetsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AltTimelineActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 20;

    @BindView(R.id.viewpager) ViewPager vpPager;
    @BindView(R.id.sliding_tabs) TabLayout tabLayout;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_timeline);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCompose);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AltTimelineActivity.this, ComposeActivity.class);
                intent.putExtra("mode", 2); // pass arbitrary data to launched activity
                AltTimelineActivity.this.startActivityForResult(intent, REQUEST_CODE);
            }
        });

        // set the adapter
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager(), this));
        // set the tab layout
        tabLayout.setupWithViewPager(vpPager);
    }
}



