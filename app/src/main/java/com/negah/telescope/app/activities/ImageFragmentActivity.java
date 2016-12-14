package com.negah.telescope.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.negah.telescope.app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by emad on 12/7/2016.
 */
public class ImageFragmentActivity extends FragmentActivity {
    public static  final String IMAGE_ARRAY_KEY="images";
    public static  String CURRENT_POSITION_KEY="currentPos";
    ImageFragmentPagerAdapter imageFragmentPagerAdapter;
    ViewPager viewPager;
    static ArrayList<String> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_fragment);
        items=getIntent().getExtras().getStringArrayList(IMAGE_ARRAY_KEY);
        imageFragmentPagerAdapter = new ImageFragmentPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.image_fragment_pager);
        viewPager.setAdapter(imageFragmentPagerAdapter);
        int currentItem=getIntent().getExtras().getInt(CURRENT_POSITION_KEY);
        viewPager.setCurrentItem(currentItem,true);
    }

    public static class ImageFragmentPagerAdapter extends FragmentPagerAdapter {



        public ImageFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Fragment getItem(int position) {
            SwipeFragment fragment = new SwipeFragment();
            return SwipeFragment.newInstance(position);
        }
    }

    public static class SwipeFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View swipeView = inflater.inflate(R.layout.image_fragment_item, container, false);
            ImageView imageView = (ImageView) swipeView.findViewById(R.id.image_fragment_item_imageView);
            Bundle bundle = getArguments();
            int position = bundle.getInt("position");
            //String imageFileName = "";//IMAGE_NAME[position];
            //int imgResId = getResources().getIdentifier(imageFileName, "drawable", "com.negah.telescope.app.activities.ImageFragmentActivity");
            ///imageView.setImageResource(imgResId);
            Picasso.with(getContext()).load(items.get(position)).into(imageView);
            return swipeView;
        }

        static SwipeFragment newInstance(int position) {
            SwipeFragment swipeFragment = new SwipeFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);

            swipeFragment.setArguments(bundle);
            return swipeFragment;
        }
    }
}
