
package com.kimboo.giffy.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.kimboo.giffy.db.DatabaseHelper;
import com.kimboo.giffy.model.Gif;
import com.kimboo.giffy.server.rest.RestClient;
import com.kimboo.giffy.server.rest.RestFactory;
import com.kimboo.giffy.utils.Constants;

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
    private RestClient restClient;

    private DatabaseHelper databaseHelper = null;

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
    
    @Override
    public void onCreate() {
        super.onCreate();
        restClient = new RestClient();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*
         * You'll need this in your class to release the helper when done.
         */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    /**
     * You'll need this in your class to get the helper from the manager once
     * per class.
     */
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }



    /**
     * Download latests gifs from the server
     */
    public void updateGifs() {
        try {
            Gif[] gifs = RestFactory.getPopularGifs(restClient);
            for (Gif gif : gifs) {
                getHelper().getGifDao().createOrUpdate(gif);
            }
            Intent intent = new Intent(Constants.Service.ServerUpdateReceiver.NEW_GIFS_INCOMING);
            sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Verify behaviour
            Intent intent = new Intent(Constants.Service.ServerUpdateReceiver.GIF_UPDATE_FAILED);
            sendBroadcast(intent);
        }
        
    }



}
