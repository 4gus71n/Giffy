
package com.kimboo.giffy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    private AnimationDrawable progress;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreen);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                //TODO: Check if was a failure or not.
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
            
        }.execute();
        
    }

    
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animate();
    }

    private void animate(){
        // Load the ImageView that will host the animation and
        // set its background to our AnimationDrawable XML resource.
        ImageView img = (ImageView)findViewById(R.id.splash_animation);
        // Get the background, which has been compiled to an AnimationDrawable object.
        progress = (AnimationDrawable) img.getBackground();
        // Start the animation (looped playback by default).
        progress.start();
    }
    
}
