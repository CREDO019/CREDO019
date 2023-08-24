package com.google.maps.android.heatmaps;

import android.graphics.Color;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class Gradient {
    private static final int DEFAULT_COLOR_MAP_SIZE = 1000;
    public final int mColorMapSize;
    public int[] mColors;
    public float[] mStartPoints;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class ColorInterval {
        private final int color1;
        private final int color2;
        private final float duration;

        private ColorInterval(int r2, int r3, float f) {
            this.color1 = r2;
            this.color2 = r3;
            this.duration = f;
        }
    }

    public Gradient(int[] r2, float[] fArr) {
        this(r2, fArr, 1000);
    }

    public Gradient(int[] r4, float[] fArr, int r6) {
        if (r4.length != fArr.length) {
            throw new IllegalArgumentException("colors and startPoints should be same length");
        }
        if (r4.length == 0) {
            throw new IllegalArgumentException("No colors have been defined");
        }
        for (int r0 = 1; r0 < fArr.length; r0++) {
            if (fArr[r0] <= fArr[r0 - 1]) {
                throw new IllegalArgumentException("startPoints should be in increasing order");
            }
        }
        this.mColorMapSize = r6;
        int[] r62 = new int[r4.length];
        this.mColors = r62;
        this.mStartPoints = new float[fArr.length];
        System.arraycopy(r4, 0, r62, 0, r4.length);
        System.arraycopy(fArr, 0, this.mStartPoints, 0, fArr.length);
    }

    private HashMap<Integer, ColorInterval> generateColorIntervals() {
        HashMap<Integer, ColorInterval> hashMap = new HashMap<>();
        if (this.mStartPoints[0] != 0.0f) {
            hashMap.put(0, new ColorInterval(Color.argb(0, Color.red(this.mColors[0]), Color.green(this.mColors[0]), Color.blue(this.mColors[0])), this.mColors[0], this.mColorMapSize * this.mStartPoints[0]));
        }
        for (int r2 = 1; r2 < this.mColors.length; r2++) {
            int r5 = r2 - 1;
            Integer valueOf = Integer.valueOf((int) (this.mColorMapSize * this.mStartPoints[r5]));
            int[] r6 = this.mColors;
            int r8 = r6[r5];
            int r9 = r6[r2];
            float[] fArr = this.mStartPoints;
            hashMap.put(valueOf, new ColorInterval(r8, r9, (fArr[r2] - fArr[r5]) * this.mColorMapSize));
        }
        float[] fArr2 = this.mStartPoints;
        if (fArr2[fArr2.length - 1] != 1.0f) {
            int length = fArr2.length - 1;
            Integer valueOf2 = Integer.valueOf((int) (this.mColorMapSize * fArr2[length]));
            int[] r52 = this.mColors;
            hashMap.put(valueOf2, new ColorInterval(r52[length], r52[length], this.mColorMapSize * (1.0f - this.mStartPoints[length])));
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] generateColorMap(double d) {
        HashMap<Integer, ColorInterval> generateColorIntervals = generateColorIntervals();
        int[] r1 = new int[this.mColorMapSize];
        ColorInterval colorInterval = generateColorIntervals.get(0);
        int r5 = 0;
        for (int r4 = 0; r4 < this.mColorMapSize; r4++) {
            if (generateColorIntervals.containsKey(Integer.valueOf(r4))) {
                colorInterval = generateColorIntervals.get(Integer.valueOf(r4));
                r5 = r4;
            }
            r1[r4] = interpolateColor(colorInterval.color1, colorInterval.color2, (r4 - r5) / colorInterval.duration);
        }
        if (d != 1.0d) {
            for (int r2 = 0; r2 < this.mColorMapSize; r2++) {
                int r0 = r1[r2];
                r1[r2] = Color.argb((int) (Color.alpha(r0) * d), Color.red(r0), Color.green(r0), Color.blue(r0));
            }
        }
        return r1;
    }

    static int interpolateColor(int r7, int r8, float f) {
        int alpha = (int) (((Color.alpha(r8) - Color.alpha(r7)) * f) + Color.alpha(r7));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(r7), Color.green(r7), Color.blue(r7), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(r8), Color.green(r8), Color.blue(r8), fArr2);
        if (fArr[0] - fArr2[0] > 180.0f) {
            fArr2[0] = fArr2[0] + 360.0f;
        } else if (fArr2[0] - fArr[0] > 180.0f) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr3 = new float[3];
        for (int r82 = 0; r82 < 3; r82++) {
            fArr3[r82] = ((fArr2[r82] - fArr[r82]) * f) + fArr[r82];
        }
        return Color.HSVToColor(alpha, fArr3);
    }
}
