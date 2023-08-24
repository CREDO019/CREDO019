package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.C2151R;

/* loaded from: classes3.dex */
public class FlowLayout extends ViewGroup {
    private int itemSpacing;
    private int lineSpacing;
    private boolean singleLine;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int r3) {
        super(context, attributeSet, r3);
        this.singleLine = false;
        loadFromAttributes(context, attributeSet);
    }

    public FlowLayout(Context context, AttributeSet attributeSet, int r3, int r4) {
        super(context, attributeSet, r3, r4);
        this.singleLine = false;
        loadFromAttributes(context, attributeSet);
    }

    private void loadFromAttributes(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2151R.styleable.FlowLayout, 0, 0);
        this.lineSpacing = obtainStyledAttributes.getDimensionPixelSize(C2151R.styleable.FlowLayout_lineSpacing, 0);
        this.itemSpacing = obtainStyledAttributes.getDimensionPixelSize(C2151R.styleable.FlowLayout_itemSpacing, 0);
        obtainStyledAttributes.recycle();
    }

    protected int getLineSpacing() {
        return this.lineSpacing;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setLineSpacing(int r1) {
        this.lineSpacing = r1;
    }

    protected int getItemSpacing() {
        return this.itemSpacing;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setItemSpacing(int r1) {
        this.itemSpacing = r1;
    }

    public boolean isSingleLine() {
        return this.singleLine;
    }

    public void setSingleLine(boolean z) {
        this.singleLine = z;
    }

    @Override // android.view.View
    protected void onMeasure(int r21, int r22) {
        int r8;
        int r15;
        int r6;
        int size = View.MeasureSpec.getSize(r21);
        int mode = View.MeasureSpec.getMode(r21);
        int size2 = View.MeasureSpec.getSize(r22);
        int mode2 = View.MeasureSpec.getMode(r22);
        int r5 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : Integer.MAX_VALUE;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = r5 - getPaddingRight();
        int r9 = paddingTop;
        int r11 = 0;
        for (int r10 = 0; r10 < getChildCount(); r10++) {
            View childAt = getChildAt(r10);
            if (childAt.getVisibility() != 8) {
                measureChild(childAt, r21, r22);
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    r8 = marginLayoutParams.leftMargin + 0;
                    r15 = marginLayoutParams.rightMargin + 0;
                } else {
                    r8 = 0;
                    r15 = 0;
                }
                int r19 = paddingLeft;
                if (paddingLeft + r8 + childAt.getMeasuredWidth() <= paddingRight || isSingleLine()) {
                    r6 = r19;
                } else {
                    r6 = getPaddingLeft();
                    r9 = this.lineSpacing + paddingTop;
                }
                int measuredWidth = r6 + r8 + childAt.getMeasuredWidth();
                int measuredHeight = r9 + childAt.getMeasuredHeight();
                if (measuredWidth > r11) {
                    r11 = measuredWidth;
                }
                paddingLeft = r6 + r8 + r15 + childAt.getMeasuredWidth() + this.itemSpacing;
                if (r10 == getChildCount() - 1) {
                    r11 += r15;
                }
                paddingTop = measuredHeight;
            }
        }
        setMeasuredDimension(getMeasuredDimension(size, mode, r11 + getPaddingRight()), getMeasuredDimension(size2, mode2, paddingTop + getPaddingBottom()));
    }

    private static int getMeasuredDimension(int r1, int r2, int r3) {
        if (r2 != Integer.MIN_VALUE) {
            return r2 != 1073741824 ? r3 : r1;
        }
        return Math.min(r3, r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r10, int r11, int r12, int r13) {
        int r4;
        int r5;
        if (getChildCount() == 0) {
            return;
        }
        boolean z2 = ViewCompat.getLayoutDirection(this) == 1;
        int paddingRight = z2 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = z2 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int r122 = (r12 - r10) - paddingLeft;
        int r2 = paddingRight;
        int r102 = paddingTop;
        for (int r0 = 0; r0 < getChildCount(); r0++) {
            View childAt = getChildAt(r0);
            if (childAt.getVisibility() != 8) {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    r5 = MarginLayoutParamsCompat.getMarginStart(marginLayoutParams);
                    r4 = MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams);
                } else {
                    r4 = 0;
                    r5 = 0;
                }
                int measuredWidth = r2 + r5 + childAt.getMeasuredWidth();
                if (!this.singleLine && measuredWidth > r122) {
                    r102 = this.lineSpacing + paddingTop;
                    r2 = paddingRight;
                }
                int r1 = r2 + r5;
                int measuredWidth2 = childAt.getMeasuredWidth() + r1;
                int measuredHeight = childAt.getMeasuredHeight() + r102;
                if (z2) {
                    childAt.layout(r122 - measuredWidth2, r102, (r122 - r2) - r5, measuredHeight);
                } else {
                    childAt.layout(r1, r102, measuredWidth2, measuredHeight);
                }
                r2 += r5 + r4 + childAt.getMeasuredWidth() + this.itemSpacing;
                paddingTop = measuredHeight;
            }
        }
    }
}
