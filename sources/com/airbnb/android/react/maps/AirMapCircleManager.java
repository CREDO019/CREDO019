package com.airbnb.android.react.maps;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.core.internal.view.SupportMenu;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes.dex */
public class AirMapCircleManager extends ViewGroupManager<AirMapCircle> {
    private final DisplayMetrics metrics;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapCircle";
    }

    public AirMapCircleManager(ReactApplicationContext reactApplicationContext) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.metrics = displayMetrics;
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public AirMapCircle createViewInstance(ThemedReactContext themedReactContext) {
        return new AirMapCircle(themedReactContext);
    }

    @ReactProp(name = TtmlNode.CENTER)
    public void setCenter(AirMapCircle airMapCircle, ReadableMap readableMap) {
        airMapCircle.setCenter(new LatLng(readableMap.getDouble("latitude"), readableMap.getDouble("longitude")));
    }

    @ReactProp(defaultDouble = 0.0d, name = "radius")
    public void setRadius(AirMapCircle airMapCircle, double d) {
        airMapCircle.setRadius(d);
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(AirMapCircle airMapCircle, float f) {
        airMapCircle.setStrokeWidth(this.metrics.density * f);
    }

    @ReactProp(customType = "Color", defaultInt = SupportMenu.CATEGORY_MASK, name = "fillColor")
    public void setFillColor(AirMapCircle airMapCircle, int r2) {
        airMapCircle.setFillColor(r2);
    }

    @ReactProp(customType = "Color", defaultInt = SupportMenu.CATEGORY_MASK, name = "strokeColor")
    public void setStrokeColor(AirMapCircle airMapCircle, int r2) {
        airMapCircle.setStrokeColor(r2);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.Z_INDEX)
    public void setZIndex(AirMapCircle airMapCircle, float f) {
        airMapCircle.setZIndex(f);
    }
}
