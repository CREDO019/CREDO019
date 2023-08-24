package androidx.core.view.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

/* loaded from: classes.dex */
class PathInterpolatorApi14 implements Interpolator {
    private static final float PRECISION = 0.002f;

    /* renamed from: mX */
    private final float[] f35mX;

    /* renamed from: mY */
    private final float[] f36mY;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PathInterpolatorApi14(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int r2 = ((int) (length / PRECISION)) + 1;
        this.f35mX = new float[r2];
        this.f36mY = new float[r2];
        float[] fArr = new float[2];
        for (int r5 = 0; r5 < r2; r5++) {
            pathMeasure.getPosTan((r5 * length) / (r2 - 1), fArr, null);
            this.f35mX[r5] = fArr[0];
            this.f36mY[r5] = fArr[1];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PathInterpolatorApi14(float f, float f2) {
        this(createQuad(f, f2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PathInterpolatorApi14(float f, float f2, float f3, float f4) {
        this(createCubic(f, f2, f3, f4));
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int r1 = 0;
        int length = this.f35mX.length - 1;
        while (length - r1 > 1) {
            int r4 = (r1 + length) / 2;
            if (f < this.f35mX[r4]) {
                length = r4;
            } else {
                r1 = r4;
            }
        }
        float[] fArr = this.f35mX;
        float f2 = fArr[length] - fArr[r1];
        if (f2 == 0.0f) {
            return this.f36mY[r1];
        }
        float[] fArr2 = this.f36mY;
        float f3 = fArr2[r1];
        return f3 + (((f - fArr[r1]) / f2) * (fArr2[length] - f3));
    }

    private static Path createQuad(float f, float f2) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f, f2, 1.0f, 1.0f);
        return path;
    }

    private static Path createCubic(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        return path;
    }
}
