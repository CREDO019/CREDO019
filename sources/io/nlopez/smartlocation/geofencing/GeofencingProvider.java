package io.nlopez.smartlocation.geofencing;

import android.content.Context;
import io.nlopez.smartlocation.OnGeofencingTransitionListener;
import io.nlopez.smartlocation.geofencing.model.GeofenceModel;
import io.nlopez.smartlocation.utils.Logger;
import java.util.List;

/* loaded from: classes5.dex */
public interface GeofencingProvider {
    void addGeofence(GeofenceModel geofenceModel);

    void addGeofences(List<GeofenceModel> list);

    void init(Context context, Logger logger);

    void removeGeofence(String str);

    void removeGeofences(List<String> list);

    void start(OnGeofencingTransitionListener onGeofencingTransitionListener);

    void stop();
}
