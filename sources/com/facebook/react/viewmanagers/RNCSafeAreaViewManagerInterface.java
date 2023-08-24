package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;

/* loaded from: classes.dex */
public interface RNCSafeAreaViewManagerInterface<T extends View> {
    void setEdges(T t, ReadableArray readableArray);

    void setMode(T t, String str);
}
