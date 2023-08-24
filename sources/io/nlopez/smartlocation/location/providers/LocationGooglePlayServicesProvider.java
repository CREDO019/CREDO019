package io.nlopez.smartlocation.location.providers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.location.LocationStore;
import io.nlopez.smartlocation.location.ServiceLocationProvider;
import io.nlopez.smartlocation.location.config.LocationAccuracy;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.utils.GooglePlayServicesListener;
import io.nlopez.smartlocation.utils.Logger;
import io.nlopez.smartlocation.utils.ServiceConnectionListener;

/* loaded from: classes5.dex */
public class LocationGooglePlayServicesProvider implements ServiceLocationProvider, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, ResultCallback<Status> {
    private static final String GMS_ID = "GMS";
    public static final int REQUEST_CHECK_SETTINGS = 20001;
    public static final int REQUEST_START_LOCATION_FIX = 10001;
    private boolean alwaysShow;
    private boolean checkLocationSettings;
    private GoogleApiClient client;
    private Context context;
    private boolean fulfilledCheckLocationSettings;
    private GooglePlayServicesListener googlePlayServicesListener;
    private OnLocationUpdatedListener listener;
    private LocationRequest locationRequest;
    private LocationStore locationStore;
    private Logger logger;
    private ServiceConnectionListener serviceListener;
    private ResultCallback<LocationSettingsResult> settingsResultCallback;
    private boolean shouldStart;
    private boolean stopped;

    public LocationGooglePlayServicesProvider() {
        this.shouldStart = false;
        this.stopped = false;
        this.alwaysShow = true;
        this.settingsResultCallback = new ResultCallback<LocationSettingsResult>() { // from class: io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider.1
            @Override // com.google.android.gms.common.api.ResultCallback
            public void onResult(LocationSettingsResult locationSettingsResult) {
                Status status = locationSettingsResult.getStatus();
                int statusCode = status.getStatusCode();
                if (statusCode == 0) {
                    LocationGooglePlayServicesProvider.this.logger.mo195d("All location settings are satisfied.", new Object[0]);
                    LocationGooglePlayServicesProvider.this.fulfilledCheckLocationSettings = true;
                    LocationGooglePlayServicesProvider locationGooglePlayServicesProvider = LocationGooglePlayServicesProvider.this;
                    locationGooglePlayServicesProvider.startUpdating(locationGooglePlayServicesProvider.locationRequest);
                } else if (statusCode != 6) {
                    if (statusCode != 8502) {
                        return;
                    }
                    LocationGooglePlayServicesProvider.this.logger.mo191i("Location settings are inadequate, and cannot be fixed here. Dialog not created.", new Object[0]);
                    LocationGooglePlayServicesProvider.this.stop();
                } else {
                    LocationGooglePlayServicesProvider.this.logger.mo187w("Location settings are not satisfied. Show the user a dialog toupgrade location settings. You should hook into the Activity onActivityResult and call this provider onActivityResult method for continuing this call flow. ", new Object[0]);
                    if (LocationGooglePlayServicesProvider.this.context instanceof Activity) {
                        try {
                            status.startResolutionForResult((Activity) LocationGooglePlayServicesProvider.this.context, LocationGooglePlayServicesProvider.REQUEST_CHECK_SETTINGS);
                            return;
                        } catch (IntentSender.SendIntentException unused) {
                            LocationGooglePlayServicesProvider.this.logger.mo191i("PendingIntent unable to execute request.", new Object[0]);
                            return;
                        }
                    }
                    LocationGooglePlayServicesProvider.this.logger.mo187w("Provided context is not the context of an activity, therefore we cant launch the resolution activity.", new Object[0]);
                }
            }
        };
        this.checkLocationSettings = false;
        this.fulfilledCheckLocationSettings = false;
    }

    public LocationGooglePlayServicesProvider(GooglePlayServicesListener googlePlayServicesListener) {
        this();
        this.googlePlayServicesListener = googlePlayServicesListener;
    }

