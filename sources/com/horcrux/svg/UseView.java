package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class UseView extends RenderableView {

    /* renamed from: mH */
    private SVGLength f1282mH;
    private String mHref;

    /* renamed from: mW */
    private SVGLength f1283mW;

    /* renamed from: mX */
    private SVGLength f1284mX;

    /* renamed from: mY */
    private SVGLength f1285mY;

    public UseView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setHref(String str) {
        this.mHref = str;
        invalidate();
    }

    public void setX(Dynamic dynamic) {
        this.f1284mX = SVGLength.from(dynamic);
        invalidate();
    }

    public void setX(String str) {
        this.f1284mX = SVGLength.from(str);
        invalidate();
    }

    public void setX(Double d) {
        this.f1284mX = SVGLength.from(d);
        invalidate();
    }

    public void setY(Dynamic dynamic) {
        this.f1285mY = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY(String str) {
        this.f1285mY = SVGLength.from(str);
        invalidate();
    }

    public void setY(Double d) {
        this.f1285mY = SVGLength.from(d);
        invalidate();
    }

    public void setWidth(Dynamic dynamic) {
        this.f1283mW = SVGLength.from(dynamic);
        invalidate();
    }

    public void setWidth(String str) {
        this.f1283mW = SVGLength.from(str);
        invalidate();
    }

    public void setWidth(Double d) {
        this.f1283mW = SVGLength.from(d);
        invalidate();
    }

    public void setHeight(Dynamic dynamic) {
        this.f1282mH = SVGLength.from(dynamic);
        invalidate();
    }

    public void setHeight(String str) {
        this.f1282mH = SVGLength.from(str);
        invalidate();
    }

    public void setHeight(Double d) {
        this.f1282mH = SVGLength.from(d);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public void draw(Canvas canvas, Paint paint, float f) {
        VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
        if (definedTemplate == null) {
            FLog.m1288w(ReactConstants.TAG, "`Use` element expected a pre-defined svg template as `href` prop, template named: " + this.mHref + " is not defined.");
            return;
        }
        definedTemplate.clearCache();
        canvas.translate((float) relativeOnWidth(this.f1284mX), (float) relativeOnHeight(this.f1285mY));
        boolean z = definedTemplate instanceof RenderableView;
        if (z) {
            ((RenderableView) definedTemplate).mergeProperties(this);
        }
        int saveAndSetupCanvas = definedTemplate.saveAndSetupCanvas(canvas, this.mCTM);
        clip(canvas, paint);
        if (definedTemplate instanceof SymbolView) {
            ((SymbolView) definedTemplate).drawSymbol(canvas, paint, f, (float) relativeOnWidth(this.f1283mW), (float) relativeOnHeight(this.f1282mH));
        } else {
            definedTemplate.draw(canvas, paint, f * this.mOpacity);
        }
        setClientRect(definedTemplate.getClientRect());
        definedTemplate.restoreCanvas(canvas, saveAndSetupCanvas);
        if (z) {
            ((RenderableView) definedTemplate).resetProperties();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public int hitTest(float[] fArr) {
        if (this.mInvertible && this.mTransformInvertible) {
            float[] fArr2 = new float[2];
            this.mInvMatrix.mapPoints(fArr2, fArr);
            this.mInvTransform.mapPoints(fArr2);
            VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
            if (definedTemplate == null) {
                FLog.m1288w(ReactConstants.TAG, "`Use` element expected a pre-defined svg template as `href` prop, template named: " + this.mHref + " is not defined.");
                return -1;
            }
            int hitTest = definedTemplate.hitTest(fArr2);
            if (hitTest != -1) {
                return (definedTemplate.isResponsible() || hitTest != definedTemplate.getId()) ? hitTest : getId();
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    public Path getPath(Canvas canvas, Paint paint) {
        VirtualView definedTemplate = getSvgView().getDefinedTemplate(this.mHref);
        if (definedTemplate == null) {
            FLog.m1288w(ReactConstants.TAG, "`Use` element expected a pre-defined svg template as `href` prop, template named: " + this.mHref + " is not defined.");
            return null;
        }
        Path path = definedTemplate.getPath(canvas, paint);
        Path path2 = new Path();
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) relativeOnWidth(this.f1284mX), (float) relativeOnHeight(this.f1285mY));
        path.transform(matrix, path2);
        return path2;
    }
}
