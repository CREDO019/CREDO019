package com.amplitude.api;

import android.os.Handler;
import android.os.HandlerThread;

/* loaded from: classes.dex */
public class WorkerThread extends HandlerThread {
    private Handler handler;

    public WorkerThread(String str) {
        super(str, 10);
    }

    Handler getHandler() {
        return this.handler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void post(Runnable runnable) {
        waitForInitialization();
        this.handler.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postDelayed(Runnable runnable, long j) {
        waitForInitialization();
        this.handler.postDelayed(runnable, j);
    }

    void removeCallbacks(Runnable runnable) {
        waitForInitialization();
        this.handler.removeCallbacks(runnable);
    }

    private synchronized void waitForInitialization() {
        if (this.handler == null) {
            this.handler = new Handler(getLooper());
        }
    }
}
