package com.amplitude.api;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AmplitudeCallbacks implements Application.ActivityLifecycleCallbacks {
    private static final String NULLMSG = "Need to initialize AmplitudeCallbacks with AmplitudeClient instance";
    private static final String TAG = "com.amplitude.api.AmplitudeCallbacks";
    private static AmplitudeLog logger = AmplitudeLog.getLogger();
    private AmplitudeClient clientInstance;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    public AmplitudeCallbacks(AmplitudeClient amplitudeClient) {
        this.clientInstance = null;
        if (amplitudeClient == null) {
            logger.m1368e(TAG, NULLMSG);
            return;
        }
        this.clientInstance = amplitudeClient;
        amplitudeClient.useForegroundTracking();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        AmplitudeClient amplitudeClient = this.clientInstance;
        if (amplitudeClient == null) {
            logger.m1368e(TAG, NULLMSG);
        } else {
            amplitudeClient.onExitForeground(getCurrentTimeMillis());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        AmplitudeClient amplitudeClient = this.clientInstance;
        if (amplitudeClient == null) {
            logger.m1368e(TAG, NULLMSG);
        } else {
            amplitudeClient.onEnterForeground(getCurrentTimeMillis());
        }
    }

    protected long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
