package com.airbnb.android.react.maps;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;

/* loaded from: classes.dex */
public class AirMapOverlayManager extends ViewGroupManager<AirMapOverlay> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapOverlay";
    }

    public AirMapOverlayManager(ReactApplicationContext reactApplicationContext) {
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(new DisplayMetrics());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public AirMapOverlay createViewInstance(ThemedReactContext themedReactContext) {
        return new AirMapOverlay(themedReactContext);
    }

    @ReactProp(name = "bounds")
    public void setBounds(AirMapOverlay airMapOverlay, ReadableArray readableArray) {
        airMapOverlay.setBounds(readableArray);
    }

    @ReactProp(name = "bearing")
    public void setBearing(AirMapOverlay airMapOverlay, float f) {
        airMapOverlay.setBearing(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.Z_INDEX)
    public void setZIndex(AirMapOverlay airMapOverlay, float f) {
        airMapOverlay.setZIndex(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(AirMapOverlay airMapOverlay, float f) {
        airMapOverlay.setTransparency(1.0f - f);
    }

    @ReactProp(name = "image")
    public void setImage(AirMapOverlay airMapOverlay, String str) {
        airMapOverlay.setImage(str);
    }

    @ReactProp(defaultBoolean = false, name = "tappable")
    public void setTappable(AirMapOverlay airMapOverlay, boolean z) {
        airMapOverlay.setTappable(z);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.m1261of("onPress", MapBuilder.m1261of("registrationName", "onPress"));
    }
}
