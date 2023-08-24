package io.nlopez.smartlocation.location.providers;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.location.DetectedActivity;
import io.nlopez.smartlocation.OnActivityUpdatedListener;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.activity.config.ActivityParams;
import io.nlopez.smartlocation.activity.providers.ActivityGooglePlayServicesProvider;
import io.nlopez.smartlocation.location.LocationProvider;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.utils.Logger;

/* loaded from: classes5.dex */
public class LocationBasedOnActivityProvider implements LocationProvider, OnActivityUpdatedListener {
    private final LocationBasedOnActivityListener locationBasedOnActivityListener;
    private LocationParams locationParams;
    private OnLocationUpdatedListener locationUpdatedListener;
    private final ActivityGooglePlayServicesProvider activityProvider = new ActivityGooglePlayServicesProvider();
    private final LocationGooglePlayServicesProvider locationProvider = new LocationGooglePlayServicesProvider();

    /* loaded from: classes5.dex */
    public interface LocationBasedOnActivityListener {
        LocationParams locationParamsForActivity(DetectedActivity detectedActivity);
    }

    public LocationBasedOnActivityProvider(LocationBasedOnActivityListener locationBasedOnActivityListener) {
        this.locationBasedOnActivityListener = locationBasedOnActivityListener;
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void init(Context context, Logger logger) {
        this.locationProvider.init(context, logger);
        this.activityProvider.init(context, logger);
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void start(OnLocationUpdatedListener onLocationUpdatedListener, LocationParams locationParams, boolean z) {
        if (z) {
            throw new IllegalArgumentException("singleUpdate cannot be set to true");
        }
        this.locationProvider.start(onLocationUpdatedListener, locationParams, false);
        this.activityProvider.start(this, ActivityParams.NORMAL);
        this.locationParams = locationParams;
        this.locationUpdatedListener = onLocationUpdatedListener;
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void stop() {
        this.locationProvider.stop();
        this.activityProvider.stop();
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public Location getLastLocation() {
        return this.locationProvider.getLastLocation();
    }

    @Override // io.nlopez.smartlocation.OnActivityUpdatedListener
    public void onActivityUpdated(DetectedActivity detectedActivity) {
        LocationParams locationParams;
        LocationParams locationParamsForActivity = this.locationBasedOnActivityListener.locationParamsForActivity(detectedActivity);
        if (locationParamsForActivity == null || (locationParams = this.locationParams) == null || locationParams.equals(locationParamsForActivity)) {
            return;
        }
        start(this.locationUpdatedListener, locationParamsForActivity, false);
    }
}
