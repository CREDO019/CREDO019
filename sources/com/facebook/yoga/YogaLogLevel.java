package com.facebook.yoga;

/* loaded from: classes.dex */
public enum YogaLogLevel {
    ERROR(0),
    WARN(1),
    INFO(2),
    DEBUG(3),
    VERBOSE(4),
    FATAL(5);
    
    private final int mIntValue;

    YogaLogLevel(int r3) {
        this.mIntValue = r3;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaLogLevel fromInt(int r3) {
        if (r3 != 0) {
            if (r3 != 1) {
                if (r3 != 2) {
                    if (r3 != 3) {
                        if (r3 != 4) {
                            if (r3 == 5) {
                                return FATAL;
                            }
                            throw new IllegalArgumentException("Unknown enum value: " + r3);
                        }
                        return VERBOSE;
                    }
                    return DEBUG;
                }
                return INFO;
            }
            return WARN;
        }
        return ERROR;
    }
}
