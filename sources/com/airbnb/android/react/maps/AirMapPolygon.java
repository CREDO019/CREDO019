package com.airbnb.android.react.maps;

import android.content.Context;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AirMapPolygon extends AirMapFeature {
    private List<LatLng> coordinates;
    private int fillColor;
    private boolean geodesic;
    private List<List<LatLng>> holes;
    private Polygon polygon;
    private PolygonOptions polygonOptions;
    private int strokeColor;
    private float strokeWidth;
    private boolean tappable;
    private float zIndex;

    public AirMapPolygon(Context context) {
        super(context);
    }

    public void setCoordinates(ReadableArray readableArray) {
        this.coordinates = new ArrayList(readableArray.size());
        for (int r0 = 0; r0 < readableArray.size(); r0++) {
            ReadableMap map = readableArray.getMap(r0);
            this.coordinates.add(r0, new LatLng(map.getDouble("latitude"), map.getDouble("longitude")));
        }
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setPoints(this.coordinates);
        }
    }

    public void setHoles(ReadableArray readableArray) {
        if (readableArray == null) {
            return;
        }
        this.holes = new ArrayList(readableArray.size());
        for (int r1 = 0; r1 < readableArray.size(); r1++) {
            ReadableArray array = readableArray.getArray(r1);
            if (array.size() >= 3) {
                ArrayList arrayList = new ArrayList();
                for (int r5 = 0; r5 < array.size(); r5++) {
                    ReadableMap map = array.getMap(r5);
                    arrayList.add(new LatLng(map.getDouble("latitude"), map.getDouble("longitude")));
                }
                if (arrayList.size() == 3) {
                    arrayList.add((LatLng) arrayList.get(0));
                }
                this.holes.add(arrayList);
            }
        }
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setHoles(this.holes);
        }
    }

    public void setFillColor(int r2) {
        this.fillColor = r2;
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setFillColor(r2);
        }
    }

    public void setStrokeColor(int r2) {
        this.strokeColor = r2;
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setStrokeColor(r2);
        }
    }

    public void setStrokeWidth(float f) {
        this.strokeWidth = f;
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setStrokeWidth(f);
        }
    }

    public void setTappable(boolean z) {
        this.tappable = z;
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setClickable(z);
        }
    }

    public void setGeodesic(boolean z) {
        this.geodesic = z;
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setGeodesic(z);
        }
    }

    public void setZIndex(float f) {
        this.zIndex = f;
        Polygon polygon = this.polygon;
        if (polygon != null) {
            polygon.setZIndex(f);
        }
    }

    public PolygonOptions getPolygonOptions() {
        if (this.polygonOptions == null) {
            this.polygonOptions = createPolygonOptions();
        }
        return this.polygonOptions;
    }

    private PolygonOptions createPolygonOptions() {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.addAll(this.coordinates);
        polygonOptions.fillColor(this.fillColor);
        polygonOptions.strokeColor(this.strokeColor);
        polygonOptions.strokeWidth(this.strokeWidth);
        polygonOptions.geodesic(this.geodesic);
        polygonOptions.zIndex(this.zIndex);
        if (this.holes != null) {
            for (int r1 = 0; r1 < this.holes.size(); r1++) {
                polygonOptions.addHole(this.holes.get(r1));
            }
        }
        return polygonOptions;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public Object getFeature() {
        return this.polygon;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void addToMap(GoogleMap googleMap) {
        Polygon addPolygon = googleMap.addPolygon(getPolygonOptions());
        this.polygon = addPolygon;
        addPolygon.setClickable(this.tappable);
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void removeFromMap(GoogleMap googleMap) {
        this.polygon.remove();
    }
}
