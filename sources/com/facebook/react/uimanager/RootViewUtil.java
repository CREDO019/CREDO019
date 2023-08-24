package com.facebook.react.uimanager;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
import com.facebook.infer.annotation.Assertions;

/* loaded from: classes.dex */
public class RootViewUtil {
    public static RootView getRootView(View view) {
        while (!(view instanceof RootView)) {
            ViewParent parent = view.getParent();
            if (parent == null) {
                return null;
            }
            Assertions.assertCondition(parent instanceof View);
            view = (View) parent;
        }
        return (RootView) view;
    }

    public static Point getViewportOffset(View view) {
        view.getLocationInWindow(r0);
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int[] r0 = {r0[0] - rect.left, r0[1] - rect.top};
        return new Point(r0[0], r0[1]);
    }
}
