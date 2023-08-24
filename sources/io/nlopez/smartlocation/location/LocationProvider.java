package io.nlopez.smartlocation.location;

import android.content.Context;
import android.location.Location;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.utils.Logger;

/* loaded from: classes5.dex */
public interface LocationProvider {
    Location getLastLocation();

    void init(Context context, Logger logger);

    void start(OnLocationUpdatedListener onLocationUpdatedListener, LocationParams locationParams, boolean z);

    void stop();
}
