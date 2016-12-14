package com.negah.telescope.app.activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.negah.telescope.app.R;
import com.negah.telescope.app.fragments.FragmentFavorite;
import com.negah.telescope.app.fragments.TabFragment;
import com.negah.telescope.app.other.E;
import com.negah.telescope.app.other.TelescopeEvents;
import com.negah.telescope.app.preferences.SettingsActivity;
import com.negah.telescope.app.utilities.ShakeListener;

import me.cheshmak.android.sdk.core.Cheshmak;


public class MainActivity extends AppCompatActivity{

    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    SharedPreferences preferences;
    ShakeListener mShaker;
    View navigationHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StartAppAd.init(this, getString(R.string.startapp_account_id), getString(R.string.startapp_app_id, false));
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_LTR){
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }*/
        setContentView(R.layout.activity_main);
        E.getInstance(this);
        /*OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                String text = "OneSignal UserID:\n" + userId + "\n\n";

                if (registrationId != null)
                    text += "Google Registration Id:\n" + registrationId;
                else
                    text += "Google Registration Id:\nCould not subscribe for push";

                TextView textView = (TextView)findViewById(R.id.debug_view);
                textView.setText(text);
            }
        });*/

        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }

        //show admob banner ad
       /* mAdView = (AdView) findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().build());
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
            }

            @Override
            public void onAdFailedToLoad(int error) {
                mAdView.setVisibility(View.GONE);
            }

            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }
        });*/

//        startAppAd.loadAd(new AdEventListener() {
//            @Override
//            public void onReceiveAd(Ad arg0) {
//                startAppAd.showAd();
//                startAppAd.loadAd();
//            }
//
//            @Override
//            public void onFailedToReceiveAd(Ad arg0) {
//            }
//        });
       /* navigationHeader=findViewById(R.id.na_header_container);
        navigationHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });*/
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mNavigationView = (NavigationView) findViewById(R.id.main_drawer) ;
        navigationHeader=mNavigationView.getHeaderView(0);
        navigationHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.frame_container, new TabFragment()).commit();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                //setTitle(menuItem.getTitle());

                if (menuItem.getItemId() == R.id.drawer_home) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, new TabFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.drawer_favorite) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container, new FragmentFavorite()).commit();
                }

                if (menuItem.getItemId() == R.id.drawer_rate) {
                    final String appName = getPackageName();
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appName)));
                    } catch (ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appName)));
                    }
                }

                if (menuItem.getItemId() == R.id.drawer_more) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.play_more_apps))));
                }

                if (menuItem.getItemId() == R.id.drawer_setting) {
                    Intent i = new Intent(getBaseContext(), SettingsActivity.class);
                    startActivity(i);
                }

                return false;
            }

        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                if (item != null && item.getItemId() == android.R.id.home) {
                    if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                        mDrawerLayout.closeDrawer(Gravity.RIGHT);
                    }
                    else {
                        mDrawerLayout.openDrawer(Gravity.RIGHT);
                    }
                }
                return false;
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        // init analytics tracker
      //  ((Analytics) getApplication()).getTracker();
        mShaker = new ShakeListener(this);
        mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
            public void onShake()
            {
                final Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(100);
                Cheshmak.trackEvent(TelescopeEvents.SHAKE_EVENT);
                Toast.makeText(MainActivity.this,"shake",Toast.LENGTH_SHORT).show();
            }
        });


        /*setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationIcon(R.drawable.telescopelogo);
        toolbar.getNavigationIcon().setTint(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
            }
        });*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
//            startAppAd.loadAd(new AdEventListener() {
//                @Override
//                public void onReceiveAd(Ad arg0) {
//                    startAppAd.showAd();
//                    startAppAd.loadAd();
//                }
//
//                @Override
//                public void onFailedToReceiveAd(Ad arg0) {
//                }
//            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // analytics
        //GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        // analytics
       // GoogleAnalytics.getInstance(this).reportActivityStop(this);
    }

    @Override
    protected void onPause() {
        //mAdView.pause();
        mShaker.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mShaker.resume();
        super.onResume();
       // mAdView.resume();
    }

    @Override
    protected void onDestroy() {
        //mAdView.destroy();
        E.getInstance(this).Clear();
        super.onDestroy();
    }


}