package com.facebook.react.views.switchview;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchesHelper;

/* loaded from: classes.dex */
class ReactSwitchEvent extends Event<ReactSwitchEvent> {
    public static final String EVENT_NAME = "topChange";
    private final boolean mIsChecked;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topChange";
    }

    @Deprecated
    public ReactSwitchEvent(int r2, boolean z) {
        this(-1, r2, z);
    }

    public ReactSwitchEvent(int r1, int r2, boolean z) {
        super(r1, r2);
        this.mIsChecked = z;
    }

    public boolean getIsChecked() {
        return this.mIsChecked;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        createMap.putBoolean("value", getIsChecked());
        return createMap;
    }
}
