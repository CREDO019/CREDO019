package com.facebook.react.views.view;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.updates.codesigning.CodeSigningAlgorithmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactViewManagerWrapper.kt */
@Metadata(m184d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\bf\u0018\u00002\u00020\u0001:\u0001%J4\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007H&J\"\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH&J\"\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH&J0\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\tH&J\u001a\u0010 \u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u0001H&J\u001a\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u0001H&J&\u0010$\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0015\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000eH&R\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006&"}, m183d2 = {"Lcom/facebook/react/views/view/ReactViewManagerWrapper;", "", "viewGroupManager", "Lcom/facebook/react/uimanager/ViewGroupManager;", "getViewGroupManager", "()Lcom/facebook/react/uimanager/ViewGroupManager;", "createView", "Landroid/view/View;", "reactTag", "", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "props", "stateWrapper", "Lcom/facebook/react/uimanager/StateWrapper;", "jsResponderHandler", "Lcom/facebook/react/touch/JSResponderHandler;", "getName", "", "onDropViewInstance", "", "view", "receiveCommand", CodeSigningAlgorithmKt.CODE_SIGNING_METADATA_DEFAULT_KEY_ID, "commandId", "args", "Lcom/facebook/react/bridge/ReadableArray;", "setPadding", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "updateExtraData", "extraData", "updateProperties", "viewToUpdate", "updateState", "DefaultViewManager", "ReactAndroid_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes.dex */
public interface ReactViewManagerWrapper {
    View createView(int r1, ThemedReactContext themedReactContext, Object obj, StateWrapper stateWrapper, JSResponderHandler jSResponderHandler);

    String getName();

    ViewGroupManager<?> getViewGroupManager();

    void onDropViewInstance(View view);

    void receiveCommand(View view, int r2, ReadableArray readableArray);

    void receiveCommand(View view, String str, ReadableArray readableArray);

    void setPadding(View view, int r2, int r3, int r4, int r5);

    void updateExtraData(View view, Object obj);

    void updateProperties(View view, Object obj);

    Object updateState(View view, Object obj, StateWrapper stateWrapper);

    /* compiled from: ReactViewManagerWrapper.kt */
    @Metadata(m184d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0017\u0012\u0010\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u0004\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0005J4\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0004H\u0016J\"\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\"\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J0\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\fH\u0016J\u001a\u0010$\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00042\b\u0010%\u001a\u0004\u0018\u00010\u0010H\u0016J\u001a\u0010&\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J&\u0010(\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0018\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u0004\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, m183d2 = {"Lcom/facebook/react/views/view/ReactViewManagerWrapper$DefaultViewManager;", "Lcom/facebook/react/views/view/ReactViewManagerWrapper;", "viewManager", "Lcom/facebook/react/uimanager/ViewManager;", "Landroid/view/View;", "(Lcom/facebook/react/uimanager/ViewManager;)V", "viewGroupManager", "Lcom/facebook/react/uimanager/ViewGroupManager;", "getViewGroupManager", "()Lcom/facebook/react/uimanager/ViewGroupManager;", "createView", "reactTag", "", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "props", "", "stateWrapper", "Lcom/facebook/react/uimanager/StateWrapper;", "jsResponderHandler", "Lcom/facebook/react/touch/JSResponderHandler;", "getName", "", "onDropViewInstance", "", "view", "receiveCommand", CodeSigningAlgorithmKt.CODE_SIGNING_METADATA_DEFAULT_KEY_ID, "commandId", "args", "Lcom/facebook/react/bridge/ReadableArray;", "setPadding", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "updateExtraData", "extraData", "updateProperties", "viewToUpdate", "updateState", "ReactAndroid_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes.dex */
    public static final class DefaultViewManager implements ReactViewManagerWrapper {
        private final ViewManager<View, ?> viewManager;

        public DefaultViewManager(ViewManager<View, ?> viewManager) {
            Intrinsics.checkNotNullParameter(viewManager, "viewManager");
            this.viewManager = viewManager;
        }

        @Override // com.facebook.react.views.view.ReactViewManagerWrapper
        public View createView(int r8, ThemedReactContext reactContext, Object obj, StateWrapper stateWrapper, JSResponderHandler jsResponderHandler) {
            Intrinsics.checkNotNullParameter(reactContext, "reactContext");
            Intrinsics.checkNotNullParameter(jsResponderHandler, "jsResponderHandler");
            View createView = this.viewManager.createView(r8, reactContext, obj instanceof ReactStylesDiffMap ? (ReactStylesDiffMap) obj : null, stateWrapper, jsResponderHandler);
            Intrinsics.checkNotNullExpressionValue(createView, "viewManager.createView(\n…pper, jsResponderHandler)");
            return createView;
        }

        @Override // com.facebook.react.views.view.ReactViewManagerWrapper
        public void updateProperties(View viewToUpdate, Object obj) {
            Intrinsics.checkNotNullParameter(viewToUpdate, "viewToUpdate");
            this.viewManager.updateProperties(viewToUpdate, obj instanceof ReactStylesDiffMap ? (ReactStylesDiffMap) obj : null);
        }

        @Override // com.facebook.react.views.view.ReactViewManagerWrapper
        public void receiveCommand(View root, String commandId, ReadableArray readableArray) {
            Intrinsics.checkNotNullParameter(root, "root");
            Intrinsics.checkNotNullParameter(commandId, "commandId");
            this.viewManager.receiveCommand((ViewManager<View, ?>) root, commandId, readableArray);
        }

        @Override // com.facebook.react.views.view.ReactViewManagerWrapper
        public void receiveCommand(View root, int r3, ReadableArray readableArray) {
            Intrinsics.checkNotNullParameter(root, "root");
            this.viewManager.receiveCommand((ViewManager<View, ?>) root, r3, readableArray);
        }

        @Override // com.facebook.react.views.view.ReactViewManagerWrapper
        public void setPadding(View view, int r9, int r10, int r11, int r12) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.viewManager.setPadding(view, r9, r10, r11, r12);
        }

        @Override // com.facebook.react.views.view.ReactViewManagerWrapper
        public Object updateState(View view, Object obj, StateWrapper stateWrapper) {
            Intrinsics.checkNotNullParameter(view, "view");
            return this.viewManager.updateState(view, obj instanceof ReactStylesDiffMap ? (ReactStylesDiffMap) obj : null, stateWrapper);
        }

        @Override // com.facebook.react.views.view.ReactViewManagerWrapper
        public void updateExtraData(View root, Object obj) {
            Intrinsics.checkNotNullParameter(root, "root");
            this.viewManager.updateExtraData(root, obj);
        }

        @Override // com.facebook.react.views.view.ReactViewManagerWrapper
        public void onDropViewInstance(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.viewManager.onDropViewInstance(view);
        }

        @Override // com.facebook.react.views.view.ReactViewManagerWrapper
        public String getName() {
            String name = this.viewManager.getName();
            Intrinsics.checkNotNullExpressionValue(name, "viewManager.name");
            return name;
        }

        @Override // com.facebook.react.views.view.ReactViewManagerWrapper
        public ViewGroupManager<?> getViewGroupManager() {
            return (ViewGroupManager) this.viewManager;
        }
    }
}
