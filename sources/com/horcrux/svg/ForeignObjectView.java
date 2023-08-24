package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ForeignObjectView extends GroupView {
    Canvas fake;
    Bitmap fakeBitmap;

    /* renamed from: mH */
    SVGLength f1245mH;

    /* renamed from: mW */
    SVGLength f1246mW;

    /* renamed from: mX */
    SVGLength f1247mX;

    /* renamed from: mY */
    SVGLength f1248mY;

    public ForeignObjectView(ReactContext reactContext) {
        super(reactContext);
        this.fakeBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        this.fake = new Canvas(this.fakeBitmap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        canvas.translate((float) relativeOnWidth(this.f1247mX), (float) relativeOnHeight(this.f1248mY));
        canvas.clipRect(0.0f, 0.0f, (float) relativeOnWidth(this.f1246mW), (float) relativeOnHeight(this.f1245mH));
        super.draw(canvas, paint, f);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onDescendantInvalidated(View view, View view2) {
        super.onDescendantInvalidated(view, view2);
        invalidate();
    }

    public void setX(Dynamic dynamic) {
        this.f1247mX = SVGLength.from(dynamic);
        invalidate();
    }

    public void setX(String str) {
        this.f1247mX = SVGLength.from(str);
        invalidate();
    }

    public void setX(Double d) {
        this.f1247mX = SVGLength.from(d);
        invalidate();
    }

    public void setY(Dynamic dynamic) {
        this.f1248mY = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY(String str) {
        this.f1248mY = SVGLength.from(str);
        invalidate();
    }

    public void setY(Double d) {
        this.f1248mY = SVGLength.from(d);
        invalidate();
    }

    public void setWidth(Dynamic dynamic) {
        this.f1246mW = SVGLength.from(dynamic);
        invalidate();
    }

    public void setWidth(String str) {
        this.f1246mW = SVGLength.from(str);
        invalidate();
    }

    public void setWidth(Double d) {
        this.f1246mW = SVGLength.from(d);
        invalidate();
    }

    public void setHeight(Dynamic dynamic) {
        this.f1245mH = SVGLength.from(dynamic);
        invalidate();
    }

    public void setHeight(String str) {
        this.f1245mH = SVGLength.from(str);
        invalidate();
    }

    public void setHeight(Double d) {
        this.f1245mH = SVGLength.from(d);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView
    public void drawGroup(Canvas canvas, Paint paint, float f) {
        pushGlyphContext();
        SvgView svgView = getSvgView();
        RectF rectF = new RectF();
        for (int r2 = 0; r2 < getChildCount(); r2++) {
            View childAt = getChildAt(r2);
            if (!(childAt instanceof MaskView)) {
                if (childAt instanceof VirtualView) {
                    VirtualView virtualView = (VirtualView) childAt;
                    if (!"none".equals(virtualView.mDisplay)) {
                        boolean z = virtualView instanceof RenderableView;
                        if (z) {
                            ((RenderableView) virtualView).mergeProperties(this);
                        }
                        int saveAndSetupCanvas = virtualView.saveAndSetupCanvas(canvas, this.mCTM);
                        virtualView.render(canvas, paint, this.mOpacity * f);
                        RectF clientRect = virtualView.getClientRect();
                        if (clientRect != null) {
                            rectF.union(clientRect);
                        }
                        virtualView.restoreCanvas(canvas, saveAndSetupCanvas);
                        if (z) {
                            ((RenderableView) virtualView).resetProperties();
                        }
                        if (virtualView.isResponsible()) {
                            svgView.enableTouchEvents();
                        }
                    }
                } else if (childAt instanceof SvgView) {
                    SvgView svgView2 = (SvgView) childAt;
                    svgView2.drawChildren(canvas);
                    if (svgView2.isResponsible()) {
                        svgView.enableTouchEvents();
                    }
                } else {
                    childAt.draw(canvas);
                }
            }
        }
        setClientRect(rectF);
        popGlyphContext();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(this.fake);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(this.fake, view, j);
    }
}
