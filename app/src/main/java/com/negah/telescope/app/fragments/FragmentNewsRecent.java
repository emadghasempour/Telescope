package com.negah.telescope.app.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.negah.telescope.app.R;
import com.negah.telescope.app.activities.AppDetailsActivity;
import com.negah.telescope.app.activities.PostDetailActivity;
import com.negah.telescope.app.adapters.AdFragmentPagerAdapter;
import com.negah.telescope.app.adapters.AdapterRecent;
import com.negah.telescope.app.adapters.RecentPostAdapter;
import com.negah.telescope.app.json.JsonConfig;
import com.negah.telescope.app.json.JsonUtils;
import com.negah.telescope.app.models.ItemRecent;
import com.negah.telescope.app.other.E;
import com.negah.telescope.app.other.TelescopeEvents;
import com.negah.telescope.app.services.Network;
import com.negah.telescope.app.services.api.APIs;
import com.negah.telescope.app.services.lists.TelescopeBanner;
import com.negah.telescope.app.services.lists.TelescopeRecent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.cheshmak.android.sdk.core.Cheshmak;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNewsRecent extends Fragment {

    ListView listView;
    ViewPager adViewPager;
    List<ItemRecent> list;
    RecyclerView recyclerView;
    AdapterRecent adapter;
    ArrayList<String> array_news, array_news_cat_name, array_cid, array_cat_id, array_cat_name, array_title, array_image, array_desc, array_date;
    String[] str_news, str_news_cat_name, str_cid, str_cat_id, str_cat_name, str_title, str_image, str_desc, str_date;
    ItemRecent object;
    JsonUtils util;
    int textlength = 0;
    SwipeRefreshLayout swipeRefreshLayout = null;
    ProgressBar progressBar;
    //private InterstitialAd interstitial;
    String TAG=FragmentNewsRecent.class.getSimpleName();
    private RecentPostAdapter recentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recent, container, false);
        setHasOptionsMenu(true);
        adViewPager= (ViewPager) v.findViewById(R.id.topad_viewpager);
        listView = (ListView) v.findViewById(R.id.lsv_latest);
        recyclerView= (RecyclerView) v.findViewById(R.id.rcyclr_latest);
        int columns=getContext().getResources().getInteger(R.integer.latest_column_count);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),columns);
        recyclerView.setLayoutManager(gridLayoutManager);

        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue, R.color.red);

        list = new ArrayList<ItemRecent>();
        array_news = new ArrayList<String>();
        array_news_cat_name = new ArrayList<String>();
        array_cid = new ArrayList<String>();
        array_cat_id = new ArrayList<String>();
        array_cat_name = new ArrayList<String>();
        array_title = new ArrayList<String>();
        array_image = new ArrayList<String>();
        array_desc = new ArrayList<String>();
        array_date = new ArrayList<String>();

        str_news = new String[array_news.size()];
        str_news_cat_name = new String[array_news_cat_name.size()];
        str_cid = new String[array_cid.size()];
        str_cat_id = new String[array_cat_id.size()];
        str_cat_name = new String[array_cat_name.size()];
        str_title = new String[array_title.size()];
        str_image = new String[array_image.size()];
        str_desc = new String[array_desc.size()];
        str_date = new String[array_date.size()];

        util = new JsonUtils(this.getActivity());

        if (JsonUtils.isNetworkAvailable(getActivity())) {
            E.getInstance(getContext()).ShowLoading(getResources().getString(R.string.pleaseWait));
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.failed_connect_network), Toast.LENGTH_SHORT).show();
        }

        // Using to refresh webpage when user swipes the screen
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       /* swipeRefreshLayout.setRefreshing(false);
                        adapter.clear();
                        new RefreshTask().execute(Config.SERVER_URL + "/api.php?latest_news=70");*/


                    }
                }, 3000);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if (listView != null && listView.getChildCount() > 0) {
                    boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                    boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipeRefreshLayout.setEnabled(enable);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                /*object = list.get(position);
                int pos = Integer.parseInt(object.getCatId());

                Intent intplay = new Intent(getActivity(), ActivityNewsDetail.class);
                intplay.putExtra("POSITION", pos);
                JsonConfig.NEWS_ITEMID = object.getCatId();
               // Intent intplay = new Intent(getActivity(), PostActivity.class);
                Log.d("position1","detailed");
                startActivity(intplay);*/
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent i2=new Intent(getActivity(),AppDetailsActivity.class);
                getActivity().startActivity(i2);
            }
        });



        APIs apIs= Network.getRetrofit().create(APIs.class);
        Call<TelescopeBanner> call =apIs.loadFullBanners();
        adViewPager.setVisibility(View.GONE);
        call.enqueue(new Callback<TelescopeBanner>() {
            @Override
            public void onResponse(Call<TelescopeBanner> call, Response<TelescopeBanner> response) {
                Log.d(TAG,"" + response.body().banners.size());
                if(true) { //TODO CHECK IF ANY TOP AD AVALIBLE...
                    adViewPager.setVisibility(View.GONE);
                }else{
                    AdFragmentPagerAdapter pagerAdapter=new AdFragmentPagerAdapter(getActivity().getSupportFragmentManager()
                            ,response.body().banners);
                    adViewPager.setAdapter(pagerAdapter);
                }
            }
            @Override
            public void onFailure(Call<TelescopeBanner> call, Throwable t) {
                Log.d(TAG,"FAILED");
            }
        });

        Call<TelescopeRecent> calllatest =apIs.loadRecent();
        calllatest.enqueue(new Callback<TelescopeRecent>() {
            @Override
            public void onResponse(Call<TelescopeRecent> call, final Response<TelescopeRecent> response) {
                recentAdapter=new RecentPostAdapter(response.body().items,getContext());
                recentAdapter.setOnItemClickListener(new RecentPostAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if(response.body().items.get(position).getPostType()==RecentPostAdapter.POST_TYPE_APP) {
                            Intent i2 = new Intent(getActivity(), AppDetailsActivity.class);
                            getActivity().startActivity(i2);
                        }
                        else if(response.body().items.get(position).getPostType()==RecentPostAdapter.POST_TYPE_CUSTOM){
                            Intent i2 = new Intent(getActivity(), PostDetailActivity.class);
                            getActivity().startActivity(i2);
                        }
                        else if(response.body().items.get(position).getPostType()==RecentPostAdapter.POST_TYPE_AD){
                            Cheshmak.trackEvent(TelescopeEvents.M_ClLICK_Ad_EVENT);
                        }

                    }
                });
                recyclerView.setAdapter(recentAdapter);
                if(E.getInstance(getContext()).isLoading()){
                    E.getInstance(getContext()).DismissLoading();
                }
                E.getInstance(getContext()).ShowLoading(getResources().getString(R.string.pleaseWait));
            }

            @Override
            public void onFailure(Call<TelescopeRecent> call, Throwable t) {
                Log.e(TAG,"FAILED"+t.getLocalizedMessage());
            }
        });


       /* Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://www.negahgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIs apIs=retrofit.create(APIs.class);
        Call<PostDetail> call =apIs.loadPostDetails("12");*/
        /*Call<TelescopeRecent> call=apIs.loadRecent();
        call.enqueue(new Callback<TelescopeRecent>() {
            @Override
            public void onResponse(Call<TelescopeRecent> call, Response<TelescopeRecent> response) {
                Log.d(TAG,"" + response.body().items.size());
                Log.d(TAG,""+response.body().items.toString());
            }

            @Override
            public void onFailure(Call<TelescopeRecent> call, Throwable t) {
                Log.d(TAG+"fail",t.getLocalizedMessage());
            }
        });*/
        /*call.enqueue(new Callback<TelescopeBanner>() {
            @Override
            public void onResponse(Call<TelescopeBanner> call, Response<TelescopeBanner> response) {
                Log.d(TAG,"" + response.body().banners.size());
                Log.d(TAG,""+response.body().banners.toString());

            }

            @Override
            public void onFailure(Call<TelescopeBanner> call, Throwable t) {

            }
        });*/

       /* call.enqueue(new Callback<PostDetail>() {
            @Override
            public void onResponse(Call<PostDetail> call, Response<PostDetail> response) {
                Log.d(TAG,"SUCCESS");
                Log.d(TAG,response.body().getVendor());
            }

            @Override
            public void onFailure(Call<PostDetail> call, Throwable t) {
                Log.d(TAG,"FAILED " +t.getLocalizedMessage() );
            }
        });*/

        return v;
    }

    private class MyTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getResources().getString(R.string.loading));
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (null != progressDialog && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            if (null == result || result.length() == 0) {
                Toast.makeText(getActivity(), getResources().getString(R.string.failed_connect_network), Toast.LENGTH_SHORT).show();
            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(JsonConfig.CATEGORY_ARRAY_NAME);
                    JSONObject objJson = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);

                        ItemRecent objItem = new ItemRecent();

                        objItem.setCId(objJson.getString(JsonConfig.CATEGORY_ITEM_CID));
                        objItem.setCategoryName(objJson.getString(JsonConfig.CATEGORY_ITEM_NAME));
                        objItem.setCatId(objJson.getString(JsonConfig.CATEGORY_ITEM_CAT_ID));
                        objItem.setNewsImage(objJson.getString(JsonConfig.CATEGORY_ITEM_NEWSIMAGE));
                        objItem.setNewsHeading(objJson.getString(JsonConfig.CATEGORY_ITEM_NEWSHEADING));
                        objItem.setNewsDescription(objJson.getString(JsonConfig.CATEGORY_ITEM_NEWSDESCRI));
                        objItem.setNewsDate(objJson.getString(JsonConfig.CATEGORY_ITEM_NEWSDATE));

                        list.add(objItem);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < list.size(); j++) {

                    object = list.get(j);

                    array_cat_id.add(object.getCatId());
                    str_cat_id = array_cat_id.toArray(str_cat_id);

                    array_cat_name.add(object.getCategoryName());
                    str_cat_name = array_cat_name.toArray(str_cat_name);

                    array_cid.add(String.valueOf(object.getCId()));
                    str_cid = array_cid.toArray(str_cid);

                    array_image.add(String.valueOf(object.getNewsImage()));
                    str_image = array_image.toArray(str_image);

                    array_title.add(String.valueOf(object.getNewsHeading()));
                    str_title = array_title.toArray(str_title);

                    array_desc.add(String.valueOf(object.getNewsDescription()));
                    str_desc = array_desc.toArray(str_desc);

                    array_date.add(String.valueOf(object.getNewsDate()));
                    str_date = array_date.toArray(str_date);

                }

                setAdapterToListView();
            }

        }
    }

    private class RefreshTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressBar.setVisibility(View.GONE);

            if (null == result || result.length() == 0) {
                Toast.makeText(getActivity(), getResources().getString(R.string.failed_connect_network), Toast.LENGTH_SHORT).show();
            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(JsonConfig.CATEGORY_ARRAY_NAME);
                    JSONObject objJson = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);

                        ItemRecent objItem = new ItemRecent();

                        objItem.setCId(objJson.getString(JsonConfig.CATEGORY_ITEM_CID));
                        objItem.setCategoryName(objJson.getString(JsonConfig.CATEGORY_ITEM_NAME));
                        objItem.setCatId(objJson.getString(JsonConfig.CATEGORY_ITEM_CAT_ID));
                        objItem.setNewsImage(objJson.getString(JsonConfig.CATEGORY_ITEM_NEWSIMAGE));
                        objItem.setNewsHeading(objJson.getString(JsonConfig.CATEGORY_ITEM_NEWSHEADING));
                        objItem.setNewsDescription(objJson.getString(JsonConfig.CATEGORY_ITEM_NEWSDESCRI));
                        objItem.setNewsDate(objJson.getString(JsonConfig.CATEGORY_ITEM_NEWSDATE));

                        list.add(objItem);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < list.size(); j++) {

                    object = list.get(j);

                    array_cat_id.add(object.getCatId());
                    str_cat_id = array_cat_id.toArray(str_cat_id);

                    array_cat_name.add(object.getCategoryName());
                    str_cat_name = array_cat_name.toArray(str_cat_name);

                    array_cid.add(String.valueOf(object.getCId()));
                    str_cid = array_cid.toArray(str_cid);

                    array_image.add(String.valueOf(object.getNewsImage()));
                    str_image = array_image.toArray(str_image);

                    array_title.add(String.valueOf(object.getNewsHeading()));
                    str_title = array_title.toArray(str_title);

                    array_desc.add(String.valueOf(object.getNewsDescription()));
                    str_desc = array_desc.toArray(str_desc);

                    array_date.add(String.valueOf(object.getNewsDate()));
                    str_date = array_date.toArray(str_date);

                }

                setAdapterToListView();
            }
        }
    }

    public void setAdapterToListView() {
        adapter = new AdapterRecent(getActivity(), R.layout.lsv_item_news_list, list);
        listView.setAdapter(adapter);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        //searchView.setQueryHint(getString(R.string.app_name));

        final MenuItem searchMenuItem = menu.findItem(R.id.search);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (!hasFocus) {
                            searchMenuItem.collapseActionView();
                            searchView.setQuery("", false);
                        }
                    }
                });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

                textlength = newText.length();
                list.clear();

                for (int i = 0; i < str_title.length; i++) {
                    if (textlength <= str_title[i].length()) {
                        if (str_title[i].toLowerCase().contains(newText.toLowerCase())) {

                            ItemRecent objItem = new ItemRecent();

                            objItem.setCategoryName((str_cat_name[i]));
                            objItem.setCatId(str_cat_id[i]);
                            objItem.setCId(str_cid[i]);
                            objItem.setNewsDate(str_date[i]);
                            objItem.setNewsDescription(str_desc[i]);
                            objItem.setNewsHeading(str_title[i]);
                            objItem.setNewsImage(str_image[i]);
                            list.add(objItem);
                        }
                    }
                }

                setAdapterToListView();
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:

                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
