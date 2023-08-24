package com.th3rdwave.safeareacontext;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.util.EnumSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SafeAreaViewShadowNode.kt */
@Metadata(m184d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0017J\u0018\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0018H\u0017J\b\u0010\u001b\u001a\u00020\u000bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m183d2 = {"Lcom/th3rdwave/safeareacontext/SafeAreaViewShadowNode;", "Lcom/facebook/react/uimanager/LayoutShadowNode;", "()V", "mLocalData", "Lcom/th3rdwave/safeareacontext/SafeAreaViewLocalData;", "mMargins", "", "mNeedsUpdate", "", "mPaddings", "onBeforeLayout", "", "nativeViewHierarchyOptimizer", "Lcom/facebook/react/uimanager/NativeViewHierarchyOptimizer;", "resetInsets", "mode", "Lcom/th3rdwave/safeareacontext/SafeAreaViewMode;", "setLocalData", "data", "", "setMargins", "index", "", ViewProps.MARGIN, "Lcom/facebook/react/bridge/Dynamic;", "setPaddings", ViewProps.PADDING, "updateInsets", "react-native-safe-area-context_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SafeAreaViewShadowNode extends LayoutShadowNode {
    private SafeAreaViewLocalData mLocalData;
    private boolean mNeedsUpdate;
    private final float[] mPaddings = new float[ViewProps.PADDING_MARGIN_SPACING_TYPES.length];
    private final float[] mMargins = new float[ViewProps.PADDING_MARGIN_SPACING_TYPES.length];

    public SafeAreaViewShadowNode() {
        int length = ViewProps.PADDING_MARGIN_SPACING_TYPES.length;
        for (int r1 = 0; r1 < length; r1++) {
            this.mPaddings[r1] = Float.NaN;
            this.mMargins[r1] = Float.NaN;
        }
    }

    private final void updateInsets() {
        float f;
        float f2;
        float f3;
        SafeAreaViewLocalData safeAreaViewLocalData = this.mLocalData;
        if (safeAreaViewLocalData == null) {
            return;
        }
        float[] fArr = safeAreaViewLocalData.getMode() == SafeAreaViewMode.PADDING ? this.mPaddings : this.mMargins;
        float f4 = fArr[8];
        if (Float.isNaN(f4)) {
            f4 = 0.0f;
            f = 0.0f;
            f2 = 0.0f;
            f3 = 0.0f;
        } else {
            f = f4;
            f2 = f;
            f3 = f2;
        }
        float f5 = fArr[7];
        if (!Float.isNaN(f5)) {
            f4 = f5;
            f2 = f4;
        }
        float f6 = fArr[6];
        if (!Float.isNaN(f6)) {
            f = f6;
            f3 = f;
        }
        float f7 = fArr[1];
        if (!Float.isNaN(f7)) {
            f4 = f7;
        }
        float f8 = fArr[2];
        if (!Float.isNaN(f8)) {
            f = f8;
        }
        float f9 = fArr[3];
        if (!Float.isNaN(f9)) {
            f2 = f9;
        }
        float f10 = fArr[0];
        if (!Float.isNaN(f10)) {
            f3 = f10;
        }
        float pixelFromDIP = PixelUtil.toPixelFromDIP(f4);
        float pixelFromDIP2 = PixelUtil.toPixelFromDIP(f);
        float pixelFromDIP3 = PixelUtil.toPixelFromDIP(f2);
        float pixelFromDIP4 = PixelUtil.toPixelFromDIP(f3);
        EnumSet<SafeAreaViewEdges> edges = safeAreaViewLocalData.getEdges();
        EdgeInsets insets = safeAreaViewLocalData.getInsets();
        float top = edges.contains(SafeAreaViewEdges.TOP) ? insets.getTop() : 0.0f;
        float right = edges.contains(SafeAreaViewEdges.RIGHT) ? insets.getRight() : 0.0f;
        float bottom = edges.contains(SafeAreaViewEdges.BOTTOM) ? insets.getBottom() : 0.0f;
        float left = edges.contains(SafeAreaViewEdges.LEFT) ? insets.getLeft() : 0.0f;
        if (safeAreaViewLocalData.getMode() == SafeAreaViewMode.PADDING) {
            super.setPadding(1, top + pixelFromDIP);
            super.setPadding(2, right + pixelFromDIP2);
            super.setPadding(3, bottom + pixelFromDIP3);
            super.setPadding(0, left + pixelFromDIP4);
            return;
        }
        super.setMargin(1, top + pixelFromDIP);
        super.setMargin(2, right + pixelFromDIP2);
        super.setMargin(3, bottom + pixelFromDIP3);
        super.setMargin(0, left + pixelFromDIP4);
    }

    private final void resetInsets(SafeAreaViewMode safeAreaViewMode) {
        if (safeAreaViewMode == SafeAreaViewMode.PADDING) {
            super.setPadding(1, this.mPaddings[1]);
            super.setPadding(2, this.mPaddings[1]);
            super.setPadding(3, this.mPaddings[3]);
            super.setPadding(0, this.mPaddings[0]);
        } else {
            super.setMargin(1, this.mMargins[1]);
            super.setMargin(2, this.mMargins[1]);
            super.setMargin(3, this.mMargins[3]);
            super.setMargin(0, this.mMargins[0]);
        }
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onBeforeLayout(NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        Intrinsics.checkNotNullParameter(nativeViewHierarchyOptimizer, "nativeViewHierarchyOptimizer");
        if (this.mNeedsUpdate) {
            this.mNeedsUpdate = false;
            updateInsets();
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void setLocalData(Object data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof SafeAreaViewLocalData) {
            SafeAreaViewLocalData safeAreaViewLocalData = this.mLocalData;
            if (safeAreaViewLocalData != null && safeAreaViewLocalData.getMode() != ((SafeAreaViewLocalData) data).getMode()) {
                resetInsets(safeAreaViewLocalData.getMode());
            }
            this.mLocalData = (SafeAreaViewLocalData) data;
            this.mNeedsUpdate = false;
            updateInsets();
        }
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    @ReactPropGroup(names = {ViewProps.PADDING, ViewProps.PADDING_VERTICAL, ViewProps.PADDING_HORIZONTAL, ViewProps.PADDING_START, ViewProps.PADDING_END, ViewProps.PADDING_TOP, ViewProps.PADDING_BOTTOM, ViewProps.PADDING_LEFT, ViewProps.PADDING_RIGHT})
    public void setPaddings(int r5, Dynamic padding) {
        Intrinsics.checkNotNullParameter(padding, "padding");
        this.mPaddings[ViewProps.PADDING_MARGIN_SPACING_TYPES[r5]] = padding.getType() == ReadableType.Number ? (float) padding.asDouble() : Float.NaN;
        super.setPaddings(r5, padding);
        this.mNeedsUpdate = true;
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    @ReactPropGroup(names = {ViewProps.MARGIN, ViewProps.MARGIN_VERTICAL, ViewProps.MARGIN_HORIZONTAL, ViewProps.MARGIN_START, ViewProps.MARGIN_END, ViewProps.MARGIN_TOP, ViewProps.MARGIN_BOTTOM, ViewProps.MARGIN_LEFT, ViewProps.MARGIN_RIGHT})
    public void setMargins(int r5, Dynamic margin) {
        Intrinsics.checkNotNullParameter(margin, "margin");
        this.mMargins[ViewProps.PADDING_MARGIN_SPACING_TYPES[r5]] = margin.getType() == ReadableType.Number ? (float) margin.asDouble() : Float.NaN;
        super.setMargins(r5, margin);
        this.mNeedsUpdate = true;
    }
}
