package com.negah.telescope.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
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

        Typeface font1 = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.title.setTypeface(font1);

        holder.title.setText(object.getCategoryName());
        //imageLoader.DisplayImage(Config.SERVER_URL + "/upload/category/" + object.getCategoryImageurl(), holder.image);

        Picasso.with(getContext()).load(Config.SERVER_URL + "/upload/category/" +
                object.getCategoryImageurl()).placeholder(R.drawable.ic_thumbnail).into(holder.image);

        return view;

    }

    public class ViewHolder {

        public TextView title;
        public ImageView image;

    }

}
