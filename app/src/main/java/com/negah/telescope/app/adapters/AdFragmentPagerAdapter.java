package com.negah.telescope.app.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.negah.telescope.app.fragments.FragementAd;
import com.negah.telescope.app.models.AdBanner;
import com.negah.telescope.app.models.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emad on 11/23/2016.
 */
public class AdFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Banner> banners;
    public AdFragmentPagerAdapter(FragmentManager fm,List<Banner> ads) {
        super(fm);
        this.banners=ads;
    }

    @Override
    public Fragment getItem(int position) {
        return FragementAd.newInstance(banners.get(position).getBannerpath(),banners.get(position).getCatID(),
                banners.get(position).getCatName());
    }

    @Override
    public int getCount() {
        return banners.size();
    }
}
