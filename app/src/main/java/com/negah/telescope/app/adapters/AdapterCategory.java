package com.negah.telescope.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.negah.telescope.app.Config;
import com.negah.telescope.app.R;
import com.negah.telescope.app.models.ItemCategory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCategory extends ArrayAdapter<ItemCategory> {

    private Activity activity;
    private List<ItemCategory> item;
    ItemCategory object;
    private int row;
    //public ImageLoader imageLoader;
    String[] color={"#E64A19","#512DA8","#E91E63","#FFC107","#00bcd4","#4caf50"};

    public AdapterCategory(Activity act, int resource, List<ItemCategory> arrayList) {
        super(act, resource, arrayList);
        this.activity = act;
        this.row = resource;
        this.item = arrayList;
        //imageLoader = new ImageLoader(activity);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);
            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if ((item == null) || ((position + 1) > item.size()))
            return view;

        object = item.get(position);

        holder.title = (TextView) view.findViewById(R.id.category_title);
        holder.image = (ImageView) view.findViewById(R.id.category_image);
        holder.parent= (CardView) view.findViewById(R.id.category_item_parent_layout);
        Typeface font1 = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.title.setTypeface(font1);

        holder.title.setText(object.getCategoryName());
        //imageLoader.DisplayImage(Config.SERVER_URL + "/upload/category/" + object.getCategoryImageurl(), holder.image);

        Picasso.with(getContext()).load(Config.SERVER_URL + "/upload/category/" +
                object.getCategoryImageurl()).placeholder(R.drawable.ic_thumbnail).into(holder.image);
       // Bitmap photo= BitmapFactory.decodeResource(activity.getResources(),holder.im)
        holder.parent.setBackgroundColor(Color.parseColor(color[position]));

        return view;

    }

    public class ViewHolder {

        public TextView title;
        public ImageView image;
        public CardView parent;

    }

}
