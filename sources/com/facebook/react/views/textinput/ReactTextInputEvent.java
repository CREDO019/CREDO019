package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;

/* loaded from: classes.dex */
public class ReactTextInputEvent extends Event<ReactTextInputEvent> {
    public static final String EVENT_NAME = "topTextInput";
    private String mPreviousText;
    private int mRangeEnd;
    private int mRangeStart;
    private String mText;

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Deprecated
    public ReactTextInputEvent(int r8, String str, String str2, int r11, int r12) {
        this(-1, r8, str, str2, r11, r12);
    }

    public ReactTextInputEvent(int r1, int r2, String str, String str2, int r5, int r6) {
        super(r1, r2);
        this.mText = str;
        this.mPreviousText = str2;
        this.mRangeStart = r5;
        this.mRangeEnd = r6;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("start", this.mRangeStart);
        createMap2.putDouble("end", this.mRangeEnd);
        createMap.putString("text", this.mText);
        createMap.putString("previousText", this.mPreviousText);
        createMap.putMap(SessionDescription.ATTR_RANGE, createMap2);
        createMap.putInt(TouchesHelper.TARGET_KEY, getViewTag());
        return createMap;
    }
}
