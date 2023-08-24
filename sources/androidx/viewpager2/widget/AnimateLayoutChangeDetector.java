package androidx.viewpager2.widget;

import android.animation.LayoutTransition;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class AnimateLayoutChangeDetector {
    private static final ViewGroup.MarginLayoutParams ZERO_MARGIN_LAYOUT_PARAMS;
    private LinearLayoutManager mLayoutManager;

    static {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -1);
        ZERO_MARGIN_LAYOUT_PARAMS = marginLayoutParams;
        marginLayoutParams.setMargins(0, 0, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnimateLayoutChangeDetector(LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean mayHaveInterferingAnimations() {
        return (!arePagesLaidOutContiguously() || this.mLayoutManager.getChildCount() <= 1) && hasRunningChangingLayoutTransition();
    }

    private boolean arePagesLaidOutContiguously() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        int top;
        int r10;
        int bottom;
        int r7;
        int childCount = this.mLayoutManager.getChildCount();
        if (childCount == 0) {
            return true;
        }
        boolean z = this.mLayoutManager.getOrientation() == 0;
        int[][] r4 = (int[][]) Array.newInstance(int.class, childCount, 2);
        for (int r5 = 0; r5 < childCount; r5++) {
            View childAt = this.mLayoutManager.getChildAt(r5);
            if (childAt == null) {
                throw new IllegalStateException("null view contained in the view hierarchy");
            }
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            } else {
                marginLayoutParams = ZERO_MARGIN_LAYOUT_PARAMS;
            }
            int[] r8 = r4[r5];
            if (z) {
                top = childAt.getLeft();
                r10 = marginLayoutParams.leftMargin;
            } else {
                top = childAt.getTop();
                r10 = marginLayoutParams.topMargin;
            }
            r8[0] = top - r10;
            int[] r82 = r4[r5];
            if (z) {
                bottom = childAt.getRight();
                r7 = marginLayoutParams.rightMargin;
            } else {
                bottom = childAt.getBottom();
                r7 = marginLayoutParams.bottomMargin;
            }
            r82[1] = bottom + r7;
        }
        Arrays.sort(r4, new Comparator<int[]>() { // from class: androidx.viewpager2.widget.AnimateLayoutChangeDetector.1
            @Override // java.util.Comparator
            public int compare(int[] r2, int[] r3) {
                return r2[0] - r3[0];
            }
        });
        for (int r2 = 1; r2 < childCount; r2++) {
            if (r4[r2 - 1][1] != r4[r2][0]) {
                return false;
            }
        }
        return r4[0][0] <= 0 && r4[childCount - 1][1] >= r4[0][1] - r4[0][0];
    }

    private boolean hasRunningChangingLayoutTransition() {
        int childCount = this.mLayoutManager.getChildCount();
        for (int r2 = 0; r2 < childCount; r2++) {
            if (hasRunningChangingLayoutTransition(this.mLayoutManager.getChildAt(r2))) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasRunningChangingLayoutTransition(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            LayoutTransition layoutTransition = viewGroup.getLayoutTransition();
            if (layoutTransition != null && layoutTransition.isChangingLayout()) {
                return true;
            }
            int childCount = viewGroup.getChildCount();
            for (int r3 = 0; r3 < childCount; r3++) {
                if (hasRunningChangingLayoutTransition(viewGroup.getChildAt(r3))) {
                    return true;
                }
            }
        }
        return false;
    }
}
