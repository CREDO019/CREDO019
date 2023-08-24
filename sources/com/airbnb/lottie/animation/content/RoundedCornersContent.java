package com.airbnb.lottie.animation.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RoundedCornersContent implements ShapeModifierContent, BaseKeyframeAnimation.AnimationListener {
    private static final float ROUNDED_CORNER_MAGIC_NUMBER = 0.5519f;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Float, Float> roundedCorners;
    private ShapeData shapeData;

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> list, List<Content> list2) {
    }

    public RoundedCornersContent(LottieDrawable lottieDrawable, BaseLayer baseLayer, RoundedCorners roundedCorners) {
        this.lottieDrawable = lottieDrawable;
        this.name = roundedCorners.getName();
        BaseKeyframeAnimation<Float, Float> createAnimation = roundedCorners.getCornerRadius().createAnimation();
        this.roundedCorners = createAnimation;
        baseLayer.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public BaseKeyframeAnimation<Float, Float> getRoundedCorners() {
        return this.roundedCorners;
    }

    @Override // com.airbnb.lottie.animation.content.ShapeModifierContent
    public ShapeData modifyShape(ShapeData shapeData) {
        List<CubicCurveData> list;
        boolean z;
        List<CubicCurveData> curves = shapeData.getCurves();
        if (curves.size() <= 2) {
            return shapeData;
        }
        float floatValue = this.roundedCorners.getValue().floatValue();
        if (floatValue == 0.0f) {
            return shapeData;
        }
        ShapeData shapeData2 = getShapeData(shapeData);
        shapeData2.setInitialPoint(shapeData.getInitialPoint().x, shapeData.getInitialPoint().y);
        List<CubicCurveData> curves2 = shapeData2.getCurves();
        boolean isClosed = shapeData.isClosed();
        int r7 = 0;
        int r8 = 0;
        while (r7 < curves.size()) {
            CubicCurveData cubicCurveData = curves.get(r7);
            CubicCurveData cubicCurveData2 = curves.get(floorMod(r7 - 1, curves.size()));
            CubicCurveData cubicCurveData3 = curves.get(floorMod(r7 - 2, curves.size()));
            PointF vertex = (r7 != 0 || isClosed) ? cubicCurveData2.getVertex() : shapeData.getInitialPoint();
            PointF controlPoint2 = (r7 != 0 || isClosed) ? cubicCurveData2.getControlPoint2() : vertex;
            PointF controlPoint1 = cubicCurveData.getControlPoint1();
            PointF vertex2 = cubicCurveData3.getVertex();
            PointF vertex3 = cubicCurveData.getVertex();
            boolean z2 = !shapeData.isClosed() && r7 == 0 && r7 == curves.size() + (-1);
            if (controlPoint2.equals(vertex) && controlPoint1.equals(vertex) && !z2) {
                float f = vertex.x - vertex2.x;
                float f2 = vertex.y - vertex2.y;
                float f3 = vertex3.x - vertex.x;
                float f4 = vertex3.y - vertex.y;
                list = curves;
                z = isClosed;
                float min = Math.min(floatValue / ((float) Math.hypot(f, f2)), 0.5f);
                float min2 = Math.min(floatValue / ((float) Math.hypot(f3, f4)), 0.5f);
                float f5 = vertex.x + ((vertex2.x - vertex.x) * min);
                float f6 = vertex.y + ((vertex2.y - vertex.y) * min);
                float f7 = vertex.x + ((vertex3.x - vertex.x) * min2);
                float f8 = vertex.y + ((vertex3.y - vertex.y) * min2);
                float f9 = f5 - ((f5 - vertex.x) * ROUNDED_CORNER_MAGIC_NUMBER);
                float f10 = f6 - ((f6 - vertex.y) * ROUNDED_CORNER_MAGIC_NUMBER);
                float f11 = f7 - ((f7 - vertex.x) * ROUNDED_CORNER_MAGIC_NUMBER);
                float f12 = f8 - ((f8 - vertex.y) * ROUNDED_CORNER_MAGIC_NUMBER);
                CubicCurveData cubicCurveData4 = curves2.get(floorMod(r8 - 1, curves2.size()));
                CubicCurveData cubicCurveData5 = curves2.get(r8);
                cubicCurveData4.setControlPoint2(f5, f6);
                cubicCurveData4.setVertex(f5, f6);
                if (r7 == 0) {
                    shapeData2.setInitialPoint(f5, f6);
                }
                cubicCurveData5.setControlPoint1(f9, f10);
                r8++;
                cubicCurveData5.setControlPoint2(f11, f12);
                cubicCurveData5.setVertex(f7, f8);
                curves2.get(r8).setControlPoint1(f7, f8);
            } else {
                list = curves;
                z = isClosed;
                CubicCurveData cubicCurveData6 = curves2.get(floorMod(r8 - 1, curves2.size()));
                cubicCurveData6.setControlPoint2(cubicCurveData2.getVertex().x, cubicCurveData2.getVertex().y);
                cubicCurveData6.setVertex(cubicCurveData2.getVertex().x, cubicCurveData2.getVertex().y);
                curves2.get(r8).setControlPoint1(cubicCurveData.getVertex().x, cubicCurveData.getVertex().y);
            }
            r8++;
            r7++;
            curves = list;
            isClosed = z;
        }
        return shapeData2;
    }

    private ShapeData getShapeData(ShapeData shapeData) {
        List<CubicCurveData> curves = shapeData.getCurves();
        boolean isClosed = shapeData.isClosed();
        int size = curves.size() - 1;
        int r5 = 0;
        while (size >= 0) {
            CubicCurveData cubicCurveData = curves.get(size);
            CubicCurveData cubicCurveData2 = curves.get(floorMod(size - 1, curves.size()));
            PointF vertex = (size != 0 || isClosed) ? cubicCurveData2.getVertex() : shapeData.getInitialPoint();
            r5 = (((size != 0 || isClosed) ? cubicCurveData2.getControlPoint2() : vertex).equals(vertex) && cubicCurveData.getControlPoint1().equals(vertex) && !(!shapeData.isClosed() && size == 0 && size == curves.size() - 1)) ? r5 + 2 : r5 + 1;
            size--;
        }
        ShapeData shapeData2 = this.shapeData;
        if (shapeData2 == null || shapeData2.getCurves().size() != r5) {
            ArrayList arrayList = new ArrayList(r5);
            for (int r0 = 0; r0 < r5; r0++) {
                arrayList.add(new CubicCurveData());
            }
            this.shapeData = new ShapeData(new PointF(0.0f, 0.0f), false, arrayList);
        }
        this.shapeData.setClosed(isClosed);
        return this.shapeData;
    }

    private static int floorMod(int r1, int r2) {
        return r1 - (floorDiv(r1, r2) * r2);
    }

    private static int floorDiv(int r2, int r3) {
        int r0 = r2 / r3;
        return ((r2 ^ r3) >= 0 || r3 * r0 == r2) ? r0 : r0 - 1;
    }
}
