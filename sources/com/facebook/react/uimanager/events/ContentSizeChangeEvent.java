package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;

/* loaded from: classes.dex */
public class ContentSizeChangeEvent extends Event<ContentSizeChangeEvent> {
    public static final String EVENT_NAME = "topContentSizeChange";
    private final int mHeight;
    private final int mWidth;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topContentSizeChange";
    }

    @Deprecated
    public ContentSizeChangeEvent(int r2, int r3, int r4) {
        this(-1, r2, r3, r4);
    }

    public ContentSizeChangeEvent(int r1, int r2, int r3, int r4) {
        super(r1, r2);
        this.mWidth = r3;
        this.mHeight = r4;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("width", PixelUtil.toDIPFromPixel(this.mWidth));
        createMap.putDouble("height", PixelUtil.toDIPFromPixel(this.mHeight));
        return createMap;
    }
}
