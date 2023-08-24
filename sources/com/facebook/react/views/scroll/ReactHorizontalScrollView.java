package com.facebook.react.views.scroll;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.i18nmanager.I18nUtil;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ReactHorizontalScrollView extends HorizontalScrollView implements ReactClippingViewGroup, FabricViewStateManager.HasFabricViewStateManager, ReactOverflowViewWithInset, ReactScrollViewHelper.HasScrollState, ReactScrollViewHelper.HasFlingAnimator, ReactScrollViewHelper.HasScrollEventThrottle {
    private static boolean DEBUG_MODE = false;
    private static int NO_SCROLL_POSITION = Integer.MIN_VALUE;
    private static String TAG = "ReactHorizontalScrollView";
    private static final int UNSET_CONTENT_OFFSET = -1;
    private static Field sScrollerField = null;
    private static boolean sTriedToGetScrollerField = false;
    private final ValueAnimator DEFAULT_FLING_ANIMATOR;
    private boolean mActivelyScrolling;
    private Rect mClippingRect;
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
    private boolean mPagedArrowScrolling;
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
    private int mScrollXAfterMeasure;
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

    public ReactHorizontalScrollView(Context context) {
        this(context, null);
    }

    public ReactHorizontalScrollView(Context context, FpsListener fpsListener) {
        super(context);
        this.mScrollXAfterMeasure = NO_SCROLL_POSITION;
        this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
        this.mVelocityHelper = new VelocityHelper();
        this.mRect = new Rect();
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
        this.mPagedArrowScrolling = false;
        this.pendingContentOffsetX = -1;
        this.pendingContentOffsetY = -1;
        this.mFabricViewStateManager = new FabricViewStateManager();
        this.DEFAULT_FLING_ANIMATOR = ObjectAnimator.ofInt(this, "scrollX", 0, 0);
        this.mPointerEvents = PointerEvents.AUTO;
        this.mLastScrollDispatchTime = 0L;
        this.mScrollEventThrottle = 0;
        this.mTempRect = new Rect();
        this.mReactBackgroundManager = new ReactViewBackgroundManager(this);
        this.mFpsListener = fpsListener;
        ViewCompat.setAccessibilityDelegate(this, new ReactScrollViewAccessibilityDelegate());
        this.mScroller = getOverScrollerFromParent();
        this.mReactScrollViewScrollState = new ReactScrollViewHelper.ReactScrollViewScrollState(I18nUtil.getInstance().isRTL(context) ? 1 : 0);
    }

    public boolean getScrollEnabled() {
        return this.mScrollEnabled;
    }

    private OverScroller getOverScrollerFromParent() {
        if (!sTriedToGetScrollerField) {
            sTriedToGetScrollerField = true;
            try {
                Field declaredField = HorizontalScrollView.class.getDeclaredField("mScroller");
                sScrollerField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                FLog.m1288w(TAG, "Failed to get mScroller field for HorizontalScrollView! This app will exhibit the bounce-back scrolling bug :(");
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
                    FLog.m1288w(TAG, "Failed to cast mScroller field in HorizontalScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to get mScroller from HorizontalScrollView!", e);
            }
        }
        return overScroller;
    }

    public void setScrollPerfTag(String str) {
        this.mScrollPerfTag = str;
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

    public void setDisableIntervalMomentum(boolean z) {
        this.mDisableIntervalMomentum = z;
    }

    public void setSendMomentumEvents(boolean z) {
        this.mSendMomentumEvents = z;
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
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

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (DEBUG_MODE) {
            FLog.m1315i(TAG, "onDraw[%d]", Integer.valueOf(getId()));
        }
        getDrawingRect(this.mRect);
        String str = this.mOverflow;
        str.hashCode();
        if (!str.equals(ViewProps.VISIBLE)) {
            canvas.clipRect(this.mRect);
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int r6, int r7) {
        OverScroller overScroller;
        MeasureSpecAssertions.assertExplicitMeasureSpec(r6, r7);
        int size = View.MeasureSpec.getSize(r6);
        int size2 = View.MeasureSpec.getSize(r7);
        if (DEBUG_MODE) {
            FLog.m1313i(TAG, "onMeasure[%d] measured width: %d measured height: %d", Integer.valueOf(getId()), Integer.valueOf(size), Integer.valueOf(size2));
        }
        boolean z = getMeasuredHeight() != size2;
        setMeasuredDimension(size, size2);
        if (!z || (overScroller = this.mScroller) == null) {
            return;
        }
        this.mScrollXAfterMeasure = overScroller.getCurrX();
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r6, int r7, int r8, int r9) {
        OverScroller overScroller;
        if (DEBUG_MODE) {
            FLog.m1310i(TAG, "onLayout[%d] l %d t %d r %d b %d", Integer.valueOf(getId()), Integer.valueOf(r6), Integer.valueOf(r7), Integer.valueOf(r8), Integer.valueOf(r9));
        }
        int r5 = this.mScrollXAfterMeasure;
        if (r5 != NO_SCROLL_POSITION && (overScroller = this.mScroller) != null && r5 != overScroller.getFinalX() && !this.mScroller.isFinished()) {
            if (DEBUG_MODE) {
                FLog.m1314i(TAG, "onLayout[%d] scroll hack enabled: reset to previous scrollX position of %d", Integer.valueOf(getId()), Integer.valueOf(this.mScrollXAfterMeasure));
            }
            OverScroller overScroller2 = this.mScroller;
            overScroller2.startScroll(this.mScrollXAfterMeasure, overScroller2.getFinalY(), 0, 0);
            this.mScroller.forceFinished(true);
            this.mScrollXAfterMeasure = NO_SCROLL_POSITION;
        }
        int r52 = this.pendingContentOffsetX;
        if (r52 == -1) {
            r52 = getScrollX();
        }
        int r72 = this.pendingContentOffsetY;
        if (r72 == -1) {
            r72 = getScrollY();
        }
        scrollTo(r52, r72);
        ReactScrollViewHelper.emitLayoutEvent(this);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (view2 != null && !this.mPagingEnabled) {
            scrollToChild(view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int r3, int r4) {
        if (this.mPagingEnabled && !this.mPagedArrowScrolling) {
            ArrayList arrayList2 = new ArrayList();
            super.addFocusables(arrayList2, r3, r4);
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                View view = (View) it.next();
                if (isScrolledInView(view) || isPartiallyScrolledInView(view) || view.isFocused()) {
                    arrayList.add(view);
                }
            }
            return;
        }
        super.addFocusables(arrayList, r3, r4);
    }

    private int getScrollDelta(View view) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        return computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
    }

    private boolean isScrolledInView(View view) {
        return getScrollDelta(view) == 0;
    }

    public boolean isPartiallyScrolledInView(View view) {
        int scrollDelta = getScrollDelta(view);
        view.getDrawingRect(this.mTempRect);
        return scrollDelta != 0 && Math.abs(scrollDelta) < this.mTempRect.width();
    }

    private boolean isMostlyScrolledInView(View view) {
        int scrollDelta = getScrollDelta(view);
        view.getDrawingRect(this.mTempRect);
        return scrollDelta != 0 && Math.abs(scrollDelta) < this.mTempRect.width() / 2;
    }

    private void scrollToChild(View view) {
        int scrollDelta = getScrollDelta(view);
        if (scrollDelta != 0) {
            scrollBy(scrollDelta, 0);
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int r6, int r7, int r8, int r9) {
        if (DEBUG_MODE) {
            FLog.m1310i(TAG, "onScrollChanged[%d] x %d y %d oldx %d oldy %d", Integer.valueOf(getId()), Integer.valueOf(r6), Integer.valueOf(r7), Integer.valueOf(r8), Integer.valueOf(r9));
        }
        super.onScrollChanged(r6, r7, r8, r9);
        this.mActivelyScrolling = true;
        if (this.mOnScrollDispatchHelper.onScrollChanged(r6, r7)) {
            if (this.mRemoveClippedSubviews) {
                updateClippingRect();
            }
            ReactScrollViewHelper.updateStateOnScrollChanged(this, this.mOnScrollDispatchHelper.getXFlingVelocity(), this.mOnScrollDispatchHelper.getYFlingVelocity());
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
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

    @Override // android.widget.HorizontalScrollView
    public boolean pageScroll(int r2) {
        boolean pageScroll = super.pageScroll(r2);
        if (this.mPagingEnabled && pageScroll) {
            handlePostTouchScrolling(0, 0);
        }
        return pageScroll;
    }

    @Override // android.widget.HorizontalScrollView
    public boolean arrowScroll(int r6) {
        if (this.mPagingEnabled) {
            boolean z = true;
            this.mPagedArrowScrolling = true;
            if (getChildCount() > 0) {
                View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus(), r6);
                View contentView = getContentView();
                if (contentView != null && findNextFocus != null && findNextFocus.getParent() == contentView) {
                    if (!isScrolledInView(findNextFocus) && !isMostlyScrolledInView(findNextFocus)) {
                        smoothScrollToNextPage(r6);
                    }
                    findNextFocus.requestFocus();
                } else {
                    smoothScrollToNextPage(r6);
                }
            } else {
                z = false;
            }
            this.mPagedArrowScrolling = false;
            return z;
        }
        return super.arrowScroll(r6);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
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

    @Override // android.widget.HorizontalScrollView
    public boolean executeKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (this.mScrollEnabled || !(keyCode == 21 || keyCode == 22)) {
            return super.executeKeyEvent(keyEvent);
        }
        return false;
    }

    @Override // android.widget.HorizontalScrollView
    public void fling(int r12) {
        if (DEBUG_MODE) {
            FLog.m1314i(TAG, "fling[%d] velocityX %d", Integer.valueOf(getId()), Integer.valueOf(r12));
        }
        int abs = (int) (Math.abs(r12) * Math.signum(this.mOnScrollDispatchHelper.getXFlingVelocity()));
        if (this.mPagingEnabled) {
            flingAndSnap(abs);
        } else if (this.mScroller != null) {
            this.mScroller.fling(getScrollX(), getScrollY(), abs, 0, 0, Integer.MAX_VALUE, 0, 0, ((getWidth() - ViewCompat.getPaddingStart(this)) - ViewCompat.getPaddingEnd(this)) / 2, 0);
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            super.fling(abs);
        }
        handlePostTouchScrolling(abs, 0);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
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

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Assertions.assertNotNull(this.mClippingRect);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            View contentView = getContentView();
            if (contentView instanceof ReactClippingViewGroup) {
                ((ReactClippingViewGroup) contentView).updateClippingRect();
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

    private int getSnapInterval() {
        int r0 = this.mSnapInterval;
        return r0 != 0 ? r0 : getWidth();
    }

    private View getContentView() {
        return getChildAt(0);
    }

    public void setEndFillColor(int r2) {
        if (r2 != this.mEndFillColor) {
            this.mEndFillColor = r2;
            this.mEndBackground = new ColorDrawable(this.mEndFillColor);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected void onOverScrolled(int r5, int r6, boolean z, boolean z2) {
        int computeHorizontalScrollRange;
        if (DEBUG_MODE) {
            FLog.m1310i(TAG, "onOverScrolled[%d] scrollX %d scrollY %d clampedX %b clampedY %b", Integer.valueOf(getId()), Integer.valueOf(r5), Integer.valueOf(r6), Boolean.valueOf(z), Boolean.valueOf(z2));
        }
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && !overScroller.isFinished() && this.mScroller.getCurrX() != this.mScroller.getFinalX() && r5 >= (computeHorizontalScrollRange = computeHorizontalScrollRange() - getWidth())) {
            this.mScroller.abortAnimation();
            r5 = computeHorizontalScrollRange;
        }
        super.onOverScrolled(r5, r6, z, z2);
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

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void draw(Canvas canvas) {
        if (this.mEndFillColor != 0) {
            View contentView = getContentView();
            if (this.mEndBackground != null && contentView != null && contentView.getRight() < getWidth()) {
                this.mEndBackground.setBounds(contentView.getRight(), 0, getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    private void handlePostTouchScrolling(int r6, int r7) {
        if (DEBUG_MODE) {
            FLog.m1313i(TAG, "handlePostTouchScrolling[%d] velocityX %d velocityY %d", Integer.valueOf(getId()), Integer.valueOf(r6), Integer.valueOf(r7));
        }
        if (this.mPostTouchRunnable != null) {
            return;
        }
        if (this.mSendMomentumEvents) {
            ReactScrollViewHelper.emitScrollMomentumBeginEvent(this, r6, r7);
        }
        this.mActivelyScrolling = false;
        Runnable runnable = new Runnable() { // from class: com.facebook.react.views.scroll.ReactHorizontalScrollView.1
            private boolean mSnappingToPage = false;
            private boolean mRunning = true;
            private int mStableFrames = 0;

            @Override // java.lang.Runnable
            public void run() {
                if (ReactHorizontalScrollView.this.mActivelyScrolling) {
                    ReactHorizontalScrollView.this.mActivelyScrolling = false;
                    this.mStableFrames = 0;
                    this.mRunning = true;
                } else {
                    ReactScrollViewHelper.updateFabricScrollState(ReactHorizontalScrollView.this);
                    int r0 = this.mStableFrames + 1;
                    this.mStableFrames = r0;
                    this.mRunning = r0 < 3;
                    if (!ReactHorizontalScrollView.this.mPagingEnabled || this.mSnappingToPage) {
                        if (ReactHorizontalScrollView.this.mSendMomentumEvents) {
                            ReactScrollViewHelper.emitScrollMomentumEndEvent(ReactHorizontalScrollView.this);
                        }
                        ReactHorizontalScrollView.this.disableFpsListener();
                    } else {
                        this.mSnappingToPage = true;
                        ReactHorizontalScrollView.this.flingAndSnap(0);
                        ViewCompat.postOnAnimationDelayed(ReactHorizontalScrollView.this, this, 20L);
                    }
                }
                if (!this.mRunning) {
                    ReactHorizontalScrollView.this.mPostTouchRunnable = null;
                } else {
                    ViewCompat.postOnAnimationDelayed(ReactHorizontalScrollView.this, this, 20L);
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

    private int predictFinalScrollPosition(int r5) {
        int max = Math.max(0, computeHorizontalScrollRange() - getWidth());
        if (getFlingAnimator() == this.DEFAULT_FLING_ANIMATOR) {
            return ReactScrollViewHelper.predictFinalScrollPosition(this, r5, 0, max, 0).x;
        }
        return getFlingExtrapolatedDistance(r5) + ReactScrollViewHelper.getNextFlingStartValue(this, getScrollX(), getReactScrollViewScrollState().getFinalAnimatedPositionScroll().x, r5);
    }

    private void smoothScrollAndSnap(int r12) {
        if (DEBUG_MODE) {
            FLog.m1314i(TAG, "smoothScrollAndSnap[%d] velocity %d", Integer.valueOf(getId()), Integer.valueOf(r12));
        }
        double snapInterval = getSnapInterval();
        double nextFlingStartValue = ReactScrollViewHelper.getNextFlingStartValue(this, getScrollX(), getReactScrollViewScrollState().getFinalAnimatedPositionScroll().x, r12);
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
            reactSmoothScrollTo((int) d2, getScrollY());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flingAndSnap(int r21) {
        int r8;
        int floor;
        int min;
        int r10;
        int r9;
        OverScroller overScroller;
        if (DEBUG_MODE) {
            FLog.m1314i(TAG, "smoothScrollAndSnap[%d] velocityX %d", Integer.valueOf(getId()), Integer.valueOf(r21));
        }
        if (getChildCount() <= 0) {
            return;
        }
        if (this.mSnapInterval == 0 && this.mSnapOffsets == null && this.mSnapToAlignment == 0) {
            smoothScrollAndSnap(r21);
            return;
        }
        boolean z = getFlingAnimator() != this.DEFAULT_FLING_ANIMATOR;
        int max = Math.max(0, computeHorizontalScrollRange() - getWidth());
        int predictFinalScrollPosition = predictFinalScrollPosition(r21);
        if (this.mDisableIntervalMomentum) {
            predictFinalScrollPosition = getScrollX();
        }
        int width = (getWidth() - ViewCompat.getPaddingStart(this)) - ViewCompat.getPaddingEnd(this);
        int layoutDirection = getReactScrollViewScrollState().getLayoutDirection();
        if (layoutDirection == 1) {
            predictFinalScrollPosition = max - predictFinalScrollPosition;
            r8 = -r21;
        } else {
            r8 = r21;
        }
        List<Integer> list = this.mSnapOffsets;
        if (list != null && !list.isEmpty()) {
            r9 = this.mSnapOffsets.get(0).intValue();
            List<Integer> list2 = this.mSnapOffsets;
            r10 = list2.get(list2.size() - 1).intValue();
            min = max;
            floor = 0;
            for (int r11 = 0; r11 < this.mSnapOffsets.size(); r11++) {
                int intValue = this.mSnapOffsets.get(r11).intValue();
                if (intValue <= predictFinalScrollPosition && predictFinalScrollPosition - intValue < predictFinalScrollPosition - floor) {
                    floor = intValue;
                }
                if (intValue >= predictFinalScrollPosition && intValue - predictFinalScrollPosition < min - predictFinalScrollPosition) {
                    min = intValue;
                }
            }
        } else {
            int r3 = this.mSnapToAlignment;
            if (r3 != 0) {
                int r92 = this.mSnapInterval;
                if (r92 > 0) {
                    double d = predictFinalScrollPosition / r92;
                    double floor2 = Math.floor(d);
                    int r93 = this.mSnapInterval;
                    floor = Math.max(getItemStartOffset(r3, (int) (floor2 * r93), r93, width), 0);
                    int r32 = this.mSnapToAlignment;
                    double ceil = Math.ceil(d);
                    int r112 = this.mSnapInterval;
                    min = Math.min(getItemStartOffset(r32, (int) (ceil * r112), r112, width), max);
                } else {
                    ViewGroup viewGroup = (ViewGroup) getContentView();
                    int r113 = max;
                    int r12 = r113;
                    int r102 = 0;
                    int r13 = 0;
                    for (int r94 = 0; r94 < viewGroup.getChildCount(); r94++) {
                        View childAt = viewGroup.getChildAt(r94);
                        int itemStartOffset = getItemStartOffset(this.mSnapToAlignment, childAt.getLeft(), childAt.getWidth(), width);
                        if (itemStartOffset <= predictFinalScrollPosition && predictFinalScrollPosition - itemStartOffset < predictFinalScrollPosition - r102) {
                            r102 = itemStartOffset;
                        }
                        if (itemStartOffset >= predictFinalScrollPosition && itemStartOffset - predictFinalScrollPosition < r12 - predictFinalScrollPosition) {
                            r12 = itemStartOffset;
                        }
                        r113 = Math.min(r113, itemStartOffset);
                        r13 = Math.max(r13, itemStartOffset);
                    }
                    int max2 = Math.max(r102, r113);
                    min = Math.min(r12, r13);
                    r10 = max;
                    floor = max2;
                    r9 = 0;
                }
            } else {
                double snapInterval = getSnapInterval();
                double d2 = predictFinalScrollPosition / snapInterval;
                floor = (int) (Math.floor(d2) * snapInterval);
                min = Math.min((int) (Math.ceil(d2) * snapInterval), max);
            }
            r10 = max;
            r9 = 0;
        }
        int r33 = predictFinalScrollPosition - floor;
        int r114 = min - predictFinalScrollPosition;
        int r4 = Math.abs(r33) < Math.abs(r114) ? floor : min;
        int scrollX = getScrollX();
        if (layoutDirection == 1) {
            scrollX = max - scrollX;
        }
        if (this.mSnapToEnd || predictFinalScrollPosition < r10) {
            if (this.mSnapToStart || predictFinalScrollPosition > r9) {
                if (r8 > 0) {
                    if (!z) {
                        r8 += (int) (r114 * 10.0d);
                    }
                    predictFinalScrollPosition = min;
                } else if (r8 < 0) {
                    if (!z) {
                        r8 -= (int) (r33 * 10.0d);
                    }
                    predictFinalScrollPosition = floor;
                } else {
                    predictFinalScrollPosition = r4;
                }
            } else if (scrollX > r9) {
                predictFinalScrollPosition = r9;
            }
        } else if (scrollX < r10) {
            predictFinalScrollPosition = r10;
        }
        int min2 = Math.min(Math.max(0, predictFinalScrollPosition), max);
        if (layoutDirection == 1) {
            min2 = max - min2;
            r8 = -r8;
        }
        int r15 = min2;
        if (z || (overScroller = this.mScroller) == null) {
            reactSmoothScrollTo(r15, getScrollY());
            return;
        }
        this.mActivelyScrolling = true;
        overScroller.fling(getScrollX(), getScrollY(), r8 != 0 ? r8 : r15 - getScrollX(), 0, r15, r15, 0, 0, (r15 == 0 || r15 == max) ? width / 2 : 0, 0);
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

    private void smoothScrollToNextPage(int r5) {
        if (DEBUG_MODE) {
            FLog.m1314i(TAG, "smoothScrollToNextPage[%d] direction %d", Integer.valueOf(getId()), Integer.valueOf(r5));
        }
        int width = getWidth();
        int scrollX = getScrollX();
        int r2 = scrollX / width;
        if (scrollX % width != 0) {
            r2++;
        }
        int r22 = r5 == 17 ? r2 - 1 : r2 + 1;
        if (r22 < 0) {
            r22 = 0;
        }
        reactSmoothScrollTo(r22 * width, getScrollY());
        handlePostTouchScrolling(0, 0);
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

    public void reactSmoothScrollTo(int r1, int r2) {
        ReactScrollViewHelper.smoothScrollTo(this, r1, r2);
        setPendingContentOffsets(r1, r2);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void scrollTo(int r6, int r7) {
        if (DEBUG_MODE) {
            FLog.m1313i(TAG, "scrollTo[%d] x %d y %d", Integer.valueOf(getId()), Integer.valueOf(r6), Integer.valueOf(r7));
        }
        super.scrollTo(r6, r7);
        ReactScrollViewHelper.updateFabricScrollState(this);
        setPendingContentOffsets(r6, r7);
    }

    private boolean isContentReady() {
        View contentView = getContentView();
        return (contentView == null || contentView.getWidth() == 0 || contentView.getHeight() == 0) ? false : true;
    }

    private void setPendingContentOffsets(int r6, int r7) {
        if (DEBUG_MODE) {
            FLog.m1313i(TAG, "setPendingContentOffsets[%d] x %d y %d", Integer.valueOf(getId()), Integer.valueOf(r6), Integer.valueOf(r7));
        }
        if (isContentReady()) {
            this.pendingContentOffsetX = -1;
            this.pendingContentOffsetY = -1;
            return;
        }
        this.pendingContentOffsetX = r6;
        this.pendingContentOffsetY = r7;
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
        return ReactScrollViewHelper.predictFinalScrollPosition(this, r3, 0, Math.max(0, computeHorizontalScrollRange() - getWidth()), 0).x;
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
