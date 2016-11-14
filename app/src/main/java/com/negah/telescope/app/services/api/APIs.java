package com.negah.telescope.app.services.api;

import com.negah.telescope.app.models.Category;
import com.negah.telescope.app.models.PostDetail;
import com.negah.telescope.app.services.lists.TelescopeCategories;
import com.negah.telescope.app.services.lists.TelescopePostDetails;
import com.negah.telescope.app.services.lists.TelescopeRecent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by emad on 11/13/2016.
 */
public interface APIs {
    @GET("/ghazalservice/getData/getData.php?request=getRecentPosts")
    Call<TelescopeRecent> loadRecent();

    @GET("/ghazalservice/getData/getData.php?request=getCategories")
    Call<TelescopeCategories> loadCategories();

    @GET("/ghazalservice/getData/getData.php?request=getPostDetails")
    Call<PostDetail> loadPostDetails(@Query("postID") String ID);
}
