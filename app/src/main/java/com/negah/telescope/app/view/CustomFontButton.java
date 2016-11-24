package com.negah.telescope.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.negah.telescope.app.other.E;

/**
 * Created by emad on 11/22/2016.
 */
public class CustomFontButton extends Button {
    public CustomFontButton(Context context) {
        super(context);
        setIranTypeFace(context);
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setIranTypeFace(context);
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setIranTypeFace(context);
    }

    private void setIranTypeFace(Context context) {
        if (!isInEditMode()) {
            Typeface font = E.geFont(context);
            setTypeface(font);
        }

    }
}
