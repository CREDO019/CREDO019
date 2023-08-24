package com.facebook.react.common;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class ArrayUtils {
    public static float[] copyArray(float[] fArr) {
        if (fArr == null) {
            return null;
        }
        return Arrays.copyOf(fArr, fArr.length);
    }

    public static int[] copyListToArray(List<Integer> list) {
        int[] r0 = new int[list.size()];
        for (int r1 = 0; r1 < list.size(); r1++) {
            r0[r1] = list.get(r1).intValue();
        }
        return r0;
    }
}
