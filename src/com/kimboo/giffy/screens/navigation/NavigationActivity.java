
package com.kimboo.giffy.screens.navigation;

import com.kimboo.giffy.R;
import com.kimboo.giffy.R.layout;
import com.kimboo.giffy.views.GifDecoder;
import com.kimboo.giffy.views.GifDecoderView;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;

public class NavigationActivity extends FragmentActivity {
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_navigation);
        FrameLayout container = (FrameLayout) findViewById(R.id.gif_container);
        GifDecoderView gif = new GifDecoderView(this);
        gif.setScaleType(ScaleType.FIT_XY);
        container.addView(gif);
        findViewById(R.id.navigation_main_frame).bringToFront();
        gif.playGif("fry.gif");
        container.requestLayout();
    }
}
