package com.negah.telescope.app.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.adapters.CommentAdapter;
import com.negah.telescope.app.adapters.ScreenShotsAdapter;
import com.negah.telescope.app.models.PostDetail;
import com.negah.telescope.app.services.Network;
import com.negah.telescope.app.services.api.APIs;
import com.negah.telescope.app.services.lists.TelescopeComment;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySplash extends AppCompatActivity {
    ImageView icon;
    TextView appName,shortDesc,vendor;
    WebView reviewWebView;
    CardView ratesContainer;
    ScreenShotsAdapter screenShotsAdapter;
    RecyclerView screenshotsRecylcer;

    String TAG=ActivitySplash.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_page);
        findViews();
        APIs apIs= Network.getRetrofit().create(APIs.class);
        Call<PostDetail> call =apIs.loadPostDetails("12");
        call.enqueue(new Callback<PostDetail>() {
            @Override
            public void onResponse(Call<PostDetail> call, Response<PostDetail> response) {
                Log.d(TAG,"success");
                Picasso.with(ActivitySplash.this).load(response.body().getIcon()).into(icon);
                appName.setText(response.body().getAppname());
                vendor.setText(response.body().getVendor());
                reviewWebView.loadData(response.body().getFullReview(),"text/html","UTF-8");
                screenShotsAdapter=new ScreenShotsAdapter(ActivitySplash.this,response.body().getScreenshot());
                screenshotsRecylcer.setAdapter(screenShotsAdapter);
            }

            @Override
            public void onFailure(Call<PostDetail> call, Throwable t) {
                Log.d(TAG,"failed : "+t.getLocalizedMessage());
            }
        });



        /*new CountDownTimer(1000, 1000) {


            @Override
            public void onFinish() {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);


                startActivity(intent);


                finish();

            }

            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();*/

    }

    private void findViews(){
        icon= (ImageView) findViewById(R.id.desc_appiconImage);
        appName= (TextView) findViewById(R.id.desc_appname);
        vendor= (TextView) findViewById(R.id.desc_appvendor);
        shortDesc= (TextView) findViewById(R.id.desc_shortdesc);
        reviewWebView= (WebView) findViewById(R.id.review_webView);
        ratesContainer= (CardView) findViewById(R.id.rates_container);
        screenshotsRecylcer= (RecyclerView) findViewById(R.id.desc_screenshots_recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        screenshotsRecylcer.setLayoutManager(linearLayoutManager);
        //click listeners
        ratesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ActivitySplash.this,CommentsActivity.class);
                startActivity(i);
                //showCommentsDialog();
            }
        });

    }

    private void showCommentsDialog(){
        Dialog msgsDialog=new Dialog(this);
        msgsDialog.setContentView(R.layout.comments_list_layout);
        final RecyclerView commnetsRecyclerView= (RecyclerView) msgsDialog.findViewById(R.id.comments_recycler);
        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        commnetsRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        APIs apIs= Network.getRetrofit().create(APIs.class);
        Call<TelescopeComment> call =apIs.loadPostComments("12");
        call.enqueue(new Callback<TelescopeComment>() {
            @Override
            public void onResponse(Call<TelescopeComment> call, Response<TelescopeComment> response) {
                Log.d(TAG,"SUCCESS "+response.body().comments.size());
                if (response.body() != null) {
                    commnetsRecyclerView.setAdapter(new CommentAdapter(response.body().comments, ActivitySplash.this));
                }
            }

            @Override
            public void onFailure(Call<TelescopeComment> call, Throwable t) {
                Log.d(TAG,"FAILED "+t.getLocalizedMessage());
            }
        });
        msgsDialog.show();
    }
}