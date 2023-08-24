package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* loaded from: classes.dex */
public class DrawerStateChangedEvent extends Event<DrawerStateChangedEvent> {
    public static final String EVENT_NAME = "topDrawerStateChanged";
    private final int mDrawerState;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Deprecated
    public DrawerStateChangedEvent(int r2, int r3) {
        this(-1, r2, r3);
    }

    public DrawerStateChangedEvent(int r1, int r2, int r3) {
        super(r1, r2);
        this.mDrawerState = r3;
    }

    public int getDrawerState() {
        return this.mDrawerState;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("drawerState", getDrawerState());
        return createMap;
    }
}
