package com.facebook.react.bridge;

import android.view.View;
import java.util.List;

/* loaded from: classes.dex */
public interface UIManager extends JSIModule, PerformanceCounter {
    @Deprecated
    <T extends View> int addRootView(T t, WritableMap writableMap, String str);

    void addUIManagerEventListener(UIManagerListener uIManagerListener);

    void dispatchCommand(int r1, int r2, ReadableArray readableArray);

    void dispatchCommand(int r1, String str, ReadableArray readableArray);

    <T> T getEventDispatcher();

    @Deprecated
    void preInitializeViewManagers(List<String> list);

    void receiveEvent(int r1, int r2, String str, WritableMap writableMap);

    @Deprecated
    void receiveEvent(int r1, String str, WritableMap writableMap);

    void removeUIManagerEventListener(UIManagerListener uIManagerListener);

    @Deprecated
    String resolveCustomDirectEventName(String str);

    View resolveView(int r1);

    void sendAccessibilityEvent(int r1, int r2);

    <T extends View> int startSurface(T t, String str, WritableMap writableMap, int r4, int r5);

    void stopSurface(int r1);

    void synchronouslyUpdateViewOnUIThread(int r1, ReadableMap readableMap);

    void updateRootLayoutSpecs(int r1, int r2, int r3, int r4, int r5);
}
