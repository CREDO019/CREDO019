package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.OnLayoutEvent;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ReactViewGroup;
import com.horcrux.svg.SVGLength;
import java.util.ArrayList;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public abstract class VirtualView extends ReactViewGroup {
    private static final int CLIP_RULE_EVENODD = 0;
    static final int CLIP_RULE_NONZERO = 1;
    private static final double M_SQRT1_2l = 0.7071067811865476d;
    private static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private double canvasDiagonal;
    private float canvasHeight;
    private float canvasWidth;
    ArrayList<PathElement> elements;
    private double fontSize;
    private GlyphContext glyphContext;
    RectF mBox;
    Matrix mCTM;
    boolean mCTMInvertible;
    private Path mCachedClipPath;
    private RectF mClientRect;
    RectF mClipBounds;
    @Nullable
    private String mClipPath;
    Region mClipRegion;
    Path mClipRegionPath;
    int mClipRule;
    final ReactContext mContext;
    String mDisplay;
    RectF mFillBounds;
    Path mFillPath;
    Matrix mInvCTM;
    Matrix mInvMatrix;
    final Matrix mInvTransform;
    boolean mInvertible;
    RectF mMarkerBounds;
    @Nullable
    String mMarkerEnd;
    @Nullable
    String mMarkerMid;
    Path mMarkerPath;
    Region mMarkerRegion;
    @Nullable
    String mMarkerStart;
    @Nullable
    String mMask;
    Matrix mMatrix;
    String mName;
    float mOpacity;
    Path mPath;
    PointerEvents mPointerEvents;
    Region mRegion;
    private boolean mResponsible;
    final float mScale;
    RectF mStrokeBounds;
    Path mStrokePath;
    Region mStrokeRegion;
    private GroupView mTextRoot;
    Matrix mTransform;
    boolean mTransformInvertible;
    private SvgView svgView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void draw(Canvas canvas, Paint paint, float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Path getPath(Canvas canvas, Paint paint);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int hitTest(float[] fArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public VirtualView(ReactContext reactContext) {
        super(reactContext);
        this.mOpacity = 1.0f;
        this.mCTM = new Matrix();
        this.mMatrix = new Matrix();
        this.mTransform = new Matrix();
        this.mInvCTM = new Matrix();
        this.mInvMatrix = new Matrix();
        this.mInvTransform = new Matrix();
        this.mInvertible = true;
        this.mCTMInvertible = true;
        this.mTransformInvertible = true;
        this.fontSize = -1.0d;
        this.canvasDiagonal = -1.0d;
        this.canvasHeight = -1.0f;
        this.canvasWidth = -1.0f;
        this.mContext = reactContext;
        this.mScale = DisplayMetricsHolder.getScreenDisplayMetrics().density;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (this.mClientRect != null) {
            SvgView svgView = getSvgView();
            int[] r1 = new int[2];
            getSvgView().getLocationOnScreen(r1);
            Rect rect = new Rect();
            boolean z = false;
            rect.left = r1[0] + ((int) Math.floor(this.mClientRect.left));
            rect.top = r1[1] + ((int) Math.floor(this.mClientRect.top));
            rect.right = rect.left + ((int) Math.ceil(this.mClientRect.width()));
            rect.bottom = rect.top + ((int) Math.ceil(this.mClientRect.height()));
            Rect rect2 = new Rect();
            if (svgView.getGlobalVisibleRect(rect2) && rect.intersect(rect2)) {
                z = true;
            }
            String canonicalName = getClass().getCanonicalName();
            accessibilityNodeInfo.setBoundsInScreen(rect);
            accessibilityNodeInfo.setClassName(canonicalName);
            accessibilityNodeInfo.setVisibleToUser(z);
        }
    }

    @Override // android.view.View
    public void invalidate() {
        if ((this instanceof RenderableView) && this.mPath == null) {
            return;
        }
        clearCache();
        clearParentCache();
        super.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearCache() {
        this.canvasDiagonal = -1.0d;
        this.canvasHeight = -1.0f;
        this.canvasWidth = -1.0f;
        this.fontSize = -1.0d;
        this.mStrokeRegion = null;
        this.mMarkerRegion = null;
        this.mRegion = null;
        this.mPath = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearChildCache() {
        clearCache();
        for (int r0 = 0; r0 < getChildCount(); r0++) {
            View childAt = getChildAt(r0);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).clearChildCache();
            }
        }
    }

    private void clearParentCache() {
        VirtualView virtualView = this;
        while (true) {
            ViewParent parent = virtualView.getParent();
            if (!(parent instanceof VirtualView)) {
                return;
            }
            virtualView = (VirtualView) parent;
            if (virtualView.mPath == null) {
                return;
            }
            virtualView.clearCache();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public GroupView getTextRoot() {
        if (this.mTextRoot == null) {
            VirtualView virtualView = this;
            while (true) {
                if (virtualView == null) {
                    break;
                }
                if (virtualView instanceof GroupView) {
                    GroupView groupView = (GroupView) virtualView;
                    if (groupView.getGlyphContext() != null) {
                        this.mTextRoot = groupView;
                        break;
                    }
                }
                ViewParent parent = virtualView.getParent();
                virtualView = !(parent instanceof VirtualView) ? null : (VirtualView) parent;
            }
        }
        return this.mTextRoot;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public GroupView getParentTextRoot() {
        ViewParent parent = getParent();
        if (parent instanceof VirtualView) {
            return ((VirtualView) parent).getTextRoot();
        }
        return null;
    }

    private double getFontSizeFromContext() {
        double d = this.fontSize;
        if (d != -1.0d) {
            return d;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            return 12.0d;
        }
        if (this.glyphContext == null) {
            this.glyphContext = textRoot.getGlyphContext();
        }
        double fontSize = this.glyphContext.getFontSize();
        this.fontSize = fontSize;
        return fontSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void render(Canvas canvas, Paint paint, float f) {
        draw(canvas, paint, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int saveAndSetupCanvas(Canvas canvas, Matrix matrix) {
        int save = canvas.save();
        this.mCTM.setConcat(this.mMatrix, this.mTransform);
        canvas.concat(this.mCTM);
        this.mCTM.preConcat(matrix);
        this.mCTMInvertible = this.mCTM.invert(this.mInvCTM);
        return save;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void restoreCanvas(Canvas canvas, int r2) {
        canvas.restoreToCount(r2);
    }

    public void setName(String str) {
        this.mName = str;
        invalidate();
    }

    public void setDisplay(String str) {
        this.mDisplay = str;
        invalidate();
    }

    public void setMask(String str) {
        this.mMask = str;
        invalidate();
    }

    public void setMarkerStart(String str) {
        this.mMarkerStart = str;
        invalidate();
    }

    public void setMarkerMid(String str) {
        this.mMarkerMid = str;
        invalidate();
    }

    public void setMarkerEnd(String str) {
        this.mMarkerEnd = str;
        invalidate();
    }

    public void setClipPath(String str) {
        this.mCachedClipPath = null;
        this.mClipPath = str;
        invalidate();
    }

    public void setClipRule(int r1) {
        this.mClipRule = r1;
        invalidate();
    }

    public void setOpacity(float f) {
        this.mOpacity = f;
        invalidate();
    }

    public void setMatrix(Dynamic dynamic) {
        setMatrix(!dynamic.isNull() && dynamic.getType().equals(ReadableType.Array) ? dynamic.asArray() : null);
    }

    public void setMatrix(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            float[] fArr = sRawMatrix;
            int matrixData = PropHelper.toMatrixData(readableArray, fArr, this.mScale);
            if (matrixData == 6) {
                if (this.mMatrix == null) {
                    this.mMatrix = new Matrix();
                    this.mInvMatrix = new Matrix();
                }
                this.mMatrix.setValues(fArr);
                this.mInvertible = this.mMatrix.invert(this.mInvMatrix);
            } else if (matrixData != -1) {
                FLog.m1288w(ReactConstants.TAG, "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix.reset();
            this.mInvMatrix.reset();
            this.mInvertible = true;
        }
        super.invalidate();
        clearParentCache();
    }

    public void setResponsible(boolean z) {
        this.mResponsible = z;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Path getClipPath() {
        return this.mCachedClipPath;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Path getClipPath(Canvas canvas, Paint paint) {
        Path path;
        if (this.mClipPath != null) {
            ClipPathView clipPathView = (ClipPathView) getSvgView().getDefinedClipPath(this.mClipPath);
            if (clipPathView != null) {
                if (this.mClipRule == 0) {
                    path = clipPathView.getPath(canvas, paint);
                } else {
                    path = clipPathView.getPath(canvas, paint, Region.Op.UNION);
                }
                path.transform(clipPathView.mMatrix);
                path.transform(clipPathView.mTransform);
                int r5 = this.mClipRule;
                if (r5 == 0) {
                    path.setFillType(Path.FillType.EVEN_ODD);
                } else if (r5 != 1) {
                    FLog.m1288w(ReactConstants.TAG, "RNSVG: clipRule: " + this.mClipRule + " unrecognized");
                }
                this.mCachedClipPath = path;
            } else {
                FLog.m1288w(ReactConstants.TAG, "RNSVG: Undefined clipPath: " + this.mClipPath);
            }
        }
        return getClipPath();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clip(Canvas canvas, Paint paint) {
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isResponsible() {
        return this.mResponsible;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public SvgView getSvgView() {
        SvgView svgView = this.svgView;
        if (svgView != null) {
            return svgView;
        }
        ViewParent parent = getParent();
        if (parent == null) {
            return null;
        }
        if (parent instanceof SvgView) {
            this.svgView = (SvgView) parent;
        } else if (parent instanceof VirtualView) {
            this.svgView = ((VirtualView) parent).getSvgView();
        } else {
            FLog.m1328e(ReactConstants.TAG, "RNSVG: " + getClass().getName() + " should be descendant of a SvgView.");
        }
        return this.svgView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double relativeOnWidth(SVGLength sVGLength) {
        double d;
        float canvasWidth;
        SVGLength.UnitType unitType = sVGLength.unit;
        if (unitType == SVGLength.UnitType.NUMBER) {
            d = sVGLength.value;
            canvasWidth = this.mScale;
        } else if (unitType == SVGLength.UnitType.PERCENTAGE) {
            d = sVGLength.value / 100.0d;
            canvasWidth = getCanvasWidth();
        } else {
            return fromRelativeFast(sVGLength);
        }
        return d * canvasWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double relativeOnHeight(SVGLength sVGLength) {
        double d;
        float canvasHeight;
        SVGLength.UnitType unitType = sVGLength.unit;
        if (unitType == SVGLength.UnitType.NUMBER) {
            d = sVGLength.value;
            canvasHeight = this.mScale;
        } else if (unitType == SVGLength.UnitType.PERCENTAGE) {
            d = sVGLength.value / 100.0d;
            canvasHeight = getCanvasHeight();
        } else {
            return fromRelativeFast(sVGLength);
        }
        return d * canvasHeight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double relativeOnOther(SVGLength sVGLength) {
        double d;
        double canvasDiagonal;
        SVGLength.UnitType unitType = sVGLength.unit;
        if (unitType == SVGLength.UnitType.NUMBER) {
            d = sVGLength.value;
            canvasDiagonal = this.mScale;
        } else if (unitType == SVGLength.UnitType.PERCENTAGE) {
            d = sVGLength.value / 100.0d;
            canvasDiagonal = getCanvasDiagonal();
        } else {
            return fromRelativeFast(sVGLength);
        }
        return d * canvasDiagonal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.horcrux.svg.VirtualView$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C34291 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$SVGLength$UnitType;

        static {
            int[] r0 = new int[SVGLength.UnitType.values().length];
            $SwitchMap$com$horcrux$svg$SVGLength$UnitType = r0;
            try {
                r0[SVGLength.UnitType.EMS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.EXS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.CM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.MM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.IN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.PT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType[SVGLength.UnitType.PC.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private double fromRelativeFast(SVGLength sVGLength) {
        double fontSizeFromContext;
        switch (C34291.$SwitchMap$com$horcrux$svg$SVGLength$UnitType[sVGLength.unit.ordinal()]) {
            case 1:
                fontSizeFromContext = getFontSizeFromContext();
                break;
            case 2:
                fontSizeFromContext = getFontSizeFromContext() / 2.0d;
                break;
            case 3:
                fontSizeFromContext = 35.43307d;
                break;
            case 4:
                fontSizeFromContext = 3.543307d;
                break;
            case 5:
                fontSizeFromContext = 90.0d;
                break;
            case 6:
                fontSizeFromContext = 1.25d;
                break;
            case 7:
                fontSizeFromContext = 15.0d;
                break;
            default:
                fontSizeFromContext = 1.0d;
                break;
        }
        return sVGLength.value * fontSizeFromContext * this.mScale;
    }

    private float getCanvasWidth() {
        float f = this.canvasWidth;
        if (f != -1.0f) {
            return f;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasWidth = getSvgView().getCanvasBounds().width();
        } else {
            this.canvasWidth = textRoot.getGlyphContext().getWidth();
        }
        return this.canvasWidth;
    }

    private float getCanvasHeight() {
        float f = this.canvasHeight;
        if (f != -1.0f) {
            return f;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasHeight = getSvgView().getCanvasBounds().height();
        } else {
            this.canvasHeight = textRoot.getGlyphContext().getHeight();
        }
        return this.canvasHeight;
    }

    private double getCanvasDiagonal() {
        double d = this.canvasDiagonal;
        if (d != -1.0d) {
            return d;
        }
        double sqrt = Math.sqrt(Math.pow(getCanvasWidth(), 2.0d) + Math.pow(getCanvasHeight(), 2.0d)) * M_SQRT1_2l;
        this.canvasDiagonal = sqrt;
        return sqrt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgView().defineTemplate(this, this.mName);
        }
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
    protected void onMeasure(int r3, int r4) {
        int defaultSize;
        int defaultSize2;
        RectF rectF = this.mClientRect;
        if (rectF != null) {
            defaultSize = (int) Math.ceil(rectF.width());
        } else {
            defaultSize = getDefaultSize(getSuggestedMinimumWidth(), r3);
        }
        RectF rectF2 = this.mClientRect;
        if (rectF2 != null) {
            defaultSize2 = (int) Math.ceil(rectF2.height());
        } else {
            defaultSize2 = getDefaultSize(getSuggestedMinimumHeight(), r4);
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int r2, int r3, int r4, int r5) {
        RectF rectF = this.mClientRect;
        if (rectF == null) {
            return;
        }
        if (!(this instanceof GroupView)) {
            setLeft((int) Math.floor(rectF.left));
            setTop((int) Math.floor(this.mClientRect.top));
            setRight((int) Math.ceil(this.mClientRect.right));
            setBottom((int) Math.ceil(this.mClientRect.bottom));
        }
        setMeasuredDimension((int) Math.ceil(this.mClientRect.width()), (int) Math.ceil(this.mClientRect.height()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setClientRect(RectF rectF) {
        RectF rectF2 = this.mClientRect;
        if (rectF2 == null || !rectF2.equals(rectF)) {
            this.mClientRect = rectF;
            if (rectF == null) {
                return;
            }
            int ceil = (int) Math.ceil(rectF.width());
            int ceil2 = (int) Math.ceil(this.mClientRect.height());
            int floor = (int) Math.floor(this.mClientRect.left);
            int floor2 = (int) Math.floor(this.mClientRect.top);
            int ceil3 = (int) Math.ceil(this.mClientRect.right);
            int ceil4 = (int) Math.ceil(this.mClientRect.bottom);
            setMeasuredDimension(ceil, ceil2);
            if (!(this instanceof GroupView)) {
                setLeft(floor);
                setTop(floor2);
                setRight(ceil3);
                setBottom(ceil4);
            }
            EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(this.mContext, getId());
            if (eventDispatcherForReactTag != null) {
                eventDispatcherForReactTag.dispatchEvent(OnLayoutEvent.obtain(getId(), floor, floor2, ceil, ceil2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF getClientRect() {
        return this.mClientRect;
    }
}
