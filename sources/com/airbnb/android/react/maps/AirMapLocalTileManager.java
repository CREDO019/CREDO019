package com.airbnb.android.react.maps;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes.dex */
public class AirMapLocalTileManager extends ViewGroupManager<AirMapLocalTile> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapLocalTile";
    }

    public AirMapLocalTileManager(ReactApplicationContext reactApplicationContext) {
        ((WindowManager) reactApplicationContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(new DisplayMetrics());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public AirMapLocalTile createViewInstance(ThemedReactContext themedReactContext) {
        return new AirMapLocalTile(themedReactContext);
    }

    @ReactProp(name = "pathTemplate")
    public void setPathTemplate(AirMapLocalTile airMapLocalTile, String str) {
        airMapLocalTile.setPathTemplate(str);
    }

    @ReactProp(defaultFloat = 256.0f, name = "tileSize")
    public void setTileSize(AirMapLocalTile airMapLocalTile, float f) {
        airMapLocalTile.setTileSize(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = -1.0f, name = ViewProps.Z_INDEX)
    public void setZIndex(AirMapLocalTile airMapLocalTile, float f) {
        airMapLocalTile.setZIndex(f);
    }

    @ReactProp(defaultBoolean = false, name = "useAssets")
    public void setUseAssets(AirMapLocalTile airMapLocalTile, boolean z) {
        airMapLocalTile.setUseAssets(z);
    }
}
