package com.facebook.react.uimanager;

import android.os.Bundle;
import android.view.ViewGroup;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public interface ReactRoot {
    public static final int STATE_STARTED = 1;
    public static final int STATE_STOPPED = 0;

    Bundle getAppProperties();

    int getHeightMeasureSpec();

    String getInitialUITemplate();

    String getJSModuleName();

    ViewGroup getRootViewGroup();

    int getRootViewTag();

    AtomicInteger getState();

    @Deprecated
    String getSurfaceID();

    int getUIManagerType();

    int getWidthMeasureSpec();

    void onStage(int r1);

    void runApplication();

    void setRootViewTag(int r1);

    void setShouldLogContentAppeared(boolean z);
}
