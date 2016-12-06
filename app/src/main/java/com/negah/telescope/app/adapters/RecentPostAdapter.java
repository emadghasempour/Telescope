package com.negah.telescope.app.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.models.Item;
import com.negah.telescope.app.view.CustomFontTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by emad on 11/23/2016.
 */
public class RecentPostAdapter extends RecyclerView.Adapter<RecentPostAdapter.RecentItem>{
    public final int POST_TYPE_AD=3, POST_TYPE_APP=1,POST_TYPE_CUSTOM=2;

    List<Item> items;
    Context context;
    private OnItemClickListener mItemClickListener;

    public RecentPostAdapter(List<Item> items,Context context) {
        this.context = context;
        this.items = items;
        this.context=context;
    }

    /**
     * should load app - post - ad
     * @param parent
     * @param viewType
     * @return
     */

    @Override
    public RecentItem onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(viewType==POST_TYPE_APP){
            View view=inflater.inflate(R.layout.recent_app_item,parent,false);
            return new RecentItemApp(view);
        }
        else if(viewType==POST_TYPE_CUSTOM){
            View view=inflater.inflate(R.layout.recent_post_item,parent,false);
            return new RecentItemCustom(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecentItem holder, int position) {


        int type=getItemViewType(position);
        switch (type){
            case POST_TYPE_APP:
                RecentItemApp recentItemApp= (RecentItemApp) holder;
                recentItemApp.title.setText(items.get(position).getTitle());
                recentItemApp.appName.setText(items.get(position).getAppname());
                recentItemApp.vendor.setText(items.get(position).getVendor());
                recentItemApp.writer.setText(items.get(position).getWriter());
                break;
            case POST_TYPE_CUSTOM:
                RecentItemCustom recentItemCustom = (RecentItemCustom) holder;
                recentItemCustom.title.setText(items.get(position).getTitle());
                Picasso.with(context).load(items.get(position).getImagePath()).into(recentItemCustom.background);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecentItem extends RecyclerView.ViewHolder implements View.OnClickListener{
        public RecentItem(View itemView) {
            super(itemView);

        }
        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView,getPosition());
            }
        }
    }

    private class RecentItemApp extends RecentItem{
        ImageView icon;
        CustomFontTextView appName,vendor,date,title,writer;
        CardView parent;
        public RecentItemApp(View itemView) {
            super(itemView);
            appName= (CustomFontTextView) itemView.findViewById(R.id.recent_app_appname);
            vendor= (CustomFontTextView) itemView.findViewById(R.id.recent_app_vendor);
            date= (CustomFontTextView) itemView.findViewById(R.id.recent_app_date);
            title= (CustomFontTextView) itemView.findViewById(R.id.recent_app_title);
            icon= (ImageView) itemView.findViewById(R.id.recent_app_icon);
            writer= (CustomFontTextView) itemView.findViewById(R.id.recent_app_userName);
            parent= (CardView) itemView.findViewById(R.id.recent_app_parent);
            parent.setOnClickListener(this);
        }
    }
    private class RecentItemCustom extends RecentItem{
        ImageView background;
        CustomFontTextView date,title;
        CardView parent;
        public RecentItemCustom(View itemView) {
            super(itemView);
            title= (CustomFontTextView) itemView.findViewById(R.id.recent_post_title);
            date= (CustomFontTextView) itemView.findViewById(R.id.recent_post_date);
            background= (ImageView) itemView.findViewById(R.id.recent_post_background);
            parent= (CardView) itemView.findViewById(R.id.recent_post_parent);
            parent.setOnClickListener(this);
        }
    }


    @Override
    public int getItemViewType(int position) {
        switch (items.get(position).getPostType()){
            case 1:
                return POST_TYPE_APP;
            case 2:
                return POST_TYPE_CUSTOM;
            case 3:
                return POST_TYPE_AD;

            default:
                return POST_TYPE_APP;
        }


    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
