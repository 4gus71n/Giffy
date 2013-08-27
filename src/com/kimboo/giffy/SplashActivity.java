
package com.kimboo.giffy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.ViewSwitcher;

import com.kimboo.giffy.utils.Constants;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class SplashActivity extends Activity {

    private static final String TAG = SplashActivity.class.getName();

    /**
     * Animation to display in the tv.
     */
    private AnimationDrawable mProgressAnimation;

    /**
     * Device physic height and width.
     */
    private int mDstHeight;
    private int mDstWidth;
    private WindowManager mWinMgr;
    
    /**
     * OnClick triggered when you click in the {@linkplain R.id.splash_init_anonymous_btn}  button.
     */
    private final OnClickListener mLoginAnonymousClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            hideFilmStripMenu();
            beginAnimationLoading();
            //Dont do anything of UI inside this method nor the AsyncTask methods-
            launchBackgroundLoading();
        }
    };
    
    /**
     * OnClick triggered when you click in the {@linkplain R.id.splash_init_session_btn}  button.
     */
    private final OnClickListener mLoginUserClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            hideFilmStripMenu();
            beginAnimationLoading();
            //Dont do anything of UI inside this method nor the AsyncTask methods-
            launchBackgroundLoading();
        }
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);
        initWidgets();
    }
    
    protected void hideFilmStripMenu() {
        final ScrollView filmStripView = (ScrollView) findViewById(R.id.splash_movie_strip);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
            ObjectAnimator.ofFloat(filmStripView, "translationX", 0, filmStripView.getWidth()),
            ObjectAnimator.ofFloat(filmStripView, "alpha", 1, 0.25f)
        );
        set.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}
            @Override
            public void onAnimationRepeat(Animator animation) {}
            @Override
            public void onAnimationEnd(Animator animation) {
                filmStripView.setVisibility(View.GONE);
            }
            @Override
            public void onAnimationCancel(Animator animation) {
                filmStripView.setVisibility(View.GONE);
            }
        });
        set.setDuration(3 * 1000).start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (App.getInstance().userLogin()) {
            launchBackgroundLoading();
        }
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        beginAnimationStatic();
    }

    /**
     * Initializes the onClick and view events.
     */
    private void initWidgets() {
        Button loginAnonymous = (Button) findViewById(R.id.splash_init_anonymous_btn);
        loginAnonymous.setOnClickListener(mLoginAnonymousClick);
        Button loginUser = (Button) findViewById(R.id.splash_init_session_btn);
        loginUser.setOnClickListener(mLoginUserClick);
    }

    private void launchBackgroundLoading() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                startBackgroundLoading();
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                endBackgroundLoading();
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (App.getInstance().firstInstallation()) {
                    setupResources();
                    App.getInstance().firstInstallation(Boolean.FALSE);
                }
                
            }

        }.execute();
    }
    
    private void startBackgroundLoading() {
        //TODO: Do background init operations here
    }

    private void endBackgroundLoading() {
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();
    }

    private void setupResources() {
        setupDeviceWidth();
    }

    private void setupDeviceWidth() {
        mWinMgr = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mDstWidth = mWinMgr.getDefaultDisplay().getWidth();
        mDstHeight = mWinMgr.getDefaultDisplay().getHeight();
        App.getInstance().putInt(Constants.System.DEVICE_WIDTH,mDstWidth);
        App.getInstance().putInt(Constants.System.DEVICE_HEIGHT,mDstHeight);
        
    }
    
    private void beginAnimationStatic() {
        ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.splash_switcher);
        if (switcher.getDisplayedChild() != 0) {
            switcher.showPrevious(); 
        }
        ImageView img = (ImageView) findViewById(R.id.splash_animation_static);
        if (mProgressAnimation != null)
            mProgressAnimation.stop();
        mProgressAnimation = (AnimationDrawable) img.getBackground();
        mProgressAnimation.start();
    }
    
    private void beginAnimationLoading() {
        ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.splash_switcher);
        if (switcher.getDisplayedChild() != 1) {
            switcher.showNext(); 
        }
        ImageView img = (ImageView) findViewById(R.id.splash_animation);
        if (mProgressAnimation != null)
            mProgressAnimation.stop();
        mProgressAnimation = (AnimationDrawable) img.getBackground();
        mProgressAnimation.start();
    }

}
