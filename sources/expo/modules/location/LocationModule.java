package expo.modules.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.amplitude.api.DeviceInfo;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ActivityEventListener;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.interfaces.permissions.PermissionsResponseListener;
import expo.modules.interfaces.permissions.PermissionsStatus;
import expo.modules.interfaces.taskManager.TaskManagerInterface;
import expo.modules.location.LocationModule;
import expo.modules.location.exceptions.LocationBackgroundUnauthorizedException;
import expo.modules.location.exceptions.LocationRequestRejectedException;
import expo.modules.location.exceptions.LocationSettingsUnsatisfiedException;
import expo.modules.location.exceptions.LocationUnauthorizedException;
import expo.modules.location.exceptions.LocationUnavailableException;
import expo.modules.location.taskConsumers.GeofencingTaskConsumer;
import expo.modules.location.taskConsumers.LocationTaskConsumer;
import io.nlopez.smartlocation.OnGeocodingListener;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.geocoding.utils.LocationAddress;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.location.utils.LocationState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes4.dex */
public class LocationModule extends ExportedModule implements LifecycleEventListener, SensorEventListener, ActivityEventListener {
    public static final int ACCURACY_BALANCED = 3;
    public static final int ACCURACY_BEST_FOR_NAVIGATION = 6;
    public static final int ACCURACY_HIGH = 4;
    public static final int ACCURACY_HIGHEST = 5;
    public static final int ACCURACY_LOW = 2;
    public static final int ACCURACY_LOWEST = 1;
    private static final int CHECK_SETTINGS_REQUEST_CODE = 42;
    private static final double DEGREE_DELTA = 0.0355d;
    public static final int GEOFENCING_EVENT_ENTER = 1;
    public static final int GEOFENCING_EVENT_EXIT = 2;
    public static final int GEOFENCING_REGION_STATE_INSIDE = 1;
    public static final int GEOFENCING_REGION_STATE_OUTSIDE = 2;
    public static final int GEOFENCING_REGION_STATE_UNKNOWN = 0;
    private static final String HEADING_EVENT_NAME = "Expo.headingChanged";
    private static final String LOCATION_EVENT_NAME = "Expo.locationChanged";
    private static final String SHOW_USER_SETTINGS_DIALOG_KEY = "mayShowUserSettingsDialog";
    private static final String TAG = "LocationModule";
    private static final float TIME_DELTA = 50.0f;
    private int mAccuracy;
    private ActivityProvider mActivityProvider;
    private Context mContext;
    private EventEmitter mEventEmitter;
    private boolean mGeocoderPaused;
    private GeomagneticField mGeofield;
    private float[] mGeomagnetic;
    private float[] mGravity;
    private int mHeadingId;
    private float mLastAzimuth;
    private long mLastUpdate;
    private Map<Integer, LocationCallback> mLocationCallbacks;
    private FusedLocationProviderClient mLocationProvider;
    private Map<Integer, LocationRequest> mLocationRequests;
    private List<LocationActivityResultListener> mPendingLocationRequests;
    private Permissions mPermissionsManager;
    private SensorManager mSensorManager;
    private TaskManagerInterface mTaskManager;
    private UIManager mUIManager;

