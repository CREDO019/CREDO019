package com.airbnb.android.react.maps;

import com.facebook.react.bridge.ReactApplicationContext;
import com.google.android.gms.maps.GoogleMapOptions;

/* loaded from: classes.dex */
public class AirMapLiteManager extends AirMapManager {
    private static final String REACT_CLASS = "AIRMapLite";

    @Override // com.airbnb.android.react.maps.AirMapManager, com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public AirMapLiteManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.googleMapOptions = new GoogleMapOptions().liteMode(true);
    }
}
