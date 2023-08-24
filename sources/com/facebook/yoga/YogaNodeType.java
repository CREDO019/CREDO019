package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaNodeType {
    DEFAULT(0),
    TEXT(1);
    
    private final int mIntValue;

    YogaNodeType(int r3) {
        this.mIntValue = r3;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaNodeType fromInt(int r3) {
        if (r3 != 0) {
            if (r3 == 1) {
                return TEXT;
            }
            throw new IllegalArgumentException("Unknown enum value: " + r3);
        }
        return DEFAULT;
    }
}
