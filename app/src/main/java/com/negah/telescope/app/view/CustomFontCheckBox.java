package com.negah.telescope.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.negah.telescope.app.other.E;

/**
 * Created by emad on 11/20/2016.
 */
public class CustomFontCheckBox extends CheckBox {
    public CustomFontCheckBox(Context context) {
        super(context);
        setIranTypeFace(context);
    }

    public CustomFontCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setIranTypeFace(context);
    }

    public CustomFontCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
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
