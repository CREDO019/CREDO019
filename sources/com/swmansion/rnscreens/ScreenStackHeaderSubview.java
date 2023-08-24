package com.swmansion.rnscreens;

import android.view.View;
import android.view.ViewParent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.view.ReactViewGroup;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenStackHeaderSubview.kt */
@Metadata(m184d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001dB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\nH\u0014J\u0018\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\nH\u0014R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, m183d2 = {"Lcom/swmansion/rnscreens/ScreenStackHeaderSubview;", "Lcom/facebook/react/views/view/ReactViewGroup;", "context", "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "config", "Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "getConfig", "()Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "mReactHeight", "", "mReactWidth", SessionDescription.ATTR_TYPE, "Lcom/swmansion/rnscreens/ScreenStackHeaderSubview$Type;", "getType", "()Lcom/swmansion/rnscreens/ScreenStackHeaderSubview$Type;", "setType", "(Lcom/swmansion/rnscreens/ScreenStackHeaderSubview$Type;)V", ViewProps.ON_LAYOUT, "", "changed", "", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "Type", "react-native-screens_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ScreenStackHeaderSubview extends ReactViewGroup {
    private int mReactHeight;
    private int mReactWidth;
    private Type type;

    /* compiled from: ScreenStackHeaderSubview.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m183d2 = {"Lcom/swmansion/rnscreens/ScreenStackHeaderSubview$Type;", "", "(Ljava/lang/String;I)V", "LEFT", "CENTER", "RIGHT", "BACK", "SEARCH_BAR", "react-native-screens_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public enum Type {
        LEFT,
        CENTER,
        RIGHT,
        BACK,
        SEARCH_BAR
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int r2, int r3, int r4, int r5) {
    }

    public ScreenStackHeaderSubview(ReactContext reactContext) {
        super(reactContext);
        this.type = Type.RIGHT;
    }

    public final Type getType() {
        return this.type;
    }

    public final void setType(Type type) {
        Intrinsics.checkNotNullParameter(type, "<set-?>");
        this.type = type;
    }

    public final ScreenStackHeaderConfig getConfig() {
        ViewParent parent = getParent();
        CustomToolbar customToolbar = parent instanceof CustomToolbar ? (CustomToolbar) parent : null;
        if (customToolbar != null) {
            return customToolbar.getConfig();
        }
        return null;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
    protected void onMeasure(int r3, int r4) {
        if (View.MeasureSpec.getMode(r3) == 1073741824 && View.MeasureSpec.getMode(r4) == 1073741824) {
            this.mReactWidth = View.MeasureSpec.getSize(r3);
            this.mReactHeight = View.MeasureSpec.getSize(r4);
            ViewParent parent = getParent();
            if (parent != null) {
                forceLayout();
                ((View) parent).requestLayout();
            }
        }
        setMeasuredDimension(this.mReactWidth, this.mReactHeight);
    }
}
