package com.negah.telescope.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.negah.telescope.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by emad on 11/26/2016.
 */
public class ScreenShotsAdapter extends RecyclerView.Adapter<ScreenShotsAdapter.ScreenShotItem> {
    public final int TYPE_IMG=1,TYPE_VIDEO=2;
   List<String> items;
    Context context;
    private OnItemClickListener mItemClickListener;

    public ScreenShotsAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ScreenShotItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.screenshots_item,parent,false);

        return new ScreenShotItem(view);
    }

    @Override
    public void onBindViewHolder(ScreenShotItem holder, int position) {
        Picasso.with(context).load(items.get(position)).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ScreenShotItem extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView image;
    public ScreenShotItem(View itemView) {
        super(itemView);
        image= (ImageView) itemView.findViewById(R.id.screen_image);
        image.setOnClickListener(this);
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
