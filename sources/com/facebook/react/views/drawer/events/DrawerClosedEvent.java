package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* loaded from: classes.dex */
public class DrawerClosedEvent extends Event<DrawerClosedEvent> {
    public static final String EVENT_NAME = "topDrawerClose";

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Deprecated
    public DrawerClosedEvent(int r2) {
        this(-1, r2);
    }

    public DrawerClosedEvent(int r1, int r2) {
        super(r1, r2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        return Arguments.createMap();
    }
}
