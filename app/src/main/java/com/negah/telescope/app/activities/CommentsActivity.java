package com.negah.telescope.app.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.negah.telescope.app.R;
import com.negah.telescope.app.adapters.CommentAdapter;
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
    String TAG=CommentsActivity.class.getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_list_layout);
        findViews();
    }
    private void findViews(){
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
            }

            @Override
            public void onFailure(Call<TelescopeComment> call, Throwable t) {
                Log.d(TAG,"FAILED "+t.getLocalizedMessage());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog msgsDialog=new Dialog(CommentsActivity.this);
                msgsDialog.setContentView(R.layout.submit_comment_layout);
                msgsDialog.show();
            }
        });

    }

}
