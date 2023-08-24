package com.horcrux.svg;

import android.graphics.Matrix;
import android.graphics.RectF;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;
import com.horcrux.svg.Brush;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class PatternView extends GroupView {
    private static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    String mAlign;

    /* renamed from: mH */
    private SVGLength f1262mH;
    private Matrix mMatrix;
    int mMeetOrSlice;
    private float mMinX;
    private float mMinY;
    private Brush.BrushUnits mPatternContentUnits;
    private Brush.BrushUnits mPatternUnits;
    private float mVbHeight;
    private float mVbWidth;

    /* renamed from: mW */
    private SVGLength f1263mW;

    /* renamed from: mX */
    private SVGLength f1264mX;

    /* renamed from: mY */
    private SVGLength f1265mY;

    public PatternView(ReactContext reactContext) {
        super(reactContext);
        this.mMatrix = null;
    }

    public void setX(Dynamic dynamic) {
        this.f1264mX = SVGLength.from(dynamic);
        invalidate();
    }

    public void setX(String str) {
        this.f1264mX = SVGLength.from(str);
        invalidate();
    }

    public void setX(Double d) {
        this.f1264mX = SVGLength.from(d);
        invalidate();
    }

    public void setY(Dynamic dynamic) {
        this.f1265mY = SVGLength.from(dynamic);
        invalidate();
    }

    public void setY(String str) {
        this.f1265mY = SVGLength.from(str);
        invalidate();
    }

    public void setY(Double d) {
        this.f1265mY = SVGLength.from(d);
        invalidate();
    }

    public void setWidth(Dynamic dynamic) {
        this.f1263mW = SVGLength.from(dynamic);
        invalidate();
    }

    public void setWidth(String str) {
        this.f1263mW = SVGLength.from(str);
        invalidate();
    }

    public void setWidth(Double d) {
        this.f1263mW = SVGLength.from(d);
        invalidate();
    }

    public void setHeight(Dynamic dynamic) {
        this.f1262mH = SVGLength.from(dynamic);
        invalidate();
    }

    public void setHeight(String str) {
        this.f1262mH = SVGLength.from(str);
        invalidate();
    }

    public void setHeight(Double d) {
        this.f1262mH = SVGLength.from(d);
        invalidate();
    }

    public void setPatternUnits(int r2) {
        if (r2 == 0) {
            this.mPatternUnits = Brush.BrushUnits.OBJECT_BOUNDING_BOX;
        } else if (r2 == 1) {
            this.mPatternUnits = Brush.BrushUnits.USER_SPACE_ON_USE;
        }
        invalidate();
    }

    public void setPatternContentUnits(int r2) {
        if (r2 == 0) {
            this.mPatternContentUnits = Brush.BrushUnits.OBJECT_BOUNDING_BOX;
        } else if (r2 == 1) {
            this.mPatternContentUnits = Brush.BrushUnits.USER_SPACE_ON_USE;
        }
        invalidate();
    }

    public void setPatternTransform(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            float[] fArr = sRawMatrix;
            int matrixData = PropHelper.toMatrixData(readableArray, fArr, this.mScale);
            if (matrixData == 6) {
                if (this.mMatrix == null) {
                    this.mMatrix = new Matrix();
                }
                this.mMatrix.setValues(fArr);
            } else if (matrixData != -1) {
                FLog.m1288w(ReactConstants.TAG, "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix = null;
        }
        invalidate();
    }

    public void setMinX(float f) {
        this.mMinX = f;
        invalidate();
    }

    public void setMinY(float f) {
        this.mMinY = f;
        invalidate();
    }

    public void setVbWidth(float f) {
        this.mVbWidth = f;
        invalidate();
    }

    public void setVbHeight(float f) {
        this.mVbHeight = f;
        invalidate();
    }

    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
    }

    public void setMeetOrSlice(int r1) {
        this.mMeetOrSlice = r1;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF getViewBox() {
        return new RectF(this.mMinX * this.mScale, this.mMinY * this.mScale, (this.mMinX + this.mVbWidth) * this.mScale, (this.mMinY + this.mVbHeight) * this.mScale);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.horcrux.svg.GroupView, com.horcrux.svg.VirtualView
    public void saveDefinition() {
        if (this.mName != null) {
            Brush brush = new Brush(Brush.BrushType.PATTERN, new SVGLength[]{this.f1264mX, this.f1265mY, this.f1263mW, this.f1262mH}, this.mPatternUnits);
            brush.setContentUnits(this.mPatternContentUnits);
            brush.setPattern(this);
            Matrix matrix = this.mMatrix;
            if (matrix != null) {
                brush.setGradientTransform(matrix);
            }
            SvgView svgView = getSvgView();
            if (this.mPatternUnits == Brush.BrushUnits.USER_SPACE_ON_USE || this.mPatternContentUnits == Brush.BrushUnits.USER_SPACE_ON_USE) {
                brush.setUserSpaceBoundingBox(svgView.getCanvasBounds());
            }
            svgView.defineBrush(brush, this.mName);
        }
    }
}
