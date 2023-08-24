package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaDisplay {
    FLEX(0),
    NONE(1);
    
    private final int mIntValue;

    YogaDisplay(int r3) {
        this.mIntValue = r3;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaDisplay fromInt(int r3) {
        if (r3 != 0) {
            if (r3 == 1) {
                return NONE;
            }
            throw new IllegalArgumentException("Unknown enum value: " + r3);
        }
        return FLEX;
    }
}
