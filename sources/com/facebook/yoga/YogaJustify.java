package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaJustify {
    FLEX_START(0),
    CENTER(1),
    FLEX_END(2),
    SPACE_BETWEEN(3),
    SPACE_AROUND(4),
    SPACE_EVENLY(5);
    
    private final int mIntValue;

    YogaJustify(int r3) {
        this.mIntValue = r3;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaJustify fromInt(int r3) {
        if (r3 != 0) {
            if (r3 != 1) {
                if (r3 != 2) {
                    if (r3 != 3) {
                        if (r3 != 4) {
                            if (r3 == 5) {
                                return SPACE_EVENLY;
                            }
                            throw new IllegalArgumentException("Unknown enum value: " + r3);
                        }
                        return SPACE_AROUND;
                    }
                    return SPACE_BETWEEN;
                }
                return FLEX_END;
            }
            return CENTER;
        }
        return FLEX_START;
    }
}
