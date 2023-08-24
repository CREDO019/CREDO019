package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes3.dex */
public class BaselineLayout extends ViewGroup {
    private int baseline;

    public BaselineLayout(Context context) {
        super(context, null, 0);
        this.baseline = -1;
    }

    public BaselineLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.baseline = -1;
    }

    public BaselineLayout(Context context, AttributeSet attributeSet, int r3) {
        super(context, attributeSet, r3);
        this.baseline = -1;
    }

    @Override // android.view.View
    protected void onMeasure(int r12, int r13) {
        int childCount = getChildCount();
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = -1;
        int r7 = -1;
        for (int r2 = 0; r2 < childCount; r2++) {
            View childAt = getChildAt(r2);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, r12, r13);
                int baseline = childAt.getBaseline();
                if (baseline != -1) {
                    r6 = Math.max(r6, baseline);
                    r7 = Math.max(r7, childAt.getMeasuredHeight() - baseline);
                }
                r4 = Math.max(r4, childAt.getMeasuredWidth());
                r3 = Math.max(r3, childAt.getMeasuredHeight());
                r5 = View.combineMeasuredStates(r5, childAt.getMeasuredState());
            }
        }
        if (r6 != -1) {
            r3 = Math.max(r3, Math.max(r7, getPaddingBottom()) + r6);
            this.baseline = r6;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(r4, getSuggestedMinimumWidth()), r12, r5), View.resolveSizeAndState(Math.max(r3, getSuggestedMinimumHeight()), r13, r5 << 16));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r8, int r9, int r10, int r11) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = ((r10 - r8) - getPaddingRight()) - paddingLeft;
        int paddingTop = getPaddingTop();
        for (int r112 = 0; r112 < childCount; r112++) {
            View childAt = getChildAt(r112);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int r3 = ((paddingRight - measuredWidth) / 2) + paddingLeft;
                int baseline = (this.baseline == -1 || childAt.getBaseline() == -1) ? paddingTop : (this.baseline + paddingTop) - childAt.getBaseline();
                childAt.layout(r3, baseline, measuredWidth + r3, measuredHeight + baseline);
            }
        }
    }

    @Override // android.view.View
    public int getBaseline() {
        return this.baseline;
    }
}
