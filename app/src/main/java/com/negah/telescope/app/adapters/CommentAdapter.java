package com.negah.telescope.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.models.Comment;
import com.negah.telescope.app.view.CustomFontTextView;
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
        if(!Boolean.parseBoolean(comments.get(position).getHasRate())){
            holder.rateparent.setVisibility(View.GONE);
        }else{
            holder.rate1.setProgress((int)(comments.get(position).getRates().getUI()*10));
            holder.rate2.setProgress((int)(comments.get(position).getRates().getUX()*10));
            holder.rate3.setProgress((int)(comments.get(position).getRates().getUsibility()*10));
            holder.rate4.setProgress((int)(comments.get(position).getRates().getOptions()*10));
            holder.rate5.setProgress((int)(comments.get(position).getRates().getCorret()*10));

            holder.trate1.setText(context.getResources().getString(R.string.ratetitleUI));
            holder.trate2.setText(context.getResources().getString(R.string.ratetitleUX));
            holder.trate3.setText(context.getResources().getString(R.string.ratetitleUsibity));
            holder.trate4.setText(context.getResources().getString(R.string.ratetitleOptions));
            holder.trate5.setText(context.getResources().getString(R.string.ratetitleCorrenct));


        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentVH extends RecyclerView.ViewHolder{
        ImageView avatar;
        CustomFontTextView userName,comment;
        LinearLayout rateparent;
        ProgressBar rate1,rate2,rate3,rate4,rate5;
        CustomFontTextView trate1,trate2,trate3,trate4,trate5;

        public CommentVH(View itemView) {
            super(itemView);
            avatar= (ImageView) itemView.findViewById(R.id.comment_item_avatar);
            userName= (CustomFontTextView) itemView.findViewById(R.id.comment_item_username);
            comment= (CustomFontTextView) itemView.findViewById(R.id.comment_item_comments);
            rateparent= (LinearLayout) itemView.findViewById(R.id.show_rate_layout);

            rate1= (ProgressBar) rateparent.findViewById(R.id.showrate_progressBar1);
            rate2= (ProgressBar) rateparent.findViewById(R.id.showrate_progressBar2);
            rate3= (ProgressBar) rateparent.findViewById(R.id.showrate_progressBar3);
            rate4= (ProgressBar) rateparent.findViewById(R.id.showrate_progressBar4);
            rate5= (ProgressBar) rateparent.findViewById(R.id.showrate_progressBar5);


            trate1= (CustomFontTextView) rateparent.findViewById(R.id.show_rate_progressBar_title1);
            trate2= (CustomFontTextView) rateparent.findViewById(R.id.show_rate_progressBar_title2);
            trate3= (CustomFontTextView) rateparent.findViewById(R.id.show_rate_progressBar_title3);
            trate4= (CustomFontTextView) rateparent.findViewById(R.id.show_rate_progressBar_title4);
            trate5= (CustomFontTextView) rateparent.findViewById(R.id.show_rate_progressBar_title5);
        }
    }
}
