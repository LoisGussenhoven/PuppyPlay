package com.example.loisgussenhoven.puppyplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.loisgussenhoven.puppyplay.entity.Park;
import com.example.loisgussenhoven.puppyplay.location.LocationManager;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private static final String TAG = "MAPS";
    private GoogleMap mMap;
    private List<Marker> allMarkers = new ArrayList<>();
    
    private boolean mLocationPermissionGranted;
    private LocationManager locationManager;

    Location location = new Location("");

    ImageView ivPoopArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalBroadcastManager lbc = LocalBroadcastManager.getInstance(this);
        GoogleReceiver receiver = new GoogleReceiver(this);
        lbc.registerReceiver(receiver, new IntentFilter("googlegeofence"));

        locationManager = new LocationManager(this);
        location.setLongitude(0);
        location.setLatitude(0);

        ivPoopArea = findViewById(R.id.maps_iv_pooparea);

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

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.594184, 4.779178), 18));
        mMap.clear();

        addAllMarkersFromRoute(Manager.parks);
        locationManager.addAllGeofences();
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

    public void addAllMarkersFromRoute(List<Park> parks) {
        Log.i("Markers", String.valueOf(parks.size()));
        allMarkers.clear();
        if(mMap != null) {
            for (Park p : parks) {
                float color = BitmapDescriptorFactory.HUE_AZURE;

                Marker m = mMap.addMarker(
                        new MarkerOptions().position(
                                new LatLng(p.getLocation().getLatitude(), p.getLocation().getLongitude())
                        ).icon(BitmapDescriptorFactory.defaultMarker(color))
                );
                m.setTag(p);
                allMarkers.add(m);
            }
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
        ivPoopArea = findViewById(R.id.maps_iv_pooparea);
        ivPoopArea.setVisibility(View.VISIBLE);
    }

    public void geofenceDwell(String geoFenceName) {
        Manager.yourDog.startPooping();

        ivPoopArea = findViewById(R.id.maps_iv_pooparea);
        ivPoopArea.setVisibility(View.VISIBLE);
    }

    public void geofenceExit(String geoFenceName) {
        ivPoopArea = findViewById(R.id.maps_iv_pooparea);
        ivPoopArea.setVisibility(View.INVISIBLE);
    }

    public void newLocationAvailable(Location lastLocation) {
        this.location = lastLocation;

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
        mMap.animateCamera(cameraUpdate);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


    class GoogleReceiver extends BroadcastReceiver {
        MapsActivity mActivity;

        public GoogleReceiver(MapsActivity activity){
            mActivity = activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: ACK");
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
