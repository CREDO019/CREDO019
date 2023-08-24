package com.facebook.react.uimanager.events;

import android.view.MotionEvent;
import androidx.core.util.Pools;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class PointerEvent extends Event<PointerEvent> {
    private static final Pools.SynchronizedPool<PointerEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(6);
    private static final int POINTER_EVENTS_POOL_SIZE = 6;
    private static final String TAG = "PointerEvent";
    private static final int UNSET_COALESCING_KEY = -1;
    private int mCoalescingKey = -1;
    private String mEventName;
    private MotionEvent mMotionEvent;
    private float mOffsetX;
    private float mOffsetY;
    private List<WritableMap> mPointersEventData;

    public static PointerEvent obtain(String str, int r9, int r10, MotionEvent motionEvent, float[] fArr) {
        PointerEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new PointerEvent();
        }
        acquire.init(str, r9, r10, (MotionEvent) Assertions.assertNotNull(motionEvent), fArr, 0);
        return acquire;
    }

    public static PointerEvent obtain(String str, int r9, int r10, MotionEvent motionEvent, float[] fArr, int r13) {
        PointerEvent acquire = EVENTS_POOL.acquire();
        if (acquire == null) {
            acquire = new PointerEvent();
        }
        acquire.init(str, r9, r10, (MotionEvent) Assertions.assertNotNull(motionEvent), fArr, r13);
        return acquire;
    }

    private void init(String str, int r4, int r5, MotionEvent motionEvent, float[] fArr, int r8) {
        super.init(r4, r5, motionEvent.getEventTime());
        this.mEventName = str;
        this.mMotionEvent = MotionEvent.obtain(motionEvent);
        this.mCoalescingKey = r8;
        this.mOffsetX = fArr[0];
        this.mOffsetY = fArr[1];
    }

    private PointerEvent() {
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return this.mEventName;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        if (this.mMotionEvent == null) {
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Cannot dispatch a Pointer that has no MotionEvent; the PointerEvehas been recycled"));
            return;
        }
        if (this.mPointersEventData == null) {
            this.mPointersEventData = createPointersEventData();
        }
        List<WritableMap> list = this.mPointersEventData;
        if (list == null) {
            return;
        }
        boolean z = list.size() > 1;
        for (WritableMap writableMap : this.mPointersEventData) {
            if (z) {
                writableMap = writableMap.copy();
            }
            rCTEventEmitter.receiveEvent(getViewTag(), this.mEventName, writableMap);
        }
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void onDispose() {
        this.mPointersEventData = null;
        MotionEvent motionEvent = this.mMotionEvent;
        this.mMotionEvent = null;
        if (motionEvent != null) {
            motionEvent.recycle();
        }
        try {
            EVENTS_POOL.release(this);
        } catch (IllegalStateException e) {
            ReactSoftExceptionLogger.logSoftException(TAG, e);
        }
    }

    private ArrayList<WritableMap> createPointerEvents() {
        MotionEvent motionEvent = this.mMotionEvent;
        ArrayList<WritableMap> arrayList = new ArrayList<>();
        for (int r2 = 0; r2 < motionEvent.getPointerCount(); r2++) {
            arrayList.add(createPointerEventData(r2));
        }
        return arrayList;
    }

    private WritableMap createPointerEventData(int r5) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("pointerId", this.mMotionEvent.getPointerId(r5));
        createMap.putDouble("pressure", this.mMotionEvent.getPressure(r5));
        createMap.putString("pointerType", PointerEventHelper.getW3CPointerType(this.mMotionEvent.getToolType(r5)));
        createMap.putDouble("clientX", this.mMotionEvent.getX(r5));
        createMap.putDouble("clientY", this.mMotionEvent.getY(r5));
        createMap.putDouble("offsetX", PixelUtil.toDIPFromPixel(this.mOffsetX));
        createMap.putDouble("offsetY", PixelUtil.toDIPFromPixel(this.mOffsetY));
        createMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        createMap.putDouble("timestamp", getTimestampMs());
        return createMap;
    }

    private List<WritableMap> createPointersEventData() {
        int actionIndex = this.mMotionEvent.getActionIndex();
        String str = this.mEventName;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1786514288:
                if (str.equals(PointerEventHelper.POINTER_ENTER)) {
                    c = 0;
                    break;
                }
                break;
            case -1780335505:
                if (str.equals(PointerEventHelper.POINTER_LEAVE)) {
                    c = 1;
                    break;
                }
                break;
            case -1304584214:
                if (str.equals(PointerEventHelper.POINTER_DOWN)) {
                    c = 2;
                    break;
                }
                break;
            case -1304316135:
                if (str.equals(PointerEventHelper.POINTER_MOVE)) {
                    c = 3;
                    break;
                }
                break;
            case -1065042973:
                if (str.equals(PointerEventHelper.POINTER_UP)) {
                    c = 4;
                    break;
                }
                break;
            case 383186882:
                if (str.equals(PointerEventHelper.POINTER_CANCEL)) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 4:
                return Arrays.asList(createPointerEventData(actionIndex));
            case 3:
            case 5:
                return createPointerEvents();
            default:
                return null;
        }
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatchModern(RCTModernEventEmitter rCTModernEventEmitter) {
        if (this.mMotionEvent == null) {
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Cannot dispatch a Pointer that has no MotionEvent; the PointerEvehas been recycled"));
            return;
        }
        if (this.mPointersEventData == null) {
            this.mPointersEventData = createPointersEventData();
        }
        List<WritableMap> list = this.mPointersEventData;
        if (list == null) {
            return;
        }
        boolean z = list.size() > 1;
        for (WritableMap writableMap : this.mPointersEventData) {
            if (z) {
                writableMap = writableMap.copy();
            }
            WritableMap writableMap2 = writableMap;
            int surfaceId = getSurfaceId();
            int viewTag = getViewTag();
            String str = this.mEventName;
            int r10 = this.mCoalescingKey;
            rCTModernEventEmitter.receiveEvent(surfaceId, viewTag, str, r10 != -1, r10, writableMap2, PointerEventHelper.getEventCategory(str));
        }
    }
}
