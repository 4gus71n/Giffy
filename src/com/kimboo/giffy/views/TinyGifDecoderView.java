
package com.kimboo.giffy.views;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.kimboo.giffy.R;
import com.kimboo.giffy.model.Gif;
import com.kimboo.giffy.utils.DiskLruImageCache;
import com.kimboo.giffy.utils.Utils;

/**
 * Same that {@link GifDecoderView} but shows a bitmap template over the gif.
 * 
 * @author astinx
 */
public class TinyGifDecoderView extends ImageView {

    private GifDecoder mGifDecoder;
    private Bitmap _template;
    private Paint _paint = new Paint();
    protected Rect rectDst;
    private Bitmap mStartBitmap;
    private Bitmap mMiddleBitmap;
    private Bitmap mFinalBitmap;
    private int who = 1;
    private DiskLruImageCache diskCache;
    
    protected Boolean readyToRock = Boolean.FALSE; 

    @Override
    protected void onDraw(Canvas canvas) {
        if (readyToRock) {
            drawFrame(canvas);
        }
        super.onDraw(canvas);
    }
    
    
    /** Adjust the measured bounds to the bitmap size*/
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int minimumWidth = getResources().getDrawable(R.drawable.tv).getMinimumWidth();
        final int minimumHeight = getResources().getDrawable(R.drawable.tv).getMinimumHeight();
        setMeasuredDimension(minimumWidth, minimumHeight);
    }

    /**
     * Draws each frame of the bitmap.
     * @param canvas
     */
    private void drawFrame(Canvas canvas) {
        switch (who) {
            case 1:
                canvas.drawBitmap(mStartBitmap, rectDst, rectDst, _paint);
                break;
            case 2:
                canvas.drawBitmap(mMiddleBitmap, rectDst, rectDst, _paint);
                break;
            case 3:
                canvas.drawBitmap(mFinalBitmap, rectDst, rectDst, _paint);
                break;
            default:
                who = 1;
                canvas.drawBitmap(mStartBitmap, rectDst, rectDst, _paint);
                break;
        }
        canvas.drawBitmap(_template, 0, 0, _paint);
        who++;
    }

    public TinyGifDecoderView(Context context, Gif model, DiskLruImageCache diskCache) {
        super(context);
        setDiskCache(diskCache);
        //Set the animation drawable
        setBackgroundDrawable(getResources().getDrawable(R.drawable.tv_loading));
        new LoadGif().execute(model);
    }
    
    class LoadGif extends AsyncTask<Gif, Void, Void> {
        @Override
        protected Void doInBackground(Gif... params) {
            _template = getTvTemplate(diskCache);
            final Gif currGif = params[0];
            final String filepath = currGif.getFilename();
            final String frame1 = Utils.makeKey(filepath+"_frame_1");
            final String frame2 = Utils.makeKey(filepath+"_frame_2");
            final String frame3 = Utils.makeKey(filepath+"_frame_3");
            if (diskCache.containsKey(frame1) && diskCache.containsKey(frame2) && diskCache.containsKey(frame3)) {
                mStartBitmap = diskCache.getBitmap(frame1);
                mMiddleBitmap = diskCache.getBitmap(frame2); 
                mFinalBitmap = diskCache.getBitmap(frame3);
                scaleBitmaps();
            } else {
                InputStream stream = getInputStream(getContext(), filepath);
                mGifDecoder = new GifDecoder();
//                mGifDecoder.setResizeIt(Boolean.TRUE);
//                mGifDecoder.setScaleFactorX(3);
//                mGifDecoder.setScaleFactorY(3);
                mGifDecoder.read(stream);
                final int frameCount = mGifDecoder.getFrameCount();
                mStartBitmap = mGifDecoder.getFrame(0);
                mMiddleBitmap = mGifDecoder.getFrame(frameCount / 2);
                mFinalBitmap = mGifDecoder.getFrame(frameCount);
                scaleBitmaps();
                diskCache.put(frame1, mStartBitmap);
                diskCache.put(frame2, mMiddleBitmap);
                diskCache.put(frame3, mFinalBitmap);
                disposeDecoder();
            }
            getTemplateRect();
            return null;
        }
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Run the animation
            post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable)getBackground()).start();
                }
            });
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //Stop the animation and play the gif
            ((AnimationDrawable)getBackground()).stop();
            readyToRock = Boolean.TRUE;
            playAnimation();
        }
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

    private void playAnimation() {
        Runnable updater = new Runnable() {
            @Override
            public void run() {
                TinyGifDecoderView.this.invalidate();
                TinyGifDecoderView.this.postDelayed(this, 500);
            }
        };
        postDelayed(updater, 500);
    }

    /**
     * Get the template bounds excluding the offsets.
     */
    private void getTemplateRect() {
        int templateBottomOffset = _template.getHeight() / 15;
        if (rectDst == null) {
            rectDst = new Rect(2, 0, _template.getWidth(), _template.getHeight()
                    - templateBottomOffset);
        }
    }

    /**
     * Scale the bitmaps to the <code>_template</code> bounds.
     */
    private void scaleBitmaps() {
        mStartBitmap = Bitmap.createScaledBitmap(mStartBitmap, _template.getWidth(), _template.getHeight()
                - (_template.getHeight() / 15), false);
        mMiddleBitmap = Bitmap.createScaledBitmap(mMiddleBitmap, _template.getWidth(), _template.getHeight()
                - (_template.getHeight() / 15), false);
        mFinalBitmap = Bitmap.createScaledBitmap(mFinalBitmap, _template.getWidth(), _template.getHeight()
                - (_template.getHeight() / 15), false);
    }



    /**
     * Clean all the bitmap data in {@link GifDecoder}
     */
    private void disposeDecoder() {
        mGifDecoder.recycle();
        mGifDecoder = null;
        System.gc();
    }



    public DiskLruImageCache getDiskCache() {
        return diskCache;
    }



    public void setDiskCache(DiskLruImageCache diskCache) {
        this.diskCache = diskCache;
    }


    /**
     * If the tv template is in the cache then returns it. Othewise decode it, and return it.
     * @param diskCache
     * @return A bitmap
     */
    private Bitmap getTvTemplate(DiskLruImageCache diskCache) {
        Bitmap _template;
        if (diskCache.containsKey(Utils.makeKey("tv_template1"))) {
            _template = diskCache.getBitmap(Utils.makeKey("tv_template1"));
        } else {
            _template = BitmapFactory.decodeResource(getResources(), R.drawable.tv);
            diskCache.put(Utils.makeKey("tv_template1"), _template);
        }
        return _template;
    }

}
