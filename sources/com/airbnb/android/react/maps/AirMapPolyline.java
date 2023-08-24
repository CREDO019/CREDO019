package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AirMapPolyline extends AirMapFeature {
    private int color;
    private List<LatLng> coordinates;
    private boolean geodesic;
    private Cap lineCap;
    private List<PatternItem> pattern;
    private ReadableArray patternValues;
    private Polyline polyline;
    private PolylineOptions polylineOptions;
    private boolean tappable;
    private float width;
    private float zIndex;

    public AirMapPolyline(Context context) {
        super(context);
        this.lineCap = new RoundCap();
    }

    public void setCoordinates(ReadableArray readableArray) {
        this.coordinates = new ArrayList(readableArray.size());
        for (int r0 = 0; r0 < readableArray.size(); r0++) {
            ReadableMap map = readableArray.getMap(r0);
            this.coordinates.add(r0, new LatLng(map.getDouble("latitude"), map.getDouble("longitude")));
        }
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setPoints(this.coordinates);
        }
    }

    public void setColor(int r2) {
        this.color = r2;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setColor(r2);
        }
    }

    public void setWidth(float f) {
        this.width = f;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setWidth(f);
        }
    }

    public void setZIndex(float f) {
        this.zIndex = f;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setZIndex(f);
        }
    }

    public void setTappable(boolean z) {
        this.tappable = z;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setClickable(z);
        }
    }

    public void setGeodesic(boolean z) {
        this.geodesic = z;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setGeodesic(z);
        }
    }

    public void setLineCap(Cap cap) {
        this.lineCap = cap;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setStartCap(cap);
            this.polyline.setEndCap(cap);
        }
        applyPattern();
    }

    public void setLineDashPattern(ReadableArray readableArray) {
        this.patternValues = readableArray;
        applyPattern();
    }

    private void applyPattern() {
        PatternItem dash;
        if (this.patternValues == null) {
            return;
        }
        this.pattern = new ArrayList(this.patternValues.size());
        for (int r1 = 0; r1 < this.patternValues.size(); r1++) {
            float f = (float) this.patternValues.getDouble(r1);
            if (r1 % 2 != 0) {
                this.pattern.add(new Gap(f));
            } else {
                if (this.lineCap instanceof RoundCap) {
                    dash = new Dot();
                } else {
                    dash = new Dash(f);
                }
                this.pattern.add(dash);
            }
        }
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setPattern(this.pattern);
        }
    }

    public PolylineOptions getPolylineOptions() {
        if (this.polylineOptions == null) {
            this.polylineOptions = createPolylineOptions();
        }
        return this.polylineOptions;
    }

    private PolylineOptions createPolylineOptions() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(this.coordinates);
        polylineOptions.color(this.color);
        polylineOptions.width(this.width);
        polylineOptions.geodesic(this.geodesic);
        polylineOptions.zIndex(this.zIndex);
        polylineOptions.startCap(this.lineCap);
        polylineOptions.endCap(this.lineCap);
        polylineOptions.pattern(this.pattern);
        return polylineOptions;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public Object getFeature() {
        return this.polyline;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void addToMap(GoogleMap googleMap) {
        Polyline addPolyline = googleMap.addPolyline(getPolylineOptions());
        this.polyline = addPolyline;
        addPolyline.setClickable(this.tappable);
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void removeFromMap(GoogleMap googleMap) {
        this.polyline.remove();
    }
}
