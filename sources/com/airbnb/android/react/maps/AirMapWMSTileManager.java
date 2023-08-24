package com.airbnb.android.react.maps;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes.dex */
public class AirMapWMSTileManager extends ViewGroupManager<AirMapWMSTile> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapWMSTile";
    }

    public AirMapWMSTileManager(ReactApplicationContext reactApplicationContext) {
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(new DisplayMetrics());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public AirMapWMSTile createViewInstance(ThemedReactContext themedReactContext) {
        return new AirMapWMSTile(themedReactContext);
    }

    @ReactProp(name = "urlTemplate")
    public void setUrlTemplate(AirMapWMSTile airMapWMSTile, String str) {
        airMapWMSTile.setUrlTemplate(str);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = -1.0f, name = ViewProps.Z_INDEX)
    public void setZIndex(AirMapWMSTile airMapWMSTile, float f) {
        airMapWMSTile.setZIndex(f);
    }

    @ReactProp(defaultFloat = 0.0f, name = "minimumZ")
    public void setMinimumZ(AirMapWMSTile airMapWMSTile, float f) {
        airMapWMSTile.setMinimumZ(f);
    }

    @ReactProp(defaultFloat = 100.0f, name = "maximumZ")
    public void setMaximumZ(AirMapWMSTile airMapWMSTile, float f) {
        airMapWMSTile.setMaximumZ(f);
    }

    @ReactProp(defaultFloat = 100.0f, name = "maximumNativeZ")
    public void setMaximumNativeZ(AirMapWMSTile airMapWMSTile, float f) {
        airMapWMSTile.setMaximumNativeZ(f);
    }

    @ReactProp(defaultFloat = 256.0f, name = "tileSize")
    public void setTileSize(AirMapWMSTile airMapWMSTile, float f) {
        airMapWMSTile.setTileSize(f);
    }

    @ReactProp(name = "tileCachePath")
    public void setTileCachePath(AirMapWMSTile airMapWMSTile, String str) {
        airMapWMSTile.setTileCachePath(str);
    }

    @ReactProp(defaultFloat = 0.0f, name = "tileCacheMaxAge")
    public void setTileCacheMaxAge(AirMapWMSTile airMapWMSTile, float f) {
        airMapWMSTile.setTileCacheMaxAge(f);
    }

    @ReactProp(defaultBoolean = false, name = "offlineMode")
    public void setOfflineMode(AirMapWMSTile airMapWMSTile, boolean z) {
        airMapWMSTile.setOfflineMode(z);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(AirMapWMSTile airMapWMSTile, float f) {
        airMapWMSTile.setOpacity(f);
    }
}
