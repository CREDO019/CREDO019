package com.facebook.react.views.scroll;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.C1413R;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactOverflowViewWithInset;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import java.lang.reflect.Field;
import java.util.List;

/* loaded from: classes.dex */
public class ReactScrollView extends ScrollView implements ReactClippingViewGroup, ViewGroup.OnHierarchyChangeListener, View.OnLayoutChangeListener, FabricViewStateManager.HasFabricViewStateManager, ReactOverflowViewWithInset, ReactScrollViewHelper.HasScrollState, ReactScrollViewHelper.HasFlingAnimator, ReactScrollViewHelper.HasScrollEventThrottle {
    private static final int UNSET_CONTENT_OFFSET = -1;
    private static Field sScrollerField = null;
    private static boolean sTriedToGetScrollerField = false;
    private final ValueAnimator DEFAULT_FLING_ANIMATOR;
    private boolean mActivelyScrolling;
    private Rect mClippingRect;
    private View mContentView;
    private boolean mDisableIntervalMomentum;
    private boolean mDragging;
    private Drawable mEndBackground;
    private int mEndFillColor;
    private final FabricViewStateManager mFabricViewStateManager;
    private FpsListener mFpsListener;
    private long mLastScrollDispatchTime;
    private final OnScrollDispatchHelper mOnScrollDispatchHelper;
    private String mOverflow;
    private final Rect mOverflowInset;
    private boolean mPagingEnabled;
    private PointerEvents mPointerEvents;
    private Runnable mPostTouchRunnable;
    private ReactViewBackgroundManager mReactBackgroundManager;
    private final ReactScrollViewHelper.ReactScrollViewScrollState mReactScrollViewScrollState;
    private final Rect mRect;
    private boolean mRemoveClippedSubviews;
    private boolean mScrollEnabled;
    private int mScrollEventThrottle;
    private String mScrollPerfTag;
    private final OverScroller mScroller;
    private boolean mSendMomentumEvents;
    private int mSnapInterval;
    private List<Integer> mSnapOffsets;
    private int mSnapToAlignment;
    private boolean mSnapToEnd;
    private boolean mSnapToStart;
    private final Rect mTempRect;
    private final VelocityHelper mVelocityHelper;
    private int pendingContentOffsetX;
    private int pendingContentOffsetY;

    public ReactScrollView(Context context) {
        this(context, null);
    }

    public ReactScrollView(Context context, FpsListener fpsListener) {
        super(context);
        this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
        this.mVelocityHelper = new VelocityHelper();
        this.mRect = new Rect();
        this.mTempRect = new Rect();
        this.mOverflowInset = new Rect();
        this.mOverflow = "hidden";
        this.mPagingEnabled = false;
        this.mScrollEnabled = true;
        this.mFpsListener = null;
        this.mEndFillColor = 0;
        this.mDisableIntervalMomentum = false;
        this.mSnapInterval = 0;
        this.mSnapToStart = true;
        this.mSnapToEnd = true;
        this.mSnapToAlignment = 0;
        this.pendingContentOffsetX = -1;
        this.pendingContentOffsetY = -1;
        this.mFabricViewStateManager = new FabricViewStateManager();
        this.mReactScrollViewScrollState = new ReactScrollViewHelper.ReactScrollViewScrollState(0);
        this.DEFAULT_FLING_ANIMATOR = ObjectAnimator.ofInt(this, "scrollY", 0, 0);
        this.mPointerEvents = PointerEvents.AUTO;
        this.mLastScrollDispatchTime = 0L;
        this.mScrollEventThrottle = 0;
        this.mFpsListener = fpsListener;
        this.mReactBackgroundManager = new ReactViewBackgroundManager(this);
        this.mScroller = getOverScrollerFromParent();
        setOnHierarchyChangeListener(this);
        setScrollBarStyle(33554432);
        ViewCompat.setAccessibilityDelegate(this, new ReactScrollViewAccessibilityDelegate());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        String str = (String) getTag(C1413R.C1417id.react_test_id);
        if (str != null) {
            accessibilityNodeInfo.setViewIdResourceName(str);
        }
    }

