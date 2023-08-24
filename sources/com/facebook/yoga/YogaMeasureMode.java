package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaMeasureMode {
    UNDEFINED(0),
    EXACTLY(1),
    AT_MOST(2);
    
    private final int mIntValue;

    YogaMeasureMode(int r3) {
        this.mIntValue = r3;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaMeasureMode fromInt(int r3) {
        if (r3 != 0) {
            if (r3 != 1) {
                if (r3 == 2) {
                    return AT_MOST;
                }
                throw new IllegalArgumentException("Unknown enum value: " + r3);
            }
            return EXACTLY;
        }
        return UNDEFINED;
    }
}
