package com.negah.telescope.app.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.Telescope;
import com.negah.telescope.app.adapters.CommentAdapter;
import com.negah.telescope.app.other.E;
import com.negah.telescope.app.services.Network;
import com.negah.telescope.app.services.api.APIs;
import com.negah.telescope.app.services.lists.TelescopeComment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by emad on 11/21/2016.
 */
public class CommentsActivity extends AppCompatActivity {
    RecyclerView commentsRecycler;
    FloatingActionButton fab;
    Toolbar toolbar;
    String TAG=CommentsActivity.class.getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_list_layout);
        findViews();
    }
    private void findViews(){
        E.getInstance(this).ShowLoading(getResources().getString(R.string.pleaseWait));
        fab= (FloatingActionButton) findViewById(R.id.comments_fab_add);
        commentsRecycler= (RecyclerView) findViewById(R.id.comments_recycler);
        //StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        int columns=getResources().getInteger(R.integer.latest_column_count);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,columns);
        /*gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position/2==1)
                return (2);
                else return 1;
            }
        });*/
        commentsRecycler.setLayoutManager(gridLayoutManager);
        APIs apIs= Network.getRetrofit().create(APIs.class);
        Call<TelescopeComment> call =apIs.loadPostComments("21");

        call.enqueue(new Callback<TelescopeComment>() {
            @Override
            public void onResponse(Call<TelescopeComment> call, Response<TelescopeComment> response) {
                Log.d(TAG,"SUCCESS "+response.body().comments.size());
                if (response.body() != null) {
                    commentsRecycler.setAdapter(new CommentAdapter(response.body().comments, CommentsActivity.this));
                }
                if(E.getInstance(CommentsActivity.this).isLoading()){
                    E.getInstance(CommentsActivity.this).DismissLoading();
                }
            }

            @Override
            public void onFailure(Call<TelescopeComment> call, Throwable t) {
                Log.d(TAG,"FAILED "+t.getLocalizedMessage());
                if(E.getInstance(CommentsActivity.this).isLoading()){
                    E.getInstance(CommentsActivity.this).DismissLoading();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Telescope.IS_USER_LOGED_IN){
                    ShowSigninPopup();
                    return;
                }

                Dialog msgsDialog=new Dialog(CommentsActivity.this,R.style.AppTheme_Dialog);
                msgsDialog .requestWindowFeature(Window.FEATURE_NO_TITLE);
                msgsDialog.setContentView(R.layout.submit_comment_layout);
                final LinearLayout submitRatePanel= (LinearLayout) msgsDialog.findViewById(R.id.submit_rate_panel);
                CheckBox submitRateCHB= (CheckBox) msgsDialog.findViewById(R.id.submit_comment_showrate_chb);
                submitRateCHB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if(b){
                            submitRatePanel.setVisibility(View.VISIBLE);
                        }else
                            submitRatePanel.setVisibility(View.GONE);
                    }
                });
                msgsDialog.show();
            }
        });
        toolbar= (Toolbar) findViewById(R.id.comment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void ShowSigninPopup(){
       AlertDialog dialog= new AlertDialog.Builder(CommentsActivity.this)
                .setTitle("")
                .setMessage(getResources().getString(R.string.title_app_youShouldSignIn))
                .setCancelable(true)
                .setNegativeButton(R.string.dismiss,null)
                .setPositiveButton(getResources().getText(R.string.title_app_signup), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i=new Intent(CommentsActivity.this,SignupActivity.class);
                        startActivity(i);
                    }
                }).show();
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        textView.setTypeface(E.geFont(this));
        Button textView2 = (Button) dialog.getButton(Dialog.BUTTON_NEGATIVE);
        textView2.setTypeface(E.geFont(this));
        TextView textView3 = (TextView) dialog.findViewById(android.R.id.button1);
        textView3.setTypeface(E.geFont(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
