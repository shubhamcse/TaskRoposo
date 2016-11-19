package com.shubham.taskroposo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shubham Gupta on 18-11-2016.
 */

public class UserModel {

    private String createdOn;

    private String id;

    @SerializedName("is_following")
    private String isFollowing;

    private String following;

    private String followers;

    private String username;

    private String handle;

    private String about;

    private String image;

    private String url;

    private List<StoryModel> story;

    public List<StoryModel> getStory() {
        return story;
    }

    public void setStory(List<StoryModel> story) {
        this.story = story;
    }

    public void addStory(StoryModel storyModel){
        story.add(storyModel);
    }
    public void toggleFollowButton(){
        setIsFollowing(!(getIsFollowing()));
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsFollowing() {

        return Boolean.parseBoolean(isFollowing);
    }

    public void setIsFollowing(boolean isFollowing) {
        this.isFollowing = String.valueOf(isFollowing);
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
