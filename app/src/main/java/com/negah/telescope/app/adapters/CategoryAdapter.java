package com.negah.telescope.app.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.models.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by emad on 11/26/2016.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private OnItemClickListener mItemClickListener;
    List<Category> items;
    Context context;

    public CategoryAdapter(Context context, List<Category> items) {
        this.context = context;
        this.items = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.lsv_item_category,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getName());
        Picasso.with(context).load(items.get(position).getImagePath()).into(holder.image);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView title;
        public TextView date;
        CardView parent;

        public ViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.category_image);
            title= (TextView) itemView.findViewById(R.id.category_title);
            parent= (CardView) itemView.findViewById(R.id.category_item_parent_layout);
            parent.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView,getPosition());
            }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
