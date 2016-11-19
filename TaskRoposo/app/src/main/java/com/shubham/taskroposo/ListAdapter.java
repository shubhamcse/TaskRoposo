package com.shubham.taskroposo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Shubham Gupta on 18-11-2016.
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
private List<StoryModel> stories;
    Context context;
    OnItemClicked onItemClicked;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        onItemClicked = (OnItemClicked)context;
        View view = inflater.inflate(R.layout.activity_list_item, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         ListViewHolder listViewHolder = (ListViewHolder)holder;
         UserModel user = stories.get(position).getUser();
         StoryModel story = stories.get(position);
         listViewHolder.mName.setText(user.getUsername());
         listViewHolder.mTime.setText(story.getVerb());
         listViewHolder.mTextLike.setText(story.getLikesCount());
         listViewHolder.mTextComment.setText(story.getCommentCount());
         listViewHolder.mStoryTitle.setText(story.getTitle());
        // listViewHolder.mFollowButton.setPressed((user.getIsFollowing()));
        if(user.getIsFollowing()){
            listViewHolder.mFollowButton.setText("Following");
            listViewHolder.mFollowButton.setSelected(true);
        }else{
            listViewHolder.mFollowButton.setText("Follow");
            listViewHolder.mFollowButton.setSelected(false);
        }
         Glide.with(context).load(user.getImage()).centerCrop().placeholder(R.drawable.blank_profile_picture).crossFade().
                into(listViewHolder.mProfilePicture);
        Glide.with(context).load(story.getSi()).centerCrop().placeholder(R.drawable.blank_profile_picture).crossFade().
                into(listViewHolder.mImageStory);
        Log.i("see:",""+user.getUsername()+";"+user.getIsFollowing());
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
       ImageView mProfilePicture;
       TextView mName;
       TextView mTime;
       ImageView mImageLike;
       TextView mTextLike;
       TextView mTextComment;
       ImageView mImageComment;
       TextView mStoryTitle;
       RelativeLayout mRelativeLayoutBottom;
       CardView mCardView;
       Button mFollowButton;
       ImageView mImageStory;
       public ListViewHolder(View itemView) {
           super(itemView);
           mProfilePicture = (ImageView) itemView.findViewById(R.id.image_view_profile_picure);
           mName = (TextView) itemView.findViewById(R.id.text_view_name);
           mTime = (TextView) itemView.findViewById(R.id.text_view_time_relative);
           mTextLike = (TextView) itemView.findViewById(R.id.text_view_like);
           mTextComment = (TextView) itemView.findViewById(R.id.text_view_comment);
           mImageLike = (ImageView) itemView.findViewById(R.id.image_view_like);
           mImageComment = (ImageView) itemView.findViewById(R.id.image_view_comment);
           mCardView = (CardView) itemView.findViewById(R.id.card_view);
           mRelativeLayoutBottom = (RelativeLayout) itemView.findViewById(R.id.relative_bottom_like_comment);
           mStoryTitle = (TextView) itemView.findViewById(R.id.text_view_title);
           mFollowButton = (Button) itemView.findViewById(R.id.button_follow);
           mImageStory = (ImageView) itemView.findViewById(R.id.image_view_story);

           mFollowButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //mFollowButton.setPressed(stories.get());
                   onItemClicked.onFollowButtonClicked(getLayoutPosition());

               }
           });
       }
   }

    public interface OnItemClicked{
        void onListItemClicked(int position,View view);
        void onFollowButtonClicked(int position);
    }

    public List<StoryModel> getStories() {
        return stories;
    }

    public void setStories(List<StoryModel> stories) {
        this.stories = stories;
    }
}
