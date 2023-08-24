package expo.modules.location;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.BaseBundle;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.work.WorkRequest;
import com.amplitude.api.Constants;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.gms.location.LocationRequest;
import expo.modules.core.Promise;
import expo.modules.core.errors.CodedException;
import io.nlopez.smartlocation.geofencing.providers.GeofencingGooglePlayServicesProvider;
import io.nlopez.smartlocation.location.config.LocationAccuracy;
import io.nlopez.smartlocation.location.config.LocationParams;
import java.util.Map;

/* loaded from: classes4.dex */
public class LocationHelpers {
    private static final String TAG = "LocationHelpers";

    private static int mapAccuracyToPriority(int r1) {
        if (r1 != 1) {
            return (r1 == 4 || r1 == 5 || r1 == 6) ? 100 : 102;
        }
        return 104;
    }

    public static boolean isAnyProviderAvailable(Context context) {
        LocationManager locationManager;
        if (context == null || (locationManager = (LocationManager) context.getSystemService(GeofencingGooglePlayServicesProvider.LOCATION_EXTRA_ID)) == null) {
            return false;
        }
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    public static boolean hasNetworkProviderEnabled(Context context) {
        LocationManager locationManager;
        return (context == null || (locationManager = (LocationManager) context.getSystemService(GeofencingGooglePlayServicesProvider.LOCATION_EXTRA_ID)) == null || !locationManager.isProviderEnabled("network")) ? false : true;
    }

    public static <BundleType extends BaseBundle> BundleType locationToBundle(Location location, Class<BundleType> cls) {
        if (location == null) {
            return null;
        }
        try {
            BundleType newInstance = cls.newInstance();
            BaseBundle locationToCoordsBundle = locationToCoordsBundle(location, cls);
            if (locationToCoordsBundle == null) {
                return null;
            }
            if (newInstance instanceof PersistableBundle) {
                ((PersistableBundle) newInstance).putPersistableBundle("coords", (PersistableBundle) locationToCoordsBundle);
            } else if (newInstance instanceof Bundle) {
                ((Bundle) newInstance).putBundle("coords", (Bundle) locationToCoordsBundle);
                ((Bundle) newInstance).putBoolean("mocked", location.isFromMockProvider());
            }
            newInstance.putDouble("timestamp", location.getTime());
            return newInstance;
        } catch (IllegalAccessException | InstantiationException e) {
            String str = TAG;
            Log.e(str, "Unexpected exception was thrown when converting location to the bundle: " + e.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <BundleType extends BaseBundle> BundleType locationToCoordsBundle(Location location, Class<BundleType> cls) {
        try {
            BundleType newInstance = cls.newInstance();
            newInstance.putDouble("latitude", location.getLatitude());
            newInstance.putDouble("longitude", location.getLongitude());
            newInstance.putDouble("altitude", location.getAltitude());
            newInstance.putDouble("accuracy", location.getAccuracy());
            newInstance.putDouble("heading", location.getBearing());
            newInstance.putDouble("speed", location.getSpeed());
            if (Build.VERSION.SDK_INT >= 26) {
                newInstance.putDouble("altitudeAccuracy", location.getVerticalAccuracyMeters());
            } else {
                newInstance.putString("altitudeAccuracy", null);
            }
            return newInstance;
        } catch (IllegalAccessException | InstantiationException e) {
            String str = TAG;
            Log.e(str, "Unexpected exception was thrown when converting location to coords bundle: " + e.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle addressToBundle(Address address) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.AMP_TRACKING_OPTION_CITY, address.getLocality());
        bundle.putString("district", address.getSubLocality());
        bundle.putString("streetNumber", address.getSubThoroughfare());
        bundle.putString("street", address.getThoroughfare());
        bundle.putString("region", address.getAdminArea());
        bundle.putString("subregion", address.getSubAdminArea());
        bundle.putString(Constants.AMP_TRACKING_OPTION_COUNTRY, address.getCountryName());
        bundle.putString(HintConstants.AUTOFILL_HINT_POSTAL_CODE, address.getPostalCode());
        bundle.putString("name", address.getFeatureName());
        bundle.putString("isoCountryCode", address.getCountryCode());
        bundle.putString("timezone", null);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle headingToBundle(double d, double d2, int r6) {
        Bundle bundle = new Bundle();
        bundle.putDouble("trueHeading", d);
        bundle.putDouble("magHeading", d2);
        bundle.putInt("accuracy", r6);
        return bundle;
    }

    public static LocationRequest prepareLocationRequest(Map<String, Object> map) {
        LocationParams mapOptionsToLocationParams = mapOptionsToLocationParams(map);
        return new LocationRequest().setFastestInterval(mapOptionsToLocationParams.getInterval()).setInterval(mapOptionsToLocationParams.getInterval()).setMaxWaitTime(mapOptionsToLocationParams.getInterval()).setSmallestDisplacement(mapOptionsToLocationParams.getDistance()).setPriority(mapAccuracyToPriority(getAccuracyFromOptions(map)));
    }

    public static LocationParams mapOptionsToLocationParams(Map<String, Object> map) {
        LocationParams.Builder buildLocationParamsForAccuracy = buildLocationParamsForAccuracy(getAccuracyFromOptions(map));
        if (map.containsKey("timeInterval")) {
            buildLocationParamsForAccuracy.setInterval(((Number) map.get("timeInterval")).longValue());
        }
        if (map.containsKey("distanceInterval")) {
            buildLocationParamsForAccuracy.setDistance(((Number) map.get("distanceInterval")).floatValue());
        }
        return buildLocationParamsForAccuracy.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void requestSingleLocation(LocationModule locationModule, LocationRequest locationRequest, final Promise promise) {
        locationRequest.setNumUpdates(1);
        locationModule.requestLocationUpdates(locationRequest, null, new LocationRequestCallbacks() { // from class: expo.modules.location.LocationHelpers.1
            @Override // expo.modules.location.LocationRequestCallbacks
            public void onLocationChanged(Location location) {
                Promise.this.resolve(LocationHelpers.locationToBundle(location, Bundle.class));
            }

            @Override // expo.modules.location.LocationRequestCallbacks
            public void onLocationError(CodedException codedException) {
                Promise.this.reject(codedException);
            }

            @Override // expo.modules.location.LocationRequestCallbacks
            public void onRequestFailed(CodedException codedException) {
                Promise.this.reject(codedException);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void requestContinuousUpdates(final LocationModule locationModule, LocationRequest locationRequest, final int r4, final Promise promise) {
        locationModule.requestLocationUpdates(locationRequest, Integer.valueOf(r4), new LocationRequestCallbacks() { // from class: expo.modules.location.LocationHelpers.2
            @Override // expo.modules.location.LocationRequestCallbacks
            public void onLocationChanged(Location location) {
                Bundle bundle = new Bundle();
                bundle.putBundle(GeofencingGooglePlayServicesProvider.LOCATION_EXTRA_ID, (Bundle) LocationHelpers.locationToBundle(location, Bundle.class));
                LocationModule.this.sendLocationResponse(r4, bundle);
            }

            @Override // expo.modules.location.LocationRequestCallbacks
            public void onRequestSuccess() {
                promise.resolve(null);
            }

            @Override // expo.modules.location.LocationRequestCallbacks
            public void onRequestFailed(CodedException codedException) {
                promise.reject(codedException);
            }
        });
    }

    public static boolean isLocationValid(Location location, Map<String, Object> map) {
        if (location == null) {
            return false;
        }
        return ((double) (System.currentTimeMillis() - location.getTime())) <= (map.containsKey("maxAge") ? ((Double) map.get("maxAge")).doubleValue() : Double.MAX_VALUE) && ((double) location.getAccuracy()) <= (map.containsKey("requiredAccuracy") ? ((Double) map.get("requiredAccuracy")).doubleValue() : Double.MAX_VALUE);
    }

    private static int getAccuracyFromOptions(Map<String, Object> map) {
        if (map.containsKey("accuracy")) {
            return ((Number) map.get("accuracy")).intValue();
        }
        return 3;
    }

    private static LocationParams.Builder buildLocationParamsForAccuracy(int r2) {
        if (r2 != 1) {
            if (r2 != 2) {
                if (r2 != 4) {
                    if (r2 != 5) {
                        if (r2 != 6) {
                            return new LocationParams.Builder().setAccuracy(LocationAccuracy.MEDIUM).setDistance(100.0f).setInterval(C1856C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                        }
                        return new LocationParams.Builder().setAccuracy(LocationAccuracy.HIGH).setDistance(0.0f).setInterval(500L);
                    }
                    return new LocationParams.Builder().setAccuracy(LocationAccuracy.HIGH).setDistance(25.0f).setInterval(1000L);
                }
                return new LocationParams.Builder().setAccuracy(LocationAccuracy.HIGH).setDistance(50.0f).setInterval(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
            }
            return new LocationParams.Builder().setAccuracy(LocationAccuracy.LOW).setDistance(1000.0f).setInterval(5000L);
        }
        return new LocationParams.Builder().setAccuracy(LocationAccuracy.LOWEST).setDistance(3000.0f).setInterval(WorkRequest.MIN_BACKOFF_MILLIS);
    }
}
