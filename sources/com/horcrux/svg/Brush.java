package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;
import com.horcrux.svg.SVGLength;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class Brush {
    private ReadableArray mColors;
    private Matrix mMatrix;
    private PatternView mPattern;
    private final SVGLength[] mPoints;
    private final BrushType mType;
    private boolean mUseContentObjectBoundingBoxUnits;
    private final boolean mUseObjectBoundingBox;
    private Rect mUserSpaceBoundingBox;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum BrushType {
        LINEAR_GRADIENT,
        RADIAL_GRADIENT,
        PATTERN
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum BrushUnits {
        OBJECT_BOUNDING_BOX,
        USER_SPACE_ON_USE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Brush(BrushType brushType, SVGLength[] sVGLengthArr, BrushUnits brushUnits) {
        this.mType = brushType;
        this.mPoints = sVGLengthArr;
        this.mUseObjectBoundingBox = brushUnits == BrushUnits.OBJECT_BOUNDING_BOX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContentUnits(BrushUnits brushUnits) {
        this.mUseContentObjectBoundingBoxUnits = brushUnits == BrushUnits.OBJECT_BOUNDING_BOX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPattern(PatternView patternView) {
        this.mPattern = patternView;
    }

    private static void parseGradientStops(ReadableArray readableArray, int r5, float[] fArr, int[] r7, float f) {
        for (int r0 = 0; r0 < r5; r0++) {
            int r1 = r0 * 2;
            fArr[r0] = (float) readableArray.getDouble(r1);
            int r12 = readableArray.getInt(r1 + 1);
            r7[r0] = (r12 & ViewCompat.MEASURED_SIZE_MASK) | (Math.round((r12 >>> 24) * f) << 24);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUserSpaceBoundingBox(Rect rect) {
        this.mUserSpaceBoundingBox = rect;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGradientColors(ReadableArray readableArray) {
        this.mColors = readableArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setGradientTransform(Matrix matrix) {
        this.mMatrix = matrix;
    }

    private RectF getPaintRect(RectF rectF) {
        float f;
        if (!this.mUseObjectBoundingBox) {
            rectF = new RectF(this.mUserSpaceBoundingBox);
        }
        float width = rectF.width();
        float height = rectF.height();
        float f2 = 0.0f;
        if (this.mUseObjectBoundingBox) {
            f2 = rectF.left;
            f = rectF.top;
        } else {
            f = 0.0f;
        }
        return new RectF(f2, f, width + f2, height + f);
    }

    private double getVal(SVGLength sVGLength, double d, float f, float f2) {
        double d2;
        if (this.mUseObjectBoundingBox && sVGLength.unit == SVGLength.UnitType.NUMBER) {
            d2 = d;
            return PropHelper.fromRelative(sVGLength, d, 0.0d, d2, f2);
        }
        d2 = f;
        return PropHelper.fromRelative(sVGLength, d, 0.0d, d2, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setupPaint(Paint paint, RectF rectF, float f, float f2) {
        int[] r8;
        float[] fArr;
        RectF paintRect = getPaintRect(rectF);
        float width = paintRect.width();
        float height = paintRect.height();
        float f3 = paintRect.left;
        float f4 = paintRect.top;
        float textSize = paint.getTextSize();
        if (this.mType == BrushType.PATTERN) {
            double d = width;
            double val = getVal(this.mPoints[0], d, f, textSize);
            double d2 = height;
            double val2 = getVal(this.mPoints[1], d2, f, textSize);
            double val3 = getVal(this.mPoints[2], d, f, textSize);
            double val4 = getVal(this.mPoints[3], d2, f, textSize);
            if (val3 <= 1.0d || val4 <= 1.0d) {
                return;
            }
            Bitmap createBitmap = Bitmap.createBitmap((int) val3, (int) val4, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            RectF viewBox = this.mPattern.getViewBox();
            if (viewBox != null && viewBox.width() > 0.0f && viewBox.height() > 0.0f) {
                canvas.concat(ViewBox.getTransform(viewBox, new RectF((float) val, (float) val2, (float) val3, (float) val4), this.mPattern.mAlign, this.mPattern.mMeetOrSlice));
            }
            if (this.mUseContentObjectBoundingBoxUnits) {
                canvas.scale(width / f, height / f);
            }
            this.mPattern.draw(canvas, new Paint(), f2);
            Matrix matrix = new Matrix();
            Matrix matrix2 = this.mMatrix;
            if (matrix2 != null) {
                matrix.preConcat(matrix2);
            }
            BitmapShader bitmapShader = new BitmapShader(createBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            bitmapShader.setLocalMatrix(matrix);
            paint.setShader(bitmapShader);
            return;
        }
        int size = this.mColors.size();
        if (size == 0) {
            FLog.m1288w(ReactConstants.TAG, "Gradient contains no stops");
            return;
        }
        int r0 = size / 2;
        int[] r3 = new int[r0];
        float[] fArr2 = new float[r0];
        parseGradientStops(this.mColors, r0, fArr2, r3, f2);
        if (r0 == 1) {
            int[] r02 = {r3[0], r3[0]};
            float[] fArr3 = {fArr2[0], fArr2[0]};
            FLog.m1288w(ReactConstants.TAG, "Gradient contains only one stop");
            r8 = r02;
            fArr = fArr3;
        } else {
            r8 = r3;
            fArr = fArr2;
        }
        if (this.mType == BrushType.LINEAR_GRADIENT) {
            double d3 = width;
            double val5 = getVal(this.mPoints[0], d3, f, textSize);
            double d4 = f3;
            double d5 = height;
            double d6 = f4;
            Shader linearGradient = new LinearGradient((float) (val5 + d4), (float) (getVal(this.mPoints[1], d5, f, textSize) + d6), (float) (getVal(this.mPoints[2], d3, f, textSize) + d4), (float) (getVal(this.mPoints[3], d5, f, textSize) + d6), r8, fArr, Shader.TileMode.CLAMP);
            if (this.mMatrix != null) {
                Matrix matrix3 = new Matrix();
                matrix3.preConcat(this.mMatrix);
                linearGradient.setLocalMatrix(matrix3);
            }
            paint.setShader(linearGradient);
        } else if (this.mType == BrushType.RADIAL_GRADIENT) {
            double d7 = width;
            double val6 = getVal(this.mPoints[2], d7, f, textSize);
            double d8 = height;
            double val7 = getVal(this.mPoints[3], d8, f, textSize) / val6;
            Shader radialGradient = new RadialGradient((float) (getVal(this.mPoints[4], d7, f, textSize) + f3), (float) (getVal(this.mPoints[5], d8 / val7, f, textSize) + (f4 / val7)), (float) val6, r8, fArr, Shader.TileMode.CLAMP);
            Matrix matrix4 = new Matrix();
            matrix4.preScale(1.0f, (float) val7);
            Matrix matrix5 = this.mMatrix;
            if (matrix5 != null) {
                matrix4.preConcat(matrix5);
            }
            radialGradient.setLocalMatrix(matrix4);
            paint.setShader(radialGradient);
        }
    }
}
