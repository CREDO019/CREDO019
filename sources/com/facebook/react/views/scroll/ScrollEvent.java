package com.facebook.react.views.scroll;

import androidx.core.util.Pools;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchesHelper;

/* loaded from: classes.dex */
public class ScrollEvent extends Event<ScrollEvent> {
    private static final Pools.SynchronizedPool<ScrollEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private static String TAG = "ScrollEvent";
    private int mContentHeight;
    private int mContentWidth;
    private ScrollEventType mScrollEventType;
    private int mScrollViewHeight;
    private int mScrollViewWidth;
    private float mScrollX;
    private float mScrollY;
    private float mXVelocity;
    private float mYVelocity;

    @Deprecated
    public static ScrollEvent obtain(int r11, ScrollEventType scrollEventType, float f, float f2, float f3, float f4, int r17, int r18, int r19, int r20) {
        return obtain(-1, r11, scrollEventType, f, f2, f3, f4, r17, r18, r19, r20);
    }

    public static ScrollEvent obtain(int r13, int r14, ScrollEventType scrollEventType, float f, float f2, float f3, float f4, int r20, int r21, int r22, int r23) {
        ScrollEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new ScrollEvent();
        }
        acquire.init(r13, r14, scrollEventType, f, f2, f3, f4, r20, r21, r22, r23);
        return acquire;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        try {
            EVENTS_POOL.release(this);
        } catch (IllegalStateException e) {
            ReactSoftExceptionLogger.logSoftException(TAG, e);
        }
    }

    private ScrollEvent() {
    }

    private void init(int r1, int r2, ScrollEventType scrollEventType, float f, float f2, float f3, float f4, int r8, int r9, int r10, int r11) {
        super.init(r1, r2);
        this.mScrollEventType = scrollEventType;
        this.mScrollX = f;
        this.mScrollY = f2;
        this.mXVelocity = f3;
        this.mYVelocity = f4;
        this.mContentWidth = r8;
        this.mContentHeight = r9;
        this.mScrollViewWidth = r10;
        this.mScrollViewHeight = r11;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return ScrollEventType.getJSEventName((ScrollEventType) Assertions.assertNotNull(this.mScrollEventType));
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return this.mScrollEventType == ScrollEventType.SCROLL;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(ViewProps.TOP, 0.0d);
        createMap.putDouble(ViewProps.BOTTOM, 0.0d);
        createMap.putDouble("left", 0.0d);
        createMap.putDouble("right", 0.0d);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("x", PixelUtil.toDIPFromPixel(this.mScrollX));
        createMap2.putDouble("y", PixelUtil.toDIPFromPixel(this.mScrollY));
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putDouble("width", PixelUtil.toDIPFromPixel(this.mContentWidth));
        createMap3.putDouble("height", PixelUtil.toDIPFromPixel(this.mContentHeight));
        WritableMap createMap4 = Arguments.createMap();
        createMap4.putDouble("width", PixelUtil.toDIPFromPixel(this.mScrollViewWidth));
        createMap4.putDouble("height", PixelUtil.toDIPFromPixel(this.mScrollViewHeight));
        WritableMap createMap5 = Arguments.createMap();
        createMap5.putDouble("x", this.mXVelocity);
        createMap5.putDouble("y", this.mYVelocity);
        WritableMap createMap6 = Arguments.createMap();
        createMap6.putMap("contentInset", createMap);
        createMap6.putMap("contentOffset", createMap2);
        createMap6.putMap("contentSize", createMap3);
        createMap6.putMap("layoutMeasurement", createMap4);
        createMap6.putMap("velocity", createMap5);
        createMap6.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        createMap6.putBoolean("responderIgnoreScroll", true);
        return createMap6;
    }
}
