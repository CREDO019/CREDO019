package com.onesignal;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.onesignal.ActivityLifecycleHandler;
import com.onesignal.AndroidSupportV4Compat;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class PermissionsActivity extends Activity {
    private static final int DELAY_TIME_CALLBACK_CALL = 500;
    private static final String INTENT_EXTRA_ANDROID_PERMISSION_STRING = "INTENT_EXTRA_ANDROID_PERMISSION_STRING";
    private static final String INTENT_EXTRA_CALLBACK_CLASS = "INTENT_EXTRA_CALLBACK_CLASS";
    private static final String INTENT_EXTRA_PERMISSION_TYPE = "INTENT_EXTRA_PERMISSION_TYPE";
    private static final int ONESIGNAL_PERMISSION_REQUEST_CODE = 2;
    private static final int REQUEST_SETTINGS = 3;
    private static final String TAG = "com.onesignal.PermissionsActivity";
    private static ActivityLifecycleHandler.ActivityAvailableListener activityAvailableListener;
    private static final HashMap<String, PermissionCallback> callbackMap = new HashMap<>();
    private static boolean fallbackToSettings;
    private static boolean neverAskAgainClicked;
    private static boolean waiting;
    private String androidPermissionString;
    private String permissionRequestType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface PermissionCallback {
        void onAccept();

        void onReject(boolean z);
    }

    public static void registerAsCallback(String str, PermissionCallback permissionCallback) {
        callbackMap.put(str, permissionCallback);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        OneSignal.initWithContext(this);
        handleBundleParams(getIntent().getExtras());
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleBundleParams(intent.getExtras());
    }

    private void handleBundleParams(Bundle bundle) {
        if (Build.VERSION.SDK_INT < 23) {
            finish();
            overridePendingTransition(C3644R.anim.onesignal_fade_in, C3644R.anim.onesignal_fade_out);
            return;
        }
        reregisterCallbackHandlers(bundle);
        this.permissionRequestType = bundle.getString(INTENT_EXTRA_PERMISSION_TYPE);
        String string = bundle.getString(INTENT_EXTRA_ANDROID_PERMISSION_STRING);
        this.androidPermissionString = string;
        requestPermission(string);
    }

    private void reregisterCallbackHandlers(Bundle bundle) {
        String string = bundle.getString(INTENT_EXTRA_CALLBACK_CLASS);
        try {
            Class.forName(string);
        } catch (ClassNotFoundException unused) {
            throw new RuntimeException("Could not find callback class for PermissionActivity: " + string);
        }
    }

    private void requestPermission(String str) {
        if (waiting) {
            return;
        }
        waiting = true;
        neverAskAgainClicked = !AndroidSupportV4Compat.ActivityCompat.shouldShowRequestPermissionRationale(this, str);
        AndroidSupportV4Compat.ActivityCompat.requestPermissions(this, new String[]{str}, 2);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int r3, String[] strArr, final int[] r5) {
        waiting = false;
        if (r3 == 2) {
            new Handler().postDelayed(new Runnable() { // from class: com.onesignal.PermissionsActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    int[] r0 = r5;
                    boolean z = false;
                    if (r0.length > 0 && r0[0] == 0) {
                        z = true;
                    }
                    PermissionCallback permissionCallback = (PermissionCallback) PermissionsActivity.callbackMap.get(PermissionsActivity.this.permissionRequestType);
                    if (permissionCallback == null) {
                        throw new RuntimeException("Missing handler for permissionRequestType: " + PermissionsActivity.this.permissionRequestType);
                    } else if (!z) {
                        permissionCallback.onReject(PermissionsActivity.this.shouldShowSettings());
                    } else {
                        permissionCallback.onAccept();
                    }
                }
            }, 500L);
        }
        ActivityLifecycleHandler activityLifecycleHandler = ActivityLifecycleListener.getActivityLifecycleHandler();
        if (activityLifecycleHandler != null) {
            activityLifecycleHandler.removeActivityAvailableListener(TAG);
        }
        finish();
        overridePendingTransition(C3644R.anim.onesignal_fade_in, C3644R.anim.onesignal_fade_out);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldShowSettings() {
        return fallbackToSettings && neverAskAgainClicked && !AndroidSupportV4Compat.ActivityCompat.shouldShowRequestPermissionRationale(this, this.androidPermissionString);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void startPrompt(boolean z, final String str, final String str2, final Class<?> cls) {
        if (waiting) {
            return;
        }
        fallbackToSettings = z;
        activityAvailableListener = new ActivityLifecycleHandler.ActivityAvailableListener() { // from class: com.onesignal.PermissionsActivity.2
            @Override // com.onesignal.ActivityLifecycleHandler.ActivityAvailableListener
            public void available(Activity activity) {
                if (activity.getClass().equals(PermissionsActivity.class)) {
                    return;
                }
                Intent intent = new Intent(activity, PermissionsActivity.class);
                intent.setFlags(131072);
                intent.putExtra(PermissionsActivity.INTENT_EXTRA_PERMISSION_TYPE, str).putExtra(PermissionsActivity.INTENT_EXTRA_ANDROID_PERMISSION_STRING, str2).putExtra(PermissionsActivity.INTENT_EXTRA_CALLBACK_CLASS, cls.getName());
                activity.startActivity(intent);
                activity.overridePendingTransition(C3644R.anim.onesignal_fade_in, C3644R.anim.onesignal_fade_out);
            }
        };
        ActivityLifecycleHandler activityLifecycleHandler = ActivityLifecycleListener.getActivityLifecycleHandler();
        if (activityLifecycleHandler != null) {
            activityLifecycleHandler.addActivityAvailableListener(TAG, activityAvailableListener);
        }
    }
}
