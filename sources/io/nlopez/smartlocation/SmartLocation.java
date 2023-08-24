package io.nlopez.smartlocation;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.location.DetectedActivity;
import io.nlopez.smartlocation.activity.ActivityProvider;
import io.nlopez.smartlocation.activity.config.ActivityParams;
import io.nlopez.smartlocation.activity.providers.ActivityGooglePlayServicesProvider;
import io.nlopez.smartlocation.geocoding.GeocodingProvider;
import io.nlopez.smartlocation.geocoding.providers.AndroidGeocodingProvider;
import io.nlopez.smartlocation.geofencing.GeofencingProvider;
import io.nlopez.smartlocation.geofencing.model.GeofenceModel;
import io.nlopez.smartlocation.geofencing.providers.GeofencingGooglePlayServicesProvider;
import io.nlopez.smartlocation.location.LocationProvider;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesWithFallbackProvider;
import io.nlopez.smartlocation.location.utils.LocationState;
import io.nlopez.smartlocation.utils.Logger;
import io.nlopez.smartlocation.utils.LoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes5.dex */
public class SmartLocation {
    private Context context;
    private Logger logger;
    private boolean preInitialize;

    private SmartLocation(Context context, Logger logger, boolean z) {
        this.context = context;
        this.logger = logger;
        this.preInitialize = z;
    }

    public static SmartLocation with(Context context) {
        return new Builder(context).build();
    }

    public LocationControl location() {
        return location(new LocationGooglePlayServicesWithFallbackProvider(this.context));
    }

    public LocationControl location(LocationProvider locationProvider) {
        return new LocationControl(this, locationProvider);
    }

    @Deprecated
    public ActivityRecognitionControl activityRecognition() {
        return activity();
    }

    public ActivityRecognitionControl activity() {
        return activity(new ActivityGooglePlayServicesProvider());
    }

    public ActivityRecognitionControl activity(ActivityProvider activityProvider) {
        return new ActivityRecognitionControl(this, activityProvider);
    }

    public GeofencingControl geofencing() {
        return geofencing(new GeofencingGooglePlayServicesProvider());
    }

    public GeofencingControl geofencing(GeofencingProvider geofencingProvider) {
        return new GeofencingControl(this, geofencingProvider);
    }

    public GeocodingControl geocoding() {
        return geocoding(new AndroidGeocodingProvider());
    }

    public GeocodingControl geocoding(GeocodingProvider geocodingProvider) {
        return new GeocodingControl(this, geocodingProvider);
    }

    /* loaded from: classes5.dex */
    public static class Builder {
        private final Context context;
        private boolean loggingEnabled = false;
        private boolean preInitialize = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder logging(boolean z) {
            this.loggingEnabled = z;
            return this;
        }

        public Builder preInitialize(boolean z) {
            this.preInitialize = z;
            return this;
        }

        public SmartLocation build() {
            return new SmartLocation(this.context, LoggerFactory.buildLogger(this.loggingEnabled), this.preInitialize);
        }
    }

    /* loaded from: classes5.dex */
    public static class LocationControl {
        private static final Map<Context, LocationProvider> MAPPING = new WeakHashMap();
        private LocationProvider provider;
        private final SmartLocation smartLocation;
        private LocationParams params = LocationParams.BEST_EFFORT;
        private boolean oneFix = false;

        public LocationControl get() {
            return this;
        }

        public LocationControl(SmartLocation smartLocation, LocationProvider locationProvider) {
            this.smartLocation = smartLocation;
            Map<Context, LocationProvider> map = MAPPING;
            if (!map.containsKey(smartLocation.context)) {
                map.put(smartLocation.context, locationProvider);
            }
            this.provider = map.get(smartLocation.context);
            if (smartLocation.preInitialize) {
                this.provider.init(smartLocation.context, smartLocation.logger);
            }
        }

        public LocationControl config(LocationParams locationParams) {
            this.params = locationParams;
            return this;
        }

        public LocationControl oneFix() {
            this.oneFix = true;
            return this;
        }

        public LocationControl continuous() {
            this.oneFix = false;
            return this;
        }

        public LocationState state() {
            return LocationState.with(this.smartLocation.context);
        }

        public Location getLastLocation() {
            return this.provider.getLastLocation();
        }

        public void start(OnLocationUpdatedListener onLocationUpdatedListener) {
            LocationProvider locationProvider = this.provider;
            if (locationProvider == null) {
                throw new RuntimeException("A provider must be initialized");
            }
            locationProvider.start(onLocationUpdatedListener, this.params, this.oneFix);
        }

        public void stop() {
            this.provider.stop();
        }
    }

    /* loaded from: classes5.dex */
    public static class GeocodingControl {
        private static final Map<Context, GeocodingProvider> MAPPING = new WeakHashMap();
        private GeocodingProvider provider;
        private final SmartLocation smartLocation;
        private boolean directAdded = false;
        private boolean reverseAdded = false;

        public GeocodingControl get() {
            return this;
        }

