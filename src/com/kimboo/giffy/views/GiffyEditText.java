package com.kimboo.giffy.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

public class GiffyEditText extends EditText {

    public GiffyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    public GiffyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public GiffyEditText(Context context) {
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
