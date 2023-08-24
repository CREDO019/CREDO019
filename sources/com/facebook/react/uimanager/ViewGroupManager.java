package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.List;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public abstract class ViewGroupManager<T extends ViewGroup> extends BaseViewManager<T, LayoutShadowNode> implements IViewManagerWithChildren {
    private static WeakHashMap<View, Integer> mZIndexHash = new WeakHashMap<>();

    @Override // com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return false;
    }

    public boolean shouldPromoteGrandchildren() {
        return false;
    }

    public void updateExtraData(T t, Object obj) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.ViewManager
    public /* bridge */ /* synthetic */ void updateExtraData(View view, Object obj) {
        updateExtraData((ViewGroupManager<T>) ((ViewGroup) view), obj);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new LayoutShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return LayoutShadowNode.class;
    }

    public void addView(T t, View view, int r3) {
        t.addView(view, r3);
    }

    public void addViews(T t, List<View> list) {
        UiThreadUtil.assertOnUiThread();
        int size = list.size();
        for (int r1 = 0; r1 < size; r1++) {
            addView(t, list.get(r1), r1);
        }
    }

    public static void setViewZIndex(View view, int r2) {
        mZIndexHash.put(view, Integer.valueOf(r2));
    }

    public static Integer getViewZIndex(View view) {
        return mZIndexHash.get(view);
    }

    public int getChildCount(T t) {
        return t.getChildCount();
    }

    public View getChildAt(T t, int r2) {
        return t.getChildAt(r2);
    }

    public void removeViewAt(T t, int r2) {
        UiThreadUtil.assertOnUiThread();
        t.removeViewAt(r2);
    }

    public void removeView(T t, View view) {
        UiThreadUtil.assertOnUiThread();
        for (int r0 = 0; r0 < getChildCount(t); r0++) {
            if (getChildAt(t, r0) == view) {
                removeViewAt(t, r0);
                return;
            }
        }
    }

    public void removeAllViews(T t) {
        UiThreadUtil.assertOnUiThread();
        for (int childCount = getChildCount(t) - 1; childCount >= 0; childCount--) {
            removeViewAt(t, childCount);
        }
    }
}
