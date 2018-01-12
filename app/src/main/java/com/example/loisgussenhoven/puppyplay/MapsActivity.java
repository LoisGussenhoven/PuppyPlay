package com.example.loisgussenhoven.puppyplay;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.loisgussenhoven.puppyplay.Location.LocationManager;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    
    private boolean mLocationPermissionGranted;
    private LocationManager locationManager;

    Location location = new Location("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationManager = new LocationManager(this);
        location.setLongitude(0);
        location.setLatitude(0);

        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ImageButton btnSocial = findViewById(R.id.maps_ib_play);
        btnSocial.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), SocialActivity.class);
            i.putExtra("LAT", location.getLatitude());
            i.putExtra("LONG", location.getLongitude());
            startActivity(i);
        });

        locationManager.startLocationUpdates();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getLocationPermission();
        updateLocationUI();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.594184, 4.779178), 15));

        mMap.clear();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                locationManager.setLastKnownLocation(null);
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            locationManager.startLocationUpdates();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    public void geofenceEnter(String geoFenceName) {
        Toast.makeText(getApplicationContext(), "Dog owner nearby", Toast.LENGTH_LONG).show();
    }

    public void geofenceDwell(String geoFenceName) {
    }

    public void geofenceExit(String geoFenceName) {
        Toast.makeText(getApplicationContext(), "leaving dog", Toast.LENGTH_LONG).show();
    }

    public void newLocationAvailable(Location lastLocation) {
        this.location = lastLocation;
    }

    class GoogleReceiver extends BroadcastReceiver {
        MapsActivity mActivity;

        public GoogleReceiver(MapsActivity activity){
            mActivity = activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if(mActivity != null) {
                int transition = intent.getExtras().getInt("transition");
                String geofenceName = (String) intent.getExtras().get("name");
                if(transition == Geofence.GEOFENCE_TRANSITION_ENTER) {
                    mActivity.geofenceEnter(geofenceName);
                } else if(transition == Geofence.GEOFENCE_TRANSITION_DWELL) {
                    mActivity.geofenceDwell(geofenceName);
                } else if(transition == Geofence.GEOFENCE_TRANSITION_EXIT) {
                    mActivity.geofenceExit(geofenceName);
                }
            }
        }
    }
}
