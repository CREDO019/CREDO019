package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.reanimated.layoutReanimation.ReanimatedNativeHierarchyManager;
import java.util.List;

/* loaded from: classes.dex */
public class ReanimatedUIImplementation extends UIImplementation {
    public ReanimatedUIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerResolver viewManagerResolver, EventDispatcher eventDispatcher, int r5) {
        this(reactApplicationContext, new ViewManagerRegistry(viewManagerResolver), eventDispatcher, r5);
    }

    public ReanimatedUIImplementation(ReactApplicationContext reactApplicationContext, List<ViewManager> list, EventDispatcher eventDispatcher, int r5) {
        this(reactApplicationContext, new ViewManagerRegistry(list), eventDispatcher, r5);
    }

    public ReanimatedUIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, int r6) {
        super(reactApplicationContext, viewManagerRegistry, new UIViewOperationQueue(reactApplicationContext, new ReanimatedNativeHierarchyManager(viewManagerRegistry, reactApplicationContext), r6), eventDispatcher);
    }

    @Override // com.facebook.react.uimanager.UIImplementation
    public void manageChildren(int r1, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        super.manageChildren(r1, readableArray, readableArray2, readableArray3, readableArray4, readableArray5);
    }
}
