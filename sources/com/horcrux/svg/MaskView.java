package com.horcrux.svg;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.horcrux.svg.Brush;

/* loaded from: classes3.dex */
class MaskView extends GroupView {

    /* renamed from: mH */
    SVGLength f1255mH;
    private Brush.BrushUnits mMaskContentUnits;
    private Brush.BrushUnits mMaskUnits;

    /* renamed from: mW */
    SVGLength f1256mW;

    /* renamed from: mX */
    SVGLength f1257mX;

    /* renamed from: mY */
    SVGLength f1258mY;

    public MaskView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setX(Dynamic dynamic) {
        this.f1257mX = SVGLength.from(dynamic);
        invalidate();
    }

    public void setX(String str) {
        this.f1257mX = SVGLength.from(str);
        invalidate();
    }

    public void setX(Double d) {
        this.f1257mX = SVGLength.from(d);
        invalidate();
    }

    public void setY(Dynamic dynamic) {
        this.f1258mY = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY(String str) {
        this.f1258mY = SVGLength.from(str);
        invalidate();
    }

    public void setY(Double d) {
        this.f1258mY = SVGLength.from(d);
        invalidate();
    }

    public void setWidth(Dynamic dynamic) {
        this.f1256mW = SVGLength.from(dynamic);
        invalidate();
    }

    public void setWidth(String str) {
        this.f1256mW = SVGLength.from(str);
        invalidate();
    }

    public void setWidth(Double d) {
        this.f1256mW = SVGLength.from(d);
        invalidate();
    }

    public void setHeight(Dynamic dynamic) {
        this.f1255mH = SVGLength.from(dynamic);
        invalidate();
    }

    public void setHeight(String str) {
        this.f1255mH = SVGLength.from(str);
        invalidate();
    }

    public void setHeight(Double d) {
        this.f1255mH = SVGLength.from(d);
        invalidate();
    }

    public void setMaskUnits(int r2) {
        if (r2 == 0) {
            this.mMaskUnits = Brush.BrushUnits.OBJECT_BOUNDING_BOX;
        } else if (r2 == 1) {
            this.mMaskUnits = Brush.BrushUnits.USER_SPACE_ON_USE;
        }
        invalidate();
    }

    public void setMaskContentUnits(int r2) {
        if (r2 == 0) {
            this.mMaskContentUnits = Brush.BrushUnits.OBJECT_BOUNDING_BOX;
        } else if (r2 == 1) {
            this.mMaskContentUnits = Brush.BrushUnits.USER_SPACE_ON_USE;
        }
        invalidate();
    }

    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    void saveDefinition() {
        if (this.mName != null) {
            getSvgView().defineMask(this, this.mName);
        }
    }
}
