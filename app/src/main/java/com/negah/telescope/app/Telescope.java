package com.negah.telescope.app;

import android.app.Application;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by emad on 11/22/2016.
 */
public class Telescope extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String languageToLoad  = "fa"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}