    public LocationGooglePlayServicesProvider(ServiceConnectionListener serviceConnectionListener) {
        this();
        this.serviceListener = serviceConnectionListener;
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void init(Context context, Logger logger) {
        this.logger = logger;
        this.context = context;
        this.locationStore = new LocationStore(context);
        if (!this.shouldStart) {
            GoogleApiClient build = new GoogleApiClient.Builder(context).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
            this.client = build;
            build.connect();
            return;
        }
        logger.mo195d("already started", new Object[0]);
    }

    private LocationRequest createRequest(LocationParams locationParams, boolean z) {
        LocationRequest smallestDisplacement = LocationRequest.create().setFastestInterval(locationParams.getInterval()).setInterval(locationParams.getInterval()).setSmallestDisplacement(locationParams.getDistance());
        int r4 = C46512.f1488x1a467bf9[locationParams.getAccuracy().ordinal()];
        if (r4 == 1) {
            smallestDisplacement.setPriority(100);
        } else if (r4 == 2) {
            smallestDisplacement.setPriority(102);
        } else if (r4 == 3) {
            smallestDisplacement.setPriority(104);
        } else if (r4 == 4) {
            smallestDisplacement.setPriority(105);
        }
        if (z) {
            smallestDisplacement.setNumUpdates(1);
        }
        return smallestDisplacement;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider$2 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C46512 {

        /* renamed from: $SwitchMap$io$nlopez$smartlocation$location$config$LocationAccuracy */
        static final /* synthetic */ int[] f1488x1a467bf9;

        static {
            int[] r0 = new int[LocationAccuracy.values().length];
            f1488x1a467bf9 = r0;
            try {
                r0[LocationAccuracy.HIGH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1488x1a467bf9[LocationAccuracy.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1488x1a467bf9[LocationAccuracy.LOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1488x1a467bf9[LocationAccuracy.LOWEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void start(OnLocationUpdatedListener onLocationUpdatedListener, LocationParams locationParams, boolean z) {
        this.listener = onLocationUpdatedListener;
        if (onLocationUpdatedListener == null) {
            this.logger.mo195d("Listener is null, you sure about this?", new Object[0]);
        }
        this.locationRequest = createRequest(locationParams, z);
        if (this.client.isConnected()) {
            startUpdating(this.locationRequest);
        } else if (this.stopped) {
            this.shouldStart = true;
            this.client.connect();
            this.stopped = false;
        } else {
            this.shouldStart = true;
            this.logger.mo195d("still not connected - scheduled start when connection is ok", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startUpdating(LocationRequest locationRequest) {
        if (this.checkLocationSettings && !this.fulfilledCheckLocationSettings) {
            this.logger.mo195d("startUpdating wont be executed for now, as we have to test the location settings before", new Object[0]);
            checkLocationSettings();
        } else if (this.client.isConnected()) {
            if (ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                this.logger.mo191i("Permission check failed. Please handle it in your app before setting up location", new Object[0]);
            } else {
                LocationServices.FusedLocationApi.requestLocationUpdates(this.client, locationRequest, this, Looper.getMainLooper()).setResultCallback(this);
            }
        } else {
            this.logger.mo187w("startUpdating executed without the GoogleApiClient being connected!!", new Object[0]);
        }
    }

    private void checkLocationSettings() {
        LocationServices.SettingsApi.checkLocationSettings(this.client, new LocationSettingsRequest.Builder().setAlwaysShow(this.alwaysShow).addLocationRequest(this.locationRequest).build()).setResultCallback(this.settingsResultCallback);
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void stop() {
        this.logger.mo195d("stop", new Object[0]);
        if (this.client.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(this.client, this);
            this.client.disconnect();
        }
        this.fulfilledCheckLocationSettings = false;
        this.shouldStart = false;
        this.stopped = true;
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public Location getLastLocation() {
        GoogleApiClient googleApiClient = this.client;
        if (googleApiClient != null && googleApiClient.isConnected()) {
            if (ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                return null;
            }
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(this.client);
            if (lastLocation != null) {
                return lastLocation;
            }
        }
        LocationStore locationStore = this.locationStore;
        if (locationStore != null) {
            return locationStore.get(GMS_ID);
        }
        return null;
    }

    @Override // io.nlopez.smartlocation.location.ServiceLocationProvider
    public ServiceConnectionListener getServiceListener() {
        return this.serviceListener;
    }

    @Override // io.nlopez.smartlocation.location.ServiceLocationProvider
    public void setServiceListener(ServiceConnectionListener serviceConnectionListener) {
        this.serviceListener = serviceConnectionListener;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        this.logger.mo195d("onConnected", new Object[0]);
        if (this.shouldStart) {
            startUpdating(this.locationRequest);
        }
        GooglePlayServicesListener googlePlayServicesListener = this.googlePlayServicesListener;
        if (googlePlayServicesListener != null) {
            googlePlayServicesListener.onConnected(bundle);
        }
        ServiceConnectionListener serviceConnectionListener = this.serviceListener;
        if (serviceConnectionListener != null) {
            serviceConnectionListener.onConnected();
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnectionSuspended(int r4) {
        Logger logger = this.logger;
        logger.mo195d("onConnectionSuspended " + r4, new Object[0]);
        GooglePlayServicesListener googlePlayServicesListener = this.googlePlayServicesListener;
        if (googlePlayServicesListener != null) {
            googlePlayServicesListener.onConnectionSuspended(r4);
        }
        ServiceConnectionListener serviceConnectionListener = this.serviceListener;
        if (serviceConnectionListener != null) {
            serviceConnectionListener.onConnectionSuspended();
        }
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Logger logger = this.logger;
        logger.mo195d("onConnectionFailed " + connectionResult.toString(), new Object[0]);
        GooglePlayServicesListener googlePlayServicesListener = this.googlePlayServicesListener;
        if (googlePlayServicesListener != null) {
            googlePlayServicesListener.onConnectionFailed(connectionResult);
        }
        ServiceConnectionListener serviceConnectionListener = this.serviceListener;
        if (serviceConnectionListener != null) {
            serviceConnectionListener.onConnectionFailed();
        }
    }

    @Override // com.google.android.gms.location.LocationListener
    public void onLocationChanged(Location location) {
        this.logger.mo195d("onLocationChanged", location);
        OnLocationUpdatedListener onLocationUpdatedListener = this.listener;
        if (onLocationUpdatedListener != null) {
            onLocationUpdatedListener.onLocationUpdated(location);
        }
        if (this.locationStore != null) {
            this.logger.mo195d("Stored in SharedPreferences", new Object[0]);
            this.locationStore.put(GMS_ID, location);
        }
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public void onResult(Status status) {
        if (status.isSuccess()) {
            this.logger.mo195d("Locations update request successful", new Object[0]);
        } else if (status.hasResolution() && (this.context instanceof Activity)) {
            this.logger.mo187w("Unable to register, but we can solve this - will startActivityForResult. You should hook into the Activity onActivityResult and call this provider onActivityResult method for continuing this call flow.", new Object[0]);
            try {
                status.startResolutionForResult((Activity) this.context, REQUEST_START_LOCATION_FIX);
            } catch (IntentSender.SendIntentException e) {
                this.logger.mo192e(e, "problem with startResolutionForResult", new Object[0]);
            }
        } else {
            Logger logger = this.logger;
            logger.mo193e("Registering failed: " + status.getStatusMessage(), new Object[0]);
        }
    }

    public boolean isCheckingLocationSettings() {
        return this.checkLocationSettings;
    }

    public void setCheckLocationSettings(boolean z) {
        this.checkLocationSettings = z;
    }

    public void setLocationSettingsAlwaysShow(boolean z) {
        this.alwaysShow = z;
    }

    public void onActivityResult(int r3, int r4, Intent intent) {
        if (r3 == 20001) {
            if (r4 == -1) {
                this.logger.mo191i("User agreed to make required location settings changes.", new Object[0]);
                this.fulfilledCheckLocationSettings = true;
                startUpdating(this.locationRequest);
            } else if (r4 != 0) {
            } else {
                this.logger.mo191i("User chose not to make required location settings changes.", new Object[0]);
                stop();
            }
        } else if (r3 == 10001) {
            if (r4 == -1) {
                this.logger.mo191i("User fixed the problem.", new Object[0]);
                startUpdating(this.locationRequest);
            } else if (r4 != 0) {
            } else {
                this.logger.mo191i("User chose not to fix the problem.", new Object[0]);
                stop();
            }
        }
    }
}
