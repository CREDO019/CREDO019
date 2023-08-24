package com.airbnb.android.react.maps;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.core.internal.view.SupportMenu;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.RoundCap;
import com.google.android.gms.maps.model.SquareCap;
import java.util.Map;

/* loaded from: classes.dex */
public class AirMapPolylineManager extends ViewGroupManager<AirMapPolyline> {
    private final DisplayMetrics metrics;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapPolyline";
    }

    public AirMapPolylineManager(ReactApplicationContext reactApplicationContext) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.metrics = displayMetrics;
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public AirMapPolyline createViewInstance(ThemedReactContext themedReactContext) {
        return new AirMapPolyline(themedReactContext);
    }

    @ReactProp(name = "coordinates")
    public void setCoordinate(AirMapPolyline airMapPolyline, ReadableArray readableArray) {
        airMapPolyline.setCoordinates(readableArray);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(AirMapPolyline airMapPolyline, float f) {
        airMapPolyline.setWidth(this.metrics.density * f);
    }

    @ReactProp(customType = "Color", defaultInt = SupportMenu.CATEGORY_MASK, name = "strokeColor")
    public void setStrokeColor(AirMapPolyline airMapPolyline, int r2) {
        airMapPolyline.setColor(r2);
    }

    @ReactProp(defaultBoolean = false, name = "tappable")
    public void setTappable(AirMapPolyline airMapPolyline, boolean z) {
        airMapPolyline.setTappable(z);
    }

    @ReactProp(defaultBoolean = false, name = "geodesic")
    public void setGeodesic(AirMapPolyline airMapPolyline, boolean z) {
        airMapPolyline.setGeodesic(z);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.Z_INDEX)
    public void setZIndex(AirMapPolyline airMapPolyline, float f) {
        airMapPolyline.setZIndex(f);
    }

    @ReactProp(name = "lineCap")
    public void setlineCap(AirMapPolyline airMapPolyline, String str) {
        Cap squareCap;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -894674659:
                if (str.equals("square")) {
                    c = 0;
                    break;
                }
                break;
            case 3035667:
                if (str.equals("butt")) {
                    c = 1;
                    break;
                }
                break;
            case 108704142:
                if (str.equals("round")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                squareCap = new SquareCap();
                break;
            case 1:
                squareCap = new ButtCap();
                break;
            case 2:
                squareCap = new RoundCap();
                break;
            default:
                squareCap = new RoundCap();
                break;
        }
        airMapPolyline.setLineCap(squareCap);
    }

    @ReactProp(name = "lineDashPattern")
    public void setLineDashPattern(AirMapPolyline airMapPolyline, ReadableArray readableArray) {
        airMapPolyline.setLineDashPattern(readableArray);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.m1261of("onPress", MapBuilder.m1261of("registrationName", "onPress"));
    }
}
