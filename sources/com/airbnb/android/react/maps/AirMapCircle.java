package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes.dex */
public class AirMapCircle extends AirMapFeature {
    private LatLng center;
    private Circle circle;
    private CircleOptions circleOptions;
    private int fillColor;
    private double radius;
    private int strokeColor;
    private float strokeWidth;
    private float zIndex;

    public AirMapCircle(Context context) {
        super(context);
    }

    public void setCenter(LatLng latLng) {
        this.center = latLng;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setCenter(latLng);
        }
    }

    public void setRadius(double d) {
        this.radius = d;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setRadius(d);
        }
    }

    public void setFillColor(int r2) {
        this.fillColor = r2;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setFillColor(r2);
        }
    }

    public void setStrokeColor(int r2) {
        this.strokeColor = r2;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setStrokeColor(r2);
        }
    }

    public void setStrokeWidth(float f) {
        this.strokeWidth = f;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setStrokeWidth(f);
        }
    }

    public void setZIndex(float f) {
        this.zIndex = f;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setZIndex(f);
        }
    }

    public CircleOptions getCircleOptions() {
        if (this.circleOptions == null) {
            this.circleOptions = createCircleOptions();
        }
        return this.circleOptions;
    }

    private CircleOptions createCircleOptions() {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(this.center);
        circleOptions.radius(this.radius);
        circleOptions.fillColor(this.fillColor);
        circleOptions.strokeColor(this.strokeColor);
        circleOptions.strokeWidth(this.strokeWidth);
        circleOptions.zIndex(this.zIndex);
        return circleOptions;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public Object getFeature() {
        return this.circle;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void addToMap(GoogleMap googleMap) {
        this.circle = googleMap.addCircle(getCircleOptions());
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void removeFromMap(GoogleMap googleMap) {
        this.circle.remove();
    }
}
