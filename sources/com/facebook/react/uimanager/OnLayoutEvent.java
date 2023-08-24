package com.facebook.react.uimanager;

import androidx.core.util.Pools;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.android.exoplayer2.text.ttml.TtmlNode;

/* loaded from: classes.dex */
public class OnLayoutEvent extends Event<OnLayoutEvent> {
    private static final Pools.SynchronizedPool<OnLayoutEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(20);
    private int mHeight;
    private int mWidth;

    /* renamed from: mX */
    private int f164mX;

    /* renamed from: mY */
    private int f165mY;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topLayout";
    }

    @Deprecated
    public static OnLayoutEvent obtain(int r6, int r7, int r8, int r9, int r10) {
        return obtain(-1, r6, r7, r8, r9, r10);
    }

    public static OnLayoutEvent obtain(int r8, int r9, int r10, int r11, int r12, int r13) {
        OnLayoutEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new OnLayoutEvent();
        }
        acquire.init(r8, r9, r10, r11, r12, r13);
        return acquire;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        EVENTS_POOL.release(this);
    }

    private OnLayoutEvent() {
    }

    @Deprecated
    protected void init(int r8, int r9, int r10, int r11, int r12) {
        init(-1, r8, r9, r10, r11, r12);
    }

    protected void init(int r1, int r2, int r3, int r4, int r5, int r6) {
        super.init(r1, r2);
        this.f164mX = r3;
        this.f165mY = r4;
        this.mWidth = r5;
        this.mHeight = r6;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("x", PixelUtil.toDIPFromPixel(this.f164mX));
        createMap.putDouble("y", PixelUtil.toDIPFromPixel(this.f165mY));
        createMap.putDouble("width", PixelUtil.toDIPFromPixel(this.mWidth));
        createMap.putDouble("height", PixelUtil.toDIPFromPixel(this.mHeight));
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap(TtmlNode.TAG_LAYOUT, createMap);
        createMap2.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        return createMap2;
    }
}
