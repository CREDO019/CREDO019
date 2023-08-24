package com.facebook.react.uimanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.view.View;
import androidx.collection.ArrayMap;
import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcherImpl;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@ReactModule(name = UIManagerModule.NAME)
/* loaded from: classes.dex */
public class UIManagerModule extends ReactContextBaseJavaModule implements OnBatchCompleteListener, LifecycleEventListener, UIManager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DEBUG = PrinterHolder.getPrinter().shouldDisplayLogMessage(ReactDebugOverlayTags.UI_MANAGER);
    public static final String NAME = "UIManager";
    public static final String TAG = "UIManagerModule";
    private int mBatchId;
    private final Map<String, Object> mCustomDirectEvents;
    private final EventDispatcher mEventDispatcher;
    private final List<UIManagerModuleListener> mListeners;
    private final MemoryTrimCallback mMemoryTrimCallback;
    private final Map<String, Object> mModuleConstants;
    private int mNumRootViews;
    private final UIImplementation mUIImplementation;
    private final CopyOnWriteArrayList<UIManagerListener> mUIManagerListeners;
    private Map<String, WritableMap> mViewManagerConstantsCache;
    private volatile int mViewManagerConstantsCacheSize;
    private final ViewManagerRegistry mViewManagerRegistry;

    /* loaded from: classes.dex */
    public interface CustomEventNamesResolver {
        String resolveCustomEventName(String str);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public UIManagerModule(ReactApplicationContext reactApplicationContext, ViewManagerResolver viewManagerResolver, int r4) {
        this(reactApplicationContext, viewManagerResolver, new UIImplementationProvider(), r4);
    }

    public UIManagerModule(ReactApplicationContext reactApplicationContext, List<ViewManager> list, int r4) {
        this(reactApplicationContext, list, new UIImplementationProvider(), r4);
    }

    @Deprecated
    public UIManagerModule(ReactApplicationContext reactApplicationContext, ViewManagerResolver viewManagerResolver, UIImplementationProvider uIImplementationProvider, int r6) {
        super(reactApplicationContext);
        this.mMemoryTrimCallback = new MemoryTrimCallback();
        this.mListeners = new ArrayList();
        this.mUIManagerListeners = new CopyOnWriteArrayList<>();
        this.mBatchId = 0;
        this.mNumRootViews = 0;
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(reactApplicationContext);
        EventDispatcherImpl eventDispatcherImpl = new EventDispatcherImpl(reactApplicationContext);
        this.mEventDispatcher = eventDispatcherImpl;
        this.mModuleConstants = createConstants(viewManagerResolver);
        this.mCustomDirectEvents = UIManagerModuleConstants.getDirectEventTypeConstants();
        ViewManagerRegistry viewManagerRegistry = new ViewManagerRegistry(viewManagerResolver);
        this.mViewManagerRegistry = viewManagerRegistry;
        this.mUIImplementation = uIImplementationProvider.createUIImplementation(reactApplicationContext, viewManagerRegistry, eventDispatcherImpl, r6);
        reactApplicationContext.addLifecycleEventListener(this);
    }

    @Deprecated
    public UIManagerModule(ReactApplicationContext reactApplicationContext, List<ViewManager> list, UIImplementationProvider uIImplementationProvider, int r7) {
        super(reactApplicationContext);
        this.mMemoryTrimCallback = new MemoryTrimCallback();
        this.mListeners = new ArrayList();
        this.mUIManagerListeners = new CopyOnWriteArrayList<>();
        this.mBatchId = 0;
        this.mNumRootViews = 0;
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(reactApplicationContext);
        EventDispatcherImpl eventDispatcherImpl = new EventDispatcherImpl(reactApplicationContext);
        this.mEventDispatcher = eventDispatcherImpl;
        HashMap newHashMap = MapBuilder.newHashMap();
        this.mCustomDirectEvents = newHashMap;
        this.mModuleConstants = createConstants(list, null, newHashMap);
        ViewManagerRegistry viewManagerRegistry = new ViewManagerRegistry(list);
        this.mViewManagerRegistry = viewManagerRegistry;
        this.mUIImplementation = uIImplementationProvider.createUIImplementation(reactApplicationContext, viewManagerRegistry, eventDispatcherImpl, r7);
        reactApplicationContext.addLifecycleEventListener(this);
    }

    @Deprecated
    public UIImplementation getUIImplementation() {
        return this.mUIImplementation;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return this.mModuleConstants;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        getReactApplicationContext().registerComponentCallbacks(this.mMemoryTrimCallback);
        getReactApplicationContext().registerComponentCallbacks(this.mViewManagerRegistry);
        this.mEventDispatcher.registerEventEmitter(1, (RCTEventEmitter) getReactApplicationContext().getJSModule(RCTEventEmitter.class));
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.mUIImplementation.onHostResume();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        this.mUIImplementation.onHostPause();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        this.mUIImplementation.onHostDestroy();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mEventDispatcher.onCatalystInstanceDestroyed();
        this.mUIImplementation.onCatalystInstanceDestroyed();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.unregisterComponentCallbacks(this.mMemoryTrimCallback);
        reactApplicationContext.unregisterComponentCallbacks(this.mViewManagerRegistry);
        YogaNodePool.get().clear();
        ViewManagerPropertyUpdater.clear();
    }

    @Deprecated
    public ViewManagerRegistry getViewManagerRegistry_DO_NOT_USE() {
        return this.mViewManagerRegistry;
    }

    private static Map<String, Object> createConstants(ViewManagerResolver viewManagerResolver) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        SystraceMessage.beginSection(0L, "CreateUIManagerConstants").arg("Lazy", (Object) true).flush();
        try {
            return UIManagerModuleConstantsHelper.createConstants(viewManagerResolver);
        } finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    private static Map<String, Object> createConstants(List<ViewManager> list, Map<String, Object> map, Map<String, Object> map2) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        SystraceMessage.beginSection(0L, "CreateUIManagerConstants").arg("Lazy", (Object) false).flush();
        try {
            return UIManagerModuleConstantsHelper.createConstants(list, map, map2);
        } finally {
            Systrace.endSection(0L);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    @Override // com.facebook.react.bridge.UIManager
    @Deprecated
    public void preInitializeViewManagers(List<String> list) {
        ArrayMap arrayMap = new ArrayMap();
        for (String str : list) {
            WritableMap computeConstantsForViewManager = computeConstantsForViewManager(str);
            if (computeConstantsForViewManager != null) {
                arrayMap.put(str, computeConstantsForViewManager);
            }
        }
        this.mViewManagerConstantsCacheSize = list.size();
        this.mViewManagerConstantsCache = Collections.unmodifiableMap(arrayMap);
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getConstantsForViewManager(String str) {
        Map<String, WritableMap> map = this.mViewManagerConstantsCache;
        if (map != null && map.containsKey(str)) {
            WritableMap writableMap = this.mViewManagerConstantsCache.get(str);
            int r0 = this.mViewManagerConstantsCacheSize - 1;
            this.mViewManagerConstantsCacheSize = r0;
            if (r0 <= 0) {
                this.mViewManagerConstantsCache = null;
            }
            return writableMap;
        }
        return computeConstantsForViewManager(str);
    }

    private WritableMap computeConstantsForViewManager(String str) {
        ViewManager resolveViewManager = str != null ? this.mUIImplementation.resolveViewManager(str) : null;
        if (resolveViewManager == null) {
            return null;
        }
        SystraceMessage.beginSection(0L, "UIManagerModule.getConstantsForViewManager").arg("ViewManager", resolveViewManager.getName()).arg("Lazy", (Object) true).flush();
        try {
            Map<String, Object> createConstantsForViewManager = UIManagerModuleConstantsHelper.createConstantsForViewManager(resolveViewManager, null, null, null, this.mCustomDirectEvents);
            if (createConstantsForViewManager != null) {
                return Arguments.makeNativeMap(createConstantsForViewManager);
            }
            return null;
        } finally {
            SystraceMessage.endSection(0L).flush();
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getDefaultEventTypes() {
        return Arguments.makeNativeMap(UIManagerModuleConstantsHelper.getDefaultExportableEventTypes());
    }

    @Deprecated
    public CustomEventNamesResolver getDirectEventNamesResolver() {
        return new CustomEventNamesResolver() { // from class: com.facebook.react.uimanager.UIManagerModule.1
            @Override // com.facebook.react.uimanager.UIManagerModule.CustomEventNamesResolver
            public String resolveCustomEventName(String str) {
                return UIManagerModule.this.resolveCustomDirectEventName(str);
            }
        };
    }

    @Override // com.facebook.react.bridge.UIManager
    @Deprecated
    public String resolveCustomDirectEventName(String str) {
        Map map;
        return (str == null || (map = (Map) this.mCustomDirectEvents.get(str)) == null) ? str : (String) map.get("registrationName");
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public void profileNextBatch() {
        this.mUIImplementation.profileNextBatch();
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public Map<String, Long> getPerformanceCounters() {
        return this.mUIImplementation.getProfiledBatchPerfCounters();
    }

    public <T extends View> int addRootView(T t) {
        return addRootView(t, null, null);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void synchronouslyUpdateViewOnUIThread(int r3, ReadableMap readableMap) {
        this.mUIImplementation.synchronouslyUpdateViewOnUIThread(r3, new ReactStylesDiffMap(readableMap));
    }

    @Override // com.facebook.react.bridge.UIManager
    public <T extends View> int addRootView(T t, WritableMap writableMap, String str) {
        Systrace.beginSection(0L, "UIManagerModule.addRootView");
        int nextRootViewTag = ReactRootViewTagGenerator.getNextRootViewTag();
        this.mUIImplementation.registerRootView(t, nextRootViewTag, new ThemedReactContext(getReactApplicationContext(), t.getContext(), ((ReactRoot) t).getSurfaceID(), -1));
        this.mNumRootViews++;
        Systrace.endSection(0L);
        return nextRootViewTag;
    }

    @Override // com.facebook.react.bridge.UIManager
    public <T extends View> int startSurface(T t, String str, WritableMap writableMap, int r4, int r5) {
        throw new UnsupportedOperationException();
    }

    @Override // com.facebook.react.bridge.UIManager
    public void stopSurface(int r1) {
        throw new UnsupportedOperationException();
    }

    @ReactMethod
    public void removeRootView(int r2) {
        this.mUIImplementation.removeRootView(r2);
        this.mNumRootViews--;
    }

    public void updateNodeSize(int r2, int r3, int r4) {
        getReactApplicationContext().assertOnNativeModulesQueueThread();
        this.mUIImplementation.updateNodeSize(r2, r3, r4);
    }

    public void setViewLocalData(final int r3, final Object obj) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.assertOnUiQueueThread();
        reactApplicationContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactApplicationContext) { // from class: com.facebook.react.uimanager.UIManagerModule.2
            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                UIManagerModule.this.mUIImplementation.setViewLocalData(r3, obj);
            }
        });
    }

    @ReactMethod
    public void createView(int r4, String str, int r6, ReadableMap readableMap) {
        if (DEBUG) {
            String str2 = "(UIManager.createView) tag: " + r4 + ", class: " + str + ", props: " + readableMap;
            FLog.m1340d(ReactConstants.TAG, str2);
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.UI_MANAGER, str2);
        }
        this.mUIImplementation.createView(r4, str, r6, readableMap);
    }

    @ReactMethod
    public void updateView(int r4, String str, ReadableMap readableMap) {
        if (DEBUG) {
            String str2 = "(UIManager.updateView) tag: " + r4 + ", class: " + str + ", props: " + readableMap;
            FLog.m1340d(ReactConstants.TAG, str2);
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.UI_MANAGER, str2);
        }
        this.mUIImplementation.updateView(r4, str, readableMap);
    }

    @ReactMethod
    public void manageChildren(int r10, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        if (DEBUG) {
            String str = "(UIManager.manageChildren) tag: " + r10 + ", moveFrom: " + readableArray + ", moveTo: " + readableArray2 + ", addTags: " + readableArray3 + ", atIndices: " + readableArray4 + ", removeFrom: " + readableArray5;
            FLog.m1340d(ReactConstants.TAG, str);
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.UI_MANAGER, str);
        }
        this.mUIImplementation.manageChildren(r10, readableArray, readableArray2, readableArray3, readableArray4, readableArray5);
    }

    @ReactMethod
    public void setChildren(int r4, ReadableArray readableArray) {
        if (DEBUG) {
            String str = "(UIManager.setChildren) tag: " + r4 + ", children: " + readableArray;
            FLog.m1340d(ReactConstants.TAG, str);
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.UI_MANAGER, str);
        }
        this.mUIImplementation.setChildren(r4, readableArray);
    }

    @ReactMethod
    @Deprecated
    public void replaceExistingNonRootView(int r2, int r3) {
        this.mUIImplementation.replaceExistingNonRootView(r2, r3);
    }

    @ReactMethod
    @Deprecated
    public void removeSubviewsFromContainerWithID(int r2) {
        this.mUIImplementation.removeSubviewsFromContainerWithID(r2);
    }

    @ReactMethod
    public void measure(int r2, Callback callback) {
        this.mUIImplementation.measure(r2, callback);
    }

    @ReactMethod
    public void measureInWindow(int r2, Callback callback) {
        this.mUIImplementation.measureInWindow(r2, callback);
    }

    @ReactMethod
    public void measureLayout(int r2, int r3, Callback callback, Callback callback2) {
        this.mUIImplementation.measureLayout(r2, r3, callback, callback2);
    }

    @ReactMethod
    @Deprecated
    public void measureLayoutRelativeToParent(int r2, Callback callback, Callback callback2) {
        this.mUIImplementation.measureLayoutRelativeToParent(r2, callback, callback2);
    }

    @ReactMethod
    public void findSubviewIn(int r5, ReadableArray readableArray, Callback callback) {
        this.mUIImplementation.findSubviewIn(r5, Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(0))), Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(1))), callback);
    }

    @ReactMethod
    @Deprecated
    public void viewIsDescendantOf(int r2, int r3, Callback callback) {
        this.mUIImplementation.viewIsDescendantOf(r2, r3, callback);
    }

    @ReactMethod
    public void setJSResponder(int r2, boolean z) {
        this.mUIImplementation.setJSResponder(r2, z);
    }

    @ReactMethod
    public void clearJSResponder() {
        this.mUIImplementation.clearJSResponder();
    }

    @ReactMethod
    public void dispatchViewManagerCommand(int r4, Dynamic dynamic, ReadableArray readableArray) {
        UIManager uIManager = UIManagerHelper.getUIManager(getReactApplicationContext(), ViewUtil.getUIManagerType(r4));
        if (uIManager == null) {
            return;
        }
        if (dynamic.getType() == ReadableType.Number) {
            uIManager.dispatchCommand(r4, dynamic.asInt(), readableArray);
        } else if (dynamic.getType() == ReadableType.String) {
            uIManager.dispatchCommand(r4, dynamic.asString(), readableArray);
        }
    }

    @Override // com.facebook.react.bridge.UIManager
    @Deprecated
    public void dispatchCommand(int r2, int r3, ReadableArray readableArray) {
        this.mUIImplementation.dispatchViewManagerCommand(r2, r3, readableArray);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void dispatchCommand(int r2, String str, ReadableArray readableArray) {
        this.mUIImplementation.dispatchViewManagerCommand(r2, str, readableArray);
    }

    @ReactMethod
    public void showPopupMenu(int r2, ReadableArray readableArray, Callback callback, Callback callback2) {
        this.mUIImplementation.showPopupMenu(r2, readableArray, callback, callback2);
    }

    @ReactMethod
    public void dismissPopupMenu() {
        this.mUIImplementation.dismissPopupMenu();
    }

    @ReactMethod
    public void setLayoutAnimationEnabledExperimental(boolean z) {
        this.mUIImplementation.setLayoutAnimationEnabledExperimental(z);
    }

    @ReactMethod
    public void configureNextLayoutAnimation(ReadableMap readableMap, Callback callback, Callback callback2) {
        this.mUIImplementation.configureNextLayoutAnimation(readableMap, callback);
    }

    public void onBatchComplete() {
        int r0 = this.mBatchId;
        this.mBatchId = r0 + 1;
        SystraceMessage.beginSection(0L, "onBatchCompleteUI").arg("BatchId", r0).flush();
        for (UIManagerModuleListener uIManagerModuleListener : this.mListeners) {
            uIManagerModuleListener.willDispatchViewUpdates(this);
        }
        Iterator<UIManagerListener> it = this.mUIManagerListeners.iterator();
        while (it.hasNext()) {
            it.next().willDispatchViewUpdates(this);
        }
        try {
            if (this.mNumRootViews > 0) {
                this.mUIImplementation.dispatchViewUpdates(r0);
            }
        } finally {
            Systrace.endSection(0L);
        }
    }

    public void setViewHierarchyUpdateDebugListener(NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener) {
        this.mUIImplementation.setViewHierarchyUpdateDebugListener(notThreadSafeViewHierarchyUpdateDebugListener);
    }

    @Override // com.facebook.react.bridge.UIManager
    public EventDispatcher getEventDispatcher() {
        return this.mEventDispatcher;
    }

    @ReactMethod
    public void sendAccessibilityEvent(int r3, int r4) {
        int uIManagerType = ViewUtil.getUIManagerType(r3);
        if (uIManagerType == 2) {
            UIManager uIManager = UIManagerHelper.getUIManager(getReactApplicationContext(), uIManagerType);
            if (uIManager != null) {
                uIManager.sendAccessibilityEvent(r3, r4);
                return;
            }
            return;
        }
        this.mUIImplementation.sendAccessibilityEvent(r3, r4);
    }

    public void addUIBlock(UIBlock uIBlock) {
        this.mUIImplementation.addUIBlock(uIBlock);
    }

    public void prependUIBlock(UIBlock uIBlock) {
        this.mUIImplementation.prependUIBlock(uIBlock);
    }

    @Deprecated
    public void addUIManagerListener(UIManagerModuleListener uIManagerModuleListener) {
        this.mListeners.add(uIManagerModuleListener);
    }

    @Deprecated
    public void removeUIManagerListener(UIManagerModuleListener uIManagerModuleListener) {
        this.mListeners.remove(uIManagerModuleListener);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void addUIManagerEventListener(UIManagerListener uIManagerListener) {
        this.mUIManagerListeners.add(uIManagerListener);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void removeUIManagerEventListener(UIManagerListener uIManagerListener) {
        this.mUIManagerListeners.remove(uIManagerListener);
    }

    @Deprecated
    public int resolveRootTagFromReactTag(int r2) {
        return ViewUtil.isRootTag(r2) ? r2 : this.mUIImplementation.resolveRootTagFromReactTag(r2);
    }

    public void invalidateNodeLayout(int r3) {
        ReactShadowNode resolveShadowNode = this.mUIImplementation.resolveShadowNode(r3);
        if (resolveShadowNode == null) {
            FLog.m1288w(ReactConstants.TAG, "Warning : attempted to dirty a non-existent react shadow node. reactTag=" + r3);
            return;
        }
        resolveShadowNode.dirty();
        this.mUIImplementation.dispatchViewUpdates(-1);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void updateRootLayoutSpecs(final int r7, final int r8, final int r9, int r10, int r11) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactApplicationContext) { // from class: com.facebook.react.uimanager.UIManagerModule.3
            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                UIManagerModule.this.mUIImplementation.updateRootView(r7, r8, r9);
                UIManagerModule.this.mUIImplementation.dispatchViewUpdates(-1);
            }
        });
    }

    /* loaded from: classes.dex */
    private class MemoryTrimCallback implements ComponentCallbacks2 {
        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
        }

        private MemoryTrimCallback() {
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int r2) {
            if (r2 >= 60) {
                YogaNodePool.get().clear();
            }
        }
    }

    @Override // com.facebook.react.bridge.UIManager
    public View resolveView(int r2) {
        UiThreadUtil.assertOnUiThread();
        return this.mUIImplementation.getUIViewOperationQueue().getNativeViewHierarchyManager().resolveView(r2);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void receiveEvent(int r2, String str, WritableMap writableMap) {
        receiveEvent(-1, r2, str, writableMap);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void receiveEvent(int r2, int r3, String str, WritableMap writableMap) {
        ((RCTEventEmitter) getReactApplicationContext().getJSModule(RCTEventEmitter.class)).receiveEvent(r3, str, writableMap);
    }
}
