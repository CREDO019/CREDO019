package com.facebook.react.uimanager;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.TouchTargetHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.PointerEvent;
import com.facebook.react.uimanager.events.PointerEventHelper;
import com.facebook.react.uimanager.events.TouchEventCoalescingKeyHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class JSPointerDispatcher {
    private static final float ONMOVE_EPSILON = 0.1f;
    private final ViewGroup mRootViewGroup;
    private final float[] mTargetCoordinates = new float[2];
    private int mChildHandlingNativeGesture = -1;
    private long mDownStartTime = Long.MIN_VALUE;
    private final TouchEventCoalescingKeyHelper mTouchEventCoalescingKeyHelper = new TouchEventCoalescingKeyHelper();
    private long mHoverInteractionKey = Long.MIN_VALUE;
    private List<TouchTargetHelper.ViewTarget> mLastHitPath = Collections.emptyList();
    private final float[] mLastEventCoordinates = new float[2];

    public JSPointerDispatcher(ViewGroup viewGroup) {
        this.mRootViewGroup = viewGroup;
    }

    public void onChildStartedNativeGesture(View view, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        if (this.mChildHandlingNativeGesture != -1 || view == null) {
            return;
        }
        dispatchCancelEvent(TouchTargetHelper.findTargetPathAndCoordinatesForTouch(motionEvent.getX(), motionEvent.getY(), this.mRootViewGroup, this.mTargetCoordinates), motionEvent, eventDispatcher);
        this.mChildHandlingNativeGesture = view.getId();
    }

    public void onChildEndedNativeGesture() {
        this.mChildHandlingNativeGesture = -1;
    }

    public void handleMotionEvent(MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        boolean supportsHover = PointerEventHelper.supportsHover(motionEvent);
        int surfaceId = UIManagerHelper.getSurfaceId(this.mRootViewGroup);
        int actionMasked = motionEvent.getActionMasked();
        List<TouchTargetHelper.ViewTarget> findTargetPathAndCoordinatesForTouch = TouchTargetHelper.findTargetPathAndCoordinatesForTouch(motionEvent.getX(), motionEvent.getY(), this.mRootViewGroup, this.mTargetCoordinates);
        if (findTargetPathAndCoordinatesForTouch.isEmpty()) {
            return;
        }
        int viewId = findTargetPathAndCoordinatesForTouch.get(0).getViewId();
        if (supportsHover) {
            if (actionMasked == 7) {
                handleHoverEvent(motionEvent, eventDispatcher, surfaceId, findTargetPathAndCoordinatesForTouch);
                return;
            } else if (actionMasked == 10 || actionMasked == 9) {
                return;
            }
        }
        if (actionMasked == 0) {
            this.mChildHandlingNativeGesture = -1;
            long eventTime = motionEvent.getEventTime();
            this.mDownStartTime = eventTime;
            this.mTouchEventCoalescingKeyHelper.addCoalescingKey(eventTime);
            if (!supportsHover) {
                List<TouchTargetHelper.ViewTarget> filterByShouldDispatch = filterByShouldDispatch(findTargetPathAndCoordinatesForTouch, PointerEventHelper.EVENT.ENTER, PointerEventHelper.EVENT.ENTER_CAPTURE, false);
                Collections.reverse(filterByShouldDispatch);
                dispatchEventForViewTargets(PointerEventHelper.POINTER_ENTER, filterByShouldDispatch, eventDispatcher, surfaceId, motionEvent);
            }
            if (isAnyoneListeningForBubblingEvent(findTargetPathAndCoordinatesForTouch, PointerEventHelper.EVENT.DOWN, PointerEventHelper.EVENT.DOWN_CAPTURE)) {
                eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_DOWN, surfaceId, viewId, motionEvent, this.mTargetCoordinates));
            }
        } else if (this.mChildHandlingNativeGesture != -1) {
        } else {
            if (actionMasked == 5) {
                this.mTouchEventCoalescingKeyHelper.incrementCoalescingKey(this.mDownStartTime);
                if (isAnyoneListeningForBubblingEvent(findTargetPathAndCoordinatesForTouch, PointerEventHelper.EVENT.DOWN, PointerEventHelper.EVENT.DOWN_CAPTURE)) {
                    eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_DOWN, surfaceId, viewId, motionEvent, this.mTargetCoordinates));
                }
            } else if (actionMasked == 2) {
                short coalescingKey = this.mTouchEventCoalescingKeyHelper.getCoalescingKey(this.mDownStartTime);
                if (isAnyoneListeningForBubblingEvent(findTargetPathAndCoordinatesForTouch, PointerEventHelper.EVENT.MOVE, PointerEventHelper.EVENT.MOVE_CAPTURE)) {
                    eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_MOVE, surfaceId, viewId, motionEvent, this.mTargetCoordinates, coalescingKey));
                }
            } else if (actionMasked == 6) {
                this.mTouchEventCoalescingKeyHelper.incrementCoalescingKey(this.mDownStartTime);
                if (isAnyoneListeningForBubblingEvent(findTargetPathAndCoordinatesForTouch, PointerEventHelper.EVENT.UP, PointerEventHelper.EVENT.UP_CAPTURE)) {
                    eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_UP, surfaceId, viewId, motionEvent, this.mTargetCoordinates));
                }
            } else if (actionMasked == 1) {
                this.mTouchEventCoalescingKeyHelper.removeCoalescingKey(this.mDownStartTime);
                this.mDownStartTime = Long.MIN_VALUE;
                if (isAnyoneListeningForBubblingEvent(findTargetPathAndCoordinatesForTouch, PointerEventHelper.EVENT.UP, PointerEventHelper.EVENT.UP_CAPTURE)) {
                    eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_UP, surfaceId, viewId, motionEvent, this.mTargetCoordinates));
                }
                if (supportsHover) {
                    return;
                }
                dispatchEventForViewTargets(PointerEventHelper.POINTER_LEAVE, filterByShouldDispatch(findTargetPathAndCoordinatesForTouch, PointerEventHelper.EVENT.LEAVE, PointerEventHelper.EVENT.LEAVE_CAPTURE, false), eventDispatcher, surfaceId, motionEvent);
            } else if (actionMasked == 3) {
                dispatchCancelEvent(findTargetPathAndCoordinatesForTouch, motionEvent, eventDispatcher);
            } else {
                FLog.m1288w(ReactConstants.TAG, "Warning : Motion Event was ignored. Action=" + actionMasked + " Target=" + viewId + " Supports Hover=" + supportsHover);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean isAnyoneListeningForBubblingEvent(java.util.List<com.facebook.react.uimanager.TouchTargetHelper.ViewTarget> r2, com.facebook.react.uimanager.events.PointerEventHelper.EVENT r3, com.facebook.react.uimanager.events.PointerEventHelper.EVENT r4) {
        /*
            java.util.Iterator r2 = r2.iterator()
        L4:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L26
            java.lang.Object r0 = r2.next()
            com.facebook.react.uimanager.TouchTargetHelper$ViewTarget r0 = (com.facebook.react.uimanager.TouchTargetHelper.ViewTarget) r0
            android.view.View r1 = r0.getView()
            boolean r1 = com.facebook.react.uimanager.events.PointerEventHelper.isListening(r1, r3)
            if (r1 != 0) goto L24
            android.view.View r0 = r0.getView()
            boolean r0 = com.facebook.react.uimanager.events.PointerEventHelper.isListening(r0, r4)
            if (r0 == 0) goto L4
        L24:
            r2 = 1
            return r2
        L26:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.JSPointerDispatcher.isAnyoneListeningForBubblingEvent(java.util.List, com.facebook.react.uimanager.events.PointerEventHelper$EVENT, com.facebook.react.uimanager.events.PointerEventHelper$EVENT):boolean");
    }

    private static List<TouchTargetHelper.ViewTarget> filterByShouldDispatch(List<TouchTargetHelper.ViewTarget> list, PointerEventHelper.EVENT event, PointerEventHelper.EVENT event2, boolean z) {
        ArrayList arrayList = new ArrayList(list);
        if (z) {
            return arrayList;
        }
        boolean z2 = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            View view = list.get(size).getView();
            if (!z2 && !PointerEventHelper.isListening(view, event2) && !PointerEventHelper.isListening(view, event)) {
                arrayList.remove(size);
            } else if (!z2 && PointerEventHelper.isListening(view, event2)) {
                z2 = true;
            }
        }
        return arrayList;
    }

    private void dispatchEventForViewTargets(String str, List<TouchTargetHelper.ViewTarget> list, EventDispatcher eventDispatcher, int r6, MotionEvent motionEvent) {
        for (TouchTargetHelper.ViewTarget viewTarget : list) {
            eventDispatcher.dispatchEvent(PointerEvent.obtain(str, r6, viewTarget.getViewId(), motionEvent, this.mTargetCoordinates));
        }
    }

    private void handleHoverEvent(MotionEvent motionEvent, EventDispatcher eventDispatcher, int r21, List<TouchTargetHelper.ViewTarget> list) {
        if (motionEvent.getActionMasked() != 7) {
            return;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (Math.abs(this.mLastEventCoordinates[0] - x) > 0.1f || Math.abs(this.mLastEventCoordinates[1] - y) > 0.1f) {
            if (this.mHoverInteractionKey < 0) {
                long eventTime = motionEvent.getEventTime();
                this.mHoverInteractionKey = eventTime;
                this.mTouchEventCoalescingKeyHelper.addCoalescingKey(eventTime);
            }
            if (this.mChildHandlingNativeGesture > 0) {
                Iterator<TouchTargetHelper.ViewTarget> it = list.iterator();
                int r1 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (it.next().getViewId() == this.mChildHandlingNativeGesture) {
                        list.subList(0, r1).clear();
                        break;
                    } else {
                        r1++;
                    }
                }
            }
            int viewId = list.isEmpty() ? -1 : list.get(0).getViewId();
            if (viewId == -1) {
                return;
            }
            boolean z = false;
            int r12 = 0;
            boolean z2 = false;
            while (r12 < Math.min(list.size(), this.mLastHitPath.size())) {
                List<TouchTargetHelper.ViewTarget> list2 = this.mLastHitPath;
                if (!list.get((list.size() - 1) - r12).equals(list2.get((list2.size() - 1) - r12))) {
                    break;
                }
                View view = list.get((list.size() - 1) - r12).getView();
                if (!z && PointerEventHelper.isListening(view, PointerEventHelper.EVENT.ENTER_CAPTURE)) {
                    z = true;
                }
                if (!z2 && PointerEventHelper.isListening(view, PointerEventHelper.EVENT.LEAVE_CAPTURE)) {
                    z2 = true;
                }
                r12++;
            }
            if (r12 < Math.max(list.size(), this.mLastHitPath.size())) {
                this.mTouchEventCoalescingKeyHelper.incrementCoalescingKey(this.mHoverInteractionKey);
                List<TouchTargetHelper.ViewTarget> filterByShouldDispatch = filterByShouldDispatch(list.subList(0, list.size() - r12), PointerEventHelper.EVENT.ENTER, PointerEventHelper.EVENT.ENTER_CAPTURE, z);
                if (filterByShouldDispatch.size() > 0) {
                    Collections.reverse(filterByShouldDispatch);
                    dispatchEventForViewTargets(PointerEventHelper.POINTER_ENTER, filterByShouldDispatch, eventDispatcher, r21, motionEvent);
                }
                List<TouchTargetHelper.ViewTarget> list3 = this.mLastHitPath;
                List<TouchTargetHelper.ViewTarget> filterByShouldDispatch2 = filterByShouldDispatch(list3.subList(0, list3.size() - r12), PointerEventHelper.EVENT.LEAVE, PointerEventHelper.EVENT.LEAVE_CAPTURE, z2);
                if (filterByShouldDispatch2.size() > 0) {
                    dispatchEventForViewTargets(PointerEventHelper.POINTER_LEAVE, filterByShouldDispatch2, eventDispatcher, r21, motionEvent);
                }
            }
            short coalescingKey = this.mTouchEventCoalescingKeyHelper.getCoalescingKey(this.mHoverInteractionKey);
            if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.MOVE, PointerEventHelper.EVENT.MOVE_CAPTURE)) {
                eventDispatcher.dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_MOVE, r21, viewId, motionEvent, this.mTargetCoordinates, coalescingKey));
            }
            this.mLastHitPath = list;
            float[] fArr = this.mLastEventCoordinates;
            fArr[0] = x;
            fArr[1] = y;
        }
    }

    private void dispatchCancelEvent(List<TouchTargetHelper.ViewTarget> list, MotionEvent motionEvent, EventDispatcher eventDispatcher) {
        Assertions.assertCondition(this.mChildHandlingNativeGesture == -1, "Expected to not have already sent a cancel for this gesture");
        int surfaceId = UIManagerHelper.getSurfaceId(this.mRootViewGroup);
        if (list.isEmpty()) {
            return;
        }
        if (isAnyoneListeningForBubblingEvent(list, PointerEventHelper.EVENT.CANCEL, PointerEventHelper.EVENT.CANCEL_CAPTURE)) {
            ((EventDispatcher) Assertions.assertNotNull(eventDispatcher)).dispatchEvent(PointerEvent.obtain(PointerEventHelper.POINTER_CANCEL, surfaceId, list.get(0).getViewId(), motionEvent, this.mTargetCoordinates));
        }
        dispatchEventForViewTargets(PointerEventHelper.POINTER_LEAVE, filterByShouldDispatch(list, PointerEventHelper.EVENT.LEAVE, PointerEventHelper.EVENT.LEAVE_CAPTURE, false), eventDispatcher, surfaceId, motionEvent);
        this.mTouchEventCoalescingKeyHelper.removeCoalescingKey(this.mDownStartTime);
        this.mDownStartTime = Long.MIN_VALUE;
    }
}
