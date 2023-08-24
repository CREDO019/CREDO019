package com.facebook.react.views.view;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* loaded from: classes.dex */
public class ViewGroupClickEvent extends Event<ViewGroupClickEvent> {
    private static final String EVENT_NAME = "topClick";

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Deprecated
    public ViewGroupClickEvent(int r2) {
        this(-1, r2);
    }

    public ViewGroupClickEvent(int r1, int r2) {
        super(r1, r2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        return Arguments.createMap();
    }
}
