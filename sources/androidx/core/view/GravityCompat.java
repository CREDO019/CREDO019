package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;

/* loaded from: classes.dex */
public final class GravityCompat {
    public static final int END = 8388613;
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 8388615;
    public static final int RELATIVE_LAYOUT_DIRECTION = 8388608;
    public static final int START = 8388611;

    public static void apply(int r2, int r3, int r4, Rect rect, Rect rect2, int r7) {
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.apply(r2, r3, r4, rect, rect2, r7);
        } else {
            Gravity.apply(r2, r3, r4, rect, rect2);
        }
    }

    public static void apply(int r2, int r3, int r4, Rect rect, int r6, int r7, Rect rect2, int r9) {
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.apply(r2, r3, r4, rect, r6, r7, rect2, r9);
        } else {
            Gravity.apply(r2, r3, r4, rect, r6, r7, rect2);
        }
    }

    public static void applyDisplay(int r2, Rect rect, Rect rect2, int r5) {
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.applyDisplay(r2, rect, rect2, r5);
        } else {
            Gravity.applyDisplay(r2, rect, rect2);
        }
    }

    public static int getAbsoluteGravity(int r2, int r3) {
        return Build.VERSION.SDK_INT >= 17 ? Gravity.getAbsoluteGravity(r2, r3) : r2 & (-8388609);
    }

    private GravityCompat() {
    }
}