    private OverScroller getOverScrollerFromParent() {
        if (!sTriedToGetScrollerField) {
            sTriedToGetScrollerField = true;
            try {
                Field declaredField = ScrollView.class.getDeclaredField("mScroller");
                sScrollerField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                FLog.m1288w(ReactConstants.TAG, "Failed to get mScroller field for ScrollView! This app will exhibit the bounce-back scrolling bug :(");
            }
        }
        Field field = sScrollerField;
        OverScroller overScroller = null;
        if (field != null) {
            try {
                Object obj = field.get(this);
                if (obj instanceof OverScroller) {
                    overScroller = (OverScroller) obj;
                } else {
                    FLog.m1288w(ReactConstants.TAG, "Failed to cast mScroller field in ScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to get mScroller from ScrollView!", e);
            }
        }
        return overScroller;
    }

    public void setDisableIntervalMomentum(boolean z) {
        this.mDisableIntervalMomentum = z;
    }

    public void setSendMomentumEvents(boolean z) {
        this.mSendMomentumEvents = z;
    }

    public void setScrollPerfTag(String str) {
        this.mScrollPerfTag = str;
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    public boolean getScrollEnabled() {
        return this.mScrollEnabled;
    }

    public void setPagingEnabled(boolean z) {
        this.mPagingEnabled = z;
    }

    public void setDecelerationRate(float f) {
        getReactScrollViewScrollState().setDecelerationRate(f);
        OverScroller overScroller = this.mScroller;
        if (overScroller != null) {
            overScroller.setFriction(1.0f - f);
        }
    }

    public void setSnapInterval(int r1) {
        this.mSnapInterval = r1;
    }

    public void setSnapOffsets(List<Integer> list) {
        this.mSnapOffsets = list;
    }

    public void setSnapToStart(boolean z) {
        this.mSnapToStart = z;
    }

    public void setSnapToEnd(boolean z) {
        this.mSnapToEnd = z;
    }

    public void setSnapToAlignment(int r1) {
        this.mSnapToAlignment = r1;
    }

    public void flashScrollIndicators() {
        awakenScrollBars();
    }

    public void setOverflow(String str) {
        this.mOverflow = str;
        invalidate();
    }

    @Override // com.facebook.react.uimanager.ReactOverflowView
    public String getOverflow() {
        return this.mOverflow;
    }

    @Override // com.facebook.react.uimanager.ReactOverflowViewWithInset
    public void setOverflowInset(int r2, int r3, int r4, int r5) {
        this.mOverflowInset.set(r2, r3, r4, r5);
    }

    @Override // com.facebook.react.uimanager.ReactOverflowViewWithInset
    public Rect getOverflowInset() {
        return this.mOverflowInset;
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int r1, int r2) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(r1, r2);
        setMeasuredDimension(View.MeasureSpec.getSize(r1), View.MeasureSpec.getSize(r2));
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r2, int r3, int r4, int r5) {
        int r1 = this.pendingContentOffsetX;
        if (r1 == -1) {
            r1 = getScrollX();
        }
        int r32 = this.pendingContentOffsetY;
        if (r32 == -1) {
            r32 = getScrollY();
        }
        scrollTo(r1, r32);
        ReactScrollViewHelper.emitLayoutEvent(this);
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (view2 != null) {
            scrollToChild(view2);
        }
        super.requestChildFocus(view, view2);
    }

    private int getScrollDelta(View view) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        return computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
    }

    public boolean isPartiallyScrolledInView(View view) {
        int scrollDelta = getScrollDelta(view);
        view.getDrawingRect(this.mTempRect);
        return scrollDelta != 0 && Math.abs(scrollDelta) < this.mTempRect.width();
    }

