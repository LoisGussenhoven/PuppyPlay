package com.example.loisgussenhoven.puppyplay.Location;

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
        super("Mapsactivity");
        Log.e("GEOFENCE CONSTRUCTOR", "INTENT SERVICE AANGEMAAKT");
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

        Intent lbcIntent = new Intent("googlegeofence");
        lbcIntent.putExtra("name", gfE.getTriggeringGeofences().get(0).getRequestId());
        lbcIntent.putExtra("transition", geofenceTransition);
        LocalBroadcastManager.getInstance(this).sendBroadcast(lbcIntent);
    }
}
