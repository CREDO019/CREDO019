package com.canhub.cropper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.canhub.cropper.CropImageView;
import com.canhub.cropper.common.CommonVersionCheck;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.reactcommunity.rndatetimepicker.Common;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CropOverlayView.kt */
@Metadata(m184d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b/\u0018\u0000 ¥\u00012\u00020\u0001:\u0006¥\u0001¦\u0001§\u0001B\u001d\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\\\u001a\u0002002\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u000200H\u0002J\u0010\u0010`\u001a\u00020^2\u0006\u0010a\u001a\u00020bH\u0002J\u0010\u0010c\u001a\u00020^2\u0006\u0010a\u001a\u00020bH\u0002J0\u0010d\u001a\u00020^2\u0006\u0010a\u001a\u00020b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010e\u001a\u00020\u00192\u0006\u0010f\u001a\u00020\u00192\u0006\u0010g\u001a\u00020\u0019H\u0002J(\u0010h\u001a\u00020^2\u0006\u0010a\u001a\u00020b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010e\u001a\u00020\u00192\u0006\u0010f\u001a\u00020\u0019H\u0002J0\u0010i\u001a\u00020^2\u0006\u0010a\u001a\u00020b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010e\u001a\u00020\u00192\u0006\u0010f\u001a\u00020\u00192\u0006\u0010g\u001a\u00020\u0019H\u0002J\u0010\u0010j\u001a\u00020^2\u0006\u0010a\u001a\u00020bH\u0002J\u0010\u0010k\u001a\u00020^2\u0006\u0010a\u001a\u00020bH\u0002J\u0010\u0010l\u001a\u00020^2\u0006\u0010a\u001a\u00020bH\u0002J(\u0010m\u001a\u00020^2\u0006\u0010a\u001a\u00020b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010e\u001a\u00020\u00192\u0006\u0010f\u001a\u00020\u0019H\u0002J\u0010\u0010n\u001a\u00020^2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0006\u0010o\u001a\u00020^J\b\u0010p\u001a\u00020^H\u0002J\u0018\u0010q\u001a\u00020^2\u0006\u0010r\u001a\u00020\u00192\u0006\u0010s\u001a\u00020\u0019H\u0002J\u0018\u0010t\u001a\u00020^2\u0006\u0010r\u001a\u00020\u00192\u0006\u0010s\u001a\u00020\u0019H\u0002J\b\u0010u\u001a\u00020^H\u0002J\u0010\u0010v\u001a\u00020^2\u0006\u0010a\u001a\u00020bH\u0014J\u0010\u0010w\u001a\u0002002\u0006\u0010x\u001a\u00020yH\u0016J\u0006\u0010z\u001a\u00020^J\u0006\u0010{\u001a\u00020^J \u0010|\u001a\u00020^2\b\u0010}\u001a\u0004\u0018\u00010>2\u0006\u0010~\u001a\u00020\b2\u0006\u0010\u007f\u001a\u00020\bJ\u0010\u0010\u0080\u0001\u001a\u0002002\u0007\u0010\u0081\u0001\u001a\u000200J\u0010\u0010\u0082\u0001\u001a\u00020^2\u0007\u0010\u0083\u0001\u001a\u00020\u0019J\u0010\u0010\u0084\u0001\u001a\u00020^2\u0007\u0010\u0085\u0001\u001a\u00020\u0011J\u0012\u0010\u0086\u0001\u001a\u00020^2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0016J\u0010\u0010\u0088\u0001\u001a\u00020^2\u0007\u0010\u0089\u0001\u001a\u00020\bJ\u0010\u0010\u008a\u0001\u001a\u00020^2\u0007\u0010\u008b\u0001\u001a\u00020\u0019J\u000f\u0010\u008c\u0001\u001a\u00020^2\u0006\u0010\u001b\u001a\u00020\u001aJ\u0012\u0010\u008d\u0001\u001a\u00020^2\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010EJ+\u0010\u008f\u0001\u001a\u00020^2\u0007\u0010\u0090\u0001\u001a\u00020\u00192\u0007\u0010\u0091\u0001\u001a\u00020\u00192\u0007\u0010\u0092\u0001\u001a\u00020\u00192\u0007\u0010\u0093\u0001\u001a\u00020\u0019J\u0010\u0010\u0094\u0001\u001a\u00020^2\u0007\u0010\u0095\u0001\u001a\u000200J\u0010\u0010\u0096\u0001\u001a\u00020^2\u0007\u0010\u0097\u0001\u001a\u000200J\u000f\u0010\u0098\u0001\u001a\u00020^2\u0006\u0010&\u001a\u00020%J\u0010\u0010\u0099\u0001\u001a\u00020^2\u0007\u0010\u009a\u0001\u001a\u00020PJ\u0019\u0010\u009b\u0001\u001a\u00020^2\u0007\u0010\u009c\u0001\u001a\u00020\b2\u0007\u0010\u009d\u0001\u001a\u00020\bJ\u0019\u0010\u009e\u0001\u001a\u00020^2\u0007\u0010\u009f\u0001\u001a\u00020\b2\u0007\u0010 \u0001\u001a\u00020\bJ\u0010\u0010¡\u0001\u001a\u0002002\u0007\u0010¢\u0001\u001a\u000200J\u0010\u0010£\u0001\u001a\u00020^2\u0007\u0010¤\u0001\u001a\u00020\u0019R$\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\"\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0010\u001a\u0004\u0018\u00010\u001a@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR$\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010&\u001a\u0004\u0018\u00010%2\b\u0010\u0010\u001a\u0004\u0018\u00010%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R(\u0010*\u001a\u0004\u0018\u00010)2\b\u0010\u001e\u001a\u0004\u0018\u00010)8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00102\u001a\u0002002\u0006\u0010\u0010\u001a\u000200@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0014\u00104\u001a\u0002008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b4\u00103R\u000e\u00105\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020>X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010A\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010BR\u000e\u0010C\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020GX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010I\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010L\u001a\u0004\u0018\u00010MX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010PX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010Q\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010BR\u000e\u0010R\u001a\u00020SX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010T\u001a\u0004\u0018\u00010UX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010[\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006¨\u0001"}, m183d2 = {"Lcom/canhub/cropper/CropOverlayView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "aspectRatioX", "", "getAspectRatioX", "()I", "setAspectRatioX", "(I)V", "aspectRatioY", "getAspectRatioY", "setAspectRatioY", "<set-?>", "Lcom/canhub/cropper/CropImageView$CropCornerShape;", "cornerShape", "getCornerShape", "()Lcom/canhub/cropper/CropImageView$CropCornerShape;", "cropLabelText", "", "cropLabelTextColor", "cropLabelTextSize", "", "Lcom/canhub/cropper/CropImageView$CropShape;", "cropShape", "getCropShape", "()Lcom/canhub/cropper/CropImageView$CropShape;", "rect", "Landroid/graphics/RectF;", "cropWindowRect", "getCropWindowRect", "()Landroid/graphics/RectF;", "setCropWindowRect", "(Landroid/graphics/RectF;)V", "Lcom/canhub/cropper/CropImageView$Guidelines;", "guidelines", "getGuidelines", "()Lcom/canhub/cropper/CropImageView$Guidelines;", "Landroid/graphics/Rect;", "initialCropWindowRect", "getInitialCropWindowRect", "()Landroid/graphics/Rect;", "setInitialCropWindowRect", "(Landroid/graphics/Rect;)V", "initializedCropWindow", "", "isCropLabelEnabled", "isFixAspectRatio", "()Z", "isNonStraightAngleRotated", "mAspectRatioX", "mAspectRatioY", "mBackgroundPaint", "Landroid/graphics/Paint;", "mBorderCornerLength", "mBorderCornerOffset", "mBorderCornerPaint", "mBorderPaint", "mBoundsPoints", "", "mCalcBounds", "mCenterMoveEnabled", "mCircleCornerFillColor", "Ljava/lang/Integer;", "mCropCornerRadius", "mCropWindowChangeListener", "Lcom/canhub/cropper/CropOverlayView$CropWindowChangeListener;", "mCropWindowHandler", "Lcom/canhub/cropper/CropWindowHandler;", "mDrawRect", "mGuidelinePaint", "mInitialCropWindowPaddingRatio", "mInitialCropWindowRect", "mMoveHandler", "Lcom/canhub/cropper/CropWindowMoveHandler;", "mMultiTouchEnabled", "mOptions", "Lcom/canhub/cropper/CropImageOptions;", "mOriginalLayerType", "mPath", "Landroid/graphics/Path;", "mScaleDetector", "Landroid/view/ScaleGestureDetector;", "mSnapRadius", "mTargetAspectRatio", "mTouchRadius", "mViewHeight", "mViewWidth", "textLabelPaint", "calculateBounds", "callOnCropWindowChanged", "", "inProgress", "drawBackground", "canvas", "Landroid/graphics/Canvas;", "drawBorders", "drawCircleShape", "cornerOffset", "cornerExtension", "radius", "drawCornerBasedOnShape", "drawCornerShape", "drawCorners", "drawCropLabelText", "drawGuidelines", "drawLineShape", "fixCropWindowRectByRules", "fixCurrentCropWindowRect", "initCropWindow", "onActionDown", "x", "y", "onActionMove", "onActionUp", "onDraw", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "resetCropOverlayView", "resetCropWindowRect", "setBounds", "boundsPoints", "viewWidth", "viewHeight", "setCenterMoveEnabled", "centerMoveEnabled", "setCropCornerRadius", "cornerRadius", "setCropCornerShape", "cropCornerShape", "setCropLabelText", "textLabel", "setCropLabelTextColor", Common.TEXT_COLOR, "setCropLabelTextSize", "textSize", "setCropShape", "setCropWindowChangeListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setCropWindowLimits", ViewProps.MAX_WIDTH, ViewProps.MAX_HEIGHT, "scaleFactorWidth", "scaleFactorHeight", "setCropperTextLabelVisibility", "isEnabled", "setFixedAspectRatio", "fixAspectRatio", "setGuidelines", "setInitialAttributeValues", "options", "setMaxCropResultSize", "maxCropResultWidth", "maxCropResultHeight", "setMinCropResultSize", "minCropResultWidth", "minCropResultHeight", "setMultiTouchEnabled", "multiTouchEnabled", "setSnapRadius", "snapRadius", "Companion", "CropWindowChangeListener", "ScaleListener", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class CropOverlayView extends View {
    public static final Companion Companion = new Companion(null);
    private CropImageView.CropCornerShape cornerShape;
    private String cropLabelText;
    private int cropLabelTextColor;
    private float cropLabelTextSize;
    private CropImageView.CropShape cropShape;
    private CropImageView.Guidelines guidelines;
    private boolean initializedCropWindow;
    private boolean isCropLabelEnabled;
    private boolean isFixAspectRatio;
    private int mAspectRatioX;
    private int mAspectRatioY;
    private Paint mBackgroundPaint;
    private float mBorderCornerLength;
    private float mBorderCornerOffset;
    private Paint mBorderCornerPaint;
    private Paint mBorderPaint;
    private final float[] mBoundsPoints;
    private final RectF mCalcBounds;
    private boolean mCenterMoveEnabled;
    private Integer mCircleCornerFillColor;
    private float mCropCornerRadius;
    private CropWindowChangeListener mCropWindowChangeListener;
    private final CropWindowHandler mCropWindowHandler;
    private final RectF mDrawRect;
    private Paint mGuidelinePaint;
    private float mInitialCropWindowPaddingRatio;
    private final Rect mInitialCropWindowRect;
    private CropWindowMoveHandler mMoveHandler;
    private boolean mMultiTouchEnabled;
    private CropImageOptions mOptions;
    private Integer mOriginalLayerType;
    private final Path mPath;
    private ScaleGestureDetector mScaleDetector;
    private float mSnapRadius;
    private float mTargetAspectRatio;
    private float mTouchRadius;
    private int mViewHeight;
    private int mViewWidth;
    private Paint textLabelPaint;

    /* compiled from: CropOverlayView.kt */
    @Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m183d2 = {"Lcom/canhub/cropper/CropOverlayView$CropWindowChangeListener;", "", "onCropWindowChanged", "", "inProgress", "", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public interface CropWindowChangeListener {
        void onCropWindowChanged(boolean z);
    }

    /* compiled from: CropOverlayView.kt */
    @Metadata(m182k = 3, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] r0 = new int[CropImageView.CropShape.values().length];
            r0[CropImageView.CropShape.RECTANGLE.ordinal()] = 1;
            r0[CropImageView.CropShape.RECTANGLE_VERTICAL_ONLY.ordinal()] = 2;
            r0[CropImageView.CropShape.RECTANGLE_HORIZONTAL_ONLY.ordinal()] = 3;
            r0[CropImageView.CropShape.OVAL.ordinal()] = 4;
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[CropImageView.CropCornerShape.values().length];
            r02[CropImageView.CropCornerShape.OVAL.ordinal()] = 1;
            r02[CropImageView.CropCornerShape.RECTANGLE.ordinal()] = 2;
            $EnumSwitchMapping$1 = r02;
        }
    }

    public CropOverlayView(Context context) {
        this(context, null, 2, null);
    }

    public CropOverlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCenterMoveEnabled = true;
        this.mCropWindowHandler = new CropWindowHandler();
        this.mDrawRect = new RectF();
        this.mPath = new Path();
        this.mBoundsPoints = new float[8];
        this.mCalcBounds = new RectF();
        this.mTargetAspectRatio = this.mAspectRatioX / this.mAspectRatioY;
        this.cropLabelText = "";
        this.cropLabelTextSize = 20.0f;
        this.cropLabelTextColor = -1;
        this.mInitialCropWindowRect = new Rect();
    }

    public /* synthetic */ CropOverlayView(Context context, AttributeSet attributeSet, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (r3 & 2) != 0 ? null : attributeSet);
    }

    /* compiled from: CropOverlayView.kt */
    @Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u000e"}, m183d2 = {"Lcom/canhub/cropper/CropOverlayView$Companion;", "", "()V", "getNewPaint", "Landroid/graphics/Paint;", "color", "", "getNewPaintOrNull", "thickness", "", "getNewPaintWithFill", "getTextPaint", "options", "Lcom/canhub/cropper/CropImageOptions;", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Paint getTextPaint(CropImageOptions cropImageOptions) {
            Paint paint = new Paint();
            paint.setStrokeWidth(1.0f);
            paint.setTextSize(cropImageOptions.cropperLabelTextSize);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(cropImageOptions.cropperLabelTextColor);
            return paint;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Paint getNewPaint(int r2) {
            Paint paint = new Paint();
            paint.setColor(r2);
            return paint;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Paint getNewPaintOrNull(float f, int r3) {
            if (f > 0.0f) {
                Paint paint = new Paint();
                paint.setColor(r3);
                paint.setStrokeWidth(f);
                paint.setStyle(Paint.Style.STROKE);
                paint.setAntiAlias(true);
                return paint;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Paint getNewPaintWithFill(int r2) {
            Paint paint = new Paint();
            paint.setColor(r2);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            return paint;
        }
    }

    public final boolean isFixAspectRatio() {
        return this.isFixAspectRatio;
    }

    public final CropImageView.Guidelines getGuidelines() {
        return this.guidelines;
    }

    public final CropImageView.CropShape getCropShape() {
        return this.cropShape;
    }

    public final CropImageView.CropCornerShape getCornerShape() {
        return this.cornerShape;
    }

    public final void setCropWindowChangeListener(CropWindowChangeListener cropWindowChangeListener) {
        this.mCropWindowChangeListener = cropWindowChangeListener;
    }

    public final RectF getCropWindowRect() {
        return this.mCropWindowHandler.getRect();
    }

    public final void setCropWindowRect(RectF rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        this.mCropWindowHandler.setRect(rect);
    }

    public final void fixCurrentCropWindowRect() {
        RectF cropWindowRect = getCropWindowRect();
        fixCropWindowRectByRules(cropWindowRect);
        this.mCropWindowHandler.setRect(cropWindowRect);
    }

    public final void setBounds(float[] fArr, int r6, int r7) {
        if (fArr == null || !Arrays.equals(this.mBoundsPoints, fArr)) {
            if (fArr == null) {
                Arrays.fill(this.mBoundsPoints, 0.0f);
            } else {
                System.arraycopy(fArr, 0, this.mBoundsPoints, 0, fArr.length);
            }
            this.mViewWidth = r6;
            this.mViewHeight = r7;
            RectF rect = this.mCropWindowHandler.getRect();
            if (!(rect.width() == 0.0f)) {
                if (!(rect.height() == 0.0f)) {
                    return;
                }
            }
            initCropWindow();
        }
    }

    public final void resetCropOverlayView() {
        if (this.initializedCropWindow) {
            setCropWindowRect(BitmapUtils.INSTANCE.getEMPTY_RECT_F());
            initCropWindow();
            invalidate();
        }
    }

    public final void setCropShape(CropImageView.CropShape cropShape) {
        Intrinsics.checkNotNullParameter(cropShape, "cropShape");
        if (this.cropShape != cropShape) {
            this.cropShape = cropShape;
            if (!CommonVersionCheck.INSTANCE.isAtLeastJ18()) {
                if (this.cropShape == CropImageView.CropShape.OVAL) {
                    Integer valueOf = Integer.valueOf(getLayerType());
                    this.mOriginalLayerType = valueOf;
                    if (valueOf != null && valueOf.intValue() == 1) {
                        this.mOriginalLayerType = null;
                    } else {
                        setLayerType(1, null);
                    }
                } else {
                    Integer num = this.mOriginalLayerType;
                    if (num != null) {
                        Intrinsics.checkNotNull(num);
                        setLayerType(num.intValue(), null);
                        this.mOriginalLayerType = null;
                    }
                }
            }
            invalidate();
        }
    }

    public final void setCropCornerShape(CropImageView.CropCornerShape cropCornerShape) {
        Intrinsics.checkNotNullParameter(cropCornerShape, "cropCornerShape");
        if (this.cornerShape != cropCornerShape) {
            this.cornerShape = cropCornerShape;
            invalidate();
        }
    }

    public final void setCropperTextLabelVisibility(boolean z) {
        this.isCropLabelEnabled = z;
        invalidate();
    }

    public final void setCropLabelText(String str) {
        if (str == null) {
            return;
        }
        this.cropLabelText = str;
    }

    public final void setCropLabelTextSize(float f) {
        this.cropLabelTextSize = f;
        invalidate();
    }

    public final void setCropLabelTextColor(int r1) {
        this.cropLabelTextColor = r1;
        invalidate();
    }

    public final void setGuidelines(CropImageView.Guidelines guidelines) {
        Intrinsics.checkNotNullParameter(guidelines, "guidelines");
        if (this.guidelines != guidelines) {
            this.guidelines = guidelines;
            if (this.initializedCropWindow) {
                invalidate();
            }
        }
    }

    public final void setFixedAspectRatio(boolean z) {
        if (this.isFixAspectRatio != z) {
            this.isFixAspectRatio = z;
            if (this.initializedCropWindow) {
                initCropWindow();
                invalidate();
            }
        }
    }

    public final int getAspectRatioX() {
        return this.mAspectRatioX;
    }

    public final void setAspectRatioX(int r2) {
        if (!(r2 > 0)) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.".toString());
        }
        if (this.mAspectRatioX != r2) {
            this.mAspectRatioX = r2;
            this.mTargetAspectRatio = r2 / this.mAspectRatioY;
            if (this.initializedCropWindow) {
                initCropWindow();
                invalidate();
            }
        }
    }

    public final int getAspectRatioY() {
        return this.mAspectRatioY;
    }

    public final void setAspectRatioY(int r2) {
        if (!(r2 > 0)) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.".toString());
        }
        if (this.mAspectRatioY != r2) {
            this.mAspectRatioY = r2;
            this.mTargetAspectRatio = this.mAspectRatioX / r2;
            if (this.initializedCropWindow) {
                initCropWindow();
                invalidate();
            }
        }
    }

    public final void setSnapRadius(float f) {
        this.mSnapRadius = f;
    }

    public final void setCropCornerRadius(float f) {
        this.mCropCornerRadius = f;
    }

    public final boolean setMultiTouchEnabled(boolean z) {
        if (this.mMultiTouchEnabled != z) {
            this.mMultiTouchEnabled = z;
            if (z && this.mScaleDetector == null) {
                this.mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener(this));
                return true;
            }
            return true;
        }
        return false;
    }

    public final boolean setCenterMoveEnabled(boolean z) {
        if (this.mCenterMoveEnabled != z) {
            this.mCenterMoveEnabled = z;
            return true;
        }
        return false;
    }

    public final void setMinCropResultSize(int r2, int r3) {
        this.mCropWindowHandler.setMinCropResultSize(r2, r3);
    }

    public final void setMaxCropResultSize(int r2, int r3) {
        this.mCropWindowHandler.setMaxCropResultSize(r2, r3);
    }

    public final void setCropWindowLimits(float f, float f2, float f3, float f4) {
        this.mCropWindowHandler.setCropWindowLimits(f, f2, f3, f4);
    }

    public final Rect getInitialCropWindowRect() {
        return this.mInitialCropWindowRect;
    }

    public final void setInitialCropWindowRect(Rect rect) {
        Rect rect2 = this.mInitialCropWindowRect;
        if (rect == null) {
            rect = BitmapUtils.INSTANCE.getEMPTY_RECT();
        }
        rect2.set(rect);
        if (this.initializedCropWindow) {
            initCropWindow();
            invalidate();
            callOnCropWindowChanged(false);
        }
    }

    public final void resetCropWindowRect() {
        if (this.initializedCropWindow) {
            initCropWindow();
            invalidate();
            callOnCropWindowChanged(false);
        }
    }

    public final void setInitialAttributeValues(CropImageOptions options) {
        Intrinsics.checkNotNullParameter(options, "options");
        this.mOptions = options;
        this.mCropWindowHandler.setInitialAttributeValues(options);
        setCropLabelTextColor(options.cropperLabelTextColor);
        setCropLabelTextSize(options.cropperLabelTextSize);
        setCropLabelText(options.cropperLabelText);
        setCropperTextLabelVisibility(options.showCropLabel);
        setCropCornerRadius(options.cropCornerRadius);
        setCropCornerShape(options.cornerShape);
        setCropShape(options.cropShape);
        setSnapRadius(options.snapRadius);
        setGuidelines(options.guidelines);
        setFixedAspectRatio(options.fixAspectRatio);
        setAspectRatioX(options.aspectRatioX);
        setAspectRatioY(options.aspectRatioY);
        setMultiTouchEnabled(options.multiTouchEnabled);
        setCenterMoveEnabled(options.centerMoveEnabled);
        this.mTouchRadius = options.touchRadius;
        this.mInitialCropWindowPaddingRatio = options.initialCropWindowPaddingRatio;
        Companion companion = Companion;
        this.mBorderPaint = companion.getNewPaintOrNull(options.borderLineThickness, options.borderLineColor);
        this.mBorderCornerOffset = options.borderCornerOffset;
        this.mBorderCornerLength = options.borderCornerLength;
        this.mCircleCornerFillColor = Integer.valueOf(options.circleCornerFillColorHexValue);
        this.mBorderCornerPaint = companion.getNewPaintOrNull(options.borderCornerThickness, options.borderCornerColor);
        this.mGuidelinePaint = companion.getNewPaintOrNull(options.guidelinesThickness, options.guidelinesColor);
        this.mBackgroundPaint = companion.getNewPaint(options.backgroundColor);
        this.textLabelPaint = companion.getTextPaint(options);
    }

    private final void initCropWindow() {
        float max = Math.max(BitmapUtils.INSTANCE.getRectLeft(this.mBoundsPoints), 0.0f);
        float max2 = Math.max(BitmapUtils.INSTANCE.getRectTop(this.mBoundsPoints), 0.0f);
        float min = Math.min(BitmapUtils.INSTANCE.getRectRight(this.mBoundsPoints), getWidth());
        float min2 = Math.min(BitmapUtils.INSTANCE.getRectBottom(this.mBoundsPoints), getHeight());
        if (min <= max || min2 <= max2) {
            return;
        }
        RectF rectF = new RectF();
        this.initializedCropWindow = true;
        float f = this.mInitialCropWindowPaddingRatio;
        float f2 = min - max;
        float f3 = f * f2;
        float f4 = min2 - max2;
        float f5 = f * f4;
        if (this.mInitialCropWindowRect.width() > 0 && this.mInitialCropWindowRect.height() > 0) {
            rectF.left = (this.mInitialCropWindowRect.left / this.mCropWindowHandler.getScaleFactorWidth()) + max;
            rectF.top = (this.mInitialCropWindowRect.top / this.mCropWindowHandler.getScaleFactorHeight()) + max2;
            rectF.right = rectF.left + (this.mInitialCropWindowRect.width() / this.mCropWindowHandler.getScaleFactorWidth());
            rectF.bottom = rectF.top + (this.mInitialCropWindowRect.height() / this.mCropWindowHandler.getScaleFactorHeight());
            rectF.left = Math.max(max, rectF.left);
            rectF.top = Math.max(max2, rectF.top);
            rectF.right = Math.min(min, rectF.right);
            rectF.bottom = Math.min(min2, rectF.bottom);
        } else if (this.isFixAspectRatio && min > max && min2 > max2) {
            if (f2 / f4 > this.mTargetAspectRatio) {
                rectF.top = max2 + f5;
                rectF.bottom = min2 - f5;
                float width = getWidth() / 2.0f;
                this.mTargetAspectRatio = this.mAspectRatioX / this.mAspectRatioY;
                float max3 = Math.max(this.mCropWindowHandler.getMinCropWidth(), rectF.height() * this.mTargetAspectRatio) / 2.0f;
                rectF.left = width - max3;
                rectF.right = width + max3;
            } else {
                rectF.left = max + f3;
                rectF.right = min - f3;
                float height = getHeight() / 2.0f;
                float max4 = Math.max(this.mCropWindowHandler.getMinCropHeight(), rectF.width() / this.mTargetAspectRatio) / 2.0f;
                rectF.top = height - max4;
                rectF.bottom = height + max4;
            }
        } else {
            rectF.left = max + f3;
            rectF.top = max2 + f5;
            rectF.right = min - f3;
            rectF.bottom = min2 - f5;
        }
        fixCropWindowRectByRules(rectF);
        this.mCropWindowHandler.setRect(rectF);
    }

    private final void fixCropWindowRectByRules(RectF rectF) {
        if (rectF.width() < this.mCropWindowHandler.getMinCropWidth()) {
            float minCropWidth = (this.mCropWindowHandler.getMinCropWidth() - rectF.width()) / 2;
            rectF.left -= minCropWidth;
            rectF.right += minCropWidth;
        }
        if (rectF.height() < this.mCropWindowHandler.getMinCropHeight()) {
            float minCropHeight = (this.mCropWindowHandler.getMinCropHeight() - rectF.height()) / 2;
            rectF.top -= minCropHeight;
            rectF.bottom += minCropHeight;
        }
        if (rectF.width() > this.mCropWindowHandler.getMaxCropWidth()) {
            float width = (rectF.width() - this.mCropWindowHandler.getMaxCropWidth()) / 2;
            rectF.left += width;
            rectF.right -= width;
        }
        if (rectF.height() > this.mCropWindowHandler.getMaxCropHeight()) {
            float height = (rectF.height() - this.mCropWindowHandler.getMaxCropHeight()) / 2;
            rectF.top += height;
            rectF.bottom -= height;
        }
        calculateBounds(rectF);
        if (this.mCalcBounds.width() > 0.0f && this.mCalcBounds.height() > 0.0f) {
            float max = Math.max(this.mCalcBounds.left, 0.0f);
            float max2 = Math.max(this.mCalcBounds.top, 0.0f);
            float min = Math.min(this.mCalcBounds.right, getWidth());
            float min2 = Math.min(this.mCalcBounds.bottom, getHeight());
            if (rectF.left < max) {
                rectF.left = max;
            }
            if (rectF.top < max2) {
                rectF.top = max2;
            }
            if (rectF.right > min) {
                rectF.right = min;
            }
            if (rectF.bottom > min2) {
                rectF.bottom = min2;
            }
        }
        if (!this.isFixAspectRatio || Math.abs(rectF.width() - (rectF.height() * this.mTargetAspectRatio)) <= 0.1d) {
            return;
        }
        if (rectF.width() > rectF.height() * this.mTargetAspectRatio) {
            float abs = Math.abs((rectF.height() * this.mTargetAspectRatio) - rectF.width()) / 2;
            rectF.left += abs;
            rectF.right -= abs;
            return;
        }
        float abs2 = Math.abs((rectF.width() / this.mTargetAspectRatio) - rectF.height()) / 2;
        rectF.top += abs2;
        rectF.bottom -= abs2;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        drawBackground(canvas);
        if (this.mCropWindowHandler.showGuidelines()) {
            if (this.guidelines == CropImageView.Guidelines.ON) {
                drawGuidelines(canvas);
            } else if (this.guidelines == CropImageView.Guidelines.ON_TOUCH && this.mMoveHandler != null) {
                drawGuidelines(canvas);
            }
        }
        Companion companion = Companion;
        CropImageOptions cropImageOptions = this.mOptions;
        float f = cropImageOptions == null ? 0.0f : cropImageOptions.borderCornerThickness;
        CropImageOptions cropImageOptions2 = this.mOptions;
        this.mBorderCornerPaint = companion.getNewPaintOrNull(f, cropImageOptions2 == null ? -1 : cropImageOptions2.borderCornerColor);
        drawCropLabelText(canvas);
        drawBorders(canvas);
        drawCorners(canvas);
    }

    private final void drawCropLabelText(Canvas canvas) {
        if (this.isCropLabelEnabled) {
            RectF rect = this.mCropWindowHandler.getRect();
            float f = (rect.left + rect.right) / 2;
            float f2 = rect.top - 50;
            Paint paint = this.textLabelPaint;
            if (paint != null) {
                paint.setTextSize(this.cropLabelTextSize);
                paint.setColor(this.cropLabelTextColor);
            }
            String str = this.cropLabelText;
            Paint paint2 = this.textLabelPaint;
            Intrinsics.checkNotNull(paint2);
            canvas.drawText(str, f, f2, paint2);
            canvas.save();
        }
    }

    private final void drawBackground(Canvas canvas) {
        RectF rect = this.mCropWindowHandler.getRect();
        float max = Math.max(BitmapUtils.INSTANCE.getRectLeft(this.mBoundsPoints), 0.0f);
        float max2 = Math.max(BitmapUtils.INSTANCE.getRectTop(this.mBoundsPoints), 0.0f);
        float min = Math.min(BitmapUtils.INSTANCE.getRectRight(this.mBoundsPoints), getWidth());
        float min2 = Math.min(BitmapUtils.INSTANCE.getRectBottom(this.mBoundsPoints), getHeight());
        CropImageView.CropShape cropShape = this.cropShape;
        int r3 = cropShape == null ? -1 : WhenMappings.$EnumSwitchMapping$0[cropShape.ordinal()];
        if (r3 != 1 && r3 != 2 && r3 != 3) {
            if (r3 == 4) {
                this.mPath.reset();
                if (CommonVersionCheck.INSTANCE.isAtLeastJ18()) {
                    this.mDrawRect.set(rect.left, rect.top, rect.right, rect.bottom);
                } else {
                    float f = 2;
                    this.mDrawRect.set(rect.left + f, rect.top + f, rect.right - f, rect.bottom - f);
                }
                this.mPath.addOval(this.mDrawRect, Path.Direction.CW);
                canvas.save();
                if (CommonVersionCheck.INSTANCE.isAtLeastO26()) {
                    canvas.clipOutPath(this.mPath);
                } else {
                    canvas.clipPath(this.mPath, Region.Op.XOR);
                }
                Paint paint = this.mBackgroundPaint;
                Intrinsics.checkNotNull(paint);
                canvas.drawRect(max, max2, min, min2, paint);
                canvas.restore();
                return;
            }
            throw new IllegalStateException("Unrecognized crop shape");
        } else if (!isNonStraightAngleRotated() || !CommonVersionCheck.INSTANCE.isAtLeastJ18()) {
            float f2 = rect.top;
            Paint paint2 = this.mBackgroundPaint;
            Intrinsics.checkNotNull(paint2);
            canvas.drawRect(max, max2, min, f2, paint2);
            float f3 = rect.bottom;
            Paint paint3 = this.mBackgroundPaint;
            Intrinsics.checkNotNull(paint3);
            canvas.drawRect(max, f3, min, min2, paint3);
            float f4 = rect.top;
            float f5 = rect.left;
            float f6 = rect.bottom;
            Paint paint4 = this.mBackgroundPaint;
            Intrinsics.checkNotNull(paint4);
            canvas.drawRect(max, f4, f5, f6, paint4);
            float f7 = rect.right;
            float f8 = rect.top;
            float f9 = rect.bottom;
            Paint paint5 = this.mBackgroundPaint;
            Intrinsics.checkNotNull(paint5);
            canvas.drawRect(f7, f8, min, f9, paint5);
        } else {
            this.mPath.reset();
            Path path = this.mPath;
            float[] fArr = this.mBoundsPoints;
            path.moveTo(fArr[0], fArr[1]);
            Path path2 = this.mPath;
            float[] fArr2 = this.mBoundsPoints;
            path2.lineTo(fArr2[2], fArr2[3]);
            Path path3 = this.mPath;
            float[] fArr3 = this.mBoundsPoints;
            path3.lineTo(fArr3[4], fArr3[5]);
            Path path4 = this.mPath;
            float[] fArr4 = this.mBoundsPoints;
            path4.lineTo(fArr4[6], fArr4[7]);
            this.mPath.close();
            canvas.save();
            if (CommonVersionCheck.INSTANCE.isAtLeastO26()) {
                canvas.clipOutPath(this.mPath);
            } else {
                canvas.clipPath(this.mPath, Region.Op.INTERSECT);
            }
            canvas.clipRect(rect, Region.Op.XOR);
            Paint paint6 = this.mBackgroundPaint;
            Intrinsics.checkNotNull(paint6);
            canvas.drawRect(max, max2, min, min2, paint6);
            canvas.restore();
        }
    }

    private final void drawGuidelines(Canvas canvas) {
        float f;
        if (this.mGuidelinePaint != null) {
            Paint paint = this.mBorderPaint;
            if (paint != null) {
                Intrinsics.checkNotNull(paint);
                f = paint.getStrokeWidth();
            } else {
                f = 0.0f;
            }
            RectF rect = this.mCropWindowHandler.getRect();
            rect.inset(f, f);
            float f2 = 3;
            float width = rect.width() / f2;
            float height = rect.height() / f2;
            CropImageView.CropShape cropShape = this.cropShape;
            int r5 = cropShape == null ? -1 : WhenMappings.$EnumSwitchMapping$0[cropShape.ordinal()];
            if (r5 == 1 || r5 == 2 || r5 == 3) {
                float f3 = rect.left + width;
                float f4 = rect.right - width;
                float f5 = rect.top;
                float f6 = rect.bottom;
                Paint paint2 = this.mGuidelinePaint;
                Intrinsics.checkNotNull(paint2);
                canvas.drawLine(f3, f5, f3, f6, paint2);
                float f7 = rect.top;
                float f8 = rect.bottom;
                Paint paint3 = this.mGuidelinePaint;
                Intrinsics.checkNotNull(paint3);
                canvas.drawLine(f4, f7, f4, f8, paint3);
                float f9 = rect.top + height;
                float f10 = rect.bottom - height;
                float f11 = rect.left;
                float f12 = rect.right;
                Paint paint4 = this.mGuidelinePaint;
                Intrinsics.checkNotNull(paint4);
                canvas.drawLine(f11, f9, f12, f9, paint4);
                float f13 = rect.left;
                float f14 = rect.right;
                Paint paint5 = this.mGuidelinePaint;
                Intrinsics.checkNotNull(paint5);
                canvas.drawLine(f13, f10, f14, f10, paint5);
            } else if (r5 == 4) {
                float f15 = 2;
                float width2 = (rect.width() / f15) - f;
                float height2 = (rect.height() / f15) - f;
                float f16 = rect.left + width;
                float f17 = rect.right - width;
                float sin = (float) (height2 * Math.sin(Math.acos((width2 - width) / width2)));
                Paint paint6 = this.mGuidelinePaint;
                Intrinsics.checkNotNull(paint6);
                canvas.drawLine(f16, (rect.top + height2) - sin, f16, (rect.bottom - height2) + sin, paint6);
                float f18 = (rect.top + height2) - sin;
                float f19 = (rect.bottom - height2) + sin;
                Paint paint7 = this.mGuidelinePaint;
                Intrinsics.checkNotNull(paint7);
                canvas.drawLine(f17, f18, f17, f19, paint7);
                float f20 = rect.top + height;
                float f21 = rect.bottom - height;
                float cos = (float) (width2 * Math.cos(Math.asin((height2 - height) / height2)));
                Paint paint8 = this.mGuidelinePaint;
                Intrinsics.checkNotNull(paint8);
                canvas.drawLine((rect.left + width2) - cos, f20, (rect.right - width2) + cos, f20, paint8);
                float f22 = (rect.left + width2) - cos;
                float f23 = (rect.right - width2) + cos;
                Paint paint9 = this.mGuidelinePaint;
                Intrinsics.checkNotNull(paint9);
                canvas.drawLine(f22, f21, f23, f21, paint9);
            } else {
                throw new IllegalStateException("Unrecognized crop shape");
            }
        }
    }

    private final void drawBorders(Canvas canvas) {
        Paint paint = this.mBorderPaint;
        if (paint != null) {
            Intrinsics.checkNotNull(paint);
            float strokeWidth = paint.getStrokeWidth();
            RectF rect = this.mCropWindowHandler.getRect();
            float f = strokeWidth / 2;
            rect.inset(f, f);
            CropImageView.CropShape cropShape = this.cropShape;
            int r0 = cropShape == null ? -1 : WhenMappings.$EnumSwitchMapping$0[cropShape.ordinal()];
            if (r0 == 1 || r0 == 2 || r0 == 3) {
                Paint paint2 = this.mBorderPaint;
                Intrinsics.checkNotNull(paint2);
                canvas.drawRect(rect, paint2);
            } else if (r0 == 4) {
                Paint paint3 = this.mBorderPaint;
                Intrinsics.checkNotNull(paint3);
                canvas.drawOval(rect, paint3);
            } else {
                throw new IllegalStateException("Unrecognized crop shape");
            }
        }
    }

    private final void drawCorners(Canvas canvas) {
        float f;
        if (this.mBorderCornerPaint != null) {
            Paint paint = this.mBorderPaint;
            if (paint != null) {
                Intrinsics.checkNotNull(paint);
                f = paint.getStrokeWidth();
            } else {
                f = 0.0f;
            }
            Paint paint2 = this.mBorderCornerPaint;
            Intrinsics.checkNotNull(paint2);
            float strokeWidth = paint2.getStrokeWidth();
            float f2 = 2;
            float f3 = (strokeWidth - f) / f2;
            float f4 = strokeWidth / f2;
            float f5 = f4 + f3;
            CropImageView.CropShape cropShape = this.cropShape;
            int r4 = cropShape == null ? -1 : WhenMappings.$EnumSwitchMapping$0[cropShape.ordinal()];
            if (r4 == 1 || r4 == 2 || r4 == 3) {
                f4 += this.mBorderCornerOffset;
            } else if (r4 != 4) {
                throw new IllegalStateException("Unrecognized crop shape");
            }
            RectF rect = this.mCropWindowHandler.getRect();
            rect.inset(f4, f4);
            drawCornerBasedOnShape(canvas, rect, f3, f5);
            if (this.cornerShape == CropImageView.CropCornerShape.OVAL) {
                Integer num = this.mCircleCornerFillColor;
                this.mBorderCornerPaint = num == null ? null : Companion.getNewPaintWithFill(num.intValue());
                drawCornerBasedOnShape(canvas, rect, f3, f5);
            }
        }
    }

    private final void drawCornerBasedOnShape(Canvas canvas, RectF rectF, float f, float f2) {
        CropImageView.CropShape cropShape = this.cropShape;
        int r0 = cropShape == null ? -1 : WhenMappings.$EnumSwitchMapping$0[cropShape.ordinal()];
        if (r0 == 1) {
            drawCornerShape(canvas, rectF, f, f2, this.mCropCornerRadius);
        } else if (r0 == 2) {
            Paint paint = this.mBorderCornerPaint;
            Intrinsics.checkNotNull(paint);
            canvas.drawLine(rectF.centerX() - this.mBorderCornerLength, rectF.top - f, rectF.centerX() + this.mBorderCornerLength, rectF.top - f, paint);
            Paint paint2 = this.mBorderCornerPaint;
            Intrinsics.checkNotNull(paint2);
            canvas.drawLine(rectF.centerX() - this.mBorderCornerLength, rectF.bottom + f, rectF.centerX() + this.mBorderCornerLength, rectF.bottom + f, paint2);
        } else if (r0 != 3) {
            if (r0 == 4) {
                drawLineShape(canvas, rectF, f, f2);
                return;
            }
            throw new IllegalStateException("Unrecognized crop shape");
        } else {
            Paint paint3 = this.mBorderCornerPaint;
            Intrinsics.checkNotNull(paint3);
            canvas.drawLine(rectF.left - f, rectF.centerY() - this.mBorderCornerLength, rectF.left - f, rectF.centerY() + this.mBorderCornerLength, paint3);
            Paint paint4 = this.mBorderCornerPaint;
            Intrinsics.checkNotNull(paint4);
            canvas.drawLine(rectF.right + f, rectF.centerY() - this.mBorderCornerLength, rectF.right + f, rectF.centerY() + this.mBorderCornerLength, paint4);
        }
    }

    private final void drawLineShape(Canvas canvas, RectF rectF, float f, float f2) {
        Paint paint = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint);
        canvas.drawLine(rectF.left - f, rectF.top - f2, rectF.left - f, rectF.top + this.mBorderCornerLength, paint);
        Paint paint2 = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint2);
        canvas.drawLine(rectF.left - f2, rectF.top - f, rectF.left + this.mBorderCornerLength, rectF.top - f, paint2);
        Paint paint3 = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint3);
        canvas.drawLine(rectF.right + f, rectF.top - f2, rectF.right + f, rectF.top + this.mBorderCornerLength, paint3);
        Paint paint4 = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint4);
        canvas.drawLine(rectF.right + f2, rectF.top - f, rectF.right - this.mBorderCornerLength, rectF.top - f, paint4);
        Paint paint5 = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint5);
        canvas.drawLine(rectF.left - f, rectF.bottom + f2, rectF.left - f, rectF.bottom - this.mBorderCornerLength, paint5);
        Paint paint6 = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint6);
        canvas.drawLine(rectF.left - f2, rectF.bottom + f, rectF.left + this.mBorderCornerLength, rectF.bottom + f, paint6);
        Paint paint7 = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint7);
        canvas.drawLine(rectF.right + f, rectF.bottom + f2, rectF.right + f, rectF.bottom - this.mBorderCornerLength, paint7);
        Paint paint8 = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint8);
        canvas.drawLine(rectF.right + f2, rectF.bottom + f, rectF.right - this.mBorderCornerLength, rectF.bottom + f, paint8);
    }

    private final void drawCornerShape(Canvas canvas, RectF rectF, float f, float f2, float f3) {
        CropImageView.CropCornerShape cropCornerShape = this.cornerShape;
        int r0 = cropCornerShape == null ? -1 : WhenMappings.$EnumSwitchMapping$1[cropCornerShape.ordinal()];
        if (r0 == 1) {
            drawCircleShape(canvas, rectF, f, f2, f3);
        } else if (r0 != 2) {
        } else {
            drawLineShape(canvas, rectF, f, f2);
        }
    }

    private final void drawCircleShape(Canvas canvas, RectF rectF, float f, float f2, float f3) {
        Paint paint = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint);
        canvas.drawCircle(rectF.left - f2, rectF.top - f2, f3, paint);
        Paint paint2 = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint2);
        canvas.drawCircle(rectF.right + f2, rectF.top - f2, f3, paint2);
        Paint paint3 = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint3);
        canvas.drawCircle(rectF.left - f2, rectF.bottom + f2, f3, paint3);
        float f4 = rectF.right + f2;
        float f5 = rectF.bottom + f2;
        Paint paint4 = this.mBorderCornerPaint;
        Intrinsics.checkNotNull(paint4);
        canvas.drawCircle(f4, f5, f3, paint4);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        ScaleGestureDetector scaleGestureDetector;
        Intrinsics.checkNotNullParameter(event, "event");
        if (isEnabled()) {
            if (this.mMultiTouchEnabled && (scaleGestureDetector = this.mScaleDetector) != null) {
                scaleGestureDetector.onTouchEvent(event);
            }
            int action = event.getAction();
            if (action == 0) {
                onActionDown(event.getX(), event.getY());
            } else {
                if (action != 1) {
                    if (action == 2) {
                        onActionMove(event.getX(), event.getY());
                        getParent().requestDisallowInterceptTouchEvent(true);
                    } else if (action != 3) {
                        return false;
                    }
                }
                getParent().requestDisallowInterceptTouchEvent(false);
                onActionUp();
            }
            return true;
        }
        return false;
    }

    private final void onActionDown(float f, float f2) {
        CropWindowHandler cropWindowHandler = this.mCropWindowHandler;
        float f3 = this.mTouchRadius;
        CropImageView.CropShape cropShape = this.cropShape;
        Intrinsics.checkNotNull(cropShape);
        CropWindowMoveHandler moveHandler = cropWindowHandler.getMoveHandler(f, f2, f3, cropShape, this.mCenterMoveEnabled);
        this.mMoveHandler = moveHandler;
        if (moveHandler != null) {
            invalidate();
        }
    }

    private final void onActionUp() {
        if (this.mMoveHandler != null) {
            this.mMoveHandler = null;
            callOnCropWindowChanged(false);
            invalidate();
        }
    }

    private final void onActionMove(float f, float f2) {
        if (this.mMoveHandler != null) {
            float f3 = this.mSnapRadius;
            RectF rect = this.mCropWindowHandler.getRect();
            float f4 = calculateBounds(rect) ? 0.0f : f3;
            CropWindowMoveHandler cropWindowMoveHandler = this.mMoveHandler;
            Intrinsics.checkNotNull(cropWindowMoveHandler);
            cropWindowMoveHandler.move(rect, f, f2, this.mCalcBounds, this.mViewWidth, this.mViewHeight, f4, this.isFixAspectRatio, this.mTargetAspectRatio);
            this.mCropWindowHandler.setRect(rect);
            callOnCropWindowChanged(true);
            invalidate();
        }
    }

    private final boolean calculateBounds(RectF rectF) {
        float rectLeft = BitmapUtils.INSTANCE.getRectLeft(this.mBoundsPoints);
        float rectTop = BitmapUtils.INSTANCE.getRectTop(this.mBoundsPoints);
        float rectRight = BitmapUtils.INSTANCE.getRectRight(this.mBoundsPoints);
        float rectBottom = BitmapUtils.INSTANCE.getRectBottom(this.mBoundsPoints);
        if (!isNonStraightAngleRotated()) {
            this.mCalcBounds.set(rectLeft, rectTop, rectRight, rectBottom);
            return false;
        }
        float[] fArr = this.mBoundsPoints;
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[4];
        float f4 = fArr[5];
        float f5 = fArr[6];
        float f6 = fArr[7];
        if (fArr[7] < fArr[1]) {
            if (fArr[1] < fArr[3]) {
                f = fArr[6];
                f2 = fArr[7];
                f3 = fArr[2];
                f4 = fArr[3];
                f5 = fArr[4];
                f6 = fArr[5];
            } else {
                f = fArr[4];
                f2 = fArr[5];
                f3 = fArr[0];
                f4 = fArr[1];
                f5 = fArr[2];
                f6 = fArr[3];
            }
        } else if (fArr[1] > fArr[3]) {
            f = fArr[2];
            f2 = fArr[3];
            f3 = fArr[6];
            f4 = fArr[7];
            f5 = fArr[0];
            f6 = fArr[1];
        }
        float f7 = (f6 - f2) / (f5 - f);
        float f8 = (-1.0f) / f7;
        float f9 = f2 - (f7 * f);
        float f10 = f2 - (f * f8);
        float f11 = f4 - (f7 * f3);
        float f12 = f4 - (f3 * f8);
        float centerY = (rectF.centerY() - rectF.top) / (rectF.centerX() - rectF.left);
        float f13 = -centerY;
        float f14 = rectF.top - (rectF.left * centerY);
        float f15 = rectF.top - (rectF.right * f13);
        float f16 = f7 - centerY;
        float f17 = (f14 - f9) / f16;
        if (f17 >= rectF.right) {
            f17 = rectLeft;
        }
        float max = Math.max(rectLeft, f17);
        float f18 = (f14 - f10) / (f8 - centerY);
        if (f18 >= rectF.right) {
            f18 = max;
        }
        float max2 = Math.max(max, f18);
        float f19 = f8 - f13;
        float f20 = (f15 - f12) / f19;
        if (f20 >= rectF.right) {
            f20 = max2;
        }
        float max3 = Math.max(max2, f20);
        float f21 = (f15 - f10) / f19;
        if (f21 <= rectF.left) {
            f21 = rectRight;
        }
        float min = Math.min(rectRight, f21);
        float f22 = (f15 - f11) / (f7 - f13);
        if (f22 <= rectF.left) {
            f22 = min;
        }
        float min2 = Math.min(min, f22);
        float f23 = (f14 - f11) / f16;
        if (f23 <= rectF.left) {
            f23 = min2;
        }
        float min3 = Math.min(min2, f23);
        float max4 = Math.max(rectTop, Math.max((f7 * max3) + f9, (f8 * min3) + f10));
        float min4 = Math.min(rectBottom, Math.min((f8 * max3) + f12, (f7 * min3) + f11));
        this.mCalcBounds.left = max3;
        this.mCalcBounds.top = max4;
        this.mCalcBounds.right = min3;
        this.mCalcBounds.bottom = min4;
        return true;
    }

    private final boolean isNonStraightAngleRotated() {
        float[] fArr = this.mBoundsPoints;
        if (fArr[0] == fArr[6]) {
            return false;
        }
        return !((fArr[1] > fArr[7] ? 1 : (fArr[1] == fArr[7] ? 0 : -1)) == 0);
    }

    private final void callOnCropWindowChanged(boolean z) {
        try {
            CropWindowChangeListener cropWindowChangeListener = this.mCropWindowChangeListener;
            if (cropWindowChangeListener == null) {
                return;
            }
            cropWindowChangeListener.onCropWindowChanged(z);
        } catch (Exception e) {
            Log.e("AIC", "Exception in crop window changed", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CropOverlayView.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0017¨\u0006\u0007"}, m183d2 = {"Lcom/canhub/cropper/CropOverlayView$ScaleListener;", "Landroid/view/ScaleGestureDetector$SimpleOnScaleGestureListener;", "(Lcom/canhub/cropper/CropOverlayView;)V", "onScale", "", "detector", "Landroid/view/ScaleGestureDetector;", "cropper_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
    /* loaded from: classes.dex */
    public final class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        final /* synthetic */ CropOverlayView this$0;

        public ScaleListener(CropOverlayView this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector detector) {
            Intrinsics.checkNotNullParameter(detector, "detector");
            RectF rect = this.this$0.mCropWindowHandler.getRect();
            float focusX = detector.getFocusX();
            float focusY = detector.getFocusY();
            float f = 2;
            float currentSpanY = detector.getCurrentSpanY() / f;
            float currentSpanX = detector.getCurrentSpanX() / f;
            float f2 = focusY - currentSpanY;
            float f3 = focusX - currentSpanX;
            float f4 = focusX + currentSpanX;
            float f5 = focusY + currentSpanY;
            if (f3 >= f4 || f2 > f5 || f3 < 0.0f || f4 > this.this$0.mCropWindowHandler.getMaxCropWidth() || f2 < 0.0f || f5 > this.this$0.mCropWindowHandler.getMaxCropHeight()) {
                return true;
            }
            rect.set(f3, f2, f4, f5);
            this.this$0.mCropWindowHandler.setRect(rect);
            this.this$0.invalidate();
            return true;
        }
    }
}
