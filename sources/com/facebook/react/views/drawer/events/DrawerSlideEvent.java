package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* loaded from: classes.dex */
public class DrawerSlideEvent extends Event<DrawerSlideEvent> {
    public static final String EVENT_NAME = "topDrawerSlide";
    private final float mOffset;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Deprecated
    public DrawerSlideEvent(int r2, float f) {
        this(-1, r2, f);
    }

    public DrawerSlideEvent(int r1, int r2, float f) {
        super(r1, r2);
        this.mOffset = f;
    }

    public float getOffset() {
        return this.mOffset;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("offset", getOffset());
        return createMap;
    }
}
