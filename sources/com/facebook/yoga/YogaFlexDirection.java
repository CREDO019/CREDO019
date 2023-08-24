package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaFlexDirection {
    COLUMN(0),
    COLUMN_REVERSE(1),
    ROW(2),
    ROW_REVERSE(3);
    
    private final int mIntValue;

    YogaFlexDirection(int r3) {
        this.mIntValue = r3;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaFlexDirection fromInt(int r3) {
        if (r3 != 0) {
            if (r3 != 1) {
                if (r3 != 2) {
                    if (r3 == 3) {
                        return ROW_REVERSE;
                    }
                    throw new IllegalArgumentException("Unknown enum value: " + r3);
                }
                return ROW;
            }
            return COLUMN_REVERSE;
        }
        return COLUMN;
    }
}
