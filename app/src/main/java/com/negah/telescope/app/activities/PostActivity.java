package com.negah.telescope.app.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.negah.telescope.app.R;

/**
 * Created by emad on 11/19/2016.
 */
public class PostActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_page);
        Log.d("place","post");


    }
}
