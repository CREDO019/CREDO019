package com.facebook.react.views.image;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import io.invertase.googlemobileads.ReactNativeGoogleMobileAdsEvent;

/* loaded from: classes.dex */
public class ImageLoadEvent extends Event<ImageLoadEvent> {
    public static final int ON_ERROR = 1;
    public static final int ON_LOAD = 2;
    public static final int ON_LOAD_END = 3;
    public static final int ON_LOAD_START = 4;
    public static final int ON_PROGRESS = 5;
    private final String mErrorMessage;
    private final int mEventType;
    private final int mHeight;
    private final int mLoaded;
    private final String mSourceUri;
    private final int mTotal;
    private final int mWidth;

    @Deprecated
    public static final ImageLoadEvent createLoadStartEvent(int r1) {
        return createLoadStartEvent(-1, r1);
    }

    @Deprecated
    public static final ImageLoadEvent createProgressEvent(int r1, String str, int r3, int r4) {
        return createProgressEvent(-1, r1, str, r3, r4);
    }

    @Deprecated
    public static final ImageLoadEvent createLoadEvent(int r1, String str, int r3, int r4) {
        return createLoadEvent(-1, r1, str, r3, r4);
    }

    @Deprecated
    public static final ImageLoadEvent createErrorEvent(int r1, Throwable th) {
        return createErrorEvent(-1, r1, th);
    }

    @Deprecated
    public static final ImageLoadEvent createLoadEndEvent(int r1) {
        return createLoadEndEvent(-1, r1);
    }

    public static final ImageLoadEvent createLoadStartEvent(int r2, int r3) {
        return new ImageLoadEvent(r2, r3, 4);
    }

    public static final ImageLoadEvent createProgressEvent(int r11, int r12, String str, int r14, int r15) {
        return new ImageLoadEvent(r11, r12, 5, null, str, 0, 0, r14, r15);
    }

    public static final ImageLoadEvent createLoadEvent(int r11, int r12, String str, int r14, int r15) {
        return new ImageLoadEvent(r11, r12, 2, null, str, r14, r15, 0, 0);
    }

    public static final ImageLoadEvent createErrorEvent(int r11, int r12, Throwable th) {
        return new ImageLoadEvent(r11, r12, 1, th.getMessage(), null, 0, 0, 0, 0);
    }

    public static final ImageLoadEvent createLoadEndEvent(int r2, int r3) {
        return new ImageLoadEvent(r2, r3, 3);
    }

    private ImageLoadEvent(int r11, int r12, int r13) {
        this(r11, r12, r13, null, null, 0, 0, 0, 0);
    }

    private ImageLoadEvent(int r1, int r2, int r3, String str, String str2, int r6, int r7, int r8, int r9) {
        super(r1, r2);
        this.mEventType = r3;
        this.mErrorMessage = str;
        this.mSourceUri = str2;
        this.mWidth = r6;
        this.mHeight = r7;
        this.mLoaded = r8;
        this.mTotal = r9;
    }

    public static String eventNameForType(int r3) {
        if (r3 != 1) {
            if (r3 != 2) {
                if (r3 != 3) {
                    if (r3 != 4) {
                        if (r3 == 5) {
                            return "topProgress";
                        }
                        throw new IllegalStateException("Invalid image event: " + Integer.toString(r3));
                    }
                    return "topLoadStart";
                }
                return "topLoadEnd";
            }
            return "topLoad";
        }
        return "topError";
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return eventNameForType(this.mEventType);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) this.mEventType;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        int r1 = this.mEventType;
        if (r1 == 1) {
            createMap.putString("error", this.mErrorMessage);
        } else if (r1 == 2) {
            createMap.putMap("source", createEventDataSource());
        } else if (r1 == 5) {
            createMap.putInt(ReactNativeGoogleMobileAdsEvent.GOOGLE_MOBILE_ADS_EVENT_LOADED, this.mLoaded);
            createMap.putInt("total", this.mTotal);
        }
        return createMap;
    }

    private WritableMap createEventDataSource() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("uri", this.mSourceUri);
        createMap.putDouble("width", this.mWidth);
        createMap.putDouble("height", this.mHeight);
        return createMap;
    }
}
