package com.negah.telescope.app.other;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by emad on 6/29/2016.
 */
public class E {
    public static String PREF_NAME="telescopeSetting";
    public  static String PREF_FONT_KEY="TXTfont";
    public static Typeface geFont(Context context){
        SharedPreferences pref=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        if(!pref.contains(PREF_FONT_KEY)){
            pref.edit().putString(PREF_FONT_KEY,"IRANSansMobile.ttf").commit();
        }
        String fontName=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE).getString(PREF_FONT_KEY,"IRANSansMobile.ttf");
        return Typeface.createFromAsset(context.getAssets(),"fonts/"+fontName);
    }

    public static void changeBrightness(Context context,int brighness){
        Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        Settings.System.putInt(context.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,brighness);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void RevealAnimationShow(View v){
        int cx=v.getRight()-30;
        int cy=v.getBottom()-60;
        int radius=Math.max(v.getWidth(),v.getHeight());
        Animator anim= ViewAnimationUtils.createCircularReveal(v,cx,cy,0,radius);
        v.setVisibility(View.VISIBLE);
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void RevealAnimationHide(final View v){
        int cx=v.getRight()-30;
        int cy=v.getBottom()-60;
        int radius=Math.max(v.getWidth(),v.getHeight());
        Animator anim= ViewAnimationUtils.createCircularReveal(v,cx,cy,radius,0);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                v.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        anim.start();
    }


}
