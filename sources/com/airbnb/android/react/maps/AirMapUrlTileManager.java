package com.airbnb.android.react.maps;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes.dex */
public class AirMapUrlTileManager extends ViewGroupManager<AirMapUrlTile> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapUrlTile";
    }

    public AirMapUrlTileManager(ReactApplicationContext reactApplicationContext) {
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(new DisplayMetrics());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public AirMapUrlTile createViewInstance(ThemedReactContext themedReactContext) {
        return new AirMapUrlTile(themedReactContext);
    }

    @ReactProp(name = "urlTemplate")
    public void setUrlTemplate(AirMapUrlTile airMapUrlTile, String str) {
        airMapUrlTile.setUrlTemplate(str);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = -1.0f, name = ViewProps.Z_INDEX)
    public void setZIndex(AirMapUrlTile airMapUrlTile, float f) {
        airMapUrlTile.setZIndex(f);
    }

    @ReactProp(defaultFloat = 0.0f, name = "minimumZ")
    public void setMinimumZ(AirMapUrlTile airMapUrlTile, float f) {
        airMapUrlTile.setMinimumZ(f);
    }

    @ReactProp(defaultFloat = 100.0f, name = "maximumZ")
    public void setMaximumZ(AirMapUrlTile airMapUrlTile, float f) {
        airMapUrlTile.setMaximumZ(f);
    }

    @ReactProp(defaultFloat = 100.0f, name = "maximumNativeZ")
    public void setMaximumNativeZ(AirMapUrlTile airMapUrlTile, float f) {
        airMapUrlTile.setMaximumNativeZ(f);
    }

    @ReactProp(defaultBoolean = false, name = "flipY")
    public void setFlipY(AirMapUrlTile airMapUrlTile, boolean z) {
        airMapUrlTile.setFlipY(z);
    }

    @ReactProp(defaultFloat = 256.0f, name = "tileSize")
    public void setTileSize(AirMapUrlTile airMapUrlTile, float f) {
        airMapUrlTile.setTileSize(f);
    }

    @ReactProp(defaultBoolean = false, name = "doubleTileSize")
    public void setDoubleTileSize(AirMapUrlTile airMapUrlTile, boolean z) {
        airMapUrlTile.setDoubleTileSize(z);
    }

    @ReactProp(name = "tileCachePath")
    public void setTileCachePath(AirMapUrlTile airMapUrlTile, String str) {
        airMapUrlTile.setTileCachePath(str);
    }

    @ReactProp(defaultFloat = 0.0f, name = "tileCacheMaxAge")
    public void setTileCacheMaxAge(AirMapUrlTile airMapUrlTile, float f) {
        airMapUrlTile.setTileCacheMaxAge(f);
    }

    @ReactProp(defaultBoolean = false, name = "offlineMode")
    public void setOfflineMode(AirMapUrlTile airMapUrlTile, boolean z) {
        airMapUrlTile.setOfflineMode(z);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(AirMapUrlTile airMapUrlTile, float f) {
        airMapUrlTile.setOpacity(f);
    }
}
