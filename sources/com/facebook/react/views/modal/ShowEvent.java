package com.facebook.react.views.modal;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* loaded from: classes.dex */
class ShowEvent extends Event<ShowEvent> {
    public static final String EVENT_NAME = "topShow";

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Deprecated
    protected ShowEvent(int r2) {
        this(-1, r2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ShowEvent(int r1, int r2) {
        super(r1, r2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        return Arguments.createMap();
    }
}
