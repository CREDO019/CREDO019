package com.facebook.react.viewmanagers;

import android.view.View;

/* loaded from: classes.dex */
public interface RNGestureHandlerButtonManagerInterface<T extends View> {
    void setBorderless(T t, boolean z);

    void setEnabled(T t, boolean z);

    void setExclusive(T t, boolean z);

    void setForeground(T t, boolean z);

    void setRippleColor(T t, Integer num);

    void setRippleRadius(T t, int r2);

    void setTouchSoundDisabled(T t, boolean z);
}
