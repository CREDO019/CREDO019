package com.airbnb.android.react.maps;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AirMapGradientPolylineManager extends ViewGroupManager<AirMapGradientPolyline> {
    private final DisplayMetrics metrics;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapGradientPolyline";
    }

    public AirMapGradientPolylineManager(ReactApplicationContext reactApplicationContext) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.metrics = displayMetrics;
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public AirMapGradientPolyline createViewInstance(ThemedReactContext themedReactContext) {
        return new AirMapGradientPolyline(themedReactContext);
    }

    @ReactProp(name = "coordinates")
    public void setCoordinates(AirMapGradientPolyline airMapGradientPolyline, ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        for (int r1 = 0; r1 < readableArray.size(); r1++) {
            ReadableMap map = readableArray.getMap(r1);
            arrayList.add(new LatLng(map.getDouble("latitude"), map.getDouble("longitude")));
        }
        airMapGradientPolyline.setCoordinates(arrayList);
    }

    @ReactProp(customType = "ColorArray", name = "strokeColors")
    public void setStrokeColors(AirMapGradientPolyline airMapGradientPolyline, ReadableArray readableArray) {
        if (readableArray != null) {
            if (readableArray.size() == 0) {
                airMapGradientPolyline.setStrokeColors(new int[]{0, 0});
                return;
            }
            if (readableArray.size() == 1) {
                airMapGradientPolyline.setStrokeColors(new int[]{readableArray.getInt(0), readableArray.getInt(0)});
                return;
            }
            int[] r0 = new int[readableArray.size()];
            for (int r3 = 0; r3 < readableArray.size(); r3++) {
                r0[r3] = readableArray.getInt(r3);
            }
            airMapGradientPolyline.setStrokeColors(r0);
            return;
        }
        airMapGradientPolyline.setStrokeColors(new int[]{0, 0});
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.Z_INDEX)
    public void setZIndex(AirMapGradientPolyline airMapGradientPolyline, float f) {
        airMapGradientPolyline.setZIndex(f);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(AirMapGradientPolyline airMapGradientPolyline, float f) {
        airMapGradientPolyline.setWidth(this.metrics.density * f);
    }
}
