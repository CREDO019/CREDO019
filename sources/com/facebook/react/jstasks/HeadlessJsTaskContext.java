package com.facebook.react.jstasks;

import android.os.Handler;
import android.util.SparseArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.appregistry.AppRegistry;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class HeadlessJsTaskContext {
    private static final WeakHashMap<ReactContext, HeadlessJsTaskContext> INSTANCES = new WeakHashMap<>();
    private final WeakReference<ReactContext> mReactContext;
    private final Set<HeadlessJsTaskEventListener> mHeadlessJsTaskEventListeners = new CopyOnWriteArraySet();
    private final AtomicInteger mLastTaskId = new AtomicInteger(0);
    private final Handler mHandler = new Handler();
    private final Set<Integer> mActiveTasks = new CopyOnWriteArraySet();
    private final Map<Integer, HeadlessJsTaskConfig> mActiveTaskConfigs = new ConcurrentHashMap();
    private final SparseArray<Runnable> mTaskTimeouts = new SparseArray<>();

    public static HeadlessJsTaskContext getInstance(ReactContext reactContext) {
        WeakHashMap<ReactContext, HeadlessJsTaskContext> weakHashMap = INSTANCES;
        HeadlessJsTaskContext headlessJsTaskContext = weakHashMap.get(reactContext);
        if (headlessJsTaskContext == null) {
            HeadlessJsTaskContext headlessJsTaskContext2 = new HeadlessJsTaskContext(reactContext);
            weakHashMap.put(reactContext, headlessJsTaskContext2);
            return headlessJsTaskContext2;
        }
        return headlessJsTaskContext;
    }

    private HeadlessJsTaskContext(ReactContext reactContext) {
        this.mReactContext = new WeakReference<>(reactContext);
    }

    public synchronized void addTaskEventListener(HeadlessJsTaskEventListener headlessJsTaskEventListener) {
        this.mHeadlessJsTaskEventListeners.add(headlessJsTaskEventListener);
        for (Integer num : this.mActiveTasks) {
            headlessJsTaskEventListener.onHeadlessJsTaskStart(num.intValue());
        }
    }

    public void removeTaskEventListener(HeadlessJsTaskEventListener headlessJsTaskEventListener) {
        this.mHeadlessJsTaskEventListeners.remove(headlessJsTaskEventListener);
    }

    public boolean hasActiveTasks() {
        return this.mActiveTasks.size() > 0;
    }

    public synchronized int startTask(HeadlessJsTaskConfig headlessJsTaskConfig) {
        int incrementAndGet;
        incrementAndGet = this.mLastTaskId.incrementAndGet();
        startTask(headlessJsTaskConfig, incrementAndGet);
        return incrementAndGet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void startTask(HeadlessJsTaskConfig headlessJsTaskConfig, int r7) {
        UiThreadUtil.assertOnUiThread();
        ReactContext reactContext = (ReactContext) Assertions.assertNotNull(this.mReactContext.get(), "Tried to start a task on a react context that has already been destroyed");
        if (reactContext.getLifecycleState() == LifecycleState.RESUMED && !headlessJsTaskConfig.isAllowedInForeground()) {
            throw new IllegalStateException("Tried to start task " + headlessJsTaskConfig.getTaskKey() + " while in foreground, but this is not allowed.");
        }
        this.mActiveTasks.add(Integer.valueOf(r7));
        this.mActiveTaskConfigs.put(Integer.valueOf(r7), new HeadlessJsTaskConfig(headlessJsTaskConfig));
        if (reactContext.hasActiveReactInstance()) {
            ((AppRegistry) reactContext.getJSModule(AppRegistry.class)).startHeadlessTask(r7, headlessJsTaskConfig.getTaskKey(), headlessJsTaskConfig.getData());
        } else {
            ReactSoftExceptionLogger.logSoftException("HeadlessJsTaskContext", new RuntimeException("Cannot start headless task, CatalystInstance not available"));
        }
        if (headlessJsTaskConfig.getTimeout() > 0) {
            scheduleTaskTimeout(r7, headlessJsTaskConfig.getTimeout());
        }
        for (HeadlessJsTaskEventListener headlessJsTaskEventListener : this.mHeadlessJsTaskEventListeners) {
            headlessJsTaskEventListener.onHeadlessJsTaskStart(r7);
        }
    }

    public synchronized boolean retryTask(final int r13) {
        HeadlessJsTaskConfig headlessJsTaskConfig = this.mActiveTaskConfigs.get(Integer.valueOf(r13));
        boolean z = headlessJsTaskConfig != null;
        Assertions.assertCondition(z, "Tried to retrieve non-existent task config with id " + r13 + ".");
        HeadlessJsTaskRetryPolicy retryPolicy = headlessJsTaskConfig.getRetryPolicy();
        if (retryPolicy.canRetry()) {
            removeTimeout(r13);
            final HeadlessJsTaskConfig headlessJsTaskConfig2 = new HeadlessJsTaskConfig(headlessJsTaskConfig.getTaskKey(), headlessJsTaskConfig.getData(), headlessJsTaskConfig.getTimeout(), headlessJsTaskConfig.isAllowedInForeground(), retryPolicy.update());
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.jstasks.HeadlessJsTaskContext.1
                @Override // java.lang.Runnable
                public void run() {
                    HeadlessJsTaskContext.this.startTask(headlessJsTaskConfig2, r13);
                }
            }, retryPolicy.getDelay());
            return true;
        }
        return false;
    }

    public synchronized void finishTask(final int r4) {
        boolean remove = this.mActiveTasks.remove(Integer.valueOf(r4));
        Assertions.assertCondition(remove, "Tried to finish non-existent task with id " + r4 + ".");
        boolean z = this.mActiveTaskConfigs.remove(Integer.valueOf(r4)) != null;
        Assertions.assertCondition(z, "Tried to remove non-existent task config with id " + r4 + ".");
        removeTimeout(r4);
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.jstasks.HeadlessJsTaskContext.2
            @Override // java.lang.Runnable
            public void run() {
                for (HeadlessJsTaskEventListener headlessJsTaskEventListener : HeadlessJsTaskContext.this.mHeadlessJsTaskEventListeners) {
                    headlessJsTaskEventListener.onHeadlessJsTaskFinish(r4);
                }
            }
        });
    }

    private void removeTimeout(int r3) {
        Runnable runnable = this.mTaskTimeouts.get(r3);
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
            this.mTaskTimeouts.remove(r3);
        }
    }

    public synchronized boolean isTaskRunning(int r2) {
        return this.mActiveTasks.contains(Integer.valueOf(r2));
    }

    private void scheduleTaskTimeout(final int r3, long j) {
        Runnable runnable = new Runnable() { // from class: com.facebook.react.jstasks.HeadlessJsTaskContext.3
            @Override // java.lang.Runnable
            public void run() {
                HeadlessJsTaskContext.this.finishTask(r3);
            }
        };
        this.mTaskTimeouts.append(r3, runnable);
        this.mHandler.postDelayed(runnable, j);
    }
}
