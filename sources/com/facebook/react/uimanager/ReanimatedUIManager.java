package com.facebook.react.uimanager;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import java.util.List;

@ReactModule(name = UIManagerModule.NAME)
/* loaded from: classes.dex */
public class ReanimatedUIManager extends UIManagerModule {
    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public boolean canOverrideExistingModule() {
        return true;
    }

    public ReanimatedUIManager(ReactApplicationContext reactApplicationContext, List<ViewManager> list, int r4) {
        super(reactApplicationContext, list, new ReaUiImplementationProvider(), r4);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule, com.facebook.react.bridge.OnBatchCompleteListener
    public void onBatchComplete() {
        super.onBatchComplete();
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getConstantsForViewManager(String str) {
        return super.getConstantsForViewManager(str);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getDefaultEventTypes() {
        return super.getDefaultEventTypes();
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void removeRootView(int r1) {
        super.removeRootView(r1);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void createView(int r1, String str, int r3, ReadableMap readableMap) {
        super.createView(r1, str, r3, readableMap);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void updateView(int r1, String str, ReadableMap readableMap) {
        super.updateView(r1, str, readableMap);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void manageChildren(int r1, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        super.manageChildren(r1, readableArray, readableArray2, readableArray3, readableArray4, readableArray5);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void setChildren(int r1, ReadableArray readableArray) {
        super.setChildren(r1, readableArray);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    @Deprecated
    public void replaceExistingNonRootView(int r1, int r2) {
        super.replaceExistingNonRootView(r1, r2);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    @Deprecated
    public void removeSubviewsFromContainerWithID(int r1) {
        super.removeSubviewsFromContainerWithID(r1);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void measure(int r1, Callback callback) {
        super.measure(r1, callback);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void measureInWindow(int r1, Callback callback) {
        super.measureInWindow(r1, callback);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void measureLayout(int r1, int r2, Callback callback, Callback callback2) {
        super.measureLayout(r1, r2, callback, callback2);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    @Deprecated
    public void measureLayoutRelativeToParent(int r1, Callback callback, Callback callback2) {
        super.measureLayoutRelativeToParent(r1, callback, callback2);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void findSubviewIn(int r1, ReadableArray readableArray, Callback callback) {
        super.findSubviewIn(r1, readableArray, callback);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    @Deprecated
    public void viewIsDescendantOf(int r1, int r2, Callback callback) {
        super.viewIsDescendantOf(r1, r2, callback);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void setJSResponder(int r1, boolean z) {
        super.setJSResponder(r1, z);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void clearJSResponder() {
        super.clearJSResponder();
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void dispatchViewManagerCommand(int r1, Dynamic dynamic, ReadableArray readableArray) {
        super.dispatchViewManagerCommand(r1, dynamic, readableArray);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void showPopupMenu(int r1, ReadableArray readableArray, Callback callback, Callback callback2) {
        super.showPopupMenu(r1, readableArray, callback, callback2);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void dismissPopupMenu() {
        super.dismissPopupMenu();
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void setLayoutAnimationEnabledExperimental(boolean z) {
        super.setLayoutAnimationEnabledExperimental(z);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule
    @ReactMethod
    public void configureNextLayoutAnimation(ReadableMap readableMap, Callback callback, Callback callback2) {
        super.configureNextLayoutAnimation(readableMap, callback, callback2);
    }

    @Override // com.facebook.react.uimanager.UIManagerModule, com.facebook.react.bridge.UIManager
    @ReactMethod
    public void sendAccessibilityEvent(int r1, int r2) {
        super.sendAccessibilityEvent(r1, r2);
    }
}
