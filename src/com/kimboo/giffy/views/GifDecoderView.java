
package com.kimboo.giffy.views;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.ImageView;

import com.kimboo.giffy.R;

public class GifDecoderView extends ImageView {

    private boolean mIsPlayingGif = false;

    private GifDecoder mGifDecoder;

    private Bitmap mTmpBitmap;

    final Handler mHandler = new Handler();

    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            if (mTmpBitmap != null && !mTmpBitmap.isRecycled()) {
                GifDecoderView.this.setImageBitmap(mTmpBitmap);
            }
        }
    };


    private String filepath;

    private InputStream stream;


    public GifDecoderView(Context context) {
        super(context);
    }

    public void playGif(String filepath) {
        setFilepath(filepath);
        setBackgroundDrawable(getResources().getDrawable(R.drawable.tv_loading));
        new Player().execute(filepath);
    }
    
    private InputStream getInputStream(Context context, String filepath) {
        InputStream stream = null;
        try {
            stream = context.getAssets().open(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }
    
    class Player extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            mGifDecoder = new GifDecoder();
            stream = getInputStream(getContext(), getFilepath());
            mGifDecoder.read(stream);
            mIsPlayingGif = true;
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            new Thread(new Runnable() {
                public void run() {
                    final int n = mGifDecoder.getFrameCount();
                    final int ntimes = mGifDecoder.getLoopCount();
                    int repetitionCounter = 0;
                    do {
                        for (int i = 0; i < n; i++) {
                            mTmpBitmap = mGifDecoder.getFrame(i);
                            int t = mGifDecoder.getDelay(i);
                            mHandler.post(mUpdateResults);
                            try {
                                Thread.sleep(t);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(ntimes != 0) {
                            repetitionCounter ++;
                        }
                    } while (mIsPlayingGif && (repetitionCounter <= ntimes));
                }
            }).start();
        }
        
        
        
    }
    
    public void stopRendering() {
        mIsPlayingGif = true;
    }


    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
