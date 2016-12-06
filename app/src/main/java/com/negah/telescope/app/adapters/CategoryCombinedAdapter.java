package com.negah.telescope.app.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.models.Banner;
import com.negah.telescope.app.models.Category;
import com.negah.telescope.app.view.CustomFontTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emad on 11/30/2016.
 */
public class CategoryCombinedAdapter extends RecyclerView.Adapter<CategoryCombinedAdapter.CategoryItem>{

    public final int TYPE_CAT=1, TYPE_BANENR=2;
    private OnItemClickListener mItemClickListener;
    List<Object> items;
    Context context;
    public CategoryCombinedAdapter(Context context,List<Object> items) {
        this.context=context;
        this.items=items;
    }

    @Override
    public CategoryItem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType){
            case TYPE_CAT:
                 view=inflater.inflate(R.layout.lsv_item_category,parent,false);
                return new CategoryViewHolder(view);
            case TYPE_BANENR:
                 view=inflater.inflate(R.layout.category_banner,parent,false);
                return new BannerViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(CategoryItem holder, int position) {
        int type=getItemViewType(position);
        switch (type){
            case TYPE_CAT:
                CategoryViewHolder categoryItem= (CategoryViewHolder) holder;
                Category category= (Category) items.get(position);
                categoryItem.title.setText(category.getName());
                Picasso.with(context).load(category.getImagePath()).into(categoryItem.image);
                break;
            case TYPE_BANENR:
                BannerViewHolder bannerViewHolder= (BannerViewHolder) holder;
                Banner banner= (Banner) items.get(position);
                bannerViewHolder.title.setText(banner.getCatName());
                Picasso.with(context).load(banner.getBannerpath()).into(bannerViewHolder.banner);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position) instanceof Category){
            return TYPE_CAT;
        }
        else if(items.get(position) instanceof Banner){
            return TYPE_BANENR;
        }
        else return 0;
    }

    public class CategoryItem extends RecyclerView.ViewHolder implements View.OnClickListener{


        public CategoryItem(View itemView) {
            super(itemView);

        }
        @Override
        public void onClick(View view) {

            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView,getPosition());
            }
        }
    }




    public class CategoryViewHolder extends CategoryItem{

        public ImageView image;
        public TextView title;
        public TextView date;
        CardView parent;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.category_image);
            title= (TextView) itemView.findViewById(R.id.category_title);
            parent= (CardView) itemView.findViewById(R.id.category_item_parent_layout);
            parent.setOnClickListener(this);

        }


    }

    public  class BannerViewHolder extends CategoryItem{

        RelativeLayout parent;
        CustomFontTextView title,owner;
        ImageView banner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            parent= (RelativeLayout) itemView.findViewById(R.id.bannerCategory_parent);
            title= (CustomFontTextView) itemView.findViewById(R.id.bannerCategory_txt);
            owner= (CustomFontTextView) itemView.findViewById(R.id.bannerCategory_owner);
            banner= (ImageView) itemView.findViewById(R.id.bannerCategory_img);
            parent.setOnClickListener(this);
        }


    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
