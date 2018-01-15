package com.example.loisgussenhoven.puppyplay.location;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.loisgussenhoven.puppyplay.entity.Park;
import com.example.loisgussenhoven.puppyplay.Manager;
import com.example.loisgussenhoven.puppyplay.MapsActivity;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick van Endhoven on 10-1-2018.
 */

public class LocationManager implements LocationListener {

    private static final String TAG = "LOCATION MANAGER";

    private MapsActivity mapsActivity;
    private LocationCallback callbackLocation;
    private GeofencingClient gfc;
    private PendingIntent geofencePendingIntent;

    private List<Geofence> allFences;

    private Location lastKnownLocation;

    public LocationManager(MapsActivity mapsActivity) {
        this.mapsActivity = mapsActivity;
        gfc = LocationServices.getGeofencingClient(mapsActivity);
        allFences = new ArrayList<>();
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void setLastKnownLocation(Location loc) {
        lastKnownLocation = loc;
    }

    public Location getLastKnownLocation() {
        return lastKnownLocation;
    }

    public void startLocationUpdates() {

        // Create the location request to start receiving updates
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(2000);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(mapsActivity.getApplicationContext());
        settingsClient.checkLocationSettings(locationSettingsRequest);

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        callbackLocation = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                lastKnownLocation = locationResult.getLastLocation();
                mapsActivity.newLocationAvailable(locationResult.getLastLocation());
            }
        };
        if (ContextCompat.checkSelfPermission(mapsActivity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.getFusedLocationProviderClient(mapsActivity.getApplicationContext()).requestLocationUpdates(mLocationRequest, callbackLocation,
                    Looper.myLooper());
        }
    }

    public void stopLocationUpdates() {
        try {
            LocationServices.getFusedLocationProviderClient(mapsActivity.getApplicationContext()).removeLocationUpdates(callbackLocation);
        } catch (Exception e) {
        }
    }

    private PendingIntent getGeofencePendingIntent() {
        if(this.geofencePendingIntent != null) {
            return this.geofencePendingIntent;
        }
        Intent i = new Intent(mapsActivity.getApplicationContext(), GeofenceTransitionIntentService.class);
        geofencePendingIntent = PendingIntent.getService(mapsActivity.getApplicationContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        return geofencePendingIntent;
    }

    public void removeAllGeofences() {
        if(allFences.size() > 0) {
            List<String> fences = new ArrayList<>();
            for(Geofence f : allFences) {
                fences.add(f.getRequestId());
            }
            gfc.removeGeofences(fences);
        }
    }

    public void addAllGeofences(){
        removeAllGeofences();

        if(gfc != null) {
            for(Park park : Manager.parks) {
                try {
                    GeofencingRequest.Builder request = new GeofencingRequest.Builder();
                    request.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_DWELL);
                    Geofence fence = new Geofence.Builder()
                            .setRequestId(park.getUuid())
                            .setExpirationDuration(1000 * 60 * 15)
                            .setCircularRegion(park.getLocation().getLatitude(), park.getLocation().getLongitude(), 30)
                            .setLoiteringDelay(0)
                            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT | Geofence.GEOFENCE_TRANSITION_DWELL)
                            .build();
                    request.addGeofence(fence);
                    gfc.addGeofences(request.build(), getGeofencePendingIntent()).addOnSuccessListener(aVoid -> {
                        allFences.add(fence);
                    }).addOnFailureListener(e -> {
                        Log.e(TAG, e.getMessage());
                    });
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
