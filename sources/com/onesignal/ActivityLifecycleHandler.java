package com.onesignal;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.view.ViewTreeObserver;
import com.google.android.exoplayer2.ExoPlayer;
import com.onesignal.OSSystemConditionController;
import com.onesignal.OneSignal;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ActivityLifecycleHandler implements OSSystemConditionController.OSSystemConditionHandler {
    private static final String FOCUS_LOST_WORKER_TAG = "FOCUS_LOST_WORKER_TAG";
    private static final int SYNC_AFTER_BG_DELAY_MS = 2000;
    private final OSFocusHandler focusHandler;
    private static final Map<String, ActivityAvailableListener> sActivityAvailableListeners = new ConcurrentHashMap();
    private static final Map<String, OSSystemConditionController.OSSystemConditionObserver> sSystemConditionObservers = new ConcurrentHashMap();
    private static final Map<String, KeyboardListener> sKeyboardListeners = new ConcurrentHashMap();
    private Activity curActivity = null;
    private boolean nextResumeIsFirstActivity = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static abstract class ActivityAvailableListener {
        void available(Activity activity) {
        }

        void lostFocus() {
        }

        void stopped(Activity activity) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivityCreated(Activity activity) {
    }

    public ActivityLifecycleHandler(OSFocusHandler oSFocusHandler) {
        this.focusHandler = oSFocusHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onConfigurationChanged(Configuration configuration, Activity activity) {
        Activity activity2 = this.curActivity;
        if (activity2 == null || !OSUtils.hasConfigChangeFlag(activity2, 128)) {
            return;
        }
        logOrientationChange(configuration.orientation, activity);
        onOrientationChanged(activity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivityStarted(Activity activity) {
        this.focusHandler.startOnStartFocusWork();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivityResumed(Activity activity) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "onActivityResumed: " + activity);
        setCurActivity(activity);
        logCurActivity();
        handleFocus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivityPaused(Activity activity) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "onActivityPaused: " + activity);
        if (activity == this.curActivity) {
            this.curActivity = null;
            handleLostFocus();
        }
        logCurActivity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivityStopped(Activity activity) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "onActivityStopped: " + activity);
        if (activity == this.curActivity) {
            this.curActivity = null;
            handleLostFocus();
        }
        for (Map.Entry<String, ActivityAvailableListener> entry : sActivityAvailableListeners.entrySet()) {
            entry.getValue().stopped(activity);
        }
        logCurActivity();
        if (this.curActivity == null) {
            this.focusHandler.startOnStopFocusWork();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onActivityDestroyed(Activity activity) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "onActivityDestroyed: " + activity);
        sKeyboardListeners.clear();
        if (activity == this.curActivity) {
            this.curActivity = null;
            handleLostFocus();
        }
        logCurActivity();
    }

    private void logCurActivity() {
        String str;
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        StringBuilder sb = new StringBuilder();
        sb.append("curActivity is NOW: ");
        if (this.curActivity != null) {
            str = "" + this.curActivity.getClass().getName() + ParameterizedMessage.ERROR_MSG_SEPARATOR + this.curActivity;
        } else {
            str = "null";
        }
        sb.append(str);
        OneSignal.Log(log_level, sb.toString());
    }

    private void logOrientationChange(int r5, Activity activity) {
        if (r5 == 2) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.onesignalLog(log_level, "Configuration Orientation Change: LANDSCAPE (" + r5 + ") on activity: " + activity);
        } else if (r5 == 1) {
            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.onesignalLog(log_level2, "Configuration Orientation Change: PORTRAIT (" + r5 + ") on activity: " + activity);
        }
    }

    private void onOrientationChanged(Activity activity) {
        handleLostFocus();
        for (Map.Entry<String, ActivityAvailableListener> entry : sActivityAvailableListeners.entrySet()) {
            entry.getValue().stopped(activity);
        }
        for (Map.Entry<String, ActivityAvailableListener> entry2 : sActivityAvailableListeners.entrySet()) {
            entry2.getValue().available(this.curActivity);
        }
        ViewTreeObserver viewTreeObserver = this.curActivity.getWindow().getDecorView().getViewTreeObserver();
        for (Map.Entry<String, OSSystemConditionController.OSSystemConditionObserver> entry3 : sSystemConditionObservers.entrySet()) {
            KeyboardListener keyboardListener = new KeyboardListener(this, entry3.getValue(), entry3.getKey());
            viewTreeObserver.addOnGlobalLayoutListener(keyboardListener);
            sKeyboardListeners.put(entry3.getKey(), keyboardListener);
        }
        handleFocus();
    }

    private void handleLostFocus() {
        OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, "ActivityLifecycleHandler Handling lost focus");
        OSFocusHandler oSFocusHandler = this.focusHandler;
        if (oSFocusHandler != null) {
            if (!oSFocusHandler.hasBackgrounded() || this.focusHandler.hasCompleted()) {
                OneSignal.getFocusTimeController().appStopped();
                this.focusHandler.startOnLostFocusWorker(FOCUS_LOST_WORKER_TAG, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, OneSignal.appContext);
            }
        }
    }

    private void handleFocus() {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.onesignalLog(log_level, "ActivityLifecycleHandler handleFocus, nextResumeIsFirstActivity: " + this.nextResumeIsFirstActivity);
        if (this.focusHandler.hasBackgrounded() || this.nextResumeIsFirstActivity) {
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, "ActivityLifecycleHandler reset background state, call app focus");
            this.nextResumeIsFirstActivity = false;
            this.focusHandler.startOnFocusWork();
            return;
        }
        OneSignal.onesignalLog(OneSignal.LOG_LEVEL.DEBUG, "ActivityLifecycleHandler cancel background lost focus worker");
        this.focusHandler.cancelOnLostFocusWorker(FOCUS_LOST_WORKER_TAG, OneSignal.appContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addSystemConditionObserver(String str, OSSystemConditionController.OSSystemConditionObserver oSSystemConditionObserver) {
        Activity activity = this.curActivity;
        if (activity != null) {
            ViewTreeObserver viewTreeObserver = activity.getWindow().getDecorView().getViewTreeObserver();
            KeyboardListener keyboardListener = new KeyboardListener(this, oSSystemConditionObserver, str);
            viewTreeObserver.addOnGlobalLayoutListener(keyboardListener);
            sKeyboardListeners.put(str, keyboardListener);
        }
        sSystemConditionObservers.put(str, oSSystemConditionObserver);
    }

    @Override // com.onesignal.OSSystemConditionController.OSSystemConditionHandler
    public void removeSystemConditionObserver(String str, KeyboardListener keyboardListener) {
        Activity activity = this.curActivity;
        if (activity != null) {
            ViewTreeObserver viewTreeObserver = activity.getWindow().getDecorView().getViewTreeObserver();
            if (Build.VERSION.SDK_INT < 16) {
                viewTreeObserver.removeGlobalOnLayoutListener(keyboardListener);
            } else {
                viewTreeObserver.removeOnGlobalLayoutListener(keyboardListener);
            }
        }
        sKeyboardListeners.remove(str);
        sSystemConditionObservers.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addActivityAvailableListener(String str, ActivityAvailableListener activityAvailableListener) {
        sActivityAvailableListeners.put(str, activityAvailableListener);
        Activity activity = this.curActivity;
        if (activity != null) {
            activityAvailableListener.available(activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeActivityAvailableListener(String str) {
        sActivityAvailableListeners.remove(str);
    }

    public Activity getCurActivity() {
        return this.curActivity;
    }

    public void setCurActivity(Activity activity) {
        this.curActivity = activity;
        for (Map.Entry<String, ActivityAvailableListener> entry : sActivityAvailableListeners.entrySet()) {
            entry.getValue().available(this.curActivity);
        }
        try {
            ViewTreeObserver viewTreeObserver = this.curActivity.getWindow().getDecorView().getViewTreeObserver();
            for (Map.Entry<String, OSSystemConditionController.OSSystemConditionObserver> entry2 : sSystemConditionObservers.entrySet()) {
                KeyboardListener keyboardListener = new KeyboardListener(this, entry2.getValue(), entry2.getKey());
                viewTreeObserver.addOnGlobalLayoutListener(keyboardListener);
                sKeyboardListeners.put(entry2.getKey(), keyboardListener);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setNextResumeIsFirstActivity(boolean z) {
        this.nextResumeIsFirstActivity = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class KeyboardListener implements ViewTreeObserver.OnGlobalLayoutListener {
        private final String key;
        private final OSSystemConditionController.OSSystemConditionObserver observer;
        private final OSSystemConditionController.OSSystemConditionHandler systemConditionListener;

        private KeyboardListener(OSSystemConditionController.OSSystemConditionHandler oSSystemConditionHandler, OSSystemConditionController.OSSystemConditionObserver oSSystemConditionObserver, String str) {
            this.systemConditionListener = oSSystemConditionHandler;
            this.observer = oSSystemConditionObserver;
            this.key = str;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (OSViewUtils.isKeyboardUp(new WeakReference(OneSignal.getCurrentActivity()))) {
                return;
            }
            this.systemConditionListener.removeSystemConditionObserver(this.key, this);
            this.observer.systemConditionChanged();
        }
    }
}
