package com.airbnb.android.react.maps;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes.dex */
public class LatLngBoundsUtils {
    public static boolean BoundsAreDifferent(LatLngBounds latLngBounds, LatLngBounds latLngBounds2) {
        LatLng center = latLngBounds.getCenter();
        double d = center.latitude;
        double d2 = center.longitude;
        double d3 = latLngBounds.northeast.latitude - latLngBounds.southwest.latitude;
        double d4 = latLngBounds.northeast.longitude - latLngBounds.southwest.longitude;
        LatLng center2 = latLngBounds2.getCenter();
        double d5 = center2.latitude;
        double d6 = center2.longitude;
        double d7 = latLngBounds2.northeast.latitude - latLngBounds2.southwest.latitude;
        double d8 = latLngBounds2.northeast.longitude - latLngBounds2.southwest.longitude;
        double LatitudeEpsilon = LatitudeEpsilon(latLngBounds, latLngBounds2);
        double LongitudeEpsilon = LongitudeEpsilon(latLngBounds, latLngBounds2);
        return different(d, d5, LatitudeEpsilon) || different(d2, d6, LongitudeEpsilon) || different(d3, d7, LatitudeEpsilon) || different(d4, d8, LongitudeEpsilon);
    }

    private static boolean different(double d, double d2, double d3) {
        return Math.abs(d - d2) > d3;
    }

    private static double LatitudeEpsilon(LatLngBounds latLngBounds, LatLngBounds latLngBounds2) {
        return Math.min(Math.abs(latLngBounds.northeast.latitude - latLngBounds.southwest.latitude), Math.abs(latLngBounds2.northeast.latitude - latLngBounds2.southwest.latitude)) / 2560.0d;
    }

    private static double LongitudeEpsilon(LatLngBounds latLngBounds, LatLngBounds latLngBounds2) {
        return Math.min(Math.abs(latLngBounds.northeast.longitude - latLngBounds.southwest.longitude), Math.abs(latLngBounds2.northeast.longitude - latLngBounds2.southwest.longitude)) / 2560.0d;
    }
}
