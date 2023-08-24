package com.airbnb.android.react.maps;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;

/* loaded from: classes.dex */
public class AirMapCalloutManager extends ViewGroupManager<AirMapCallout> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "AIRMapCallout";
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public AirMapCallout createViewInstance(ThemedReactContext themedReactContext) {
        return new AirMapCallout(themedReactContext);
    }

    @ReactProp(defaultBoolean = false, name = "tooltip")
    public void setTooltip(AirMapCallout airMapCallout, boolean z) {
        airMapCallout.setTooltip(z);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.m1261of("onPress", MapBuilder.m1261of("registrationName", "onPress"));
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new SizeReportingShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void updateExtraData(AirMapCallout airMapCallout, Object obj) {
        Map map = (Map) obj;
        float floatValue = ((Float) map.get("width")).floatValue();
        float floatValue2 = ((Float) map.get("height")).floatValue();
        airMapCallout.width = (int) floatValue;
        airMapCallout.height = (int) floatValue2;
    }
}
