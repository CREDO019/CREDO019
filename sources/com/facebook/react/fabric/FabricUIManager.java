package com.facebook.react.fabric;

import android.content.Context;
import android.graphics.Point;
import android.os.SystemClock;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.fabric.DevToolsReactPerfLogger;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.events.FabricEventEmitter;
import com.facebook.react.fabric.mounting.LayoutMetricsConversions;
import com.facebook.react.fabric.mounting.MountItemDispatcher;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.fabric.mounting.mountitems.DispatchIntCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.DispatchStringCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.IntBufferBatchMountItem;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.fabric.mounting.mountitems.PreAllocateViewMountItem;
import com.facebook.react.fabric.mounting.mountitems.SendAccessibilityEvent;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactRoot;
import com.facebook.react.uimanager.ReactRootViewTagGenerator;
import com.facebook.react.uimanager.RootViewUtil;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewManagerPropertyUpdater;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcherImpl;
import com.facebook.react.uimanager.events.LockFreeEventDispatcherImpl;
import com.facebook.react.uimanager.events.RCTModernEventEmitter;
import com.facebook.react.views.text.TextLayoutManager;
import com.facebook.react.views.text.TextLayoutManagerMapBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class FabricUIManager implements UIManager, LifecycleEventListener {
    public static final boolean ENABLE_FABRIC_LOGS;
    public static final boolean ENABLE_FABRIC_PERF_LOGS;
    private static final DevToolsReactPerfLogger.DevToolsReactPerfLoggerListener FABRIC_PERF_LOGGER;
    public static final boolean IS_DEVELOPMENT_ENVIRONMENT = false;
    public static final String TAG = "FabricUIManager";
    private Binding mBinding;
    public DevToolsReactPerfLogger mDevToolsReactPerfLogger;
    private final DispatchUIFrameCallback mDispatchUIFrameCallback;
    private final EventBeatManager mEventBeatManager;
    private final EventDispatcher mEventDispatcher;
    private final MountItemDispatcher mMountItemDispatcher;
    private final MountingManager mMountingManager;
    private final ReactApplicationContext mReactApplicationContext;
    private volatile boolean mShouldDeallocateEventDispatcher;
    private final ViewManagerRegistry mViewManagerRegistry;
    private final CopyOnWriteArrayList<UIManagerListener> mListeners = new CopyOnWriteArrayList<>();
    private volatile boolean mDestroyed = false;
    private boolean mDriveCxxAnimations = false;
    private long mDispatchViewUpdatesTime = 0;
    private long mCommitStartTime = 0;
    private long mLayoutTime = 0;
    private long mFinishTransactionTime = 0;
    private long mFinishTransactionCPPTime = 0;
    private int mCurrentSynchronousCommitNumber = 10000;
    private MountingManager.MountItemExecutor mMountItemExecutor = new MountingManager.MountItemExecutor() { // from class: com.facebook.react.fabric.FabricUIManager.2
        @Override // com.facebook.react.fabric.mounting.MountingManager.MountItemExecutor
        public void executeItems(Queue<MountItem> queue) {
            FabricUIManager.this.mMountItemDispatcher.dispatchMountItems(queue);
        }
    };

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public void profileNextBatch() {
    }

    static {
        boolean z = ReactFeatureFlags.enableFabricLogs || PrinterHolder.getPrinter().shouldDisplayLogMessage(ReactDebugOverlayTags.FABRIC_UI_MANAGER);
        ENABLE_FABRIC_LOGS = z;
        ENABLE_FABRIC_PERF_LOGS = z;
        FABRIC_PERF_LOGGER = new DevToolsReactPerfLogger.DevToolsReactPerfLoggerListener() { // from class: com.facebook.react.fabric.FabricUIManager.1
            @Override // com.facebook.react.fabric.DevToolsReactPerfLogger.DevToolsReactPerfLoggerListener
            public void onFabricCommitEnd(DevToolsReactPerfLogger.FabricCommitPoint fabricCommitPoint) {
                long commitDuration = fabricCommitPoint.getCommitDuration();
                long layoutDuration = fabricCommitPoint.getLayoutDuration();
                long diffDuration = fabricCommitPoint.getDiffDuration();
                long transactionEndDuration = fabricCommitPoint.getTransactionEndDuration();
                long batchExecutionDuration = fabricCommitPoint.getBatchExecutionDuration();
                DevToolsReactPerfLogger.mStreamingCommitStats.add(commitDuration);
                DevToolsReactPerfLogger.mStreamingLayoutStats.add(layoutDuration);
                DevToolsReactPerfLogger.mStreamingDiffStats.add(diffDuration);
                DevToolsReactPerfLogger.mStreamingTransactionEndStats.add(transactionEndDuration);
                DevToolsReactPerfLogger.mStreamingBatchExecutionStats.add(batchExecutionDuration);
                FLog.m1310i(FabricUIManager.TAG, "Statistics of Fabric commit #%d:\n - Total commit time: %d ms. Avg: %.2f. Median: %.2f ms. Max: %d ms.\n - Layout time: %d ms. Avg: %.2f. Median: %.2f ms. Max: %d ms.\n - Diffing time: %d ms. Avg: %.2f. Median: %.2f ms. Max: %d ms.\n - FinishTransaction (Diffing + JNI serialization): %d ms. Avg: %.2f. Median: %.2f ms. Max: %d ms.\n - Mounting: %d ms. Avg: %.2f. Median: %.2f ms. Max: %d ms.\n", Long.valueOf(fabricCommitPoint.getCommitNumber()), Long.valueOf(commitDuration), Double.valueOf(DevToolsReactPerfLogger.mStreamingCommitStats.getAverage()), Double.valueOf(DevToolsReactPerfLogger.mStreamingCommitStats.getMedian()), Long.valueOf(DevToolsReactPerfLogger.mStreamingCommitStats.getMax()), Long.valueOf(layoutDuration), Double.valueOf(DevToolsReactPerfLogger.mStreamingLayoutStats.getAverage()), Double.valueOf(DevToolsReactPerfLogger.mStreamingLayoutStats.getMedian()), Long.valueOf(DevToolsReactPerfLogger.mStreamingLayoutStats.getMax()), Long.valueOf(diffDuration), Double.valueOf(DevToolsReactPerfLogger.mStreamingDiffStats.getAverage()), Double.valueOf(DevToolsReactPerfLogger.mStreamingDiffStats.getMedian()), Long.valueOf(DevToolsReactPerfLogger.mStreamingDiffStats.getMax()), Long.valueOf(transactionEndDuration), Double.valueOf(DevToolsReactPerfLogger.mStreamingTransactionEndStats.getAverage()), Double.valueOf(DevToolsReactPerfLogger.mStreamingTransactionEndStats.getMedian()), Long.valueOf(DevToolsReactPerfLogger.mStreamingTransactionEndStats.getMax()), Long.valueOf(batchExecutionDuration), Double.valueOf(DevToolsReactPerfLogger.mStreamingBatchExecutionStats.getAverage()), Double.valueOf(DevToolsReactPerfLogger.mStreamingBatchExecutionStats.getMedian()), Long.valueOf(DevToolsReactPerfLogger.mStreamingBatchExecutionStats.getMax()));
            }
        };
        FabricSoLoader.staticInit();
    }

    @Deprecated
    public FabricUIManager(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventDispatcher eventDispatcher, EventBeatManager eventBeatManager) {
        this.mShouldDeallocateEventDispatcher = false;
        this.mDispatchUIFrameCallback = new DispatchUIFrameCallback(reactApplicationContext);
        this.mReactApplicationContext = reactApplicationContext;
        MountingManager mountingManager = new MountingManager(viewManagerRegistry, this.mMountItemExecutor);
        this.mMountingManager = mountingManager;
        this.mMountItemDispatcher = new MountItemDispatcher(mountingManager, new MountItemDispatchListener());
        this.mEventDispatcher = eventDispatcher;
        this.mShouldDeallocateEventDispatcher = false;
        this.mEventBeatManager = eventBeatManager;
        reactApplicationContext.addLifecycleEventListener(this);
        this.mViewManagerRegistry = viewManagerRegistry;
        reactApplicationContext.registerComponentCallbacks(viewManagerRegistry);
    }

    public FabricUIManager(ReactApplicationContext reactApplicationContext, ViewManagerRegistry viewManagerRegistry, EventBeatManager eventBeatManager) {
        EventDispatcher eventDispatcherImpl;
        this.mShouldDeallocateEventDispatcher = false;
        this.mDispatchUIFrameCallback = new DispatchUIFrameCallback(reactApplicationContext);
        this.mReactApplicationContext = reactApplicationContext;
        MountingManager mountingManager = new MountingManager(viewManagerRegistry, this.mMountItemExecutor);
        this.mMountingManager = mountingManager;
        this.mMountItemDispatcher = new MountItemDispatcher(mountingManager, new MountItemDispatchListener());
        if (ReactFeatureFlags.enableLockFreeEventDispatcher) {
            eventDispatcherImpl = new LockFreeEventDispatcherImpl(reactApplicationContext);
        } else {
            eventDispatcherImpl = new EventDispatcherImpl(reactApplicationContext);
        }
        this.mEventDispatcher = eventDispatcherImpl;
        this.mShouldDeallocateEventDispatcher = true;
        this.mEventBeatManager = eventBeatManager;
        reactApplicationContext.addLifecycleEventListener(this);
        this.mViewManagerRegistry = viewManagerRegistry;
        reactApplicationContext.registerComponentCallbacks(viewManagerRegistry);
    }

    @Override // com.facebook.react.bridge.UIManager
    @Deprecated
    public <T extends View> int addRootView(T t, WritableMap writableMap, String str) {
        String str2 = TAG;
        ReactSoftExceptionLogger.logSoftException(str2, new IllegalViewOperationException("Do not call addRootView in Fabric; it is unsupported. Call startSurface instead."));
        int nextRootViewTag = ReactRootViewTagGenerator.getNextRootViewTag();
        ReactRoot reactRoot = (ReactRoot) t;
        this.mMountingManager.startSurface(nextRootViewTag, new ThemedReactContext(this.mReactApplicationContext, t.getContext(), reactRoot.getSurfaceID(), nextRootViewTag), t);
        String jSModuleName = reactRoot.getJSModuleName();
        if (ENABLE_FABRIC_LOGS) {
            FLog.m1338d(str2, "Starting surface for module: %s and reactTag: %d", jSModuleName, Integer.valueOf(nextRootViewTag));
        }
        this.mBinding.startSurface(nextRootViewTag, jSModuleName, (NativeMap) writableMap);
        if (str != null) {
            this.mBinding.renderTemplateToSurface(nextRootViewTag, str);
        }
        return nextRootViewTag;
    }

    public ReadableMap getInspectorDataForInstance(int r2, View view) {
        UiThreadUtil.assertOnUiThread();
        return this.mBinding.getInspectorDataForInstance(this.mMountingManager.getEventEmitter(r2, view.getId()));
    }

    @Override // com.facebook.react.bridge.UIManager
    public void preInitializeViewManagers(List<String> list) {
        for (String str : list) {
            this.mMountingManager.initializeViewManager(str);
        }
    }

    @Override // com.facebook.react.bridge.UIManager
    public <T extends View> int startSurface(T t, String str, WritableMap writableMap, int r19, int r20) {
        int nextRootViewTag = ReactRootViewTagGenerator.getNextRootViewTag();
        Context context = t.getContext();
        ThemedReactContext themedReactContext = new ThemedReactContext(this.mReactApplicationContext, context, str, nextRootViewTag);
        if (ENABLE_FABRIC_LOGS) {
            FLog.m1338d(TAG, "Starting surface for module: %s and reactTag: %d", str, Integer.valueOf(nextRootViewTag));
        }
        this.mMountingManager.startSurface(nextRootViewTag, themedReactContext, t);
        Point viewportOffset = UiThreadUtil.isOnUiThread() ? RootViewUtil.getViewportOffset(t) : new Point(0, 0);
        this.mBinding.startSurfaceWithConstraints(nextRootViewTag, str, (NativeMap) writableMap, LayoutMetricsConversions.CC.getMinSize(r19), LayoutMetricsConversions.CC.getMaxSize(r19), LayoutMetricsConversions.CC.getMinSize(r20), LayoutMetricsConversions.CC.getMaxSize(r20), viewportOffset.x, viewportOffset.y, I18nUtil.getInstance().isRTL(context), I18nUtil.getInstance().doLeftAndRightSwapInRTL(context));
        return nextRootViewTag;
    }

    public void startSurface(SurfaceHandler surfaceHandler, Context context, View view) {
        int nextRootViewTag = ReactRootViewTagGenerator.getNextRootViewTag();
        this.mMountingManager.startSurface(nextRootViewTag, new ThemedReactContext(this.mReactApplicationContext, context, surfaceHandler.getModuleName(), nextRootViewTag), view);
        surfaceHandler.setSurfaceId(nextRootViewTag);
        if (surfaceHandler instanceof SurfaceHandlerBinding) {
            this.mBinding.registerSurface((SurfaceHandlerBinding) surfaceHandler);
        }
        surfaceHandler.setMountable(view != null);
        surfaceHandler.start();
    }

    public void attachRootView(SurfaceHandler surfaceHandler, View view) {
        this.mMountingManager.attachRootView(surfaceHandler.getSurfaceId(), view, new ThemedReactContext(this.mReactApplicationContext, view.getContext(), surfaceHandler.getModuleName(), surfaceHandler.getSurfaceId()));
        surfaceHandler.setMountable(true);
    }

    public void stopSurface(SurfaceHandler surfaceHandler) {
        if (!surfaceHandler.isRunning()) {
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Trying to stop surface that hasn't started yet"));
            return;
        }
        this.mMountingManager.stopSurface(surfaceHandler.getSurfaceId());
        surfaceHandler.stop();
        if (surfaceHandler instanceof SurfaceHandlerBinding) {
            this.mBinding.unregisterSurface((SurfaceHandlerBinding) surfaceHandler);
        }
    }

    public void onRequestEventBeat() {
        this.mEventDispatcher.dispatchAllEvents();
    }

    @Override // com.facebook.react.bridge.UIManager
    public void stopSurface(int r2) {
        this.mMountingManager.stopSurface(r2);
        this.mBinding.stopSurface(r2);
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void initialize() {
        this.mEventDispatcher.registerEventEmitter(2, (RCTModernEventEmitter) new FabricEventEmitter(this));
        this.mEventDispatcher.addBatchEventDispatchedListener(this.mEventBeatManager);
        if (ENABLE_FABRIC_PERF_LOGS) {
            DevToolsReactPerfLogger devToolsReactPerfLogger = new DevToolsReactPerfLogger();
            this.mDevToolsReactPerfLogger = devToolsReactPerfLogger;
            devToolsReactPerfLogger.addDevToolsReactPerfLoggerListener(FABRIC_PERF_LOGGER);
            ReactMarker.addFabricListener(this.mDevToolsReactPerfLogger);
        }
    }

    @Override // com.facebook.react.bridge.JSIModule
    public void onCatalystInstanceDestroy() {
        String str = TAG;
        FLog.m1316i(str, "FabricUIManager.onCatalystInstanceDestroy");
        DevToolsReactPerfLogger devToolsReactPerfLogger = this.mDevToolsReactPerfLogger;
        if (devToolsReactPerfLogger != null) {
            devToolsReactPerfLogger.removeDevToolsReactPerfLoggerListener(FABRIC_PERF_LOGGER);
            ReactMarker.removeFabricListener(this.mDevToolsReactPerfLogger);
        }
        if (this.mDestroyed) {
            ReactSoftExceptionLogger.logSoftException(str, new IllegalStateException("Cannot double-destroy FabricUIManager"));
            return;
        }
        this.mDestroyed = true;
        this.mDispatchUIFrameCallback.stop();
        this.mEventDispatcher.removeBatchEventDispatchedListener(this.mEventBeatManager);
        this.mEventDispatcher.unregisterEventEmitter(2);
        this.mReactApplicationContext.unregisterComponentCallbacks(this.mViewManagerRegistry);
        this.mReactApplicationContext.removeLifecycleEventListener(this);
        onHostPause();
        this.mBinding.unregister();
        this.mBinding = null;
        ViewManagerPropertyUpdater.clear();
        if (this.mShouldDeallocateEventDispatcher) {
            this.mEventDispatcher.onCatalystInstanceDestroyed();
        }
    }

    private NativeArray measureLines(ReadableMap readableMap, ReadableMap readableMap2, float f, float f2) {
        return (NativeArray) TextLayoutManager.measureLines(this.mReactApplicationContext, readableMap, readableMap2, PixelUtil.toPixelFromDIP(f));
    }

    private NativeArray measureLinesMapBuffer(ReadableMapBuffer readableMapBuffer, ReadableMapBuffer readableMapBuffer2, float f, float f2) {
        return (NativeArray) TextLayoutManagerMapBuffer.measureLines(this.mReactApplicationContext, readableMapBuffer, readableMapBuffer2, PixelUtil.toPixelFromDIP(f));
    }

    private long measure(int r12, String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, float f2, float f3, float f4) {
        return measure(r12, str, readableMap, readableMap2, readableMap3, f, f2, f3, f4, null);
    }

    public int getColor(int r5, String[] strArr) {
        ThemedReactContext context = this.mMountingManager.getSurfaceManagerEnforced(r5, "getColor").getContext();
        for (String str : strArr) {
            Integer resolveResourcePath = ColorPropConverter.resolveResourcePath(context, str);
            if (resolveResourcePath != null) {
                return resolveResourcePath.intValue();
            }
        }
        return 0;
    }

    private long measure(int r14, String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, float f2, float f3, float f4, float[] fArr) {
        ReactContext reactContext;
        if (r14 > 0) {
            SurfaceMountingManager surfaceManagerEnforced = this.mMountingManager.getSurfaceManagerEnforced(r14, "measure");
            if (surfaceManagerEnforced.isStopped()) {
                return 0L;
            }
            reactContext = surfaceManagerEnforced.getContext();
        } else {
            reactContext = this.mReactApplicationContext;
        }
        return this.mMountingManager.measure(reactContext, str, readableMap, readableMap2, readableMap3, LayoutMetricsConversions.CC.getYogaSize(f, f2), LayoutMetricsConversions.CC.getYogaMeasureMode(f, f2), LayoutMetricsConversions.CC.getYogaSize(f3, f4), LayoutMetricsConversions.CC.getYogaMeasureMode(f3, f4), fArr);
    }

    private long measureMapBuffer(int r14, String str, ReadableMapBuffer readableMapBuffer, ReadableMapBuffer readableMapBuffer2, ReadableMapBuffer readableMapBuffer3, float f, float f2, float f3, float f4, float[] fArr) {
        ReactContext reactContext;
        if (r14 > 0) {
            SurfaceMountingManager surfaceManagerEnforced = this.mMountingManager.getSurfaceManagerEnforced(r14, "measure");
            if (surfaceManagerEnforced.isStopped()) {
                return 0L;
            }
            reactContext = surfaceManagerEnforced.getContext();
        } else {
            reactContext = this.mReactApplicationContext;
        }
        return this.mMountingManager.measureMapBuffer(reactContext, str, readableMapBuffer, readableMapBuffer2, readableMapBuffer3, LayoutMetricsConversions.CC.getYogaSize(f, f2), LayoutMetricsConversions.CC.getYogaMeasureMode(f, f2), LayoutMetricsConversions.CC.getYogaSize(f3, f4), LayoutMetricsConversions.CC.getYogaMeasureMode(f3, f4), fArr);
    }

    public boolean getThemeData(int r4, float[] fArr) {
        ThemedReactContext context = this.mMountingManager.getSurfaceManagerEnforced(r4, "getThemeData").getContext();
        if (context == null) {
            FLog.m1288w(TAG, "\"themedReactContext\" is null when call \"getThemeData\"");
            return false;
        }
        float[] defaultTextInputPadding = UIManagerHelper.getDefaultTextInputPadding(context);
        fArr[0] = defaultTextInputPadding[0];
        fArr[1] = defaultTextInputPadding[1];
        fArr[2] = defaultTextInputPadding[2];
        fArr[3] = defaultTextInputPadding[3];
        return true;
    }

    @Override // com.facebook.react.bridge.UIManager
    public void addUIManagerEventListener(UIManagerListener uIManagerListener) {
        this.mListeners.add(uIManagerListener);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void removeUIManagerEventListener(UIManagerListener uIManagerListener) {
        this.mListeners.remove(uIManagerListener);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void synchronouslyUpdateViewOnUIThread(final int r6, final ReadableMap readableMap) {
        UiThreadUtil.assertOnUiThread();
        int r0 = this.mCurrentSynchronousCommitNumber;
        this.mCurrentSynchronousCommitNumber = r0 + 1;
        MountItem mountItem = new MountItem() { // from class: com.facebook.react.fabric.FabricUIManager.3
            @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
            public int getSurfaceId() {
                return -1;
            }

            @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
            public void execute(MountingManager mountingManager) {
                try {
                    mountingManager.updateProps(r6, readableMap);
                } catch (Exception unused) {
                }
            }

            public String toString() {
                return String.format("SYNC UPDATE PROPS [%d]: %s", Integer.valueOf(r6), "<hidden>");
            }
        };
        if (!this.mMountingManager.getViewExists(r6)) {
            this.mMountItemDispatcher.addMountItem(mountItem);
            return;
        }
        ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_START, null, r0);
        if (ENABLE_FABRIC_LOGS) {
            FLog.m1338d(TAG, "SynchronouslyUpdateViewOnUIThread for tag %d: %s", Integer.valueOf(r6), "<hidden>");
        }
        mountItem.execute(this.mMountingManager);
        ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_UPDATE_UI_MAIN_THREAD_END, null, r0);
    }

    private void preallocateView(int r12, int r13, String str, Object obj, Object obj2, Object obj3, boolean z) {
        this.mMountItemDispatcher.addPreAllocateMountItem(new PreAllocateViewMountItem(r12, r13, FabricComponents.getFabricComponentName(str), obj, (StateWrapper) obj2, (EventEmitterWrapper) obj3, z));
    }

    private MountItem createIntBufferBatchMountItem(int r2, int[] r3, Object[] objArr, int r5) {
        return new IntBufferBatchMountItem(r2, r3, objArr, r5);
    }

    private void scheduleMountItem(MountItem mountItem, int r21, long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        long uptimeMillis = SystemClock.uptimeMillis();
        boolean z = mountItem instanceof IntBufferBatchMountItem;
        boolean z2 = (z && ((IntBufferBatchMountItem) mountItem).shouldSchedule()) || !(z || mountItem == null);
        for (Iterator<UIManagerListener> it = this.mListeners.iterator(); it.hasNext(); it = it) {
            it.next().didScheduleMountItems(this);
        }
        if (z) {
            this.mCommitStartTime = j;
            this.mLayoutTime = j5 - j4;
            this.mFinishTransactionCPPTime = j7 - j6;
            this.mFinishTransactionTime = uptimeMillis - j6;
            this.mDispatchViewUpdatesTime = SystemClock.uptimeMillis();
        }
        if (z2) {
            this.mMountItemDispatcher.addMountItem(mountItem);
            Runnable runnable = new Runnable() { // from class: com.facebook.react.fabric.FabricUIManager.4
                @Override // java.lang.Runnable
                public void run() {
                    FabricUIManager.this.mMountItemDispatcher.tryDispatchMountItems();
                }
            };
            if (UiThreadUtil.isOnUiThread()) {
                runnable.run();
            } else if (ReactFeatureFlags.enableEarlyScheduledMountItemExecution) {
                UiThreadUtil.runOnUiThread(runnable);
            }
        }
        if (z) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_COMMIT_START, null, r21, j);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_FINISH_TRANSACTION_START, null, r21, j6);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_FINISH_TRANSACTION_END, null, r21, j7);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_DIFF_START, null, r21, j2);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_DIFF_END, null, r21, j3);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_LAYOUT_START, null, r21, j4);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_LAYOUT_END, null, r21, j5);
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_COMMIT_END, null, r21);
        }
    }

    public void setBinding(Binding binding) {
        this.mBinding = binding;
    }

    @Override // com.facebook.react.bridge.UIManager
    public void updateRootLayoutSpecs(int r12, int r13, int r14, int r15, int r16) {
        boolean z;
        boolean z2;
        if (ENABLE_FABRIC_LOGS) {
            FLog.m1339d(TAG, "Updating Root Layout Specs for [%d]", Integer.valueOf(r12));
        }
        SurfaceMountingManager surfaceManager = this.mMountingManager.getSurfaceManager(r12);
        if (surfaceManager == null) {
            String str = TAG;
            ReactSoftExceptionLogger.logSoftException(str, new IllegalViewOperationException("Cannot updateRootLayoutSpecs on surfaceId that does not exist: " + r12));
            return;
        }
        ThemedReactContext context = surfaceManager.getContext();
        if (context != null) {
            boolean isRTL = I18nUtil.getInstance().isRTL(context);
            z2 = I18nUtil.getInstance().doLeftAndRightSwapInRTL(context);
            z = isRTL;
        } else {
            z = false;
            z2 = false;
        }
        this.mBinding.setConstraints(r12, LayoutMetricsConversions.CC.getMinSize(r13), LayoutMetricsConversions.CC.getMaxSize(r13), LayoutMetricsConversions.CC.getMinSize(r14), LayoutMetricsConversions.CC.getMaxSize(r14), r15, r16, z, z2);
    }

    @Override // com.facebook.react.bridge.UIManager
    public View resolveView(int r2) {
        UiThreadUtil.assertOnUiThread();
        SurfaceMountingManager surfaceManagerForView = this.mMountingManager.getSurfaceManagerForView(r2);
        if (surfaceManagerForView == null) {
            return null;
        }
        return surfaceManagerForView.getView(r2);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void receiveEvent(int r2, String str, WritableMap writableMap) {
        receiveEvent(-1, r2, str, writableMap);
    }

    @Override // com.facebook.react.bridge.UIManager
    public void receiveEvent(int r8, int r9, String str, WritableMap writableMap) {
        receiveEvent(r8, r9, str, false, 0, writableMap);
    }

    public void receiveEvent(int r9, int r10, String str, boolean z, int r13, WritableMap writableMap) {
        receiveEvent(r9, r10, str, z, r13, writableMap, 2);
    }

    public void receiveEvent(int r8, int r9, String str, boolean z, int r12, WritableMap writableMap, int r14) {
        if (this.mDestroyed) {
            FLog.m1328e(TAG, "Attempted to receiveEvent after destruction");
            return;
        }
        EventEmitterWrapper eventEmitter = this.mMountingManager.getEventEmitter(r8, r9);
        if (eventEmitter != null) {
            if (z) {
                eventEmitter.invokeUnique(str, writableMap, r12);
            } else {
                eventEmitter.invoke(str, writableMap, r14);
            }
        } else if (ReactFeatureFlags.enableFabricPendingEventQueue && this.mMountingManager.getViewExists(r9)) {
            this.mMountingManager.enqueuePendingEvent(r9, new SurfaceMountingManager.ViewEvent(str, writableMap, r14, z, r12));
        } else {
            String str2 = TAG;
            FLog.m1340d(str2, "Unable to invoke event: " + str + " for reactTag: " + r9);
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
    }

    @Override // com.facebook.react.bridge.UIManager
    public EventDispatcher getEventDispatcher() {
        return this.mEventDispatcher;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        ReactChoreographer.getInstance().removeFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, this.mDispatchUIFrameCallback);
    }

    @Override // com.facebook.react.bridge.UIManager
    @Deprecated
    public void dispatchCommand(int r1, int r2, ReadableArray readableArray) {
        throw new UnsupportedOperationException("dispatchCommand called without surfaceId - Fabric dispatchCommand must be called through Fabric JSI API");
    }

    @Override // com.facebook.react.bridge.UIManager
    @Deprecated
    public void dispatchCommand(int r1, String str, ReadableArray readableArray) {
        throw new UnsupportedOperationException("dispatchCommand called without surfaceId - Fabric dispatchCommand must be called through Fabric JSI API");
    }

    @Deprecated
    public void dispatchCommand(int r3, int r4, int r5, ReadableArray readableArray) {
        this.mMountItemDispatcher.dispatchCommandMountItem(new DispatchIntCommandMountItem(r3, r4, r5, readableArray));
    }

    public void dispatchCommand(int r3, int r4, String str, ReadableArray readableArray) {
        this.mMountItemDispatcher.dispatchCommandMountItem(new DispatchStringCommandMountItem(r3, r4, str, readableArray));
    }

    @Override // com.facebook.react.bridge.UIManager
    public void sendAccessibilityEvent(int r4, int r5) {
        this.mMountItemDispatcher.addMountItem(new SendAccessibilityEvent(-1, r4, r5));
    }

    public void sendAccessibilityEventFromJS(int r3, int r4, String str) {
        int r5;
        if ("focus".equals(str)) {
            r5 = 8;
        } else if ("windowStateChange".equals(str)) {
            r5 = 32;
        } else if (!"click".equals(str)) {
            throw new IllegalArgumentException("sendAccessibilityEventFromJS: invalid eventType " + str);
        } else {
            r5 = 1;
        }
        this.mMountItemDispatcher.addMountItem(new SendAccessibilityEvent(r3, r4, r5));
    }

    public void setJSResponder(final int r9, final int r10, final int r11, final boolean z) {
        this.mMountItemDispatcher.addMountItem(new MountItem() { // from class: com.facebook.react.fabric.FabricUIManager.5
            @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
            public void execute(MountingManager mountingManager) {
                SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(r9);
                if (surfaceManager != null) {
                    surfaceManager.setJSResponder(r10, r11, z);
                    return;
                }
                String str = FabricUIManager.TAG;
                FLog.m1328e(str, "setJSResponder skipped, surface no longer available [" + r9 + "]");
            }

            @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
            public int getSurfaceId() {
                return r9;
            }

            public String toString() {
                return String.format("SET_JS_RESPONDER [%d] [surface:%d]", Integer.valueOf(r10), Integer.valueOf(r9));
            }
        });
    }

    public void clearJSResponder() {
        this.mMountItemDispatcher.addMountItem(new MountItem() { // from class: com.facebook.react.fabric.FabricUIManager.6
            @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
            public int getSurfaceId() {
                return -1;
            }

            public String toString() {
                return "CLEAR_JS_RESPONDER";
            }

            @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
            public void execute(MountingManager mountingManager) {
                mountingManager.clearJSResponder();
            }
        });
    }

    @Override // com.facebook.react.bridge.UIManager
    @Deprecated
    public String resolveCustomDirectEventName(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith(ViewProps.TOP)) {
            return "on" + str.substring(3);
        }
        return str;
    }

    public void onAnimationStarted() {
        this.mDriveCxxAnimations = true;
    }

    public void onAllAnimationsComplete() {
        this.mDriveCxxAnimations = false;
    }

    @Override // com.facebook.react.bridge.PerformanceCounter
    public Map<String, Long> getPerformanceCounters() {
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(this.mCommitStartTime));
        hashMap.put("LayoutTime", Long.valueOf(this.mLayoutTime));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.mDispatchViewUpdatesTime));
        hashMap.put("RunStartTime", Long.valueOf(this.mMountItemDispatcher.getRunStartTime()));
        hashMap.put("BatchedExecutionTime", Long.valueOf(this.mMountItemDispatcher.getBatchedExecutionTime()));
        hashMap.put("FinishFabricTransactionTime", Long.valueOf(this.mFinishTransactionTime));
        hashMap.put("FinishFabricTransactionCPPTime", Long.valueOf(this.mFinishTransactionCPPTime));
        return hashMap;
    }

    /* loaded from: classes.dex */
    private class MountItemDispatchListener implements MountItemDispatcher.ItemDispatchListener {
        private MountItemDispatchListener() {
        }

        @Override // com.facebook.react.fabric.mounting.MountItemDispatcher.ItemDispatchListener
        public void didDispatchMountItems() {
            Iterator it = FabricUIManager.this.mListeners.iterator();
            while (it.hasNext()) {
                ((UIManagerListener) it.next()).didDispatchMountItems(FabricUIManager.this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class DispatchUIFrameCallback extends GuardedFrameCallback {
        private volatile boolean mIsMountingEnabled;

        private DispatchUIFrameCallback(ReactContext reactContext) {
            super(reactContext);
            this.mIsMountingEnabled = true;
        }

        void stop() {
            this.mIsMountingEnabled = false;
        }

        @Override // com.facebook.react.fabric.GuardedFrameCallback
        public void doFrameGuarded(long j) {
            if (this.mIsMountingEnabled && !FabricUIManager.this.mDestroyed) {
                if (FabricUIManager.this.mDriveCxxAnimations && FabricUIManager.this.mBinding != null) {
                    FabricUIManager.this.mBinding.driveCxxAnimations();
                }
                try {
                    try {
                        FabricUIManager.this.mMountItemDispatcher.dispatchPreMountItems(j);
                        FabricUIManager.this.mMountItemDispatcher.tryDispatchMountItems();
                        return;
                    } catch (Exception e) {
                        FLog.m1327e(FabricUIManager.TAG, "Exception thrown when executing UIFrameGuarded", e);
                        stop();
                        throw e;
                    }
                } finally {
                    ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.DISPATCH_UI, FabricUIManager.this.mDispatchUIFrameCallback);
                }
            }
            FLog.m1288w(FabricUIManager.TAG, "Not flushing pending UI operations because of previously thrown Exception");
        }
    }
}
