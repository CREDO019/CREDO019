package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* loaded from: classes.dex */
public class DrawerOpenedEvent extends Event<DrawerOpenedEvent> {
    public static final String EVENT_NAME = "topDrawerOpen";

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Deprecated
    public DrawerOpenedEvent(int r2) {
        this(-1, r2);
    }

    public DrawerOpenedEvent(int r1, int r2) {
        super(r1, r2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        return Arguments.createMap();
    }
}
