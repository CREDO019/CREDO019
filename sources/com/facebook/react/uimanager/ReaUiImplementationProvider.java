package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.events.EventDispatcher;

/* compiled from: ReanimatedUIManager.java */
/* loaded from: classes.dex */
class ReaUiImplementationProvider extends UIImplementationProvider {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.facebook.react.uimanager.UIImplementationProvider
    public UIImplementation createUIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, int r5) {
        return new ReanimatedUIImplementation(reactApplicationContext, viewManagerRegistry, eventDispatcher, r5);
    }
}
