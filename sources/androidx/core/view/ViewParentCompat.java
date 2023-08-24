package androidx.core.view;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: classes.dex */
public final class ViewParentCompat {
    private static final String TAG = "ViewParentCompat";
    private static int[] sTempNestedScrollConsumed;

    private ViewParentCompat() {
    }

    @Deprecated
    public static boolean requestSendAccessibilityEvent(ViewParent viewParent, View view, AccessibilityEvent accessibilityEvent) {
        return viewParent.requestSendAccessibilityEvent(view, accessibilityEvent);
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int r4) {
        return onStartNestedScroll(viewParent, view, view2, r4, 0);
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int r4) {
        onNestedScrollAccepted(viewParent, view, view2, r4, 0);
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view) {
        onStopNestedScroll(viewParent, view, 0);
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int r10, int r11, int r12, int r13) {
        onNestedScroll(viewParent, view, r10, r11, r12, r13, 0, getTempNestedScrollConsumed());
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int r10, int r11, int r12, int r13, int r14) {
        onNestedScroll(viewParent, view, r10, r11, r12, r13, r14, getTempNestedScrollConsumed());
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int r8, int r9, int[] r10) {
        onNestedPreScroll(viewParent, view, r8, r9, r10, 0);
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int r4, int r5) {
        if (viewParent instanceof NestedScrollingParent2) {
            return ((NestedScrollingParent2) viewParent).onStartNestedScroll(view, view2, r4, r5);
        }
        if (r5 == 0) {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    return viewParent.onStartNestedScroll(view, view2, r4);
                } catch (AbstractMethodError e) {
                    Log.e(TAG, "ViewParent " + viewParent + " does not implement interface method onStartNestedScroll", e);
                    return false;
                }
            } else if (viewParent instanceof NestedScrollingParent) {
                return ((NestedScrollingParent) viewParent).onStartNestedScroll(view, view2, r4);
            } else {
                return false;
            }
        }
        return false;
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int r4, int r5) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedScrollAccepted(view, view2, r4, r5);
        } else if (r5 == 0) {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedScrollAccepted(view, view2, r4);
                } catch (AbstractMethodError e) {
                    Log.e(TAG, "ViewParent " + viewParent + " does not implement interface method onNestedScrollAccepted", e);
                }
            } else if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedScrollAccepted(view, view2, r4);
            }
        }
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view, int r3) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onStopNestedScroll(view, r3);
        } else if (r3 == 0) {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onStopNestedScroll(view);
                } catch (AbstractMethodError e) {
                    Log.e(TAG, "ViewParent " + viewParent + " does not implement interface method onStopNestedScroll", e);
                }
            } else if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onStopNestedScroll(view);
            }
        }
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int r12, int r13, int r14, int r15, int r16, int[] r17) {
        if (viewParent instanceof NestedScrollingParent3) {
            ((NestedScrollingParent3) viewParent).onNestedScroll(view, r12, r13, r14, r15, r16, r17);
            return;
        }
        r17[0] = r17[0] + r14;
        r17[1] = r17[1] + r15;
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedScroll(view, r12, r13, r14, r15, r16);
        } else if (r16 == 0) {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedScroll(view, r12, r13, r14, r15);
                } catch (AbstractMethodError e) {
                    Log.e(TAG, "ViewParent " + viewParent + " does not implement interface method onNestedScroll", e);
                }
            } else if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedScroll(view, r12, r13, r14, r15);
            }
        }
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int r9, int r10, int[] r11, int r12) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedPreScroll(view, r9, r10, r11, r12);
        } else if (r12 == 0) {
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    viewParent.onNestedPreScroll(view, r9, r10, r11);
                } catch (AbstractMethodError e) {
                    Log.e(TAG, "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e);
                }
            } else if (viewParent instanceof NestedScrollingParent) {
                ((NestedScrollingParent) viewParent).onNestedPreScroll(view, r9, r10, r11);
            }
        }
    }

    public static boolean onNestedFling(ViewParent viewParent, View view, float f, float f2, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return viewParent.onNestedFling(view, f, f2, z);
            } catch (AbstractMethodError e) {
                Log.e(TAG, "ViewParent " + viewParent + " does not implement interface method onNestedFling", e);
                return false;
            }
        } else if (viewParent instanceof NestedScrollingParent) {
            return ((NestedScrollingParent) viewParent).onNestedFling(view, f, f2, z);
        } else {
            return false;
        }
    }

    public static boolean onNestedPreFling(ViewParent viewParent, View view, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return viewParent.onNestedPreFling(view, f, f2);
            } catch (AbstractMethodError e) {
                Log.e(TAG, "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e);
                return false;
            }
        } else if (viewParent instanceof NestedScrollingParent) {
            return ((NestedScrollingParent) viewParent).onNestedPreFling(view, f, f2);
        } else {
            return false;
        }
    }

    public static void notifySubtreeAccessibilityStateChanged(ViewParent viewParent, View view, View view2, int r5) {
        if (Build.VERSION.SDK_INT >= 19) {
            viewParent.notifySubtreeAccessibilityStateChanged(view, view2, r5);
        }
    }

    private static int[] getTempNestedScrollConsumed() {
        int[] r0 = sTempNestedScrollConsumed;
        if (r0 == null) {
            sTempNestedScrollConsumed = new int[2];
        } else {
            r0[0] = 0;
            r0[1] = 0;
        }
        return sTempNestedScrollConsumed;
    }
}
