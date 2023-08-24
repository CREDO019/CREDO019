package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaDimension {
    WIDTH(0),
    HEIGHT(1);
    
    private final int mIntValue;

    YogaDimension(int r3) {
        this.mIntValue = r3;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaDimension fromInt(int r3) {
        if (r3 != 0) {
            if (r3 == 1) {
                return HEIGHT;
            }
            throw new IllegalArgumentException("Unknown enum value: " + r3);
        }
        return WIDTH;
    }
}
