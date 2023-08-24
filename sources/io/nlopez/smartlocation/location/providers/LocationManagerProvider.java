package io.nlopez.smartlocation.location.providers;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import androidx.core.app.ActivityCompat;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.geofencing.providers.GeofencingGooglePlayServicesProvider;
import io.nlopez.smartlocation.location.LocationProvider;
import io.nlopez.smartlocation.location.LocationStore;
import io.nlopez.smartlocation.location.config.LocationAccuracy;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.utils.Logger;

/* loaded from: classes5.dex */
public class LocationManagerProvider implements LocationProvider, LocationListener {
    private static final String LOCATIONMANAGERPROVIDER_ID = "LMP";
    private OnLocationUpdatedListener listener;
    private LocationManager locationManager;
    private LocationStore locationStore;
    private Logger logger;
    private Context mContext;

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int r2, Bundle bundle) {
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void init(Context context, Logger logger) {
        this.locationManager = (LocationManager) context.getSystemService(GeofencingGooglePlayServicesProvider.LOCATION_EXTRA_ID);
        this.logger = logger;
        this.mContext = context;
        this.locationStore = new LocationStore(context);
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void start(OnLocationUpdatedListener onLocationUpdatedListener, LocationParams locationParams, boolean z) {
        this.listener = onLocationUpdatedListener;
        if (onLocationUpdatedListener == null) {
            this.logger.mo195d("Listener is null, you sure about this?", new Object[0]);
        }
        Criteria provider = getProvider(locationParams);
        if (z) {
            if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                this.logger.mo191i("Permission check failed. Please handle it in your app before setting up location", new Object[0]);
                return;
            } else {
                this.locationManager.requestSingleUpdate(provider, this, Looper.getMainLooper());
                return;
            }
        }
        this.locationManager.requestLocationUpdates(locationParams.getInterval(), locationParams.getDistance(), provider, this, Looper.getMainLooper());
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public void stop() {
        if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.locationManager.removeUpdates(this);
        }
    }

    @Override // io.nlopez.smartlocation.location.LocationProvider
    public Location getLastLocation() {
        if (this.locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                return null;
            }
            Location lastKnownLocation = this.locationManager.getLastKnownLocation("gps");
            if (lastKnownLocation != null) {
                return lastKnownLocation;
            }
        }
        Location location = this.locationStore.get(LOCATIONMANAGERPROVIDER_ID);
        if (location != null) {
            return location;
        }
        return null;
    }

    private Criteria getProvider(LocationParams locationParams) {
        LocationAccuracy accuracy = locationParams.getAccuracy();
        Criteria criteria = new Criteria();
        int r4 = C46521.f1489x1a467bf9[accuracy.ordinal()];
        if (r4 == 1) {
            criteria.setAccuracy(1);
            criteria.setHorizontalAccuracy(3);
            criteria.setVerticalAccuracy(3);
            criteria.setBearingAccuracy(3);
            criteria.setSpeedAccuracy(3);
            criteria.setPowerRequirement(3);
        } else if (r4 == 2) {
            criteria.setAccuracy(2);
            criteria.setHorizontalAccuracy(2);
            criteria.setVerticalAccuracy(2);
            criteria.setBearingAccuracy(2);
            criteria.setSpeedAccuracy(2);
            criteria.setPowerRequirement(2);
        } else {
            criteria.setAccuracy(2);
            criteria.setHorizontalAccuracy(1);
            criteria.setVerticalAccuracy(1);
            criteria.setBearingAccuracy(1);
            criteria.setSpeedAccuracy(1);
            criteria.setPowerRequirement(1);
        }
        return criteria;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.nlopez.smartlocation.location.providers.LocationManagerProvider$1 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C46521 {

        /* renamed from: $SwitchMap$io$nlopez$smartlocation$location$config$LocationAccuracy */
        static final /* synthetic */ int[] f1489x1a467bf9;

        static {
            int[] r0 = new int[LocationAccuracy.values().length];
            f1489x1a467bf9 = r0;
            try {
                r0[LocationAccuracy.HIGH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1489x1a467bf9[LocationAccuracy.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1489x1a467bf9[LocationAccuracy.LOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1489x1a467bf9[LocationAccuracy.LOWEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        this.logger.mo195d("onLocationChanged", location);
        OnLocationUpdatedListener onLocationUpdatedListener = this.listener;
        if (onLocationUpdatedListener != null) {
            onLocationUpdatedListener.onLocationUpdated(location);
        }
        if (this.locationStore != null) {
            this.logger.mo195d("Stored in SharedPreferences", new Object[0]);
            this.locationStore.put(LOCATIONMANAGERPROVIDER_ID, location);
        }
    }
}