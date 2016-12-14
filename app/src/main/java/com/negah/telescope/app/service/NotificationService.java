package com.negah.telescope.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by emad on 12/8/2016.
 */
public class NotificationService extends Service {

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if(intent.getStringExtra("me.cheshmak.data")!=null) {
            Log.d("CHESHMAK_POPP", "SERVICE STARTED" + intent.getStringExtra("me.cheshmak.data"));
            JSONObject object = null;
            try {
                object = new JSONObject(intent.getStringExtra("me.cheshmak.data"));
                String myOption = object.getString("MyKey");
                Log.d("CHESHMAK_POPP", "SERVICE STARTED" + myOption);
                Toast.makeText(getApplicationContext(), myOption, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
