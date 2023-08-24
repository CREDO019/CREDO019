package com.airbnb.lottie.utils;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.animation.content.KeyPathElementContent;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeData;
import java.util.List;

/* loaded from: classes.dex */
public class MiscUtils {
    private static final PointF pathFromDataCurrentPoint = new PointF();

    public static boolean contains(float f, float f2, float f3) {
        return f >= f2 && f <= f3;
    }

    public static double lerp(double d, double d2, double d3) {
        return d + (d3 * (d2 - d));
    }

    public static float lerp(float f, float f2, float f3) {
        return f + (f3 * (f2 - f));
    }

    public static int lerp(int r1, int r2, float f) {
        return (int) (r1 + (f * (r2 - r1)));
    }

    public static PointF addPoints(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    public static void getPathFromData(ShapeData shapeData, Path path) {
        path.reset();
        PointF initialPoint = shapeData.getInitialPoint();
        path.moveTo(initialPoint.x, initialPoint.y);
        pathFromDataCurrentPoint.set(initialPoint.x, initialPoint.y);
        for (int r0 = 0; r0 < shapeData.getCurves().size(); r0++) {
            CubicCurveData cubicCurveData = shapeData.getCurves().get(r0);
            PointF controlPoint1 = cubicCurveData.getControlPoint1();
            PointF controlPoint2 = cubicCurveData.getControlPoint2();
            PointF vertex = cubicCurveData.getVertex();
            PointF pointF = pathFromDataCurrentPoint;
            if (controlPoint1.equals(pointF) && controlPoint2.equals(vertex)) {
                path.lineTo(vertex.x, vertex.y);
            } else {
                path.cubicTo(controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y, vertex.x, vertex.y);
            }
            pointF.set(vertex.x, vertex.y);
        }
        if (shapeData.isClosed()) {
            path.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int floorMod(float f, float f2) {
        return floorMod((int) f, (int) f2);
    }

    private static int floorMod(int r1, int r2) {
        return r1 - (r2 * floorDiv(r1, r2));
    }

    private static int floorDiv(int r2, int r3) {
        int r0 = r2 / r3;
        return (((r2 ^ r3) >= 0) || r2 % r3 == 0) ? r0 : r0 - 1;
    }

    public static int clamp(int r0, int r1, int r2) {
        return Math.max(r1, Math.min(r2, r0));
    }

    public static float clamp(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f3, f));
    }

    public static double clamp(double d, double d2, double d3) {
        return Math.max(d2, Math.min(d3, d));
    }

    public static void resolveKeyPath(KeyPath keyPath, int r2, List<KeyPath> list, KeyPath keyPath2, KeyPathElementContent keyPathElementContent) {
        if (keyPath.fullyResolvesTo(keyPathElementContent.getName(), r2)) {
            list.add(keyPath2.addKey(keyPathElementContent.getName()).resolve(keyPathElementContent));
        }
    }
}
