package com.kimboo.giffy.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class GiffyButtonView extends Button {

    public GiffyButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    public GiffyButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public GiffyButtonView(Context context) {
        super(context);
        setFont();
    }

    private void setFont() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SFMoviePoster.ttf");
            setTypeface(tf);
        }
    }
    
    
    
    
}
