package com.negah.telescope.app.other;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.negah.telescope.app.R;
import com.negah.telescope.app.activities.SignupActivity;

/**
 * Created by emad on 6/29/2016.
 */
public class E {
    public static String PREF_NAME="telescopeSetting";
    public  static String PREF_FONT_KEY="TXTfont";
    private static Dialog loading;
    private static  E instance;
    Context context;

    public E(Context context) {
        this.context = context;
    }

    public  static E getInstance(Context context){
        if(instance==null){
            instance=new E(context);
        }
        return instance;
    }


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
    public static void RevealAnimationShow(View v,@Nullable int startFromX,@Nullable int startFromY){
        int cx=v.getRight()-30;
        int cy=v.getBottom()-60;
        int radius=Math.max(v.getWidth(),v.getHeight());
        Animator anim= ViewAnimationUtils.createCircularReveal(v,startFromX,startFromY,0,radius);
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
    public static int dpToPx(Context context,int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int pxToDp(Context context,int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static void showMessagePopUp(Context context,String message,String btnMsg){
        AlertDialog dialog= new AlertDialog.Builder(context)
                .setTitle("")
                .setMessage(context.getResources().getString(R.string.title_app_youShouldSignIn))
                .setCancelable(true)
                .setPositiveButton(btnMsg,null).show();
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        textView.setTypeface(E.geFont(context));

        TextView textView3 = (TextView) dialog.findViewById(android.R.id.button1);
        textView3.setTypeface(E.geFont(context));
    }

    public void ShowLoading(String title){
        if(loading==null){
            loading=new Dialog(context,R.style.AppTheme_Dialog);
            loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
            loading.setContentView(R.layout.loading);
        }
        TextView txt= (TextView) loading.findViewById(R.id.telescopeLoading_txt);
        txt.setText(title);
        loading.show();
    }

    public void DismissLoading(){
        if(loading==null){
            loading=new Dialog(context,R.style.AppTheme_Dialog);
        }
        loading.dismiss();
    }
    public boolean isLoading(){
        if(loading!=null){
            if (loading.isShowing()){
                return true;
            }
        }
        return false;
    }
    public void Clear(){
        loading=null;
    }

}
