package androidx.customview.widget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.SparseArrayCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewParentCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.customview.widget.FocusStrategy;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat {
    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final Rect INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> NODE_ADAPTER = new FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat>() { // from class: androidx.customview.widget.ExploreByTouchHelper.1
        @Override // androidx.customview.widget.FocusStrategy.BoundsAdapter
        public void obtainBounds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Rect rect) {
            accessibilityNodeInfoCompat.getBoundsInParent(rect);
        }
    };
    private static final FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> SPARSE_VALUES_ADAPTER = new FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat>() { // from class: androidx.customview.widget.ExploreByTouchHelper.2
        @Override // androidx.customview.widget.FocusStrategy.CollectionAdapter
        public AccessibilityNodeInfoCompat get(SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat, int r2) {
            return sparseArrayCompat.valueAt(r2);
        }

        @Override // androidx.customview.widget.FocusStrategy.CollectionAdapter
        public int size(SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat) {
            return sparseArrayCompat.size();
        }
    };
    private final View mHost;
    private final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();
    private final int[] mTempGlobalRect = new int[2];
    int mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
    int mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
    private int mHoveredVirtualViewId = Integer.MIN_VALUE;

    private static int keyToDirection(int r1) {
        if (r1 != 19) {
            if (r1 != 21) {
                if (r1 != 22) {
                    return TsExtractor.TS_STREAM_TYPE_HDMV_DTS;
                }
                return 66;
            }
            return 17;
        }
        return 33;
    }

    protected abstract int getVirtualViewAt(float f, float f2);

    protected abstract void getVisibleVirtualViews(List<Integer> list);

    protected abstract boolean onPerformActionForVirtualView(int r1, int r2, Bundle bundle);

    protected void onPopulateEventForHost(AccessibilityEvent accessibilityEvent) {
    }

    protected void onPopulateEventForVirtualView(int r1, AccessibilityEvent accessibilityEvent) {
    }

    protected void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    protected abstract void onPopulateNodeForVirtualView(int r1, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    protected void onVirtualViewKeyboardFocusChanged(int r1, boolean z) {
    }

    public ExploreByTouchHelper(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.mHost = view;
        this.mManager = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (ViewCompat.getImportantForAccessibility(view) == 0) {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new MyNodeProvider();
        }
        return this.mNodeProvider;
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.mManager.isEnabled() && this.mManager.isTouchExplorationEnabled()) {
            int action = motionEvent.getAction();
            if (action == 7 || action == 9) {
                int virtualViewAt = getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
                updateHoveredVirtualView(virtualViewAt);
                return virtualViewAt != Integer.MIN_VALUE;
            } else if (action == 10 && this.mHoveredVirtualViewId != Integer.MIN_VALUE) {
                updateHoveredVirtualView(Integer.MIN_VALUE);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int r1 = 0;
        if (keyEvent.getAction() != 1) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode != 61) {
                if (keyCode != 66) {
                    switch (keyCode) {
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                            if (keyEvent.hasNoModifiers()) {
                                int keyToDirection = keyToDirection(keyCode);
                                int repeatCount = keyEvent.getRepeatCount() + 1;
                                boolean z = false;
                                while (r1 < repeatCount && moveFocus(keyToDirection, null)) {
                                    r1++;
                                    z = true;
                                }
                                return z;
                            }
                            return false;
                        case 23:
                            break;
                        default:
                            return false;
                    }
                }
                if (keyEvent.hasNoModifiers() && keyEvent.getRepeatCount() == 0) {
                    clickKeyboardFocusedVirtualView();
                    return true;
                }
                return false;
            } else if (keyEvent.hasNoModifiers()) {
                return moveFocus(2, null);
            } else {
                if (keyEvent.hasModifiers(1)) {
                    return moveFocus(1, null);
                }
                return false;
            }
        }
        return false;
    }

    public final void onFocusChanged(boolean z, int r4, Rect rect) {
        int r0 = this.mKeyboardFocusedVirtualViewId;
        if (r0 != Integer.MIN_VALUE) {
            clearKeyboardFocusForVirtualView(r0);
        }
        if (z) {
            moveFocus(r4, rect);
        }
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }

    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }

    private void getBoundsInParent(int r1, Rect rect) {
        obtainAccessibilityNodeInfo(r1).getBoundsInParent(rect);
    }

    private boolean moveFocus(int r10, Rect rect) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        SparseArrayCompat<AccessibilityNodeInfoCompat> allNodes = getAllNodes();
        int r0 = this.mKeyboardFocusedVirtualViewId;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = r0 == Integer.MIN_VALUE ? null : allNodes.get(r0);
        if (r10 == 1 || r10 == 2) {
            accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInRelativeDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, accessibilityNodeInfoCompat2, r10, ViewCompat.getLayoutDirection(this.mHost) == 1, false);
        } else if (r10 == 17 || r10 == 33 || r10 == 66 || r10 == 130) {
            Rect rect2 = new Rect();
            int r02 = this.mKeyboardFocusedVirtualViewId;
            if (r02 != Integer.MIN_VALUE) {
                getBoundsInParent(r02, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                guessPreviouslyFocusedRect(this.mHost, r10, rect2);
            }
            accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) FocusStrategy.findNextFocusInAbsoluteDirection(allNodes, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, accessibilityNodeInfoCompat2, rect2, r10);
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return requestKeyboardFocusForVirtualView(accessibilityNodeInfoCompat != null ? allNodes.keyAt(allNodes.indexOfValue(accessibilityNodeInfoCompat)) : Integer.MIN_VALUE);
    }

    private SparseArrayCompat<AccessibilityNodeInfoCompat> getAllNodes() {
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat = new SparseArrayCompat<>();
        for (int r2 = 0; r2 < arrayList.size(); r2++) {
            sparseArrayCompat.put(r2, createNodeForChild(r2));
        }
        return sparseArrayCompat;
    }

    private static Rect guessPreviouslyFocusedRect(View view, int r5, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (r5 == 17) {
            rect.set(width, 0, width, height);
        } else if (r5 == 33) {
            rect.set(0, height, width, height);
        } else if (r5 == 66) {
            rect.set(-1, 0, -1, height);
        } else if (r5 == 130) {
            rect.set(0, -1, width, -1);
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return rect;
    }

    private boolean clickKeyboardFocusedVirtualView() {
        int r0 = this.mKeyboardFocusedVirtualViewId;
        return r0 != Integer.MIN_VALUE && onPerformActionForVirtualView(r0, 16, null);
    }

    public final boolean sendEventForVirtualView(int r3, int r4) {
        ViewParent parent;
        if (r3 == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return false;
        }
        return ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, createEvent(r3, r4));
    }

    public final void invalidateRoot() {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int r2) {
        invalidateVirtualView(r2, 0);
    }

    public final void invalidateVirtualView(int r3, int r4) {
        ViewParent parent;
        if (r3 == Integer.MIN_VALUE || !this.mManager.isEnabled() || (parent = this.mHost.getParent()) == null) {
            return;
        }
        AccessibilityEvent createEvent = createEvent(r3, 2048);
        AccessibilityEventCompat.setContentChangeTypes(createEvent, r4);
        ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, createEvent);
    }

    @Deprecated
    public int getFocusedVirtualView() {
        return getAccessibilityFocusedVirtualViewId();
    }

    private void updateHoveredVirtualView(int r3) {
        int r0 = this.mHoveredVirtualViewId;
        if (r0 == r3) {
            return;
        }
        this.mHoveredVirtualViewId = r3;
        sendEventForVirtualView(r3, 128);
        sendEventForVirtualView(r0, 256);
    }

    private AccessibilityEvent createEvent(int r2, int r3) {
        if (r2 == -1) {
            return createEventForHost(r3);
        }
        return createEventForChild(r2, r3);
    }

    private AccessibilityEvent createEventForHost(int r2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(r2);
        this.mHost.onInitializeAccessibilityEvent(obtain);
        return obtain;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        onPopulateEventForHost(accessibilityEvent);
    }

    private AccessibilityEvent createEventForChild(int r4, int r5) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(r5);
        AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo = obtainAccessibilityNodeInfo(r4);
        obtain.getText().add(obtainAccessibilityNodeInfo.getText());
        obtain.setContentDescription(obtainAccessibilityNodeInfo.getContentDescription());
        obtain.setScrollable(obtainAccessibilityNodeInfo.isScrollable());
        obtain.setPassword(obtainAccessibilityNodeInfo.isPassword());
        obtain.setEnabled(obtainAccessibilityNodeInfo.isEnabled());
        obtain.setChecked(obtainAccessibilityNodeInfo.isChecked());
        onPopulateEventForVirtualView(r4, obtain);
        if (obtain.getText().isEmpty() && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        obtain.setClassName(obtainAccessibilityNodeInfo.getClassName());
        AccessibilityRecordCompat.setSource(obtain, this.mHost, r4);
        obtain.setPackageName(this.mHost.getContext().getPackageName());
        return obtain;
    }

    AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int r2) {
        if (r2 == -1) {
            return createNodeForHost();
        }
        return createNodeForChild(r2);
    }

    private AccessibilityNodeInfoCompat createNodeForHost() {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(this.mHost);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, obtain);
        ArrayList arrayList = new ArrayList();
        getVisibleVirtualViews(arrayList);
        if (obtain.getChildCount() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int r2 = 0; r2 < size; r2++) {
            obtain.addChild(this.mHost, ((Integer) arrayList.get(r2)).intValue());
        }
        return obtain;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        onPopulateNodeForHost(accessibilityNodeInfoCompat);
    }

    private AccessibilityNodeInfoCompat createNodeForChild(int r8) {
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        obtain.setEnabled(true);
        obtain.setFocusable(true);
        obtain.setClassName(DEFAULT_CLASS_NAME);
        Rect rect = INVALID_PARENT_BOUNDS;
        obtain.setBoundsInParent(rect);
        obtain.setBoundsInScreen(rect);
        obtain.setParent(this.mHost);
        onPopulateNodeForVirtualView(r8, obtain);
        if (obtain.getText() == null && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obtain.getBoundsInParent(this.mTempParentRect);
        if (this.mTempParentRect.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int actions = obtain.getActions();
        if ((actions & 64) == 0) {
            if ((actions & 128) != 0) {
                throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            }
            obtain.setPackageName(this.mHost.getContext().getPackageName());
            obtain.setSource(this.mHost, r8);
            if (this.mAccessibilityFocusedVirtualViewId == r8) {
                obtain.setAccessibilityFocused(true);
                obtain.addAction(128);
            } else {
                obtain.setAccessibilityFocused(false);
                obtain.addAction(64);
            }
            boolean z = this.mKeyboardFocusedVirtualViewId == r8;
            if (z) {
                obtain.addAction(2);
            } else if (obtain.isFocusable()) {
                obtain.addAction(1);
            }
            obtain.setFocused(z);
            this.mHost.getLocationOnScreen(this.mTempGlobalRect);
            obtain.getBoundsInScreen(this.mTempScreenRect);
            if (this.mTempScreenRect.equals(rect)) {
                obtain.getBoundsInParent(this.mTempScreenRect);
                if (obtain.mParentVirtualDescendantId != -1) {
                    AccessibilityNodeInfoCompat obtain2 = AccessibilityNodeInfoCompat.obtain();
                    for (int r3 = obtain.mParentVirtualDescendantId; r3 != -1; r3 = obtain2.mParentVirtualDescendantId) {
                        obtain2.setParent(this.mHost, -1);
                        obtain2.setBoundsInParent(INVALID_PARENT_BOUNDS);
                        onPopulateNodeForVirtualView(r3, obtain2);
                        obtain2.getBoundsInParent(this.mTempParentRect);
                        this.mTempScreenRect.offset(this.mTempParentRect.left, this.mTempParentRect.top);
                    }
                    obtain2.recycle();
                }
                this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
            }
            if (this.mHost.getLocalVisibleRect(this.mTempVisibleRect)) {
                this.mTempVisibleRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
                if (this.mTempScreenRect.intersect(this.mTempVisibleRect)) {
                    obtain.setBoundsInScreen(this.mTempScreenRect);
                    if (isVisibleToUser(this.mTempScreenRect)) {
                        obtain.setVisibleToUser(true);
                    }
                }
            }
            return obtain;
        }
        throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
    }

    boolean performAction(int r2, int r3, Bundle bundle) {
        if (r2 == -1) {
            return performActionForHost(r3, bundle);
        }
        return performActionForChild(r2, r3, bundle);
    }

    private boolean performActionForHost(int r2, Bundle bundle) {
        return ViewCompat.performAccessibilityAction(this.mHost, r2, bundle);
    }

    private boolean performActionForChild(int r2, int r3, Bundle bundle) {
        if (r3 != 1) {
            if (r3 != 2) {
                if (r3 != 64) {
                    if (r3 == 128) {
                        return clearAccessibilityFocus(r2);
                    }
                    return onPerformActionForVirtualView(r2, r3, bundle);
                }
                return requestAccessibilityFocus(r2);
            }
            return clearKeyboardFocusForVirtualView(r2);
        }
        return requestKeyboardFocusForVirtualView(r2);
    }

    private boolean isVisibleToUser(Rect rect) {
        if (rect == null || rect.isEmpty() || this.mHost.getWindowVisibility() != 0) {
            return false;
        }
        ViewParent parent = this.mHost.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    private boolean requestAccessibilityFocus(int r3) {
        int r0;
        if (this.mManager.isEnabled() && this.mManager.isTouchExplorationEnabled() && (r0 = this.mAccessibilityFocusedVirtualViewId) != r3) {
            if (r0 != Integer.MIN_VALUE) {
                clearAccessibilityFocus(r0);
            }
            this.mAccessibilityFocusedVirtualViewId = r3;
            this.mHost.invalidate();
            sendEventForVirtualView(r3, 32768);
            return true;
        }
        return false;
    }

    private boolean clearAccessibilityFocus(int r2) {
        if (this.mAccessibilityFocusedVirtualViewId == r2) {
            this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
            this.mHost.invalidate();
            sendEventForVirtualView(r2, 65536);
            return true;
        }
        return false;
    }

    public final boolean requestKeyboardFocusForVirtualView(int r3) {
        int r0;
        if ((this.mHost.isFocused() || this.mHost.requestFocus()) && (r0 = this.mKeyboardFocusedVirtualViewId) != r3) {
            if (r0 != Integer.MIN_VALUE) {
                clearKeyboardFocusForVirtualView(r0);
            }
            this.mKeyboardFocusedVirtualViewId = r3;
            onVirtualViewKeyboardFocusChanged(r3, true);
            sendEventForVirtualView(r3, 8);
            return true;
        }
        return false;
    }

    public final boolean clearKeyboardFocusForVirtualView(int r3) {
        if (this.mKeyboardFocusedVirtualViewId != r3) {
            return false;
        }
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        onVirtualViewKeyboardFocusChanged(r3, false);
        sendEventForVirtualView(r3, 8);
        return true;
    }

    /* loaded from: classes.dex */
    private class MyNodeProvider extends AccessibilityNodeProviderCompat {
        MyNodeProvider() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int r2) {
            return AccessibilityNodeInfoCompat.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(r2));
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public boolean performAction(int r2, int r3, Bundle bundle) {
            return ExploreByTouchHelper.this.performAction(r2, r3, bundle);
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat findFocus(int r2) {
            int r22 = r2 == 2 ? ExploreByTouchHelper.this.mAccessibilityFocusedVirtualViewId : ExploreByTouchHelper.this.mKeyboardFocusedVirtualViewId;
            if (r22 == Integer.MIN_VALUE) {
                return null;
            }
            return createAccessibilityNodeInfo(r22);
        }
    }
}
