package com.airbnb.android.react.maps;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.WeightedLatLng;

/* loaded from: classes.dex */
public class AirMapHeatmapManager extends ViewGroupManager<AirMapHeatmap> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapHeatmap";
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public AirMapHeatmap createViewInstance(ThemedReactContext themedReactContext) {
        return new AirMapHeatmap(themedReactContext);
    }

    @ReactProp(name = "points")
    public void setPoints(AirMapHeatmap airMapHeatmap, ReadableArray readableArray) {
        WeightedLatLng weightedLatLng;
        WeightedLatLng[] weightedLatLngArr = new WeightedLatLng[readableArray.size()];
        for (int r1 = 0; r1 < readableArray.size(); r1++) {
            ReadableMap map = readableArray.getMap(r1);
            LatLng latLng = new LatLng(map.getDouble("latitude"), map.getDouble("longitude"));
            if (map.hasKey("weight")) {
                weightedLatLng = new WeightedLatLng(latLng, map.getDouble("weight"));
            } else {
                weightedLatLng = new WeightedLatLng(latLng);
            }
            weightedLatLngArr[r1] = weightedLatLng;
        }
        airMapHeatmap.setPoints(weightedLatLngArr);
    }

    @ReactProp(name = "gradient")
    public void setGradient(AirMapHeatmap airMapHeatmap, ReadableMap readableMap) {
        ReadableArray array = readableMap.getArray("colors");
        int[] r1 = new int[array.size()];
        for (int r3 = 0; r3 < array.size(); r3++) {
            r1[r3] = array.getInt(r3);
        }
        ReadableArray array2 = readableMap.getArray("startPoints");
        float[] fArr = new float[array2.size()];
        for (int r2 = 0; r2 < array2.size(); r2++) {
            fArr[r2] = (float) array2.getDouble(r2);
        }
        if (readableMap.hasKey("colorMapSize")) {
            airMapHeatmap.setGradient(new Gradient(r1, fArr, readableMap.getInt("colorMapSize")));
        } else {
            airMapHeatmap.setGradient(new Gradient(r1, fArr));
        }
    }

    @ReactProp(name = ViewProps.OPACITY)
    public void setOpacity(AirMapHeatmap airMapHeatmap, double d) {
        airMapHeatmap.setOpacity(d);
    }

    @ReactProp(name = "radius")
    public void setRadius(AirMapHeatmap airMapHeatmap, int r2) {
        airMapHeatmap.setRadius(r2);
    }
}
