package com.negah.telescope.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.models.Category;
import com.negah.telescope.app.view.CustomFontTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by emad on 11/16/2016.
 */
public class BannerCategoriesAdapter extends RecyclerView.Adapter<BannerCategoriesAdapter.BannerCategoryViewHolder> {
    OnItemClickListener mItemClickListener;
    Context mContext;
    List<Category> items;

    public BannerCategoriesAdapter(List<Category> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }

    @Override
    public BannerCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.category_banner,parent,false);
        return new BannerCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BannerCategoryViewHolder holder, int position) {
        holder.title.setText(items.get(position).getCatName());
        Picasso.with(mContext).load(items.get(position).getBannerpath()).into(holder.banner);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  class BannerCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        RelativeLayout parent;
        CustomFontTextView title,owner;
        ImageView banner;
        public BannerCategoryViewHolder(View itemView) {
            super(itemView);
            parent= (RelativeLayout) itemView.findViewById(R.id.bannerCategory_parent);
            title= (CustomFontTextView) itemView.findViewById(R.id.bannerCategory_txt);
            owner= (CustomFontTextView) itemView.findViewById(R.id.bannerCategory_owner);
            banner= (ImageView) itemView.findViewById(R.id.bannerCategory_img);
            parent.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
