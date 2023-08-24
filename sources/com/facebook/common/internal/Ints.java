package com.facebook.common.internal;

/* loaded from: classes.dex */
public class Ints {
    private Ints() {
    }

    public static int max(int... array) {
        Preconditions.checkArgument(Boolean.valueOf(array.length > 0));
        int r0 = array[0];
        for (int r2 = 1; r2 < array.length; r2++) {
            if (array[r2] > r0) {
                r0 = array[r2];
            }
        }
        return r0;
    }
}
