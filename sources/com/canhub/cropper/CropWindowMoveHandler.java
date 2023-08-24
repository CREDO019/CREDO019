package com.canhub.cropper;

import android.graphics.PointF;
import android.graphics.RectF;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CropWindowMoveHandler.kt */
@Metadata(m184d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001f\u0018\u0000 82\u00020\u0001:\u000289B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJH\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J@\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u001bH\u0002J\u0018\u0010\"\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J \u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002JH\u0010$\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u001bH\u0002J\u0018\u0010'\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J@\u0010(\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J \u0010*\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u0018\u0010+\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J \u0010,\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002JN\u0010-\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u001b2\u0006\u0010\u0019\u001a\u00020\u0007J@\u00101\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00102\u001a\u00020\u0007H\u0002JH\u00103\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J@\u00104\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007H\u0002J \u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u0007H\u0002R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, m183d2 = {"Lcom/canhub/cropper/CropWindowMoveHandler;", "", SessionDescription.ATTR_TYPE, "Lcom/canhub/cropper/CropWindowMoveHandler$Type;", "cropWindowHandler", "Lcom/canhub/cropper/CropWindowHandler;", "touchX", "", "touchY", "(Lcom/canhub/cropper/CropWindowMoveHandler$Type;Lcom/canhub/cropper/CropWindowHandler;FF)V", "mMaxCropHeight", "mMaxCropWidth", "mMinCropHeight", "mMinCropWidth", "mTouchOffset", "Landroid/graphics/PointF;", "adjustBottom", "", "rect", "Landroid/graphics/RectF;", ViewProps.BOTTOM, "bounds", "viewHeight", "", "snapMargin", ViewProps.ASPECT_RATIO, "leftMoves", "", "rightMoves", "adjustBottomByAspectRatio", "adjustLeft", "left", "topMoves", "bottomMoves", "adjustLeftByAspectRatio", "adjustLeftRightByAspectRatio", "adjustRight", "right", "viewWidth", "adjustRightByAspectRatio", "adjustTop", ViewProps.TOP, "adjustTopBottomByAspectRatio", "adjustTopByAspectRatio", "calculateTouchOffset", "move", "x", "y", "fixedAspectRatio", "moveCenter", "snapRadius", "moveSizeWithFixedAspectRatio", "moveSizeWithFreeAspectRatio", "snapEdgesToBounds", "edges", ViewProps.MARGIN, "Companion", "Type", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class CropWindowMoveHandler {
    public static final Companion Companion = new Companion(null);
    private final float mMaxCropHeight;
    private final float mMaxCropWidth;
    private final float mMinCropHeight;
    private final float mMinCropWidth;
    private final PointF mTouchOffset;
    private final Type type;

    /* compiled from: CropWindowMoveHandler.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, m183d2 = {"Lcom/canhub/cropper/CropWindowMoveHandler$Type;", "", "(Ljava/lang/String;I)V", "TOP_LEFT", "TOP_RIGHT", "BOTTOM_LEFT", "BOTTOM_RIGHT", "LEFT", "TOP", "RIGHT", "BOTTOM", "CENTER", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public enum Type {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        CENTER
    }

    /* compiled from: CropWindowMoveHandler.kt */
    @Metadata(m182k = 3, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[Type.values().length];
            r0[Type.TOP_LEFT.ordinal()] = 1;
            r0[Type.TOP_RIGHT.ordinal()] = 2;
            r0[Type.BOTTOM_LEFT.ordinal()] = 3;
            r0[Type.BOTTOM_RIGHT.ordinal()] = 4;
            r0[Type.LEFT.ordinal()] = 5;
            r0[Type.TOP.ordinal()] = 6;
            r0[Type.RIGHT.ordinal()] = 7;
            r0[Type.BOTTOM.ordinal()] = 8;
            r0[Type.CENTER.ordinal()] = 9;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public CropWindowMoveHandler(Type type, CropWindowHandler cropWindowHandler, float f, float f2) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(cropWindowHandler, "cropWindowHandler");
        this.type = type;
        this.mMinCropWidth = cropWindowHandler.getMinCropWidth();
        this.mMinCropHeight = cropWindowHandler.getMinCropHeight();
        this.mMaxCropWidth = cropWindowHandler.getMaxCropWidth();
        this.mMaxCropHeight = cropWindowHandler.getMaxCropHeight();
        this.mTouchOffset = new PointF(0.0f, 0.0f);
        calculateTouchOffset(cropWindowHandler.getRect(), f, f2);
    }

    /* compiled from: CropWindowMoveHandler.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002¨\u0006\t"}, m183d2 = {"Lcom/canhub/cropper/CropWindowMoveHandler$Companion;", "", "()V", "calculateAspectRatio", "", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float calculateAspectRatio(float f, float f2, float f3, float f4) {
            return (f3 - f) / (f4 - f2);
        }

        private Companion() {
        }
    }

    public final void move(RectF rect, float f, float f2, RectF bounds, int r15, int r16, float f3, boolean z, float f4) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        float f5 = f + this.mTouchOffset.x;
        float f6 = f2 + this.mTouchOffset.y;
        if (this.type == Type.CENTER) {
            moveCenter(rect, f5, f6, bounds, r15, r16, f3);
        } else if (z) {
            moveSizeWithFixedAspectRatio(rect, f5, f6, bounds, r15, r16, f3, f4);
        } else {
            moveSizeWithFreeAspectRatio(rect, f5, f6, bounds, r15, r16, f3);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final void calculateTouchOffset(RectF rectF, float f, float f2) {
        float f3;
        float f4;
        float f5;
        float f6 = 0.0f;
        switch (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()]) {
            case 1:
                f6 = rectF.left - f;
                f3 = rectF.top;
                f5 = f3 - f2;
                break;
            case 2:
                f6 = rectF.right - f;
                f3 = rectF.top;
                f5 = f3 - f2;
                break;
            case 3:
                f6 = rectF.left - f;
                f3 = rectF.bottom;
                f5 = f3 - f2;
                break;
            case 4:
                f6 = rectF.right - f;
                f3 = rectF.bottom;
                f5 = f3 - f2;
                break;
            case 5:
                f4 = rectF.left;
                f6 = f4 - f;
                f5 = 0.0f;
                break;
            case 6:
                f3 = rectF.top;
                f5 = f3 - f2;
                break;
            case 7:
                f4 = rectF.right;
                f6 = f4 - f;
                f5 = 0.0f;
                break;
            case 8:
                f3 = rectF.bottom;
                f5 = f3 - f2;
                break;
            case 9:
                f6 = rectF.centerX() - f;
                f3 = rectF.centerY();
                f5 = f3 - f2;
                break;
            default:
                f5 = 0.0f;
                break;
        }
        this.mTouchOffset.x = f6;
        this.mTouchOffset.y = f5;
    }

    private final void moveCenter(RectF rectF, float f, float f2, RectF rectF2, int r10, int r11, float f3) {
        float centerX = f - rectF.centerX();
        float centerY = f2 - rectF.centerY();
        if (rectF.left + centerX < 0.0f || rectF.right + centerX > r10 || rectF.left + centerX < rectF2.left || rectF.right + centerX > rectF2.right) {
            centerX /= 1.05f;
            this.mTouchOffset.x -= centerX / 2;
        }
        if (rectF.top + centerY < 0.0f || rectF.bottom + centerY > r11 || rectF.top + centerY < rectF2.top || rectF.bottom + centerY > rectF2.bottom) {
            centerY /= 1.05f;
            this.mTouchOffset.y -= centerY / 2;
        }
        rectF.offset(centerX, centerY);
        snapEdgesToBounds(rectF, rectF2, f3);
    }

    private final void moveSizeWithFreeAspectRatio(RectF rectF, float f, float f2, RectF rectF2, int r15, int r16, float f3) {
        switch (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()]) {
            case 1:
                adjustTop(rectF, f2, rectF2, f3, 0.0f, false, false);
                adjustLeft(rectF, f, rectF2, f3, 0.0f, false, false);
                return;
            case 2:
                adjustTop(rectF, f2, rectF2, f3, 0.0f, false, false);
                adjustRight(rectF, f, rectF2, r15, f3, 0.0f, false, false);
                return;
            case 3:
                adjustBottom(rectF, f2, rectF2, r16, f3, 0.0f, false, false);
                adjustLeft(rectF, f, rectF2, f3, 0.0f, false, false);
                return;
            case 4:
                adjustBottom(rectF, f2, rectF2, r16, f3, 0.0f, false, false);
                adjustRight(rectF, f, rectF2, r15, f3, 0.0f, false, false);
                return;
            case 5:
                adjustLeft(rectF, f, rectF2, f3, 0.0f, false, false);
                return;
            case 6:
                adjustTop(rectF, f2, rectF2, f3, 0.0f, false, false);
                return;
            case 7:
                adjustRight(rectF, f, rectF2, r15, f3, 0.0f, false, false);
                return;
            case 8:
                adjustBottom(rectF, f2, rectF2, r16, f3, 0.0f, false, false);
                return;
            default:
                return;
        }
    }

    private final void moveSizeWithFixedAspectRatio(RectF rectF, float f, float f2, RectF rectF2, int r18, int r19, float f3, float f4) {
        switch (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()]) {
            case 1:
                if (Companion.calculateAspectRatio(f, f2, rectF.right, rectF.bottom) < f4) {
                    adjustTop(rectF, f2, rectF2, f3, f4, true, false);
                    adjustLeftByAspectRatio(rectF, f4);
                    return;
                }
                adjustLeft(rectF, f, rectF2, f3, f4, true, false);
                adjustTopByAspectRatio(rectF, f4);
                return;
            case 2:
                if (Companion.calculateAspectRatio(rectF.left, f2, f, rectF.bottom) < f4) {
                    adjustTop(rectF, f2, rectF2, f3, f4, false, true);
                    adjustRightByAspectRatio(rectF, f4);
                    return;
                }
                adjustRight(rectF, f, rectF2, r18, f3, f4, true, false);
                adjustTopByAspectRatio(rectF, f4);
                return;
            case 3:
                if (Companion.calculateAspectRatio(f, rectF.top, rectF.right, f2) < f4) {
                    adjustBottom(rectF, f2, rectF2, r19, f3, f4, true, false);
                    adjustLeftByAspectRatio(rectF, f4);
                    return;
                }
                adjustLeft(rectF, f, rectF2, f3, f4, false, true);
                adjustBottomByAspectRatio(rectF, f4);
                return;
            case 4:
                if (Companion.calculateAspectRatio(rectF.left, rectF.top, f, f2) < f4) {
                    adjustBottom(rectF, f2, rectF2, r19, f3, f4, false, true);
                    adjustRightByAspectRatio(rectF, f4);
                    return;
                }
                adjustRight(rectF, f, rectF2, r18, f3, f4, false, true);
                adjustBottomByAspectRatio(rectF, f4);
                return;
            case 5:
                adjustLeft(rectF, f, rectF2, f3, f4, true, true);
                adjustTopBottomByAspectRatio(rectF, rectF2, f4);
                return;
            case 6:
                adjustTop(rectF, f2, rectF2, f3, f4, true, true);
                adjustLeftRightByAspectRatio(rectF, rectF2, f4);
                return;
            case 7:
                adjustRight(rectF, f, rectF2, r18, f3, f4, true, true);
                adjustTopBottomByAspectRatio(rectF, rectF2, f4);
                return;
            case 8:
                adjustBottom(rectF, f2, rectF2, r19, f3, f4, true, true);
                adjustLeftRightByAspectRatio(rectF, rectF2, f4);
                return;
            default:
                return;
        }
    }

    private final void snapEdgesToBounds(RectF rectF, RectF rectF2, float f) {
        if (rectF.left < rectF2.left + f) {
            rectF.offset(rectF2.left - rectF.left, 0.0f);
        }
        if (rectF.top < rectF2.top + f) {
            rectF.offset(0.0f, rectF2.top - rectF.top);
        }
        if (rectF.right > rectF2.right - f) {
            rectF.offset(rectF2.right - rectF.right, 0.0f);
        }
        if (rectF.bottom > rectF2.bottom - f) {
            rectF.offset(0.0f, rectF2.bottom - rectF.bottom);
        }
    }

    private final void adjustLeft(RectF rectF, float f, RectF rectF2, float f2, float f3, boolean z, boolean z2) {
        if (f < 0.0f) {
            f /= 1.05f;
            this.mTouchOffset.x -= f / 1.1f;
        }
        if (f < rectF2.left) {
            this.mTouchOffset.x -= (f - rectF2.left) / 2.0f;
        }
        if (f - rectF2.left < f2) {
            f = rectF2.left;
        }
        if (rectF.right - f < this.mMinCropWidth) {
            f = rectF.right - this.mMinCropWidth;
        }
        if (rectF.right - f > this.mMaxCropWidth) {
            f = rectF.right - this.mMaxCropWidth;
        }
        if (f - rectF2.left < f2) {
            f = rectF2.left;
        }
        if (f3 > 0.0f) {
            float f4 = (rectF.right - f) / f3;
            if (f4 < this.mMinCropHeight) {
                f = Math.max(rectF2.left, rectF.right - (this.mMinCropHeight * f3));
                f4 = (rectF.right - f) / f3;
            }
            if (f4 > this.mMaxCropHeight) {
                f = Math.max(rectF2.left, rectF.right - (this.mMaxCropHeight * f3));
                f4 = (rectF.right - f) / f3;
            }
            if (z && z2) {
                f = Math.max(f, Math.max(rectF2.left, rectF.right - (rectF2.height() * f3)));
            } else {
                if (z && rectF.bottom - f4 < rectF2.top) {
                    f = Math.max(rectF2.left, rectF.right - ((rectF.bottom - rectF2.top) * f3));
                    f4 = (rectF.right - f) / f3;
                }
                if (z2 && rectF.top + f4 > rectF2.bottom) {
                    f = Math.max(f, Math.max(rectF2.left, rectF.right - ((rectF2.bottom - rectF.top) * f3)));
                }
            }
        }
        rectF.left = f;
    }

    private final void adjustRight(RectF rectF, float f, RectF rectF2, int r7, float f2, float f3, boolean z, boolean z2) {
        float f4 = r7;
        if (f > f4) {
            f = ((f - f4) / 1.05f) + f4;
            this.mTouchOffset.x -= (f - f4) / 1.1f;
        }
        if (f > rectF2.right) {
            this.mTouchOffset.x -= (f - rectF2.right) / 2.0f;
        }
        if (rectF2.right - f < f2) {
            f = rectF2.right;
        }
        if (f - rectF.left < this.mMinCropWidth) {
            f = rectF.left + this.mMinCropWidth;
        }
        if (f - rectF.left > this.mMaxCropWidth) {
            f = rectF.left + this.mMaxCropWidth;
        }
        if (rectF2.right - f < f2) {
            f = rectF2.right;
        }
        if (f3 > 0.0f) {
            float f5 = (f - rectF.left) / f3;
            if (f5 < this.mMinCropHeight) {
                f = Math.min(rectF2.right, rectF.left + (this.mMinCropHeight * f3));
                f5 = (f - rectF.left) / f3;
            }
            if (f5 > this.mMaxCropHeight) {
                f = Math.min(rectF2.right, rectF.left + (this.mMaxCropHeight * f3));
                f5 = (f - rectF.left) / f3;
            }
            if (z && z2) {
                f = Math.min(f, Math.min(rectF2.right, rectF.left + (rectF2.height() * f3)));
            } else {
                if (z && rectF.bottom - f5 < rectF2.top) {
                    f = Math.min(rectF2.right, rectF.left + ((rectF.bottom - rectF2.top) * f3));
                    f5 = (f - rectF.left) / f3;
                }
                if (z2 && rectF.top + f5 > rectF2.bottom) {
                    f = Math.min(f, Math.min(rectF2.right, rectF.left + ((rectF2.bottom - rectF.top) * f3)));
                }
            }
        }
        rectF.right = f;
    }

    private final void adjustTop(RectF rectF, float f, RectF rectF2, float f2, float f3, boolean z, boolean z2) {
        if (f < 0.0f) {
            f /= 1.05f;
            this.mTouchOffset.y -= f / 1.1f;
        }
        if (f < rectF2.top) {
            this.mTouchOffset.y -= (f - rectF2.top) / 2.0f;
        }
        if (f - rectF2.top < f2) {
            f = rectF2.top;
        }
        if (rectF.bottom - f < this.mMinCropHeight) {
            f = rectF.bottom - this.mMinCropHeight;
        }
        if (rectF.bottom - f > this.mMaxCropHeight) {
            f = rectF.bottom - this.mMaxCropHeight;
        }
        if (f - rectF2.top < f2) {
            f = rectF2.top;
        }
        if (f3 > 0.0f) {
            float f4 = (rectF.bottom - f) * f3;
            if (f4 < this.mMinCropWidth) {
                f = Math.max(rectF2.top, rectF.bottom - (this.mMinCropWidth / f3));
                f4 = (rectF.bottom - f) * f3;
            }
            if (f4 > this.mMaxCropWidth) {
                f = Math.max(rectF2.top, rectF.bottom - (this.mMaxCropWidth / f3));
                f4 = (rectF.bottom - f) * f3;
            }
            if (z && z2) {
                f = Math.max(f, Math.max(rectF2.top, rectF.bottom - (rectF2.width() / f3)));
            } else {
                if (z && rectF.right - f4 < rectF2.left) {
                    f = Math.max(rectF2.top, rectF.bottom - ((rectF.right - rectF2.left) / f3));
                    f4 = (rectF.bottom - f) * f3;
                }
                if (z2 && rectF.left + f4 > rectF2.right) {
                    f = Math.max(f, Math.max(rectF2.top, rectF.bottom - ((rectF2.right - rectF.left) / f3)));
                }
            }
        }
        rectF.top = f;
    }

    private final void adjustBottom(RectF rectF, float f, RectF rectF2, int r7, float f2, float f3, boolean z, boolean z2) {
        float f4 = r7;
        if (f > f4) {
            f = ((f - f4) / 1.05f) + f4;
            this.mTouchOffset.y -= (f - f4) / 1.1f;
        }
        if (f > rectF2.bottom) {
            this.mTouchOffset.y -= (f - rectF2.bottom) / 2.0f;
        }
        if (rectF2.bottom - f < f2) {
            f = rectF2.bottom;
        }
        if (f - rectF.top < this.mMinCropHeight) {
            f = rectF.top + this.mMinCropHeight;
        }
        if (f - rectF.top > this.mMaxCropHeight) {
            f = rectF.top + this.mMaxCropHeight;
        }
        if (rectF2.bottom - f < f2) {
            f = rectF2.bottom;
        }
        if (f3 > 0.0f) {
            float f5 = (f - rectF.top) * f3;
            if (f5 < this.mMinCropWidth) {
                f = Math.min(rectF2.bottom, rectF.top + (this.mMinCropWidth / f3));
                f5 = (f - rectF.top) * f3;
            }
            if (f5 > this.mMaxCropWidth) {
                f = Math.min(rectF2.bottom, rectF.top + (this.mMaxCropWidth / f3));
                f5 = (f - rectF.top) * f3;
            }
            if (z && z2) {
                f = Math.min(f, Math.min(rectF2.bottom, rectF.top + (rectF2.width() / f3)));
            } else {
                if (z && rectF.right - f5 < rectF2.left) {
                    f = Math.min(rectF2.bottom, rectF.top + ((rectF.right - rectF2.left) / f3));
                    f5 = (f - rectF.top) * f3;
                }
                if (z2 && rectF.left + f5 > rectF2.right) {
                    f = Math.min(f, Math.min(rectF2.bottom, rectF.top + ((rectF2.right - rectF.left) / f3)));
                }
            }
        }
        rectF.bottom = f;
    }

    private final void adjustLeftByAspectRatio(RectF rectF, float f) {
        rectF.left = rectF.right - (rectF.height() * f);
    }

    private final void adjustTopByAspectRatio(RectF rectF, float f) {
        rectF.top = rectF.bottom - (rectF.width() / f);
    }

    private final void adjustRightByAspectRatio(RectF rectF, float f) {
        rectF.right = rectF.left + (rectF.height() * f);
    }

    private final void adjustBottomByAspectRatio(RectF rectF, float f) {
        rectF.bottom = rectF.top + (rectF.width() / f);
    }

    private final void adjustLeftRightByAspectRatio(RectF rectF, RectF rectF2, float f) {
        rectF.inset((rectF.width() - (rectF.height() * f)) / 2, 0.0f);
        if (rectF.left < rectF2.left) {
            rectF.offset(rectF2.left - rectF.left, 0.0f);
        }
        if (rectF.right > rectF2.right) {
            rectF.offset(rectF2.right - rectF.right, 0.0f);
        }
    }

    private final void adjustTopBottomByAspectRatio(RectF rectF, RectF rectF2, float f) {
        rectF.inset(0.0f, (rectF.height() - (rectF.width() / f)) / 2);
        if (rectF.top < rectF2.top) {
            rectF.offset(0.0f, rectF2.top - rectF.top);
        }
        if (rectF.bottom > rectF2.bottom) {
            rectF.offset(0.0f, rectF2.bottom - rectF.bottom);
        }
    }
}
