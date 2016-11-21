package com.negah.telescope.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.models.Comment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by emad on 11/19/2016.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentVH>{

    List<Comment> comments;
    Context context;

    public CommentAdapter(List<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @Override
    public CommentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false);
        return new CommentVH(view);
    }

    @Override
    public void onBindViewHolder(CommentVH holder, int position) {
        Picasso.with(context).load(comments.get(position).getAvatar()).into(holder.avatar);
        holder.userName.setText(comments.get(position).getUsername());
        holder.comment.setText(comments.get(position).getCommnet());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentVH extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView userName,comment;

        public CommentVH(View itemView) {
            super(itemView);
            avatar= (ImageView) itemView.findViewById(R.id.comment_item_avatar);
            userName= (TextView) itemView.findViewById(R.id.comment_item_username);
            comment= (TextView) itemView.findViewById(R.id.comment_item_comments);
            
        }
    }
}
