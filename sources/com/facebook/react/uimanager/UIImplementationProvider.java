package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.systrace.Systrace;
import java.util.List;

@Deprecated
/* loaded from: classes.dex */
public class UIImplementationProvider {
    public UIImplementation createUIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerResolver viewManagerResolver, EventDispatcher eventDispatcher, int r7) {
        Systrace.beginSection(0L, "UIImplementationProvider.createUIImplementation[1]");
        try {
            return new UIImplementation(reactApplicationContext, viewManagerResolver, eventDispatcher, r7);
        } finally {
            Systrace.endSection(0L);
        }
    }

    public UIImplementation createUIImplementation(ReactApplicationContext reactApplicationContext, List<ViewManager> list, EventDispatcher eventDispatcher, int r7) {
        Systrace.beginSection(0L, "UIImplementationProvider.createUIImplementation[2]");
        try {
            return new UIImplementation(reactApplicationContext, list, eventDispatcher, r7);
        } finally {
            Systrace.endSection(0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UIImplementation createUIImplementation(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, int r7) {
        Systrace.beginSection(0L, "UIImplementationProvider.createUIImplementation[3]");
        try {
            return new UIImplementation(reactApplicationContext, viewManagerRegistry, eventDispatcher, r7);
        } finally {
            Systrace.endSection(0L);
        }
    }
}
