package com.kimboo.giffy.receivers;

import com.kimboo.giffy.utils.Constants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class ServerUpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.Service.ServerUpdateReceiver.NEW_GIFS_INCOMING)) {
            onNewGifsAdded();
        }
        if (intent.getAction().equals(Constants.Service.ServerUpdateReceiver.GIF_UPDATE_FAILED)) {
            onGifUpdateFailed();
        }
        // XXX: Add new action managers here.
    }
    
    /**
     * This method is executed when the service has bring up some new gifs from the server.
     */
    public abstract void onNewGifsAdded();
    
    /**
     * This method is executed when the server has failed in request some gif to the server.
     */
    public abstract void onGifUpdateFailed();
    
}
