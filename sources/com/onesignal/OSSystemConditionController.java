package com.onesignal;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.onesignal.ActivityLifecycleHandler;
import com.onesignal.OneSignal;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes3.dex */
class OSSystemConditionController {
    private static final String TAG = "com.onesignal.OSSystemConditionController";
    private final OSSystemConditionObserver systemConditionObserver;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface OSSystemConditionHandler {
        void removeSystemConditionObserver(String str, ActivityLifecycleHandler.KeyboardListener keyboardListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface OSSystemConditionObserver {
        void systemConditionChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSSystemConditionController(OSSystemConditionObserver oSSystemConditionObserver) {
        this.systemConditionObserver = oSSystemConditionObserver;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean systemConditionsAvailable() {
        if (OneSignal.getCurrentActivity() == null) {
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.WARN, "OSSystemConditionObserver curActivity null");
            return false;
        }
        try {
            if (isDialogFragmentShowing(OneSignal.getCurrentActivity())) {
                OneSignal.onesignalLog(OneSignal.LOG_LEVEL.WARN, "OSSystemConditionObserver dialog fragment detected");
                return false;
            }
        } catch (NoClassDefFoundError e) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
            OneSignal.onesignalLog(log_level, "AppCompatActivity is not used in this app, skipping 'isDialogFragmentShowing' check: " + e);
        }
        ActivityLifecycleHandler activityLifecycleHandler = ActivityLifecycleListener.getActivityLifecycleHandler();
        boolean isKeyboardUp = OSViewUtils.isKeyboardUp(new WeakReference(OneSignal.getCurrentActivity()));
        if (isKeyboardUp && activityLifecycleHandler != null) {
            activityLifecycleHandler.addSystemConditionObserver(TAG, this.systemConditionObserver);
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.WARN, "OSSystemConditionObserver keyboard up detected");
        }
        return !isKeyboardUp;
    }

    boolean isDialogFragmentShowing(Context context) throws NoClassDefFoundError {
        if (context instanceof AppCompatActivity) {
            final FragmentManager supportFragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
            supportFragmentManager.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() { // from class: com.onesignal.OSSystemConditionController.1
                @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
                public void onFragmentDetached(FragmentManager fragmentManager, Fragment fragment) {
                    super.onFragmentDetached(fragmentManager, fragment);
                    if (fragment instanceof DialogFragment) {
                        supportFragmentManager.unregisterFragmentLifecycleCallbacks(this);
                        OSSystemConditionController.this.systemConditionObserver.systemConditionChanged();
                    }
                }
            }, true);
            List<Fragment> fragments = supportFragmentManager.getFragments();
            int size = fragments.size();
            if (size > 0) {
                Fragment fragment = fragments.get(size - 1);
                return fragment.isVisible() && (fragment instanceof DialogFragment);
            }
            return false;
        }
        return false;
    }
}
