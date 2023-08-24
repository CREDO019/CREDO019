package com.swmansion.reanimated;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootViewUtil;
import com.facebook.react.views.scroll.ReactHorizontalScrollView;
import com.facebook.react.views.scroll.ReactScrollView;
import com.facebook.react.views.swiperefresh.ReactSwipeRefreshLayout;

/* loaded from: classes3.dex */
public class NativeMethodsHelper {
    public static float[] measure(View view) {
        View view2 = (View) RootViewUtil.getRootView(view);
        if (view2 == null || view == null) {
            float[] fArr = new float[6];
            fArr[0] = -1234567.0f;
            return fArr;
        }
        computeBoundingBox(view2, r3);
        int r0 = r3[0];
        int r5 = r3[1];
        computeBoundingBox(view, r3);
        int[] r3 = {r3[0] - r0, r3[1] - r5};
        float[] fArr2 = new float[6];
        fArr2[1] = 0.0f;
        fArr2[0] = 0.0f;
        for (int r02 = 2; r02 < 6; r02++) {
            fArr2[r02] = PixelUtil.toDIPFromPixel(r3[r02 - 2]);
        }
        return fArr2;
    }

    public static void scrollTo(View view, double d, double d2, boolean z) {
        boolean z2;
        int round = Math.round(PixelUtil.toPixelFromDIP(d));
        int round2 = Math.round(PixelUtil.toPixelFromDIP(d2));
        if (view instanceof ReactHorizontalScrollView) {
            z2 = true;
        } else {
            if (view instanceof ReactSwipeRefreshLayout) {
                view = findScrollView((ReactSwipeRefreshLayout) view);
            }
            if (!(view instanceof ReactScrollView)) {
                Log.w("REANIMATED", "NativeMethodsHelper: Unhandled scroll view type - allowed only {ReactScrollView, ReactHorizontalScrollView}");
                return;
            }
            z2 = false;
        }
        if (z) {
            if (z2) {
                ((ReactHorizontalScrollView) view).smoothScrollTo(round, round2);
            } else {
                ((ReactScrollView) view).smoothScrollTo(round, round2);
            }
        } else if (z2) {
            ((ReactHorizontalScrollView) view).scrollTo(round, round2);
        } else {
            ((ReactScrollView) view).scrollTo(round, round2);
        }
    }

    private static ReactScrollView findScrollView(ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
        for (int r0 = 0; r0 < reactSwipeRefreshLayout.getChildCount(); r0++) {
            if (reactSwipeRefreshLayout.getChildAt(r0) instanceof ReactScrollView) {
                return (ReactScrollView) reactSwipeRefreshLayout.getChildAt(r0);
            }
        }
        return null;
    }

    private static void computeBoundingBox(View view, int[] r5) {
        RectF rectF = new RectF();
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        mapRectFromViewToWindowCoords(view, rectF);
        r5[0] = Math.round(rectF.left);
        r5[1] = Math.round(rectF.top);
        r5[2] = Math.round(rectF.right - rectF.left);
        r5[3] = Math.round(rectF.bottom - rectF.top);
    }

    private static void mapRectFromViewToWindowCoords(View view, RectF rectF) {
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            matrix.mapRect(rectF);
        }
        rectF.offset(view.getLeft(), view.getTop());
        ViewParent parent = view.getParent();
        while (parent instanceof View) {
            View view2 = (View) parent;
            rectF.offset(-view2.getScrollX(), -view2.getScrollY());
            Matrix matrix2 = view2.getMatrix();
            if (!matrix2.isIdentity()) {
                matrix2.mapRect(rectF);
            }
            rectF.offset(view2.getLeft(), view2.getTop());
            parent = view2.getParent();
        }
    }
}
