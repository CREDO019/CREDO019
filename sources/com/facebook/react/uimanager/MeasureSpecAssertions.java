package com.facebook.react.uimanager;

import android.view.View;

/* loaded from: classes.dex */
public class MeasureSpecAssertions {
    public static final void assertExplicitMeasureSpec(int r0, int r1) {
        int mode = View.MeasureSpec.getMode(r0);
        int mode2 = View.MeasureSpec.getMode(r1);
        if (mode == 0 || mode2 == 0) {
            throw new IllegalStateException("A catalyst view must have an explicit width and height given to it. This should normally happen as part of the standard catalyst UI framework.");
        }
    }
}
