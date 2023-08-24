package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaLayoutType {
    LAYOUT(0),
    MEASURE(1),
    CACHED_LAYOUT(2),
    CACHED_MEASURE(3);
    
    private final int mIntValue;

    YogaLayoutType(int r3) {
        this.mIntValue = r3;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaLayoutType fromInt(int r3) {
        if (r3 != 0) {
            if (r3 != 1) {
                if (r3 != 2) {
                    if (r3 == 3) {
                        return CACHED_MEASURE;
                    }
                    throw new IllegalArgumentException("Unknown enum value: " + r3);
                }
                return CACHED_LAYOUT;
            }
            return MEASURE;
        }
        return LAYOUT;
    }
}
