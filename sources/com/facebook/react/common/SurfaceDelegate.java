package com.facebook.react.common;

/* loaded from: classes.dex */
public interface SurfaceDelegate {
    void createContentView(String str);

    void destroyContentView();

    void hide();

    boolean isContentViewReady();

    boolean isShowing();

    void show();
}
