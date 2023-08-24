package com.facebook.react.modules.core;

import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.SystemClock;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class JavaTimerManager {
    private static final float FRAME_DURATION_MS = 16.666666f;
    private static final float IDLE_CALLBACK_FRAME_DEADLINE_MS = 1.0f;
    private IdleCallbackRunnable mCurrentIdleCallbackRunnable;
    private final DevSupportManager mDevSupportManager;
    private final JavaScriptTimerExecutor mJavaScriptTimerExecutor;
    private final ReactApplicationContext mReactApplicationContext;
    private final ReactChoreographer mReactChoreographer;
    private final Object mTimerGuard = new Object();
    private final Object mIdleCallbackGuard = new Object();
    private final AtomicBoolean isPaused = new AtomicBoolean(true);
    private final AtomicBoolean isRunningTasks = new AtomicBoolean(false);
    private final TimerFrameCallback mTimerFrameCallback = new TimerFrameCallback();
    private final IdleFrameCallback mIdleFrameCallback = new IdleFrameCallback();
    private boolean mFrameCallbackPosted = false;
    private boolean mFrameIdleCallbackPosted = false;
    private boolean mSendIdleEvents = false;
    private final PriorityQueue<Timer> mTimers = new PriorityQueue<>(11, new Comparator<Timer>() { // from class: com.facebook.react.modules.core.JavaTimerManager.1
        @Override // java.util.Comparator
        public int compare(Timer timer, Timer timer2) {
            int r2 = ((timer.mTargetTime - timer2.mTargetTime) > 0L ? 1 : ((timer.mTargetTime - timer2.mTargetTime) == 0L ? 0 : -1));
            if (r2 == 0) {
                return 0;
            }
            return r2 < 0 ? -1 : 1;
        }
    });
    private final SparseArray<Timer> mTimerIdsToTimers = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Timer {
        private final int mCallbackID;
        private final int mInterval;
        private final boolean mRepeat;
        private long mTargetTime;

        private Timer(int r1, long j, int r4, boolean z) {
            this.mCallbackID = r1;
            this.mTargetTime = j;
            this.mInterval = r4;
            this.mRepeat = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class TimerFrameCallback extends ChoreographerCompat.FrameCallback {
        private WritableArray mTimersToCall;

        private TimerFrameCallback() {
            this.mTimersToCall = null;
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j) {
            if (!JavaTimerManager.this.isPaused.get() || JavaTimerManager.this.isRunningTasks.get()) {
                long j2 = j / 1000000;
                synchronized (JavaTimerManager.this.mTimerGuard) {
                    while (!JavaTimerManager.this.mTimers.isEmpty() && ((Timer) JavaTimerManager.this.mTimers.peek()).mTargetTime < j2) {
                        Timer timer = (Timer) JavaTimerManager.this.mTimers.poll();
                        if (this.mTimersToCall == null) {
                            this.mTimersToCall = Arguments.createArray();
                        }
                        this.mTimersToCall.pushInt(timer.mCallbackID);
                        if (timer.mRepeat) {
                            timer.mTargetTime = timer.mInterval + j2;
                            JavaTimerManager.this.mTimers.add(timer);
                        } else {
                            JavaTimerManager.this.mTimerIdsToTimers.remove(timer.mCallbackID);
                        }
                    }
                }
                if (this.mTimersToCall != null) {
                    JavaTimerManager.this.mJavaScriptTimerExecutor.callTimers(this.mTimersToCall);
                    this.mTimersToCall = null;
                }
                JavaTimerManager.this.mReactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class IdleFrameCallback extends ChoreographerCompat.FrameCallback {
        private IdleFrameCallback() {
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j) {
            if (!JavaTimerManager.this.isPaused.get() || JavaTimerManager.this.isRunningTasks.get()) {
                if (JavaTimerManager.this.mCurrentIdleCallbackRunnable != null) {
                    JavaTimerManager.this.mCurrentIdleCallbackRunnable.cancel();
                }
                JavaTimerManager javaTimerManager = JavaTimerManager.this;
                javaTimerManager.mCurrentIdleCallbackRunnable = new IdleCallbackRunnable(j);
                JavaTimerManager.this.mReactApplicationContext.runOnJSQueueThread(JavaTimerManager.this.mCurrentIdleCallbackRunnable);
                JavaTimerManager.this.mReactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this);
            }
        }
    }

    /* loaded from: classes.dex */
    private class IdleCallbackRunnable implements Runnable {
        private volatile boolean mCancelled = false;
        private final long mFrameStartTime;

        public IdleCallbackRunnable(long j) {
            this.mFrameStartTime = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            if (this.mCancelled) {
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis() - (this.mFrameStartTime / 1000000);
            long currentTimeMillis = SystemClock.currentTimeMillis() - uptimeMillis;
            if (JavaTimerManager.FRAME_DURATION_MS - ((float) uptimeMillis) < 1.0f) {
                return;
            }
            synchronized (JavaTimerManager.this.mIdleCallbackGuard) {
                z = JavaTimerManager.this.mSendIdleEvents;
            }
            if (z) {
                JavaTimerManager.this.mJavaScriptTimerExecutor.callIdleCallbacks(currentTimeMillis);
            }
            JavaTimerManager.this.mCurrentIdleCallbackRunnable = null;
        }

        public void cancel() {
            this.mCancelled = true;
        }
    }

    public JavaTimerManager(ReactApplicationContext reactApplicationContext, JavaScriptTimerExecutor javaScriptTimerExecutor, ReactChoreographer reactChoreographer, DevSupportManager devSupportManager) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mJavaScriptTimerExecutor = javaScriptTimerExecutor;
        this.mReactChoreographer = reactChoreographer;
        this.mDevSupportManager = devSupportManager;
    }

    public void onHostPause() {
        this.isPaused.set(true);
        clearFrameCallback();
        maybeIdleCallback();
    }

    public void onHostDestroy() {
        clearFrameCallback();
        maybeIdleCallback();
    }

    public void onHostResume() {
        this.isPaused.set(false);
        setChoreographerCallback();
        maybeSetChoreographerIdleCallback();
    }

    public void onHeadlessJsTaskStart(int r2) {
        if (this.isRunningTasks.getAndSet(true)) {
            return;
        }
        setChoreographerCallback();
        maybeSetChoreographerIdleCallback();
    }

    public void onHeadlessJsTaskFinish(int r2) {
        if (HeadlessJsTaskContext.getInstance(this.mReactApplicationContext).hasActiveTasks()) {
            return;
        }
        this.isRunningTasks.set(false);
        clearFrameCallback();
        maybeIdleCallback();
    }

    public void onInstanceDestroy() {
        clearFrameCallback();
        clearChoreographerIdleCallback();
    }

    private void maybeSetChoreographerIdleCallback() {
        synchronized (this.mIdleCallbackGuard) {
            if (this.mSendIdleEvents) {
                setChoreographerIdleCallback();
            }
        }
    }

    private void maybeIdleCallback() {
        if (!this.isPaused.get() || this.isRunningTasks.get()) {
            return;
        }
        clearFrameCallback();
    }

    private void setChoreographerCallback() {
        if (this.mFrameCallbackPosted) {
            return;
        }
        this.mReactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, this.mTimerFrameCallback);
        this.mFrameCallbackPosted = true;
    }

    private void clearFrameCallback() {
        HeadlessJsTaskContext headlessJsTaskContext = HeadlessJsTaskContext.getInstance(this.mReactApplicationContext);
        if (this.mFrameCallbackPosted && this.isPaused.get() && !headlessJsTaskContext.hasActiveTasks()) {
            this.mReactChoreographer.removeFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, this.mTimerFrameCallback);
            this.mFrameCallbackPosted = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChoreographerIdleCallback() {
        if (this.mFrameIdleCallbackPosted) {
            return;
        }
        this.mReactChoreographer.postFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this.mIdleFrameCallback);
        this.mFrameIdleCallbackPosted = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearChoreographerIdleCallback() {
        if (this.mFrameIdleCallbackPosted) {
            this.mReactChoreographer.removeFrameCallback(ReactChoreographer.CallbackType.IDLE_EVENT, this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = false;
        }
    }

    public void createTimer(int r10, long j, boolean z) {
        Timer timer = new Timer(r10, (SystemClock.nanoTime() / 1000000) + j, (int) j, z);
        synchronized (this.mTimerGuard) {
            this.mTimers.add(timer);
            this.mTimerIdsToTimers.put(r10, timer);
        }
    }

    public void createAndMaybeCallTimer(int r8, int r9, double d, boolean z) {
        long currentTimeMillis = SystemClock.currentTimeMillis();
        long j = (long) d;
        if (this.mDevSupportManager.getDevSupportEnabled() && Math.abs(j - currentTimeMillis) > 60000) {
            this.mJavaScriptTimerExecutor.emitTimeDriftWarning("Debugger and device times have drifted by more than 60s. Please correct this by running adb shell \"date `date +%m%d%H%M%Y.%S`\" on your debugger machine.");
        }
        long max = Math.max(0L, (j - currentTimeMillis) + r9);
        if (r9 == 0 && !z) {
            WritableArray createArray = Arguments.createArray();
            createArray.pushInt(r8);
            this.mJavaScriptTimerExecutor.callTimers(createArray);
            return;
        }
        createTimer(r8, max, z);
    }

    public void deleteTimer(int r4) {
        synchronized (this.mTimerGuard) {
            Timer timer = this.mTimerIdsToTimers.get(r4);
            if (timer == null) {
                return;
            }
            this.mTimerIdsToTimers.remove(r4);
            this.mTimers.remove(timer);
        }
    }

    public void setSendIdleEvents(final boolean z) {
        synchronized (this.mIdleCallbackGuard) {
            this.mSendIdleEvents = z;
        }
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.core.JavaTimerManager.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (JavaTimerManager.this.mIdleCallbackGuard) {
                    if (z) {
                        JavaTimerManager.this.setChoreographerIdleCallback();
                    } else {
                        JavaTimerManager.this.clearChoreographerIdleCallback();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasActiveTimersInRange(long j) {
        synchronized (this.mTimerGuard) {
            Timer peek = this.mTimers.peek();
            if (peek == null) {
                return false;
            }
            if (isTimerInRange(peek, j)) {
                return true;
            }
            Iterator<Timer> it = this.mTimers.iterator();
            while (it.hasNext()) {
                if (isTimerInRange(it.next(), j)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static boolean isTimerInRange(Timer timer, long j) {
        return !timer.mRepeat && ((long) timer.mInterval) < j;
    }
}
