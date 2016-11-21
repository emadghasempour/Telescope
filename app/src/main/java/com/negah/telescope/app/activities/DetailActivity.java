package com.negah.telescope.app.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.negah.telescope.app.R;

/**
 * Created by emad on 11/19/2016.
 */
public class DetailActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        Log.d("position","detailed");
        super.onCreate(savedInstanceState, persistentState);

        setContentView(R.layout.test);

    }
}
