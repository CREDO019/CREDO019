package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingProhibitedView;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactOverflowViewWithInset;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ReactZIndexedViewGroup;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewUtil;
import com.facebook.react.uimanager.ViewGroupDrawingOrderHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;
import com.facebook.yoga.YogaConstants;

/* loaded from: classes.dex */
public class ReactViewGroup extends ViewGroup implements ReactInterceptingViewGroup, ReactClippingViewGroup, ReactPointerEventsView, ReactHitSlopView, ReactZIndexedViewGroup, ReactOverflowViewWithInset {
    private static final int ARRAY_CAPACITY_INCREMENT = 12;
    private static final int DEFAULT_BACKGROUND_COLOR = 0;
    private static final ViewGroup.LayoutParams sDefaultLayoutParam = new ViewGroup.LayoutParams(0, 0);
    private static final Rect sHelperRect = new Rect();
    private View[] mAllChildren;
    private int mAllChildrenCount;
    private float mBackfaceOpacity;
    private String mBackfaceVisibility;
    private ChildrenLayoutChangeListener mChildrenLayoutChangeListener;
    private Rect mClippingRect;
    private ViewGroupDrawingOrderHelper mDrawingOrderHelper;
    private Rect mHitSlopRect;
    private int mLayoutDirection;
    private boolean mNeedsOffscreenAlphaCompositing;
    private OnInterceptTouchEventListener mOnInterceptTouchEventListener;
    private String mOverflow;
    private final Rect mOverflowInset;
    private Path mPath;
    private PointerEvents mPointerEvents;
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private boolean mRemoveClippedSubviews;

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSetPressed(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int r2, int r3, int r4, int r5) {
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ChildrenLayoutChangeListener implements View.OnLayoutChangeListener {
        private final ReactViewGroup mParent;

        private ChildrenLayoutChangeListener(ReactViewGroup reactViewGroup) {
            this.mParent = reactViewGroup;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            if (this.mParent.getRemoveClippedSubviews()) {
                this.mParent.updateSubviewClipStatus(view);
            }
        }
    }

    public ReactViewGroup(Context context) {
        super(context);
        this.mOverflowInset = new Rect();
        initView();
    }

    private void initView() {
        setClipChildren(false);
        this.mRemoveClippedSubviews = false;
        this.mAllChildren = null;
        this.mAllChildrenCount = 0;
        this.mClippingRect = null;
        this.mHitSlopRect = null;
        this.mOverflow = null;
        this.mPointerEvents = PointerEvents.AUTO;
        this.mChildrenLayoutChangeListener = null;
        this.mReactBackgroundDrawable = null;
        this.mOnInterceptTouchEventListener = null;
        this.mNeedsOffscreenAlphaCompositing = false;
        this.mDrawingOrderHelper = null;
        this.mPath = null;
        this.mLayoutDirection = 0;
        this.mBackfaceOpacity = 1.0f;
        this.mBackfaceVisibility = ViewProps.VISIBLE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recycleView() {
        initView();
        this.mOverflowInset.setEmpty();
        sHelperRect.setEmpty();
        removeAllViews();
        updateBackgroundDrawable(null);
        resetPointerEvents();
    }

    private ViewGroupDrawingOrderHelper getDrawingOrderHelper() {
        if (this.mDrawingOrderHelper == null) {
            this.mDrawingOrderHelper = new ViewGroupDrawingOrderHelper(this);
        }
        return this.mDrawingOrderHelper;
    }

    @Override // android.view.View
    protected void onMeasure(int r1, int r2) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(r1, r2);
        setMeasuredDimension(View.MeasureSpec.getSize(r1), View.MeasureSpec.getSize(r2));
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int r2) {
        ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.mReactBackgroundDrawable;
        if (reactViewBackgroundDrawable != null) {
            reactViewBackgroundDrawable.setResolvedLayoutDirection(this.mLayoutDirection);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        try {
            super.dispatchProvideStructure(viewStructure);
        } catch (NullPointerException e) {
            FLog.m1327e(ReactConstants.TAG, "NullPointerException when executing dispatchProvideStructure", e);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        if (r2 == 0 && this.mReactBackgroundDrawable == null) {
            return;
        }
        getOrCreateReactViewBackground().setColor(r2);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        throw new UnsupportedOperationException("This method is not supported for ReactViewGroup instances");
    }

    public void setTranslucentBackgroundDrawable(Drawable drawable) {
        updateBackgroundDrawable(null);
        if (this.mReactBackgroundDrawable != null && drawable != null) {
            updateBackgroundDrawable(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, drawable}));
        } else if (drawable != null) {
            updateBackgroundDrawable(drawable);
        }
    }

    @Override // com.facebook.react.touch.ReactInterceptingViewGroup
    public void setOnInterceptTouchEventListener(OnInterceptTouchEventListener onInterceptTouchEventListener) {
        this.mOnInterceptTouchEventListener = onInterceptTouchEventListener;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        OnInterceptTouchEventListener onInterceptTouchEventListener = this.mOnInterceptTouchEventListener;
        if ((onInterceptTouchEventListener == null || !onInterceptTouchEventListener.onInterceptTouchEvent(this, motionEvent)) && PointerEvents.canChildrenBeTouchTarget(this.mPointerEvents)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return PointerEvents.canBeTouchTarget(this.mPointerEvents);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchGenericPointerEvent(MotionEvent motionEvent) {
        if (PointerEvents.canChildrenBeTouchTarget(this.mPointerEvents)) {
            return super.dispatchGenericPointerEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return this.mNeedsOffscreenAlphaCompositing;
    }

    public void setNeedsOffscreenAlphaCompositing(boolean z) {
        this.mNeedsOffscreenAlphaCompositing = z;
    }

    public void setBorderWidth(int r2, float f) {
        getOrCreateReactViewBackground().setBorderWidth(r2, f);
    }

    public void setBorderColor(int r2, float f, float f2) {
        getOrCreateReactViewBackground().setBorderColor(r2, f, f2);
    }

    public void setBorderRadius(float f) {
        getOrCreateReactViewBackground().setRadius(f);
    }

    public void setBorderRadius(float f, int r3) {
        getOrCreateReactViewBackground().setRadius(f, r3);
    }

    public void setBorderStyle(String str) {
        getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z == this.mRemoveClippedSubviews) {
            return;
        }
        this.mRemoveClippedSubviews = z;
        if (z) {
            Rect rect = new Rect();
            this.mClippingRect = rect;
            ReactClippingViewGroupHelper.calculateClippingRect(this, rect);
            int childCount = getChildCount();
            this.mAllChildrenCount = childCount;
            this.mAllChildren = new View[Math.max(12, childCount)];
            this.mChildrenLayoutChangeListener = new ChildrenLayoutChangeListener();
            for (int r0 = 0; r0 < this.mAllChildrenCount; r0++) {
                View childAt = getChildAt(r0);
                this.mAllChildren[r0] = childAt;
                childAt.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
            }
            updateClippingRect();
            return;
        }
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        Assertions.assertNotNull(this.mChildrenLayoutChangeListener);
        for (int r5 = 0; r5 < this.mAllChildrenCount; r5++) {
            this.mAllChildren[r5].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        }
        getDrawingRect(this.mClippingRect);
        updateClippingToRect(this.mClippingRect);
        this.mAllChildren = null;
        this.mClippingRect = null;
        this.mAllChildrenCount = 0;
        this.mChildrenLayoutChangeListener = null;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void getClippingRect(Rect rect) {
        rect.set(this.mClippingRect);
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            Assertions.assertNotNull(this.mClippingRect);
            Assertions.assertNotNull(this.mAllChildren);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            updateClippingToRect(this.mClippingRect);
        }
    }

    private void updateClippingToRect(Rect rect) {
        Assertions.assertNotNull(this.mAllChildren);
        int r1 = 0;
        for (int r0 = 0; r0 < this.mAllChildrenCount; r0++) {
            updateSubviewClipStatus(rect, r0, r1);
            if (this.mAllChildren[r0].getParent() == null) {
                r1++;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0060, code lost:
        if (r7 != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateSubviewClipStatus(android.graphics.Rect r7, int r8, int r9) {
        /*
            r6 = this;
            com.facebook.react.bridge.UiThreadUtil.assertOnUiThread()
            android.view.View[] r0 = r6.mAllChildren
            java.lang.Object r0 = com.facebook.infer.annotation.Assertions.assertNotNull(r0)
            android.view.View[] r0 = (android.view.View[]) r0
            r0 = r0[r8]
            android.graphics.Rect r1 = com.facebook.react.views.view.ReactViewGroup.sHelperRect
            int r2 = r0.getLeft()
            int r3 = r0.getTop()
            int r4 = r0.getRight()
            int r5 = r0.getBottom()
            r1.set(r2, r3, r4, r5)
            int r2 = r1.left
            int r3 = r1.top
            int r4 = r1.right
            int r1 = r1.bottom
            boolean r7 = r7.intersects(r2, r3, r4, r1)
            android.view.animation.Animation r1 = r0.getAnimation()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L3e
            boolean r1 = r1.hasEnded()
            if (r1 != 0) goto L3e
            r1 = 1
            goto L3f
        L3e:
            r1 = 0
        L3f:
            if (r7 != 0) goto L4e
            android.view.ViewParent r4 = r0.getParent()
            if (r4 == 0) goto L4e
            if (r1 != 0) goto L4e
            int r8 = r8 - r9
            super.removeViewsInLayout(r8, r3)
            goto L62
        L4e:
            if (r7 == 0) goto L60
            android.view.ViewParent r1 = r0.getParent()
            if (r1 != 0) goto L60
            int r8 = r8 - r9
            android.view.ViewGroup$LayoutParams r7 = com.facebook.react.views.view.ReactViewGroup.sDefaultLayoutParam
            super.addViewInLayout(r0, r8, r7, r3)
            r6.invalidate()
            goto L62
        L60:
            if (r7 == 0) goto L63
        L62:
            r2 = 1
        L63:
            if (r2 == 0) goto L74
            boolean r7 = r0 instanceof com.facebook.react.uimanager.ReactClippingViewGroup
            if (r7 == 0) goto L74
            com.facebook.react.uimanager.ReactClippingViewGroup r0 = (com.facebook.react.uimanager.ReactClippingViewGroup) r0
            boolean r7 = r0.getRemoveClippedSubviews()
            if (r7 == 0) goto L74
            r0.updateClippingRect()
        L74:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewGroup.updateSubviewClipStatus(android.graphics.Rect, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSubviewClipStatus(View view) {
        if (!this.mRemoveClippedSubviews || getParent() == null) {
            return;
        }
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        Rect rect = sHelperRect;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if (this.mClippingRect.intersects(rect.left, rect.top, rect.right, rect.bottom) != (view.getParent() != null)) {
            int r0 = 0;
            for (int r2 = 0; r2 < this.mAllChildrenCount; r2++) {
                View[] viewArr = this.mAllChildren;
                if (viewArr[r2] == view) {
                    updateSubviewClipStatus(this.mClippingRect, r2, r0);
                    return;
                }
                if (viewArr[r2].getParent() == null) {
                    r0++;
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean getChildVisibleRect(View view, Rect rect, Point point) {
        return super.getChildVisibleRect(view, rect, point);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    private boolean customDrawOrderDisabled() {
        return getId() != -1 && ViewUtil.getUIManagerType(getId()) == 2;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int r3, ViewGroup.LayoutParams layoutParams) {
        if (!customDrawOrderDisabled()) {
            getDrawingOrderHelper().handleAddView(view);
            setChildrenDrawingOrderEnabled(getDrawingOrderHelper().shouldEnableCustomDrawingOrder());
        } else {
            setChildrenDrawingOrderEnabled(false);
        }
        super.addView(view, r3, layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        UiThreadUtil.assertOnUiThread();
        if (!customDrawOrderDisabled()) {
            getDrawingOrderHelper().handleRemoveView(view);
            setChildrenDrawingOrderEnabled(getDrawingOrderHelper().shouldEnableCustomDrawingOrder());
        } else {
            setChildrenDrawingOrderEnabled(false);
        }
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int r3) {
        UiThreadUtil.assertOnUiThread();
        if (!customDrawOrderDisabled()) {
            getDrawingOrderHelper().handleRemoveView(getChildAt(r3));
            setChildrenDrawingOrderEnabled(getDrawingOrderHelper().shouldEnableCustomDrawingOrder());
        } else {
            setChildrenDrawingOrderEnabled(false);
        }
        super.removeViewAt(r3);
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int r2, int r3) {
        UiThreadUtil.assertOnUiThread();
        return !customDrawOrderDisabled() ? getDrawingOrderHelper().getChildDrawingOrder(r2, r3) : r3;
    }

    @Override // com.facebook.react.uimanager.ReactZIndexedViewGroup
    public int getZIndexMappedChildIndex(int r3) {
        UiThreadUtil.assertOnUiThread();
        return (customDrawOrderDisabled() || !getDrawingOrderHelper().shouldEnableCustomDrawingOrder()) ? r3 : getDrawingOrderHelper().getChildDrawingOrder(getChildCount(), r3);
    }

    @Override // com.facebook.react.uimanager.ReactZIndexedViewGroup
    public void updateDrawingOrder() {
        if (customDrawOrderDisabled()) {
            return;
        }
        getDrawingOrderHelper().update();
        setChildrenDrawingOrderEnabled(getDrawingOrderHelper().shouldEnableCustomDrawingOrder());
        invalidate();
    }

    @Override // com.facebook.react.uimanager.ReactPointerEventsView
    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    void resetPointerEvents() {
        this.mPointerEvents = PointerEvents.AUTO;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getAllChildrenCount() {
        return this.mAllChildrenCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getChildAtWithSubviewClippingEnabled(int r2) {
        return ((View[]) Assertions.assertNotNull(this.mAllChildren))[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addViewWithSubviewClippingEnabled(View view, int r3) {
        addViewWithSubviewClippingEnabled(view, r3, sDefaultLayoutParam);
    }

    void addViewWithSubviewClippingEnabled(final View view, int r4, ViewGroup.LayoutParams layoutParams) {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        addInArray(view, r4);
        int r0 = 0;
        for (int r5 = 0; r5 < r4; r5++) {
            if (this.mAllChildren[r5].getParent() == null) {
                r0++;
            }
        }
        updateSubviewClipStatus(this.mClippingRect, r4, r0);
        view.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        if (view instanceof ReactClippingProhibitedView) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.views.view.ReactViewGroup.1
                @Override // java.lang.Runnable
                public void run() {
                    if (view.isShown()) {
                        return;
                    }
                    ReactSoftExceptionLogger.logSoftException(ReactConstants.TAG, new ReactNoCrashSoftException("Child view has been added to Parent view in which it is clipped and not visible. This is not legal for this particular child view. Child: [" + view.getId() + "] " + view.toString() + " Parent: [" + ReactViewGroup.this.getId() + "] " + toString()));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeViewWithSubviewClippingEnabled(View view) {
        UiThreadUtil.assertOnUiThread();
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        view.removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        int indexOfChildInAllChildren = indexOfChildInAllChildren(view);
        if (this.mAllChildren[indexOfChildInAllChildren].getParent() != null) {
            int r1 = 0;
            for (int r0 = 0; r0 < indexOfChildInAllChildren; r0++) {
                if (this.mAllChildren[r0].getParent() == null) {
                    r1++;
                }
            }
            super.removeViewsInLayout(indexOfChildInAllChildren - r1, 1);
        }
        removeFromArray(indexOfChildInAllChildren);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeAllViewsWithSubviewClippingEnabled() {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mAllChildren);
        for (int r1 = 0; r1 < this.mAllChildrenCount; r1++) {
            this.mAllChildren[r1].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        }
        removeAllViewsInLayout();
        this.mAllChildrenCount = 0;
    }

    private int indexOfChildInAllChildren(View view) {
        int r0 = this.mAllChildrenCount;
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        for (int r2 = 0; r2 < r0; r2++) {
            if (viewArr[r2] == view) {
                return r2;
            }
        }
        return -1;
    }

    private void addInArray(View view, int r6) {
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        int r1 = this.mAllChildrenCount;
        int length = viewArr.length;
        if (r6 == r1) {
            if (length == r1) {
                View[] viewArr2 = new View[length + 12];
                this.mAllChildren = viewArr2;
                System.arraycopy(viewArr, 0, viewArr2, 0, length);
                viewArr = this.mAllChildren;
            }
            int r62 = this.mAllChildrenCount;
            this.mAllChildrenCount = r62 + 1;
            viewArr[r62] = view;
        } else if (r6 < r1) {
            if (length == r1) {
                View[] viewArr3 = new View[length + 12];
                this.mAllChildren = viewArr3;
                System.arraycopy(viewArr, 0, viewArr3, 0, r6);
                System.arraycopy(viewArr, r6, this.mAllChildren, r6 + 1, r1 - r6);
                viewArr = this.mAllChildren;
            } else {
                System.arraycopy(viewArr, r6, viewArr, r6 + 1, r1 - r6);
            }
            viewArr[r6] = view;
            this.mAllChildrenCount++;
        } else {
            throw new IndexOutOfBoundsException("index=" + r6 + " count=" + r1);
        }
    }

    private void removeFromArray(int r5) {
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        int r1 = this.mAllChildrenCount;
        if (r5 == r1 - 1) {
            int r12 = r1 - 1;
            this.mAllChildrenCount = r12;
            viewArr[r12] = null;
        } else if (r5 >= 0 && r5 < r1) {
            System.arraycopy(viewArr, r5 + 1, viewArr, r5, (r1 - r5) - 1);
            int r52 = this.mAllChildrenCount - 1;
            this.mAllChildrenCount = r52;
            viewArr[r52] = null;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getBackgroundColor() {
        if (getBackground() != null) {
            return ((ReactViewBackgroundDrawable) getBackground()).getColor();
        }
        return 0;
    }

    ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable(getContext());
            Drawable background = getBackground();
            updateBackgroundDrawable(null);
            if (background == null) {
                updateBackgroundDrawable(this.mReactBackgroundDrawable);
            } else {
                updateBackgroundDrawable(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, background}));
            }
            boolean isRTL = I18nUtil.getInstance().isRTL(getContext());
            this.mLayoutDirection = isRTL ? 1 : 0;
            this.mReactBackgroundDrawable.setResolvedLayoutDirection(isRTL ? 1 : 0);
        }
        return this.mReactBackgroundDrawable;
    }

    @Override // com.facebook.react.touch.ReactHitSlopView
    public Rect getHitSlopRect() {
        return this.mHitSlopRect;
    }

    public void setHitSlopRect(Rect rect) {
        this.mHitSlopRect = rect;
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

    void updateBackgroundDrawable(Drawable drawable) {
        super.setBackground(drawable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        try {
            dispatchOverflowDraw(canvas);
            super.dispatchDraw(canvas);
        } catch (NullPointerException | StackOverflowError e) {
            RootView rootView = RootViewUtil.getRootView(this);
            if (rootView != null) {
                rootView.handleException(e);
            } else if (getContext() instanceof ReactContext) {
                ((ReactContext) getContext()).handleException(new IllegalViewOperationException("StackOverflowException", this, e));
            } else {
                throw e;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        boolean z = view.getElevation() > 0.0f && ReactFeatureFlags.insertZReorderBarriersOnViewGroupChildren;
        if (z) {
            CanvasUtil.enableZ(canvas, true);
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        if (z) {
            CanvasUtil.enableZ(canvas, false);
        }
        return drawChild;
    }

    private void dispatchOverflowDraw(Canvas canvas) {
        boolean z;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        String str = this.mOverflow;
        if (str != null) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1217487446:
                    if (str.equals("hidden")) {
                        c = 0;
                        break;
                    }
                    break;
                case -907680051:
                    if (str.equals(ViewProps.SCROLL)) {
                        c = 1;
                        break;
                    }
                    break;
                case 466743410:
                    if (str.equals(ViewProps.VISIBLE)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                    float width = getWidth();
                    float height = getHeight();
                    ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.mReactBackgroundDrawable;
                    if (reactViewBackgroundDrawable != null) {
                        RectF directionAwareBorderInsets = reactViewBackgroundDrawable.getDirectionAwareBorderInsets();
                        if (directionAwareBorderInsets.top > 0.0f || directionAwareBorderInsets.left > 0.0f || directionAwareBorderInsets.bottom > 0.0f || directionAwareBorderInsets.right > 0.0f) {
                            f3 = directionAwareBorderInsets.left + 0.0f;
                            f2 = directionAwareBorderInsets.top + 0.0f;
                            width -= directionAwareBorderInsets.right;
                            height -= directionAwareBorderInsets.bottom;
                        } else {
                            f3 = 0.0f;
                            f2 = 0.0f;
                        }
                        float fullBorderRadius = this.mReactBackgroundDrawable.getFullBorderRadius();
                        float borderRadiusOrDefaultTo = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_LEFT);
                        float borderRadiusOrDefaultTo2 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_RIGHT);
                        float borderRadiusOrDefaultTo3 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_LEFT);
                        float borderRadiusOrDefaultTo4 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_RIGHT);
                        boolean z2 = this.mLayoutDirection == 1;
                        float borderRadius = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_START);
                        float borderRadius2 = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_END);
                        float borderRadius3 = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_START);
                        float borderRadius4 = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_END);
                        if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(getContext())) {
                            f5 = YogaConstants.isUndefined(borderRadius) ? borderRadiusOrDefaultTo : borderRadius;
                            if (!YogaConstants.isUndefined(borderRadius2)) {
                                borderRadiusOrDefaultTo2 = borderRadius2;
                            }
                            if (!YogaConstants.isUndefined(borderRadius3)) {
                                borderRadiusOrDefaultTo3 = borderRadius3;
                            }
                            if (YogaConstants.isUndefined(borderRadius4)) {
                                borderRadius4 = borderRadiusOrDefaultTo4;
                            }
                            f4 = z2 ? borderRadiusOrDefaultTo2 : f5;
                            if (!z2) {
                                f5 = borderRadiusOrDefaultTo2;
                            }
                            f6 = z2 ? borderRadius4 : borderRadiusOrDefaultTo3;
                            if (z2) {
                                borderRadius4 = borderRadiusOrDefaultTo3;
                            }
                        } else {
                            float f7 = z2 ? borderRadius2 : borderRadius;
                            if (!z2) {
                                borderRadius = borderRadius2;
                            }
                            float f8 = z2 ? borderRadius4 : borderRadius3;
                            if (!z2) {
                                borderRadius3 = borderRadius4;
                            }
                            if (YogaConstants.isUndefined(f7)) {
                                f7 = borderRadiusOrDefaultTo;
                            }
                            if (!YogaConstants.isUndefined(borderRadius)) {
                                borderRadiusOrDefaultTo2 = borderRadius;
                            }
                            if (!YogaConstants.isUndefined(f8)) {
                                borderRadiusOrDefaultTo3 = f8;
                            }
                            if (YogaConstants.isUndefined(borderRadius3)) {
                                f4 = f7;
                                f5 = borderRadiusOrDefaultTo2;
                                f6 = borderRadiusOrDefaultTo3;
                                borderRadius4 = borderRadiusOrDefaultTo4;
                            } else {
                                borderRadius4 = borderRadius3;
                                f4 = f7;
                                f5 = borderRadiusOrDefaultTo2;
                                f6 = borderRadiusOrDefaultTo3;
                            }
                        }
                        if (f4 > 0.0f || f5 > 0.0f || borderRadius4 > 0.0f || f6 > 0.0f) {
                            if (this.mPath == null) {
                                this.mPath = new Path();
                            }
                            this.mPath.rewind();
                            this.mPath.addRoundRect(new RectF(f3, f2, width, height), new float[]{Math.max(f4 - directionAwareBorderInsets.left, 0.0f), Math.max(f4 - directionAwareBorderInsets.top, 0.0f), Math.max(f5 - directionAwareBorderInsets.right, 0.0f), Math.max(f5 - directionAwareBorderInsets.top, 0.0f), Math.max(borderRadius4 - directionAwareBorderInsets.right, 0.0f), Math.max(borderRadius4 - directionAwareBorderInsets.bottom, 0.0f), Math.max(f6 - directionAwareBorderInsets.left, 0.0f), Math.max(f6 - directionAwareBorderInsets.bottom, 0.0f)}, Path.Direction.CW);
                            canvas.clipPath(this.mPath);
                            f = f3;
                            z = true;
                        } else {
                            f = f3;
                            z = false;
                        }
                    } else {
                        z = false;
                        f = 0.0f;
                        f2 = 0.0f;
                    }
                    if (z) {
                        return;
                    }
                    canvas.clipRect(new RectF(f, f2, width, height));
                    return;
                case 2:
                    Path path = this.mPath;
                    if (path != null) {
                        path.rewind();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void setOpacityIfPossible(float f) {
        this.mBackfaceOpacity = f;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setBackfaceVisibility(String str) {
        this.mBackfaceVisibility = str;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setBackfaceVisibilityDependantOpacity() {
        if (this.mBackfaceVisibility.equals(ViewProps.VISIBLE)) {
            setAlpha(this.mBackfaceOpacity);
            return;
        }
        float rotationX = getRotationX();
        float rotationY = getRotationY();
        if (rotationX >= -90.0f && rotationX < 90.0f && rotationY >= -90.0f && rotationY < 90.0f) {
            setAlpha(this.mBackfaceOpacity);
        } else {
            setAlpha(0.0f);
        }
    }
}
