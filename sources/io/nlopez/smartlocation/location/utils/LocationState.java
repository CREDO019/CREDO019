package io.nlopez.smartlocation.location.utils;

import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import io.nlopez.smartlocation.geofencing.providers.GeofencingGooglePlayServicesProvider;

/* loaded from: classes5.dex */
public class LocationState {
    private static LocationState instance;
    private Context context;
    private LocationManager locationManager;

    private LocationState(Context context) {
        this.context = context;
        this.locationManager = (LocationManager) context.getSystemService(GeofencingGooglePlayServicesProvider.LOCATION_EXTRA_ID);
    }

    public static LocationState with(Context context) {
        if (instance == null) {
            instance = new LocationState(context.getApplicationContext());
        }
        return instance;
    }

    public boolean locationServicesEnabled() {
        int r2;
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                r2 = Settings.Secure.getInt(this.context.getContentResolver(), "location_mode");
            } catch (Settings.SettingNotFoundException unused) {
                r2 = 0;
            }
            return r2 != 0;
        }
        return !TextUtils.isEmpty(Settings.Secure.getString(this.context.getContentResolver(), "location_providers_allowed"));
    }

    public boolean isAnyProviderAvailable() {
        return isGpsAvailable() || isNetworkAvailable();
    }

    public boolean isGpsAvailable() {
        return this.locationManager.isProviderEnabled("gps");
    }

    public boolean isNetworkAvailable() {
        return this.locationManager.isProviderEnabled("network");
    }

    public boolean isPassiveAvailable() {
        return this.locationManager.isProviderEnabled("passive");
    }

    @Deprecated
    public boolean isMockSettingEnabled() {
        return !SessionDescription.SUPPORTED_SDP_VERSION.equals(Settings.Secure.getString(this.context.getContentResolver(), "mock_location"));
    }
}
