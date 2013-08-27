
package com.kimboo.giffy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.kimboo.giffy.db.DatabaseHelper;
import com.kimboo.giffy.model.Gif;
import com.kimboo.giffy.receivers.ServerUpdateReceiver;
import com.kimboo.giffy.services.GifManagerService;
import com.kimboo.giffy.services.GifManagerService.LocalBinder;
import com.kimboo.giffy.utils.Constants;
import com.kimboo.giffy.utils.DaoAdapter;
import com.kimboo.giffy.utils.DiskLruImageCache;
import com.kimboo.giffy.views.TinyGifDecoderView;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    /** Service stuff*/
    private GifManagerService mService;
    private boolean mBound = false;
    
    /** UI stuff*/
    private GridView gifGrid;

    /** Receivers */
    private ServerUpdateReceiver serviceUpdatesCallback = new ServerUpdateReceiver() {
        
        @Override
        public void onNewGifsAdded() {
            Log.d(TAG, "The service has bring some new gifs!");
            refreshGridView();
        }
        
        @Override
        public void onGifUpdateFailed() {
            Log.d(TAG, "Some error has happened in the service");
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //diskCache = new DiskLruImageCache(this, "cache", 1024 * 1024 * 30, CompressFormat.PNG, 70);
        setContentView(R.layout.activity_main);
        gifGrid = (GridView) findViewById(R.id.main_gridview);
    }
    
    protected void refreshGridView() {
        //CONTINUE HERE!
        //gifGrid.setAdapter(new GifGridAdapter(this, getHelper().getGifDao()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, GifManagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        // Register receiver
        registerReceiver(serviceUpdatesCallback, 
                new IntentFilter(Constants.Service.ServerUpdateReceiver.GIF_UPDATE_FAILED));
        registerReceiver(serviceUpdatesCallback, 
                new IntentFilter(Constants.Service.ServerUpdateReceiver.NEW_GIFS_INCOMING));
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        unregisterReceiver(serviceUpdatesCallback);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalBinder binder = (LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            mService.updateGifs();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    class GifGridAdapter extends DaoAdapter<Gif> {
        public GifGridAdapter(Context context, RuntimeExceptionDao<Gif, Integer> dao) {
            super(context, dao);
        }

        @Override
        public View getView(Gif model, View convertView, ViewGroup parent) {
            //TinyGifDecoderView view = new TinyGifDecoderView(getContext(), model, diskCache);
//            view.setScaleType(TinyGifDecoderView.ScaleType.FIT_XY);
//            //TODO: Improve
//            view.setLayoutParams(new GridView.LayoutParams(150, 100));
//            view.setPadding(0, 0, 0, 0);
//            return view;
            return null;
        }

    }
}
