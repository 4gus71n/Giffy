package com.kimboo.giffy.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class GiffyTextView extends TextView {

    public GiffyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    public GiffyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    private void setFont() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SFMoviePoster.ttf");
            setTypeface(tf);
        }
    }

    public GiffyTextView(Context context) {
        super(context);
        setFont();
    }

    
    
}
