package androidx.customview.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ViewDragHelper {
    private static final int BASE_SETTLE_DURATION = 256;
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    private static final int EDGE_SIZE = 20;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "ViewDragHelper";
    private static final Interpolator sInterpolator = new Interpolator() { // from class: androidx.customview.widget.ViewDragHelper.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private final Callback mCallback;
    private View mCapturedView;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private OverScroller mScroller;
    private int mTouchSlop;
    private int mTrackingEdges;
    private VelocityTracker mVelocityTracker;
    private int mActivePointerId = -1;
    private final Runnable mSetIdleRunnable = new Runnable() { // from class: androidx.customview.widget.ViewDragHelper.2
        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper.this.setDragState(0);
        }
    };

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public int clampViewPositionHorizontal(View view, int r2, int r3) {
            return 0;
        }

        public int clampViewPositionVertical(View view, int r2, int r3) {
            return 0;
        }

        public int getOrderedChildIndex(int r1) {
            return r1;
        }

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange(View view) {
            return 0;
        }

        public void onEdgeDragStarted(int r1, int r2) {
        }

        public boolean onEdgeLock(int r1) {
            return false;
        }

        public void onEdgeTouched(int r1, int r2) {
        }

        public void onViewCaptured(View view, int r2) {
        }

        public void onViewDragStateChanged(int r1) {
        }

        public void onViewPositionChanged(View view, int r2, int r3, int r4, int r5) {
        }

        public void onViewReleased(View view, float f, float f2) {
        }

        public abstract boolean tryCaptureView(View view, int r2);
    }

    public static ViewDragHelper create(ViewGroup viewGroup, Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    public static ViewDragHelper create(ViewGroup viewGroup, float f, Callback callback) {
        ViewDragHelper create = create(viewGroup, callback);
        create.mTouchSlop = (int) (create.mTouchSlop * (1.0f / f));
        return create;
    }

    private ViewDragHelper(Context context, ViewGroup viewGroup, Callback callback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.mParentView = viewGroup;
        this.mCallback = callback;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mEdgeSize = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMaxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mScroller = new OverScroller(context, sInterpolator);
    }

    public void setMinVelocity(float f) {
        this.mMinVelocity = f;
    }

    public float getMinVelocity() {
        return this.mMinVelocity;
    }

    public int getViewDragState() {
        return this.mDragState;
    }

    public void setEdgeTrackingEnabled(int r1) {
        this.mTrackingEdges = r1;
    }

    public int getEdgeSize() {
        return this.mEdgeSize;
    }

    public void captureChildView(View view, int r4) {
        if (view.getParent() != this.mParentView) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.mParentView + ")");
        }
        this.mCapturedView = view;
        this.mActivePointerId = r4;
        this.mCallback.onViewCaptured(view, r4);
        setDragState(1);
    }

    public View getCapturedView() {
        return this.mCapturedView;
    }

    public int getActivePointerId() {
        return this.mActivePointerId;
    }

    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public void cancel() {
        this.mActivePointerId = -1;
        clearMotionHistory();
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void abort() {
        cancel();
        if (this.mDragState == 2) {
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            this.mScroller.abortAnimation();
            int currX2 = this.mScroller.getCurrX();
            int currY2 = this.mScroller.getCurrY();
            this.mCallback.onViewPositionChanged(this.mCapturedView, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        setDragState(0);
    }

    public boolean smoothSlideViewTo(View view, int r2, int r3) {
        this.mCapturedView = view;
        this.mActivePointerId = -1;
        boolean forceSettleCapturedViewAt = forceSettleCapturedViewAt(r2, r3, 0, 0);
        if (!forceSettleCapturedViewAt && this.mDragState == 0 && this.mCapturedView != null) {
            this.mCapturedView = null;
        }
        return forceSettleCapturedViewAt;
    }

    public boolean settleCapturedViewAt(int r4, int r5) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        }
        return forceSettleCapturedViewAt(r4, r5, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
    }

    private boolean forceSettleCapturedViewAt(int r11, int r12, int r13, int r14) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        int r112 = r11 - left;
        int r122 = r12 - top;
        if (r112 == 0 && r122 == 0) {
            this.mScroller.abortAnimation();
            setDragState(0);
            return false;
        }
        this.mScroller.startScroll(left, top, r112, r122, computeSettleDuration(this.mCapturedView, r112, r122, r13, r14));
        setDragState(2);
        return true;
    }

    private int computeSettleDuration(View view, int r8, int r9, int r10, int r11) {
        float f;
        float f2;
        float f3;
        float f4;
        int clampMag = clampMag(r10, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int clampMag2 = clampMag(r11, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int abs = Math.abs(r8);
        int abs2 = Math.abs(r9);
        int abs3 = Math.abs(clampMag);
        int abs4 = Math.abs(clampMag2);
        int r4 = abs3 + abs4;
        int r5 = abs + abs2;
        if (clampMag != 0) {
            f = abs3;
            f2 = r4;
        } else {
            f = abs;
            f2 = r5;
        }
        float f5 = f / f2;
        if (clampMag2 != 0) {
            f3 = abs4;
            f4 = r4;
        } else {
            f3 = abs2;
            f4 = r5;
        }
        return (int) ((computeAxisDuration(r8, clampMag, this.mCallback.getViewHorizontalDragRange(view)) * f5) + (computeAxisDuration(r9, clampMag2, this.mCallback.getViewVerticalDragRange(view)) * (f3 / f4)));
    }

    private int computeAxisDuration(int r4, int r5, int r6) {
        int abs;
        if (r4 == 0) {
            return 0;
        }
        int width = this.mParentView.getWidth();
        float f = width / 2;
        float distanceInfluenceForSnapDuration = f + (distanceInfluenceForSnapDuration(Math.min(1.0f, Math.abs(r4) / width)) * f);
        int abs2 = Math.abs(r5);
        if (abs2 > 0) {
            abs = Math.round(Math.abs(distanceInfluenceForSnapDuration / abs2) * 1000.0f) * 4;
        } else {
            abs = (int) (((Math.abs(r4) / r6) + 1.0f) * 256.0f);
        }
        return Math.min(abs, (int) MAX_SETTLE_DURATION);
    }

    private int clampMag(int r2, int r3, int r4) {
        int abs = Math.abs(r2);
        if (abs < r3) {
            return 0;
        }
        return abs > r4 ? r2 > 0 ? r4 : -r4 : r2;
    }

    private float clampMag(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        return abs > f3 ? f > 0.0f ? f3 : -f3 : f;
    }

    private float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    public void flingCapturedView(int r11, int r12, int r13, int r14) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
        }
        this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId), r11, r13, r12, r14);
        setDragState(2);
    }

    public boolean continueSettling(boolean z) {
        if (this.mDragState == 2) {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            int left = currX - this.mCapturedView.getLeft();
            int top = currY - this.mCapturedView.getTop();
            if (left != 0) {
                ViewCompat.offsetLeftAndRight(this.mCapturedView, left);
            }
            if (top != 0) {
                ViewCompat.offsetTopAndBottom(this.mCapturedView, top);
            }
            if (left != 0 || top != 0) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.mScroller.getFinalX() && currY == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.mParentView.post(this.mSetIdleRunnable);
                } else {
                    setDragState(0);
                }
            }
        }
        return this.mDragState == 2;
    }

    private void dispatchViewReleased(float f, float f2) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f, f2);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    private void clearMotionHistory() {
        float[] fArr = this.mInitialMotionX;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.mInitialMotionY, 0.0f);
        Arrays.fill(this.mLastMotionX, 0.0f);
        Arrays.fill(this.mLastMotionY, 0.0f);
        Arrays.fill(this.mInitialEdgesTouched, 0);
        Arrays.fill(this.mEdgeDragsInProgress, 0);
        Arrays.fill(this.mEdgeDragsLocked, 0);
        this.mPointersDown = 0;
    }

    private void clearMotionHistory(int r3) {
        if (this.mInitialMotionX == null || !isPointerDown(r3)) {
            return;
        }
        this.mInitialMotionX[r3] = 0.0f;
        this.mInitialMotionY[r3] = 0.0f;
        this.mLastMotionX[r3] = 0.0f;
        this.mLastMotionY[r3] = 0.0f;
        this.mInitialEdgesTouched[r3] = 0;
        this.mEdgeDragsInProgress[r3] = 0;
        this.mEdgeDragsLocked[r3] = 0;
        this.mPointersDown = (~(1 << r3)) & this.mPointersDown;
    }

    private void ensureMotionHistorySizeForId(int r10) {
        float[] fArr = this.mInitialMotionX;
        if (fArr == null || fArr.length <= r10) {
            int r102 = r10 + 1;
            float[] fArr2 = new float[r102];
            float[] fArr3 = new float[r102];
            float[] fArr4 = new float[r102];
            float[] fArr5 = new float[r102];
            int[] r5 = new int[r102];
            int[] r6 = new int[r102];
            int[] r103 = new int[r102];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.mInitialMotionY;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.mLastMotionX;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.mLastMotionY;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] r0 = this.mInitialEdgesTouched;
                System.arraycopy(r0, 0, r5, 0, r0.length);
                int[] r02 = this.mEdgeDragsInProgress;
                System.arraycopy(r02, 0, r6, 0, r02.length);
                int[] r03 = this.mEdgeDragsLocked;
                System.arraycopy(r03, 0, r103, 0, r03.length);
            }
            this.mInitialMotionX = fArr2;
            this.mInitialMotionY = fArr3;
            this.mLastMotionX = fArr4;
            this.mLastMotionY = fArr5;
            this.mInitialEdgesTouched = r5;
            this.mEdgeDragsInProgress = r6;
            this.mEdgeDragsLocked = r103;
        }
    }

    private void saveInitialMotion(float f, float f2, int r5) {
        ensureMotionHistorySizeForId(r5);
        float[] fArr = this.mInitialMotionX;
        this.mLastMotionX[r5] = f;
        fArr[r5] = f;
        float[] fArr2 = this.mInitialMotionY;
        this.mLastMotionY[r5] = f2;
        fArr2[r5] = f2;
        this.mInitialEdgesTouched[r5] = getEdgesTouched((int) f, (int) f2);
        this.mPointersDown |= 1 << r5;
    }

    private void saveLastMotion(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int r1 = 0; r1 < pointerCount; r1++) {
            int pointerId = motionEvent.getPointerId(r1);
            if (isValidPointerForActionMove(pointerId)) {
                float x = motionEvent.getX(r1);
                float y = motionEvent.getY(r1);
                this.mLastMotionX[pointerId] = x;
                this.mLastMotionY[pointerId] = y;
            }
        }
    }

    public boolean isPointerDown(int r3) {
        return ((1 << r3) & this.mPointersDown) != 0;
    }

    void setDragState(int r3) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != r3) {
            this.mDragState = r3;
            this.mCallback.onViewDragStateChanged(r3);
            if (this.mDragState == 0) {
                this.mCapturedView = null;
            }
        }
    }

    boolean tryCaptureViewForDrag(View view, int r4) {
        if (view == this.mCapturedView && this.mActivePointerId == r4) {
            return true;
        }
        if (view == null || !this.mCallback.tryCaptureView(view, r4)) {
            return false;
        }
        this.mActivePointerId = r4;
        captureChildView(view, r4);
        return true;
    }

    protected boolean canScroll(View view, boolean z, int r16, int r17, int r18, int r19) {
        int r8;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int r6 = r18 + scrollX;
                if (r6 >= childAt.getLeft() && r6 < childAt.getRight() && (r8 = r19 + scrollY) >= childAt.getTop() && r8 < childAt.getBottom() && canScroll(childAt, true, r16, r17, r6 - childAt.getLeft(), r8 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && (view.canScrollHorizontally(-r16) || view.canScrollVertically(-r17));
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00dd, code lost:
        if (r12 != r11) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean shouldInterceptTouchEvent(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public void processTouchEvent(MotionEvent motionEvent) {
        int r10;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int r2 = 0;
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View findTopChildUnder = findTopChildUnder((int) x, (int) y);
            saveInitialMotion(x, y, pointerId);
            tryCaptureViewForDrag(findTopChildUnder, pointerId);
            int r0 = this.mInitialEdgesTouched[pointerId];
            int r1 = this.mTrackingEdges;
            if ((r0 & r1) != 0) {
                this.mCallback.onEdgeTouched(r0 & r1, pointerId);
            }
        } else if (actionMasked == 1) {
            if (this.mDragState == 1) {
                releaseViewForPointerUp();
            }
            cancel();
        } else if (actionMasked == 2) {
            if (this.mDragState == 1) {
                if (isValidPointerForActionMove(this.mActivePointerId)) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    float x2 = motionEvent.getX(findPointerIndex);
                    float y2 = motionEvent.getY(findPointerIndex);
                    float[] fArr = this.mLastMotionX;
                    int r3 = this.mActivePointerId;
                    int r12 = (int) (x2 - fArr[r3]);
                    int r02 = (int) (y2 - this.mLastMotionY[r3]);
                    dragTo(this.mCapturedView.getLeft() + r12, this.mCapturedView.getTop() + r02, r12, r02);
                    saveLastMotion(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (r2 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(r2);
                if (isValidPointerForActionMove(pointerId2)) {
                    float x3 = motionEvent.getX(r2);
                    float y3 = motionEvent.getY(r2);
                    float f = x3 - this.mInitialMotionX[pointerId2];
                    float f2 = y3 - this.mInitialMotionY[pointerId2];
                    reportNewEdgeDrags(f, f2, pointerId2);
                    if (this.mDragState != 1) {
                        View findTopChildUnder2 = findTopChildUnder((int) x3, (int) y3);
                        if (checkTouchSlop(findTopChildUnder2, f, f2) && tryCaptureViewForDrag(findTopChildUnder2, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                r2++;
            }
            saveLastMotion(motionEvent);
        } else if (actionMasked == 3) {
            if (this.mDragState == 1) {
                dispatchViewReleased(0.0f, 0.0f);
            }
            cancel();
        } else if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x4 = motionEvent.getX(actionIndex);
            float y4 = motionEvent.getY(actionIndex);
            saveInitialMotion(x4, y4, pointerId3);
            if (this.mDragState == 0) {
                tryCaptureViewForDrag(findTopChildUnder((int) x4, (int) y4), pointerId3);
                int r102 = this.mInitialEdgesTouched[pointerId3];
                int r13 = this.mTrackingEdges;
                if ((r102 & r13) != 0) {
                    this.mCallback.onEdgeTouched(r102 & r13, pointerId3);
                }
            } else if (isCapturedViewUnder((int) x4, (int) y4)) {
                tryCaptureViewForDrag(this.mCapturedView, pointerId3);
            }
        } else if (actionMasked != 6) {
        } else {
            int pointerId4 = motionEvent.getPointerId(actionIndex);
            if (this.mDragState == 1 && pointerId4 == this.mActivePointerId) {
                int pointerCount2 = motionEvent.getPointerCount();
                while (true) {
                    if (r2 >= pointerCount2) {
                        r10 = -1;
                        break;
                    }
                    int pointerId5 = motionEvent.getPointerId(r2);
                    if (pointerId5 != this.mActivePointerId) {
                        View findTopChildUnder3 = findTopChildUnder((int) motionEvent.getX(r2), (int) motionEvent.getY(r2));
                        View view = this.mCapturedView;
                        if (findTopChildUnder3 == view && tryCaptureViewForDrag(view, pointerId5)) {
                            r10 = this.mActivePointerId;
                            break;
                        }
                    }
                    r2++;
                }
                if (r10 == -1) {
                    releaseViewForPointerUp();
                }
            }
            clearMotionHistory(pointerId4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.customview.widget.ViewDragHelper$Callback] */
    private void reportNewEdgeDrags(float f, float f2, int r5) {
        boolean checkNewEdgeDrag = checkNewEdgeDrag(f, f2, r5, 1);
        boolean z = checkNewEdgeDrag;
        if (checkNewEdgeDrag(f2, f, r5, 4)) {
            z = checkNewEdgeDrag | true;
        }
        boolean z2 = z;
        if (checkNewEdgeDrag(f, f2, r5, 2)) {
            z2 = (z ? 1 : 0) | true;
        }
        ?? r0 = z2;
        if (checkNewEdgeDrag(f2, f, r5, 8)) {
            r0 = (z2 ? 1 : 0) | true;
        }
        if (r0 != 0) {
            int[] r3 = this.mEdgeDragsInProgress;
            r3[r5] = r3[r5] | r0;
            this.mCallback.onEdgeDragStarted(r0, r5);
        }
    }

    private boolean checkNewEdgeDrag(float f, float f2, int r6, int r7) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.mInitialEdgesTouched[r6] & r7) != r7 || (this.mTrackingEdges & r7) == 0 || (this.mEdgeDragsLocked[r6] & r7) == r7 || (this.mEdgeDragsInProgress[r6] & r7) == r7) {
            return false;
        }
        int r0 = this.mTouchSlop;
        if (abs > r0 || abs2 > r0) {
            if (abs >= abs2 * 0.5f || !this.mCallback.onEdgeLock(r7)) {
                return (this.mEdgeDragsInProgress[r6] & r7) == 0 && abs > ((float) this.mTouchSlop);
            }
            int[] r4 = this.mEdgeDragsLocked;
            r4[r6] = r4[r6] | r7;
            return false;
        }
        return false;
    }

    private boolean checkTouchSlop(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z = this.mCallback.getViewHorizontalDragRange(view) > 0;
        boolean z2 = this.mCallback.getViewVerticalDragRange(view) > 0;
        if (!z || !z2) {
            return z ? Math.abs(f) > ((float) this.mTouchSlop) : z2 && Math.abs(f2) > ((float) this.mTouchSlop);
        }
        int r5 = this.mTouchSlop;
        return (f * f) + (f2 * f2) > ((float) (r5 * r5));
    }

    public boolean checkTouchSlop(int r5) {
        int length = this.mInitialMotionX.length;
        for (int r2 = 0; r2 < length; r2++) {
            if (checkTouchSlop(r5, r2)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTouchSlop(int r7, int r8) {
        if (isPointerDown(r8)) {
            boolean z = (r7 & 1) == 1;
            boolean z2 = (r7 & 2) == 2;
            float f = this.mLastMotionX[r8] - this.mInitialMotionX[r8];
            float f2 = this.mLastMotionY[r8] - this.mInitialMotionY[r8];
            if (!z || !z2) {
                return z ? Math.abs(f) > ((float) this.mTouchSlop) : z2 && Math.abs(f2) > ((float) this.mTouchSlop);
            }
            int r72 = this.mTouchSlop;
            return (f * f) + (f2 * f2) > ((float) (r72 * r72));
        }
        return false;
    }

    public boolean isEdgeTouched(int r5) {
        int length = this.mInitialEdgesTouched.length;
        for (int r2 = 0; r2 < length; r2++) {
            if (isEdgeTouched(r5, r2)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEdgeTouched(int r2, int r3) {
        return isPointerDown(r3) && (r2 & this.mInitialEdgesTouched[r3]) != 0;
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    private void dragTo(int r11, int r12, int r13, int r14) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        if (r13 != 0) {
            r11 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, r11, r13);
            ViewCompat.offsetLeftAndRight(this.mCapturedView, r11 - left);
        }
        int r6 = r11;
        if (r14 != 0) {
            r12 = this.mCallback.clampViewPositionVertical(this.mCapturedView, r12, r14);
            ViewCompat.offsetTopAndBottom(this.mCapturedView, r12 - top);
        }
        int r7 = r12;
        if (r13 == 0 && r14 == 0) {
            return;
        }
        this.mCallback.onViewPositionChanged(this.mCapturedView, r6, r7, r6 - left, r7 - top);
    }

    public boolean isCapturedViewUnder(int r2, int r3) {
        return isViewUnder(this.mCapturedView, r2, r3);
    }

    public boolean isViewUnder(View view, int r4, int r5) {
        return view != null && r4 >= view.getLeft() && r4 < view.getRight() && r5 >= view.getTop() && r5 < view.getBottom();
    }

    public View findTopChildUnder(int r4, int r5) {
        for (int childCount = this.mParentView.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(childCount));
            if (r4 >= childAt.getLeft() && r4 < childAt.getRight() && r5 >= childAt.getTop() && r5 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private int getEdgesTouched(int r4, int r5) {
        int r0 = r4 < this.mParentView.getLeft() + this.mEdgeSize ? 1 : 0;
        if (r5 < this.mParentView.getTop() + this.mEdgeSize) {
            r0 |= 4;
        }
        if (r4 > this.mParentView.getRight() - this.mEdgeSize) {
            r0 |= 2;
        }
        return r5 > this.mParentView.getBottom() - this.mEdgeSize ? r0 | 8 : r0;
    }

    private boolean isValidPointerForActionMove(int r3) {
        if (isPointerDown(r3)) {
            return true;
        }
        Log.e(TAG, "Ignoring pointerId=" + r3 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }
}
