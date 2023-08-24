package com.facebook.react.fabric.mounting;

import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.uimanager.RootViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.yoga.YogaMeasureMode;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class MountingManager {
    private static final int MAX_STOPPED_SURFACE_IDS_LENGTH = 15;
    public static final String TAG = "MountingManager";
    private SurfaceMountingManager mLastQueriedSurfaceMountingManager;
    private SurfaceMountingManager mMostRecentSurfaceMountingManager;
    private final MountItemExecutor mMountItemExecutor;
    private final ViewManagerRegistry mViewManagerRegistry;
    private final ConcurrentHashMap<Integer, SurfaceMountingManager> mSurfaceIdToManager = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<Integer> mStoppedSurfaceIds = new CopyOnWriteArrayList<>();
    private final JSResponderHandler mJSResponderHandler = new JSResponderHandler();
    private final RootViewManager mRootViewManager = new RootViewManager();

    /* loaded from: classes.dex */
    public interface MountItemExecutor {
        void executeItems(Queue<MountItem> queue);
    }

    public MountingManager(ViewManagerRegistry viewManagerRegistry, MountItemExecutor mountItemExecutor) {
        this.mViewManagerRegistry = viewManagerRegistry;
        this.mMountItemExecutor = mountItemExecutor;
    }

    public SurfaceMountingManager startSurface(int r9, ThemedReactContext themedReactContext, View view) {
        SurfaceMountingManager surfaceMountingManager = new SurfaceMountingManager(r9, this.mJSResponderHandler, this.mViewManagerRegistry, this.mRootViewManager, this.mMountItemExecutor, themedReactContext);
        this.mSurfaceIdToManager.putIfAbsent(Integer.valueOf(r9), surfaceMountingManager);
        if (this.mSurfaceIdToManager.get(Integer.valueOf(r9)) != surfaceMountingManager) {
            String str = TAG;
            ReactSoftExceptionLogger.logSoftException(str, new IllegalStateException("Called startSurface more than once for the SurfaceId [" + r9 + "]"));
        }
        this.mMostRecentSurfaceMountingManager = this.mSurfaceIdToManager.get(Integer.valueOf(r9));
        if (view != null) {
            surfaceMountingManager.attachRootView(view, themedReactContext);
        }
        return surfaceMountingManager;
    }

    public void attachRootView(int r2, View view, ThemedReactContext themedReactContext) {
        SurfaceMountingManager surfaceManagerEnforced = getSurfaceManagerEnforced(r2, "attachView");
        if (surfaceManagerEnforced.isStopped()) {
            ReactSoftExceptionLogger.logSoftException(TAG, new IllegalStateException("Trying to attach a view to a stopped surface"));
        } else {
            surfaceManagerEnforced.attachRootView(view, themedReactContext);
        }
    }

    public void stopSurface(int r5) {
        SurfaceMountingManager surfaceMountingManager = this.mSurfaceIdToManager.get(Integer.valueOf(r5));
        if (surfaceMountingManager != null) {
            while (this.mStoppedSurfaceIds.size() >= 15) {
                Integer num = this.mStoppedSurfaceIds.get(0);
                this.mSurfaceIdToManager.remove(Integer.valueOf(num.intValue()));
                this.mStoppedSurfaceIds.remove(num);
                FLog.m1339d(TAG, "Removing stale SurfaceMountingManager: [%d]", Integer.valueOf(num.intValue()));
            }
            this.mStoppedSurfaceIds.add(Integer.valueOf(r5));
            surfaceMountingManager.stopSurface();
            if (surfaceMountingManager == this.mMostRecentSurfaceMountingManager) {
                this.mMostRecentSurfaceMountingManager = null;
                return;
            }
            return;
        }
        String str = TAG;
        ReactSoftExceptionLogger.logSoftException(str, new IllegalStateException("Cannot call stopSurface on non-existent surface: [" + r5 + "]"));
    }

    public SurfaceMountingManager getSurfaceManager(int r2) {
        SurfaceMountingManager surfaceMountingManager = this.mLastQueriedSurfaceMountingManager;
        if (surfaceMountingManager != null && surfaceMountingManager.getSurfaceId() == r2) {
            return this.mLastQueriedSurfaceMountingManager;
        }
        SurfaceMountingManager surfaceMountingManager2 = this.mMostRecentSurfaceMountingManager;
        if (surfaceMountingManager2 != null && surfaceMountingManager2.getSurfaceId() == r2) {
            return this.mMostRecentSurfaceMountingManager;
        }
        SurfaceMountingManager surfaceMountingManager3 = this.mSurfaceIdToManager.get(Integer.valueOf(r2));
        this.mLastQueriedSurfaceMountingManager = surfaceMountingManager3;
        return surfaceMountingManager3;
    }

    public SurfaceMountingManager getSurfaceManagerEnforced(int r4, String str) {
        SurfaceMountingManager surfaceManager = getSurfaceManager(r4);
        if (surfaceManager != null) {
            return surfaceManager;
        }
        throw new RetryableMountingLayerException("Unable to find SurfaceMountingManager for surfaceId: [" + r4 + "]. Context: " + str);
    }

    public boolean surfaceIsStopped(int r3) {
        if (this.mStoppedSurfaceIds.contains(Integer.valueOf(r3))) {
            return true;
        }
        SurfaceMountingManager surfaceManager = getSurfaceManager(r3);
        return surfaceManager != null && surfaceManager.isStopped();
    }

    public boolean isWaitingForViewAttach(int r3) {
        SurfaceMountingManager surfaceManager = getSurfaceManager(r3);
        if (surfaceManager == null || surfaceManager.isStopped()) {
            return false;
        }
        return !surfaceManager.isRootViewAttached();
    }

    public SurfaceMountingManager getSurfaceManagerForView(int r4) {
        SurfaceMountingManager surfaceMountingManager = this.mMostRecentSurfaceMountingManager;
        if (surfaceMountingManager != null && surfaceMountingManager.getViewExists(r4)) {
            return this.mMostRecentSurfaceMountingManager;
        }
        for (Map.Entry<Integer, SurfaceMountingManager> entry : this.mSurfaceIdToManager.entrySet()) {
            SurfaceMountingManager value = entry.getValue();
            if (value != this.mMostRecentSurfaceMountingManager && value.getViewExists(r4)) {
                if (this.mMostRecentSurfaceMountingManager == null) {
                    this.mMostRecentSurfaceMountingManager = value;
                }
                return value;
            }
        }
        return null;
    }

    public SurfaceMountingManager getSurfaceManagerForViewEnforced(int r4) {
        SurfaceMountingManager surfaceManagerForView = getSurfaceManagerForView(r4);
        if (surfaceManagerForView != null) {
            return surfaceManagerForView;
        }
        throw new RetryableMountingLayerException("Unable to find SurfaceMountingManager for tag: [" + r4 + "]");
    }

    public boolean getViewExists(int r1) {
        return getSurfaceManagerForView(r1) != null;
    }

    @Deprecated
    public void receiveCommand(int r2, int r3, int r4, ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        getSurfaceManagerEnforced(r2, "receiveCommand:int").receiveCommand(r3, r4, readableArray);
    }

    public void receiveCommand(int r2, int r3, String str, ReadableArray readableArray) {
        UiThreadUtil.assertOnUiThread();
        getSurfaceManagerEnforced(r2, "receiveCommand:string").receiveCommand(r3, str, readableArray);
    }

    public void sendAccessibilityEvent(int r2, int r3, int r4) {
        UiThreadUtil.assertOnUiThread();
        if (r2 == -1) {
            getSurfaceManagerForViewEnforced(r3).sendAccessibilityEvent(r3, r4);
        } else {
            getSurfaceManagerEnforced(r2, "sendAccessibilityEvent").sendAccessibilityEvent(r3, r4);
        }
    }

    public void updateProps(int r2, ReadableMap readableMap) {
        UiThreadUtil.assertOnUiThread();
        if (readableMap == null) {
            return;
        }
        getSurfaceManagerForViewEnforced(r2).updateProps(r2, readableMap);
    }

    public void clearJSResponder() {
        this.mJSResponderHandler.clearJSResponder();
    }

    public EventEmitterWrapper getEventEmitter(int r2, int r3) {
        SurfaceMountingManager surfaceManagerForView = r2 == -1 ? getSurfaceManagerForView(r3) : getSurfaceManager(r2);
        if (surfaceManagerForView == null) {
            return null;
        }
        return surfaceManagerForView.getEventEmitter(r3);
    }

    public long measure(ReactContext reactContext, String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, float[] fArr) {
        return this.mViewManagerRegistry.get(str).measure(reactContext, readableMap, readableMap2, readableMap3, f, yogaMeasureMode, f2, yogaMeasureMode2, fArr);
    }

    public long measureMapBuffer(ReactContext reactContext, String str, MapBuffer mapBuffer, MapBuffer mapBuffer2, MapBuffer mapBuffer3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, float[] fArr) {
        return this.mViewManagerRegistry.get(str).measure(reactContext, mapBuffer, mapBuffer2, mapBuffer3, f, yogaMeasureMode, f2, yogaMeasureMode2, fArr);
    }

    public void initializeViewManager(String str) {
        this.mViewManagerRegistry.get(str);
    }

    public void enqueuePendingEvent(int r2, SurfaceMountingManager.ViewEvent viewEvent) {
        SurfaceMountingManager surfaceManagerForView = getSurfaceManagerForView(r2);
        if (surfaceManagerForView == null) {
            return;
        }
        surfaceManagerForView.enqueuePendingEvent(r2, viewEvent);
    }
}
