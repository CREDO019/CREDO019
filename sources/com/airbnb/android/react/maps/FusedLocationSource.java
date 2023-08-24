package com.airbnb.android.react.maps;

import android.content.Context;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.tasks.OnSuccessListener;

/* loaded from: classes.dex */
public class FusedLocationSource implements LocationSource {
    private final FusedLocationProviderClient fusedLocationClientProviderClient;
    private LocationCallback locationCallback;
    private final LocationRequest locationRequest;

    public FusedLocationSource(Context context) {
        this.fusedLocationClientProviderClient = LocationServices.getFusedLocationProviderClient(context);
        LocationRequest create = LocationRequest.create();
        this.locationRequest = create;
        create.setPriority(100);
        create.setInterval(5000L);
    }

    public void setPriority(int r2) {
        this.locationRequest.setPriority(r2);
    }

    public void setInterval(int r4) {
        this.locationRequest.setInterval(r4);
    }

    public void setFastestInterval(int r4) {
        this.locationRequest.setFastestInterval(r4);
    }

    @Override // com.google.android.gms.maps.LocationSource
    public void activate(final LocationSource.OnLocationChangedListener onLocationChangedListener) {
        try {
            this.fusedLocationClientProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() { // from class: com.airbnb.android.react.maps.FusedLocationSource.1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public void onSuccess(Location location) {
                    if (location != null) {
                        onLocationChangedListener.onLocationChanged(location);
                    }
                }
            });
            LocationCallback locationCallback = new LocationCallback() { // from class: com.airbnb.android.react.maps.FusedLocationSource.2
                @Override // com.google.android.gms.location.LocationCallback
                public void onLocationResult(LocationResult locationResult) {
                    for (Location location : locationResult.getLocations()) {
                        onLocationChangedListener.onLocationChanged(location);
                    }
                }
            };
            this.locationCallback = locationCallback;
            this.fusedLocationClientProviderClient.requestLocationUpdates(this.locationRequest, locationCallback, Looper.myLooper());
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override // com.google.android.gms.maps.LocationSource
    public void deactivate() {
        this.fusedLocationClientProviderClient.removeLocationUpdates(this.locationCallback);
    }
}
