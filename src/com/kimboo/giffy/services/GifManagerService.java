
package com.kimboo.giffy.services;

import java.io.IOException;
import java.io.InputStream;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Binder;
import android.os.IBinder;
import android.view.WindowManager;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.kimboo.giffy.db.DatabaseHelper;
import com.kimboo.giffy.model.Gif;
import com.kimboo.giffy.model.GifFrame;
import com.kimboo.giffy.utils.DiskLruImageCache;
import com.kimboo.giffy.utils.Utils;
import com.kimboo.giffy.views.GifDecoder;

/**
 * This service is designed to do the following things: -Download the gif from
 * Internet and put the in the assets folder. -Take the gif images and resize
 * then to two kind of sizes. One to show as thumbnail. Another to show full
 * screen.
 * 
 * @author astinx
 */
public class GifManagerService extends Service {

    private final IBinder mBinder = new LocalBinder();


    /**
     * Class used for the client Binder. Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public GifManagerService getService() {
            return GifManagerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



}
