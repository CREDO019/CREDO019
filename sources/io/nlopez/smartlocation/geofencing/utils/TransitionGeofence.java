package io.nlopez.smartlocation.geofencing.utils;

import io.nlopez.smartlocation.geofencing.model.GeofenceModel;

/* loaded from: classes5.dex */
public class TransitionGeofence {
    private GeofenceModel geofenceModel;
    private int transitionType;

    public TransitionGeofence(GeofenceModel geofenceModel, int r2) {
        this.geofenceModel = geofenceModel;
        this.transitionType = r2;
    }

    public GeofenceModel getGeofenceModel() {
        return this.geofenceModel;
    }

    public int getTransitionType() {
        return this.transitionType;
    }
}
