package com.facebook.react.uimanager;

import android.widget.ImageView;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.TouchEventType;
import com.reactnativecommunity.picker.PickerItemSelectEvent;
import com.reactnativecommunity.webview.events.TopLoadingErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingFinishEvent;
import com.reactnativecommunity.webview.events.TopLoadingStartEvent;
import com.reactnativecommunity.webview.events.TopMessageEvent;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
class UIManagerModuleConstants {
    public static final String ACTION_DISMISSED = "dismissed";
    public static final String ACTION_ITEM_SELECTED = "itemSelected";

    UIManagerModuleConstants() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map getBubblingEventTypeConstants() {
        return MapBuilder.builder().put("topChange", MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onChange", "captured", "onChangeCapture"))).put(PickerItemSelectEvent.EVENT_NAME, MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onSelect", "captured", "onSelectCapture"))).put(TouchEventType.getJSEventName(TouchEventType.START), MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onTouchStart", "captured", "onTouchStartCapture"))).put(TouchEventType.getJSEventName(TouchEventType.MOVE), MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onTouchMove", "captured", "onTouchMoveCapture"))).put(TouchEventType.getJSEventName(TouchEventType.END), MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onTouchEnd", "captured", "onTouchEndCapture"))).put(TouchEventType.getJSEventName(TouchEventType.CANCEL), MapBuilder.m1261of("phasedRegistrationNames", MapBuilder.m1260of("bubbled", "onTouchCancel", "captured", "onTouchCancelCapture"))).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map getDirectEventTypeConstants() {
        return MapBuilder.builder().put("topContentSizeChange", MapBuilder.m1261of("registrationName", "onContentSizeChange")).put("topLayout", MapBuilder.m1261of("registrationName", ViewProps.ON_LAYOUT)).put(TopLoadingErrorEvent.EVENT_NAME, MapBuilder.m1261of("registrationName", "onLoadingError")).put(TopLoadingFinishEvent.EVENT_NAME, MapBuilder.m1261of("registrationName", "onLoadingFinish")).put(TopLoadingStartEvent.EVENT_NAME, MapBuilder.m1261of("registrationName", "onLoadingStart")).put("topSelectionChange", MapBuilder.m1261of("registrationName", "onSelectionChange")).put(TopMessageEvent.EVENT_NAME, MapBuilder.m1261of("registrationName", "onMessage")).put("topClick", MapBuilder.m1261of("registrationName", "onClick")).put("topScrollBeginDrag", MapBuilder.m1261of("registrationName", "onScrollBeginDrag")).put("topScrollEndDrag", MapBuilder.m1261of("registrationName", "onScrollEndDrag")).put("topScroll", MapBuilder.m1261of("registrationName", "onScroll")).put("topMomentumScrollBegin", MapBuilder.m1261of("registrationName", "onMomentumScrollBegin")).put("topMomentumScrollEnd", MapBuilder.m1261of("registrationName", "onMomentumScrollEnd")).build();
    }

    public static Map<String, Object> getConstants() {
        HashMap newHashMap = MapBuilder.newHashMap();
        newHashMap.put("UIView", MapBuilder.m1261of("ContentMode", MapBuilder.m1259of("ScaleAspectFit", Integer.valueOf(ImageView.ScaleType.FIT_CENTER.ordinal()), "ScaleAspectFill", Integer.valueOf(ImageView.ScaleType.CENTER_CROP.ordinal()), "ScaleAspectCenter", Integer.valueOf(ImageView.ScaleType.CENTER_INSIDE.ordinal()))));
        newHashMap.put("StyleConstants", MapBuilder.m1261of("PointerEventsValues", MapBuilder.m1258of("none", Integer.valueOf(PointerEvents.NONE.ordinal()), "boxNone", Integer.valueOf(PointerEvents.BOX_NONE.ordinal()), "boxOnly", Integer.valueOf(PointerEvents.BOX_ONLY.ordinal()), "unspecified", Integer.valueOf(PointerEvents.AUTO.ordinal()))));
        newHashMap.put("PopupMenu", MapBuilder.m1260of("dismissed", "dismissed", ACTION_ITEM_SELECTED, ACTION_ITEM_SELECTED));
        newHashMap.put("AccessibilityEventTypes", MapBuilder.m1259of("typeWindowStateChanged", 32, "typeViewFocused", 8, "typeViewClicked", 1));
        return newHashMap;
    }
}
