package com.th3rdwave.safeareacontext;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InsetsChangeEvent.kt */
@Metadata(m184d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m183d2 = {"Lcom/th3rdwave/safeareacontext/InsetsChangeEvent;", "Lcom/facebook/react/uimanager/events/Event;", "surfaceId", "", "viewTag", "mInsets", "Lcom/th3rdwave/safeareacontext/EdgeInsets;", "mFrame", "Lcom/th3rdwave/safeareacontext/Rect;", "(IILcom/th3rdwave/safeareacontext/EdgeInsets;Lcom/th3rdwave/safeareacontext/Rect;)V", "dispatch", "", "rctEventEmitter", "Lcom/facebook/react/uimanager/events/RCTEventEmitter;", "getEventName", "", "Companion", "react-native-safe-area-context_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class InsetsChangeEvent extends Event<InsetsChangeEvent> {
    public static final Companion Companion = new Companion(null);
    public static final String EVENT_NAME = "topInsetsChange";
    private final Rect mFrame;
    private final EdgeInsets mInsets;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InsetsChangeEvent(int r1, int r2, EdgeInsets mInsets, Rect mFrame) {
        super(r2);
        Intrinsics.checkNotNullParameter(mInsets, "mInsets");
        Intrinsics.checkNotNullParameter(mFrame, "mFrame");
        this.mInsets = mInsets;
        this.mFrame = mFrame;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        Intrinsics.checkNotNullParameter(rctEventEmitter, "rctEventEmitter");
        WritableMap createMap = Arguments.createMap();
        createMap.putMap("insets", SerializationUtils.edgeInsetsToJsMap(this.mInsets));
        createMap.putMap("frame", SerializationUtils.rectToJsMap(this.mFrame));
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), createMap);
    }

    /* compiled from: InsetsChangeEvent.kt */
    @Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m183d2 = {"Lcom/th3rdwave/safeareacontext/InsetsChangeEvent$Companion;", "", "()V", "EVENT_NAME", "", "react-native-safe-area-context_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
