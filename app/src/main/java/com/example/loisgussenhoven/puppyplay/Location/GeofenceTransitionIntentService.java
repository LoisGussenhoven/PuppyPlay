package com.example.loisgussenhoven.puppyplay.location;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.GeofencingEvent;

/**
 * Created by Nick van Endhoven on 10-1-2018.
 */

public class GeofenceTransitionIntentService extends IntentService {

    public GeofenceTransitionIntentService() {
        super("PuppyPlayIntentService");
        Log.e("GEOFENCE CONSTRUCT", "INTENT ONTVANGEN");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("GEOFENCE HANDLE", "INTENT ONTVANGEN");

        GeofencingEvent gfE = null;
        if(intent != null) {
            gfE = GeofencingEvent.fromIntent(intent);
        } else {
        }

        if(gfE.hasError()) {
            return;
        }
        int geofenceTransition = gfE.getGeofenceTransition();

        Log.i("BOY", "onHandleIntent: handle intent");

        Intent lbcIntent = new Intent("googlegeofence");
        lbcIntent.putExtra("name", gfE.getTriggeringGeofences().get(0).getRequestId());
        lbcIntent.putExtra("transition", geofenceTransition);
        LocalBroadcastManager.getInstance(this).sendBroadcast(lbcIntent);
    }
}
