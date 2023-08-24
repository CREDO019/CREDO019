package com.facebook.react.uimanager.util;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.C1413R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class ReactFindViewUtil {
    private static final List<OnViewFoundListener> mOnViewFoundListeners = new ArrayList();
    private static final Map<OnMultipleViewsFoundListener, Set<String>> mOnMultipleViewsFoundListener = new HashMap();

    /* loaded from: classes.dex */
    public interface OnMultipleViewsFoundListener {
        void onViewFound(View view, String str);
    }

    /* loaded from: classes.dex */
    public interface OnViewFoundListener {
        String getNativeId();

        void onViewFound(View view);
    }

    public static View findView(View view, String str) {
        String nativeId = getNativeId(view);
        if (nativeId == null || !nativeId.equals(str)) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int r0 = 0; r0 < viewGroup.getChildCount(); r0++) {
                    View findView = findView(viewGroup.getChildAt(r0), str);
                    if (findView != null) {
                        return findView;
                    }
                }
                return null;
            }
            return null;
        }
        return view;
    }

    public static void findView(View view, OnViewFoundListener onViewFoundListener) {
        View findView = findView(view, onViewFoundListener.getNativeId());
        if (findView != null) {
            onViewFoundListener.onViewFound(findView);
        }
        addViewListener(onViewFoundListener);
    }

    public static void addViewListener(OnViewFoundListener onViewFoundListener) {
        mOnViewFoundListeners.add(onViewFoundListener);
    }

    public static void removeViewListener(OnViewFoundListener onViewFoundListener) {
        mOnViewFoundListeners.remove(onViewFoundListener);
    }

    public static void addViewsListener(OnMultipleViewsFoundListener onMultipleViewsFoundListener, Set<String> set) {
        mOnMultipleViewsFoundListener.put(onMultipleViewsFoundListener, set);
    }

    public static void removeViewsListener(OnMultipleViewsFoundListener onMultipleViewsFoundListener) {
        mOnMultipleViewsFoundListener.remove(onMultipleViewsFoundListener);
    }

    public static void notifyViewRendered(View view) {
        String nativeId = getNativeId(view);
        if (nativeId == null) {
            return;
        }
        Iterator<OnViewFoundListener> it = mOnViewFoundListeners.iterator();
        while (it.hasNext()) {
            OnViewFoundListener next = it.next();
            if (nativeId != null && nativeId.equals(next.getNativeId())) {
                next.onViewFound(view);
                it.remove();
            }
        }
        for (Map.Entry<OnMultipleViewsFoundListener, Set<String>> entry : mOnMultipleViewsFoundListener.entrySet()) {
            Set<String> value = entry.getValue();
            if (value != null && value.contains(nativeId)) {
                entry.getKey().onViewFound(view, nativeId);
            }
        }
    }

    private static String getNativeId(View view) {
        Object tag = view.getTag(C1413R.C1417id.view_tag_native_id);
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }
}