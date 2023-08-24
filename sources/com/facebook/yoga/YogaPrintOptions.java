package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaPrintOptions {
    LAYOUT(1),
    STYLE(2),
    CHILDREN(4);
    
    private final int mIntValue;

    YogaPrintOptions(int r3) {
        this.mIntValue = r3;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaPrintOptions fromInt(int r3) {
        if (r3 != 1) {
            if (r3 != 2) {
                if (r3 == 4) {
                    return CHILDREN;
                }
                throw new IllegalArgumentException("Unknown enum value: " + r3);
            }
            return STYLE;
        }
        return LAYOUT;
    }
}
