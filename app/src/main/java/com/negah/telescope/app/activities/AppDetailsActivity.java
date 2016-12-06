package com.negah.telescope.app.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.negah.telescope.app.R;
import com.negah.telescope.app.adapters.CommentAdapter;
import com.negah.telescope.app.adapters.ScreenShotsAdapter;
import com.negah.telescope.app.models.PostDetail;
import com.negah.telescope.app.other.E;
import com.negah.telescope.app.services.Network;
import com.negah.telescope.app.services.api.APIs;
import com.negah.telescope.app.services.lists.TelescopeComment;
import com.negah.telescope.app.view.CustomFontButton;
import com.negah.telescope.app.view.CustomFontTextView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppDetailsActivity extends AppCompatActivity {
    ImageView icon;
    CustomFontTextView appName,shortDesc,vendor,postTitle,date;
    WebView reviewWebView;
    CardView ratesContainer;
    ScreenShotsAdapter screenShotsAdapter;
    RecyclerView screenshotsRecylcer;
    CustomFontButton marketsBtn;
    String TAG=AppDetailsActivity.class.getSimpleName();
    RelativeLayout linksViewParent;
    LinearLayout linksBtnParent;
    AppBarLayout appBarLayout;
    int SCROLL_MARGIN;
    NestedScrollView nestedScrollView;
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
                Picasso.with(AppDetailsActivity.this).load(response.body().getIcon()).into(icon);
                appName.setText(response.body().getAppname());
                vendor.setText(response.body().getVendor());
                reviewWebView.loadData(response.body().getFullReview(),"text/html; charset=utf-8",null);
                shortDesc.setText(response.body().getShortReview());

                screenShotsAdapter = new ScreenShotsAdapter(AppDetailsActivity.this,response.body().getScreenshot());
                screenshotsRecylcer.setAdapter(screenShotsAdapter);
                postTitle.setText(response.body().getTitle());
                View buttonItem= LayoutInflater.from(AppDetailsActivity.this).inflate(R.layout.market_button_item,null);
                CustomFontButton item= (CustomFontButton) buttonItem.findViewById(R.id.links_item_btn);
                for(int i=0;i<response.body().getMarkets().size();i++){
                    item.setText(response.body().getMarkets().get(i).getMarketName());
                    item.setId(1000+i);
                }
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
        SCROLL_MARGIN=E.dpToPx(this,30);
        nestedScrollView= (NestedScrollView) findViewById(R.id.desc_content_nested);
        nestedScrollView.setSmoothScrollingEnabled(true);
        postTitle= (CustomFontTextView) findViewById(R.id.desc_app_title);
        icon= (ImageView) findViewById(R.id.desc_appiconImage);
        appName= (CustomFontTextView) findViewById(R.id.desc_appname);
        vendor= (CustomFontTextView) findViewById(R.id.desc_appvendor);
        shortDesc= (CustomFontTextView) findViewById(R.id.desc_shortdesc_txt);
        reviewWebView= (WebView) findViewById(R.id.review_webView);
        date= (CustomFontTextView) findViewById(R.id.desc_app_date);
        ratesContainer= (CardView) findViewById(R.id.rates_container);
        appBarLayout= (AppBarLayout) findViewById(R.id.desc_appbar);
        screenshotsRecylcer= (RecyclerView) findViewById(R.id.desc_screenshots_recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        screenshotsRecylcer.setLayoutManager(linearLayoutManager);
        //click listeners
        ratesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AppDetailsActivity.this,CommentsActivity.class);
                startActivity(i);
               // showComme/ntsDialog();
            }
        });
        linksViewParent = (RelativeLayout) findViewById(R.id.desc_downloadAddress_parent);
        linksBtnParent= (LinearLayout) findViewById(R.id.desc_market_layout_center);
        marketsBtn= (CustomFontButton) findViewById(R.id.desc_markets_btn);
        marketsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                E.RevealAnimationShow(linksViewParent,(int)marketsBtn.getTop(),(int)marketsBtn.getRight());
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {



                if ((Math.abs(verticalOffset))-(appBarLayout.getTotalScrollRange()) == 0)
                {
                    //  Collapsed
                    marketsBtn.setVisibility(View.INVISIBLE);

                }
                else
                {
                    //Expanded
                    marketsBtn.setVisibility(View.VISIBLE);

                }
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
                    commnetsRecyclerView.setAdapter(new CommentAdapter(response.body().comments, AppDetailsActivity.this));
                }
            }

            @Override
            public void onFailure(Call<TelescopeComment> call, Throwable t) {
                Log.d(TAG,"FAILED "+t.getLocalizedMessage());
            }
        });
        msgsDialog.show();
    }

    @Override
    public void onBackPressed() {
        if(linksViewParent.getVisibility()==View.VISIBLE){
            E.RevealAnimationHide(linksViewParent);
        }
        else
             super.onBackPressed();

    }
}