    private void scrollToChild(View view) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        int computeScrollDeltaToGetChildRectOnScreen = computeScrollDeltaToGetChildRectOnScreen(rect);
        if (computeScrollDeltaToGetChildRectOnScreen != 0) {
            scrollBy(0, computeScrollDeltaToGetChildRectOnScreen);
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int r1, int r2, int r3, int r4) {
        super.onScrollChanged(r1, r2, r3, r4);
        this.mActivelyScrolling = true;
        if (this.mOnScrollDispatchHelper.onScrollChanged(r1, r2)) {
            if (this.mRemoveClippedSubviews) {
                updateClippingRect();
            }
            ReactScrollViewHelper.updateStateOnScrollChanged(this, this.mOnScrollDispatchHelper.getXFlingVelocity(), this.mOnScrollDispatchHelper.getYFlingVelocity());
        }
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mScrollEnabled) {
            if (PointerEvents.canChildrenBeTouchTarget(this.mPointerEvents)) {
                try {
                    if (super.onInterceptTouchEvent(motionEvent)) {
                        handleInterceptedTouchEvent(motionEvent);
                        return true;
                    }
                } catch (IllegalArgumentException e) {
                    FLog.m1287w(ReactConstants.TAG, "Error intercepting touch event.", e);
                }
                return false;
            }
            return true;
        }
        return false;
    }

    protected void handleInterceptedTouchEvent(MotionEvent motionEvent) {
        NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
        ReactScrollViewHelper.emitScrollBeginDragEvent(this);
        this.mDragging = true;
        enableFpsListener();
        getFlingAnimator().cancel();
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mScrollEnabled && PointerEvents.canBeTouchTarget(this.mPointerEvents)) {
            this.mVelocityHelper.calculateVelocity(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 1 && this.mDragging) {
                ReactScrollViewHelper.updateFabricScrollState(this);
                float xVelocity = this.mVelocityHelper.getXVelocity();
                float yVelocity = this.mVelocityHelper.getYVelocity();
                ReactScrollViewHelper.emitScrollEndDragEvent(this, xVelocity, yVelocity);
                this.mDragging = false;
                handlePostTouchScrolling(Math.round(xVelocity), Math.round(yVelocity));
            }
            if (actionMasked == 0) {
                cancelPostTouchScrolling();
            }
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchGenericPointerEvent(MotionEvent motionEvent) {
        if (PointerEvents.canChildrenBeTouchTarget(this.mPointerEvents)) {
            return super.dispatchGenericPointerEvent(motionEvent);
        }
        return false;
    }

    @Override // android.widget.ScrollView
    public boolean executeKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (this.mScrollEnabled || !(keyCode == 19 || keyCode == 20)) {
            return super.executeKeyEvent(keyEvent);
        }
        return false;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void setRemoveClippedSubviews(boolean z) {
        if (z && this.mClippingRect == null) {
            this.mClippingRect = new Rect();
        }
        this.mRemoveClippedSubviews = z;
        updateClippingRect();
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Assertions.assertNotNull(this.mClippingRect);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            View childAt = getChildAt(0);
            if (childAt instanceof ReactClippingViewGroup) {
                ((ReactClippingViewGroup) childAt).updateClippingRect();
            }
        }
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void getClippingRect(Rect rect) {
        rect.set((Rect) Assertions.assertNotNull(this.mClippingRect));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean getChildVisibleRect(View view, Rect rect, Point point) {
        return super.getChildVisibleRect(view, rect, point);
    }

    @Override // android.widget.ScrollView
    public void fling(int r12) {
        float signum = Math.signum(this.mOnScrollDispatchHelper.getYFlingVelocity());
        if (signum == 0.0f) {
            signum = Math.signum(r12);
        }
        int abs = (int) (Math.abs(r12) * signum);
        if (this.mPagingEnabled) {
            flingAndSnap(abs);
        } else if (this.mScroller != null) {
            this.mScroller.fling(getScrollX(), getScrollY(), 0, abs, 0, 0, 0, Integer.MAX_VALUE, 0, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            super.fling(abs);
        }
        handlePostTouchScrolling(0, abs);
    }

    private void enableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.enable(this.mScrollPerfTag);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.disable(this.mScrollPerfTag);
        }
    }

    private boolean isScrollPerfLoggingEnabled() {
        String str;
        return (this.mFpsListener == null || (str = this.mScrollPerfTag) == null || str.isEmpty()) ? false : true;
    }

    private int getMaxScrollY() {
        return Math.max(0, this.mContentView.getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
    }

    @Override // android.widget.ScrollView, android.view.View
    public void draw(Canvas canvas) {
        if (this.mEndFillColor != 0) {
            View childAt = getChildAt(0);
            if (this.mEndBackground != null && childAt != null && childAt.getBottom() < getHeight()) {
                this.mEndBackground.setBounds(0, childAt.getBottom(), getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        getDrawingRect(this.mRect);
        String str = this.mOverflow;
        str.hashCode();
        if (!str.equals(ViewProps.VISIBLE)) {
            canvas.clipRect(this.mRect);
        }
        super.draw(canvas);
    }

    private void handlePostTouchScrolling(int r3, int r4) {
        if (this.mPostTouchRunnable != null) {
            return;
        }
        if (this.mSendMomentumEvents) {
            enableFpsListener();
            ReactScrollViewHelper.emitScrollMomentumBeginEvent(this, r3, r4);
        }
        this.mActivelyScrolling = false;
        Runnable runnable = new Runnable() { // from class: com.facebook.react.views.scroll.ReactScrollView.1
            private boolean mSnappingToPage = false;
            private boolean mRunning = true;
            private int mStableFrames = 0;

            @Override // java.lang.Runnable
            public void run() {
                if (ReactScrollView.this.mActivelyScrolling) {
                    ReactScrollView.this.mActivelyScrolling = false;
                    this.mStableFrames = 0;
                    this.mRunning = true;
                } else {
                    ReactScrollViewHelper.updateFabricScrollState(ReactScrollView.this);
                    int r0 = this.mStableFrames + 1;
                    this.mStableFrames = r0;
                    this.mRunning = r0 < 3;
                    if (!ReactScrollView.this.mPagingEnabled || this.mSnappingToPage) {
                        if (ReactScrollView.this.mSendMomentumEvents) {
                            ReactScrollViewHelper.emitScrollMomentumEndEvent(ReactScrollView.this);
                        }
                        ReactScrollView.this.disableFpsListener();
                    } else {
                        this.mSnappingToPage = true;
                        ReactScrollView.this.flingAndSnap(0);
                        ViewCompat.postOnAnimationDelayed(ReactScrollView.this, this, 20L);
                    }
                }
                if (!this.mRunning) {
                    ReactScrollView.this.mPostTouchRunnable = null;
                } else {
                    ViewCompat.postOnAnimationDelayed(ReactScrollView.this, this, 20L);
                }
            }
        };
        this.mPostTouchRunnable = runnable;
        ViewCompat.postOnAnimationDelayed(this, runnable, 20L);
    }

    private void cancelPostTouchScrolling() {
        Runnable runnable = this.mPostTouchRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
            this.mPostTouchRunnable = null;
            getFlingAnimator().cancel();
        }
    }

    private int predictFinalScrollPosition(int r3) {
        if (getFlingAnimator() == this.DEFAULT_FLING_ANIMATOR) {
            return ReactScrollViewHelper.predictFinalScrollPosition(this, 0, r3, 0, getMaxScrollY()).y;
        }
        return getFlingExtrapolatedDistance(r3) + ReactScrollViewHelper.getNextFlingStartValue(this, getScrollY(), getReactScrollViewScrollState().getFinalAnimatedPositionScroll().y, r3);
    }

    private View getContentView() {
        return getChildAt(0);
    }

    private void smoothScrollAndSnap(int r12) {
        double snapInterval = getSnapInterval();
        double nextFlingStartValue = ReactScrollViewHelper.getNextFlingStartValue(this, getScrollY(), getReactScrollViewScrollState().getFinalAnimatedPositionScroll().y, r12);
        double d = nextFlingStartValue / snapInterval;
        int floor = (int) Math.floor(d);
        int ceil = (int) Math.ceil(d);
        int round = (int) Math.round(d);
        int round2 = (int) Math.round(predictFinalScrollPosition(r12) / snapInterval);
        if (r12 > 0 && ceil == floor) {
            ceil++;
        } else if (r12 < 0 && floor == ceil) {
            floor--;
        }
        if (r12 > 0 && round < ceil && round2 > floor) {
            round = ceil;
        } else if (r12 < 0 && round > floor && round2 < ceil) {
            round = floor;
        }
        double d2 = round * snapInterval;
        if (d2 != nextFlingStartValue) {
            this.mActivelyScrolling = true;
            reactSmoothScrollTo(getScrollX(), (int) d2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flingAndSnap(int r28) {
        int min;
        int r9;
        int floor;
        int r7;
        int top;
        int top2;
        int height;
        int r3;
        OverScroller overScroller;
        if (getChildCount() <= 0) {
            return;
        }
        if (this.mSnapInterval == 0 && this.mSnapOffsets == null && this.mSnapToAlignment == 0) {
            smoothScrollAndSnap(r28);
            return;
        }
        int r32 = 1;
        boolean z = getFlingAnimator() != this.DEFAULT_FLING_ANIMATOR;
        int maxScrollY = getMaxScrollY();
        int predictFinalScrollPosition = predictFinalScrollPosition(r28);
        if (this.mDisableIntervalMomentum) {
            predictFinalScrollPosition = getScrollY();
        }
        int height2 = (getHeight() - getPaddingBottom()) - getPaddingTop();
        List<Integer> list = this.mSnapOffsets;
        if (list != null) {
            r7 = list.get(0).intValue();
            List<Integer> list2 = this.mSnapOffsets;
            r9 = list2.get(list2.size() - 1).intValue();
            min = maxScrollY;
            floor = 0;
            for (int r10 = 0; r10 < this.mSnapOffsets.size(); r10++) {
                int intValue = this.mSnapOffsets.get(r10).intValue();
                if (intValue <= predictFinalScrollPosition && predictFinalScrollPosition - intValue < predictFinalScrollPosition - floor) {
                    floor = intValue;
                }
                if (intValue >= predictFinalScrollPosition && intValue - predictFinalScrollPosition < min - predictFinalScrollPosition) {
                    min = intValue;
                }
            }
        } else {
            int r72 = this.mSnapToAlignment;
            if (r72 != 0) {
                int r92 = this.mSnapInterval;
                if (r92 > 0) {
                    double d = predictFinalScrollPosition / r92;
                    double floor2 = Math.floor(d);
                    int r93 = this.mSnapInterval;
                    int max = Math.max(getItemStartOffset(r72, (int) (floor2 * r93), r93, height2), 0);
                    int r94 = this.mSnapToAlignment;
                    double ceil = Math.ceil(d);
                    int r12 = this.mSnapInterval;
                    min = Math.min(getItemStartOffset(r94, (int) (ceil * r12), r12, height2), maxScrollY);
                    r9 = maxScrollY;
                    floor = max;
                } else {
                    ViewGroup viewGroup = (ViewGroup) getContentView();
                    int r11 = maxScrollY;
                    int r122 = r11;
                    int r95 = 0;
                    int r102 = 0;
                    int r13 = 0;
                    while (r95 < viewGroup.getChildCount()) {
                        View childAt = viewGroup.getChildAt(r95);
                        int r15 = this.mSnapToAlignment;
                        if (r15 != r32) {
                            if (r15 == 2) {
                                top2 = childAt.getTop();
                                height = (height2 - childAt.getHeight()) / 2;
                            } else if (r15 == 3) {
                                top2 = childAt.getTop();
                                height = height2 - childAt.getHeight();
                            } else {
                                throw new IllegalStateException("Invalid SnapToAlignment value: " + this.mSnapToAlignment);
                            }
                            top = top2 - height;
                        } else {
                            top = childAt.getTop();
                        }
                        if (top <= predictFinalScrollPosition && predictFinalScrollPosition - top < predictFinalScrollPosition - r102) {
                            r102 = top;
                        }
                        if (top >= predictFinalScrollPosition && top - predictFinalScrollPosition < r122 - predictFinalScrollPosition) {
                            r122 = top;
                        }
                        r11 = Math.min(r11, top);
                        r13 = Math.max(r13, top);
                        r95++;
                        r32 = 1;
                    }
                    floor = Math.max(r102, r11);
                    min = Math.min(r122, r13);
                    r9 = maxScrollY;
                }
            } else {
                double snapInterval = getSnapInterval();
                double d2 = predictFinalScrollPosition / snapInterval;
                min = Math.min((int) (Math.ceil(d2) * snapInterval), maxScrollY);
                r9 = maxScrollY;
                floor = (int) (Math.floor(d2) * snapInterval);
            }
            r7 = 0;
        }
        int r33 = predictFinalScrollPosition - floor;
        int r132 = min - predictFinalScrollPosition;
        int r103 = Math.abs(r33) < Math.abs(r132) ? floor : min;
        if (!this.mSnapToEnd && predictFinalScrollPosition >= r9) {
            if (getScrollY() < r9) {
                r3 = r28;
                predictFinalScrollPosition = r9;
            }
            r3 = r28;
        } else if (!this.mSnapToStart && predictFinalScrollPosition <= r7) {
            if (getScrollY() > r7) {
                r3 = r28;
                predictFinalScrollPosition = r7;
            }
            r3 = r28;
        } else if (r28 > 0) {
            r3 = !z ? r28 + ((int) (r132 * 10.0d)) : r28;
            predictFinalScrollPosition = min;
        } else if (r28 < 0) {
            r3 = !z ? r28 - ((int) (r33 * 10.0d)) : r28;
            predictFinalScrollPosition = floor;
        } else {
            r3 = r28;
            predictFinalScrollPosition = r103;
        }
        int min2 = Math.min(Math.max(0, predictFinalScrollPosition), maxScrollY);
        if (z || (overScroller = this.mScroller) == null) {
            reactSmoothScrollTo(getScrollX(), min2);
            return;
        }
        this.mActivelyScrolling = true;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        if (r3 == 0) {
            r3 = min2 - getScrollY();
        }
        overScroller.fling(scrollX, scrollY, 0, r3, 0, 0, min2, min2, 0, (min2 == 0 || min2 == maxScrollY) ? height2 / 2 : 0);
        postInvalidateOnAnimation();
    }

    private int getItemStartOffset(int r2, int r3, int r4, int r5) {
        int r52;
        if (r2 != 1) {
            if (r2 == 2) {
                r52 = (r5 - r4) / 2;
            } else if (r2 != 3) {
                throw new IllegalStateException("Invalid SnapToAlignment value: " + this.mSnapToAlignment);
            } else {
                r52 = r5 - r4;
            }
            return r3 - r52;
        }
        return r3;
    }

    private int getSnapInterval() {
        int r0 = this.mSnapInterval;
        return r0 != 0 ? r0 : getHeight();
    }

    public void setEndFillColor(int r2) {
        if (r2 != this.mEndFillColor) {
            this.mEndFillColor = r2;
            this.mEndBackground = new ColorDrawable(this.mEndFillColor);
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onOverScrolled(int r3, int r4, boolean z, boolean z2) {
        int maxScrollY;
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && this.mContentView != null && !overScroller.isFinished() && this.mScroller.getCurrY() != this.mScroller.getFinalY() && r4 >= (maxScrollY = getMaxScrollY())) {
            this.mScroller.abortAnimation();
            r4 = maxScrollY;
        }
        super.onOverScrolled(r3, r4, z, z2);
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewAdded(View view, View view2) {
        this.mContentView = view2;
        view2.addOnLayoutChangeListener(this);
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewRemoved(View view, View view2) {
        this.mContentView.removeOnLayoutChangeListener(this);
        this.mContentView = null;
    }

    public void reactSmoothScrollTo(int r1, int r2) {
        ReactScrollViewHelper.smoothScrollTo(this, r1, r2);
        setPendingContentOffsets(r1, r2);
    }

    @Override // android.widget.ScrollView, android.view.View
    public void scrollTo(int r1, int r2) {
        super.scrollTo(r1, r2);
        ReactScrollViewHelper.updateFabricScrollState(this);
        setPendingContentOffsets(r1, r2);
    }

    private boolean isContentReady() {
        View contentView = getContentView();
        return (contentView == null || contentView.getWidth() == 0 || contentView.getHeight() == 0) ? false : true;
    }

    private void setPendingContentOffsets(int r2, int r3) {
        if (isContentReady()) {
            this.pendingContentOffsetX = -1;
            this.pendingContentOffsetY = -1;
            return;
        }
        this.pendingContentOffsetX = r2;
        this.pendingContentOffsetY = r3;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        if (this.mContentView == null) {
            return;
        }
        int scrollY = getScrollY();
        int maxScrollY = getMaxScrollY();
        if (scrollY > maxScrollY) {
            scrollTo(getScrollX(), maxScrollY);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        this.mReactBackgroundManager.setBackgroundColor(r2);
    }

    public void setBorderWidth(int r2, float f) {
        this.mReactBackgroundManager.setBorderWidth(r2, f);
    }

    public void setBorderColor(int r2, float f, float f2) {
        this.mReactBackgroundManager.setBorderColor(r2, f, f2);
    }

    public void setBorderRadius(float f) {
        this.mReactBackgroundManager.setBorderRadius(f);
    }

    public void setBorderRadius(float f, int r3) {
        this.mReactBackgroundManager.setBorderRadius(f, r3);
    }

    public void setBorderStyle(String str) {
        this.mReactBackgroundManager.setBorderStyle(str);
    }

    public void setScrollAwayTopPaddingEnabledUnstable(int r6) {
        int childCount = getChildCount();
        Assertions.assertCondition(childCount == 1, "React Native ScrollView always has exactly 1 child; a content View");
        if (childCount > 0) {
            for (int r1 = 0; r1 < childCount; r1++) {
                getChildAt(r1).setTranslationY(r6);
            }
            setPadding(0, 0, 0, r6);
        }
        updateScrollAwayState(r6);
        setRemoveClippedSubviews(this.mRemoveClippedSubviews);
    }

    private void updateScrollAwayState(int r2) {
        getReactScrollViewScrollState().setScrollAwayPaddingTop(r2);
        ReactScrollViewHelper.forceUpdateState(this);
    }

    @Override // com.facebook.react.uimanager.FabricViewStateManager.HasFabricViewStateManager
    public FabricViewStateManager getFabricViewStateManager() {
        return this.mFabricViewStateManager;
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.HasScrollState
    public ReactScrollViewHelper.ReactScrollViewScrollState getReactScrollViewScrollState() {
        return this.mReactScrollViewScrollState;
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.HasFlingAnimator
    public void startFlingAnimator(int r4, int r5) {
        this.DEFAULT_FLING_ANIMATOR.cancel();
        this.DEFAULT_FLING_ANIMATOR.setDuration(ReactScrollViewHelper.getDefaultScrollAnimationDuration(getContext())).setIntValues(r4, r5);
        this.DEFAULT_FLING_ANIMATOR.start();
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.HasFlingAnimator
    public ValueAnimator getFlingAnimator() {
        return this.DEFAULT_FLING_ANIMATOR;
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.HasFlingAnimator
    public int getFlingExtrapolatedDistance(int r3) {
        return ReactScrollViewHelper.predictFinalScrollPosition(this, 0, r3, 0, getMaxScrollY()).y;
    }

    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.HasScrollEventThrottle
    public void setScrollEventThrottle(int r1) {
        this.mScrollEventThrottle = r1;
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.HasScrollEventThrottle
    public int getScrollEventThrottle() {
        return this.mScrollEventThrottle;
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.HasScrollEventThrottle
    public void setLastScrollDispatchTime(long j) {
        this.mLastScrollDispatchTime = j;
    }

    @Override // com.facebook.react.views.scroll.ReactScrollViewHelper.HasScrollEventThrottle
    public long getLastScrollDispatchTime() {
        return this.mLastScrollDispatchTime;
    }
}
