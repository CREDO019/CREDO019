package io.nlopez.smartlocation.geocoding;

import android.content.Context;
import android.location.Location;
import io.nlopez.smartlocation.OnGeocodingListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.utils.Logger;

/* loaded from: classes5.dex */
public interface GeocodingProvider {
    void addLocation(Location location, int r2);

    void addName(String str, int r2);

    void init(Context context, Logger logger);

    void start(OnGeocodingListener onGeocodingListener, OnReverseGeocodingListener onReverseGeocodingListener);

    void stop();
}
