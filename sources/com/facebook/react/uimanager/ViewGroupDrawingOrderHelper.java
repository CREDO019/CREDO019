package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes.dex */
public class ViewGroupDrawingOrderHelper {
    private int[] mDrawingOrderIndices;
    private int mNumberOfChildrenWithZIndex = 0;
    private final ViewGroup mViewGroup;

    public ViewGroupDrawingOrderHelper(ViewGroup viewGroup) {
        this.mViewGroup = viewGroup;
    }

    public void handleAddView(View view) {
        if (ViewGroupManager.getViewZIndex(view) != null) {
            this.mNumberOfChildrenWithZIndex++;
        }
        this.mDrawingOrderIndices = null;
    }

    public void handleRemoveView(View view) {
        if (ViewGroupManager.getViewZIndex(view) != null) {
            this.mNumberOfChildrenWithZIndex--;
        }
        this.mDrawingOrderIndices = null;
    }

    public boolean shouldEnableCustomDrawingOrder() {
        return this.mNumberOfChildrenWithZIndex > 0;
    }

    public int getChildDrawingOrder(int r6, int r7) {
        if (this.mDrawingOrderIndices == null) {
            ArrayList arrayList = new ArrayList();
            for (int r2 = 0; r2 < r6; r2++) {
                arrayList.add(this.mViewGroup.getChildAt(r2));
            }
            Collections.sort(arrayList, new Comparator<View>() { // from class: com.facebook.react.uimanager.ViewGroupDrawingOrderHelper.1
                @Override // java.util.Comparator
                public int compare(View view, View view2) {
                    Integer viewZIndex = ViewGroupManager.getViewZIndex(view);
                    if (viewZIndex == null) {
                        viewZIndex = r0;
                    }
                    Integer viewZIndex2 = ViewGroupManager.getViewZIndex(view2);
                    return viewZIndex.intValue() - (viewZIndex2 != null ? viewZIndex2 : 0).intValue();
                }
            });
            this.mDrawingOrderIndices = new int[r6];
            for (int r1 = 0; r1 < r6; r1++) {
                this.mDrawingOrderIndices[r1] = this.mViewGroup.indexOfChild((View) arrayList.get(r1));
            }
        }
        return this.mDrawingOrderIndices[r7];
    }

    public void update() {
        this.mNumberOfChildrenWithZIndex = 0;
        for (int r0 = 0; r0 < this.mViewGroup.getChildCount(); r0++) {
            if (ViewGroupManager.getViewZIndex(this.mViewGroup.getChildAt(r0)) != null) {
                this.mNumberOfChildrenWithZIndex++;
            }
        }
        this.mDrawingOrderIndices = null;
    }
}
