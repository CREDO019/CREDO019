package com.facebook.react.animated;

import com.facebook.fbreact.specs.NativeAnimatedModuleSpec;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.GuardedFrameCallback;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.common.ViewUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

@ReactModule(name = NativeAnimatedModule.NAME)
/* loaded from: classes.dex */
public class NativeAnimatedModule extends NativeAnimatedModuleSpec implements LifecycleEventListener, UIManagerListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final boolean ANIMATED_MODULE_DEBUG = false;
    public static final String NAME = "NativeAnimatedModule";
    private final GuardedFrameCallback mAnimatedFrameCallback;
    private boolean mBatchingControlledByJS;
    private volatile long mCurrentBatchNumber;
    private volatile long mCurrentFrameNumber;
    private boolean mInitializedForFabric;
    private boolean mInitializedForNonFabric;
    private final AtomicReference<NativeAnimatedNodesManager> mNodesManager;
    private int mNumFabricAnimations;
    private int mNumNonFabricAnimations;
    private final ConcurrentOperationQueue mOperations;
    private final ConcurrentOperationQueue mPreOperations;
    private final ReactChoreographer mReactChoreographer;
    private int mUIManagerType;

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void removeListeners(double d) {
    }

    /* loaded from: classes.dex */
    private enum BatchExecutionOpCodes {
        OP_CODE_CREATE_ANIMATED_NODE(1),
        OP_CODE_UPDATE_ANIMATED_NODE_CONFIG(2),
        OP_CODE_GET_VALUE(3),
        OP_START_LISTENING_TO_ANIMATED_NODE_VALUE(4),
        OP_STOP_LISTENING_TO_ANIMATED_NODE_VALUE(5),
        OP_CODE_CONNECT_ANIMATED_NODES(6),
        OP_CODE_DISCONNECT_ANIMATED_NODES(7),
        OP_CODE_START_ANIMATING_NODE(8),
        OP_CODE_STOP_ANIMATION(9),
        OP_CODE_SET_ANIMATED_NODE_VALUE(10),
        OP_CODE_SET_ANIMATED_NODE_OFFSET(11),
        OP_CODE_FLATTEN_ANIMATED_NODE_OFFSET(12),
        OP_CODE_EXTRACT_ANIMATED_NODE_OFFSET(13),
        OP_CODE_CONNECT_ANIMATED_NODE_TO_VIEW(14),
        OP_CODE_DISCONNECT_ANIMATED_NODE_FROM_VIEW(15),
        OP_CODE_RESTORE_DEFAULT_VALUES(16),
        OP_CODE_DROP_ANIMATED_NODE(17),
        OP_CODE_ADD_ANIMATED_EVENT_TO_VIEW(18),
        OP_CODE_REMOVE_ANIMATED_EVENT_FROM_VIEW(19),
        OP_CODE_ADD_LISTENER(20),
        OP_CODE_REMOVE_LISTENERS(21);
        
        private static BatchExecutionOpCodes[] valueMap = null;
        private final int value;

        BatchExecutionOpCodes(int r3) {
            this.value = r3;
        }

        public int getValue() {
            return this.value;
        }

        public static BatchExecutionOpCodes fromId(int r1) {
            if (valueMap == null) {
                valueMap = values();
            }
            return valueMap[r1 - 1];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public abstract class UIThreadOperation {
        long mBatchNumber;

        abstract void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager);

        private UIThreadOperation() {
            this.mBatchNumber = -1L;
        }

        public void setBatchNumber(long j) {
            this.mBatchNumber = j;
        }

        public long getBatchNumber() {
            return this.mBatchNumber;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ConcurrentOperationQueue {
        private UIThreadOperation mPeekedOperation;
        private final Queue<UIThreadOperation> mQueue;
        private boolean mSynchronizedAccess;

        private ConcurrentOperationQueue() {
            this.mQueue = new ConcurrentLinkedQueue();
            this.mPeekedOperation = null;
            this.mSynchronizedAccess = false;
        }

        boolean isEmpty() {
            return this.mQueue.isEmpty() && this.mPeekedOperation == null;
        }

        void setSynchronizedAccess(boolean z) {
            this.mSynchronizedAccess = z;
        }

        void add(UIThreadOperation uIThreadOperation) {
            if (this.mSynchronizedAccess) {
                synchronized (this) {
                    this.mQueue.add(uIThreadOperation);
                }
                return;
            }
            this.mQueue.add(uIThreadOperation);
        }

        void executeBatch(long j, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
            List<UIThreadOperation> drainQueueIntoList;
            if (this.mSynchronizedAccess) {
                synchronized (this) {
                    drainQueueIntoList = drainQueueIntoList(j);
                }
            } else {
                drainQueueIntoList = drainQueueIntoList(j);
            }
            if (drainQueueIntoList != null) {
                for (UIThreadOperation uIThreadOperation : drainQueueIntoList) {
                    uIThreadOperation.execute(nativeAnimatedNodesManager);
                }
            }
        }

        private List<UIThreadOperation> drainQueueIntoList(long j) {
            if (isEmpty()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            while (true) {
                UIThreadOperation uIThreadOperation = this.mPeekedOperation;
                if (uIThreadOperation != null) {
                    if (uIThreadOperation.getBatchNumber() > j) {
                        break;
                    }
                    arrayList.add(this.mPeekedOperation);
                    this.mPeekedOperation = null;
                }
                UIThreadOperation poll = this.mQueue.poll();
                if (poll == null) {
                    break;
                } else if (poll.getBatchNumber() > j) {
                    this.mPeekedOperation = poll;
                    break;
                } else {
                    arrayList.add(poll);
                }
            }
            return arrayList;
        }
    }

    public NativeAnimatedModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        ConcurrentOperationQueue concurrentOperationQueue = new ConcurrentOperationQueue();
        this.mOperations = concurrentOperationQueue;
        ConcurrentOperationQueue concurrentOperationQueue2 = new ConcurrentOperationQueue();
        this.mPreOperations = concurrentOperationQueue2;
        this.mNodesManager = new AtomicReference<>();
        this.mBatchingControlledByJS = false;
        this.mInitializedForFabric = false;
        this.mInitializedForNonFabric = false;
        this.mUIManagerType = 1;
        this.mNumFabricAnimations = 0;
        this.mNumNonFabricAnimations = 0;
        this.mReactChoreographer = ReactChoreographer.getInstance();
        this.mAnimatedFrameCallback = new GuardedFrameCallback(reactApplicationContext) { // from class: com.facebook.react.animated.NativeAnimatedModule.1
            @Override // com.facebook.react.uimanager.GuardedFrameCallback
            protected void doFrameGuarded(long j) {
                try {
                    NativeAnimatedNodesManager nodesManager = NativeAnimatedModule.this.getNodesManager();
                    if (nodesManager != null && nodesManager.hasActiveAnimations()) {
                        nodesManager.runUpdates(j);
                    }
                    if (nodesManager == null && NativeAnimatedModule.this.mReactChoreographer == null) {
                        return;
                    }
                    ((ReactChoreographer) Assertions.assertNotNull(NativeAnimatedModule.this.mReactChoreographer)).postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, NativeAnimatedModule.this.mAnimatedFrameCallback);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        concurrentOperationQueue.setSynchronizedAccess(ReactFeatureFlags.enableSynchronizationForAnimated);
        concurrentOperationQueue2.setSynchronizedAccess(ReactFeatureFlags.enableSynchronizationForAnimated);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.addLifecycleEventListener(this);
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        enqueueFrameCallback();
    }

    private void addOperation(UIThreadOperation uIThreadOperation) {
        uIThreadOperation.setBatchNumber(this.mCurrentBatchNumber);
        this.mOperations.add(uIThreadOperation);
    }

    private void addUnbatchedOperation(UIThreadOperation uIThreadOperation) {
        uIThreadOperation.setBatchNumber(-1L);
        this.mOperations.add(uIThreadOperation);
    }

    private void addPreOperation(UIThreadOperation uIThreadOperation) {
        uIThreadOperation.setBatchNumber(this.mCurrentBatchNumber);
        this.mPreOperations.add(uIThreadOperation);
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didScheduleMountItems(UIManager uIManager) {
        this.mCurrentFrameNumber++;
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void didDispatchMountItems(UIManager uIManager) {
        if (this.mUIManagerType != 2) {
            return;
        }
        long j = this.mCurrentBatchNumber - 1;
        if (!this.mBatchingControlledByJS) {
            this.mCurrentFrameNumber++;
            if (this.mCurrentFrameNumber - this.mCurrentBatchNumber > 2) {
                this.mCurrentBatchNumber = this.mCurrentFrameNumber;
                j = this.mCurrentBatchNumber;
            }
        }
        this.mPreOperations.executeBatch(j, getNodesManager());
        this.mOperations.executeBatch(j, getNodesManager());
    }

    @Override // com.facebook.react.bridge.UIManagerListener
    public void willDispatchViewUpdates(UIManager uIManager) {
        if ((this.mOperations.isEmpty() && this.mPreOperations.isEmpty()) || this.mUIManagerType == 2) {
            return;
        }
        final long j = this.mCurrentBatchNumber;
        this.mCurrentBatchNumber = 1 + j;
        UIBlock uIBlock = new UIBlock() { // from class: com.facebook.react.animated.NativeAnimatedModule.2
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                NativeAnimatedModule.this.mPreOperations.executeBatch(j, NativeAnimatedModule.this.getNodesManager());
            }
        };
        UIBlock uIBlock2 = new UIBlock() { // from class: com.facebook.react.animated.NativeAnimatedModule.3
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                NativeAnimatedModule.this.mOperations.executeBatch(j, NativeAnimatedModule.this.getNodesManager());
            }
        };
        UIManagerModule uIManagerModule = (UIManagerModule) uIManager;
        uIManagerModule.prependUIBlock(uIBlock);
        uIManagerModule.addUIBlock(uIBlock2);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        clearFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        clearFrameCallback();
    }

    public NativeAnimatedNodesManager getNodesManager() {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn;
        if (this.mNodesManager.get() == null && (reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn()) != null) {
            this.mNodesManager.compareAndSet(null, new NativeAnimatedNodesManager(reactApplicationContextIfActiveOrWarn));
        }
        return this.mNodesManager.get();
    }

    private void clearFrameCallback() {
        ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).removeFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    private void enqueueFrameCallback() {
        ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    public void setNodesManager(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNodesManager.set(nativeAnimatedNodesManager);
    }

    private void initializeLifecycleEventListenersForViewTag(int r4) {
        UIManager uIManager;
        int uIManagerType = ViewUtil.getUIManagerType(r4);
        this.mUIManagerType = uIManagerType;
        if (uIManagerType == 2) {
            this.mNumFabricAnimations++;
        } else {
            this.mNumNonFabricAnimations++;
        }
        NativeAnimatedNodesManager nodesManager = getNodesManager();
        if (nodesManager != null) {
            nodesManager.initializeEventListenerForUIManagerType(this.mUIManagerType);
        } else {
            ReactSoftExceptionLogger.logSoftException(NAME, new RuntimeException("initializeLifecycleEventListenersForViewTag could not get NativeAnimatedNodesManager"));
        }
        if (this.mUIManagerType == 2) {
            if (this.mInitializedForFabric) {
                return;
            }
        } else if (this.mInitializedForNonFabric) {
            return;
        }
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext == null || (uIManager = UIManagerHelper.getUIManager(reactApplicationContext, this.mUIManagerType)) == null) {
            return;
        }
        uIManager.addUIManagerEventListener(this);
        if (this.mUIManagerType == 2) {
            this.mInitializedForFabric = true;
        } else {
            this.mInitializedForNonFabric = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void decrementInFlightAnimationsForViewTag(int r4) {
        if (ViewUtil.getUIManagerType(r4) == 2) {
            this.mNumFabricAnimations--;
        } else {
            this.mNumNonFabricAnimations--;
        }
        int r42 = this.mNumNonFabricAnimations;
        if (r42 == 0 && this.mNumFabricAnimations > 0 && this.mUIManagerType != 2) {
            this.mUIManagerType = 2;
        } else if (this.mNumFabricAnimations != 0 || r42 <= 0 || this.mUIManagerType == 1) {
        } else {
            this.mUIManagerType = 1;
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void startOperationBatch() {
        this.mBatchingControlledByJS = true;
        this.mCurrentBatchNumber++;
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void finishOperationBatch() {
        this.mBatchingControlledByJS = true;
        this.mCurrentBatchNumber++;
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void createAnimatedNode(double d, final ReadableMap readableMap) {
        final int r1 = (int) d;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.createAnimatedNode(r1, readableMap);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void updateAnimatedNodeConfig(double d, final ReadableMap readableMap) {
        final int r1 = (int) d;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.updateAnimatedNodeConfig(r1, readableMap);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void startListeningToAnimatedNodeValue(double d) {
        final int r2 = (int) d;
        final AnimatedNodeValueListener animatedNodeValueListener = new AnimatedNodeValueListener() { // from class: com.facebook.react.animated.NativeAnimatedModule.6
            @Override // com.facebook.react.animated.AnimatedNodeValueListener
            public void onValueUpdate(double d2) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("tag", r2);
                createMap.putDouble("value", d2);
                ReactApplicationContext reactApplicationContextIfActiveOrWarn = NativeAnimatedModule.this.getReactApplicationContextIfActiveOrWarn();
                if (reactApplicationContextIfActiveOrWarn != null) {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContextIfActiveOrWarn.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onAnimatedValueUpdate", createMap);
                }
            }
        };
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.startListeningToAnimatedNodeValue(r2, animatedNodeValueListener);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void stopListeningToAnimatedNodeValue(double d) {
        final int r1 = (int) d;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.stopListeningToAnimatedNodeValue(r1);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void dropAnimatedNode(double d) {
        final int r1 = (int) d;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.dropAnimatedNode(r1);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void setAnimatedNodeValue(double d, final double d2) {
        final int r1 = (int) d;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.setAnimatedNodeValue(r1, d2);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void setAnimatedNodeOffset(double d, final double d2) {
        final int r1 = (int) d;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.setAnimatedNodeOffset(r1, d2);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void flattenAnimatedNodeOffset(double d) {
        final int r1 = (int) d;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.12
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.flattenAnimatedNodeOffset(r1);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void extractAnimatedNodeOffset(double d) {
        final int r1 = (int) d;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.extractAnimatedNodeOffset(r1);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void startAnimatingNode(double d, double d2, final ReadableMap readableMap, final Callback callback) {
        final int r2 = (int) d;
        final int r3 = (int) d2;
        addUnbatchedOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.14
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.startAnimatingNode(r2, r3, readableMap, callback);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void stopAnimation(double d) {
        final int r1 = (int) d;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.15
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.stopAnimation(r1);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void connectAnimatedNodes(double d, double d2) {
        final int r1 = (int) d;
        final int r2 = (int) d2;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.16
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.connectAnimatedNodes(r1, r2);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void disconnectAnimatedNodes(double d, double d2) {
        final int r1 = (int) d;
        final int r2 = (int) d2;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.17
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.disconnectAnimatedNodes(r1, r2);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void connectAnimatedNodeToView(double d, double d2) {
        final int r1 = (int) d;
        final int r2 = (int) d2;
        initializeLifecycleEventListenersForViewTag(r2);
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.18
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.connectAnimatedNodeToView(r1, r2);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void disconnectAnimatedNodeFromView(double d, double d2) {
        final int r1 = (int) d;
        final int r2 = (int) d2;
        decrementInFlightAnimationsForViewTag(r2);
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.19
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.disconnectAnimatedNodeFromView(r1, r2);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void restoreDefaultValues(double d) {
        final int r1 = (int) d;
        addPreOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.20
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.restoreDefaultValues(r1);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void addAnimatedEventToView(double d, final String str, final ReadableMap readableMap) {
        final int r1 = (int) d;
        initializeLifecycleEventListenersForViewTag(r1);
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.21
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.addAnimatedEventToView(r1, str, readableMap);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void removeAnimatedEventFromView(double d, final String str, double d2) {
        final int r1 = (int) d;
        final int r2 = (int) d2;
        decrementInFlightAnimationsForViewTag(r1);
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.22
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.removeAnimatedEventFromView(r1, str, r2);
            }
        });
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void getValue(double d, final Callback callback) {
        final int r1 = (int) d;
        addOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.23
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.getValue(r1, callback);
            }
        });
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.removeLifecycleEventListener(this);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeAnimatedModuleSpec
    public void queueAndExecuteBatchedOperations(final ReadableArray readableArray) {
        int r2;
        final int size = readableArray.size();
        int r1 = 0;
        while (r1 < size) {
            int r22 = r1 + 1;
            switch (C146025.f161xb6ab8f2a[BatchExecutionOpCodes.fromId(readableArray.getInt(r1)).ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    r2 = r22 + 1;
                    break;
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                    r2 = r22 + 2;
                    break;
                case 18:
                case 19:
                    r2 = r22 + 3;
                    break;
                case 20:
                    int r23 = r22 + 1;
                    r1 = r23 + 1;
                    initializeLifecycleEventListenersForViewTag(readableArray.getInt(r23));
                    continue;
                case 21:
                    initializeLifecycleEventListenersForViewTag(readableArray.getInt(r22));
                    r1 = r22 + 1 + 1 + 1;
                    continue;
                default:
                    throw new IllegalArgumentException("Batch animation execution op: fetching viewTag: unknown op code");
            }
            r1 = r2;
        }
        startOperationBatch();
        addUnbatchedOperation(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.24
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                int r4;
                int r3;
                int r12;
                NativeAnimatedModule.this.getReactApplicationContextIfActiveOrWarn();
                int r0 = 0;
                while (r0 < size) {
                    int r24 = r0 + 1;
                    switch (C146025.f161xb6ab8f2a[BatchExecutionOpCodes.fromId(readableArray.getInt(r0)).ordinal()]) {
                        case 1:
                            r3 = r24 + 1;
                            nativeAnimatedNodesManager.getValue(readableArray.getInt(r24), null);
                            r0 = r3;
                            break;
                        case 2:
                            r12 = r24 + 1;
                            final int r02 = readableArray.getInt(r24);
                            nativeAnimatedNodesManager.startListeningToAnimatedNodeValue(r02, new AnimatedNodeValueListener() { // from class: com.facebook.react.animated.NativeAnimatedModule.24.1
                                @Override // com.facebook.react.animated.AnimatedNodeValueListener
                                public void onValueUpdate(double d) {
                                    WritableMap createMap = Arguments.createMap();
                                    createMap.putInt("tag", r02);
                                    createMap.putDouble("value", d);
                                    ReactApplicationContext reactApplicationContextIfActiveOrWarn = NativeAnimatedModule.this.getReactApplicationContextIfActiveOrWarn();
                                    if (reactApplicationContextIfActiveOrWarn != null) {
                                        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContextIfActiveOrWarn.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onAnimatedValueUpdate", createMap);
                                    }
                                }
                            });
                            r0 = r12;
                            break;
                        case 3:
                            r12 = r24 + 1;
                            nativeAnimatedNodesManager.stopListeningToAnimatedNodeValue(readableArray.getInt(r24));
                            r0 = r12;
                            break;
                        case 4:
                            r12 = r24 + 1;
                            nativeAnimatedNodesManager.stopAnimation(readableArray.getInt(r24));
                            r0 = r12;
                            break;
                        case 5:
                            r12 = r24 + 1;
                            nativeAnimatedNodesManager.flattenAnimatedNodeOffset(readableArray.getInt(r24));
                            r0 = r12;
                            break;
                        case 6:
                            r12 = r24 + 1;
                            nativeAnimatedNodesManager.extractAnimatedNodeOffset(readableArray.getInt(r24));
                            r0 = r12;
                            break;
                        case 7:
                            r12 = r24 + 1;
                            nativeAnimatedNodesManager.restoreDefaultValues(readableArray.getInt(r24));
                            r0 = r12;
                            break;
                        case 8:
                            r12 = r24 + 1;
                            nativeAnimatedNodesManager.dropAnimatedNode(readableArray.getInt(r24));
                            r0 = r12;
                            break;
                        case 9:
                        case 10:
                            r0 = r24 + 1;
                            break;
                        case 11:
                            int r13 = r24 + 1;
                            r3 = r13 + 1;
                            nativeAnimatedNodesManager.createAnimatedNode(readableArray.getInt(r24), readableArray.getMap(r13));
                            r0 = r3;
                            break;
                        case 12:
                            int r14 = r24 + 1;
                            r3 = r14 + 1;
                            nativeAnimatedNodesManager.updateAnimatedNodeConfig(readableArray.getInt(r24), readableArray.getMap(r14));
                            r0 = r3;
                            break;
                        case 13:
                            int r15 = r24 + 1;
                            r3 = r15 + 1;
                            nativeAnimatedNodesManager.connectAnimatedNodes(readableArray.getInt(r24), readableArray.getInt(r15));
                            r0 = r3;
                            break;
                        case 14:
                            int r16 = r24 + 1;
                            r3 = r16 + 1;
                            nativeAnimatedNodesManager.disconnectAnimatedNodes(readableArray.getInt(r24), readableArray.getInt(r16));
                            r0 = r3;
                            break;
                        case 15:
                            int r17 = r24 + 1;
                            r3 = r17 + 1;
                            nativeAnimatedNodesManager.setAnimatedNodeValue(readableArray.getInt(r24), readableArray.getDouble(r17));
                            r0 = r3;
                            break;
                        case 16:
                            int r18 = r24 + 1;
                            r3 = r18 + 1;
                            nativeAnimatedNodesManager.setAnimatedNodeValue(readableArray.getInt(r24), readableArray.getDouble(r18));
                            r0 = r3;
                            break;
                        case 17:
                            int r19 = r24 + 1;
                            int r03 = readableArray.getInt(r24);
                            r3 = r19 + 1;
                            int r110 = readableArray.getInt(r19);
                            NativeAnimatedModule.this.decrementInFlightAnimationsForViewTag(r110);
                            nativeAnimatedNodesManager.disconnectAnimatedNodeFromView(r03, r110);
                            r0 = r3;
                            break;
                        case 18:
                            int r32 = r24 + 1;
                            int r42 = r32 + 1;
                            nativeAnimatedNodesManager.startAnimatingNode(readableArray.getInt(r24), readableArray.getInt(r32), readableArray.getMap(r42), null);
                            r0 = r42 + 1;
                            break;
                        case 19:
                            int r111 = r24 + 1;
                            int r04 = readableArray.getInt(r24);
                            NativeAnimatedModule.this.decrementInFlightAnimationsForViewTag(r04);
                            int r33 = r111 + 1;
                            r4 = r33 + 1;
                            nativeAnimatedNodesManager.removeAnimatedEventFromView(r04, readableArray.getString(r111), readableArray.getInt(r33));
                            r0 = r4;
                            break;
                        case 20:
                            int r112 = r24 + 1;
                            r3 = r112 + 1;
                            nativeAnimatedNodesManager.connectAnimatedNodeToView(readableArray.getInt(r24), readableArray.getInt(r112));
                            r0 = r3;
                            break;
                        case 21:
                            int r113 = r24 + 1;
                            int r34 = r113 + 1;
                            r4 = r34 + 1;
                            nativeAnimatedNodesManager.addAnimatedEventToView(readableArray.getInt(r24), readableArray.getString(r113), readableArray.getMap(r34));
                            r0 = r4;
                            break;
                        default:
                            throw new IllegalArgumentException("Batch animation execution op: unknown op code");
                    }
                }
            }
        });
        finishOperationBatch();
    }

    /* renamed from: com.facebook.react.animated.NativeAnimatedModule$25 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C146025 {

        /* renamed from: $SwitchMap$com$facebook$react$animated$NativeAnimatedModule$BatchExecutionOpCodes */
        static final /* synthetic */ int[] f161xb6ab8f2a;

        static {
            int[] r0 = new int[BatchExecutionOpCodes.values().length];
            f161xb6ab8f2a = r0;
            try {
                r0[BatchExecutionOpCodes.OP_CODE_GET_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_START_LISTENING_TO_ANIMATED_NODE_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_STOP_LISTENING_TO_ANIMATED_NODE_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_STOP_ANIMATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_FLATTEN_ANIMATED_NODE_OFFSET.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_EXTRACT_ANIMATED_NODE_OFFSET.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_RESTORE_DEFAULT_VALUES.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_DROP_ANIMATED_NODE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_ADD_LISTENER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_REMOVE_LISTENERS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_CREATE_ANIMATED_NODE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_UPDATE_ANIMATED_NODE_CONFIG.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_CONNECT_ANIMATED_NODES.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_DISCONNECT_ANIMATED_NODES.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_SET_ANIMATED_NODE_VALUE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_SET_ANIMATED_NODE_OFFSET.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_DISCONNECT_ANIMATED_NODE_FROM_VIEW.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_START_ANIMATING_NODE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_REMOVE_ANIMATED_EVENT_FROM_VIEW.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_CONNECT_ANIMATED_NODE_TO_VIEW.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f161xb6ab8f2a[BatchExecutionOpCodes.OP_CODE_ADD_ANIMATED_EVENT_TO_VIEW.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
        }
    }
}
