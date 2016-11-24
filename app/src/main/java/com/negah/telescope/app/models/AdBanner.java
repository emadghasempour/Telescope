package com.negah.telescope.app.models;

/**
 * Created by emad on 11/23/2016.
 */
public class AdBanner {
    String url,postId,title;

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
