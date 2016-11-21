package com.negah.telescope.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.negah.telescope.app.other.E;



/**
 * Created by emad on 7/12/2016.
 */
public class CustomFontTextView extends TextView {
    public CustomFontTextView(Context context) {
        super(context);
        setIranTypeFace(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setIranTypeFace(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
