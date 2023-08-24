package com.swmansion.reanimated.transitions;

import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionManager;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;

/* loaded from: classes3.dex */
public class TransitionModule {
    private final UIManagerModule mUIManager;

    public TransitionModule(UIManagerModule uIManagerModule) {
        this.mUIManager = uIManagerModule;
    }

    public void animateNextTransition(final int r3, final ReadableMap readableMap) {
        this.mUIManager.prependUIBlock(new UIBlock() { // from class: com.swmansion.reanimated.transitions.TransitionModule.1
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    View resolveView = nativeViewHierarchyManager.resolveView(r3);
                    if (resolveView instanceof ViewGroup) {
                        ReadableArray array = readableMap.getArray("transitions");
                        int size = array.size();
                        for (int r1 = 0; r1 < size; r1++) {
                            TransitionManager.beginDelayedTransition((ViewGroup) resolveView, TransitionUtils.inflate(array.getMap(r1)));
                        }
                    }
                } catch (IllegalViewOperationException unused) {
                }
            }
        });
    }
}
