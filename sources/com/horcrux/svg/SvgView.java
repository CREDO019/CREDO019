package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Base64;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.uimanager.ReactCompoundViewGroup;
import com.facebook.react.views.view.ReactViewGroup;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class SvgView extends ReactViewGroup implements ReactCompoundView, ReactCompoundViewGroup {
    private String mAlign;
    @Nullable
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private final Map<String, Brush> mDefinedBrushes;
    private final Map<String, VirtualView> mDefinedClipPaths;
    private final Map<String, VirtualView> mDefinedMarkers;
    private final Map<String, VirtualView> mDefinedMasks;
    private final Map<String, VirtualView> mDefinedTemplates;
    final Matrix mInvViewBoxMatrix;
    private boolean mInvertible;
    private int mMeetOrSlice;
    private float mMinX;
    private float mMinY;
    private boolean mRemovalTransitionStarted;
    private boolean mRendered;
    private boolean mResponsible;
    private final float mScale;
    int mTintColor;
    private float mVbHeight;
    private float mVbWidth;
    private SVGLength mbbHeight;
    private SVGLength mbbWidth;
    private Runnable toDataUrlTask;

    @Override // com.facebook.react.uimanager.ReactCompoundViewGroup
    public boolean interceptsTouchEvent(float f, float f2) {
        return true;
    }

    /* loaded from: classes3.dex */
    public enum Events {
        EVENT_DATA_URL("onDataURL");
        
        private final String mName;

        Events(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        @Nonnull
        public String toString() {
            return this.mName;
        }
    }

    public SvgView(ReactContext reactContext) {
        super(reactContext);
        this.toDataUrlTask = null;
        this.mResponsible = false;
        this.mDefinedClipPaths = new HashMap();
        this.mDefinedTemplates = new HashMap();
        this.mDefinedMarkers = new HashMap();
        this.mDefinedMasks = new HashMap();
        this.mDefinedBrushes = new HashMap();
        this.mInvViewBoxMatrix = new Matrix();
        this.mInvertible = true;
        this.mRendered = false;
        this.mTintColor = 0;
        this.mScale = DisplayMetricsHolder.getScreenDisplayMetrics().density;
        setWillNotDraw(false);
    }

    @Override // android.view.View
    public void setId(int r1) {
        super.setId(r1);
        SvgViewManager.setSvgView(r1, this);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setVisibleToUser(getGlobalVisibleRect(new Rect()));
        accessibilityNodeInfo.setClassName(getClass().getCanonicalName());
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        ViewParent parent = getParent();
        if (parent instanceof VirtualView) {
            if (this.mRendered) {
                this.mRendered = false;
                ((VirtualView) parent).getSvgView().invalidate();
            }
        } else if (this.mRemovalTransitionStarted) {
        } else {
            Bitmap bitmap = this.mBitmap;
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.mBitmap = null;
        }
    }

    @Override // android.view.ViewGroup
    public void startViewTransition(View view) {
        this.mRemovalTransitionStarted = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.mBitmap = null;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (getParent() instanceof VirtualView) {
            return;
        }
        super.onDraw(canvas);
        if (this.mBitmap == null) {
            this.mBitmap = drawOutput();
        }
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            Runnable runnable = this.toDataUrlTask;
            if (runnable != null) {
                runnable.run();
                this.toDataUrlTask = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setToDataUrlTask(Runnable runnable) {
        this.toDataUrlTask = runnable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        invalidate();
    }

    @Override // com.facebook.react.uimanager.ReactCompoundView
    public int reactTagForTouch(float f, float f2) {
        return hitTest(f, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean notRendered() {
        return !this.mRendered;
    }

    private void clearChildCache() {
        if (this.mRendered) {
            this.mRendered = false;
            for (int r0 = 0; r0 < getChildCount(); r0++) {
                View childAt = getChildAt(r0);
                if (childAt instanceof VirtualView) {
                    ((VirtualView) childAt).clearChildCache();
                }
            }
        }
    }

    public void setTintColor(Integer num) {
        this.mTintColor = num != null ? num.intValue() : 0;
        invalidate();
        clearChildCache();
    }

    public void setMinX(float f) {
        this.mMinX = f;
        invalidate();
        clearChildCache();
    }

    public void setMinY(float f) {
        this.mMinY = f;
        invalidate();
        clearChildCache();
    }

    public void setVbWidth(float f) {
        this.mVbWidth = f;
        invalidate();
        clearChildCache();
    }

    public void setVbHeight(float f) {
        this.mVbHeight = f;
        invalidate();
        clearChildCache();
    }

    public void setBbWidth(Dynamic dynamic) {
        this.mbbWidth = SVGLength.from(dynamic);
        invalidate();
        clearChildCache();
    }

    public void setBbWidth(String str) {
        this.mbbWidth = SVGLength.from(str);
        invalidate();
        clearChildCache();
    }

    public void setBbWidth(Double d) {
        this.mbbWidth = SVGLength.from(d);
        invalidate();
        clearChildCache();
    }

    public void setBbHeight(Dynamic dynamic) {
        this.mbbHeight = SVGLength.from(dynamic);
        invalidate();
        clearChildCache();
    }

    public void setBbHeight(String str) {
        this.mbbHeight = SVGLength.from(str);
        invalidate();
        clearChildCache();
    }

    public void setBbHeight(Double d) {
        this.mbbHeight = SVGLength.from(d);
        invalidate();
        clearChildCache();
    }

    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
        clearChildCache();
    }

    public void setMeetOrSlice(int r1) {
        this.mMeetOrSlice = r1;
        invalidate();
        clearChildCache();
    }

    private Bitmap drawOutput() {
        boolean z = true;
        this.mRendered = true;
        float width = getWidth();
        float height = getHeight();
        if (!Float.isNaN(width) && !Float.isNaN(height) && width >= 1.0f && height >= 1.0f && Math.log10(width) + Math.log10(height) <= 42.0d) {
            z = false;
        }
        if (z) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
        drawChildren(new Canvas(createBitmap));
        return createBitmap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Rect getCanvasBounds() {
        return this.mCanvas.getClipBounds();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void drawChildren(Canvas canvas) {
        this.mRendered = true;
        this.mCanvas = canvas;
        Matrix matrix = new Matrix();
        if (this.mAlign != null) {
            RectF viewBox = getViewBox();
            float width = canvas.getWidth();
            float height = canvas.getHeight();
            boolean z = getParent() instanceof VirtualView;
            if (z) {
                width = (float) PropHelper.fromRelative(this.mbbWidth, width, 0.0d, this.mScale, 12.0d);
                height = (float) PropHelper.fromRelative(this.mbbHeight, height, 0.0d, this.mScale, 12.0d);
            }
            RectF rectF = new RectF(0.0f, 0.0f, width, height);
            if (z) {
                canvas.clipRect(rectF);
            }
            matrix = ViewBox.getTransform(viewBox, rectF, this.mAlign, this.mMeetOrSlice);
            this.mInvertible = matrix.invert(this.mInvViewBoxMatrix);
            canvas.concat(matrix);
        }
        Paint paint = new Paint();
        paint.setFlags(385);
        paint.setTypeface(Typeface.DEFAULT);
        for (int r4 = 0; r4 < getChildCount(); r4++) {
            View childAt = getChildAt(r4);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).saveDefinition();
            }
        }
        for (int r3 = 0; r3 < getChildCount(); r3++) {
            View childAt2 = getChildAt(r3);
            if (childAt2 instanceof VirtualView) {
                VirtualView virtualView = (VirtualView) childAt2;
                int saveAndSetupCanvas = virtualView.saveAndSetupCanvas(canvas, matrix);
                virtualView.render(canvas, paint, 1.0f);
                virtualView.restoreCanvas(canvas, saveAndSetupCanvas);
                if (virtualView.isResponsible() && !this.mResponsible) {
                    this.mResponsible = true;
                }
            }
        }
    }

    private RectF getViewBox() {
        float f = this.mMinX;
        float f2 = this.mScale;
        float f3 = this.mMinY;
        return new RectF(f * f2, f3 * f2, (f + this.mVbWidth) * f2, (f3 + this.mVbHeight) * f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String toDataURL() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        clearChildCache();
        drawChildren(new Canvas(createBitmap));
        clearChildCache();
        invalidate();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        createBitmap.recycle();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String toDataURL(int r3, int r4) {
        Bitmap createBitmap = Bitmap.createBitmap(r3, r4, Bitmap.Config.ARGB_8888);
        clearChildCache();
        drawChildren(new Canvas(createBitmap));
        clearChildCache();
        invalidate();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        createBitmap.recycle();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enableTouchEvents() {
        if (this.mResponsible) {
            return;
        }
        this.mResponsible = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isResponsible() {
        return this.mResponsible;
    }

    private int hitTest(float f, float f2) {
        if (!this.mResponsible || !this.mInvertible) {
            return getId();
        }
        float[] fArr = {f, f2};
        this.mInvViewBoxMatrix.mapPoints(fArr);
        int r3 = -1;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt instanceof VirtualView) {
                r3 = ((VirtualView) childAt).hitTest(fArr);
            } else if (childAt instanceof SvgView) {
                r3 = ((SvgView) childAt).hitTest(f, f2);
            }
            if (r3 != -1) {
                break;
            }
        }
        return r3 == -1 ? getId() : r3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineClipPath(VirtualView virtualView, String str) {
        this.mDefinedClipPaths.put(str, virtualView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VirtualView getDefinedClipPath(String str) {
        return this.mDefinedClipPaths.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineTemplate(VirtualView virtualView, String str) {
        this.mDefinedTemplates.put(str, virtualView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VirtualView getDefinedTemplate(String str) {
        return this.mDefinedTemplates.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineBrush(Brush brush, String str) {
        this.mDefinedBrushes.put(str, brush);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Brush getDefinedBrush(String str) {
        return this.mDefinedBrushes.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineMask(VirtualView virtualView, String str) {
        this.mDefinedMasks.put(str, virtualView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VirtualView getDefinedMask(String str) {
        return this.mDefinedMasks.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defineMarker(VirtualView virtualView, String str) {
        this.mDefinedMarkers.put(str, virtualView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VirtualView getDefinedMarker(String str) {
        return this.mDefinedMarkers.get(str);
    }
}
