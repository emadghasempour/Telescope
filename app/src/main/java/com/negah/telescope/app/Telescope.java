package com.negah.telescope.app;

import android.app.Application;
import android.content.res.Configuration;

import com.negah.telescope.app.other.E;

import java.util.Locale;

import me.cheshmak.android.sdk.core.Cheshmak;
import me.cheshmak.android.sdk.core.CheshmakConfig;


/**
 * Created by emad on 11/22/2016.
 */
public class Telescope extends Application {
    public static boolean IS_USER_LOGED_IN=true;
    @Override
    public void onCreate() {
        super.onCreate();

        String languageToLoad  = "fa";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

       /* CheshmakConfig Cconfig= new CheshmakConfig();
        Cconfig.setIsEnableAutoActivityReports(false);
        Cconfig.setIsEnableExceptionReporting(true);
        Cheshmak.with(getApplicationContext(), Cconfig);

        Cheshmak.initTracker(getResources().getString(R.string.cheshmak_appkey));*/
    }
}
