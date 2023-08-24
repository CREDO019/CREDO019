package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaDirection {
    INHERIT(0),
    LTR(1),
    RTL(2);
    
    private final int mIntValue;

    YogaDirection(int r3) {
        this.mIntValue = r3;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaDirection fromInt(int r3) {
        if (r3 != 0) {
            if (r3 != 1) {
                if (r3 == 2) {
                    return RTL;
                }
                throw new IllegalArgumentException("Unknown enum value: " + r3);
            }
            return LTR;
        }
        return INHERIT;
    }
}
