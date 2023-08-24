package androidx.core.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

@Deprecated
/* loaded from: classes.dex */
public final class ScrollerCompat {
    OverScroller mScroller;

    @Deprecated
    public static ScrollerCompat create(Context context) {
        return create(context, null);
    }

    @Deprecated
    public static ScrollerCompat create(Context context, Interpolator interpolator) {
        return new ScrollerCompat(context, interpolator);
    }

    ScrollerCompat(Context context, Interpolator interpolator) {
        this.mScroller = interpolator != null ? new OverScroller(context, interpolator) : new OverScroller(context);
    }

    @Deprecated
    public boolean isFinished() {
        return this.mScroller.isFinished();
    }

    @Deprecated
    public int getCurrX() {
        return this.mScroller.getCurrX();
    }

    @Deprecated
    public int getCurrY() {
        return this.mScroller.getCurrY();
    }

    @Deprecated
    public int getFinalX() {
        return this.mScroller.getFinalX();
    }

    @Deprecated
    public int getFinalY() {
        return this.mScroller.getFinalY();
    }

    @Deprecated
    public float getCurrVelocity() {
        return this.mScroller.getCurrVelocity();
    }

    @Deprecated
    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }

    @Deprecated
    public void startScroll(int r2, int r3, int r4, int r5) {
        this.mScroller.startScroll(r2, r3, r4, r5);
    }

    @Deprecated
    public void startScroll(int r7, int r8, int r9, int r10, int r11) {
        this.mScroller.startScroll(r7, r8, r9, r10, r11);
    }

    @Deprecated
    public void fling(int r11, int r12, int r13, int r14, int r15, int r16, int r17, int r18) {
        this.mScroller.fling(r11, r12, r13, r14, r15, r16, r17, r18);
    }

    @Deprecated
    public void fling(int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20, int r21, int r22) {
        this.mScroller.fling(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22);
    }

    @Deprecated
    public boolean springBack(int r8, int r9, int r10, int r11, int r12, int r13) {
        return this.mScroller.springBack(r8, r9, r10, r11, r12, r13);
    }

    @Deprecated
    public void abortAnimation() {
        this.mScroller.abortAnimation();
    }

    @Deprecated
    public void notifyHorizontalEdgeReached(int r2, int r3, int r4) {
        this.mScroller.notifyHorizontalEdgeReached(r2, r3, r4);
    }

    @Deprecated
    public void notifyVerticalEdgeReached(int r2, int r3, int r4) {
        this.mScroller.notifyVerticalEdgeReached(r2, r3, r4);
    }

    @Deprecated
    public boolean isOverScrolled() {
        return this.mScroller.isOverScrolled();
    }
}