    /* loaded from: classes4.dex */
    public interface OnResultListener {
        void onResult(Location location);
    }

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExpoLocation";
    }

    @Override // expo.modules.core.interfaces.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    public LocationModule(Context context) {
        super(context);
        this.mLocationCallbacks = new HashMap();
        this.mLocationRequests = new HashMap();
        this.mPendingLocationRequests = new ArrayList();
        this.mLastAzimuth = 0.0f;
        this.mAccuracy = 0;
        this.mLastUpdate = 0L;
        this.mGeocoderPaused = false;
        this.mContext = context;
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        UIManager uIManager = this.mUIManager;
        if (uIManager != null) {
            uIManager.unregisterLifecycleEventListener(this);
        }
        this.mEventEmitter = (EventEmitter) moduleRegistry.getModule(EventEmitter.class);
        this.mUIManager = (UIManager) moduleRegistry.getModule(UIManager.class);
        this.mPermissionsManager = (Permissions) moduleRegistry.getModule(Permissions.class);
        this.mTaskManager = (TaskManagerInterface) moduleRegistry.getModule(TaskManagerInterface.class);
        this.mActivityProvider = (ActivityProvider) moduleRegistry.getModule(ActivityProvider.class);
        UIManager uIManager2 = this.mUIManager;
        if (uIManager2 != null) {
            uIManager2.registerLifecycleEventListener(this);
        }
    }

    @ExpoMethod
    @Deprecated
    public void requestPermissionsAsync(final Promise promise) {
        if (this.mPermissionsManager == null) {
            promise.reject("E_NO_PERMISSIONS", "Permissions module is null. Are you sure all the installed Expo modules are properly linked?");
        } else if (Build.VERSION.SDK_INT == 29) {
            this.mPermissionsManager.askForPermissions(new PermissionsResponseListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda1
                @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
                public final void onResult(Map map) {
                    LocationModule.this.m212x4e3df246(promise, map);
                }
            }, "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION");
        } else {
            requestForegroundPermissionsAsync(promise);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$requestPermissionsAsync$0$expo-modules-location-LocationModule */
    public /* synthetic */ void m212x4e3df246(Promise promise, Map map) {
        promise.resolve(handleLegacyPermissions(map));
    }

    @ExpoMethod
    @Deprecated
    public void getPermissionsAsync(final Promise promise) {
        if (this.mPermissionsManager == null) {
            promise.reject("E_NO_PERMISSIONS", "Permissions module is null. Are you sure all the installed Expo modules are properly linked?");
        } else if (Build.VERSION.SDK_INT == 29) {
            this.mPermissionsManager.getPermissions(new PermissionsResponseListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda15
                @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
                public final void onResult(Map map) {
                    LocationModule.this.m215xa972a4be(promise, map);
                }
            }, "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION");
        } else {
            getForegroundPermissionsAsync(promise);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getPermissionsAsync$1$expo-modules-location-LocationModule */
    public /* synthetic */ void m215xa972a4be(Promise promise, Map map) {
        promise.resolve(handleLegacyPermissions(map));
    }

    @ExpoMethod
    public void requestForegroundPermissionsAsync(final Promise promise) {
        Permissions permissions = this.mPermissionsManager;
        if (permissions == null) {
            promise.reject("E_NO_PERMISSIONS", "Permissions module is null. Are you sure all the installed Expo modules are properly linked?");
        } else {
            permissions.askForPermissions(new PermissionsResponseListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda17
                @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
                public final void onResult(Map map) {
                    LocationModule.this.m213x4495d941(promise, map);
                }
            }, "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$requestForegroundPermissionsAsync$2$expo-modules-location-LocationModule */
    public /* synthetic */ void m213x4495d941(Promise promise, Map map) {
        promise.resolve(handleForegroundLocationPermissions(map));
    }

    @ExpoMethod
    public void requestBackgroundPermissionsAsync(final Promise promise) {
        if (this.mPermissionsManager == null) {
            promise.reject("E_NO_PERMISSIONS", "Permissions module is null. Are you sure all the installed Expo modules are properly linked?");
        } else if (!isBackgroundPermissionInManifest()) {
            promise.reject("ERR_NO_PERMISSIONS", "You need to add `ACCESS_BACKGROUND_LOCATION` to the AndroidManifest.");
        } else if (!shouldAskBackgroundPermissions()) {
            requestForegroundPermissionsAsync(promise);
        } else {
            this.mPermissionsManager.askForPermissions(new PermissionsResponseListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda16
                @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
                public final void onResult(Map map) {
                    LocationModule.this.m214xb9aa3d95(promise, map);
                }
            }, "android.permission.ACCESS_BACKGROUND_LOCATION");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$requestBackgroundPermissionsAsync$3$expo-modules-location-LocationModule */
    public /* synthetic */ void m214xb9aa3d95(Promise promise, Map map) {
        promise.resolve(handleBackgroundLocationPermissions(map));
    }

    @ExpoMethod
    public void getForegroundPermissionsAsync(final Promise promise) {
        Permissions permissions = this.mPermissionsManager;
        if (permissions == null) {
            promise.reject("E_NO_PERMISSIONS", "Permissions module is null. Are you sure all the installed Expo modules are properly linked?");
        } else {
            permissions.getPermissions(new PermissionsResponseListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda14
                @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
                public final void onResult(Map map) {
                    LocationModule.this.m216x87226d18(promise, map);
                }
            }, "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getForegroundPermissionsAsync$4$expo-modules-location-LocationModule */
    public /* synthetic */ void m216x87226d18(Promise promise, Map map) {
        promise.resolve(handleForegroundLocationPermissions(map));
    }

    @ExpoMethod
    public void getBackgroundPermissionsAsync(final Promise promise) {
        if (this.mPermissionsManager == null) {
            promise.reject("E_NO_PERMISSIONS", "Permissions module is null. Are you sure all the installed Expo modules are properly linked?");
        } else if (!isBackgroundPermissionInManifest()) {
            promise.reject("ERR_NO_PERMISSIONS", "You need to add `ACCESS_BACKGROUND_LOCATION` to the AndroidManifest.");
        } else if (!shouldAskBackgroundPermissions()) {
            getForegroundPermissionsAsync(promise);
        } else {
            this.mPermissionsManager.getPermissions(new PermissionsResponseListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda13
                @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
                public final void onResult(Map map) {
                    LocationModule.this.m218xfc36d16c(promise, map);
                }
            }, "android.permission.ACCESS_BACKGROUND_LOCATION");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getBackgroundPermissionsAsync$5$expo-modules-location-LocationModule */
    public /* synthetic */ void m218xfc36d16c(Promise promise, Map map) {
        promise.resolve(handleBackgroundLocationPermissions(map));
    }

    @ExpoMethod
    public void getLastKnownPositionAsync(final Map<String, Object> map, final Promise promise) {
        if (isMissingForegroundPermissions()) {
            promise.reject(new LocationUnauthorizedException());
        } else {
            getLastKnownLocation(new OnResultListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda5
                @Override // expo.modules.location.LocationModule.OnResultListener
                public final void onResult(Location location) {
                    LocationModule.lambda$getLastKnownPositionAsync$6(map, promise, location);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getLastKnownPositionAsync$6(Map map, Promise promise, Location location) {
        if (LocationHelpers.isLocationValid(location, map)) {
            promise.resolve(LocationHelpers.locationToBundle(location, Bundle.class));
        } else {
            promise.resolve(null);
        }
    }

    @ExpoMethod
    public void getCurrentPositionAsync(Map<String, Object> map, final Promise promise) {
        final LocationRequest prepareLocationRequest = LocationHelpers.prepareLocationRequest(map);
        boolean z = !map.containsKey(SHOW_USER_SETTINGS_DIALOG_KEY) || ((Boolean) map.get(SHOW_USER_SETTINGS_DIALOG_KEY)).booleanValue();
        if (isMissingForegroundPermissions()) {
            promise.reject(new LocationUnauthorizedException());
        } else if (LocationHelpers.hasNetworkProviderEnabled(this.mContext) || !z) {
            LocationHelpers.requestSingleLocation(this, prepareLocationRequest, promise);
        } else {
            addPendingLocationRequest(prepareLocationRequest, new LocationActivityResultListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda4
                @Override // expo.modules.location.LocationActivityResultListener
                public final void onResult(int r4) {
                    LocationModule.this.m217x4f086d36(prepareLocationRequest, promise, r4);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getCurrentPositionAsync$7$expo-modules-location-LocationModule */
    public /* synthetic */ void m217x4f086d36(LocationRequest locationRequest, Promise promise, int r4) {
        if (r4 == -1) {
            LocationHelpers.requestSingleLocation(this, locationRequest, promise);
        } else {
            promise.reject(new LocationSettingsUnsatisfiedException());
        }
    }

    @ExpoMethod
    public void getProviderStatusAsync(Promise promise) {
        Context context = this.mContext;
        if (context == null) {
            promise.reject("E_CONTEXT_UNAVAILABLE", "Context is not available");
            return;
        }
        LocationState state = SmartLocation.with(context).location().state();
        Bundle bundle = new Bundle();
        bundle.putBoolean("locationServicesEnabled", state.locationServicesEnabled());
        bundle.putBoolean("gpsAvailable", state.isGpsAvailable());
        bundle.putBoolean("networkAvailable", state.isNetworkAvailable());
        bundle.putBoolean("passiveAvailable", state.isPassiveAvailable());
        bundle.putBoolean("backgroundModeEnabled", state.locationServicesEnabled());
        promise.resolve(bundle);
    }

    @ExpoMethod
    public void watchDeviceHeading(int r3, Promise promise) {
        this.mSensorManager = (SensorManager) this.mContext.getSystemService("sensor");
        this.mHeadingId = r3;
        startHeadingUpdate();
        promise.resolve(null);
    }

    @ExpoMethod
    public void watchPositionImplAsync(final int r4, Map<String, Object> map, final Promise promise) {
        if (isMissingForegroundPermissions()) {
            promise.reject(new LocationUnauthorizedException());
            return;
        }
        final LocationRequest prepareLocationRequest = LocationHelpers.prepareLocationRequest(map);
        boolean z = !map.containsKey(SHOW_USER_SETTINGS_DIALOG_KEY) || ((Boolean) map.get(SHOW_USER_SETTINGS_DIALOG_KEY)).booleanValue();
        if (LocationHelpers.hasNetworkProviderEnabled(this.mContext) || !z) {
            LocationHelpers.requestContinuousUpdates(this, prepareLocationRequest, r4, promise);
        } else {
            addPendingLocationRequest(prepareLocationRequest, new LocationActivityResultListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda3
                @Override // expo.modules.location.LocationActivityResultListener
                public final void onResult(int r5) {
                    LocationModule.this.m207x14e43d07(prepareLocationRequest, r4, promise, r5);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$watchPositionImplAsync$8$expo-modules-location-LocationModule */
    public /* synthetic */ void m207x14e43d07(LocationRequest locationRequest, int r3, Promise promise, int r5) {
        if (r5 == -1) {
            LocationHelpers.requestContinuousUpdates(this, locationRequest, r3, promise);
        } else {
            promise.reject(new LocationSettingsUnsatisfiedException());
        }
    }

    @ExpoMethod
    public void removeWatchAsync(int r2, Promise promise) {
        if (isMissingForegroundPermissions()) {
            promise.reject(new LocationUnauthorizedException());
            return;
        }
        if (r2 == this.mHeadingId) {
            destroyHeadingWatch();
        } else {
            removeLocationUpdatesForRequest(Integer.valueOf(r2));
        }
        promise.resolve(null);
    }

    @ExpoMethod
    public void geocodeAsync(String str, final Promise promise) {
        if (this.mGeocoderPaused) {
            promise.reject("E_CANNOT_GEOCODE", "Geocoder is not running.");
        } else if (isMissingForegroundPermissions()) {
            promise.reject(new LocationUnauthorizedException());
        } else if (Geocoder.isPresent()) {
            SmartLocation.with(this.mContext).geocoding().direct(str, new OnGeocodingListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda6
                @Override // io.nlopez.smartlocation.OnGeocodingListener
                public final void onLocationResolved(String str2, List list) {
                    LocationModule.this.m1687lambda$geocodeAsync$9$expomoduleslocationLocationModule(promise, str2, list);
                }
            });
        } else {
            promise.reject("E_NO_GEOCODER", "Geocoder service is not available for this device.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$geocodeAsync$9$expo-modules-location-LocationModule  reason: not valid java name */
    public /* synthetic */ void m1687lambda$geocodeAsync$9$expomoduleslocationLocationModule(Promise promise, String str, List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Bundle bundle = (Bundle) LocationHelpers.locationToCoordsBundle(((LocationAddress) it.next()).getLocation(), Bundle.class);
            if (bundle != null) {
                arrayList.add(bundle);
            }
        }
        SmartLocation.with(this.mContext).geocoding().stop();
        promise.resolve(arrayList);
    }

    @ExpoMethod
    public void reverseGeocodeAsync(Map<String, Object> map, final Promise promise) {
        if (this.mGeocoderPaused) {
            promise.reject("E_CANNOT_GEOCODE", "Geocoder is not running.");
        } else if (isMissingForegroundPermissions()) {
            promise.reject(new LocationUnauthorizedException());
        } else {
            Location location = new Location("");
            location.setLatitude(((Double) map.get("latitude")).doubleValue());
            location.setLongitude(((Double) map.get("longitude")).doubleValue());
            if (Geocoder.isPresent()) {
                SmartLocation.with(this.mContext).geocoding().reverse(location, new OnReverseGeocodingListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda8
                    @Override // io.nlopez.smartlocation.OnReverseGeocodingListener
                    public final void onAddressResolved(Location location2, List list) {
                        LocationModule.this.m209xb9ed6b5c(promise, location2, list);
                    }
                });
            } else {
                promise.reject("E_NO_GEOCODER", "Geocoder service is not available for this device.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$reverseGeocodeAsync$10$expo-modules-location-LocationModule */
    public /* synthetic */ void m209xb9ed6b5c(Promise promise, Location location, List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(LocationHelpers.addressToBundle((Address) it.next()));
        }
        SmartLocation.with(this.mContext).geocoding().stop();
        promise.resolve(arrayList);
    }

    @ExpoMethod
    public void enableNetworkProviderAsync(final Promise promise) {
        if (LocationHelpers.hasNetworkProviderEnabled(this.mContext)) {
            promise.resolve(null);
        } else {
            addPendingLocationRequest(LocationHelpers.prepareLocationRequest(new HashMap()), new LocationActivityResultListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda2
                @Override // expo.modules.location.LocationActivityResultListener
                public final void onResult(int r2) {
                    LocationModule.lambda$enableNetworkProviderAsync$11(Promise.this, r2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$enableNetworkProviderAsync$11(Promise promise, int r2) {
        if (r2 == -1) {
            promise.resolve(null);
        } else {
            promise.reject(new LocationSettingsUnsatisfiedException());
        }
    }

    @ExpoMethod
    public void hasServicesEnabledAsync(Promise promise) {
        promise.resolve(Boolean.valueOf(LocationHelpers.isAnyProviderAvailable(getContext())));
    }

    @ExpoMethod
    public void startLocationUpdatesAsync(String str, Map<String, Object> map, Promise promise) {
        if (!LocationTaskConsumer.shouldUseForegroundService(map) && isMissingBackgroundPermissions()) {
            promise.reject(new LocationBackgroundUnauthorizedException());
            return;
        }
        try {
            this.mTaskManager.registerTask(str, LocationTaskConsumer.class, map);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ExpoMethod
    public void stopLocationUpdatesAsync(String str, Promise promise) {
        try {
            this.mTaskManager.unregisterTask(str, LocationTaskConsumer.class);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ExpoMethod
    public void hasStartedLocationUpdatesAsync(String str, Promise promise) {
        promise.resolve(Boolean.valueOf(this.mTaskManager.taskHasConsumerOfClass(str, LocationTaskConsumer.class)));
    }

    @ExpoMethod
    public void startGeofencingAsync(String str, Map<String, Object> map, Promise promise) {
        if (isMissingBackgroundPermissions()) {
            promise.reject(new LocationBackgroundUnauthorizedException());
            return;
        }
        try {
            this.mTaskManager.registerTask(str, GeofencingTaskConsumer.class, map);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ExpoMethod
    public void stopGeofencingAsync(String str, Promise promise) {
        if (isMissingBackgroundPermissions()) {
            promise.reject(new LocationBackgroundUnauthorizedException());
            return;
        }
        try {
            this.mTaskManager.unregisterTask(str, GeofencingTaskConsumer.class);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ExpoMethod
    public void hasStartedGeofencingAsync(String str, Promise promise) {
        if (isMissingBackgroundPermissions()) {
            promise.reject(new LocationBackgroundUnauthorizedException());
        } else {
            promise.resolve(Boolean.valueOf(this.mTaskManager.taskHasConsumerOfClass(str, GeofencingTaskConsumer.class)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestLocationUpdates(LocationRequest locationRequest, Integer num, final LocationRequestCallbacks locationRequestCallbacks) {
        FusedLocationProviderClient locationProvider = getLocationProvider();
        LocationCallback locationCallback = new LocationCallback() { // from class: expo.modules.location.LocationModule.1
            boolean isLocationAvailable = false;

            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(LocationResult locationResult) {
                Location lastLocation = locationResult != null ? locationResult.getLastLocation() : null;
                if (lastLocation != null) {
                    locationRequestCallbacks.onLocationChanged(lastLocation);
                } else if (this.isLocationAvailable) {
                } else {
                    locationRequestCallbacks.onLocationError(new LocationUnavailableException());
                }
            }

            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                this.isLocationAvailable = locationAvailability.isLocationAvailable();
            }
        };
        if (num != null) {
            this.mLocationCallbacks.put(num, locationCallback);
            this.mLocationRequests.put(num, locationRequest);
        }
        try {
            locationProvider.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
            locationRequestCallbacks.onRequestSuccess();
        } catch (SecurityException e) {
            locationRequestCallbacks.onRequestFailed(new LocationRequestRejectedException(e));
        }
    }

    private boolean isMissingForegroundPermissions() {
        Permissions permissions = this.mPermissionsManager;
        if (permissions == null) {
            return true;
        }
        return (permissions.hasGrantedPermissions("android.permission.ACCESS_FINE_LOCATION") || this.mPermissionsManager.hasGrantedPermissions("android.permission.ACCESS_COARSE_LOCATION")) ? false : true;
    }

    private boolean isMissingBackgroundPermissions() {
        return this.mPermissionsManager == null || (Build.VERSION.SDK_INT >= 29 && !this.mPermissionsManager.hasGrantedPermissions("android.permission.ACCESS_BACKGROUND_LOCATION"));
    }

    private FusedLocationProviderClient getLocationProvider() {
        if (this.mLocationProvider == null) {
            this.mLocationProvider = LocationServices.getFusedLocationProviderClient(this.mContext);
        }
        return this.mLocationProvider;
    }

    private void getLastKnownLocation(final OnResultListener onResultListener) {
        try {
            getLocationProvider().getLastLocation().addOnSuccessListener(new OnSuccessListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda11
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    LocationModule.OnResultListener.this.onResult((Location) obj);
                }
            }).addOnCanceledListener(new OnCanceledListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnCanceledListener
                public final void onCanceled() {
                    LocationModule.OnResultListener.this.onResult(null);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda9
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    LocationModule.OnResultListener.this.onResult(null);
                }
            });
        } catch (SecurityException unused) {
            onResultListener.onResult(null);
        }
    }

    private void addPendingLocationRequest(LocationRequest locationRequest, LocationActivityResultListener locationActivityResultListener) {
        this.mPendingLocationRequests.add(locationActivityResultListener);
        if (this.mPendingLocationRequests.size() == 1) {
            resolveUserSettingsForRequest(locationRequest);
        }
    }

    private void resolveUserSettingsForRequest(LocationRequest locationRequest) {
        final Activity currentActivity = this.mActivityProvider.getCurrentActivity();
        if (currentActivity == null) {
            executePendingRequests(0);
            return;
        }
        Task<LocationSettingsResponse> checkLocationSettings = LocationServices.getSettingsClient(this.mContext).checkLocationSettings(new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build());
        checkLocationSettings.addOnSuccessListener(new OnSuccessListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda12
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                LocationModule.this.m211x62ea3c97((LocationSettingsResponse) obj);
            }
        });
        checkLocationSettings.addOnFailureListener(new OnFailureListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda10
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LocationModule.this.m210xc43cd936(currentActivity, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$resolveUserSettingsForRequest$15$expo-modules-location-LocationModule */
    public /* synthetic */ void m211x62ea3c97(LocationSettingsResponse locationSettingsResponse) {
        executePendingRequests(-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$resolveUserSettingsForRequest$16$expo-modules-location-LocationModule */
    public /* synthetic */ void m210xc43cd936(Activity activity, Exception exc) {
        if (((ApiException) exc).getStatusCode() == 6) {
            try {
                this.mUIManager.registerActivityEventListener(this);
                ((ResolvableApiException) exc).startResolutionForResult(activity, 42);
                return;
            } catch (IntentSender.SendIntentException unused) {
                executePendingRequests(0);
                return;
            }
        }
        executePendingRequests(0);
    }

    private void pauseLocationUpdatesForRequest(Integer num) {
        LocationCallback locationCallback = this.mLocationCallbacks.get(num);
        if (locationCallback != null) {
            getLocationProvider().removeLocationUpdates(locationCallback);
        }
    }

    private void resumeLocationUpdates() {
        FusedLocationProviderClient locationProvider = getLocationProvider();
        for (Integer num : this.mLocationCallbacks.keySet()) {
            LocationCallback locationCallback = this.mLocationCallbacks.get(num);
            LocationRequest locationRequest = this.mLocationRequests.get(num);
            if (locationCallback != null && locationRequest != null) {
                try {
                    locationProvider.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                } catch (SecurityException e) {
                    String str = TAG;
                    Log.e(str, "Error occurred while resuming location updates: " + e.toString());
                }
            }
        }
    }

    private void removeLocationUpdatesForRequest(Integer num) {
        pauseLocationUpdatesForRequest(num);
        this.mLocationCallbacks.remove(num);
        this.mLocationRequests.remove(num);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendLocationResponse(int r2, Bundle bundle) {
        bundle.putInt("watchId", r2);
        this.mEventEmitter.emit(LOCATION_EVENT_NAME, bundle);
    }

    private void executePendingRequests(int r3) {
        for (LocationActivityResultListener locationActivityResultListener : this.mPendingLocationRequests) {
            locationActivityResultListener.onResult(r3);
        }
        this.mPendingLocationRequests.clear();
    }

    private void startHeadingUpdate() {
        Context context;
        if (this.mSensorManager == null || (context = this.mContext) == null) {
            return;
        }
        SmartLocation.LocationControl config = SmartLocation.with(context).location().oneFix().config(LocationParams.BEST_EFFORT);
        Location lastLocation = config.getLastLocation();
        if (lastLocation != null) {
            this.mGeofield = new GeomagneticField((float) lastLocation.getLatitude(), (float) lastLocation.getLongitude(), (float) lastLocation.getAltitude(), System.currentTimeMillis());
        } else {
            config.start(new OnLocationUpdatedListener() { // from class: expo.modules.location.LocationModule$$ExternalSyntheticLambda7
                @Override // io.nlopez.smartlocation.OnLocationUpdatedListener
                public final void onLocationUpdated(Location location) {
                    LocationModule.this.m208x914418d8(location);
                }
            });
        }
        SensorManager sensorManager = this.mSensorManager;
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(2), 3);
        SensorManager sensorManager2 = this.mSensorManager;
        sensorManager2.registerListener(this, sensorManager2.getDefaultSensor(1), 3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startHeadingUpdate$17$expo-modules-location-LocationModule */
    public /* synthetic */ void m208x914418d8(Location location) {
        this.mGeofield = new GeomagneticField((float) location.getLatitude(), (float) location.getLongitude(), (float) location.getAltitude(), System.currentTimeMillis());
    }

    private void sendUpdate() {
        float[] fArr = new float[9];
        if (SensorManager.getRotationMatrix(fArr, new float[9], this.mGravity, this.mGeomagnetic)) {
            float[] fArr2 = new float[3];
            SensorManager.getOrientation(fArr, fArr2);
            if (Math.abs(fArr2[0] - this.mLastAzimuth) <= DEGREE_DELTA || ((float) (System.currentTimeMillis() - this.mLastUpdate)) <= TIME_DELTA) {
                return;
            }
            this.mLastAzimuth = fArr2[0];
            this.mLastUpdate = System.currentTimeMillis();
            float calcMagNorth = calcMagNorth(fArr2[0]);
            float calcTrueNorth = calcTrueNorth(calcMagNorth);
            Bundle bundle = new Bundle();
            Bundle headingToBundle = LocationHelpers.headingToBundle(calcTrueNorth, calcMagNorth, this.mAccuracy);
            bundle.putInt("watchId", this.mHeadingId);
            bundle.putBundle("heading", headingToBundle);
            this.mEventEmitter.emit(HEADING_EVENT_NAME, bundle);
        }
    }

    private float calcMagNorth(float f) {
        return (((float) Math.toDegrees(f)) + 360.0f) % 360.0f;
    }

    private float calcTrueNorth(float f) {
        GeomagneticField geomagneticField;
        if (isMissingForegroundPermissions() || (geomagneticField = this.mGeofield) == null) {
            return -1.0f;
        }
        return (f + geomagneticField.getDeclination()) % 360.0f;
    }

    private void stopHeadingWatch() {
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager == null) {
            return;
        }
        sensorManager.unregisterListener(this);
    }

    private void destroyHeadingWatch() {
        stopHeadingWatch();
        this.mSensorManager = null;
        this.mGravity = null;
        this.mGeomagnetic = null;
        this.mGeofield = null;
        this.mHeadingId = 0;
        this.mLastAzimuth = 0.0f;
        this.mAccuracy = 0;
    }

    private void startWatching() {
        if (this.mContext == null) {
            return;
        }
        if (!isMissingForegroundPermissions()) {
            this.mGeocoderPaused = false;
        }
        resumeLocationUpdates();
    }

    private void stopWatching() {
        if (this.mContext == null) {
            return;
        }
        if (Geocoder.isPresent() && !isMissingForegroundPermissions()) {
            SmartLocation.with(this.mContext).geocoding().stop();
            this.mGeocoderPaused = true;
        }
        for (Integer num : this.mLocationCallbacks.keySet()) {
            pauseLocationUpdatesForRequest(num);
        }
    }

    private Bundle handleForegroundLocationPermissions(Map<String, PermissionsResponse> map) {
        PermissionsResponse permissionsResponse = map.get("android.permission.ACCESS_FINE_LOCATION");
        PermissionsResponse permissionsResponse2 = map.get("android.permission.ACCESS_COARSE_LOCATION");
        Objects.requireNonNull(permissionsResponse);
        Objects.requireNonNull(permissionsResponse2);
        PermissionsStatus permissionsStatus = PermissionsStatus.UNDETERMINED;
        boolean z = permissionsResponse2.getCanAskAgain() && permissionsResponse.getCanAskAgain();
        String str = "none";
        if (permissionsResponse.getStatus() == PermissionsStatus.GRANTED) {
            permissionsStatus = PermissionsStatus.GRANTED;
            str = "fine";
        } else if (permissionsResponse2.getStatus() == PermissionsStatus.GRANTED) {
            permissionsStatus = PermissionsStatus.GRANTED;
            str = "coarse";
        } else if (permissionsResponse.getStatus() == PermissionsStatus.DENIED && permissionsResponse2.getStatus() == PermissionsStatus.DENIED) {
            permissionsStatus = PermissionsStatus.DENIED;
        }
        Bundle bundle = new Bundle();
        bundle.putString("status", permissionsStatus.getStatus());
        bundle.putString(PermissionsResponse.EXPIRES_KEY, "never");
        bundle.putBoolean(PermissionsResponse.CAN_ASK_AGAIN_KEY, z);
        bundle.putBoolean(PermissionsResponse.GRANTED_KEY, permissionsStatus == PermissionsStatus.GRANTED);
        Bundle bundle2 = new Bundle();
        bundle2.putString("scoped", str);
        bundle2.putString("accuracy", str);
        bundle.putBundle(DeviceInfo.OS_NAME, bundle2);
        return bundle;
    }

    private Bundle handleBackgroundLocationPermissions(Map<String, PermissionsResponse> map) {
        PermissionsResponse permissionsResponse = map.get("android.permission.ACCESS_BACKGROUND_LOCATION");
        Objects.requireNonNull(permissionsResponse);
        PermissionsStatus status = permissionsResponse.getStatus();
        boolean canAskAgain = permissionsResponse.getCanAskAgain();
        Bundle bundle = new Bundle();
        bundle.putString("status", status.getStatus());
        bundle.putString(PermissionsResponse.EXPIRES_KEY, "never");
        bundle.putBoolean(PermissionsResponse.CAN_ASK_AGAIN_KEY, canAskAgain);
        bundle.putBoolean(PermissionsResponse.GRANTED_KEY, status == PermissionsStatus.GRANTED);
        return bundle;
    }

    private Bundle handleLegacyPermissions(Map<String, PermissionsResponse> map) {
        PermissionsResponse permissionsResponse = map.get("android.permission.ACCESS_FINE_LOCATION");
        PermissionsResponse permissionsResponse2 = map.get("android.permission.ACCESS_COARSE_LOCATION");
        Objects.requireNonNull(permissionsResponse);
        Objects.requireNonNull(permissionsResponse2);
        Objects.requireNonNull(map.get("android.permission.ACCESS_BACKGROUND_LOCATION"));
        PermissionsStatus permissionsStatus = PermissionsStatus.UNDETERMINED;
        boolean z = permissionsResponse2.getCanAskAgain() && permissionsResponse.getCanAskAgain();
        String str = "none";
        if (permissionsResponse.getStatus() == PermissionsStatus.GRANTED) {
            permissionsStatus = PermissionsStatus.GRANTED;
            str = "fine";
        } else if (permissionsResponse2.getStatus() == PermissionsStatus.GRANTED) {
            permissionsStatus = PermissionsStatus.GRANTED;
            str = "coarse";
        } else if (permissionsResponse.getStatus() == PermissionsStatus.DENIED && permissionsResponse2.getStatus() == PermissionsStatus.DENIED) {
            permissionsStatus = PermissionsStatus.DENIED;
        }
        Bundle bundle = new Bundle();
        bundle.putString("status", permissionsStatus.getStatus());
        bundle.putString(PermissionsResponse.EXPIRES_KEY, "never");
        bundle.putBoolean(PermissionsResponse.CAN_ASK_AGAIN_KEY, z);
        bundle.putBoolean(PermissionsResponse.GRANTED_KEY, permissionsStatus == PermissionsStatus.GRANTED);
        Bundle bundle2 = new Bundle();
        bundle2.putString("accuracy", str);
        bundle.putBundle(DeviceInfo.OS_NAME, bundle2);
        return bundle;
    }

    private boolean shouldAskBackgroundPermissions() {
        return Build.VERSION.SDK_INT >= 29;
    }

    private boolean isBackgroundPermissionInManifest() {
        if (Build.VERSION.SDK_INT >= 29) {
            return this.mPermissionsManager.isPermissionPresentInManifest("android.permission.ACCESS_BACKGROUND_LOCATION");
        }
        return true;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 1) {
            this.mGravity = sensorEvent.values;
        } else if (sensorEvent.sensor.getType() == 2) {
            this.mGeomagnetic = sensorEvent.values;
        }
        if (this.mGravity == null || this.mGeomagnetic == null) {
            return;
        }
        sendUpdate();
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int r2) {
        this.mAccuracy = r2;
    }

    @Override // expo.modules.core.interfaces.ActivityEventListener
    public void onActivityResult(Activity activity, int r2, int r3, Intent intent) {
        if (r2 != 42) {
            return;
        }
        executePendingRequests(r3);
        this.mUIManager.unregisterActivityEventListener(this);
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
        startWatching();
        startHeadingUpdate();
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
        stopWatching();
        stopHeadingWatch();
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
        stopWatching();
        stopHeadingWatch();
    }
}
