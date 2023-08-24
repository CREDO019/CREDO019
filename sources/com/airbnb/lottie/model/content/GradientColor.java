package com.airbnb.lottie.model.content;

import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class GradientColor {
    private final int[] colors;
    private final float[] positions;

    public GradientColor(float[] fArr, int[] r2) {
        this.positions = fArr;
        this.colors = r2;
    }

    public float[] getPositions() {
        return this.positions;
    }

    public int[] getColors() {
        return this.colors;
    }

    public int getSize() {
        return this.colors.length;
    }

    public void lerp(GradientColor gradientColor, GradientColor gradientColor2, float f) {
        if (gradientColor.colors.length != gradientColor2.colors.length) {
            throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + gradientColor.colors.length + " vs " + gradientColor2.colors.length + ")");
        }
        for (int r0 = 0; r0 < gradientColor.colors.length; r0++) {
            this.positions[r0] = MiscUtils.lerp(gradientColor.positions[r0], gradientColor2.positions[r0], f);
            this.colors[r0] = GammaEvaluator.evaluate(f, gradientColor.colors[r0], gradientColor2.colors[r0]);
        }
    }
}
