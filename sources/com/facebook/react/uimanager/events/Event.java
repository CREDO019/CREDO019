package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.SystemClock;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.Event;

/* loaded from: classes.dex */
public abstract class Event<T extends Event> {
    private static int sUniqueID;
    private boolean mInitialized;
    private int mSurfaceId;
    private long mTimestampMs;
    private int mUIManagerType;
    private int mUniqueID;
    private int mViewTag;

    public boolean canCoalesce() {
        return true;
    }

    public short getCoalescingKey() {
        return (short) 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getEventCategory() {
        return 2;
    }

    protected WritableMap getEventData() {
        return null;
    }

    public abstract String getEventName();

    public void onDispose() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Event() {
        int r0 = sUniqueID;
        sUniqueID = r0 + 1;
        this.mUniqueID = r0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public Event(int r3) {
        int r0 = sUniqueID;
        sUniqueID = r0 + 1;
        this.mUniqueID = r0;
        init(r3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Event(int r3, int r4) {
        int r0 = sUniqueID;
        sUniqueID = r0 + 1;
        this.mUniqueID = r0;
        init(r3, r4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public void init(int r2) {
        init(-1, r2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(int r3, int r4) {
        init(r3, r4, SystemClock.uptimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(int r3, int r4, long j) {
        this.mSurfaceId = r3;
        this.mViewTag = r4;
        int r32 = r3 == -1 ? 1 : 2;
        if (r32 == 1 && !ViewUtil.isRootTag(r4)) {
            r32 = ViewUtil.getUIManagerType(r4);
        }
        this.mUIManagerType = r32;
        this.mTimestampMs = j;
        this.mInitialized = true;
    }

    public final int getViewTag() {
        return this.mViewTag;
    }

    public final int getSurfaceId() {
        return this.mSurfaceId;
    }

    public final long getTimestampMs() {
        return this.mTimestampMs;
    }

    public T coalesce(T t) {
        return getTimestampMs() >= t.getTimestampMs() ? this : t;
    }

    public int getUniqueID() {
        return this.mUniqueID;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInitialized() {
        return this.mInitialized;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void dispose() {
        this.mInitialized = false;
        onDispose();
    }

    public final int getUIManagerType() {
        return this.mUIManagerType;
    }

    @Deprecated
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        WritableMap eventData = getEventData();
        if (eventData == null) {
            throw new IllegalViewOperationException("Event: you must return a valid, non-null value from `getEventData`, or override `dispatch` and `dispatchModern`. Event: " + getEventName());
        }
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), eventData);
    }

    @Deprecated
    public void dispatchModern(RCTModernEventEmitter rCTModernEventEmitter) {
        WritableMap eventData;
        if (getSurfaceId() != -1 && (eventData = getEventData()) != null) {
            rCTModernEventEmitter.receiveEvent(getSurfaceId(), getViewTag(), getEventName(), canCoalesce(), getCoalescingKey(), eventData, getEventCategory());
        } else {
            dispatch(rCTModernEventEmitter);
        }
    }
}
