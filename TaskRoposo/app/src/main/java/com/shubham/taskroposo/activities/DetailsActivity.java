package com.shubham.taskroposo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shubham.taskroposo.R;
import com.shubham.taskroposo.adapers.ListAdapter;
import com.shubham.taskroposo.adapers.ListDetailAdapter;
import com.shubham.taskroposo.models.StoryModel;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shubham Gupta on 19-11-2016.
 */
@EActivity(R.layout.activity_details)
public class DetailsActivity extends AppCompatActivity implements ListAdapter.OnItemClicked{
    @ViewById(R.id.recycler_view)
    RecyclerView recyclerView;
    @ViewById(R.id.linear_layout)
    LinearLayout linearLayout;
    @ViewById(R.id.toolbarImage)
    ImageView toolbarImage;
    @ViewById(R.id.image_view_profile_picture)
    ImageView profileImage;

    @ViewById(R.id.text_view_name)
    TextView name;
    @ViewById(R.id.text_view_handle)
    TextView handle;
    @ViewById(R.id.text_view_followers)
    TextView followers;
    @ViewById(R.id.button_follow)
    Button followButton;
    @ViewById(R.id.text_view_about)
    TextView aboutView;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    ListDetailAdapter listAdapter;

    int RESULT;

    StoryModel storyModel;
    List<StoryModel> storyModels;
    boolean is_following;
    @AfterViews
    void init() {
        Bundle bundle = getIntent().getExtras();
        storyModel = (StoryModel) bundle.getSerializable("story");
        listAdapter = new ListDetailAdapter();
       storyModels = new ArrayList<StoryModel>();
        Log.i("seee",""+storyModel.getTitle()+":"+storyModel.getUser().getUsername());
        storyModels.add(storyModel);
        listAdapter.setStories(storyModels);
        setResult(RESULT_CANCELED);
        RESULT = RESULT_CANCELED;

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(storyModel.getUser().getUsername());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparentColor));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(listAdapter);
        Glide.with(this).load(storyModel.getSi()).fitCenter().placeholder(R.color.colorPrimary).crossFade().
                into(toolbarImage);
        Glide.with(this).load(storyModel.getUser().getImage()).centerCrop().placeholder(R.drawable.blank_profile_picture).crossFade().
                into(profileImage);
        name.setText(storyModel.getUser().getUsername());
        handle.setText(storyModel.getUser().getHandle());
        aboutView.setText(storyModel.getUser().getAbout());
        followers.setText(storyModel.getUser().getFollowers()+" followers");
        setFollowButton();
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFollowButtonClicked(0);
                setFollowButton();
            }
        });
    }

    public void setFollowButton() {
        if (storyModel.getUser().getIsFollowing()) {
            followButton.setText("Following");
            followButton.setSelected(true);
        } else {
            followButton.setText("Follow");
            followButton.setSelected(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClicked(int position, View view) {

    }

    @Override
    public void onBackPressed() {
        if(RESULT == RESULT_OK) {
            Intent intent = new Intent();
            intent.putExtra("is_following", storyModels.get(0).getUser().getIsFollowing());
            setResult(RESULT,intent);
        }else{
            setResult(RESULT_CANCELED);
        }
        super.onBackPressed();
    }

    @Override
    public void onFollowButtonClicked(int position) {
       storyModels.get(position).getUser().toggleFollowButton();
        //  Log.i("see",""+stories.get(position).getUser().getIsFollowing());
      //  listAdapter.setStories(stories);
        listAdapter.notifyDataSetChanged();
        setFollowButton();

        setResult(RESULT_OK);
        RESULT =RESULT_OK;
    }
}
