package com.negah.telescope.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.models.Item;
import com.negah.telescope.app.models.ItemRecent;
import com.negah.telescope.app.view.CustomFontTextView;

import java.util.List;

/**
 * Created by emad on 11/23/2016.
 */
public class RecentPostAdapter extends RecyclerView.Adapter<RecentPostAdapter.RecentItem>{
    public int POST_TYPE_AD=3, POST_TYPE_APP=2,POST_TYPE_CUSTOM=1;

    List<Item> items;
    Context context;

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

        if(true){
            View view=inflater.inflate(R.layout.recent_post_item,parent,false);
            return new RecentItemApp(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecentItem holder, int position) {
        RecentItemApp recentItemApp= (RecentItemApp) holder;
        recentItemApp.title.setText(items.get(position).getTitle());
        recentItemApp.appName.setText(items.get(position).getAppname());
        recentItemApp.vendor.setText(items.get(position).getVendor());
        /*switch (getItemViewType(position)){
            default:


                break;

        }*/
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

        }
    }

    private class RecentItemApp extends RecentItem{
        ImageView icon;
        CustomFontTextView appName,vendor,date,title;
        public RecentItemApp(View itemView) {
            super(itemView);
            appName= (CustomFontTextView) itemView.findViewById(R.id.recent_post_appname);
            vendor= (CustomFontTextView) itemView.findViewById(R.id.recent_post_vendor);
            date= (CustomFontTextView) itemView.findViewById(R.id.recent_post_date);
            title= (CustomFontTextView) itemView.findViewById(R.id.recent_post_title);
            icon= (ImageView) itemView.findViewById(R.id.recent_post_icon);
        }

        @Override
        public void onClick(View view) {
            super.onClick(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return POST_TYPE_APP;
    }
}
