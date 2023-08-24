package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public class NestedScrollingParentHelper {
    private int mNestedScrollAxesNonTouch;
    private int mNestedScrollAxesTouch;

    public NestedScrollingParentHelper(ViewGroup viewGroup) {
    }

    public void onNestedScrollAccepted(View view, View view2, int r4) {
        onNestedScrollAccepted(view, view2, r4, 0);
    }

    public void onNestedScrollAccepted(View view, View view2, int r3, int r4) {
        if (r4 == 1) {
            this.mNestedScrollAxesNonTouch = r3;
        } else {
            this.mNestedScrollAxesTouch = r3;
        }
    }

    public int getNestedScrollAxes() {
        return this.mNestedScrollAxesTouch | this.mNestedScrollAxesNonTouch;
    }

    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    public void onStopNestedScroll(View view, int r3) {
        if (r3 == 1) {
            this.mNestedScrollAxesNonTouch = 0;
        } else {
            this.mNestedScrollAxesTouch = 0;
        }
    }
}
