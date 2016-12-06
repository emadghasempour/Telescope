package com.negah.telescope.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.models.PostDetail;
import com.negah.telescope.app.services.Network;
import com.negah.telescope.app.services.api.APIs;
import com.negah.telescope.app.view.CustomFontTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by emad on 12/6/2016.
 */
public class PostDetailActivity extends AppCompatActivity {
    CustomFontTextView postTitle,date;
    WebView postfull;
    String TAG=AppDetailsActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_post);
        findViews();
        APIs apIs= Network.getRetrofit().create(APIs.class);
        Call<PostDetail> call =apIs.loadPostDetails("12");
        call.enqueue(new Callback<PostDetail>() {
            @Override
            public void onResponse(Call<PostDetail> call, Response<PostDetail> response) {
                postfull.loadData(response.body().getFullReview(),"text/html; charset=utf-8",null);
                postTitle.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<PostDetail> call, Throwable t) {

            }
        });
    }


    private void findViews(){
        postTitle= (CustomFontTextView) findViewById(R.id.desc_post_title);
        date= (CustomFontTextView) findViewById(R.id.desc_post_date);
        postfull= (WebView) findViewById(R.id.desc_post_review_webView);
    }
}
