package com.facebook.react.uimanager.events;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.common.ViewUtil;

/* loaded from: classes.dex */
public class ReactEventEmitter implements RCTModernEventEmitter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "ReactEventEmitter";
    private RCTModernEventEmitter mFabricEventEmitter = null;
    private RCTEventEmitter mRCTEventEmitter = null;
    private final ReactApplicationContext mReactContext;

    public ReactEventEmitter(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
    }

    public void register(int r1, RCTModernEventEmitter rCTModernEventEmitter) {
        this.mFabricEventEmitter = rCTModernEventEmitter;
    }

    public void register(int r1, RCTEventEmitter rCTEventEmitter) {
        this.mRCTEventEmitter = rCTEventEmitter;
    }

    public void unregister(int r3) {
        if (r3 == 1) {
            this.mRCTEventEmitter = null;
        } else {
            this.mFabricEventEmitter = null;
        }
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int r2, String str, WritableMap writableMap) {
        receiveEvent(-1, r2, str, writableMap);
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveEvent(int r9, int r10, String str, WritableMap writableMap) {
        receiveEvent(r9, r10, str, false, 0, writableMap, 2);
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        Assertions.assertCondition(writableArray.size() > 0);
        int r0 = writableArray.getMap(0).getInt(TouchesHelper.TARGET_KEY);
        if (ViewUtil.getUIManagerType(r0) != 1 || getEventEmitter(r0) == null) {
            return;
        }
        this.mRCTEventEmitter.receiveTouches(str, writableArray, writableArray2);
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveTouches(TouchEvent touchEvent) {
        RCTModernEventEmitter rCTModernEventEmitter;
        int viewTag = touchEvent.getViewTag();
        int uIManagerType = touchEvent.getUIManagerType();
        if (uIManagerType == 2 && (rCTModernEventEmitter = this.mFabricEventEmitter) != null) {
            rCTModernEventEmitter.receiveTouches(touchEvent);
        } else if (uIManagerType == 1 && getEventEmitter(viewTag) != null) {
            TouchesHelper.sendTouchesLegacy(this.mRCTEventEmitter, touchEvent);
        } else {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Cannot find EventEmitter for receivedTouches: ReactTag[" + viewTag + "] UIManagerType[" + uIManagerType + "] EventName[" + touchEvent.getEventName() + "]"));
        }
    }

    private RCTEventEmitter getEventEmitter(int r5) {
        int uIManagerType = ViewUtil.getUIManagerType(r5);
        if (this.mRCTEventEmitter == null) {
            if (this.mReactContext.hasActiveReactInstance()) {
                this.mRCTEventEmitter = (RCTEventEmitter) this.mReactContext.getJSModule(RCTEventEmitter.class);
            } else {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Cannot get RCTEventEmitter from Context for reactTag: " + r5 + " - uiManagerType: " + uIManagerType + " - No active Catalyst instance!"));
            }
        }
        return this.mRCTEventEmitter;
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveEvent(int r10, int r11, String str, boolean z, int r14, WritableMap writableMap, int r16) {
        RCTModernEventEmitter rCTModernEventEmitter;
        int uIManagerType = ViewUtil.getUIManagerType(r11);
        if (uIManagerType == 2 && (rCTModernEventEmitter = this.mFabricEventEmitter) != null) {
            rCTModernEventEmitter.receiveEvent(r10, r11, str, z, r14, writableMap, r16);
        } else if (uIManagerType == 1 && getEventEmitter(r11) != null) {
            this.mRCTEventEmitter.receiveEvent(r11, str, writableMap);
        } else {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Cannot find EventEmitter for receiveEvent: SurfaceId[" + r10 + "] ReactTag[" + r11 + "] UIManagerType[" + uIManagerType + "] EventName[" + str + "]"));
        }
    }
}
