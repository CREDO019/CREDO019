package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConstants;
import java.util.Arrays;

/* loaded from: classes.dex */
public class Spacing {
    public static final int ALL = 8;
    public static final int BOTTOM = 3;
    public static final int END = 5;
    public static final int HORIZONTAL = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    public static final int START = 4;
    public static final int TOP = 1;
    public static final int VERTICAL = 7;
    private static final int[] sFlagsMap = {1, 2, 4, 8, 16, 32, 64, 128, 256};
    private final float mDefaultValue;
    private boolean mHasAliasesSet;
    private final float[] mSpacing;
    private int mValueFlags;

    public Spacing() {
        this(0.0f);
    }

    public Spacing(float f) {
        this.mValueFlags = 0;
        this.mDefaultValue = f;
        this.mSpacing = newFullSpacingArray();
    }

    public Spacing(Spacing spacing) {
        this.mValueFlags = 0;
        this.mDefaultValue = spacing.mDefaultValue;
        float[] fArr = spacing.mSpacing;
        this.mSpacing = Arrays.copyOf(fArr, fArr.length);
        this.mValueFlags = spacing.mValueFlags;
        this.mHasAliasesSet = spacing.mHasAliasesSet;
    }

    public boolean set(int r4, float f) {
        boolean z = false;
        if (FloatUtil.floatsEqual(this.mSpacing[r4], f)) {
            return false;
        }
        this.mSpacing[r4] = f;
        if (YogaConstants.isUndefined(f)) {
            this.mValueFlags = (~sFlagsMap[r4]) & this.mValueFlags;
        } else {
            this.mValueFlags = sFlagsMap[r4] | this.mValueFlags;
        }
        int r42 = this.mValueFlags;
        int[] r5 = sFlagsMap;
        this.mHasAliasesSet = ((r5[8] & r42) == 0 && (r5[7] & r42) == 0 && (r42 & r5[6]) == 0) ? true : true;
        return true;
    }

    public float get(int r5) {
        float f = (r5 == 4 || r5 == 5) ? Float.NaN : this.mDefaultValue;
        int r1 = this.mValueFlags;
        if (r1 == 0) {
            return f;
        }
        int[] r2 = sFlagsMap;
        if ((r2[r5] & r1) != 0) {
            return this.mSpacing[r5];
        }
        if (this.mHasAliasesSet) {
            char c = (r5 == 1 || r5 == 3) ? (char) 7 : (char) 6;
            if ((r2[c] & r1) != 0) {
                return this.mSpacing[c];
            }
            if ((r1 & r2[8]) != 0) {
                return this.mSpacing[8];
            }
        }
        return f;
    }

    public float getRaw(int r2) {
        return this.mSpacing[r2];
    }

    public void reset() {
        Arrays.fill(this.mSpacing, Float.NaN);
        this.mHasAliasesSet = false;
        this.mValueFlags = 0;
    }

    float getWithFallback(int r3, int r4) {
        return (this.mValueFlags & sFlagsMap[r3]) != 0 ? this.mSpacing[r3] : get(r4);
    }

    private static float[] newFullSpacingArray() {
        return new float[]{Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN};
    }
}