        public GeocodingControl(SmartLocation smartLocation, GeocodingProvider geocodingProvider) {
            this.smartLocation = smartLocation;
            Map<Context, GeocodingProvider> map = MAPPING;
            if (!map.containsKey(smartLocation.context)) {
                map.put(smartLocation.context, geocodingProvider);
            }
            this.provider = map.get(smartLocation.context);
            if (smartLocation.preInitialize) {
                this.provider.init(smartLocation.context, smartLocation.logger);
            }
        }

        public void reverse(Location location, OnReverseGeocodingListener onReverseGeocodingListener) {
            add(location);
            start(onReverseGeocodingListener);
        }

        public void direct(String str, OnGeocodingListener onGeocodingListener) {
            add(str);
            start(onGeocodingListener);
        }

        public GeocodingControl add(Location location) {
            this.reverseAdded = true;
            this.provider.addLocation(location, 1);
            return this;
        }

        public GeocodingControl add(Location location, int r3) {
            this.reverseAdded = true;
            this.provider.addLocation(location, r3);
            return this;
        }

        public GeocodingControl add(String str) {
            this.directAdded = true;
            this.provider.addName(str, 1);
            return this;
        }

        public GeocodingControl add(String str, int r3) {
            this.directAdded = true;
            this.provider.addName(str, r3);
            return this;
        }

        public void start(OnGeocodingListener onGeocodingListener) {
            start(onGeocodingListener, null);
        }

        public void start(OnReverseGeocodingListener onReverseGeocodingListener) {
            start(null, onReverseGeocodingListener);
        }

        public void start(OnGeocodingListener onGeocodingListener, OnReverseGeocodingListener onReverseGeocodingListener) {
            if (this.provider == null) {
                throw new RuntimeException("A provider must be initialized");
            }
            if (this.directAdded && onGeocodingListener == null) {
                this.smartLocation.logger.mo187w("Some places were added for geocoding but the listener was not specified!", new Object[0]);
            }
            if (this.reverseAdded && onReverseGeocodingListener == null) {
                this.smartLocation.logger.mo187w("Some places were added for reverse geocoding but the listener was not specified!", new Object[0]);
            }
            this.provider.start(onGeocodingListener, onReverseGeocodingListener);
        }

        public void stop() {
            this.provider.stop();
        }
    }

    /* loaded from: classes5.dex */
    public static class ActivityRecognitionControl {
        private static final Map<Context, ActivityProvider> MAPPING = new WeakHashMap();
        private ActivityParams params = ActivityParams.NORMAL;
        private ActivityProvider provider;
        private final SmartLocation smartLocation;

        public ActivityRecognitionControl get() {
            return this;
        }

        public ActivityRecognitionControl(SmartLocation smartLocation, ActivityProvider activityProvider) {
            this.smartLocation = smartLocation;
            Map<Context, ActivityProvider> map = MAPPING;
            if (!map.containsKey(smartLocation.context)) {
                map.put(smartLocation.context, activityProvider);
            }
            this.provider = map.get(smartLocation.context);
            if (smartLocation.preInitialize) {
                this.provider.init(smartLocation.context, smartLocation.logger);
            }
        }

        public ActivityRecognitionControl config(ActivityParams activityParams) {
            this.params = activityParams;
            return this;
        }

        public DetectedActivity getLastActivity() {
            return this.provider.getLastActivity();
        }

        public void start(OnActivityUpdatedListener onActivityUpdatedListener) {
            ActivityProvider activityProvider = this.provider;
            if (activityProvider == null) {
                throw new RuntimeException("A provider must be initialized");
            }
            activityProvider.start(onActivityUpdatedListener, this.params);
        }

        public void stop() {
            this.provider.stop();
        }
    }

    /* loaded from: classes5.dex */
    public static class GeofencingControl {
        private static final Map<Context, GeofencingProvider> MAPPING = new WeakHashMap();
        private GeofencingProvider provider;
        private final SmartLocation smartLocation;

        public GeofencingControl(SmartLocation smartLocation, GeofencingProvider geofencingProvider) {
            this.smartLocation = smartLocation;
            Map<Context, GeofencingProvider> map = MAPPING;
            if (!map.containsKey(smartLocation.context)) {
                map.put(smartLocation.context, geofencingProvider);
            }
            this.provider = map.get(smartLocation.context);
            if (smartLocation.preInitialize) {
                this.provider.init(smartLocation.context, smartLocation.logger);
            }
        }

        public GeofencingControl add(GeofenceModel geofenceModel) {
            this.provider.addGeofence(geofenceModel);
            return this;
        }

        public GeofencingControl remove(String str) {
            this.provider.removeGeofence(str);
            return this;
        }

        public GeofencingControl addAll(List<GeofenceModel> list) {
            this.provider.addGeofences(list);
            return this;
        }

        public GeofencingControl removeAll(List<String> list) {
            this.provider.removeGeofences(list);
            return this;
        }

        public void start(OnGeofencingTransitionListener onGeofencingTransitionListener) {
            GeofencingProvider geofencingProvider = this.provider;
            if (geofencingProvider == null) {
                throw new RuntimeException("A provider must be initialized");
            }
            geofencingProvider.start(onGeofencingTransitionListener);
        }

        public void stop() {
            this.provider.stop();
        }
    }
}
