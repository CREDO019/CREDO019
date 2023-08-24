package com.google.maps.android.data;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.Observable;

/* loaded from: classes3.dex */
public abstract class Style extends Observable {
    protected MarkerOptions mMarkerOptions = new MarkerOptions();
    protected PolylineOptions mPolylineOptions = new PolylineOptions();
    protected PolygonOptions mPolygonOptions = new PolygonOptions();

    public float getRotation() {
        return this.mMarkerOptions.getRotation();
    }

    public void setMarkerRotation(float f) {
        this.mMarkerOptions.rotation(f);
    }

    public void setMarkerHotSpot(float f, float f2, String str, String str2) {
        if (!str.equals("fraction")) {
            f = 0.5f;
        }
        if (!str2.equals("fraction")) {
            f2 = 1.0f;
        }
        this.mMarkerOptions.anchor(f, f2);
    }

    public void setLineStringWidth(float f) {
        this.mPolylineOptions.width(f);
    }

    public void setPolygonStrokeWidth(float f) {
        this.mPolygonOptions.strokeWidth(f);
    }

    public void setPolygonFillColor(int r2) {
        this.mPolygonOptions.fillColor(r2);
    }
}
