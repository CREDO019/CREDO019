package androidx.core.view;

import android.view.View;
import android.view.ViewParent;

/* loaded from: classes.dex */
public class NestedScrollingChildHelper {
    private boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParentNonTouch;
    private ViewParent mNestedScrollingParentTouch;
    private int[] mTempNestedScrollConsumed;
    private final View mView;

    public NestedScrollingChildHelper(View view) {
        this.mView = view;
    }

    public void setNestedScrollingEnabled(boolean z) {
        if (this.mIsNestedScrollingEnabled) {
            ViewCompat.stopNestedScroll(this.mView);
        }
        this.mIsNestedScrollingEnabled = z;
    }

    public boolean isNestedScrollingEnabled() {
        return this.mIsNestedScrollingEnabled;
    }

    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public boolean hasNestedScrollingParent(int r1) {
        return getNestedScrollingParentForType(r1) != null;
    }

    public boolean startNestedScroll(int r2) {
        return startNestedScroll(r2, 0);
    }

    public boolean startNestedScroll(int r5, int r6) {
        if (hasNestedScrollingParent(r6)) {
            return true;
        }
        if (isNestedScrollingEnabled()) {
            View view = this.mView;
            for (ViewParent parent = this.mView.getParent(); parent != null; parent = parent.getParent()) {
                if (ViewParentCompat.onStartNestedScroll(parent, view, this.mView, r5, r6)) {
                    setNestedScrollingParentForType(r6, parent);
                    ViewParentCompat.onNestedScrollAccepted(parent, view, this.mView, r5, r6);
                    return true;
                }
                if (parent instanceof View) {
                    view = (View) parent;
                }
            }
            return false;
        }
        return false;
    }

    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public void stopNestedScroll(int r3) {
        ViewParent nestedScrollingParentForType = getNestedScrollingParentForType(r3);
        if (nestedScrollingParentForType != null) {
            ViewParentCompat.onStopNestedScroll(nestedScrollingParentForType, this.mView, r3);
            setNestedScrollingParentForType(r3, null);
        }
    }

    public boolean dispatchNestedScroll(int r9, int r10, int r11, int r12, int[] r13) {
        return dispatchNestedScrollInternal(r9, r10, r11, r12, r13, 0, null);
    }

    public boolean dispatchNestedScroll(int r9, int r10, int r11, int r12, int[] r13, int r14) {
        return dispatchNestedScrollInternal(r9, r10, r11, r12, r13, r14, null);
    }

    public void dispatchNestedScroll(int r1, int r2, int r3, int r4, int[] r5, int r6, int[] r7) {
        dispatchNestedScrollInternal(r1, r2, r3, r4, r5, r6, r7);
    }

    private boolean dispatchNestedScrollInternal(int r16, int r17, int r18, int r19, int[] r20, int r21, int[] r22) {
        ViewParent nestedScrollingParentForType;
        int r13;
        int r14;
        int[] r11;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(r21)) == null) {
            return false;
        }
        if (r16 == 0 && r17 == 0 && r18 == 0 && r19 == 0) {
            if (r20 != null) {
                r20[0] = 0;
                r20[1] = 0;
            }
            return false;
        }
        if (r20 != null) {
            this.mView.getLocationInWindow(r20);
            r13 = r20[0];
            r14 = r20[1];
        } else {
            r13 = 0;
            r14 = 0;
        }
        if (r22 == null) {
            int[] tempNestedScrollConsumed = getTempNestedScrollConsumed();
            tempNestedScrollConsumed[0] = 0;
            tempNestedScrollConsumed[1] = 0;
            r11 = tempNestedScrollConsumed;
        } else {
            r11 = r22;
        }
        ViewParentCompat.onNestedScroll(nestedScrollingParentForType, this.mView, r16, r17, r18, r19, r21, r11);
        if (r20 != null) {
            this.mView.getLocationInWindow(r20);
            r20[0] = r20[0] - r13;
            r20[1] = r20[1] - r14;
        }
        return true;
    }

    public boolean dispatchNestedPreScroll(int r7, int r8, int[] r9, int[] r10) {
        return dispatchNestedPreScroll(r7, r8, r9, r10, 0);
    }

    public boolean dispatchNestedPreScroll(int r11, int r12, int[] r13, int[] r14, int r15) {
        ViewParent nestedScrollingParentForType;
        int r8;
        int r9;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(r15)) == null) {
            return false;
        }
        if (r11 == 0 && r12 == 0) {
            if (r14 != null) {
                r14[0] = 0;
                r14[1] = 0;
                return false;
            }
            return false;
        }
        if (r14 != null) {
            this.mView.getLocationInWindow(r14);
            r8 = r14[0];
            r9 = r14[1];
        } else {
            r8 = 0;
            r9 = 0;
        }
        if (r13 == null) {
            r13 = getTempNestedScrollConsumed();
        }
        r13[0] = 0;
        r13[1] = 0;
        ViewParentCompat.onNestedPreScroll(nestedScrollingParentForType, this.mView, r11, r12, r13, r15);
        if (r14 != null) {
            this.mView.getLocationInWindow(r14);
            r14[0] = r14[0] - r8;
            r14[1] = r14[1] - r9;
        }
        return (r13[0] == 0 && r13[1] == 0) ? false : true;
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        ViewParent nestedScrollingParentForType;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedFling(nestedScrollingParentForType, this.mView, f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        ViewParent nestedScrollingParentForType;
        if (!isNestedScrollingEnabled() || (nestedScrollingParentForType = getNestedScrollingParentForType(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedPreFling(nestedScrollingParentForType, this.mView, f, f2);
    }

    public void onDetachedFromWindow() {
        ViewCompat.stopNestedScroll(this.mView);
    }

    public void onStopNestedScroll(View view) {
        ViewCompat.stopNestedScroll(this.mView);
    }

    private ViewParent getNestedScrollingParentForType(int r2) {
        if (r2 != 0) {
            if (r2 != 1) {
                return null;
            }
            return this.mNestedScrollingParentNonTouch;
        }
        return this.mNestedScrollingParentTouch;
    }

    private void setNestedScrollingParentForType(int r2, ViewParent viewParent) {
        if (r2 == 0) {
            this.mNestedScrollingParentTouch = viewParent;
        } else if (r2 != 1) {
        } else {
            this.mNestedScrollingParentNonTouch = viewParent;
        }
    }

    private int[] getTempNestedScrollConsumed() {
        if (this.mTempNestedScrollConsumed == null) {
            this.mTempNestedScrollConsumed = new int[2];
        }
        return this.mTempNestedScrollConsumed;
    }
}
