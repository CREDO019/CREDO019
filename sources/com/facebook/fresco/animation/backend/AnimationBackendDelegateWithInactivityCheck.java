package com.facebook.fresco.animation.backend;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.facebook.common.time.MonotonicClock;
import com.facebook.fresco.animation.backend.AnimationBackend;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AnimationBackendDelegateWithInactivityCheck<T extends AnimationBackend> extends AnimationBackendDelegate<T> {
    static final long INACTIVITY_CHECK_POLLING_TIME_MS = 1000;
    static final long INACTIVITY_THRESHOLD_MS = 2000;
    private long mInactivityCheckPollingTimeMs;
    private boolean mInactivityCheckScheduled;
    @Nullable
    private InactivityListener mInactivityListener;
    private long mInactivityThresholdMs;
    private final Runnable mIsInactiveCheck;
    private long mLastDrawnTimeMs;
    private final MonotonicClock mMonotonicClock;
    private final ScheduledExecutorService mScheduledExecutorServiceForUiThread;

    /* loaded from: classes.dex */
    public interface InactivityListener {
        void onInactive();
    }

    public static <T extends AnimationBackend & InactivityListener> AnimationBackendDelegate<T> createForBackend(T backend, MonotonicClock monotonicClock, ScheduledExecutorService scheduledExecutorServiceForUiThread) {
        return createForBackend(backend, (InactivityListener) backend, monotonicClock, scheduledExecutorServiceForUiThread);
    }

    public static <T extends AnimationBackend> AnimationBackendDelegate<T> createForBackend(T backend, InactivityListener inactivityListener, MonotonicClock monotonicClock, ScheduledExecutorService scheduledExecutorServiceForUiThread) {
        return new AnimationBackendDelegateWithInactivityCheck(backend, inactivityListener, monotonicClock, scheduledExecutorServiceForUiThread);
    }

    private AnimationBackendDelegateWithInactivityCheck(@Nullable T animationBackend, @Nullable InactivityListener inactivityListener, MonotonicClock monotonicClock, ScheduledExecutorService scheduledExecutorServiceForUiThread) {
        super(animationBackend);
        this.mInactivityCheckScheduled = false;
        this.mInactivityThresholdMs = 2000L;
        this.mInactivityCheckPollingTimeMs = 1000L;
        this.mIsInactiveCheck = new Runnable() { // from class: com.facebook.fresco.animation.backend.AnimationBackendDelegateWithInactivityCheck.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (AnimationBackendDelegateWithInactivityCheck.this) {
                    AnimationBackendDelegateWithInactivityCheck.this.mInactivityCheckScheduled = false;
                    if (AnimationBackendDelegateWithInactivityCheck.this.isInactive()) {
                        if (AnimationBackendDelegateWithInactivityCheck.this.mInactivityListener != null) {
                            AnimationBackendDelegateWithInactivityCheck.this.mInactivityListener.onInactive();
                        }
                    } else {
                        AnimationBackendDelegateWithInactivityCheck.this.maybeScheduleInactivityCheck();
                    }
                }
            }
        };
        this.mInactivityListener = inactivityListener;
        this.mMonotonicClock = monotonicClock;
        this.mScheduledExecutorServiceForUiThread = scheduledExecutorServiceForUiThread;
    }

    @Override // com.facebook.fresco.animation.backend.AnimationBackendDelegate, com.facebook.fresco.animation.backend.AnimationBackend
    public boolean drawFrame(Drawable parent, Canvas canvas, int frameNumber) {
        this.mLastDrawnTimeMs = this.mMonotonicClock.now();
        boolean drawFrame = super.drawFrame(parent, canvas, frameNumber);
        maybeScheduleInactivityCheck();
        return drawFrame;
    }

    public void setInactivityListener(@Nullable InactivityListener inactivityListener) {
        this.mInactivityListener = inactivityListener;
    }

    public long getInactivityCheckPollingTimeMs() {
        return this.mInactivityCheckPollingTimeMs;
    }

    public void setInactivityCheckPollingTimeMs(long inactivityCheckPollingTimeMs) {
        this.mInactivityCheckPollingTimeMs = inactivityCheckPollingTimeMs;
    }

    public long getInactivityThresholdMs() {
        return this.mInactivityThresholdMs;
    }

    public void setInactivityThresholdMs(long inactivityThresholdMs) {
        this.mInactivityThresholdMs = inactivityThresholdMs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInactive() {
        return this.mMonotonicClock.now() - this.mLastDrawnTimeMs > this.mInactivityThresholdMs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void maybeScheduleInactivityCheck() {
        if (!this.mInactivityCheckScheduled) {
            this.mInactivityCheckScheduled = true;
            this.mScheduledExecutorServiceForUiThread.schedule(this.mIsInactiveCheck, this.mInactivityCheckPollingTimeMs, TimeUnit.MILLISECONDS);
        }
    }
}
