package androidx.core.view;

import android.view.VelocityTracker;

@Deprecated
/* loaded from: classes.dex */
public final class VelocityTrackerCompat {
    @Deprecated
    public static float getXVelocity(VelocityTracker velocityTracker, int r1) {
        return velocityTracker.getXVelocity(r1);
    }

    @Deprecated
    public static float getYVelocity(VelocityTracker velocityTracker, int r1) {
        return velocityTracker.getYVelocity(r1);
    }

    private VelocityTrackerCompat() {
    }
}
