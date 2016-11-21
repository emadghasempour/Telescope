package com.negah.telescope.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.negah.telescope.app.other.E;

/**
 * Created by emad on 11/21/2016.
 */
public class CustomEditText extends EditText {
    public CustomEditText(Context context) {
        super(context);
        setIranTypeFace(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setIranTypeFace(context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
