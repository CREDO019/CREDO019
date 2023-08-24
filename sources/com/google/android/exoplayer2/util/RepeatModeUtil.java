package com.google.android.exoplayer2.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes2.dex */
public final class RepeatModeUtil {
    public static final int REPEAT_TOGGLE_MODE_ALL = 2;
    public static final int REPEAT_TOGGLE_MODE_NONE = 0;
    public static final int REPEAT_TOGGLE_MODE_ONE = 1;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RepeatToggleModes {
    }

    public static boolean isRepeatModeEnabled(int r3, int r4) {
        if (r3 != 0) {
            return r3 != 1 ? r3 == 2 && (r4 & 2) != 0 : (r4 & 1) != 0;
        }
        return true;
    }

    private RepeatModeUtil() {
    }

    public static int getNextRepeatMode(int r3, int r4) {
        for (int r0 = 1; r0 <= 2; r0++) {
            int r1 = (r3 + r0) % 3;
            if (isRepeatModeEnabled(r1, r4)) {
                return r1;
            }
        }
        return r3;
    }
}
