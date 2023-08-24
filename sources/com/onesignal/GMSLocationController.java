package com.onesignal;

import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.onesignal.OneSignal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class GMSLocationController extends LocationController {
    static final int API_FALLBACK_TIME = 30000;
    private static GoogleApiClientCompatProxy googleApiClient;
    static LocationUpdateListener locationUpdateListener;

    private static int getApiFallbackWait() {
        return 30000;
    }

    GMSLocationController() {
    }

    static /* synthetic */ int access$100() {
        return getApiFallbackWait();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void startGetLocation() {
        initGoogleLocation();
    }

    private static void initGoogleLocation() {
        if (fallbackFailThread != null) {
            return;
        }
        synchronized (syncLock) {
            startFallBackThread();
            if (googleApiClient != null && lastLocation != null) {
                fireCompleteForLocation(lastLocation);
            }
            GoogleApiClientListener googleApiClientListener = new GoogleApiClientListener();
            GoogleApiClientCompatProxy googleApiClientCompatProxy = new GoogleApiClientCompatProxy(new GoogleApiClient.Builder(classContext).addApi(LocationServices.API).addConnectionCallbacks(googleApiClientListener).addOnConnectionFailedListener(googleApiClientListener).setHandler(getLocationHandlerThread().mHandler).build());
            googleApiClient = googleApiClientCompatProxy;
            googleApiClientCompatProxy.connect();
        }
    }

    private static void startFallBackThread() {
        fallbackFailThread = new Thread(new Runnable() { // from class: com.onesignal.GMSLocationController.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(GMSLocationController.access$100());
                    OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Location permission exists but GoogleApiClient timed out. Maybe related to mismatch google-play aar versions.");
                    LocationController.fireFailedComplete();
                    LocationController.scheduleUpdate(LocationController.classContext);
                } catch (InterruptedException unused) {
                }
            }
        }, "OS_GMS_LOCATION_FALLBACK");
        fallbackFailThread.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fireFailedComplete() {
        synchronized (syncLock) {
            GoogleApiClientCompatProxy googleApiClientCompatProxy = googleApiClient;
            if (googleApiClientCompatProxy != null) {
                googleApiClientCompatProxy.disconnect();
            }
            googleApiClient = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void onFocusChange() {
        synchronized (syncLock) {
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "GMSLocationController onFocusChange!");
            GoogleApiClientCompatProxy googleApiClientCompatProxy = googleApiClient;
            if (googleApiClientCompatProxy != null && googleApiClientCompatProxy.realInstance().isConnected()) {
                GoogleApiClientCompatProxy googleApiClientCompatProxy2 = googleApiClient;
                if (googleApiClientCompatProxy2 != null) {
                    GoogleApiClient realInstance = googleApiClientCompatProxy2.realInstance();
                    if (locationUpdateListener != null) {
                        LocationServices.FusedLocationApi.removeLocationUpdates(realInstance, locationUpdateListener);
                    }
                    locationUpdateListener = new LocationUpdateListener(realInstance);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class GoogleApiClientListener implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private GoogleApiClientListener() {
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public void onConnected(Bundle bundle) {
            synchronized (LocationController.syncLock) {
                if (GMSLocationController.googleApiClient != null && GMSLocationController.googleApiClient.realInstance() != null) {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                    OneSignal.Log(log_level, "GMSLocationController GoogleApiClientListener onConnected lastLocation: " + LocationController.lastLocation);
                    if (LocationController.lastLocation == null) {
                        LocationController.lastLocation = FusedLocationApiWrapper.getLastLocation(GMSLocationController.googleApiClient.realInstance());
                        OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.DEBUG;
                        OneSignal.Log(log_level2, "GMSLocationController GoogleApiClientListener lastLocation: " + LocationController.lastLocation);
                        if (LocationController.lastLocation != null) {
                            LocationController.fireCompleteForLocation(LocationController.lastLocation);
                        }
                    }
                    GMSLocationController.locationUpdateListener = new LocationUpdateListener(GMSLocationController.googleApiClient.realInstance());
                    return;
                }
                OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "GMSLocationController GoogleApiClientListener onConnected googleApiClient not available, returning");
            }
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public void onConnectionSuspended(int r4) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, "GMSLocationController GoogleApiClientListener onConnectionSuspended i: " + r4);
            GMSLocationController.fireFailedComplete();
        }

        @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
        public void onConnectionFailed(ConnectionResult connectionResult) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, "GMSLocationController GoogleApiClientListener onConnectionSuspended connectionResult: " + connectionResult);
            GMSLocationController.fireFailedComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class LocationUpdateListener implements LocationListener {
        private GoogleApiClient googleApiClient;

        LocationUpdateListener(GoogleApiClient googleApiClient) {
            this.googleApiClient = googleApiClient;
            init();
        }

        private void init() {
            long j = OneSignal.isInForeground() ? 270000L : 570000L;
            if (this.googleApiClient != null) {
                LocationRequest priority = LocationRequest.create().setFastestInterval(j).setInterval(j).setMaxWaitTime((long) (j * 1.5d)).setPriority(102);
                OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "GMSLocationController GoogleApiClient requestLocationUpdates!");
                FusedLocationApiWrapper.requestLocationUpdates(this.googleApiClient, priority, this);
            }
        }

        @Override // com.google.android.gms.location.LocationListener
        public void onLocationChanged(Location location) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, "GMSLocationController onLocationChanged: " + location);
            LocationController.lastLocation = location;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FusedLocationApiWrapper {
        FusedLocationApiWrapper() {
        }

        static void requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener) {
            try {
                synchronized (GMSLocationController.syncLock) {
                    if (Looper.myLooper() == null) {
                        Looper.prepare();
                    }
                    if (googleApiClient.isConnected()) {
                        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
                    }
                }
            } catch (Throwable th) {
                OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "FusedLocationApi.requestLocationUpdates failed!", th);
            }
        }

        static Location getLastLocation(GoogleApiClient googleApiClient) {
            synchronized (GMSLocationController.syncLock) {
                if (googleApiClient.isConnected()) {
                    return LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                }
                return null;
            }
        }
    }
}
