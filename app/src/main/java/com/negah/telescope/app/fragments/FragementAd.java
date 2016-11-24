package com.negah.telescope.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.negah.telescope.app.R;
import com.squareup.picasso.Picasso;

/**
 * Created by emad on 11/23/2016.
 */
public class FragementAd extends Fragment {
    public static String URL_KEY="ad_image_url",POST_ID_KEY="ad_post_id",TITLE_KEY="ad_post_id";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // should dynamic height
        View rootView = inflater.inflate(R.layout.category_banner, container, false);
        ImageView banner= (ImageView) rootView.findViewById(R.id.bannerCategory_img);
        TextView title= (TextView) rootView.findViewById(R.id.bannerCategory_txt);
        Picasso.with(getContext()).load(getArguments().getString(URL_KEY)).into(banner);
        title.setText(getArguments().getString(TITLE_KEY));

        return  rootView;
    }

    public static FragementAd newInstance(String url,String postID,String title) {
        FragementAd fragment=new FragementAd();
        Bundle b = new Bundle();
        b.putString(URL_KEY,url);
        b.putString(POST_ID_KEY,postID);
        b.putString(TITLE_KEY,title);
        fragment.setArguments(b);

        return fragment;
    }

}
