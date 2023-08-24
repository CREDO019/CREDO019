package com.swmansion.reanimated;

import android.util.Log;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModuleListener;
import com.swmansion.reanimated.transitions.TransitionModule;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;

@ReactModule(name = ReanimatedModule.NAME)
/* loaded from: classes3.dex */
public class ReanimatedModule extends ReactContextBaseJavaModule implements LifecycleEventListener, UIManagerModuleListener {
    public static final String NAME = "ReanimatedModule";
    @Nullable
    private NodesManager mNodesManager;
    private ArrayList<UIThreadOperation> mOperations;
    @Nullable
    private TransitionModule mTransitionManager;
    private UIManagerModule mUIManager;

    /* loaded from: classes3.dex */
    private interface UIThreadOperation {
        void execute(NodesManager nodesManager);
    }

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public ReanimatedModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mOperations = new ArrayList<>();
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        UIManagerModule uIManagerModule = (UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class);
        reactApplicationContext.addLifecycleEventListener(this);
        uIManagerModule.addUIManagerListener(this);
        this.mTransitionManager = new TransitionModule(uIManagerModule);
        this.mUIManager = uIManagerModule;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        NodesManager nodesManager = this.mNodesManager;
        if (nodesManager != null) {
            nodesManager.onHostPause();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        NodesManager nodesManager = this.mNodesManager;
        if (nodesManager != null) {
            nodesManager.onHostResume();
        }
    }

    @Override // com.facebook.react.uimanager.UIManagerModuleListener
    public void willDispatchViewUpdates(UIManagerModule uIManagerModule) {
        if (this.mOperations.isEmpty()) {
            return;
        }
        final ArrayList<UIThreadOperation> arrayList = this.mOperations;
        this.mOperations = new ArrayList<>();
        uIManagerModule.addUIBlock(new UIBlock() { // from class: com.swmansion.reanimated.ReanimatedModule.1
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                NodesManager nodesManager = ReanimatedModule.this.getNodesManager();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((UIThreadOperation) it.next()).execute(nodesManager);
                }
            }
        });
    }

    public NodesManager getNodesManager() {
        if (this.mNodesManager == null) {
            this.mNodesManager = new NodesManager(getReactApplicationContext());
        }
        return this.mNodesManager;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public void installTurboModule() {
        C4139Utils.isChromeDebugger = getReactApplicationContext().getJavaScriptContextHolder().get() == 0;
        if (!C4139Utils.isChromeDebugger) {
            getNodesManager().initWithContext(getReactApplicationContext());
        } else {
            Log.w("[REANIMATED]", "Unable to create Reanimated Native Module. You can ignore this message if you are using Chrome Debugger now.");
        }
    }

    @ReactMethod
    public void animateNextTransition(int r2, ReadableMap readableMap) {
        this.mTransitionManager.animateNextTransition(r2, readableMap);
    }

    @ReactMethod
    public void createNode(final int r3, final ReadableMap readableMap) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.swmansion.reanimated.ReanimatedModule.2
            @Override // com.swmansion.reanimated.ReanimatedModule.UIThreadOperation
            public void execute(NodesManager nodesManager) {
                nodesManager.createNode(r3, readableMap);
            }
        });
    }

    @ReactMethod
    public void dropNode(final int r3) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.swmansion.reanimated.ReanimatedModule.3
            @Override // com.swmansion.reanimated.ReanimatedModule.UIThreadOperation
            public void execute(NodesManager nodesManager) {
                nodesManager.dropNode(r3);
            }
        });
    }

    @ReactMethod
    public void connectNodes(final int r3, final int r4) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.swmansion.reanimated.ReanimatedModule.4
            @Override // com.swmansion.reanimated.ReanimatedModule.UIThreadOperation
            public void execute(NodesManager nodesManager) {
                nodesManager.connectNodes(r3, r4);
            }
        });
    }

    @ReactMethod
    public void disconnectNodes(final int r3, final int r4) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.swmansion.reanimated.ReanimatedModule.5
            @Override // com.swmansion.reanimated.ReanimatedModule.UIThreadOperation
            public void execute(NodesManager nodesManager) {
                nodesManager.disconnectNodes(r3, r4);
            }
        });
    }

    @ReactMethod
    public void connectNodeToView(final int r3, final int r4) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.swmansion.reanimated.ReanimatedModule.6
            @Override // com.swmansion.reanimated.ReanimatedModule.UIThreadOperation
            public void execute(NodesManager nodesManager) {
                nodesManager.connectNodeToView(r3, r4);
            }
        });
    }

    @ReactMethod
    public void disconnectNodeFromView(final int r3, final int r4) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.swmansion.reanimated.ReanimatedModule.7
            @Override // com.swmansion.reanimated.ReanimatedModule.UIThreadOperation
            public void execute(NodesManager nodesManager) {
                nodesManager.disconnectNodeFromView(r3, r4);
            }
        });
    }

    @ReactMethod
    public void attachEvent(final int r3, final String str, final int r5) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.swmansion.reanimated.ReanimatedModule.8
            @Override // com.swmansion.reanimated.ReanimatedModule.UIThreadOperation
            public void execute(NodesManager nodesManager) {
                nodesManager.attachEvent(r3, str, r5);
            }
        });
    }

    @ReactMethod
    public void detachEvent(final int r3, final String str, final int r5) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.swmansion.reanimated.ReanimatedModule.9
            @Override // com.swmansion.reanimated.ReanimatedModule.UIThreadOperation
            public void execute(NodesManager nodesManager) {
                nodesManager.detachEvent(r3, str, r5);
            }
        });
    }

    @ReactMethod
    public void getValue(final int r3, final Callback callback) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.swmansion.reanimated.ReanimatedModule.10
            @Override // com.swmansion.reanimated.ReanimatedModule.UIThreadOperation
            public void execute(NodesManager nodesManager) {
                nodesManager.getValue(r3, callback);
            }
        });
    }

    @ReactMethod
    public void setValue(final int r3, final Double d) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.swmansion.reanimated.ReanimatedModule.11
            @Override // com.swmansion.reanimated.ReanimatedModule.UIThreadOperation
            public void execute(NodesManager nodesManager) {
                nodesManager.setValue(r3, d);
            }
        });
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        NodesManager nodesManager = this.mNodesManager;
        if (nodesManager != null) {
            nodesManager.onCatalystInstanceDestroy();
        }
    }
}
