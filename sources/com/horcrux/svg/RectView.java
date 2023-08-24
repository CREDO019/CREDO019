package com.horcrux.svg;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class RectView extends RenderableView {

    /* renamed from: mH */
    private SVGLength f1270mH;
    private SVGLength mRx;
    private SVGLength mRy;

    /* renamed from: mW */
    private SVGLength f1271mW;

    /* renamed from: mX */
    private SVGLength f1272mX;

    /* renamed from: mY */
    private SVGLength f1273mY;

    public RectView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setX(Dynamic dynamic) {
        this.f1272mX = SVGLength.from(dynamic);
        invalidate();
    }

    public void setX(String str) {
        this.f1272mX = SVGLength.from(str);
        invalidate();
    }

    public void setX(Double d) {
        this.f1272mX = SVGLength.from(d);
        invalidate();
    }

    public void setY(Dynamic dynamic) {
        this.f1273mY = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY(String str) {
        this.f1273mY = SVGLength.from(str);
        invalidate();
    }

    public void setY(Double d) {
        this.f1273mY = SVGLength.from(d);
        invalidate();
    }

    public void setWidth(Dynamic dynamic) {
        this.f1271mW = SVGLength.from(dynamic);
        invalidate();
    }

    public void setWidth(String str) {
        this.f1271mW = SVGLength.from(str);
        invalidate();
    }

    public void setWidth(Double d) {
        this.f1271mW = SVGLength.from(d);
        invalidate();
    }

    public void setHeight(Dynamic dynamic) {
        this.f1270mH = SVGLength.from(dynamic);
        invalidate();
    }

    public void setHeight(String str) {
        this.f1270mH = SVGLength.from(str);
        invalidate();
    }

    public void setHeight(Double d) {
        this.f1270mH = SVGLength.from(d);
        invalidate();
    }

    public void setRx(Dynamic dynamic) {
        this.mRx = SVGLength.from(dynamic);
        invalidate();
    }

    public void setRx(String str) {
        this.mRx = SVGLength.from(str);
        invalidate();
    }

    public void setRx(Double d) {
        this.mRx = SVGLength.from(d);
        invalidate();
    }

    public void setRy(Dynamic dynamic) {
        this.mRy = SVGLength.from(dynamic);
        invalidate();
    }

    public void setRy(String str) {
        this.mRy = SVGLength.from(str);
        invalidate();
    }

    public void setRy(Double d) {
        this.mRy = SVGLength.from(d);
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008f  */
    @Override // com.horcrux.svg.RenderableView, com.horcrux.svg.VirtualView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Path getPath(android.graphics.Canvas r21, android.graphics.Paint r22) {
        /*
            Method dump skipped, instructions count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.RectView.getPath(android.graphics.Canvas, android.graphics.Paint):android.graphics.Path");
    }
}
