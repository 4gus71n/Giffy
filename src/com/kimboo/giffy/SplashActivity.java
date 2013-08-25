
package com.kimboo.giffy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.kimboo.giffy.utils.Constants;

public class SplashActivity extends Activity {

    private AnimationDrawable mProgressAnimation;

    private int mDstHeight;
    private int mDstWidth;
    private WindowManager mWinMgr;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (App.getInstance().userLogin()) {
            launchBackgroundLoading();
        }
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        beginAnimationStatic();
    }

    private void beginAnimationStatic() {
        ImageView img = (ImageView) findViewById(R.id.splash_animation_static);
        if (mProgressAnimation != null)
            mProgressAnimation.stop();
        mProgressAnimation = (AnimationDrawable) img.getBackground();
        mProgressAnimation.start();
    }
    
    private void beginAnimationLoading() {
        ImageView img = (ImageView) findViewById(R.id.splash_animation);
        if (mProgressAnimation != null)
            mProgressAnimation.stop();
        mProgressAnimation = (AnimationDrawable) img.getBackground();
        mProgressAnimation.start();
    }
    
    private void startBackgroundLoading() {
        //TODO: Do background init operations here
        downloadRecentGif();
    }

    private void endBackgroundLoading() {
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();
    }

    private void setupResources() {
        setupDeviceWidth();
    }
    
    private void downloadRecentGif() {
        // TODO Auto-generated method stub
        
    }

    private void setupDeviceWidth() {
        mWinMgr = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mDstWidth = mWinMgr.getDefaultDisplay().getWidth();
        mDstHeight = mWinMgr.getDefaultDisplay().getHeight();
        App.getInstance().putInt(Constants.System.DEVICE_WIDTH,mDstWidth);
        App.getInstance().putInt(Constants.System.DEVICE_HEIGHT,mDstHeight);
        
    }

}
